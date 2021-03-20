package net.tslat.aoa3.entity.animal;

import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAAnimal;

import javax.annotation.Nullable;

public class ElkanyneEntity extends AoAAnimal {
	public ElkanyneEntity(EntityType<? extends AnimalEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 1.05f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_ELKANYNE_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_ELKANYNE_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_ELKANYNE_HURT.get();
	}

	@Override
	protected boolean isBreedable() {
		return true;
	}

	@Nullable
	@Override
	protected Item getTemptItem() {
		return Item.byBlock(Blocks.BROWN_MUSHROOM);
	}

	@Nullable
	@Override
	public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity mate) {
		return new ElkanyneEntity(AoAEntities.Animals.ELKANYNE.get(), this.level);
	}
}
