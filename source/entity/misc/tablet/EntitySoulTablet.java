package net.tslat.aoa3.entity.misc.tablet;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.item.tablet.TabletItem;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.player.PlayerUtil;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public abstract class EntitySoulTablet extends Entity {
	private static final DataParameter<Boolean> ACTIVE = EntityDataManager.<Boolean>createKey(EntitySoulTablet.class, DataSerializers.BOOLEAN);
	protected EntityPlayer owner = null;
	private UUID ownerUUID = null;

	public EntitySoulTablet(World world) {
		super(world);
		setSize(0.6f, 0.09375f);

		this.preventEntitySpawning = true;
	}

	public EntitySoulTablet(World world, EntityPlayer placer) {
		this(world);
		this.owner = placer;
		this.ownerUUID = owner != null ? owner.getUniqueID() : null;
	}

	@Override
	protected void entityInit() {
		this.dataManager.register(ACTIVE, true);
	}

	@Override
	public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
		return ownerUUID == null || player.getUniqueID().equals(ownerUUID);
	}

	@Override
	public EnumActionResult applyPlayerInteraction(EntityPlayer player, Vec3d vec, EnumHand hand) {
		if (!isDead && (ownerUUID == null || player.getUniqueID().equals(ownerUUID))) {
			if (!world.isRemote && !player.isCreative()) {
				ItemStack stack = new ItemStack(getRelevantItem());

				if (player.getHeldItem(hand).isEmpty()) {
					player.setHeldItem(hand, new ItemStack(getRelevantItem()));
				}
				else if (!player.addItemStackToInventory(stack)) {
					entityDropItem(stack, 0f);
				}
			}

			setDead();

			return EnumActionResult.SUCCESS;
		}

		return EnumActionResult.PASS;
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		if (owner != null)
			compound.setString("OwnedBy", ownerUUID.toString());
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		if (compound.hasKey("OwnedBy")) {
			try {
				ownerUUID = UUID.fromString(compound.getString("OwnedBy"));
				owner = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUUID(ownerUUID);
			}
			catch (IllegalArgumentException e) {
				AdventOfAscension.logMessage(Level.WARN, "Unknown or malformed owner UUID for soul tablet entity: " + compound.getString("OwnerBy"));
			}
		}
	}

	@Override
	public void onUpdate() {
		if (!world.isRemote) {
			if (!isDead && ticksExisted % 5 == 0) {
				if (world.isAirBlock(getPosition().down())) {
					EntityItem itemDrop = entityDropItem(new ItemStack(getRelevantItem()), 0f);

					if (owner != null && itemDrop != null)
						itemDrop.setOwner(owner.getName());

					setDead();

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
			setDead();
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
	public boolean isEntityInvulnerable(DamageSource source) {
		return source != DamageSource.OUT_OF_WORLD;
	}

	@Override
	public void move(MoverType type, double x, double y, double z) {}

	protected abstract void doTickEffect();

	public abstract TabletItem getRelevantItem();

	private boolean testSoulSupply() {
		if (owner == null)
			return false;

		return PlayerUtil.consumeResource(owner, Enums.Resources.SOUL, getRelevantItem().getSoulDrain() * 5 * (PlayerUtil.isWearingFullSet(owner, Enums.ArmourSets.ANIMA) ? 0.5f : 1f), false);
	}

	public boolean isActive() {
		return dataManager.get(ACTIVE);
	}

	private void deactivate() {
		dataManager.set(ACTIVE, false);
	}

	protected <T extends Entity> List<T> getTargetsWithinRadius(Class<? extends T> targetClass, @Nullable Predicate<? super T> predicate) {
		return world.getEntitiesWithinAABB(targetClass, getEntityBoundingBox().grow(getRelevantItem().getEffectRadius()), predicate);
	}
}
