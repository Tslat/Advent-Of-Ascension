package net.tslat.aoa3.content.entity.misc;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.data.server.AoAHaulingFishReloadListener;
import net.tslat.aoa3.event.AoAPlayerEvents;
import net.tslat.aoa3.content.item.tool.misc.HaulingRod;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;
import java.util.function.Function;

public class HaulingFishingBobberEntity extends FishingBobberEntity {
	protected static final DataParameter<Integer> HOOKED_ENTITY = EntityDataManager.defineId(HaulingFishingBobberEntity.class, DataSerializers.INT);
	protected static final DataParameter<Integer> STATE = EntityDataManager.defineId(HaulingFishingBobberEntity.class, DataSerializers.INT);

	protected final ItemStack rod;
	protected float luck;
	protected float lure;

	protected float fishingBonusMod = 1;
	protected int timeUntilFishSpawn = -1;
	protected Entity hookedEntity = null;
	protected Entity spawnedFish = null;
	protected long hookTime = 0;
	protected double lastFishDist = 0;

	protected State state = State.MIDAIR;

	public int ownerId;

	// Clientside constructor, don't use.
	public HaulingFishingBobberEntity(World world, PlayerEntity player, double posX, double posY, double posZ) {
		super(world, player, posX, posY, posZ);

		this.rod = null;
		this.luck = 0;
		this.lure = 0;
		this.ownerId = player.getId();
	}

	public HaulingFishingBobberEntity(PlayerEntity player, World world, ItemStack rod) {
		this(player, world, rod, 0, 0);

		this.luck = calculateLuck(player, rod);
		this.lure = calculateLure(player, rod);
	}

	public HaulingFishingBobberEntity(PlayerEntity player, World world, ItemStack rod, float luck, float lure) {
		super(player, world, 0, 0);

		this.rod = rod;
		this.luck = luck;
		this.lure = lure;
		this.ownerId = player.getId();
		float castStrength = getCastStrength();

		setDeltaMovement(getDeltaMovement().multiply(castStrength, castStrength, castStrength));
	}

	@Override
	protected void defineSynchedData() {
		getEntityData().define(HOOKED_ENTITY, -1);
		getEntityData().define(STATE, 0);
	}

	protected float calculateLuck(PlayerEntity player, ItemStack rod) {
		float luck = player.getLuck();

		if (rod.getItem() instanceof HaulingRod) {
			luck += ((HaulingRod)rod.getItem()).getLuckMod(player, rod);
		}
		else {
			luck += EnchantmentHelper.getFishingLuckBonus(rod);
		}

		return luck;
	}

	protected float calculateLure(PlayerEntity player, ItemStack rod) {
		if (rod.getItem() instanceof HaulingRod) {
			return ((HaulingRod)rod.getItem()).getLureMod(player, rod);
		}
		else {
			return EnchantmentHelper.getFishingLuckBonus(rod);
		}
	}

	protected int minLureTime() {
		return 100;
	}

	protected int maxLureTime() {
		return 700;
	}

	public State getState() {
		return this.state;
	}

	public float getLuck() {
		return this.luck;
	}

	protected void calculateFishingLureBonus() {
		this.fishingBonusMod = 1;

		Biome biome = level.getBiome(blockPosition());
		float temperature = biome.getTemperature(blockPosition());

		if (temperature < 0.15f) {
			this.fishingBonusMod *= 0.9f;
		}
		else if (temperature > 1.5f) {
			this.fishingBonusMod *= 0.8f;
		}

		if (biome.getPrecipitation() == Biome.RainType.RAIN)
			this.fishingBonusMod *= 1.1f;

		if (level.isRainingAt(blockPosition()))
			this.fishingBonusMod *= 1.1f;

		this.fishingBonusMod *= fishingBonusModForBiomeCategory(biome.getBiomeCategory());

		int nearbyFluidBlocks = WorldUtil.getBlocksWithinAABB(level, getBoundingBox().inflate(2, 1, 2), (state, pos) -> state.getFluidState().is(getApplicableFluid()) && state.getFluidState().isSource()).size();

		if (nearbyFluidBlocks <=  50) {
			this.fishingBonusMod *= 0.5f;

			if (nearbyFluidBlocks < 15)
				this.fishingBonusMod *= 0.5f;
		}

		this.fishingBonusMod *= 1 + (nearbyFluidBlocks * 0.0035f);
		this.fishingBonusMod += 0.25f * lure;

		if (!WorldUtil.getAllPlayersInRegion(level, getBoundingBox().inflate(5)).isEmpty())
			this.fishingBonusMod *= 0.2f;
	}

