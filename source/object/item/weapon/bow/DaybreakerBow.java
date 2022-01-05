package net.tslat.aoa3.object.item.weapon.bow;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.object.entity.projectile.arrow.CustomArrowEntity;
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

		if (shooter.xRot < -70) {
			for (double x = -0.5; x <= 0.5; x += 0.5) {
				for (double z = -0.5; z <= 0.5; z += 0.5) {
					if (x == 0 && z == 0)
						continue;

					CustomArrowEntity arrow = CustomArrowEntity.fromArrow(centralArrow, this, shooter, dmg);

					arrow.pickup = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
					arrow.teleportTo(arrow.getX() + x, arrow.getY(), arrow.getZ() + z);
					arrow.level.addFreshEntity(arrow);
				}
			}
		}

		return centralArrow;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
