package net.tslat.aoa3.entity.mob.overworld;

import net.minecraft.entity.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.AdvancementUtil;

import javax.annotation.Nullable;

public class DeadTreeEntity extends AoAMeleeMob {
	public DeadTreeEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Nullable
	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		setPositionAndRotation(((int)getPosX()) + 0.5d, getPosY(), ((int)getPosZ()) + 0.5d, 0, 0);

		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	@Override
	protected void registerGoals() {}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 2.4f;
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 1.0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 0.5;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 0;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0;
	}

	@Override
	protected boolean isDaylightMob() {
		return true;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (source == DamageSource.OUT_OF_WORLD) {
			remove();

			return true;
		}

		if (!world.isRemote && isAlive() && source.getImmediateSource() instanceof PlayerEntity) {
			TreeSpiritEntity treeSpirit = new TreeSpiritEntity(AoAEntities.Mobs.TREE_SPIRIT.get(), world);

			treeSpirit.setLocationAndAngles(getPosX(), getPosY(), getPosZ(), rotationYaw, rotationPitch);
			world.addEntity(treeSpirit);
			treeSpirit.attackEntityFrom(source, amount);

			if (treeSpirit.getHealth() <= 0)
				AdvancementUtil.completeAdvancement((ServerPlayerEntity)source.getImmediateSource(), new ResourceLocation("aoa3", "overworld/mightiest_tree_in_the_forest"), "tree_spirit_instakill");

			world.playSound(null, getPosX(), getPosY(), getPosZ(), AoASounds.ENTITY_TREE_SPIRIT_AMBIENT.get(), SoundCategory.HOSTILE, 1.0f, 1.0f);
			remove();

			return true;
		}

		return false;
	}

	@Override
	public boolean isPushedByWater() {
		return false;
	}

	@Override
	public void onCollideWithPlayer(PlayerEntity entityIn) {}

	@Override
	protected void collideWithEntity(Entity entityIn) {}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}
}