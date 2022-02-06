package net.tslat.aoa3.object.entity.npc.ambient;

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
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.object.entity.ai.animation.Animatable;
import net.tslat.aoa3.object.entity.base.AoAAmbientNPC;
import net.tslat.aoa3.util.DamageUtil;
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

	public boolean successful = false;

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
	protected ActionResultType mobInteract(PlayerEntity player, Hand hand) {
		if (isOwner(player)) {
			ItemStack heldStack = player.getItemInHand(hand);

			if (heldStack.getToolTypes().contains(ToolType.HOE)) {
				if (heldStack.getItem() == getVariant().getTool()) {
					successful = true;

					if (!level.isClientSide())
						DamageUtil.dealMeleeDamage(player, this, getHealth(), true);
				}
				else {
					deathTime = -1;

					remove();
				}
			}
		}

		return super.mobInteract(player, hand);
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (super.hurt(source, amount)) {
			if (isOwner(source.getEntity())) {
				if (!successful) {
					remove();
				}
				else {
					hurtMarked = false;
					setDeltaMovement(Vector3d.ZERO);
					hurtTime = 0;
				}
			}

			return true;
		}

		return false;
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();

		entityData.define(VARIANT, 0);
		entityData.define(OWNER, Optional.empty());
	}

	public Variant getVariant() {
		return Variant.byNumber(entityData.get(VARIANT));
	}

	@Override
	public void checkDespawn() {
		super.checkDespawn();

		if (!removed && tickCount > 200 && !successful)
			remove();
	}

	@Override
	public void remove(boolean keepData) {
		super.remove(keepData);

		if (deathTime == -1) {
			level.playSound(null, getX(), getY(), getZ(), AoASounds.ENTITY_DRYAD_SPRITE_UNHAPPY.get(), SoundCategory.PLAYERS, 1, 1);

			for(int i = 0; i < 20; ++i) {
				this.level.addParticle(ParticleTypes.ANGRY_VILLAGER, this.getRandomX(1), this.getRandomY(), this.getRandomZ(1), RandomUtil.randomScaledGaussianValue(0.02d), RandomUtil.randomScaledGaussianValue(0.02d), RandomUtil.randomScaledGaussianValue(0.02d));
			}
		}
	}

	@Override
	protected void tickDeath() {
		++this.deathTime;

		if (this.deathTime == 44) {
			this.remove(false);

			IParticleData particle = successful ? ParticleTypes.HAPPY_VILLAGER : ParticleTypes.POOF;
			level.playSound(null, getX(), getY(), getZ(), AoASounds.ENTITY_DRYAD_SPRITE_HAPPY.get(), SoundCategory.PLAYERS, 1, 1);

			for(int i = 0; i < 20; ++i) {
				this.level.addParticle(particle, this.getRandomX(1), this.getRandomY(), this.getRandomZ(1), RandomUtil.randomScaledGaussianValue(0.02d), RandomUtil.randomScaledGaussianValue(0.02d), RandomUtil.randomScaledGaussianValue(0.02d));
			}
		}
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<DryadSpriteEntity>(this, "movement", 0, event -> {
			if (successful)
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
			if (successful) {
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
