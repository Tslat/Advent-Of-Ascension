package net.tslat.aoa3.content.entity.npc.ambient;

import com.google.common.base.Suppliers;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.Tags;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAEntityDataSerializers;
import net.tslat.aoa3.common.registration.entity.AoAEntityStats;
import net.tslat.aoa3.common.registration.entity.variant.DryadSpriteVariant;
import net.tslat.aoa3.content.entity.base.AoAAmbientNPC;
import net.tslat.tme.api.util.RandomUtil;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.internal.networking.packet.TMEParticlePacket;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.constant.DefaultAnimations;

import java.util.Optional;
import java.util.UUID;

public class DryadSpriteEntity extends AoAAmbientNPC {
	private static final EntityDataAccessor<DryadSpriteVariant> VARIANT = makeSynchedData(DryadSpriteEntity.class, AoAEntityDataSerializers.DRYAD_SPRITE_VARIANT.get());
	private static final EntityDataAccessor<Optional<UUID>> OWNER = makeSynchedData(DryadSpriteEntity.class, EntityDataSerializers.OPTIONAL_UUID);
	private static final EntityDataAccessor<Integer> SUCCESS_TIMER = makeSynchedData(DryadSpriteEntity.class, EntityDataSerializers.INT);

	public DryadSpriteEntity(EntityType<? extends DryadSpriteEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);

