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
		double xOffset = MathHelper.cos(shooter.rotationYaw / 180.0F * (float)Math.PI) * 0.7F;
		double zOffset = MathHelper.sin(shooter.rotationYaw / 180.0F * (float)Math.PI) * 0.7F;

		CustomArrowEntity centralArrow = super.makeArrow(shooter, bowStack, ammoStack, velocity, consumeAmmo);
		CustomArrowEntity leftArrow = CustomArrowEntity.fromArrow(centralArrow, this, shooter, getDamage());
		CustomArrowEntity rightArrow = CustomArrowEntity.fromArrow(centralArrow, this, shooter, getDamage());

		leftArrow.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
		rightArrow.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;

		leftArrow.setPositionAndUpdate(leftArrow.getPosX() + xOffset, leftArrow.getPosY(), leftArrow.getPosZ() + zOffset);
		rightArrow.setPositionAndUpdate(rightArrow.getPosX() - xOffset, rightArrow.getPosY(), rightArrow.getPosZ() - zOffset);
		shooter.world.addEntity(leftArrow);
		shooter.world.addEntity(rightArrow);

		return centralArrow;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.addInformation(stack, world, tooltip, flag);
	}
}
