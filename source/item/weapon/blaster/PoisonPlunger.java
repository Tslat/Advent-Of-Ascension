package net.tslat.aoa3.item.weapon.blaster;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;
import java.util.List;

public class PoisonPlunger extends BaseBlaster {
	public PoisonPlunger(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(dmg, durability, fireDelayTicks, energyCost);
		setTranslationKey("PoisonPlunger");
		setRegistryName("aoa3:poison_plunger");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.GAS_GUN_FIRE;
	}

	@Override
	public void fire(ItemStack blaster, EntityLivingBase shooter) {
		float x = (-MathHelper.sin(shooter.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(shooter.rotationPitch / 180.0F * (float)Math.PI)) * 5;
		float y = (-MathHelper.sin(shooter.rotationPitch / 180.0F * (float)Math.PI)) * 5;
		float z = (MathHelper.cos(shooter.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(shooter.rotationPitch / 180.0F * (float)Math.PI)) * 5;

		EntityAreaEffectCloud cloud = new EntityAreaEffectCloud(shooter.world, shooter.posX + x, shooter.posY + shooter.getEyeHeight() + y, shooter.posZ + z);

		cloud.setRadius(4);
		cloud.setWaitTime(0);
		cloud.setDuration(100);
		cloud.setColor(Enums.RGBIntegers.TOXIC_GREEN);
		cloud.addEffect(new PotionEffect(MobEffects.POISON, 200, 1, false, true));
		cloud.setOwner(shooter);
		shooter.world.spawnEntity(cloud);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.PoisonPlunger.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
