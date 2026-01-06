package net.tslat.aoa3.content.item.weapon.crossbow;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.item.AoADataComponents;
import net.tslat.aoa3.content.item.ArrowFiringWeapon;
import net.tslat.aoa3.content.item.datacomponent.CrossbowStats;
import net.tslat.aoa3.util.EnchantmentUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.sound.SoundBuilder;
import net.tslat.tme.api.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AoACrossbow extends CrossbowItem implements ArrowFiringWeapon {
	public AoACrossbow(Item.Properties properties) {
		super(properties.component(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY));
	}

	public CrossbowStats getCrossbowStats() {
		return getCrossbowStats(getDefaultInstance());
	}

	public CrossbowStats getCrossbowStats(ItemStack stack) {
		return stack.get(AoADataComponents.CROSSBOW_STATS.get());
	}

	public float getCrossbowDamage(ItemStack stack) {
		return getCrossbowStats(stack).damage();
	}

	public float getChargeSpeedModifier(ItemStack stack) {
		return getCrossbowStats(stack).chargeSpeedModifier();
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.CROSSBOW;
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity entity) {
		return getTimeToReload(stack, entity) + 3;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		ChargedProjectiles loadedProjectiles = stack.get(DataComponents.CHARGED_PROJECTILES);

		if (loadedProjectiles != null && !loadedProjectiles.isEmpty()) {
			performShooting(level, player, hand, stack, getShootingPower(loadedProjectiles), 1, null);

			return InteractionResultHolder.consume(stack);
		}

		if (findAmmo(player, stack, player.hasInfiniteMaterials()).isEmpty())
			return InteractionResultHolder.fail(stack);

		this.startSoundPlayed = false;
		this.midLoadSoundPlayed = false;
		player.startUsingItem(hand);

		return InteractionResultHolder.consume(stack);
	}

	@Override
	public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
		if (!level.isClientSide) {
			ChargingSounds chargeSounds = getChargingSounds(stack);
			float chargeTime = (float)(stack.getUseDuration(livingEntity) - remainingUseDuration) / (float)getTimeToReload(stack, livingEntity);

			if (chargeTime < 0.2F) {
				this.startSoundPlayed = false;
				this.midLoadSoundPlayed = false;
			}
			else if (chargeTime >= 0.2F) {
				if (!this.startSoundPlayed) {
					this.startSoundPlayed = true;
					chargeSounds.start().ifPresent((sound) -> SoundBuilder.at(sound, livingEntity).volume(0.5f).play());
				}

				if (chargeTime >= 0.5f && !this.midLoadSoundPlayed) {
					this.midLoadSoundPlayed = true;
					chargeSounds.mid().ifPresent((sound) -> SoundBuilder.at(sound, livingEntity).volume(0.5f).play());
				}
			}
		}
	}

	@Override
	public void releaseUsing(ItemStack stack, Level level, LivingEntity shooter, int ticksRemaining) {
		int ticksCharged = getUseDuration(stack, shooter) - ticksRemaining;
		float chargePower = getPowerForTime(ticksCharged, stack, shooter);

		if (chargePower >= 1 && !isCharged(stack) && tryLoadProjectiles(shooter, stack))
			SoundBuilder.at(SoundEvents.CROSSBOW_LOADING_END, shooter).pitch(1 / RandomUtil.valueBetween(1f, 1.5f) + 0.2f).play();
	}

	protected boolean tryLoadProjectiles(LivingEntity shooter, ItemStack stack) {
		boolean infiniteAmmo = (shooter instanceof Player pl && pl.hasInfiniteMaterials()) || EnchantmentUtil.hasEnchantment(shooter.level(), stack, Enchantments.INFINITY);
		ItemStack ammoStack = findAmmo(shooter, stack, infiniteAmmo);

		if (ammoStack.isEmpty())
			return false;

		List<ItemStack> projectileItems = drawProjectileItems(stack, ammoStack, shooter, 1, false);

		if (projectileItems.isEmpty())
			return false;

		stack.set(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.of(projectileItems));

		return true;
	}

	protected float getPowerForTime(int useTicks, ItemStack pCrossbowStack, LivingEntity shooter) {
		return Math.min(1, (float)useTicks / (float) getTimeToReload(pCrossbowStack, shooter));
	}

	public int getTimeToReload(ItemStack stack, LivingEntity shooter) {
		float chargeTicks = EnchantmentHelper.modifyCrossbowChargingTime(stack, shooter, 1.25F / (stack.getItem() instanceof AoACrossbow crossbow ? crossbow.getChargeSpeedModifier(stack) : 1f));

		return Mth.floor(chargeTicks * 20f);
	}

	@Override
	protected void shoot(ServerLevel level, LivingEntity shooter, InteractionHand hand, ItemStack weapon, List<ItemStack> projectileItems, float velocity, float inaccuracy, boolean isCrit, @Nullable LivingEntity target) {
		ItemStack ammo = projectileItems.isEmpty() ? shooter instanceof Player pl ? getDefaultCreativeAmmo(pl, weapon) : Items.ARROW.getDefaultInstance() : projectileItems.getFirst();
		boolean infiniteAmmo = (shooter instanceof Player pl && pl.hasInfiniteMaterials()) || EnchantmentUtil.hasEnchantment(level, weapon, Enchantments.INFINITY);

		fireArrows(weapon, this, ammo, projectileItems, level, shooter, velocity / 3f, getCrossbowDamage(weapon), infiniteAmmo);
	}

	@Override
	public final void shootProjectile(LivingEntity shooter, Projectile projectile, int index, float velocity, float inaccuracy, float angle, @Nullable LivingEntity target) {
		shootProjectile(shooter, projectile, index, velocity, inaccuracy, 0, angle, target);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(1, LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.ARROW_DAMAGE, LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, Component.literal(Double.toString(getCrossbowDamage(stack)))));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.AMMO_ITEM, LocaleUtil.ItemDescriptionType.ITEM_AMMO_COST, Component.translatable(Items.ARROW.getDescriptionId())));
		super.appendHoverText(stack, context, tooltip, tooltipFlag);
	}
}
