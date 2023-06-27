package net.tslat.aoa3.content.entity.npc.ambient;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataSerializers;
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
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.ServerParticlePacket;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.common.registration.entity.AoANpcs;
import net.tslat.aoa3.content.entity.base.AoAAmbientNPC;
import net.tslat.aoa3.library.builder.ParticleBuilder;
import net.tslat.aoa3.library.object.EntityDataHolder;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.smartbrainlib.util.RandomUtil;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

public class DryadSpriteEntity extends AoAAmbientNPC {
	private static final EntityDataHolder<Integer> VARIANT = EntityDataHolder.register(DryadSpriteEntity.class, EntityDataSerializers.INT, 0, entity -> entity.variant, (entity, value) -> entity.variant = value);
	private static final EntityDataHolder<Optional<UUID>> OWNER = EntityDataHolder.register(DryadSpriteEntity.class, EntityDataSerializers.OPTIONAL_UUID, Optional.empty(), entity -> entity.owner, (entity, uuid) -> entity.owner = uuid);
	private static final EntityDataHolder<Integer> SUCCESS_TIMER = EntityDataHolder.register(DryadSpriteEntity.class, EntityDataSerializers.INT, -1, entity -> entity.successTimer, (entity, value) -> entity.successTimer = value);

	private int variant = 0;
	private Optional<UUID> owner = Optional.empty();
	private int successTimer = -1;

	public DryadSpriteEntity(EntityType<? extends DryadSpriteEntity> entityType, Level world) {
		super(entityType, world);
	}

	public DryadSpriteEntity(Player owner) {
		super(AoANpcs.DRYAD_SPRITE.get(), owner.level());

		OWNER.set(this, Optional.of(owner.getUUID()));
		VARIANT.set(this, rand().randomNumberUpTo(6));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();

		registerDataParams(VARIANT, OWNER, SUCCESS_TIMER);
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag nbt) {
		VARIANT.set(this, rand().randomNumberUpTo(6));

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
		if (!source.is(AoATags.DamageTypes.IS_TECHNICAL))
			return false;

		return super.hurt(source, amount);
	}

	@Override
	protected InteractionResult mobInteract(Player player, InteractionHand hand) {
		if (isAlive() && /*isOwner(player) && */SUCCESS_TIMER.is(this, -1)) {
			ItemStack heldStack = player.getItemInHand(hand);

			if (OWNER.get(this).isEmpty())
				OWNER.set(this, Optional.of(player.getUUID()));

			if (ItemUtil.isHoe(heldStack.getItem())) {
				if (heldStack.getItem() == getVariant().getTool()) {
					if (!level().isClientSide()) {
						SUCCESS_TIMER.set(this, 44);
						player.awardKillScore(this, 1, this.level().damageSources().playerAttack(player));
						navigation.stop();
						setDeltaMovement(0, 0, 0);
					}
				}
				else if (!level().isClientSide) {
					discard();

					level().playSound(null, getX(), getY(), getZ(), AoASounds.ENTITY_DRYAD_SPRITE_UNHAPPY.get(), SoundSource.PLAYERS, 1, 1);

					for(int i = 0; i < 20; ++i) {
						AoAPackets.messageAllPlayersTrackingEntity(new ServerParticlePacket(ParticleBuilder.forRandomPosInEntity(ParticleTypes.ANGRY_VILLAGER, this)
										.velocity(RandomUtil.randomScaledGaussianValue(0.02d), RandomUtil.randomScaledGaussianValue(0.02d), RandomUtil.randomScaledGaussianValue(0.02d))),
								this);
					}
				}

				return InteractionResult.SUCCESS;
			}
		}

		return super.mobInteract(player, hand);
	}

	public Variant getVariant() {
		return Variant.byNumber(VARIANT.get(this));
	}

	@Override
	public void checkDespawn() {
		super.checkDespawn();

		if (!isRemoved() && tickCount > 100 && SUCCESS_TIMER.is(this, -1))
			discard();
	}

	@Override
	protected void customServerAiStep() {
		if (successTimer > 0) {
			SUCCESS_TIMER.set(this, successTimer - 1);
		}
		else if (successTimer == 0) {
			ServerParticlePacket packet = new ServerParticlePacket();

			for (int i = 0; i < 20; ++i) {
				packet.particle(ParticleBuilder.forRandomPosInEntity(ParticleTypes.HAPPY_VILLAGER, this)
						.velocity(rand().randomScaledGaussianValue(0.02d), rand().randomScaledGaussianValue(0.02d), rand().randomScaledGaussianValue(0.02d)));
			}

			AoAPackets.messageAllPlayersTrackingEntity(packet, this);
			level().playSound(null, getX(), getY(), getZ(), AoASounds.ENTITY_DRYAD_SPRITE_HAPPY.get(), SoundSource.NEUTRAL, 1, 1);

			OWNER.get(this).ifPresent(ownerId -> {
				setHealth(0);

				Player player = level().getPlayerByUUID(ownerId);

				if (player != null) {
					setLastHurtByPlayer(player);
					dropAllDeathLoot(this.level().damageSources().playerAttack(player));
				}
			});

			remove(RemovalReason.KILLED);
		}
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, state -> {
			if (SUCCESS_TIMER.get(this) >= 0)
				return state.setAndContinue(AoAAnimations.SUCCEED);

			return state.setAndContinue(state.isMoving() ? DefaultAnimations.WALK : DefaultAnimations.IDLE);
		}));
	}

	public boolean isOwner(Entity entity) {
		return OWNER.get(this).map(value -> value.equals(entity.getUUID())).orElse(false);
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
