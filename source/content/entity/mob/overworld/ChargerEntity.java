package net.tslat.aoa3.content.entity.mob.overworld;

import com.google.common.base.Suppliers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAEntityDataSerializers;
import net.tslat.aoa3.common.registration.entity.variant.ChargerVariant;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.object.EntityDataHolder;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableMeleeAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;

public class ChargerEntity extends AoAMeleeMob<ChargerEntity> {
	private static final EntityDataHolder<ChargerVariant> VARIANT = EntityDataHolder.register(ChargerEntity.class, AoAEntityDataSerializers.CHARGER_VARIANT.get(), ChargerVariant.PLAINS.get(), entity -> entity.variant, (entity, value) -> entity.variant = value);

	private ChargerVariant variant = ChargerVariant.PLAINS.get();

	public ChargerEntity(EntityType<? extends AoAMeleeMob> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();

		registerDataParams(VARIANT);
	}

	@Override
	public BrainActivityGroup<ChargerEntity> getFightTasks() {
		return BrainActivityGroup.fightTasks(
				new InvalidateAttackTarget<>().invalidateIf((entity, target) -> (target instanceof Player pl && pl.getAbilities().invulnerable) || distanceToSqr(target.position()) > Math.pow(getAttributeValue(Attributes.FOLLOW_RANGE), 2)),
				new SetWalkTargetToAttackTarget<>().speedMod((entity, target) -> 1.125f),
				new AnimatableMeleeAttack<>(getPreAttackTime()).attackInterval(entity -> getAttackSwingDuration()));
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @org.jetbrains.annotations.Nullable SpawnGroupData spawnData, @org.jetbrains.annotations.Nullable CompoundTag dataTag) {
		spawnData = super.finalizeSpawn(world, difficulty, reason, spawnData, dataTag);

		VARIANT.set(this, ChargerVariant.getVariantForSpawn(world.getLevel(), difficulty, reason, this, Suppliers.memoize(() -> level().getBiome(blockPosition())), spawnData, dataTag));

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

	public ChargerVariant getVariant() {
		return this.variant;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(AoAAnimations.genericWalkRunSwimIdleController(this),
				AoAAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_BITE));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);

		tag.putString("Variant", AoARegistries.CHARGER_VARIANTS.getKey(VARIANT.get(this)).toString());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);

		if (compound.contains("Variant", Tag.TAG_STRING))
			VARIANT.set(this, AoARegistries.CHARGER_VARIANTS.getEntry(ResourceLocation.tryParse(compound.getString("Variant"))));
	}

	@Override
	protected ResourceLocation getDefaultLootTable() {
		return getVariant().lootTable().orElseGet(super::getDefaultLootTable);
	}
}
