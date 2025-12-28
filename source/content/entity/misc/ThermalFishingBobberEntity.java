package net.tslat.aoa3.content.entity.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.common.registration.entity.AoAMiscEntities;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.tme.api.util.EntityRetrievalUtil;

public class ThermalFishingBobberEntity extends HaulingFishingBobberEntity {
	public ThermalFishingBobberEntity(ServerPlayer player, Level world, ItemStack rod) {
		super(player, world, rod);
	}

	public ThermalFishingBobberEntity(Player player, Level world, ItemStack rod, float luck, float lure) {
		super(player, world, rod, luck, lure);
	}

	public ThermalFishingBobberEntity(EntityType<? extends ThermalFishingBobberEntity> entityType, Level level) {
		super(entityType, level);
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
	protected void calculateFishingLureBonus() {
		this.fishingBonusMod = 1;

		Holder<Biome> biome = level().getBiome(blockPosition());
		float temperature = biome.value().getTemperature(blockPosition());

		if (temperature > 2) {
			this.fishingBonusMod *= 1.25f;
		}
		else if (temperature < 1.5f) {
			this.fishingBonusMod *= 0.8f;
		}

		if (biome.value().getPrecipitationAt(blockPosition()) == Biome.Precipitation.NONE) {
			this.fishingBonusMod *= 1.1f;
		}
		else {
			this.fishingBonusMod *= 0.9f;
		}

		if (level().isRainingAt(blockPosition()))
			this.fishingBonusMod *= 0.75f;

		this.fishingBonusMod *= fishingBonusModForBiome(biome);

		int nearbyFluidBlocks = WorldUtil.getBlocksWithinAABB(level(), getBoundingBox().inflate(2, 1, 2), (state, pos) -> state.getFluidState().is(getApplicableFluid()) && state.getFluidState().isSource()).size();

		if (nearbyFluidBlocks <=  50) {
			this.fishingBonusMod *= 0.5f;

			if (nearbyFluidBlocks < 15)
				this.fishingBonusMod *= 0.5f;
		}

		this.fishingBonusMod *= 1 + (nearbyFluidBlocks * 0.0035f);
		this.fishingBonusMod += 0.25f * lureReduction;

		if (!EntityRetrievalUtil.getPlayers(level(), getBoundingBox().inflate(5)).isEmpty())
			this.fishingBonusMod *= 0.2f;
	}

	@Override
	protected void doBobbing(FluidState fluidState) {
		if (state == State.IN_FLUID) {
			BlockPos pos = blockPosition();
			float fluidHeight = fluidState.getHeight(level(), pos);
			Vec3 vector3d = this.getDeltaMovement();
			double fluidAdjustedHeight = this.getY() + vector3d.y - (double)pos.getY() - (double)fluidHeight + 0.1;

			if (Math.abs(fluidAdjustedHeight) < 0.01D)
				fluidAdjustedHeight += Math.signum(fluidAdjustedHeight) * 0.1D;

			setDeltaMovement(vector3d.x * 0.9D, vector3d.y - fluidAdjustedHeight * (double)this.random.nextFloat() * 0.05D, vector3d.z * 0.9D);
		}
	}

	@Override
	protected float fishingBonusModForBiome(Holder<Biome> biome) {
		float modifier = 1f;

		if (biome.is(AoATags.Biomes.LAVA_FISHING_BENEFICIAL))
			modifier *= 1.25f;

		if (biome.is(AoATags.Biomes.LAVA_FISHING_DETRIMENTAL))
			modifier *= 0.5f;

		return modifier;
	}
}
