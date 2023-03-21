package net.tslat.aoa3.content.entity.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.entity.AoAMiscEntities;
import net.tslat.aoa3.common.registration.item.AoATools;
import net.tslat.aoa3.event.AoAPlayerEvents;
import net.tslat.aoa3.library.object.EntityDataHolder;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.smartbrainlib.util.RandomUtil;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;

public class FishingCageEntity extends Entity {
	public static final EntityDataHolder<ItemStack> CAUGHT_STACK_1 = EntityDataHolder.register(FishingCageEntity.class, EntityDataSerializers.ITEM_STACK, ItemStack.EMPTY, cage -> cage.loot[0], (cage, stack) -> cage.loot[0] = stack);
	public static final EntityDataHolder<ItemStack> CAUGHT_STACK_2 = EntityDataHolder.register(FishingCageEntity.class, EntityDataSerializers.ITEM_STACK, ItemStack.EMPTY, cage -> cage.loot[1], (cage, stack) -> cage.loot[1] = stack);
	public static final EntityDataHolder<ItemStack> CAUGHT_STACK_3 = EntityDataHolder.register(FishingCageEntity.class, EntityDataSerializers.ITEM_STACK, ItemStack.EMPTY, cage -> cage.loot[2], (cage, stack) -> cage.loot[2] = stack);

	private UUID ownerUUID = null;
	private int damage;
	private final ItemStack[] loot = new ItemStack[] {ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY};

	public FishingCageEntity(Level world, Player player, ItemStack stack) {
		this(AoAMiscEntities.FISHING_CAGE.get(), world);

		setPos(player.getX(), player.getY() + player.getEyeHeight(), player.getZ());
		setRot(player.getYRot(), player.getXRot());

		setDeltaMovement(EntityUtil.getDirectionForFacing(player).multiply(0.75f, 0.75f, 0.75f));

		this.ownerUUID = player.getUUID();
		this.damage = stack.getDamageValue();
	}

	public FishingCageEntity(EntityType<? extends Entity> entityType, Level world) {
		super(entityType, world);

		this.blocksBuilding = true;
	}

	@Override
	public InteractionResult interact(Player player, InteractionHand hand) {
		if (!level.isClientSide() && ownerUUID == null || player.getUUID().equals(ownerUUID)) {
			ItemStack fishingCage = new ItemStack(AoATools.FISHING_CAGE.get());
			int damage = this.damage + 1;

			if (damage < fishingCage.getMaxDamage()) {
				fishingCage.setDamageValue(damage);

				if (!player.isCreative())
					ItemUtil.givePlayerItemOrDrop(player, fishingCage);
			}

			if (hasCatches()) {
				AoAPlayerEvents.handleCustomInteraction((ServerPlayer)player, "fishing_cage_harvest", loot);

				for (ItemStack drop : loot) {
					if (drop.is(ItemTags.FISHES))
						player.awardStat(Stats.FISH_CAUGHT, 1);

					ItemUtil.givePlayerItemOrDrop(player, drop);
				}
			}

			player.awardStat(Stats.ITEM_USED.get(AoATools.FISHING_CAGE.get()));
			discard();

			return InteractionResult.SUCCESS;
		}

		return level.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.FAIL;
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	protected void defineSynchedData() {
		CAUGHT_STACK_1.defineDefault(this);
		CAUGHT_STACK_2.defineDefault(this);
		CAUGHT_STACK_3.defineDefault(this);
	}

	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
		super.onSyncedDataUpdated(key);

		if (!CAUGHT_STACK_1.checkSync(this, key) && !CAUGHT_STACK_2.checkSync(this, key))
			CAUGHT_STACK_3.checkSync(this, key);
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag compound) {
		if (compound.hasUUID("OwnerUUID"))
			ownerUUID = compound.getUUID("OwnerUUID");

		if (loot[0] != ItemStack.EMPTY) {
			ListTag lootList = new ListTag();

			for (ItemStack stack : loot) {
				lootList.add(stack.save(new CompoundTag()));
			}

			compound.put("loot", lootList);
		}
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag compound) {
		if (ownerUUID != null)
			compound.putUUID("OwnerUUID", ownerUUID);

		if (compound.contains("loot")) {
			ListTag lootList = compound.getList("loot", Tag.TAG_COMPOUND);

			if (lootList.size() > 2)
				CAUGHT_STACK_3.set(this, ItemStack.of(lootList.getCompound(2)));

			if (lootList.size() > 1)
				CAUGHT_STACK_2.set(this, ItemStack.of(lootList.getCompound(1)));

			CAUGHT_STACK_1.set(this, ItemStack.of(lootList.getCompound(0)));
		}
	}

