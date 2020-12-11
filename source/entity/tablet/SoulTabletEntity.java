package net.tslat.aoa3.entity.tablet;

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
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.item.armour.AdventArmour;
import net.tslat.aoa3.item.tablet.TabletItem;
import net.tslat.aoa3.util.constant.Resources;
import net.tslat.aoa3.util.player.PlayerUtil;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public abstract class SoulTabletEntity extends Entity {
	private static final DataParameter<Boolean> ACTIVE = EntityDataManager.<Boolean>createKey(SoulTabletEntity.class, DataSerializers.BOOLEAN);
	protected ServerPlayerEntity owner = null;
	private UUID ownerUUID = null;

	public SoulTabletEntity(EntityType<? extends SoulTabletEntity> entityType, World world) {
		super(entityType, world);

		this.preventEntitySpawning = true;
	}

	public SoulTabletEntity(EntityType<? extends SoulTabletEntity> entityType, World world, ServerPlayerEntity placer) {
		this(entityType, world);
		this.owner = placer;
		this.ownerUUID = owner != null ? owner.getUniqueID() : null;
	}

	@Override
	protected void registerData() {
		this.dataManager.register(ACTIVE, true);
	}

	@Override
	public boolean processInitialInteract(PlayerEntity player, Hand hand) {
		return ownerUUID == null || player.getUniqueID().equals(ownerUUID);
	}

	@Override
	public ActionResultType applyPlayerInteraction(PlayerEntity player, Vec3d vec, Hand hand) {
		if (isAlive() && (ownerUUID == null || player.getUniqueID().equals(ownerUUID))) {
			if (!world.isRemote && !player.isCreative()) {
				ItemStack stack = new ItemStack(getRelevantItem());

				if (player.getHeldItem(hand).isEmpty()) {
					player.setHeldItem(hand, new ItemStack(getRelevantItem()));
				}
				else if (!player.addItemStackToInventory(stack)) {
					entityDropItem(stack, 0f);
				}
			}

			remove();

			return ActionResultType.SUCCESS;
		}

		return ActionResultType.PASS;
	}

	@Override
	protected void writeAdditional(CompoundNBT compound) {
		if (owner != null)
			compound.putString("OwnedBy", ownerUUID.toString());
	}

	@Override
	protected void readAdditional(CompoundNBT compound) {
		if (compound.contains("OwnedBy")) {
			try {
				ownerUUID = UUID.fromString(compound.getString("OwnedBy"));
				owner = world instanceof ServerWorld ? (ServerPlayerEntity)world.getPlayerByUuid(ownerUUID) : null;
			}
			catch (IllegalArgumentException e) {
				Logging.logMessage(Level.WARN, "Unknown or malformed owner UUID for soul tablet entity: " + compound.getString("OwnerBy"));
			}
		}
	}

	@Override
	public void tick() {
		if (!world.isRemote) {
			if (isAlive() && ticksExisted % 5 == 0) {
				if (world.isAirBlock(getPosition().down())) {
					ItemEntity itemDrop = entityDropItem(new ItemStack(getRelevantItem()), 0f);

					if (owner != null && itemDrop != null)
						itemDrop.setOwnerId(ownerUUID);

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
		else if (world.isAirBlock(getPosition().down())) {
			remove();
		}
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	public boolean isPushedByWater() {
		return false;
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		return source != DamageSource.OUT_OF_WORLD;
	}

	@Override
	public void move(MoverType typeIn, Vec3d pos) {}

	protected abstract void doTickEffect();

	public abstract TabletItem getRelevantItem();

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	private boolean testSoulSupply() {
		if (owner == null)
			return false;

		return PlayerUtil.consumeResource(owner, Resources.SOUL, getRelevantItem().getSoulDrain() * 5 * (PlayerUtil.isWearingFullSet(owner, AdventArmour.Type.ANIMA) ? 0.5f : 1f), false);
	}

	public boolean isActive() {
		return dataManager.get(ACTIVE);
	}

	private void deactivate() {
		dataManager.set(ACTIVE, false);
	}

	protected <T extends Entity> List<T> getTargetsWithinRadius(Class<? extends T> targetClass, @Nullable Predicate<? super T> predicate) {
		return world.getEntitiesWithinAABB(targetClass, getBoundingBox().grow(getRelevantItem().getEffectRadius()), predicate);
	}
}
