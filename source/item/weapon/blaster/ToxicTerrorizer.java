package net.tslat.aoa3.item.weapon.blaster;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.blaster.EntityToxicShot;
import net.tslat.aoa3.entity.projectiles.staff.BaseEnergyShot;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;
import java.util.List;

public class ToxicTerrorizer extends BaseBlaster {
	public ToxicTerrorizer(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(dmg, durability, fireDelayTicks, energyCost);
		setTranslationKey("ToxicTerrorizer");
		setRegistryName("aoa3:toxic_terrorizer");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.gunMagicGun;
	}

	@Override
	public void fire(ItemStack blaster, EntityLivingBase shooter) {
		shooter.world.spawnEntity(new EntityToxicShot(shooter, this, 60));
		shooter.world.spawnEntity(new EntityToxicShot(shooter, this, 60, -0.05f, -0.05f, 0f));
		shooter.world.spawnEntity(new EntityToxicShot(shooter, this, 60, 0.05f, -0.05f, 0f));
		shooter.world.spawnEntity(new EntityToxicShot(shooter, this, 60, 0, -0.05f, -0.05f));
		shooter.world.spawnEntity(new EntityToxicShot(shooter, this, 60, 0, -0.05f, 0.05f));
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, EntityLivingBase shooter) {
		if (target instanceof EntityLivingBase && !EntityUtil.isTypeImmune(target, Enums.MobProperties.BLASTER_IMMUNE))
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.POISON, 185, 1, false, true));

		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("items.description.damage.poison", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
