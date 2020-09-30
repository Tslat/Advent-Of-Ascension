package net.tslat.aoa3.entity.misc;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.utils.LootUtil;
import net.tslat.aoa3.utils.ModUtil;

import java.util.UUID;

public class EntityLottoTotem extends Entity {
	public static final float entityWidth = 0.75f;

	private UUID winnerUUID = null;
	private UUID ownerUUID = null;

	public EntityLottoTotem(World world, BlockPos pos, UUID winnerUUID, UUID ownerUUID) {
		this(world);

		this.winnerUUID = winnerUUID;
		this.ownerUUID = ownerUUID;
		AxisAlignedBB floorBoundingBox = world.getBlockState(pos).getActualState(world, pos).getCollisionBoundingBox(world, pos);

		setPosition(pos.getX() + 0.5d, pos.getY() + (floorBoundingBox == null ? 0 : floorBoundingBox.maxY), pos.getZ() + 0.5d);
	}

	public EntityLottoTotem(World world) {
		super(world);

		this.preventEntitySpawning = true;

		setSize(entityWidth, 0.95f);
	}

	@Override
	public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
		return ticksExisted >= 12000 || (!world.isRemote && (ownerUUID == null || player.getUniqueID().equals(ownerUUID)));
	}

	@Override
	public EnumActionResult applyPlayerInteraction(EntityPlayer player, Vec3d vec, EnumHand hand) {
		if (!isDead && (ownerUUID == null || player.getUniqueID().equals(ownerUUID))) {
			if (!world.isRemote) {
				if (winnerUUID != null && winnerUUID.equals(getUniqueID())) {
					for (ItemStack stack : LootUtil.generateLootWithCustomLuck(LootSystemRegister.lottoTotem, (WorldServer)world, 5)) {
						EntityItem drop = entityDropItem(stack, 0);

						if (drop != null)
							drop.setOwner(player.getName());

						ModUtil.completeAdvancement((EntityPlayerMP)player, "overworld/winner_winner", "lotto_win");
					}

					EntityItem drop = entityDropItem(new ItemStack(BlockRegister.LOTTO_BANNER), 0);

					if (drop != null)
						drop.setOwner(player.getName());

					world.playSound(null, getPosition(), SoundsRegister.LOTTO_WIN, SoundCategory.PLAYERS, 1.0f, 1.0f);
				}

				for (EntityLottoTotem totem : world.getEntitiesWithinAABB(EntityLottoTotem.class, new AxisAlignedBB(getPosition()).grow(2d))) {
					totem.setDead();
				}
			}

			return EnumActionResult.SUCCESS;
		}

		return EnumActionResult.PASS;
	}

	@Override
	public void onRemovedFromWorld() {
		super.onRemovedFromWorld();

		if (world.isRemote && isDead) {
			for (int i = 0; i < 3; i++) {
				world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, posX, posY + 0.3d, posZ, 0, 0.1, 0);
			}
		}
	}

	@Override
	protected void entityInit() {}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		if (winnerUUID != null)
			compound.setUniqueId("WinningUUID", winnerUUID);

		if (ownerUUID != null)
			compound.setUniqueId("OwnerUUID", ownerUUID);
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		if (compound.hasUniqueId("WinningUUID"))
			winnerUUID = compound.getUniqueId("WinningUUID");

		if (compound.hasUniqueId("OwnerUUID"))
			ownerUUID = compound.getUniqueId("OwnerUUID");
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

	@Override
	public void onUpdate() {}
}
