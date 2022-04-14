package net.tslat.aoa3.content.entity.npc.ambient;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
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
import net.tslat.aoa3.content.entity.ai.animation.AnimatableWithStates;
import net.tslat.aoa3.content.entity.base.AoAAmbientNPC;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.RandomUtil;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.manager.AnimationData;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

public class DryadSpriteEntity extends AoAAmbientNPC implements AnimatableWithStates {
	private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(DryadSpriteEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Optional<UUID>> OWNER = SynchedEntityData.defineId(DryadSpriteEntity.class, EntityDataSerializers.OPTIONAL_UUID);

	public boolean successful = false;

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
	protected InteractionResult mobInteract(Player player, InteractionHand hand) {
		if (isOwner(player)) {
			ItemStack heldStack = player.getItemInHand(hand);

			if (ItemUtil.isHoe(heldStack.getItem())) {
				if (heldStack.getItem() == getVariant().getTool()) {
					successful = true;

					if (!level.isClientSide())
						DamageUtil.dealMeleeDamage(player, this, getHealth(), true);
				}
				else {
					deathTime = -1;

					discard();
				}
			}
		}

		return super.mobInteract(player, hand);
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (source == DamageSource.OUT_OF_WORLD) {
			discard();

			return true;
		}
		else if (successful && !level.isClientSide()) {
			if (source.getEntity() instanceof ServerPlayer) {
				((ServerPlayer)source.getEntity()).awardStat(Stats.DAMAGE_DEALT_ABSORBED, (int)getHealth());
				source.getEntity().killed((ServerLevel)level, this);
			}

			this.getCombatTracker().recordDamage(source, 0, amount);
			setHealth(0);
			dropAllDeathLoot(source);
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

		if (!isRemoved() && tickCount > 100 && !successful)
			discard();
	}

	@Override
	public void remove(RemovalReason pReason) {
		super.remove(pReason);

		if (deathTime == -1) {
			level.playSound(null, getX(), getY(), getZ(), AoASounds.ENTITY_DRYAD_SPRITE_UNHAPPY.get(), SoundSource.PLAYERS, 1, 1);

			for(int i = 0; i < 20; ++i) {
				this.level.addParticle(ParticleTypes.ANGRY_VILLAGER, this.getRandomX(1), this.getRandomY(), this.getRandomZ(1), RandomUtil.randomScaledGaussianValue(0.02d), RandomUtil.randomScaledGaussianValue(0.02d), RandomUtil.randomScaledGaussianValue(0.02d));
			}
		}
	}

	@Override
	protected void tickDeath() {
		++this.deathTime;

		if (this.deathTime == 44) {
			this.remove(RemovalReason.KILLED);

			ParticleOptions particle = successful ? ParticleTypes.HAPPY_VILLAGER : ParticleTypes.POOF;
			level.playSound(null, getX(), getY(), getZ(), AoASounds.ENTITY_DRYAD_SPRITE_HAPPY.get(), SoundSource.PLAYERS, 1, 1);

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
