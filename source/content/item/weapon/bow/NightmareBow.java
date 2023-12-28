package net.tslat.aoa3.content.item.weapon.bow;

import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.content.entity.projectile.arrow.CustomArrowEntity;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NightmareBow extends BaseBow {
	public NightmareBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
	}

	@Override
	protected CustomArrowEntity makeArrow(LivingEntity shooter, ItemStack bowStack, ItemStack ammoStack, float velocity, boolean consumeAmmo) {
		double xOffset = Mth.cos(shooter.getYRot() / 180.0F * (float)Math.PI) * 0.7F;
		double zOffset = Mth.sin(shooter.getYRot() / 180.0F * (float)Math.PI) * 0.7F;

		CustomArrowEntity centralArrow = super.makeArrow(shooter, bowStack, ammoStack, velocity, consumeAmmo);
		CustomArrowEntity leftArrow = CustomArrowEntity.fromArrow(centralArrow, this, shooter, getDamage());
		CustomArrowEntity rightArrow = CustomArrowEntity.fromArrow(centralArrow, this, shooter, getDamage());

		leftArrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
		rightArrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;

		leftArrow.teleportTo(leftArrow.getX() + xOffset, leftArrow.getY(), leftArrow.getZ() + zOffset);
		rightArrow.teleportTo(rightArrow.getX() - xOffset, rightArrow.getY(), rightArrow.getZ() - zOffset);
		shooter.level().addFreshEntity(leftArrow);
		shooter.level().addFreshEntity(rightArrow);

		return centralArrow;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
