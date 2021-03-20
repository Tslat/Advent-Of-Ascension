package net.tslat.aoa3.item.weapon.blaster;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class VortexBlaster extends BaseBlaster {
	public VortexBlaster(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(dmg, durability, fireDelayTicks, energyCost);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_GRAVITY_BLASTER_FIRE.get();
	}

	@Override
	public void fire(ItemStack blaster, LivingEntity shooter) {
		float x = -MathHelper.sin(shooter.yRot / 180.0F * (float)Math.PI) * MathHelper.cos(shooter.xRot / 180.0F * (float)Math.PI);
		float y = -MathHelper.sin(shooter.xRot / 180.0F * (float)Math.PI);
		float z = MathHelper.cos(shooter.yRot / 180.0F * (float)Math.PI) * MathHelper.cos(shooter.xRot / 180.0F * (float)Math.PI);

		for (LivingEntity entity : shooter.level.getEntitiesOfClass(LivingEntity.class, shooter.getBoundingBox().inflate(x * 7 + 1, y * 7 + 1, z * 7 + 1))) {
			DamageUtil.doScaledKnockback(entity, shooter, 4f, shooter.getX() - entity.getX(), shooter.getZ() - entity.getZ());
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
