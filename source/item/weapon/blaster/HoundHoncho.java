package net.tslat.aoa3.item.weapon.blaster;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.entity.projectiles.blaster.EntityBearShot;

public class HoundHoncho extends BaseBlaster {
	public HoundHoncho(double dmg, SoundEvent sound, int durability, int fireDelayTicks, float energyCost) {
		super(dmg, sound, durability, fireDelayTicks, energyCost);
		setUnlocalizedName("HoundHoncho");
		setRegistryName("aoa3:hound_honcho");
	}

	@Override
	public void fire(ItemStack blaster, EntityLivingBase shooter) {
		shooter.world.spawnEntity(new EntityBearShot(shooter, this, 60));
	}
}
