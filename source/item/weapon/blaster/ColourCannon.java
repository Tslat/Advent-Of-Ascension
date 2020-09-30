package net.tslat.aoa3.item.weapon.blaster;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.blaster.EntityRainbowShot;

import javax.annotation.Nullable;

public class ColourCannon extends BaseBlaster {
	public ColourCannon(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(dmg, durability, fireDelayTicks, energyCost);
		setTranslationKey("ColourCannon");
		setRegistryName("aoa3:colour_cannon");
		setCreativeTab(null);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.COLOUR_CANNON_FIRE;
	}

	@Override
	public void fire(ItemStack blaster, EntityLivingBase shooter) {
		shooter.world.spawnEntity(new EntityRainbowShot(shooter, this, 120));
	}
}
