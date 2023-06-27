package net.tslat.aoa3.content.item.weapon.bow;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.content.entity.projectile.arrow.CustomArrowEntity;
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

		if (shooter.getXRot() < -70) {
			for (double x = -0.5; x <= 0.5; x += 0.5) {
				for (double z = -0.5; z <= 0.5; z += 0.5) {
					if (x == 0 && z == 0)
						continue;

					CustomArrowEntity arrow = CustomArrowEntity.fromArrow(centralArrow, this, shooter, dmg);

					arrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
					arrow.teleportTo(arrow.getX() + x, arrow.getY(), arrow.getZ() + z);
					arrow.level().addFreshEntity(arrow);
				}
			}
		}

		return centralArrow;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
