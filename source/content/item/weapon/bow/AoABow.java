package net.tslat.aoa3.content.item.weapon.bow;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.EventHooks;
import net.tslat.aoa3.common.registration.item.AoADataComponents;
import net.tslat.aoa3.content.item.ArrowFiringWeapon;
import net.tslat.aoa3.content.item.datacomponent.BowStats;
import net.tslat.aoa3.util.EnchantmentUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.sound.SoundBuilder;
import net.tslat.tme.api.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AoABow extends BowItem implements ArrowFiringWeapon {
	public AoABow(Item.Properties properties) {
		super(properties);
	}

	public BowStats getBowStats(ItemStack bowStack) {
		return bowStack.get(AoADataComponents.BOW_STATS.get());
	}

	public float getBowDamage(ItemStack bowStack) {
		return getBowStats(bowStack).damage();
	}

	public float getDrawSpeedMultiplier(ItemStack bowStack) {
		return getBowStats(bowStack).drawSpeedModifier();
	}

	@Override
	public int getEnchantmentValue() {
		return 8;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		ItemStack heldStack = player.getItemInHand(hand);
		boolean hasAmmo = !findAmmo(player, heldStack, player.hasInfiniteMaterials()).isEmpty();
		InteractionResultHolder<ItemStack> eventResult = EventHooks.onArrowNock(heldStack, world, player, hand, hasAmmo);

		if (eventResult != null)
			return eventResult;

		if (!player.hasInfiniteMaterials() && !hasAmmo)
			return InteractionResultHolder.fail(heldStack);

		player.startUsingItem(hand);

		return InteractionResultHolder.consume(heldStack);
	}

	@Override
	public void releaseUsing(ItemStack stack, Level level, LivingEntity shooter, int timeLeft) {
		boolean infiniteAmmo = (shooter instanceof Player pl && pl.hasInfiniteMaterials()) || EnchantmentUtil.hasEnchantment(level, stack, Enchantments.INFINITY);
		ItemStack ammoStack = findAmmo(shooter, stack, infiniteAmmo);

		if (ammoStack.isEmpty())
			return;

		int charge = (int)((getUseDuration(stack, shooter) - timeLeft) * getDrawSpeedMultiplier(stack));

		if (shooter instanceof Player pl)
			charge = EventHooks.onArrowLoose(stack, level, pl, charge, true);

		if (charge < 0)
			return;

		float power = getPowerForTime(charge);

		if (power < 0.1f)
			return;

		List<ItemStack> projectileItems = drawProjectileItems(stack, ammoStack, shooter, power, false);

		if (!projectileItems.isEmpty() && level instanceof ServerLevel serverLevel)
			fireArrows(stack, this, ammoStack, projectileItems, serverLevel, shooter, power, getBowDamage(stack), infiniteAmmo);

		if (!level.isClientSide)
			SoundBuilder.at(SoundEvents.ARROW_SHOOT, shooter).pitch(1f / RandomUtil.valueBetween(1.2f, 1.6f) + power * 0.5F).play();

		if (shooter instanceof Player pl)
			pl.awardStat(Stats.ITEM_USED.get(this));
	}

	@Override
	protected void shoot(ServerLevel level, LivingEntity shooter, InteractionHand hand, ItemStack weapon, List<ItemStack> projectileItems, float velocity, float inaccuracy, boolean isCrit, @Nullable LivingEntity target) {
		ItemStack ammo = projectileItems.isEmpty() ? shooter instanceof Player pl ? getDefaultCreativeAmmo(pl, weapon) : Items.ARROW.getDefaultInstance() : projectileItems.getFirst();
		boolean infiniteAmmo = (shooter instanceof Player pl && pl.hasInfiniteMaterials()) || EnchantmentUtil.hasEnchantment(level, weapon, Enchantments.INFINITY);

		fireArrows(weapon, this, ammo, projectileItems, level, shooter, velocity / 3f, getBowDamage(weapon), infiniteAmmo);
	}

	@Override
	public final void shootProjectile(LivingEntity shooter, Projectile projectile, int index, float velocity, float inaccuracy, float angle, @Nullable LivingEntity target) {
		shootProjectile(shooter, projectile, index, velocity, inaccuracy, 0, angle, target);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(1, LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.ARROW_DAMAGE, LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, Component.literal(Float.toString(getBowDamage(stack)))));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.BOW_DRAW_TIME, LocaleUtil.ItemDescriptionType.NEUTRAL, Component.literal(Double.toString(((int)(72000 / getDrawSpeedMultiplier(stack)) / 720) / 100d))));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.AMMO_ITEM, LocaleUtil.ItemDescriptionType.ITEM_AMMO_COST, LocaleUtil.getLocaleMessage(Items.ARROW.getDescriptionId())));
	}
}