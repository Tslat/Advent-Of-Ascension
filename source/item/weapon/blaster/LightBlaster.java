package net.tslat.aoa3.item.weapon.blaster;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.entity.projectiles.blaster.EntityLightBlasterShot;

public class LightBlaster extends BaseBlaster {
	public LightBlaster(double dmg, SoundEvent sound, int durability, int fireDelayTicks, float energyCost) {
		super(dmg, sound, durability, fireDelayTicks, energyCost);
		setUnlocalizedName("LightBlaster");
		setRegistryName("aoa3:light_blaster");
	}

	@Override
	public void fire(ItemStack blaster, EntityLivingBase shooter) {
		shooter.world.spawnEntity(new EntityLightBlasterShot(shooter, this, 60));
	}
}
