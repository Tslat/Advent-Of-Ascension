package net.tslat.aoa3.content.world.gen.placementmodifier;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementFilter;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.tslat.aoa3.common.registration.worldgen.AoAPlacementModifiers;

import java.util.Random;

public class PercentChance extends PlacementFilter {
	public static final Codec<PercentChance> CODEC = ExtraCodecs.POSITIVE_FLOAT.fieldOf("chance").xmap(PercentChance::new, instance -> instance.chance).codec();

	private final float chance;

	private PercentChance(float chance) {
		this.chance = chance;
	}

	public static PercentChance of(float chance) {
		return new PercentChance(chance);
	}

	@Override
	protected boolean shouldPlace(PlacementContext context, Random rand, BlockPos pos) {
		return rand.nextFloat() < this.chance;
	}

	@Override
	public PlacementModifierType<?> type() {
		return AoAPlacementModifiers.PERCENT_CHANCE;
	}
}