	protected float fishingBonusModForBiomeCategory(Biome.Category category) {
		switch (category) {
			case OCEAN:
			case RIVER:
			case SWAMP:
				return 1.25f;
			case DESERT:
			case THEEND:
			case SAVANNA:
			case MESA:
			case NETHER:
				return 0.5f;
			default:
				return 1;
		}
	}

	@Override
	public void absMoveTo(double x, double y, double z, float pitch, float yaw) { // Because we have to use hacky spawn positioning
		if (y == ownerId)
			return;

		super.absMoveTo(x, y, z, pitch, yaw);
	}

	@Override
	public void tick() {
		handleSuperTick();

		PlayerEntity player = getPlayerOwner();

		if (!level.isClientSide() && !checkStillValid(player)) {
			remove();

			return;
		}

		if (isInLava() && getApplicableFluid() != FluidTags.LAVA) {
			remove();

			return;
		}

		if (!level.isClientSide())
			updateState();

		if (hookedEntity == null)
			checkIfCollided();

		if (!level.isClientSide() && position().distanceToSqr(player.position()) > Math.pow(getMaxCastDistance() * 2f, 2)) {
			remove();

			return;
		}

		BlockPos pos = blockPosition();
		FluidState fluidState = level.getFluidState(pos);

		if (state == State.HOOKED_FISH || state == State.HOOKED_IN_ENTITY) {
			if (hookedEntity != null) {
				setPos(hookedEntity.getX(), hookedEntity.getY(0.8d) - 0.1d, hookedEntity.getZ());

				if (state == State.HOOKED_FISH) {
					if (!level.isClientSide() && hookTime-- <= 0) {
						stopFishing();
					}
					else {
						if (EntityUtil.isEntityMoving(player))
							hookTime -= 2;

						if (hookedEntity instanceof CreatureEntity) {
							if (!level.isClientSide()) {
								CreatureEntity creature = (CreatureEntity)hookedEntity;

								if (creature.getNavigation().isDone()) {
									Vector3d targetPos = RandomPositionGenerator.getPosAvoid(creature, 30, 5, player.position());

									if (targetPos != null)
										creature.getNavigation().moveTo(creature.getNavigation().createPath(new BlockPos(targetPos), 0), 5);
								}
							}
						}
						else {
							if (hookedEntity.isOnGround()) {
								stopFishing();
							}
							else {
								hookedEntity.setDeltaMovement(hookedEntity.getDeltaMovement().subtract(0, 0.008, 0));
							}
						}
					}
				}
			}

			return;
		}

		if (!level.isClientSide()) {
			if (timeUntilFishSpawn >= 0) {
				if (timeUntilFishSpawn-- == 0)
					spawnFish((ServerPlayerEntity)player);
			}
			else if (state == State.IN_FLUID) {
				if (spawnedFish != null && spawnedFish.isAlive()) {
					if (spawnedFish instanceof CreatureEntity) {
						CreatureEntity creature = (CreatureEntity)spawnedFish;
						BlockPos targetPos = blockPosition();
						PathNavigator navigator = creature.getNavigation();
						float dist = (float)creature.distanceToSqr(this);

						if (lastFishDist - dist < 0.2 || dist <= 5) {
							EntityUtil.pullEntityIn(this, creature, 0.025f, false);

							if (dist <= 5)
								navigator.stop();
						}
						else if (!creature.isPathFinding() || !navigator.getTargetPos().equals(targetPos)) {
							navigator.moveTo(navigator.createPath(targetPos, 0), 0.5f);
						}

						lastFishDist = dist;
					}
				}
				else {
					startFishing();
				}
			}
		}

		doBobbing(fluidState);

		if (state == State.STUCK || state == State.MIDAIR)
			setDeltaMovement(getDeltaMovement().subtract(0, 0.08d, 0));

		if (state == State.IN_FLUID)
			setDeltaMovement(getDeltaMovement().scale(0.92d));

		move(MoverType.SELF, getDeltaMovement());
		updateRotation();
		reapplyPosition();
	}

