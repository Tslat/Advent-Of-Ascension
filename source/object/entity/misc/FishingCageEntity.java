package net.tslat.aoa3.object.entity.misc;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.network.NetworkHooks;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoATools;
import net.tslat.aoa3.event.AoAPlayerEvents;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.RandomUtil;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class FishingCageEntity extends Entity {
	private static final DataParameter<ItemStack> CAUGHT_STACK_1 = EntityDataManager.defineId(FishingCageEntity.class, DataSerializers.ITEM_STACK);
	private static final DataParameter<ItemStack> CAUGHT_STACK_2 = EntityDataManager.defineId(FishingCageEntity.class, DataSerializers.ITEM_STACK);
	private static final DataParameter<ItemStack> CAUGHT_STACK_3 = EntityDataManager.defineId(FishingCageEntity.class, DataSerializers.ITEM_STACK);

	private UUID ownerUUID = null;
	private int damage;
	private List<ItemStack> loot = null;

	public FishingCageEntity(World world, PlayerEntity player, ItemStack stack) {
		this(AoAEntities.Misc.FISHING_CAGE.get(), world);

		setPos(player.getX(), player.getY() + player.getEyeHeight(), player.getZ());
		setRot(player.yRot, player.xRot);

		setDeltaMovement(EntityUtil.getDirectionForFacing(player).multiply(0.75f, 0.75f, 0.75f));

		this.ownerUUID = player.getUUID();
		this.damage = stack.getDamageValue();
	}

	public FishingCageEntity(EntityType<? extends Entity> entityType, World world) {
		super(entityType, world);

		blocksBuilding = true;
	}

	@Override
	public ActionResultType interact(PlayerEntity player, Hand hand) {
		if (!level.isClientSide() && ownerUUID == null || player.getUUID().equals(ownerUUID)) {
			ItemStack fishingCage = new ItemStack(AoATools.FISHING_CAGE.get());
			int damage = this.damage + 1;

			if (damage < fishingCage.getMaxDamage()) {
				fishingCage.setDamageValue(damage);

				if (!player.isCreative())
					ItemUtil.givePlayerItemOrDrop(player, fishingCage);
			}

			List<ItemStack> loot = getLoot();

			if (!loot.isEmpty()) {
				AoAPlayerEvents.handleCustomInteraction((ServerPlayerEntity)player, "fishing_cage_harvest", loot);

				for (ItemStack drop : loot) {
					if (drop.getItem().is(ItemTags.FISHES))
						player.awardStat(Stats.FISH_CAUGHT, 1);

					ItemUtil.givePlayerItemOrDrop(player, drop);
				}
			}

			player.awardStat(Stats.ITEM_USED.get(AoATools.FISHING_CAGE.get()));
			remove();

			return ActionResultType.SUCCESS;
		}

		return level.isClientSide() ? ActionResultType.SUCCESS : ActionResultType.FAIL;
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected void defineSynchedData() {
		getEntityData().define(CAUGHT_STACK_1, ItemStack.EMPTY);
		getEntityData().define(CAUGHT_STACK_2, ItemStack.EMPTY);
		getEntityData().define(CAUGHT_STACK_3, ItemStack.EMPTY);
	}

	@Override
	public void onSyncedDataUpdated(DataParameter<?> key) {
		if (level.isClientSide()) {
			ItemStack stack = ItemStack.EMPTY;

			if (key == CAUGHT_STACK_1) {
				stack = getEntityData().get(CAUGHT_STACK_1);
			}
			else if (key == CAUGHT_STACK_2) {
				stack = getEntityData().get(CAUGHT_STACK_2);
			}
			else if (key == CAUGHT_STACK_3) {
				stack = getEntityData().get(CAUGHT_STACK_3);
			}

			if (stack != ItemStack.EMPTY) {
				if (loot == null)
					loot = new ArrayList<ItemStack>(3);

				loot.add(stack);
			}
		}
	}

	@Override
	protected void addAdditionalSaveData(CompoundNBT compound) {
		if (compound.hasUUID("OwnerUUID"))
			ownerUUID = compound.getUUID("OwnerUUID");

		if (!loot.isEmpty()) {
			ListNBT lootList = new ListNBT();

			for (ItemStack stack : loot) {
				lootList.add(stack.save(new CompoundNBT()));
			}

			compound.put("loot", lootList);
		}
	}

	@Override
	protected void readAdditionalSaveData(CompoundNBT compound) {
		if (ownerUUID != null)
			compound.putUUID("OwnerUUID", ownerUUID);

		if (compound.contains("loot")) {
			ListNBT lootList = compound.getList("loot", Constants.NBT.TAG_COMPOUND);
			this.loot = new ArrayList<ItemStack>(Math.min(lootList.size(), 3));

			for (INBT nbt : lootList) {
				try {
					this.loot.add(ItemStack.of((CompoundNBT)nbt));
				}
				catch (Exception ignored) {}
			}

			updateLootData();
		}
	}

	@Nonnull
	public List<ItemStack> getLoot() {
		return this.loot == null ? Collections.emptyList() : loot;
	}

	@Override
	public boolean isPushable() {
		return true;
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
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		return source == DamageSource.OUT_OF_WORLD;
	}

	protected void doFishingCheckTick() {
		if (level.isClientSide() || ownerUUID == null)
			return;

		if (loot != null && loot.size() >= 3)
			return;

		if (!onGround || !isInWater())
			return;

		if (RandomUtil.oneInNChance(5000)) {
			FluidState fluid = level.getFluidState(blockPosition());

			if (!(fluid.getType() instanceof FlowingFluid)) {
				if (!RandomUtil.oneInNChance(3))
					return;
			}
			else {
				FlowingFluid flowingFluid = (FlowingFluid)fluid.getType();

				if (flowingFluid.getFlow(level, blockPosition(), fluid).lengthSqr() == 0 && !RandomUtil.fiftyFifty())
					return;
			}

			PlayerEntity owner = level.getPlayerByUUID(ownerUUID);

			if (owner != null) {
				LootContext.Builder lootContext = new LootContext.Builder((ServerWorld)this.level)
						.withParameter(LootParameters.ORIGIN, this.position())
						.withParameter(LootParameters.TOOL, new ItemStack(AoATools.FISHING_CAGE.get()))
						.withParameter(LootParameters.THIS_ENTITY, this)
						.withParameter(LootParameters.KILLER_ENTITY, owner)
						.withRandom(random).withLuck(2 + owner.getLuck());
				LootTable lootTable = level.getServer().getLootTables().get(AdventOfAscension.id("misc/fishing_cage_catches"));

				if (loot == null)
					loot = new ArrayList<ItemStack>(3);

				this.loot.addAll(lootTable.getRandomItems(lootContext.create(LootParameterSets.FISHING)));
				updateLootData();
			}
		}
	}

	protected void updateLootData() {
		EntityDataManager data = getEntityData();

		if (loot == null || loot.isEmpty()) {
			data.set(CAUGHT_STACK_1, ItemStack.EMPTY);
			data.set(CAUGHT_STACK_2, ItemStack.EMPTY);
			data.set(CAUGHT_STACK_3, ItemStack.EMPTY);
		}
		else {
			data.set(CAUGHT_STACK_1, loot.get(0));

			if (loot.size() > 1) {
				data.set(CAUGHT_STACK_2, loot.get(1));

				if (loot.size() > 2)
					data.set(CAUGHT_STACK_3, loot.get(2));
			}
		}
	}

	@Override
	public void tick() {
		boolean wasInWater = isInWater();

		baseTick();
		doFishingCheckTick();

		Vector3d velocity = getDeltaMovement();
		double gravity = 0.08d;

		if (isInWater()) {
			double yPos = this.getY();
			float drag = 0.8f;

			if (!wasInWater) {
				setDeltaMovement(velocity.multiply(0.1f, 0.1f, 0.1f));
			}

			if (velocity.y() < -0.023f)
				level.addParticle(ParticleTypes.BUBBLE, getX() + random.nextGaussian() * getBbWidth() * 0.5f, getY(), getZ() + random.nextGaussian() * getBbWidth() * 0.5f, 0, 0, 0);

			move(MoverType.SELF, getDeltaMovement());

			Vector3d motion = getDeltaMovement().multiply(drag, 0.8f, drag);
			double yVelocity;

			if (velocity.y() <= 0 && Math.abs(motion.y() - 0.005D) >= 0.003d && Math.abs(motion.y() - gravity / 16d) < 0.003d) {
				yVelocity = -0.003d;
			}
			else {
				yVelocity = motion.y() - gravity / 16d;
			}

			Vector3d newVelocity = new Vector3d(motion.x(), yVelocity, motion.z());

			setDeltaMovement(newVelocity);

			if (horizontalCollision && isFree(newVelocity.x(), newVelocity.y() + (double)0.6f - getY() + yPos, newVelocity.z()))
				setDeltaMovement(newVelocity.x(), 0.3F, newVelocity.z());
		}
		else {
			BlockPos feetPos = getBlockPosBelowThatAffectsMyMovement();
			float blockSlipperiness = this.level.getBlockState(this.getBlockPosBelowThatAffectsMyMovement()).getSlipperiness(level, this.getBlockPosBelowThatAffectsMyMovement(), this);
			float friction = this.onGround ? blockSlipperiness * 0.91f : 0.91f;

			move(MoverType.SELF, getDeltaMovement());

			Vector3d newVelocity = getDeltaMovement();
			double newYVelocity = newVelocity.y();

			if (this.level.isClientSide && !this.level.hasChunkAt(feetPos)) {
				if (getY() > 0) {
					newYVelocity = -0.1d;
				}
				else {
					newYVelocity = 0;
				}
			}
			else if (!this.isNoGravity()) {
				newYVelocity -= gravity;
			}

			setDeltaMovement(newVelocity.x() * (double)friction, newYVelocity * (double)0.98f, newVelocity.z() * (double)friction);
		}

		setDeltaMovement(getDeltaMovement().multiply(0.98f, 0.98f, 0.98f));

		if (!level.isClientSide())
			setSharedFlag(6, isGlowing());
	}

	@Override
	public void baseTick() {
		this.level.getProfiler().push("entityBaseTick");

		updateInWaterStateAndDoFluidPushing();

		if (!level.isClientSide()) {
			if (getY() < -64.0D)
				outOfWorld();

			if (isInLava())
				lavaHurt();
		}

		this.level.getProfiler().pop();
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (isInvulnerableTo(source))
			return false;

		if (!level.isClientSide() && amount >= 1) {
			Entity entity = source.getEntity();

			if (entity == null || entity.getUUID().equals(ownerUUID)) {
				markHurt();
				remove();
			}
		}

		return true;
	}

	@Override
	public void remove() {
		super.remove();

		if (!level.isClientSide() && loot != null) {
			for (ItemStack stack : loot) {
				spawnAtLocation(stack);
			}
		}
	}
}
