package net.tslat.aoa3.entity.animals;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAAnimal;

import javax.annotation.Nullable;

public class EntityElkanyne extends AoAAnimal {
	public static final float entityWidth = 0.85f;

	public EntityElkanyne(World world) {
		super(world, entityWidth, 1.1875f);
	}

	@Override
	public float getEyeHeight() {
		return 1.05f;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 40;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.1d;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobElkanyneLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobElkanyneDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobElkanyneHit;
	}

	@Override
	protected boolean isBreedable() {
		return true;
	}

	@Nullable
	@Override
	protected Item getTemptItem() {
		return Item.getItemFromBlock(Blocks.BROWN_MUSHROOM);
	}

	@Nullable
	@Override
	public EntityAgeable createChild(EntityAgeable mate) {
		return new EntityElkanyne(world);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityElkanyne;
	}
}
