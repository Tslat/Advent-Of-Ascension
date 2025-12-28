package net.tslat.aoa3.content.entity.monster.misc;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.entity.AoAEntityStats;
import net.tslat.aoa3.common.registration.entity.AoAMiscEntities;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableMeleeAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.look.LookAtTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.TargetOrRetaliate;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.HurtBySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyLivingEntitySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyPlayersSensor;
import net.tslat.tme.api.util.EntityRetrievalUtil;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.constant.DefaultAnimations;

import java.util.List;

public class ThornyPlantSproutEntity extends AoAMeleeMob<ThornyPlantSproutEntity> {
	private boolean isCrop = false;

	public ThornyPlantSproutEntity(EntityType<? extends ThornyPlantSproutEntity> entityType, Level world) {
		super(entityType, world);

		setPersistenceRequired();
		this.attackReach = 2;
	}

	public ThornyPlantSproutEntity(Level world, BlockPos plantPos) {
		this(AoAMiscEntities.THORNY_PLANT_SPROUT.get(), world);

		moveTo(plantPos.getX() + 0.5f + rand().nextGaussian() * 0.1f, plantPos.getY() + 0.1f ,plantPos.getZ() + 0.5f + rand().nextGaussian() * 0.1f, rand().nextFloat() * 360, 0);
		this.isCrop = true;
	}

	@Override
	public BrainActivityGroup<ThornyPlantSproutEntity> getCoreTasks() {
		return BrainActivityGroup.coreTasks(new LookAtTarget<>());
	}

	@Override
	public BrainActivityGroup<ThornyPlantSproutEntity> getIdleTasks() {
		return BrainActivityGroup.idleTasks(new TargetOrRetaliate<>()
				.useMemory(MemoryModuleType.NEAREST_VISIBLE_ATTACKABLE_PLAYER)
				.attackablePredicate(target -> DamageUtil.isAttackable(target) && !isAlliedTo(target)));
	}

	@Override
	public BrainActivityGroup<ThornyPlantSproutEntity> getFightTasks() {
		return BrainActivityGroup.fightTasks(
				new InvalidateAttackTarget<>().invalidateIf((entity, target) -> !DamageUtil.isAttackable(target) || distanceToSqr(target.position()) > Mth.square(getAttributeValue(Attributes.FOLLOW_RANGE))),
				new AnimatableMeleeAttack<>(getPreAttackTime()).attackInterval(entity -> getAttackSwingDuration() + 2));
	}

	@Override
	public List<ExtendedSensor<? extends ThornyPlantSproutEntity>> getSensors() {
		return ObjectArrayList.of(
				new NearbyPlayersSensor<>(),
				new NearbyLivingEntitySensor<ThornyPlantSproutEntity>()
						.setPredicate((target, entity) -> target.getType() != entity.getType() && (target instanceof Monster || (target instanceof OwnableEntity tamedEntity && tamedEntity.getOwnerUUID() != null))).setScanRate(entity -> 20),
				new HurtBySensor<>());
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);

		compound.putBoolean("IsCropSprout", this.isCrop);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);

		if (compound.contains("IsCropSprout", Tag.TAG_BYTE))
			this.isCrop = compound.getBoolean("IsCropSprout");
	}

	@Override
	protected void customServerAiStep() {
		if (this.isCrop && this.tickCount % 5 == 0 && level().getBlockState(BlockPos.containing(getX(), getY() + 0.25f, getZ())).getBlock() != AoABlocks.THORNY_PLANT_CROP.get())
			discard();
	}

	public static boolean checkSpawnConditions(EntityType<?> entityType, LevelAccessor levelAccessor, MobSpawnType spawnReason, BlockPos blockPos, RandomSource rand) {
		if (!(levelAccessor instanceof Level level) || (spawnReason != MobSpawnType.STRUCTURE && spawnReason != MobSpawnType.NATURAL))
			return true;

		return levelAccessor.getBlockState(blockPos.below()).is(BlockTags.DIRT) && EntityRetrievalUtil.getEntities(level, new AABB(blockPos.getX() - 15, blockPos.getY() - 10, blockPos.getZ() - 15, blockPos.getX() + 15, blockPos.getY() + 10, blockPos.getZ() + 15), ThornyPlantSproutEntity.class::isInstance).isEmpty();
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
	public boolean canChangeDimensions(Level from, Level to) {
		return false;
	}

	@Override
	protected int getAttackSwingDuration() {
		return 15;
	}

	@Override
	public void remove(RemovalReason reason) {
		super.remove(reason);

		if (!level().isClientSide) {
			if (level().getBlockState(blockPosition().above()).is(AoABlocks.THORNY_PLANT_CROP) && EntityRetrievalUtil.getEntities(this, 0, ThornyPlantSproutEntity.class).isEmpty())
				level().destroyBlock(blockPosition().above(), true);
		}
	}

	public static AoAEntityStats.AttributeBuilder entityStats(EntityType<ThornyPlantSproutEntity> entityType) {
		return AoAEntityStats.AttributeBuilder.createMonster(entityType)
				.health(50)
				.moveSpeed(0)
				.meleeStrength(8)
				.knockbackResist(1)
				.followRange(8);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(
				new AnimationController<>(this, "living", 0, event -> event.setAndContinue(this.tickCount < 20 ? DefaultAnimations.SPAWN : DefaultAnimations.IDLE)),
				DefaultAnimations.genericAttackAnimation(this, AoAAnimations.ATTACK_SWIPE_RIGHT).transitionLength(0));
	}
}
