package net.tslat.aoa3.content.item.weapon.bow;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.event.ForgeEventFactory;
import net.tslat.aoa3.content.entity.projectile.arrow.CustomArrowEntity;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.smartbrainlib.util.RandomUtil;

import javax.annotation.Nullable;
import java.util.List;

public class BaseBow extends BowItem {
	protected float drawSpeedMultiplier;
	protected double dmg;

	public BaseBow(double damage, float drawSpeedMultiplier, int durability) {
		super(new Item.Properties().durability(durability));

		this.dmg = damage;
		this.drawSpeedMultiplier = drawSpeedMultiplier;
	}

	public double getDamage() {
		return dmg;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		ItemStack heldStack = player.getItemInHand(hand);
		boolean hasAmmo = !findAmmo(player, heldStack, player.isCreative()).isEmpty();
		InteractionResultHolder<ItemStack> arrowNockEventResult = ForgeEventFactory.onArrowNock(heldStack, world, player, hand, hasAmmo);

		if (arrowNockEventResult != null)
			return arrowNockEventResult;

		if (!player.isCreative() && !hasAmmo) {
			return InteractionResultHolder.fail(heldStack);
		}
		else {
			player.startUsingItem(hand);
			return InteractionResultHolder.consume(heldStack);
		}
	}

	public void releaseUsing(ItemStack stack, Level world, LivingEntity shooter, int timeLeft) {
		if (!(shooter instanceof Player))
			return;

		Player pl = (Player)shooter;
		boolean infiniteAmmo = pl.isCreative() || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, stack) > 0;
		ItemStack ammoStack = findAmmo(pl, stack, infiniteAmmo);
		int charge = (int)((getUseDuration(stack) - timeLeft) * getDrawSpeedMultiplier());
		charge = ForgeEventFactory.onArrowLoose(stack, world, pl, charge, !ammoStack.isEmpty() || infiniteAmmo);

		if (charge < 0)
			return;

		if (!ammoStack.isEmpty() || infiniteAmmo) {
			if (ammoStack.isEmpty())
				ammoStack = new ItemStack(Items.ARROW);

			float velocity = getPowerForTime(charge);

			if ((double)velocity >= 0.1D) {
				if (!world.isClientSide) {
					CustomArrowEntity arrow = makeArrow(pl, stack, ammoStack, velocity, !infiniteAmmo);

					arrow = doArrowMods(arrow, shooter, ammoStack, timeLeft);
					world.addFreshEntity(arrow);
				}

				world.playSound(null, pl.getX(), pl.getY(), pl.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (float)RandomUtil.randomValueBetween(1.2f, 1.6f) + velocity * 0.5F);

				if (!infiniteAmmo && !pl.getAbilities().instabuild) {
					ammoStack.shrink(1);

					if (ammoStack.isEmpty())
						pl.getInventory().removeItem(ammoStack);
				}

				pl.awardStat(Stats.ITEM_USED.get(this));
			}
		}
	}

	protected ItemStack findAmmo(Player shooter, ItemStack bowStack, boolean infiniteAmmo) {
		return shooter.getProjectile(bowStack);
	}

	protected CustomArrowEntity makeArrow(LivingEntity shooter, ItemStack bowStack, ItemStack ammoStack, float velocity, boolean consumeAmmo) {
		ArrowItem arrowItem = (ArrowItem)(ammoStack.getItem() instanceof ArrowItem ? ammoStack.getItem() : Items.ARROW);
		CustomArrowEntity arrow = CustomArrowEntity.fromArrow(arrowItem.createArrow(shooter.level, ammoStack, shooter), this, shooter, getDamage());

		arrow.shootFromRotation(shooter, shooter.getXRot(), shooter.getYRot(), 0.0F, velocity * 3.0F, 1.0F);

		if (velocity == 1.0F)
			arrow.setCritArrow(true);

		int powerEnchant = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, bowStack);
		int punchEnchant = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, bowStack);

		if (powerEnchant > 0)
			arrow.setBaseDamage(arrow.getBaseDamage() + powerEnchant * 1.5D + 1D);

		if (punchEnchant > 0)
			arrow.setKnockback(punchEnchant);

		if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, bowStack) > 0)
			arrow.setSecondsOnFire(100);

		bowStack.hurtAndBreak(1, shooter, (firingEntity) -> firingEntity.broadcastBreakEvent(shooter.getUsedItemHand()));

		if (!consumeAmmo || (shooter instanceof Player && ((Player)shooter).isCreative()) && (ammoStack.getItem() == Items.SPECTRAL_ARROW || ammoStack.getItem() == Items.TIPPED_ARROW))
			arrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;

		return arrow;
	}

	public float getDrawSpeedMultiplier() {
		return drawSpeedMultiplier;
	}

	public CustomArrowEntity doArrowMods(CustomArrowEntity arrow, LivingEntity shooter, ItemStack ammoStack, int useTicksRemaining) {
		return arrow;
	}

	public void onEntityHit(CustomArrowEntity arrow, Entity target, Entity shooter, double damage, float drawStrength) {}

	public void onBlockHit(CustomArrowEntity arrow, BlockHitResult rayTrace, Entity shooter) {}

	public void onArrowTick(CustomArrowEntity arrow, Entity shooter) {}

	public double getArrowDamage(CustomArrowEntity arrow, Entity target, double currentDamage, float drawStrength, boolean isCritical) {
		double damage = currentDamage * 0.5d * (drawStrength / 3f);

		if (isCritical)
			damage += damage + (damage * RandomUtil.randomScaledGaussianValue(0.35f));

		return damage;
	}

	@Override
	public int getEnchantmentValue() {
		return 8;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(1, LocaleUtil.getFormattedItemDescriptionText("items.description.damage.arrows", LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, Component.literal(Double.toString(getDamage()))));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.bow.drawSpeed", LocaleUtil.ItemDescriptionType.NEUTRAL, Component.literal(Double.toString(((int)(72000 / getDrawSpeedMultiplier()) / 720) / (double)100))));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.AMMO_ITEM, LocaleUtil.ItemDescriptionType.ITEM_AMMO_COST, LocaleUtil.getLocaleMessage(Items.ARROW.getDescriptionId())));
	}
}