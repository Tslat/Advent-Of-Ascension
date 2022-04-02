package net.tslat.aoa3.content.entity.tablet;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.content.item.tablet.TabletItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public abstract class SoulTabletEntity extends Entity {
	private static final EntityDataAccessor<Boolean> ACTIVE = SynchedEntityData.<Boolean>defineId(SoulTabletEntity.class, EntityDataSerializers.BOOLEAN);
	protected ServerPlayer owner = null;
	private UUID ownerUUID = null;

	public SoulTabletEntity(EntityType<? extends SoulTabletEntity> entityType, Level world) {
		super(entityType, world);

		this.blocksBuilding = true;
	}

	public SoulTabletEntity(EntityType<? extends SoulTabletEntity> entityType, Level world, ServerPlayer placer) {
		this(entityType, world);
		this.owner = placer;
		this.ownerUUID = owner != null ? owner.getUUID() : null;
	}

	@Override
	protected void defineSynchedData() {
		this.entityData.define(ACTIVE, true);
	}

	@Override
	public InteractionResult interact(Player player, InteractionHand hand) {
		return ownerUUID == null || player.getUUID().equals(ownerUUID) ? InteractionResult.SUCCESS : InteractionResult.FAIL;
	}

	@Override
	public InteractionResult interactAt(Player player, Vec3 vec, InteractionHand hand) {
		if (isAlive() && (ownerUUID == null || player.getUUID().equals(ownerUUID))) {
			if (!level.isClientSide && !player.isCreative()) {
				ItemStack stack = new ItemStack(getRelevantItem());

				if (player.getItemInHand(hand).isEmpty()) {
					player.setItemInHand(hand, new ItemStack(getRelevantItem()));
				}
				else if (!player.addItem(stack)) {
					spawnAtLocation(stack, 0f);
				}
			}

			discard();

			return InteractionResult.SUCCESS;
		}

		return InteractionResult.PASS;
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag compound) {
		if (owner != null)
			compound.putString("OwnedBy", ownerUUID.toString());
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag compound) {
		if (compound.contains("OwnedBy")) {
			try {
				ownerUUID = UUID.fromString(compound.getString("OwnedBy"));
				owner = level instanceof ServerLevel ? (ServerPlayer)level.getPlayerByUUID(ownerUUID) : null;
			}
			catch (IllegalArgumentException e) {
				Logging.logMessage(org.apache.logging.log4j.Level.WARN, "Unknown or malformed owner UUID for soul tablet entity: " + compound.getString("OwnerBy"));
			}
		}
	}

	@Override
	public void tick() {
		if (!level.isClientSide) {
			if (isAlive() && tickCount % 5 == 0) {
				if (level.isEmptyBlock(blockPosition().below())) {
					ItemEntity itemDrop = spawnAtLocation(new ItemStack(getRelevantItem()), 0f);

					if (owner != null && itemDrop != null)
						itemDrop.setOwner(ownerUUID);

					discard();

					return;
				}

				if (isActive()) {
					if (testSoulSupply()) {
						doTickEffect();
					}
					else {
						deactivate();
					}
				}
			}
		}
		else if (level.isEmptyBlock(blockPosition().below())) {
			discard();
		}
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	public boolean isPushedByFluid() {
		return false;
	}

	@Override
	public boolean isPickable() {
		return true;
	}

	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		return source != DamageSource.OUT_OF_WORLD;
	}

	@Override
	public void move(MoverType typeIn, Vec3 pos) {}

	protected abstract void doTickEffect();

	public abstract TabletItem getRelevantItem();

	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	private boolean testSoulSupply() {
		if (owner == null)
			return false;
		return true; // TODO
		//return PlayerUtil.consumeResource(owner, AoAResource.SOUL, getRelevantItem().getSoulDrain() * 5 * (PlayerUtil.isWearingFullSet(owner, AdventArmour.Type.ANIMA) ? 0.5f : 1f), false);
	}

	public boolean isActive() {
		return entityData.get(ACTIVE);
	}

	private void deactivate() {
		entityData.set(ACTIVE, false);
	}

	protected <T extends Entity> List<T> getTargetsWithinRadius(Class<T> targetClass, @Nullable Predicate<? super T> predicate) {
		return level.getEntitiesOfClass(targetClass, getBoundingBox().inflate(getRelevantItem().getEffectRadius()), predicate);
	}
}
