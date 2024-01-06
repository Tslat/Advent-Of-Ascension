package net.tslat.aoa3.content.entity.mob.overworld;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.IExtensibleEnum;
import net.minecraftforge.common.Tags;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.content.world.gen.BiomeMatcher;
import net.tslat.aoa3.library.object.CachedFunction;
import net.tslat.aoa3.library.object.EntityDataHolder;
import net.tslat.aoa3.util.HolidayUtil;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableMeleeAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;

import java.util.Random;
import java.util.function.Function;

public class ChargerEntity extends AoAMeleeMob<ChargerEntity> {
	private static final EntityDataHolder<String> TYPE = EntityDataHolder.register(ChargerEntity.class, EntityDataSerializers.STRING, Type.DEFAULT.name, entity -> entity.type.name, (entity, value) -> entity.type = Type.fromString(value));

	private Type type = Type.DEFAULT;

	public ChargerEntity(EntityType<? extends AoAMeleeMob> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();

		registerDataParams(TYPE);
	}

	@Override
	public BrainActivityGroup<ChargerEntity> getFightTasks() {
		return BrainActivityGroup.fightTasks(
				new InvalidateAttackTarget<>().invalidateIf((entity, target) -> (target instanceof Player pl && pl.getAbilities().invulnerable) || distanceToSqr(target.position()) > Math.pow(getAttributeValue(Attributes.FOLLOW_RANGE), 2)),
				new SetWalkTargetToAttackTarget<>().speedMod((entity, target) -> 1.125f),
				new AnimatableMeleeAttack<>(getPreAttackTime()).attackInterval(entity -> getAttackSwingDuration()));
	}

	@org.jetbrains.annotations.Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @org.jetbrains.annotations.Nullable SpawnGroupData spawnData, @org.jetbrains.annotations.Nullable CompoundTag dataTag) {
		spawnData = super.finalizeSpawn(world, difficulty, reason, spawnData, dataTag);

		if (world.getLightEngine().getRawBrightness(blockPosition(), 0) == 0) {
			TYPE.set(this, Type.VOID.name);
		}
		else {
			Holder<Biome> biome = world.getBiome(blockPosition());

			for (Type type : Type.values()) {
				if (type != Type.DEFAULT && type.canSpawnType(world.getLevel(), blockPosition(), biome)) {
					TYPE.set(this, type.name);

					break;
				}
			}
		}

		return spawnData;
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return 1.275f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_CHARGER_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_CHARGER_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
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

	public Type getVariant() {
		return this.type;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(AoAAnimations.genericWalkRunSwimIdleController(this),
				AoAAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_BITE));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);

		tag.putString("ChargerType", TYPE.get(this));
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);

		if (compound.contains("ChargerType"))
			TYPE.set(this, compound.getString("ChargerType"));
	}

	@Override
	protected ResourceLocation getDefaultLootTable() {
		return getVariant() == Type.VOID ? AdventOfAscension.id("entities/void_charger") : super.getDefaultLootTable();
	}

	public enum Type implements IExtensibleEnum {
		DEFAULT("", null),
		RAVEN("raven", null) {
			@Override
			public boolean canSpawnType(ServerLevel level, BlockPos position, Holder<Biome> biome) {
				return HolidayUtil.isHalloween() && new Random(level.getGameTime() ^ position.asLong()).nextInt(3) == 0;
			}
		},
		ZOMBIE("zombie", null) {
			@Override
			public boolean canSpawnType(ServerLevel level, BlockPos position, Holder<Biome> biome) {
				return HolidayUtil.isHalloween() && new Random(level.getGameTime() ^ position.asLong()).nextInt(3) == 1;
			}
		},
		SKELETON("skeleton", null) {
			@Override
			public boolean canSpawnType(ServerLevel level, BlockPos position, Holder<Biome> biome) {
				return HolidayUtil.isHalloween() && new Random(level.getGameTime() ^ position.asLong()).nextInt(3) == 2;
			}
		},
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

		// Use this to create additional variants of Chargers if you're an addon creator
		public static Type create(String name, String prefix, @Nullable Function<ServerLevel, BiomeMatcher> spawnBiomeMatcher) {
			throw new IllegalStateException("Enum not extended");
		}
	}
}
