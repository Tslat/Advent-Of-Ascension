package net.tslat.aoa3.item.weapon.blaster;

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
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;

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
		float x = (-MathHelper.sin(shooter.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(shooter.rotationPitch / 180.0F * (float)Math.PI)) * 5;
		float y = (-MathHelper.sin(shooter.rotationPitch / 180.0F * (float)Math.PI)) * 5;
		float z = (MathHelper.cos(shooter.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(shooter.rotationPitch / 180.0F * (float)Math.PI)) * 5;

		AreaEffectCloudEntity cloud = new AreaEffectCloudEntity(shooter.world, shooter.getPosX() + x, shooter.getPosY() + shooter.getEyeHeight() + y, shooter.getPosZ() + z);

		cloud.setRadius(4);
		cloud.setWaitTime(0);
		cloud.setDuration(100);
		cloud.setColor(NumberUtil.RGB(51, 102, 0));
		cloud.addEffect(new EffectInstance(Effects.POISON, 200, 1, false, true));
		cloud.setOwner(shooter);
		shooter.world.addEntity(cloud);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.addInformation(stack, world, tooltip, flag);
	}
}
