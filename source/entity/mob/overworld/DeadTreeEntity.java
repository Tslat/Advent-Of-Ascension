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
import net.minecraft.world.IServerWorld;
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
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		absMoveTo(((int)getX()) + 0.5d, getY(), ((int)getZ()) + 0.5d, 0, 0);

		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	@Override
	protected void registerGoals() {}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 2.4f;
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (source == DamageSource.OUT_OF_WORLD) {
			remove();

			return true;
		}

		if (!level.isClientSide && isAlive() && source.getDirectEntity() instanceof PlayerEntity) {
			TreeSpiritEntity treeSpirit = new TreeSpiritEntity(AoAEntities.Mobs.TREE_SPIRIT.get(), level);

			treeSpirit.moveTo(getX(), getY(), getZ(), yRot, xRot);
			level.addFreshEntity(treeSpirit);
			treeSpirit.hurt(source, amount);

			if (treeSpirit.getHealth() <= 0)
				AdvancementUtil.completeAdvancement((ServerPlayerEntity)source.getDirectEntity(), new ResourceLocation("aoa3", "overworld/mightiest_tree_in_the_forest"), "tree_spirit_instakill");

			level.playSound(null, getX(), getY(), getZ(), AoASounds.ENTITY_TREE_SPIRIT_AMBIENT.get(), SoundCategory.HOSTILE, 1.0f, 1.0f);
			remove();

			return true;
		}

		return false;
	}

	@Override
	public boolean isPushedByFluid() {
		return false;
	}

	@Override
	public void playerTouch(PlayerEntity entityIn) {}

	@Override
	protected void doPush(Entity entityIn) {}

}