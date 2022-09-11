/*
package net.tslat.aoa3.content.entity.tablet;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.content.item.tablet.TabletItem;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public abstract class SoulTabletEntity extends Entity {
	private static final DataParameter<Boolean> ACTIVE = EntityDataManager.<Boolean>defineId(SoulTabletEntity.class, DataSerializers.BOOLEAN);
	protected ServerPlayerEntity owner = null;
	private UUID ownerUUID = null;

	public SoulTabletEntity(EntityType<? extends SoulTabletEntity> entityType, World world) {
		super(entityType, world);

		this.blocksBuilding = true;
	}

	public SoulTabletEntity(EntityType<? extends SoulTabletEntity> entityType, World world, ServerPlayerEntity placer) {
		this(entityType, world);
		this.owner = placer;
		this.ownerUUID = owner != null ? owner.getUUID() : null;
	}

	@Override
	protected void defineSynchedData() {
		this.entityData.define(ACTIVE, true);
	}

	@Override
	public ActionResultType interact(PlayerEntity player, Hand hand) {
		return ownerUUID == null || player.getUUID().equals(ownerUUID) ? ActionResultType.SUCCESS : ActionResultType.FAIL;
	}

	@Override
	public ActionResultType interactAt(PlayerEntity player, Vector3d vec, Hand hand) {
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

			remove();

			return ActionResultType.SUCCESS;
		}

		return ActionResultType.PASS;
	}

	@Override
	protected void addAdditionalSaveData(CompoundNBT compound) {
		if (owner != null)
			compound.putString("OwnedBy", ownerUUID.toString());
	}

	@Override
	protected void readAdditionalSaveData(CompoundNBT compound) {
		if (compound.contains("OwnedBy")) {
			try {
				ownerUUID = UUID.fromString(compound.getString("OwnedBy"));
				owner = level instanceof ServerWorld ? (ServerPlayerEntity)level.getPlayerByUUID(ownerUUID) : null;
			}
			catch (IllegalArgumentException e) {
				Logging.logMessage(Level.WARN, "Unknown or malformed owner UUID for soul tablet entity: " + compound.getString("OwnerBy"));
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

					remove();

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
			remove();
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
	public void move(MoverType typeIn, Vector3d pos) {}

	protected abstract void doTickEffect();

	public abstract TabletItem getRelevantItem();

	@Override
	public IPacket<?> getAddEntityPacket() {
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

	protected <T extends Entity> List<T> getTargetsWithinRadius(Class<? extends T> targetClass, @Nullable Predicate<? super T> predicate) {
		return level.getEntitiesOfClass(targetClass, getBoundingBox().inflate(getRelevantItem().getEffectRadius()), predicate);
	}
}
*/
