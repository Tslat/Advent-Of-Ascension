package net.tslat.aoa3.entity.projectiles.arrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.weapon.bow.BaseBow;

public class EntitySpectralHollyArrow extends EntityHollyArrow {
	public EntitySpectralHollyArrow(World world) {
		super(world);
	}

	public EntitySpectralHollyArrow(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	public EntitySpectralHollyArrow(World world, BaseBow bow, EntityLivingBase shooter, double damageBase) {
		super(world, bow, shooter, damageBase);
	}

	protected void arrowHit(EntityLivingBase target) {
		super.arrowHit(target);

		PotionEffect potioneffect = new PotionEffect(MobEffects.GLOWING, 200, 0);
		target.addPotionEffect(potioneffect);
	}

	protected ItemStack getArrowStack() {
		return new ItemStack(ItemRegister.SPECTRAL_HOLLY_ARROW);
	}

	public void onUpdate() {
		super.onUpdate();

		if (world.isRemote && !inGround)
			world.spawnParticle(EnumParticleTypes.SPELL_INSTANT, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
	}
}
