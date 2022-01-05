package net.tslat.aoa3.object.entity.misc;

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
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
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

		setPos(pos.getX() + 0.5d, pos.getY() + floorShape.max(Direction.Axis.Y), pos.getZ() + 0.5d);
	}

	public LottoTotemEntity(EntityType<? extends Entity> entityType, World world) {
		super(entityType, world);

		blocksBuilding = true;
	}

	@Override
	public ActionResultType interact(PlayerEntity player, Hand hand) {
		return tickCount >= 12000 || (!level.isClientSide && (ownerUUID == null || player.getUUID().equals(ownerUUID))) ? ActionResultType.SUCCESS : ActionResultType.FAIL;
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	public ActionResultType interactAt(PlayerEntity player, Vector3d vec, Hand hand) {
		if (isAlive() && (ownerUUID == null || player.getUUID().equals(ownerUUID))) {
			if (player instanceof ServerPlayerEntity) {
				if (winnerUUID != null && winnerUUID.equals(getUUID())) {

					for (ItemStack stack : LootUtil.generateLoot((ServerWorld)level,  new ResourceLocation(AdventOfAscension.MOD_ID, "misc/lotto_totem"), LootUtil.getGiftContext((ServerWorld)level, position(), 5, player))) {
						ItemEntity drop = spawnAtLocation(stack, 0);

						if (drop != null)
							drop.setOwner(player.getUUID());

						AdvancementUtil.completeAdvancement((ServerPlayerEntity)player, new ResourceLocation(AdventOfAscension.MOD_ID, "overworld/winner_winner"), "lotto_win");
					}

					ItemEntity drop = spawnAtLocation(new ItemStack(AoABlocks.LOTTO_BANNER.get()), 0);

					if (drop != null)
						drop.setOwner(player.getUUID());

					level.playSound(null, blockPosition(), AoASounds.LOTTO_WIN.get(), SoundCategory.PLAYERS, 1.0f, 1.0f);
				}

				for (LottoTotemEntity totem : level.getEntitiesOfClass(LottoTotemEntity.class, new AxisAlignedBB(blockPosition()).inflate(2d))) {
					totem.remove();
				}
			}

			return ActionResultType.SUCCESS;
		}

		return ActionResultType.PASS;
	}

	@Override
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	public void onRemovedFromWorld() {
		super.onRemovedFromWorld();

		if (level.isClientSide && !isAlive()) {
			for (int i = 0; i < 3; i++) {
				level.addParticle(ParticleTypes.LARGE_SMOKE, getX(), getY() + 0.3d, getZ(), 0, 0.1, 0);
			}
		}
	}

	@Override
	protected void defineSynchedData() {}

	@Override
	protected void addAdditionalSaveData(CompoundNBT compound) {
		if (compound.hasUUID("WinningUUID"))
			winnerUUID = compound.getUUID("WinningUUID");

		if (compound.hasUUID("OwnerUUID"))
			ownerUUID = compound.getUUID("OwnerUUID");
	}

	@Override
	protected void readAdditionalSaveData(CompoundNBT compound) {
		if (winnerUUID != null)
			compound.putUUID("WinningUUID", winnerUUID);

		if (ownerUUID != null)
			compound.putUUID("OwnerUUID", ownerUUID);
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
	public void tick() {
		baseTick();
	}

	@Override
	public void baseTick() {
		this.level.getProfiler().push("entityBaseTick");

		if (getY() < -64.0D)
			outOfWorld();

		this.level.getProfiler().pop();
	}
}
