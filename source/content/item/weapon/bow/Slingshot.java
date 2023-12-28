package net.tslat.aoa3.content.item.weapon.bow;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.projectile.arrow.CustomArrowEntity;
import net.tslat.aoa3.content.entity.projectile.arrow.PopShotEntity;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public class Slingshot extends BaseBow {
	public static final Predicate<ItemStack> AMMO_PREDICATE = stack -> stack.getItem() == AoAItems.POP_SHOT.get() || stack.getItem() == Items.FLINT;

	private final float drawSpeedMultiplier;
	private final double dmg;

	public Slingshot(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);

		this.drawSpeedMultiplier = drawSpeedMultiplier;
		this.dmg = damage;
	}

	@Override
	public void onEntityHit(CustomArrowEntity shot, Entity target, Entity shooter, double damage, float drawStrength) {
		if (shot instanceof PopShotEntity && ((PopShotEntity)shot).isExplosive)
			WorldUtil.createExplosion(shooter, shot.level(), shot, 1.0f);

		shot.discard();
	}

	@Override
	public void onBlockHit(CustomArrowEntity shot, BlockHitResult rayTrace, Entity shooter) {
		if (shot instanceof PopShotEntity && ((PopShotEntity)shot).isExplosive)
			WorldUtil.createExplosion(shooter, shot.level(), shot, 1.0f);

		shot.discard();
	}

	@Override
	public Predicate<ItemStack> getAllSupportedProjectiles() {
		return AMMO_PREDICATE;
	}

	@Override
	public double getArrowDamage(CustomArrowEntity arrow, Entity target, double currentDamage, float drawStrength, boolean isCritical) {
		double damage = currentDamage * (drawStrength / 2f);

		if (isCritical)
			damage += damage + (damage * RandomUtil.randomScaledGaussianValue(0.35f));

		return damage;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return super.canApplyAtEnchantingTable(stack, enchantment) && enchantment != Enchantments.PUNCH_ARROWS && enchantment != Enchantments.FLAMING_ARROWS;
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return super.isBookEnchantable(stack, book);
	}

	@Override
	protected CustomArrowEntity makeArrow(LivingEntity shooter, ItemStack bowStack, ItemStack ammoStack, float velocity, boolean consumeAmmo) {
		PopShotEntity popShot = new PopShotEntity(shooter.level(), this, shooter, dmg, ammoStack.getItem() instanceof ArrowItem);
		int powerEnchant = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, bowStack);

		popShot.shootFromRotation(shooter, shooter.getXRot(), shooter.getYRot(), 0, velocity * 2, 1);

		if (powerEnchant > 0)
			popShot.setBaseDamage(popShot.getBaseDamage() + powerEnchant * 1.5D + 1D);

		return popShot;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(1, LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.ARROW_DAMAGE, LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, Component.literal(Double.toString(dmg))));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.BOW_DRAW_TIME, LocaleUtil.ItemDescriptionType.NEUTRAL, Component.literal(Double.toString(((72000 / drawSpeedMultiplier) / 720) / (double)100))));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.AMMO_ITEM, LocaleUtil.ItemDescriptionType.ITEM_AMMO_COST, LocaleUtil.getLocaleMessage(AoAItems.POP_SHOT.get().getDescriptionId()).append(Component.literal("/")).append(LocaleUtil.getLocaleMessage(Items.FLINT.getDescriptionId()))));
	}
}
