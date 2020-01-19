package net.tslat.aoa3.item.weapon.blaster;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.blaster.EntityWinderShot;
import net.tslat.aoa3.entity.projectiles.staff.BaseEnergyShot;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.PredicateUtil;

import javax.annotation.Nullable;
import java.util.List;

public class DarklyGuster extends BaseBlaster {
	public DarklyGuster(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(dmg, durability, fireDelayTicks, energyCost);
		setTranslationKey("DarklyGuster");
		setRegistryName("aoa3:darkly_guster");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.gunDarkGun;
	}

	@Override
	public void fire(ItemStack blaster, EntityLivingBase shooter) {
		shooter.world.spawnEntity(new EntityWinderShot(shooter, this, 60));
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, EntityLivingBase shooter) {
		List<Entity> nearbyTargets = shot.world.getEntitiesWithinAABBExcludingEntity(target, target.getEntityBoundingBox().grow(3, 1, 3));

		nearbyTargets.removeIf(entity -> !(entity instanceof EntityLivingBase) || !PredicateUtil.IS_HOSTILE_MOB.apply((EntityLivingBase)entity));
		nearbyTargets.add(target);

		float splitDmg = (float)(baseDmg / nearbyTargets.size() * (Math.pow(1.05, nearbyTargets.size())));
		boolean success = false;

		for (Entity entity : nearbyTargets) {
			success |= EntityUtil.dealBlasterDamage(shooter, entity, shot, splitDmg, false);
		}

		return success;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.WhimsyWinder.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
