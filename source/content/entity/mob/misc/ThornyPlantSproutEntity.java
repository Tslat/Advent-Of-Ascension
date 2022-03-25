package net.tslat.aoa3.content.entity.mob.misc;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.entity.ai.mob.AnimatableMeleeAttackGoal;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.manager.AnimationData;

public class ThornyPlantSproutEntity extends AoAMeleeMob {
	private static final AnimationBuilder ATTACK_LEFT = new AnimationBuilder().addAnimation("attack.swipe_left", false);
	private static final AnimationBuilder ATTACK_RIGHT = new AnimationBuilder().addAnimation("attack.swipe_right", false);

	public ThornyPlantSproutEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);

		setPersistenceRequired();
	}

	public ThornyPlantSproutEntity(World world, BlockPos plantPos) {
		this(AoAEntities.Misc.THORNY_PLANT_SPROUT.get(), world);

		moveTo(plantPos.getX() + 0.5f + random.nextGaussian() * 0.1f, plantPos.getY() + 0.1f ,plantPos.getZ() + 0.5f + random.nextGaussian() * 0.1f, random.nextFloat() * 360, 0);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 1.4f;
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(2, new AnimatableMeleeAttackGoal<AoAMeleeMob>(this).preAttackTime(getPreAttackTime()).attackInterval(getCurrentSwingDuration()).attackReach(2f));
		goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8f));
		goalSelector.addGoal(8, new LookRandomlyGoal(this));
		targetSelector.addGoal(1, new HurtByTargetGoal(this));
		targetSelector.addGoal(2, new NearestAttackableTargetGoal<MonsterEntity>(this, MonsterEntity.class, 5, true, true, entity -> entity.getType() != this.getType()));
		targetSelector.addGoal(3, new NearestAttackableTargetGoal<PlayerEntity>(this, PlayerEntity.class, 5, true, true, null));
	}

	@Override
	protected void customServerAiStep() {
		if (tickCount % 5 == 0 && level.getBlockState(new BlockPos(getX(), getY() + 0.25f, getZ())).getBlock() != AoABlocks.THORNY_PLANT_CROP.get())
			remove();
	}

	@Override
	public void move(MoverType pType, Vector3d pPos) {}

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
	public void remove(boolean keepData) {
		super.remove(keepData);

		if (level.getBlockState(blockPosition().above()).getBlock() == AoABlocks.THORNY_PLANT_CROP.get() && level.getEntities(this, getBoundingBox(), entity -> entity.getType() == getType()).isEmpty())
			level.destroyBlock(blockPosition().above(), true);
	}

	@Override
	public void registerControllers(AnimationData animationData) {
		animationData.addAnimationController(new AnimationController<ThornyPlantSproutEntity>(this, "living", 0, event -> {
			if (tickCount < 20) {
				event.getController().setAnimation(AoAAnimations.SPAWN);
			}
			else {
				event.getController().setAnimation(AoAAnimations.IDLE);
			}

			return PlayState.CONTINUE;
		}));
		animationData.addAnimationController(new AnimationController<ThornyPlantSproutEntity>(this, "attacking", 0, event -> {
			if (swinging) {
				event.getController().setAnimation(ATTACK_RIGHT);

				return PlayState.CONTINUE;
			}

			event.getController().markNeedsReload();
			return PlayState.STOP;
		}));
	}
}
