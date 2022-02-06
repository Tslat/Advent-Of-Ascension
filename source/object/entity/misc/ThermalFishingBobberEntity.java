package net.tslat.aoa3.object.entity.misc;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.data.server.AoAHaulingFishReloadListener;
import net.tslat.aoa3.event.AoAPlayerEvents;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;
import java.util.function.Function;

public class ThermalFishingBobberEntity extends HaulingFishingBobberEntity {
	public ThermalFishingBobberEntity(World world, PlayerEntity player, double posX, double posY, double posZ) {
		super(world, player, posX, posY, posZ);
	}

	public ThermalFishingBobberEntity(PlayerEntity player, World world, ItemStack rod) {
		super(player, world, rod);
	}

	public ThermalFishingBobberEntity(PlayerEntity player, World world, ItemStack rod, float luck, float lure) {
		super(player, world, rod, luck, lure);
	}

	@Override
	public ITag<Fluid> getApplicableFluid() {
		return FluidTags.LAVA;
	}

	@Override
	public EntityType<?> getType() {
		return AoAEntities.Misc.THERMAL_BOBBER.get();
	}

	@Override
	protected void spawnFish(ServerPlayerEntity player) {
		Function<World, Entity> fishFunction = AoAHaulingFishReloadListener.getFishListForBiome(level.getBiome(blockPosition()), true).getRandomElement(player, getLuck());

		if (fishFunction != null) {
			Entity entity = fishFunction.apply(player.level);

			if (entity == null)
				return;

			if (entity instanceof MobEntity) {
				MobEntity mob = (MobEntity)entity;
				BlockPos pos = RandomUtil.getRandomPositionWithinRange(this.blockPosition(), 10, 10, 10, false, level, state -> state.getFluidState().getType() == Fluids.LAVA, 5);

				mob.setPos(pos.getX(), pos.getY(), pos.getZ());
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

	@Override
	protected void calculateFishingLureBonus() {
		this.fishingBonusMod = 1;

		Biome biome = level.getBiome(blockPosition());
		float temperature = biome.getTemperature(blockPosition());

		if (temperature > 2) {
			this.fishingBonusMod *= 1.25f;
		}
		else if (temperature < 1.5f) {
			this.fishingBonusMod *= 0.8f;
		}

		if (biome.getPrecipitation() == Biome.RainType.NONE) {
			this.fishingBonusMod *= 1.1f;
		}
		else {
			this.fishingBonusMod *= 0.9f;
		}

		if (level.isRainingAt(blockPosition()))
			this.fishingBonusMod *= 0.75f;

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

	@Override
	protected void doBobbing(FluidState fluidState) {
		if (state == State.IN_FLUID) {
			BlockPos pos = blockPosition();
			float fluidHeight = fluidState.getHeight(level, pos);
			Vector3d vector3d = this.getDeltaMovement();
			double fluidAdjustedHeight = this.getY() + vector3d.y - (double)pos.getY() - (double)fluidHeight + 0.1;

			if (Math.abs(fluidAdjustedHeight) < 0.01D)
				fluidAdjustedHeight += Math.signum(fluidAdjustedHeight) * 0.1D;

			setDeltaMovement(vector3d.x * 0.9D, vector3d.y - fluidAdjustedHeight * (double)this.random.nextFloat() * 0.05D, vector3d.z * 0.9D);
		}
	}

	@Override
	protected float fishingBonusModForBiomeCategory(Biome.Category category) {
		switch (category) {
			case OCEAN:
			case RIVER:
			case SWAMP:
				return 0.5f;
			case DESERT:
			case SAVANNA:
			case MESA:
			case NETHER:
				return 1.25f;
			case THEEND:
			default:
				return 1;
		}
	}

	@Nullable
	public static ThermalFishingBobberEntity handleClientSpawn(FMLPlayMessages.SpawnEntity packet, World world) {
		Entity owner = world.getEntity((int)packet.getPosY());

		if (owner instanceof PlayerEntity)
			return new ThermalFishingBobberEntity(world, (PlayerEntity)owner, packet.getPosX(), owner.getEyeY(), packet.getPosZ());

		return null;
	}
}
