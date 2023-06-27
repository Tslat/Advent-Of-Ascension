package net.tslat.aoa3.content.entity.mob.misc;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.entity.AoAMiscEntities;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.builder.EntityPredicate;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableMeleeAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.look.LookAtTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.TargetOrRetaliate;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.HurtBySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyLivingEntitySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyPlayersSensor;
import net.tslat.smartbrainlib.util.EntityRetrievalUtil;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;

import java.util.List;

public class ThornyPlantSproutEntity extends AoAMeleeMob<ThornyPlantSproutEntity> {
	public ThornyPlantSproutEntity(EntityType<? extends ThornyPlantSproutEntity> entityType, Level world) {
		super(entityType, world);

		setPersistenceRequired();
		this.attackReach = 2;
	}

	public ThornyPlantSproutEntity(Level world, BlockPos plantPos) {
		this(AoAMiscEntities.THORNY_PLANT_SPROUT.get(), world);

		moveTo(plantPos.getX() + 0.5f + rand().nextGaussian() * 0.1f, plantPos.getY() + 0.1f ,plantPos.getZ() + 0.5f + rand().nextGaussian() * 0.1f, rand().nextFloat() * 360, 0);
	}

	@Override
	public BrainActivityGroup<ThornyPlantSproutEntity> getCoreTasks() {
		return BrainActivityGroup.coreTasks(new LookAtTarget<>());
	}

	@Override
	public BrainActivityGroup<ThornyPlantSproutEntity> getIdleTasks() {
		return BrainActivityGroup.idleTasks(new TargetOrRetaliate<>().useMemory(MemoryModuleType.NEAREST_VISIBLE_ATTACKABLE_PLAYER));
	}

	@Override
	public BrainActivityGroup<ThornyPlantSproutEntity> getFightTasks() {
		return BrainActivityGroup.fightTasks(
				new InvalidateAttackTarget<>(),
				new AnimatableMeleeAttack<>(getPreAttackTime()).attackInterval(entity -> getAttackSwingDuration()));
	}

	@Override
	public List<ExtendedSensor<ThornyPlantSproutEntity>> getSensors() {
		return ObjectArrayList.of(
				new NearbyPlayersSensor<>(),
				new NearbyLivingEntitySensor<ThornyPlantSproutEntity>()
						.setPredicate((target, entity) -> target.getType() != entity.getType() && (target instanceof Monster || (target instanceof OwnableEntity tamedEntity && tamedEntity.getOwnerUUID() != null))).setScanRate(entity -> 20),
				new HurtBySensor<>());
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return 1.4f;
	}

	@Override
	protected void customServerAiStep() {
		if (tickCount % 5 == 0 && level().getBlockState(BlockPos.containing(getX(), getY() + 0.25f, getZ())).getBlock() != AoABlocks.THORNY_PLANT_CROP.get())
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

		if (level().getBlockState(blockPosition().above()).getBlock() == AoABlocks.THORNY_PLANT_CROP.get() && EntityRetrievalUtil.getEntities(this.level(), getBoundingBox(), new EntityPredicate<>(this).is(getType())).isEmpty())
			level().destroyBlock(blockPosition().above(), true);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(
				new AnimationController<>(this, "living", 0, event -> event.setAndContinue(this.tickCount < 20 ? DefaultAnimations.SPAWN : DefaultAnimations.IDLE)),
				AoAAnimations.genericAttackAnimation(this, AoAAnimations.ATTACK_SWIPE_RIGHT));
	}
}