		builder.define(VARIANT, DryadSpriteVariant.WOOD.get());
		builder.define(SUCCESS_TIMER, -1);
		builder.define(OWNER, Optional.empty());
	}

	@Override
	public boolean shouldBeSaved() {
		return super.shouldBeSaved() && getSynchedData(SUCCESS_TIMER) == -1;
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData) {
		spawnData = super.finalizeSpawn(world, difficulty, reason, spawnData);

		setSynchedData(VARIANT, DryadSpriteVariant.getVariantForSpawn(world.getLevel(), difficulty, reason, this, Suppliers.memoize(() -> level().getBiome(blockPosition())), spawnData));

		return spawnData;
	}

	@Nullable
	@Override
	protected String getInteractMessage(ItemStack heldItem) {
		if (heldItem.isEmpty())
			return getType().getDescriptionId() + ".interact.empty";

		if (!heldItem.getItem().canPerformAction(heldItem, ItemAbilities.HOE_TILL))
			return getType().getDescriptionId() + ".interact.incorrect";

		return null;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (!source.is(Tags.DamageTypes.IS_TECHNICAL))
			return false;

		return super.hurt(source, amount);
	}

	public void setOwner(ServerPlayer owner) {
		setSynchedData(OWNER, Optional.of(owner.getUUID()));
	}

	@Override
	protected InteractionResult mobInteract(Player player, InteractionHand hand) {
		if (isAlive() && isOwner(player) && getSynchedData(SUCCESS_TIMER) == -1) {
			ItemStack heldStack = player.getItemInHand(hand);

			if (getSynchedData(OWNER).isEmpty() && player instanceof ServerPlayer owner)
				setOwner(owner);

			if (heldStack.canPerformAction(ItemAbilities.HOE_TILL)) {
				if (getVariant().isCorrectOffering(heldStack)) {
					if (!level().isClientSide()) {
						setSynchedData(SUCCESS_TIMER, 44);
						player.awardKillScore(this, 1, this.level().damageSources().playerAttack(player));
						navigation.stop();
						setDeltaMovement(0, 0, 0);
					}
				}
				else if (!level().isClientSide) {
					discard();

					level().playSound(null, getX(), getY(), getZ(), AoASounds.ENTITY_DRYAD_SPRITE_UNHAPPY.get(), SoundSource.PLAYERS, 1, 1);

					for(int i = 0; i < 20; ++i) {
						ParticleBuilder.forRandomPosInEntity(ParticleTypes.ANGRY_VILLAGER, this)
								.velocity(RandomUtil.scaledGaussianValue(0.02d), RandomUtil.scaledGaussianValue(0.02d), RandomUtil.scaledGaussianValue(0.02d))
								.sendToAllPlayersTrackingEntity(this);
					}
				}

				return InteractionResult.SUCCESS;
			}
		}

		return super.mobInteract(player, hand);
	}

	public DryadSpriteVariant getVariant() {
		return getSynchedData(VARIANT);
	}

	@Override
	public void checkDespawn() {
		super.checkDespawn();

		if (!isRemoved() && tickCount > 100 && getSynchedData(SUCCESS_TIMER) == -1)
			discard();
	}

	@Override
	protected void customServerAiStep() {
		if (getSynchedData(SUCCESS_TIMER) > 0) {
			setSynchedData(SUCCESS_TIMER, getSynchedData(SUCCESS_TIMER) - 1);
		}
		else if (getSynchedData(SUCCESS_TIMER) == 0) {
			TMEParticlePacket packet = new TMEParticlePacket();

			for (int i = 0; i < 20; ++i) {
				packet.particle(ParticleBuilder.forRandomPosInEntity(ParticleTypes.HAPPY_VILLAGER, this)
						.velocity(rand().scaledGaussianValue(0.02d), rand().scaledGaussianValue(0.02d), rand().scaledGaussianValue(0.02d)));
			}

			packet.sendToAllPlayersTrackingEntity(this);
			level().playSound(null, getX(), getY(), getZ(), AoASounds.ENTITY_DRYAD_SPRITE_HAPPY.get(), SoundSource.NEUTRAL, 1, 1);

			if (level().getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT))
				ExperienceOrb.award((ServerLevel)level(), position(), random.nextInt(10, 20));

			getSynchedData(OWNER).ifPresent(ownerId -> {
				setHealth(0);

				Player player = level().getPlayerByUUID(ownerId);

				if (player != null) {
					setLastHurtByPlayer(player);

					DamageSource damageSource = damageSources().playerAttack(player);

					if (this.deathScore >= 0)
						player.awardKillScore(this, this.deathScore, damageSource);

					this.dead = true;

					getCombatTracker().recheckStatus();

					if (player.killedEntity((ServerLevel)level(), this)) {
						gameEvent(GameEvent.ENTITY_DIE);
						dropAllDeathLoot((ServerLevel)level(), damageSource);
						createWitherRose(player);
					}
				}
			});

			remove(RemovalReason.KILLED);
		}
	}

	public boolean isOwner(Entity entity) {
		return getSynchedData(OWNER).map(value -> value.equals(entity.getUUID())).orElse(true);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);

		getSynchedData(OWNER).ifPresent(uuid -> tag.putUUID("Owner", uuid));
		tag.putString("Variant", AoARegistries.DRYAD_SPRITE_VARIANTS.getKey(getSynchedData(VARIANT)).toString());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);

		if (compound.hasUUID("Owner"))
			setSynchedData(OWNER, Optional.of(compound.getUUID("Owner")));

		if (compound.contains("Variant", Tag.TAG_STRING)) {
			setSynchedData(VARIANT, DryadSpriteVariant.getOrDefault(ResourceLocation.tryParse(compound.getString("Variant"))));
		}
		else if (getVariant() == null) {
			setSynchedData(VARIANT, DryadSpriteVariant.WOOD.get());
		}
	}

	public static AoAEntityStats.AttributeBuilder entityStats(EntityType<DryadSpriteEntity> entityType) {
		return AoAEntityStats.AttributeBuilder.createMonster(entityType)
				.health(5)
				.moveSpeed(0.329)
				.followRange(16);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, state -> {
			if (getSynchedData(SUCCESS_TIMER) >= 0)
				return state.setAndContinue(AoAAnimations.SUCCEED);

			return state.setAndContinue(state.isMoving() ? DefaultAnimations.WALK : DefaultAnimations.IDLE);
		}));
	}
}