	protected void doBobbing(FluidState fluidState) {
		if (state == State.IN_FLUID) {
			BlockPos pos = blockPosition();
			float fluidHeight = fluidState.getHeight(level, pos);
			Vector3d vector3d = this.getDeltaMovement();
			double fluidAdjustedHeight = this.getY() + vector3d.y - (double)pos.getY() - (double)fluidHeight + 0.1;

			if (Math.abs(fluidAdjustedHeight) < 0.01D)
				fluidAdjustedHeight += Math.signum(fluidAdjustedHeight) * 0.1D;

			setDeltaMovement(vector3d.x * 0.9D, vector3d.y - fluidAdjustedHeight * (double)this.random.nextFloat() * 0.2D, vector3d.z * 0.9D);
		}
	}

	protected void updateState() {
		State fromState = getState();

		if (onGround || horizontalCollision) {
			state = State.STUCK;
		}
		else if (state == State.STUCK) {
			state = State.MIDAIR;
		}

		if (hookedEntity != null && hookedEntity.isAlive()) {
			state = hookedEntity == spawnedFish ? State.HOOKED_FISH : State.HOOKED_IN_ENTITY;
		}
		else if (state == State.HOOKED_FISH || state == State.HOOKED_IN_ENTITY) {
			state = State.MIDAIR;
		}

		if (state == State.IN_FLUID && spawnedFish != null && spawnedFish.distanceToSqr(this) < 0.25f)
			state = State.HOOKED_FISH;

		FluidState fluidState = level.getFluidState(blockPosition());

		if (fluidState.is(getApplicableFluid())) {
			if (state == State.MIDAIR)
				state = State.IN_FLUID;
		}
		else if (state == State.IN_FLUID) {
			state = State.MIDAIR;
		}

		if (getState() != fromState)
			onStateChange(fromState, getState());
	}

	protected void onStateChange(State fromState, State toState) {
		if (fromState == State.IN_FLUID) {
			if (hookedEntity == null && spawnedFish == null) {
				stopFishing();
			}
			else if (toState == State.HOOKED_FISH) {
				hookedEntity = spawnedFish;
				hookTime = 1200;
			}
		}

		if (toState == State.HOOKED_IN_ENTITY || toState == State.HOOKED_FISH || toState == State.STUCK) {
			setDeltaMovement(Vector3d.ZERO);

			if (toState == State.STUCK) {
				stopFishing();
			}
			else {
				updateHookedEntity();
			}
		}
		else if (toState == State.IN_FLUID) {
			setDeltaMovement(getDeltaMovement().multiply(0.3d, 0.2d, 0.3d));
			startFishing();
		}

		if (fromState == State.IN_FLUID && hookedEntity == null && spawnedFish == null)
			stopFishing();

		if (fromState == State.HOOKED_FISH || fromState == State.HOOKED_IN_ENTITY)
			updateHookedEntity();

		getEntityData().set(STATE, state.value());
	}

	protected void startFishing() {
		stopFishing();
		calculateFishingLureBonus();

		int minTime = minLureTime();
		int maxTime = maxLureTime();

		if (fishingBonusMod < 1) {
			minTime /= fishingBonusMod * 2f;
			maxTime /= fishingBonusMod * 3f;
		}
		else {
			maxTime /= fishingBonusMod;
		}

		timeUntilFishSpawn = MathHelper.nextInt(random, minTime, minTime + 50 + (maxTime - minTime));
	}

	protected void stopFishing() {
		timeUntilFishSpawn = -1;
		hookTime = 0;
		lastFishDist = 0;

		if (hookedEntity != null) {
			if (hookedEntity == spawnedFish)
				hookedEntity.remove();

			hookedEntity = null;
		}

		if (spawnedFish != null) {
			spawnedFish.remove();
			spawnedFish = null;
		}

		if (!removed)
			updateHookedEntity();
	}

	protected void spawnFish(ServerPlayerEntity player) {
		Function<World, Entity> fishFunction = AoAHaulingFishReloadListener.getFishListForBiome(level.getBiome(blockPosition()), false).getRandomElement(player, getLuck());

		if (fishFunction != null) {
			Entity entity = fishFunction.apply(player.level);

			if (entity == null)
				return;

			if (entity instanceof MobEntity) {
				MobEntity mob = (MobEntity)entity;
				BlockPos pos = RandomUtil.getRandomPositionWithinRange(this.blockPosition(), 10, 10, 10, false, level, state -> state.getFluidState().getType() == Fluids.WATER, 5);

				mob.setPos(pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f);
				mob.getNavigation().createPath(blockPosition(), 0);
				level.addFreshEntity(mob);
			}
			else {
				entity.setPos(getX(), getY() - entity.getBbHeight(), getZ());
				level.addFreshEntity(entity);
			}

			spawnedFish = entity;

			AoAPlayerEvents.handleCustomInteraction(player, "hauling_spawn_fish", spawnedFish);
		}
	}

