package net.tslat.aoa3.item.weapon.bow;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.projectile.arrow.CustomArrowEntity;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class DaybreakerBow extends BaseBow {
	public DaybreakerBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
	}

	@Override
	protected CustomArrowEntity makeArrow(LivingEntity shooter, ItemStack bowStack, ItemStack ammoStack, float velocity, boolean consumeAmmo) {
		CustomArrowEntity centralArrow = super.makeArrow(shooter, bowStack, ammoStack, velocity, consumeAmmo);

		if (shooter.rotationPitch < -70) {
			for (double x = -0.5; x <= 0.5; x += 0.5) {
				for (double z = -0.5; z <= 0.5; z += 0.5) {
					if (x == 0 && z == 0)
						continue;

					CustomArrowEntity arrow = CustomArrowEntity.fromArrow(centralArrow, this, shooter, dmg);

					arrow.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
					arrow.setPositionAndUpdate(arrow.getPosX() + x, arrow.getPosY(), arrow.getPosZ() + z);
					arrow.world.addEntity(arrow);
				}
			}
		}

		return centralArrow;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.addInformation(stack, world, tooltip, flag);
	}
}
