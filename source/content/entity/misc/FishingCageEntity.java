package net.tslat.aoa3.content.entity.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
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
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.Tags;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.common.registration.entity.AoAMiscEntities;
import net.tslat.aoa3.common.registration.item.AoATools;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.InventoryUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.tme.api.util.EntityRetrievalUtil;
import net.tslat.tme.api.util.RandomUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class FishingCageEntity extends BasicMiscEntity implements OwnableEntity {
	public static final EntityDataAccessor<ItemStack> CAUGHT_STACK_1 = makeSynchedData(FishingCageEntity.class, EntityDataSerializers.ITEM_STACK);
	public static final EntityDataAccessor<ItemStack> CAUGHT_STACK_2 = makeSynchedData(FishingCageEntity.class, EntityDataSerializers.ITEM_STACK);
	public static final EntityDataAccessor<ItemStack> CAUGHT_STACK_3 = makeSynchedData(FishingCageEntity.class, EntityDataSerializers.ITEM_STACK);

	public static final ResourceKey<LootTable> FISHING_CAGE_LOOT_TABLE = ResourceKey.create(Registries.LOOT_TABLE, AdventOfAscension.id("misc/fishing_cage_catches"));

	private UUID ownerUUID = null;
	private int damage;

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

	@Nullable
	@Override
	public UUID getOwnerUUID() {
		return this.ownerUUID;
	}

	@NotNull
	public ItemStack[] getLoot() {
		return new ItemStack[] {getSynchedData(CAUGHT_STACK_1), getSynchedData(CAUGHT_STACK_2), getSynchedData(CAUGHT_STACK_3)};
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
		return this.tickCount > 1 && super.canBeCollidedWith();
	}

	@Nullable
	@Override
	public ItemStack getPickResult() {
		return AoATools.FISHING_CAGE.get().getDefaultInstance();
	}

	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		return !source.is(Tags.DamageTypes.IS_TECHNICAL);
	}

	@Override
	public InteractionResult interact(Player player, InteractionHand hand) {
		if (player instanceof ServerPlayer pl && (this.ownerUUID == null || pl.getUUID().equals(this.ownerUUID))) {
			ItemStack fishingCage = new ItemStack(AoATools.FISHING_CAGE.get());
			int damage = this.damage;

			if (hasCatches()) {
				AoASkill.Instance hauling = PlayerUtil.getAdventPlayer(pl).getSkill(AoASkills.HAULING.get());
				ItemStack[] loot = getLoot();
				damage += loot.length;

				if (hauling.canGainXp(true)) {
					float xp = PlayerUtil.getTimeBasedXpForLevel(hauling.getLevel(true), 1000) * Math.min(4, loot.length);

					hauling.adjustXp(xp, false, false);
				}

				for (ItemStack drop : loot) {
					if (drop.is(ItemTags.FISHES))
						pl.awardStat(Stats.FISH_CAUGHT, 1);

					InventoryUtil.giveItemTo(pl, drop);
				}
			}

			if (damage < fishingCage.getMaxDamage()) {
				fishingCage.setDamageValue(damage);

				if (!pl.hasInfiniteMaterials())
					InventoryUtil.giveItemTo(pl, fishingCage);
			}

			pl.awardStat(Stats.ITEM_USED.get(AoATools.FISHING_CAGE.get()));
			discard();

			return InteractionResult.SUCCESS;
		}

		return level().isClientSide() ? InteractionResult.SUCCESS : InteractionResult.FAIL;
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		builder.define(CAUGHT_STACK_1, ItemStack.EMPTY);
		builder.define(CAUGHT_STACK_2, ItemStack.EMPTY);
		builder.define(CAUGHT_STACK_3, ItemStack.EMPTY);
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag compound) {
		if (this.ownerUUID != null)
			compound.putUUID("OwnerUUID", this.ownerUUID);

		compound.putInt("Damage", this.damage);

		ListTag lootList = new ListTag();
		ItemStack[] loot = getLoot();

		for (int i = 0; i < 3; i++) {
			if (!loot[i].isEmpty())
				lootList.add(loot[i].save(registryAccess()));
		}

		if (!lootList.isEmpty())
			compound.put("loot", lootList);
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag compound) {
		if (compound.hasUUID("OwnerUUID"))
			this.ownerUUID = compound.getUUID("OwnerUUID");

		if (compound.contains("Damage", Tag.TAG_INT))
			this.damage = compound.getInt("Damage");

		if (compound.contains("loot")) {
			ListTag lootList = compound.getList("loot", Tag.TAG_COMPOUND);

			if (lootList.size() > 2)
				setSynchedData(CAUGHT_STACK_3, ItemStack.parseOptional(level().registryAccess(), lootList.getCompound(2)));

			if (lootList.size() > 1)
				setSynchedData(CAUGHT_STACK_2, ItemStack.parseOptional(level().registryAccess(), lootList.getCompound(1)));

			setSynchedData(CAUGHT_STACK_1, ItemStack.parseOptional(level().registryAccess(), lootList.getCompound(0)));
		}
	}

	protected void doFishingCheckTick() {
		if (level().isClientSide() || this.ownerUUID == null)
			return;

		if (!getSynchedData(CAUGHT_STACK_3).isEmpty())
			return;

		if (!onGround() || !isInWater())
			return;

		if (RandomUtil.oneInNChance(5000)) {
			FluidState fluid = level().getFluidState(blockPosition());

			if (!(fluid.getType() instanceof FlowingFluid flowingFluid)) {
				if (!RandomUtil.oneInNChance(10))
					return;
			}
			else if (flowingFluid.getFlow(level(), blockPosition(), fluid).lengthSqr() == 0 && !RandomUtil.fiftyFifty()) {
				return;
			}

			if (!RandomUtil.oneInNChance(Math.max(1, EntityRetrievalUtil.getEntities(this, 5, entity -> entity instanceof FishingCageEntity).size())))
				return;

			Player owner = level().getPlayerByUUID(this.ownerUUID);

			if (owner != null) {
				LootParams.Builder lootContext = new LootParams.Builder((ServerLevel)this.level())
						.withParameter(LootContextParams.ORIGIN, this.position())
						.withParameter(LootContextParams.TOOL, new ItemStack(AoATools.FISHING_CAGE.get()))
						.withParameter(LootContextParams.THIS_ENTITY, this)
						.withParameter(LootContextParams.ATTACKING_ENTITY, owner)
						.withLuck(2 + owner.getLuck());
				LootTable lootTable = level().getServer().reloadableRegistries().getLootTable(FISHING_CAGE_LOOT_TABLE);
				List<ItemStack> loot = lootTable.getRandomItems(lootContext.create(LootContextParamSets.FISHING));

				for (int i = 0; i < 3 && i < loot.size(); i++) {
					if (getSynchedData(CAUGHT_STACK_1).isEmpty()) {
						setSynchedData(CAUGHT_STACK_1, loot.get(i));
					}
					else if (getSynchedData(CAUGHT_STACK_2).isEmpty()) {
						setSynchedData(CAUGHT_STACK_2, loot.get(i));
					}
					else {
						setSynchedData(CAUGHT_STACK_3, loot.get(i));
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
				level().addParticle(ParticleTypes.BUBBLE, getX() + random.nextGaussian() * getBbWidth() * 0.5f, getY(), getZ() + random.nextGaussian() * getBbWidth() * 0.5f, 0, 0, 0);

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
			float blockSlipperiness = this.level().getBlockState(this.getBlockPosBelowThatAffectsMyMovement()).getFriction(level(), this.getBlockPosBelowThatAffectsMyMovement(), this);
			float friction = this.onGround() ? blockSlipperiness * 0.91f : 0.91f;

			move(MoverType.SELF, getDeltaMovement());

			Vec3 newVelocity = getDeltaMovement();
			double newYVelocity = newVelocity.y();

			if (this.level().isClientSide && !this.level().hasChunkAt(feetPos)) {
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

		if (!level().isClientSide())
			setSharedFlag(6, hasGlowingTag());
	}

	@Override
	public void baseTick() {
		this.level().getProfiler().push("entityBaseTick");

		updateInWaterStateAndDoFluidPushing();

		if (!level().isClientSide()) {
			if (getY() < level().getMinBuildHeight() - 20)
				onBelowWorld();

			if (isInLava())
				lavaHurt();

			if (isAlive() && tickCount > 40 && onGround() && !isInWater()) {
				ItemStack fishingCage = new ItemStack(AoATools.FISHING_CAGE.get());

				if (hasCatches())
					this.damage += 1;

				fishingCage.setDamageValue(this.damage);
				spawnAtLocation(fishingCage);
				discard();
			}
		}

		this.level().getProfiler().pop();
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (isInvulnerableTo(source))
			return false;

		if (!level().isClientSide() && amount >= 1) {
			markHurt();
			discard();
		}

		return true;
	}

	@Override
	public void remove(RemovalReason reason) {
		if (!level().isClientSide() && hasCatches()) {
			for (ItemStack stack : getLoot()) {
				spawnAtLocation(stack);
			}
		}

		super.remove(reason);
	}

	public boolean hasCatches() {
		return !getSynchedData(CAUGHT_STACK_1).isEmpty();
	}
}
