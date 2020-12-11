package net.tslat.aoa3.entity.misc;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.LootUtil;

import java.util.UUID;

public class LottoTotemEntity extends Entity {
	private UUID winnerUUID = null;
	private UUID ownerUUID = null;

	public LottoTotemEntity(World world, BlockPos pos, UUID winnerUUID, UUID ownerUUID) {
		this(AoAEntities.Misc.LOTTO_TOTEM.get(), world);

		this.winnerUUID = winnerUUID;
		this.ownerUUID = ownerUUID;
		VoxelShape floorShape = world.getBlockState(pos).getShape(world, pos);

		setPosition(pos.getX() + 0.5d, pos.getY() + floorShape.getEnd(Direction.Axis.Y), pos.getZ() + 0.5d);
	}

	public LottoTotemEntity(EntityType<? extends Entity> entityType, World world) {
		super(entityType, world);

		preventEntitySpawning = true;
	}

	@Override
	public boolean processInitialInteract(PlayerEntity player, Hand hand) {
		return ticksExisted >= 12000 || (!world.isRemote && (ownerUUID == null || player.getUniqueID().equals(ownerUUID)));
	}

	@Override
	public ActionResultType applyPlayerInteraction(PlayerEntity player, Vec3d vec, Hand hand) {
		if (isAlive() && (ownerUUID == null || player.getUniqueID().equals(ownerUUID))) {
			if (player instanceof ServerPlayerEntity) {
				if (winnerUUID != null && winnerUUID.equals(getUniqueID())) {

					for (ItemStack stack : LootUtil.generateLoot((ServerWorld)world,  new ResourceLocation(AdventOfAscension.MOD_ID, "misc/lotto_totem"), LootUtil.getGiftContext((ServerWorld)world, getPosition(), 5, player))) {
						ItemEntity drop = entityDropItem(stack, 0);

						if (drop != null)
							drop.setOwnerId(player.getUniqueID());

						AdvancementUtil.completeAdvancement((ServerPlayerEntity)player, new ResourceLocation(AdventOfAscension.MOD_ID, "overworld/winner_winner"), "lotto_win");
					}

					ItemEntity drop = entityDropItem(new ItemStack(AoABlocks.LOTTO_BANNER.get()), 0);

					if (drop != null)
						drop.setOwnerId(player.getUniqueID());

					world.playSound(null, getPosition(), AoASounds.LOTTO_WIN.get(), SoundCategory.PLAYERS, 1.0f, 1.0f);
				}

				for (LottoTotemEntity totem : world.getEntitiesWithinAABB(LottoTotemEntity.class, new AxisAlignedBB(getPosition()).grow(2d))) {
					totem.remove();
				}
			}

			return ActionResultType.SUCCESS;
		}

		return ActionResultType.PASS;
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	public void onRemovedFromWorld() {
		super.onRemovedFromWorld();

		if (world.isRemote && !isAlive()) {
			for (int i = 0; i < 3; i++) {
				world.addParticle(ParticleTypes.LARGE_SMOKE, getPosX(), getPosY() + 0.3d, getPosZ(), 0, 0.1, 0);
			}
		}
	}

	@Override
	protected void registerData() {}

	@Override
	protected void writeAdditional(CompoundNBT compound) {
		if (compound.hasUniqueId("WinningUUID"))
			winnerUUID = compound.getUniqueId("WinningUUID");

		if (compound.hasUniqueId("OwnerUUID"))
			ownerUUID = compound.getUniqueId("OwnerUUID");
	}

	@Override
	protected void readAdditional(CompoundNBT compound) {
		if (winnerUUID != null)
			compound.putUniqueId("WinningUUID", winnerUUID);

		if (ownerUUID != null)
			compound.putUniqueId("OwnerUUID", ownerUUID);
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
	public void tick() {
		baseTick();
	}

	@Override
	public void baseTick() {
		this.world.getProfiler().startSection("entityBaseTick");

		if (getPosY() < -64.0D)
			outOfWorld();

		this.world.getProfiler().endSection();
	}
}
