package net.tslat.aoa3.content.item.weapon.bow;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.projectile.arrow.CustomArrowEntity;
import net.tslat.aoa3.content.entity.projectile.arrow.PopShotEntity;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.smartbrainlib.util.RandomUtil;

import javax.annotation.Nullable;
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
			WorldUtil.createExplosion(shooter, shot.level, shot, 1.0f);

		shot.discard();
	}

	@Override
	public void onBlockHit(CustomArrowEntity shot, BlockHitResult rayTrace, Entity shooter) {
		if (shot instanceof PopShotEntity && ((PopShotEntity)shot).isExplosive)
			WorldUtil.createExplosion(shooter, shot.level, shot, 1.0f);

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
	public CustomArrowEntity doArrowMods(CustomArrowEntity arrow, LivingEntity shooter, ItemStack ammoStack, int useTicksRemaining) {
		PopShotEntity popShot = new PopShotEntity(arrow.level, this, shooter, dmg, ammoStack.getItem() instanceof ArrowItem);

		popShot.shootFromRotation(shooter, shooter.getXRot(), shooter.getYRot(), 0, BowItem.getPowerForTime((int)(72000 / drawSpeedMultiplier - useTicksRemaining)) * 2, 1);

		return popShot;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(1, LocaleUtil.getFormattedItemDescriptionText("items.description.damage.arrows", LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, Component.literal(Double.toString(dmg))));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.bow.drawSpeed", LocaleUtil.ItemDescriptionType.NEUTRAL, Component.literal(Double.toString(((72000 / drawSpeedMultiplier) / 720) / (double)100))));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.AMMO_ITEM, LocaleUtil.ItemDescriptionType.ITEM_AMMO_COST, LocaleUtil.getLocaleMessage(AoAItems.POP_SHOT.get().getDescriptionId()).append(Component.literal("/")).append(LocaleUtil.getLocaleMessage(Items.FLINT.getDescriptionId()))));
	}
}
