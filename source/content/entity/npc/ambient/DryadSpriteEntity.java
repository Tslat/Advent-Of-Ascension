package net.tslat.aoa3.content.entity.npc.ambient;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.ai.animation.Animatable;
import net.tslat.aoa3.content.entity.base.AoAAmbientNPC;
import net.tslat.aoa3.util.RandomUtil;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.manager.AnimationData;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

public class DryadSpriteEntity extends AoAAmbientNPC implements Animatable {
	private static final DataParameter<Integer> VARIANT = EntityDataManager.defineId(DryadSpriteEntity.class, DataSerializers.INT);
	private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.defineId(DryadSpriteEntity.class, DataSerializers.OPTIONAL_UUID);
	private static final DataParameter<Integer> SUCCESS_TIMER = EntityDataManager.defineId(DryadSpriteEntity.class, DataSerializers.INT);

	public DryadSpriteEntity(EntityType<? extends DryadSpriteEntity> entityType, World world) {
		super(entityType, world);
	}

	public DryadSpriteEntity(PlayerEntity owner) {
		super(AoAEntities.NPCs.DRYAD_SPRITE.get(), owner.level);

		entityData.set(OWNER, Optional.of(owner.getUUID()));
		entityData.set(VARIANT, RandomUtil.randomNumberUpTo(6));
	}

	@Nullable
	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT nbt) {
		entityData.set(VARIANT, RandomUtil.randomNumberUpTo(6));

		return super.finalizeSpawn(world, difficulty, reason, spawnData, nbt);
	}

	@Nullable
	@Override
	protected String getInteractMessage(ItemStack heldItem) {
		if (heldItem.isEmpty())
			return getType().getDescriptionId() + ".interact.empty";

		if (!heldItem.getToolTypes().contains(ToolType.HOE))
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
	protected ActionResultType mobInteract(PlayerEntity player, Hand hand) {
		if (isAlive() && isOwner(player) && getEntityData().get(SUCCESS_TIMER) == -1) {
			ItemStack heldStack = player.getItemInHand(hand);
			if (!getEntityData().get(OWNER).isPresent())
				getEntityData().set(OWNER, Optional.of(player.getUUID()));

			if (heldStack.getToolTypes().contains(ToolType.HOE)) {
				if (heldStack.getItem() == getVariant().getTool()) {
					if (!level.isClientSide()) {
						getEntityData().set(SUCCESS_TIMER, 44);
						player.killed((ServerWorld)level, this);
						navigation.stop();
						setNoAi(true);
						setDeltaMovement(0, 0, 0);
					}
				}
				else {
					remove();

					level.playSound(null, getX(), getY(), getZ(), AoASounds.ENTITY_DRYAD_SPRITE_UNHAPPY.get(), SoundCategory.PLAYERS, 1, 1);

					for(int i = 0; i < 20; ++i) {
						this.level.addParticle(ParticleTypes.ANGRY_VILLAGER, this.getRandomX(1), this.getRandomY(), this.getRandomZ(1), RandomUtil.randomScaledGaussianValue(0.02d), RandomUtil.randomScaledGaussianValue(0.02d), RandomUtil.randomScaledGaussianValue(0.02d));
					}
				}

				return ActionResultType.SUCCESS;
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

		if (!removed && tickCount > 100 && getEntityData().get(SUCCESS_TIMER) == -1)
			remove();
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
			level.playSound(null, getX(), getY(), getZ(), AoASounds.ENTITY_DRYAD_SPRITE_HAPPY.get(), SoundCategory.PLAYERS, 1, 1);

			UUID ownerUuid = getEntityData().get(OWNER).orElse(null);

			if (ownerUuid != null) {
				PlayerEntity player = level.getPlayerByUUID(ownerUuid);

				setHealth(0);

				if (player != null)
					dropAllDeathLoot(DamageSource.playerAttack(player));
			}

			remove(false);
		}
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<DryadSpriteEntity>(this, "movement", 0, event -> {
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

		data.addAnimationController(new AnimationController<DryadSpriteEntity>(this, "collection", 0, event -> {
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
			switch (number) {
				case 1:
					return STONE;
				case 2:
					return IRON;
				case 3:
					return GOLD;
				case 4:
					return DIAMOND;
				case 5:
					return NETHERITE;
				case 0:
				default:
					return WOOD;
			}
		}
	}
}
