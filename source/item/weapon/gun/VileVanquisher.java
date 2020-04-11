package net.tslat.aoa3.item.weapon.gun;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;
import java.util.List;

public class VileVanquisher extends BaseGun {
	public VileVanquisher(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
		setTranslationKey("VileVanquisher");
		setRegistryName("aoa3:vile_vanquisher");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.gunFastRifle;
	}

	@Override
	protected void doImpactEffect(Entity target, EntityLivingBase shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		if (target.isDead || (target instanceof EntityLivingBase && ((EntityLivingBase)target).getHealth() <= 0)) {
			EntityAreaEffectCloud cloud = new EntityAreaEffectCloud(bullet.world, (target.posX + bullet.posX) / 2d, (target.posY + bullet.posY) / 2d, (target.posZ + bullet.posZ) / 2d);

			cloud.setRadius(0.5f);
			cloud.setDuration(10);
			cloud.setRadiusPerTick(0.45f);
			cloud.setWaitTime(0);
			cloud.setColor(Enums.RGBIntegers.TOXIC_GREEN);
			cloud.addEffect(new PotionEffect(MobEffects.POISON, 150, 0, false, true));

			bullet.world.spawnEntity(cloud);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.VileVanquisher.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