	@Nonnull
	public ItemStack[] getLoot() {
		return this.loot;
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
		return tickCount > 1;
	}

	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		return source == DamageSource.OUT_OF_WORLD;
	}

	protected void doFishingCheckTick() {
		if (level.isClientSide() || ownerUUID == null)
			return;

		if (!CAUGHT_STACK_3.is(this, ItemStack.EMPTY))
			return;

		if (!onGround || !isInWater())
			return;

		if (RandomUtil.oneInNChance(5000)) {
			FluidState fluid = level.getFluidState(blockPosition());

			if (!(fluid.getType() instanceof FlowingFluid flowingFluid)) {
				if (!RandomUtil.oneInNChance(3))
					return;
			}
			else if (flowingFluid.getFlow(level, blockPosition(), fluid).lengthSqr() == 0 && !RandomUtil.fiftyFifty()) {
				return;
			}

			Player owner = level.getPlayerByUUID(ownerUUID);

			if (owner != null) {
				LootContext.Builder lootContext = new LootContext.Builder((ServerLevel)this.level)
						.withParameter(LootContextParams.ORIGIN, this.position())
						.withParameter(LootContextParams.TOOL, new ItemStack(AoATools.FISHING_CAGE.get()))
						.withParameter(LootContextParams.THIS_ENTITY, this)
						.withParameter(LootContextParams.KILLER_ENTITY, owner)
						.withRandom(random).withLuck(2 + owner.getLuck());
				LootTable lootTable = level.getServer().getLootTables().get(AdventOfAscension.id("misc/fishing_cage_catches"));
				List<ItemStack> loot = lootTable.getRandomItems(lootContext.create(LootContextParamSets.FISHING));

				for (int i = 0; i < 3 && i < loot.size(); i++) {
					if (CAUGHT_STACK_1.is(this, ItemStack.EMPTY)) {
						CAUGHT_STACK_1.set(this, loot.get(i));
					}
					else if (CAUGHT_STACK_2.is(this, ItemStack.EMPTY)) {
						CAUGHT_STACK_2.set(this, loot.get(i));
					}
					else {
						CAUGHT_STACK_3.set(this, loot.get(i));
					}
				}
			}
		}
	}

	@Override
	public void tick() {
		boolean wasInWater = isInWater();

		baseTick();
		doFishingCheckTick();

		Vec3 velocity = getDeltaMovement();
		double gravity = 0.08d;

		if (isInWater()) {
			double yPos = this.getY();

			if (!wasInWater) {
				setDeltaMovement(velocity.multiply(0.1f, 0.1f, 0.1f));
			}

			if (velocity.y() < -0.023f)
				level.addParticle(ParticleTypes.BUBBLE, getX() + random.nextGaussian() * getBbWidth() * 0.5f, getY(), getZ() + random.nextGaussian() * getBbWidth() * 0.5f, 0, 0, 0);

			move(MoverType.SELF, getDeltaMovement());

			Vec3 motion = getDeltaMovement().multiply(0.8f, 0.8f, 0.8f);
			double yVelocity;

			if (velocity.y() <= 0 && Math.abs(motion.y() - 0.005D) >= 0.003d && Math.abs(motion.y() - gravity / 16d) < 0.003d) {
				yVelocity = -0.003d;
			}
			else {
				yVelocity = motion.y() - gravity / 16d;
			}

			Vec3 newVelocity = new Vec3(motion.x(), yVelocity, motion.z());

			setDeltaMovement(newVelocity);

			if (horizontalCollision && isFree(newVelocity.x(), newVelocity.y() + (double)0.6f - getY() + yPos, newVelocity.z()))
				setDeltaMovement(newVelocity.x(), 0.3F, newVelocity.z());
		}
		else {
			BlockPos feetPos = getBlockPosBelowThatAffectsMyMovement();
			float blockSlipperiness = this.level.getBlockState(this.getBlockPosBelowThatAffectsMyMovement()).getFriction(level, this.getBlockPosBelowThatAffectsMyMovement(), this);
			float friction = this.onGround ? blockSlipperiness * 0.91f : 0.91f;

			move(MoverType.SELF, getDeltaMovement());

			Vec3 newVelocity = getDeltaMovement();
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
			setSharedFlag(6, hasGlowingTag());
	}

	@Override
	public void baseTick() {
		this.level.getProfiler().push("entityBaseTick");

		updateInWaterStateAndDoFluidPushing();

		if (!level.isClientSide()) {
			if (getY() < level.getMinBuildHeight() - 20)
				outOfWorld();

			if (isInLava())
				lavaHurt();

			if (isAlive() && tickCount > 40 && onGround && !isInWater()) {
				ItemStack fishingCage = new ItemStack(AoATools.FISHING_CAGE.get());

				if (hasCatches())
					this.damage += 1;

				fishingCage.setDamageValue(this.damage);
				spawnAtLocation(fishingCage);
				discard();
			}
		}

		this.level.getProfiler().pop();
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (isInvulnerableTo(source))
			return false;

		if (!level.isClientSide() && amount >= 1) {
			markHurt();
			discard();
		}

		return true;
	}

	@Override
	public void remove(RemovalReason reason) {
		if (!level.isClientSide() && hasCatches()) {
			for (ItemStack stack : loot) {
				spawnAtLocation(stack);
			}
		}

		super.remove(reason);
	}

	public boolean hasCatches() {
		return !CAUGHT_STACK_1.is(this, ItemStack.EMPTY);
	}
}
