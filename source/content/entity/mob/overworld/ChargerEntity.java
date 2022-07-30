package net.tslat.aoa3.content.entity.mob.overworld;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.IExtensibleEnum;
import net.minecraftforge.common.Tags;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.ai.mob.ExtendedFindNearbyTargetGoal;
import net.tslat.aoa3.content.entity.ai.mob.ExtendedMeleeAttackGoal;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.content.world.gen.BiomeMatcher;
import net.tslat.aoa3.library.builder.EntityPredicate;
import net.tslat.aoa3.library.object.CachedFunction;
import software.bernie.geckolib3.core.manager.AnimationData;

import javax.annotation.Nullable;
import java.util.function.Function;

public class ChargerEntity extends AoAMeleeMob {
	private static final EntityDataAccessor<String> TYPE = SynchedEntityData.defineId(ChargerEntity.class, EntityDataSerializers.STRING);

	private Type type = null;

	public ChargerEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();

		getEntityData().define(TYPE, Type.DEFAULT.name);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new FloatGoal(this));
		goalSelector.addGoal(2, new ExtendedMeleeAttackGoal<>(this).attackInterval(ConstantInt.of(getCurrentSwingDuration())).speedModifier(1.125f).actionTelegraphTicks(getPreAttackTime()).onStart(goal -> setSprinting(true)).onStop(goal -> setSprinting(false)));
		goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1));
		goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8f));
		goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		targetSelector.addGoal(1, new HurtByTargetGoal(this));
		targetSelector.addGoal(2, new ExtendedFindNearbyTargetGoal<>(this, true, EntityPredicate.SURVIVAL_PLAYER).searchRadius(16));
	}

	@org.jetbrains.annotations.Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @org.jetbrains.annotations.Nullable SpawnGroupData spawnData, @org.jetbrains.annotations.Nullable CompoundTag dataTag) {
		spawnData = super.finalizeSpawn(world, difficulty, reason, spawnData, dataTag);

		if (world.getLightEngine().getRawBrightness(blockPosition(), 0) == 0) {
			getEntityData().set(TYPE, Type.VOID.name);
		}
		else {
			Holder<Biome> biome = world.getBiome(blockPosition());

			for (Type type : Type.values()) {
				if (type != Type.DEFAULT && type.canSpawnType(world.getLevel(), blockPosition(), biome)) {
					getEntityData().set(TYPE, type.name);

					break;
				}
			}
		}

		return spawnData;
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return size.height * 0.85f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_CHARGER_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_CHARGER_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_CHARGER_HURT.get();
	}

	@Override
	protected int getAttackSwingDuration() {
		return 11;
	}

	@Override
	protected int getPreAttackTime() {
		return 7;
	}

	public Type chargerType() {
		if (this.type != null)
			return this.type;

		this.type = Type.fromString(getEntityData().get(TYPE));

		return this.type;
	}

	@Override
	public void registerControllers(AnimationData animationData) {
		animationData.addAnimationController(AoAAnimations.genericWalkRunSwimIdleController(this));
		animationData.addAnimationController(AoAAnimations.genericAttackController(this, AoAAnimations.ATTACK_BITE));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);

		tag.putString("ChargerType", chargerType().name);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);

		if (compound.contains("ChargerType")) {
			Type type = Type.fromString(compound.getString("ChargerType"));
			this.type = type;

			getEntityData().set(TYPE, type.name);
		}
	}

	@Override
	protected ResourceLocation getDefaultLootTable() {
		return chargerType() == Type.VOID ? AdventOfAscension.id("entities/void_charger") : super.getDefaultLootTable();
	}

	public enum Type implements IExtensibleEnum {
		DEFAULT("", null),
		DESERT("desert", level -> new BiomeMatcher.Builder(level).mustBe(Tags.Biomes.IS_HOT).atLeastOneOf(Tags.Biomes.IS_SANDY).build()),
		HILL("hill", level -> new BiomeMatcher.Builder(level).mustBe(Tags.Biomes.IS_MOUNTAIN).cannotBe(Tags.Biomes.IS_SNOWY).build()),
		SEA("sea", level -> new BiomeMatcher.Builder(level).atLeastOneOf(BiomeTags.IS_OCEAN, BiomeTags.IS_RIVER, BiomeTags.IS_BEACH).cannotBe(Tags.Biomes.IS_SNOWY).build()),
		SNOW("snow", level -> new BiomeMatcher.Builder(level).mustBe(Tags.Biomes.IS_SNOWY).build()),
		SWAMP("swamp", level -> new BiomeMatcher.Builder(level).mustBe(Tags.Biomes.IS_SWAMP).cannotBe(Tags.Biomes.IS_SNOWY).build()),
		VOID("void", null) {
			@Override
			public boolean canSpawnType(ServerLevel level, BlockPos position, Holder<Biome> biome) {
				return level.getRawBrightness(position, 0) == 0;
			}
		};

		public final String name;
		@Nullable
		private final CachedFunction<ServerLevel, BiomeMatcher> spawnBiomeMatcher;

		Type(String prefix, @Nullable Function<ServerLevel, BiomeMatcher> spawnBiomeMatcher) {
			this.name = prefix;
			this.spawnBiomeMatcher = spawnBiomeMatcher == null ? null : CachedFunction.of(spawnBiomeMatcher);
		}

		public boolean canSpawnType(ServerLevel level, BlockPos position, Holder<Biome> biome) {
			return spawnBiomeMatcher == null || spawnBiomeMatcher.apply(level).test(biome);
		}

		public static Type fromString(String typeName) {
			for (Type type : values()) {
				if (type.name.equals(typeName))
					return type;
			}

			return DEFAULT;
		}

		// Use this to create additional variants of chargers if you're an addon creator
		public static Type create(String name, String prefix, @Nullable Function<ServerLevel, BiomeMatcher> spawnBiomeMatcher) {
			throw new IllegalStateException("Enum not extended");
		}
	}
}
