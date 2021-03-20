package net.tslat.aoa3.item.weapon.bow;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.projectile.arrow.CustomArrowEntity;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class NightmareBow extends BaseBow {
	public NightmareBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
	}

	@Override
	protected CustomArrowEntity makeArrow(LivingEntity shooter, ItemStack bowStack, ItemStack ammoStack, float velocity, boolean consumeAmmo) {
		double xOffset = MathHelper.cos(shooter.yRot / 180.0F * (float)Math.PI) * 0.7F;
		double zOffset = MathHelper.sin(shooter.yRot / 180.0F * (float)Math.PI) * 0.7F;

		CustomArrowEntity centralArrow = super.makeArrow(shooter, bowStack, ammoStack, velocity, consumeAmmo);
		CustomArrowEntity leftArrow = CustomArrowEntity.fromArrow(centralArrow, this, shooter, getDamage());
		CustomArrowEntity rightArrow = CustomArrowEntity.fromArrow(centralArrow, this, shooter, getDamage());

		leftArrow.pickup = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
		rightArrow.pickup = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;

		leftArrow.teleportTo(leftArrow.getX() + xOffset, leftArrow.getY(), leftArrow.getZ() + zOffset);
		rightArrow.teleportTo(rightArrow.getX() - xOffset, rightArrow.getY(), rightArrow.getZ() - zOffset);
		shooter.level.addFreshEntity(leftArrow);
		shooter.level.addFreshEntity(rightArrow);

		return centralArrow;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
