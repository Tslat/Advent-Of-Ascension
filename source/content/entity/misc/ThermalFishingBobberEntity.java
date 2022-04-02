package net.tslat.aoa3.content.entity.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;
import net.tslat.aoa3.common.registration.entity.AoAMiscEntities;
import net.tslat.aoa3.data.server.AoAHaulingFishReloadListener;
import net.tslat.aoa3.event.AoAPlayerEvents;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;
import java.util.function.Function;

public class ThermalFishingBobberEntity extends HaulingFishingBobberEntity {
	public ThermalFishingBobberEntity(Level world, Player player, double posX, double posY, double posZ) {
		super(world, player, posX, posY, posZ);
	}

	public ThermalFishingBobberEntity(Player player, Level world, ItemStack rod) {
		super(player, world, rod);
	}

	public ThermalFishingBobberEntity(Player player, Level world, ItemStack rod, float luck, float lure) {
		super(player, world, rod, luck, lure);
	}

	@Override
	public TagKey<Fluid> getApplicableFluid() {
		return FluidTags.LAVA;
	}

	@Override
	public EntityType<?> getType() {
		return AoAMiscEntities.THERMAL_BOBBER.get();
	}

	@Override
	protected void spawnFish(ServerPlayer player) {
		Function<Level, Entity> fishFunction = AoAHaulingFishReloadListener.getFishListForBiome(level.getBiome(blockPosition()).value(), true).getRandomElement(player, getLuck());

		if (fishFunction != null) {
			Entity entity = fishFunction.apply(player.level);

			if (entity == null)
				return;

			if (entity instanceof Mob mob) {
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

		Biome biome = level.getBiome(blockPosition()).value();
		float temperature = biome.getTemperature(blockPosition());

		if (temperature > 2) {
			this.fishingBonusMod *= 1.25f;
		}
		else if (temperature < 1.5f) {
			this.fishingBonusMod *= 0.8f;
		}

		if (biome.getPrecipitation() == Biome.Precipitation.NONE) {
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
			Vec3 vector3d = this.getDeltaMovement();
			double fluidAdjustedHeight = this.getY() + vector3d.y - (double)pos.getY() - (double)fluidHeight + 0.1;

			if (Math.abs(fluidAdjustedHeight) < 0.01D)
				fluidAdjustedHeight += Math.signum(fluidAdjustedHeight) * 0.1D;

			setDeltaMovement(vector3d.x * 0.9D, vector3d.y - fluidAdjustedHeight * (double)this.random.nextFloat() * 0.05D, vector3d.z * 0.9D);
		}
	}

	@Override
	protected float fishingBonusModForBiomeCategory(Biome.BiomeCategory category) {
		return switch (category) {
			case OCEAN, RIVER, SWAMP -> 0.5f;
			case DESERT, SAVANNA, MESA, NETHER -> 1.25f;
			default -> 1;
		};
	}

	@Nullable
	public static ThermalFishingBobberEntity handleClientSpawn(PlayMessages.SpawnEntity packet, Level world) {
		Entity owner = world.getEntity((int)packet.getPosY());

		if (owner instanceof Player)
			return new ThermalFishingBobberEntity(world, (Player)owner, packet.getPosX(), owner.getEyeY(), packet.getPosZ());

		return null;
	}
}
