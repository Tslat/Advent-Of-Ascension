package net.tslat.aoa3.content.entity.mob.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.entity.AoAMiscEntities;
import net.tslat.aoa3.content.entity.ai.mob.TelegraphedMeleeAttackGoal;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.builder.EntityPredicate;
import net.tslat.smartbrainlib.api.util.EntityRetrievalUtil;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.manager.AnimationData;

public class ThornyPlantSproutEntity extends AoAMeleeMob {
	public ThornyPlantSproutEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);

		setPersistenceRequired();
	}

	public ThornyPlantSproutEntity(Level world, BlockPos plantPos) {
		this(AoAMiscEntities.THORNY_PLANT_SPROUT.get(), world);

		moveTo(plantPos.getX() + 0.5f + random.nextGaussian() * 0.1f, plantPos.getY() + 0.1f ,plantPos.getZ() + 0.5f + random.nextGaussian() * 0.1f, random.nextFloat() * 360, 0);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 1.4f;
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(2, new TelegraphedMeleeAttackGoal<AoAMeleeMob>(this).preAttackTime(getPreAttackTime()).attackInterval(getCurrentSwingDuration()).attackReach(2f));
		goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8f));
		goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		targetSelector.addGoal(1, new HurtByTargetGoal(this));
		targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Monster.class, 5, true, true, entity -> entity.getType() != this.getType()));
		targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, 5, true, true, null));
	}

	@Override
	protected void customServerAiStep() {
		if (tickCount % 5 == 0 && level.getBlockState(new BlockPos(getX(), getY() + 0.25f, getZ())).getBlock() != AoABlocks.THORNY_PLANT_CROP.get())
			discard();
	}

	@Override
	public void move(MoverType pType, Vec3 pPos) {}

	@Override
	public void push(double pX, double pY, double pZ) {}

	@Override
	protected boolean shouldDespawnInPeaceful() {
		return false;
	}

	@Override
	public boolean isPushedByFluid() {
		return false;
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	protected int getAttackSwingDuration() {
		return 15;
	}

	@Override
	public void remove(RemovalReason reason) {
		super.remove(reason);

		if (level.getBlockState(blockPosition().above()).getBlock() == AoABlocks.THORNY_PLANT_CROP.get() && EntityRetrievalUtil.getEntities(this.level, getBoundingBox(), new EntityPredicate<>(this).is(getType())).isEmpty())
			level.destroyBlock(blockPosition().above(), true);
	}

	@Override
	public void registerControllers(AnimationData animationData) {
		animationData.addAnimationController(new AnimationController<>(this, "living", 0, event -> {
			if (tickCount < 20) {
				event.getController().setAnimation(AoAAnimations.SPAWN);
			}
			else {
				event.getController().setAnimation(AoAAnimations.IDLE);
			}

			return PlayState.CONTINUE;
		}));
		animationData.addAnimationController(new AnimationController<>(this, "attacking", 0, event -> {
			if (swinging) {
				event.getController().setAnimation(AoAAnimations.ATTACK_SWIPE_RIGHT);

				return PlayState.CONTINUE;
			}

			event.getController().markNeedsReload();
			return PlayState.STOP;
		}));
	}
}