	protected void checkIfCollided() {
		RayTraceResult rayTrace = ProjectileHelper.getHitResult(this, this::canHitEntity);

		if (rayTrace.getType() != RayTraceResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, rayTrace))
			this.onHit(rayTrace);
	}

	@Override
	protected boolean canHitEntity(Entity entity) {
		if (entity.isSpectator() || !entity.isAlive())
			return false;

		if (entity instanceof ItemEntity)
			return true;

		if (!entity.isPickable())
			return false;

		Entity owner = getOwner();

		return owner == null || !owner.isPassengerOfSameVehicle(entity);
	}

	private void handleSuperTick() {
		if (!this.leftOwner)
			this.leftOwner = checkLeftOwner();

		if (!this.level.isClientSide())
			setSharedFlag(6, isGlowing());

		baseTick();
	}

	protected boolean checkStillValid(PlayerEntity owner) {
		if (!isAlive())
			return false;

		if (owner == null || !owner.isAlive())
			return false;

		if (rod == null || rod.getItem() == Items.AIR)
			return false;

		float maxCastDistance = getMaxCastDistance();

		if (owner.distanceToSqr(this) > maxCastDistance * maxCastDistance)
			return false;

		return ItemUtil.isHoldingItem(owner, rod.getItem());
	}

	@Override
	protected void onHitEntity(EntityRayTraceResult rayTrace) {
		if (!level.isClientSide()) {
			this.hookedEntity = rayTrace.getEntity();

			updateHookedEntity();
		}
	}

	@Override
	public void onSyncedDataUpdated(DataParameter<?> param) {
		if (param == HOOKED_ENTITY) {
			int id = getEntityData().get(HOOKED_ENTITY);

			this.hookedEntity = id == -1 ? null : this.level.getEntity(id);
		}
		else if (param == STATE) {
			this.state = State.byValue(getEntityData().get(STATE));
		}

		super.onSyncedDataUpdated(param);
	}

	protected void updateHookedEntity() {
		if (!level.isClientSide())
			getEntityData().set(HOOKED_ENTITY, this.hookedEntity == null ? -1 : this.hookedEntity.getId());
	}

	@Override
	public EntityType<?> getType() {
		return AoAEntities.Misc.REINFORCED_BOBBER.get();
	}

	@Override
	public EntitySize getDimensions(Pose pose) {
		return getType().getDimensions();
	}

	protected float getMaxCastDistance() {
		return 32;
	}

	protected float getCastStrength() {
		return 1;
	}

	public ITag<Fluid> getApplicableFluid() {
		return FluidTags.WATER;
	}

	@Override
	public void handleEntityEvent(byte p_70103_1_) {}

	@Nullable
	public static HaulingFishingBobberEntity handleClientSpawn(FMLPlayMessages.SpawnEntity packet, World world) {
		Entity owner = world.getEntity((int)packet.getPosY());

		if (owner instanceof PlayerEntity)
			return new HaulingFishingBobberEntity(world, (PlayerEntity)owner, packet.getPosX(), owner.getEyeY(), packet.getPosZ());

		return null;
	}

	@Nullable
	@Override
	public Entity getHookedIn() {
		return this.hookedEntity;
	}

	@Override
	public void remove(boolean keepData) {
		super.remove(keepData);

		stopFishing();
	}

	@Override
	public IPacket<?> getAddEntityPacket() {
		double y = getY();

		setPosRaw(getX(), ownerId, getZ());

		IPacket<?> spawnPacket = NetworkHooks.getEntitySpawningPacket(this);

		setPosRaw(getX(), y, getZ());

		if (ownerId == -1)
			remove();

		return spawnPacket;
	}

	public enum State {
		MIDAIR(0),
		HOOKED_FISH(1),
		HOOKED_IN_ENTITY(2),
		IN_FLUID(3),
		STUCK(4);

		private final int value;

		State(int value) {
			this.value = value;
		}

		public int value() {
			return this.value;
		}

		public static State byValue(int value) {
			switch (value) {
				case 0:
					return MIDAIR;
				case 1:
					return HOOKED_FISH;
				case 2:
					return HOOKED_IN_ENTITY;
				case 3:
					return IN_FLUID;
				case 4:
				default:
					return STUCK;
			}
		}
	}
}
