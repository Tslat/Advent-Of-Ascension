package net.tslat.aoa3.object.item.weapon.blaster;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class PoisonPlunger extends BaseBlaster {
	public PoisonPlunger(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(dmg, durability, fireDelayTicks, energyCost);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_GAS_GUN_FIRE.get();
	}

	@Override
	public void fire(ItemStack blaster, LivingEntity shooter) {
		float x = (-MathHelper.sin(shooter.yRot / 180.0F * (float)Math.PI) * MathHelper.cos(shooter.xRot / 180.0F * (float)Math.PI)) * 5;
		float y = (-MathHelper.sin(shooter.xRot / 180.0F * (float)Math.PI)) * 5;
		float z = (MathHelper.cos(shooter.yRot / 180.0F * (float)Math.PI) * MathHelper.cos(shooter.xRot / 180.0F * (float)Math.PI)) * 5;

		AreaEffectCloudEntity cloud = new AreaEffectCloudEntity(shooter.level, shooter.getX() + x, shooter.getY() + shooter.getEyeHeight() + y, shooter.getZ() + z);

		cloud.setRadius(4);
		cloud.setWaitTime(0);
		cloud.setDuration(100);
		cloud.setFixedColor(ColourUtil.RGB(51, 102, 0));
		cloud.addEffect(new EffectInstance(Effects.POISON, 200, 1, false, true));
		cloud.setOwner(shooter);
		shooter.level.addFreshEntity(cloud);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}