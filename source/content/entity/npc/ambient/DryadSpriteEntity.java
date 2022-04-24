package net.tslat.aoa3.content.entity.npc.ambient;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoANpcs;
import net.tslat.aoa3.content.entity.base.AoAAmbientNPC;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.RandomUtil;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.manager.AnimationData;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

public class DryadSpriteEntity extends AoAAmbientNPC implements IAnimatable {
	private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(DryadSpriteEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Optional<UUID>> OWNER = SynchedEntityData.defineId(DryadSpriteEntity.class, EntityDataSerializers.OPTIONAL_UUID);
	private static final EntityDataAccessor<Integer> SUCCESS_TIMER = SynchedEntityData.defineId(DryadSpriteEntity.class, EntityDataSerializers.INT);

	public DryadSpriteEntity(EntityType<? extends DryadSpriteEntity> entityType, Level world) {
		super(entityType, world);
	}

	public DryadSpriteEntity(Player owner) {
		super(AoANpcs.DRYAD_SPRITE.get(), owner.level);

		entityData.set(OWNER, Optional.of(owner.getUUID()));
		entityData.set(VARIANT, RandomUtil.randomNumberUpTo(6));
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag nbt) {
		entityData.set(VARIANT, RandomUtil.randomNumberUpTo(6));

		return super.finalizeSpawn(world, difficulty, reason, spawnData, nbt);
	}

	@Nullable
	@Override
	protected String getInteractMessage(ItemStack heldItem) {
		if (heldItem.isEmpty())
			return getType().getDescriptionId() + ".interact.empty";

		if (!ItemUtil.isHoe(heldItem.getItem()))
			return getType().getDescriptionId() + ".interact.incorrect";

		return null;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (source != DamageSource.OUT_OF_WORLD && getEntityData().get(SUCCESS_TIMER) >= 0)
			return false;

		return super.hurt(source, amount);
	}

	@Override
	protected InteractionResult mobInteract(Player player, InteractionHand hand) {
		if (isAlive() && isOwner(player) && getEntityData().get(SUCCESS_TIMER) == -1) {
			ItemStack heldStack = player.getItemInHand(hand);
			if (!getEntityData().get(OWNER).isPresent())
				getEntityData().set(OWNER, Optional.of(player.getUUID()));

			if (ItemUtil.isHoe(heldStack.getItem())) {
				if (heldStack.getItem() == getVariant().getTool()) {
					if (!level.isClientSide()) {
						getEntityData().set(SUCCESS_TIMER, 44);
						player.killed((ServerLevel)level, this);
						navigation.stop();
						setNoAi(true);
						setDeltaMovement(0, 0, 0);
					}
				}
				else {
					discard();

					level.playSound(null, getX(), getY(), getZ(), AoASounds.ENTITY_DRYAD_SPRITE_UNHAPPY.get(), SoundSource.PLAYERS, 1, 1);

					for(int i = 0; i < 20; ++i) {
						this.level.addParticle(ParticleTypes.ANGRY_VILLAGER, this.getRandomX(1), this.getRandomY(), this.getRandomZ(1), RandomUtil.randomScaledGaussianValue(0.02d), RandomUtil.randomScaledGaussianValue(0.02d), RandomUtil.randomScaledGaussianValue(0.02d));
					}
				}

				return InteractionResult.SUCCESS;
			}
		}

		return super.mobInteract(player, hand);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();

		entityData.define(VARIANT, 0);
		entityData.define(OWNER, Optional.empty());
		entityData.define(SUCCESS_TIMER, -1);
	}

	public Variant getVariant() {
		return Variant.byNumber(entityData.get(VARIANT));
	}

	@Override
	public void checkDespawn() {
		super.checkDespawn();

		if (!isRemoved() && tickCount > 100 && getEntityData().get(SUCCESS_TIMER) == -1)
			discard();
	}

	@Override
	public void aiStep() {
		super.aiStep();

		int successTimer = getEntityData().get(SUCCESS_TIMER);

		if (successTimer == -1)
			return;

		if (successTimer > 0) {
			getEntityData().set(SUCCESS_TIMER, successTimer - 1);

			return;
		}

		for (int i = 0; i < 20; ++i) {
			this.level.addParticle(ParticleTypes.HAPPY_VILLAGER, this.getRandomX(1), this.getRandomY(), this.getRandomZ(1), RandomUtil.randomScaledGaussianValue(0.02d), RandomUtil.randomScaledGaussianValue(0.02d), RandomUtil.randomScaledGaussianValue(0.02d));
		}

		if (!level.isClientSide()) {
			level.playSound(null, getX(), getY(), getZ(), AoASounds.ENTITY_DRYAD_SPRITE_HAPPY.get(), SoundSource.PLAYERS, 1, 1);

			UUID ownerUuid = getEntityData().get(OWNER).orElse(null);

			if (ownerUuid != null) {
				Player player = level.getPlayerByUUID(ownerUuid);

				setHealth(0);

				if (player != null)
					dropAllDeathLoot(DamageSource.playerAttack(player));
			}

			remove(RemovalReason.KILLED);
		}
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<>(this, "movement", 0, event -> {
			if (getEntityData().get(SUCCESS_TIMER) >= 0)
				return PlayState.STOP;

			if (event.isMoving()) {
				event.getController().setAnimation(AoAAnimations.WALK);
			}
			else {
				event.getController().setAnimation(AoAAnimations.IDLE);
			}

			return PlayState.CONTINUE;
		}));

		data.addAnimationController(new AnimationController<>(this, "collection", 0, event -> {
			if (getEntityData().get(SUCCESS_TIMER) >= 0) {
				event.getController().setAnimation(AoAAnimations.SUCCEED);

				return PlayState.CONTINUE;
			}

			return PlayState.STOP;
		}));
	}

	public boolean isOwner(Entity entity) {
		return entityData.get(OWNER).map(value -> value.equals(entity.getUUID())).orElse(false);
	}

	public enum Variant {
		WOOD(0, () -> Items.WOODEN_HOE),
		STONE(1, () -> Items.STONE_HOE),
		IRON(2, () -> Items.IRON_HOE),
		GOLD(3, () -> Items.GOLDEN_HOE),
		DIAMOND(4, () -> Items.DIAMOND_HOE),
		NETHERITE(5, () -> Items.NETHERITE_HOE);

		private final int number;
		private final Supplier<Item> tool;

		Variant(int number, Supplier<Item> tool) {
			this.number = number;
			this.tool = tool;
		}

		public int getNumber() {
			return number;
		}

		public Item getTool() {
			return tool.get();
		}

		public static Variant byNumber(int number) {
			return switch (number) {
				case 1 -> STONE;
				case 2 -> IRON;
				case 3 -> GOLD;
				case 4 -> DIAMOND;
				case 5 -> NETHERITE;
				default -> WOOD;
			};
		}
	}
}
