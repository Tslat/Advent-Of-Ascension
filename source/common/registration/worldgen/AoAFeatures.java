package net.tslat.aoa3.common.registration.worldgen;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.content.world.gen.feature.config.StructureFeatureConfig;
import net.tslat.aoa3.content.world.gen.feature.lakes.BigLakeFeature;
import net.tslat.aoa3.content.world.gen.feature.misc.BetterBlockBlobFeature;
import net.tslat.aoa3.content.world.gen.feature.misc.FluidChuteFeature;
import net.tslat.aoa3.content.world.gen.feature.misc.StructurePieceFeature;
import net.tslat.aoa3.content.world.gen.feature.ore.BrushableBlockOreFeature;

import java.util.function.Supplier;

public final class AoAFeatures {
	public static final DeferredHolder<Feature<?>, BigLakeFeature> BIG_LAKE = register("big_lake", () -> new BigLakeFeature(BigLakeFeature.Configuration.CODEC));
	public static final DeferredHolder<Feature<?>, FluidChuteFeature> FLUID_CHUTE = register("fluid_chute", () -> new FluidChuteFeature(FluidChuteFeature.Configuration.CODEC));
	public static final DeferredHolder<Feature<?>, BrushableBlockOreFeature> BRUSHABLE_BLOCK_ORE = register("brushable_block_ore", () -> new BrushableBlockOreFeature(BrushableBlockOreFeature.Configuration.CODEC));
	public static final DeferredHolder<Feature<?>, StructurePieceFeature> STRUCTURE_PIECE = register("structure_piece", () -> new StructurePieceFeature(StructureFeatureConfig.CODEC));
	public static final DeferredHolder<Feature<?>, BetterBlockBlobFeature> BETTER_BLOCK_BLOB = register("better_block_blob", () -> new BetterBlockBlobFeature(BetterBlockBlobFeature.Configuration.CODEC));

/*
	public static final DeferredHolder<Feature<?>, AchonyTreeFeature> ACHONY_TREE = register("achony_tree", () -> new AchonyTreeFeature(BlockStatePlacementConfig.CODEC, (DeferredHolder)AoABlocks.ACHONY_SAPLING.plant));
	public static final DeferredHolder<Feature<?>, BloodtwisterTreeFeature> BLOODTWISTER_TREE = register("bloodtwister_tree", () -> new BloodtwisterTreeFeature(BlockStatePlacementConfig.CODEC, (DeferredHolder)AoABlocks.BLOODTWISTER_SAPLING.plant));
	public static final DeferredHolder<Feature<?>, BlueCelevusTreeFeature> BLUE_CELEVUS_TREE = register("blue_celevus_tree", () -> new BlueCelevusTreeFeature(BlockStatePlacementConfig.CODEC, (DeferredHolder)AoABlocks.BLUE_CELEVUS_SAPLING.plant));
	public static final DeferredHolder<Feature<?>, BlueHavenTreeFeature> BLUE_HAVEN_TREE = register("blue_haven_tree", () -> new BlueHavenTreeFeature(BlockStatePlacementConfig.CODEC, (DeferredHolder)AoABlocks.BLUE_HAVEN_SAPLING.plant));
	public static final DeferredHolder<Feature<?>, BrightShyreTreeFeature> BRIGHT_SHYRE_TREE = register("bright_shyre_tree", () -> new BrightShyreTreeFeature(BlockStatePlacementConfig.CODEC, (DeferredHolder)AoABlocks.BRIGHT_SHYRE_SAPLING.plant));
	public static final DeferredHolder<Feature<?>, ChurryTreeFeature> CHURRY_TREE = register("churry_tree", () -> new ChurryTreeFeature(BlockStatePlacementConfig.CODEC, (DeferredHolder)AoABlocks.CHURRY_SAPLING.plant));
	public static final DeferredHolder<Feature<?>, CreepTreeFeature> CREEP_TREE = register("creep_tree", () -> new CreepTreeFeature(BlockStatePlacementConfig.CODEC, (DeferredHolder)AoABlocks.CREEP_SAPLING.plant));
	public static final DeferredHolder<Feature<?>, DawnwoodTreeFeature> DAWNWOOD_TREE = register("dawnwood_tree", () -> new DawnwoodTreeFeature(BlockStatePlacementConfig.CODEC, (DeferredHolder)AoABlocks.DAWNWOOD_SAPLING.plant));
	public static final DeferredHolder<Feature<?>, EyebushTreeFeature> EYEBUSH_TREE = register("eyebush_tree", () -> new EyebushTreeFeature(BlockStatePlacementConfig.CODEC, (DeferredHolder)AoABlocks.EYEBUSH_SAPLING.plant));
	public static final DeferredHolder<Feature<?>, EyeHangerTreeFeature> EYE_HANGER_TREE = register("eye_hanger_tree", () -> new EyeHangerTreeFeature(BlockStatePlacementConfig.CODEC, (DeferredHolder)AoABlocks.GREEN_CELEVUS_SAPLING.plant));
	public static final DeferredHolder<Feature<?>, GreenCelevusTreeFeature> GREEN_CELEVUS_TREE = register("green_celevus_tree", () -> new GreenCelevusTreeFeature(BlockStatePlacementConfig.CODEC, (DeferredHolder)AoABlocks.GREEN_CELEVUS_SAPLING.plant));
	public static final DeferredHolder<Feature<?>, HauntedTreeFeature> HAUNTED_TREE = register("haunted_tree", () -> new HauntedTreeFeature(BlockStatePlacementConfig.CODEC, (DeferredHolder)AoABlocks.HAUNTED_SAPLING.plant));
	public static final DeferredHolder<Feature<?>, IrodustTreeFeature> IRODUST_TREE = register("irodust_tree", () -> new IrodustTreeFeature(BlockStatePlacementConfig.CODEC, (DeferredHolder)AoABlocks.IRODUST_SAPLING.plant));
	public static final DeferredHolder<Feature<?>, IrogoldTreeFeature> IROGOLD_TREE = register("irogold_tree", () -> new IrogoldTreeFeature(BlockStatePlacementConfig.CODEC, (DeferredHolder)AoABlocks.IROGOLD_SAPLING.plant));
	public static final DeferredHolder<Feature<?>, LuniciaTreeFeature> LUNICIA_TREE = register("lunicia_tree", () -> new LuniciaTreeFeature(BlockStatePlacementConfig.CODEC, (DeferredHolder)AoABlocks.LUNICIA_SAPLING.plant));
	public static final DeferredHolder<Feature<?>, LunossoTreeFeature> LUNOSSO_TREE = register("lunosso_tree", () -> new LunossoTreeFeature(BlockStatePlacementConfig.CODEC, (DeferredHolder)AoABlocks.LUNOSSO_SAPLING.plant));
	public static final DeferredHolder<Feature<?>, NormalShyreTreeFeature> NORMAL_SHYRE_TREE = register("normal_shyre_tree", () -> new NormalShyreTreeFeature(BlockStatePlacementConfig.CODEC, (DeferredHolder)AoABlocks.SHYRE_SAPLING.plant));
	public static final DeferredHolder<Feature<?>, PinkHavenTreeFeature> PINK_HAVEN_TREE = register("pink_haven_tree", () -> new PinkHavenTreeFeature(BlockStatePlacementConfig.CODEC, (DeferredHolder)AoABlocks.PINK_HAVEN_SAPLING.plant));
	public static final DeferredHolder<Feature<?>, PurpleCelevusTreeFeature> PURPLE_CELEVUS_TREE = register("purple_celevus_tree", () -> new PurpleCelevusTreeFeature(BlockStatePlacementConfig.CODEC, (DeferredHolder)AoABlocks.PURPLE_CELEVUS_SAPLING.plant));
	public static final DeferredHolder<Feature<?>, PurpleHavenTreeFeature> PURPLE_HAVEN_TREE = register("purple_haven_tree", () -> new PurpleHavenTreeFeature(BlockStatePlacementConfig.CODEC, (DeferredHolder)AoABlocks.PURPLE_HAVEN_SAPLING.plant));
	public static final DeferredHolder<Feature<?>, RedCelevusTreeFeature> RED_CELEVUS_TREE = register("red_celevus_tree", () -> new RedCelevusTreeFeature(BlockStatePlacementConfig.CODEC, (DeferredHolder)AoABlocks.RED_CELEVUS_SAPLING.plant));
	public static final DeferredHolder<Feature<?>, RedHavenTreeFeature> RED_HAVEN_TREE = register("red_haven_tree", () -> new RedHavenTreeFeature(BlockStatePlacementConfig.CODEC, (DeferredHolder)AoABlocks.RED_HAVEN_SAPLING.plant));
	public static final DeferredHolder<Feature<?>, RunicTreeFeature> RUNIC_TREE = register("runic_tree", () -> new RunicTreeFeature(BlockStatePlacementConfig.CODEC, (DeferredHolder)AoABlocks.RUNIC_SAPLING.plant));
	public static final DeferredHolder<Feature<?>, ShadowTreeFeature> SHADOW_TREE = register("shadow_tree", () -> new ShadowTreeFeature(BlockStatePlacementConfig.CODEC, (DeferredHolder)AoABlocks.SHADOW_SAPLING.plant));
	public static final DeferredHolder<Feature<?>, TurquoiseHavenTreeFeature> TURQUOISE_HAVEN_TREE = register("turquoise_haven_tree", () -> new TurquoiseHavenTreeFeature(BlockStatePlacementConfig.CODEC, (DeferredHolder)AoABlocks.TURQUOISE_HAVEN_SAPLING.plant));
	public static final DeferredHolder<Feature<?>, YellowCelevusTreeFeature> YELLOW_CELEVUS_TREE = register("yellow_celevus_tree", () -> new YellowCelevusTreeFeature(BlockStatePlacementConfig.CODEC, (DeferredHolder)AoABlocks.YELLOW_CELEVUS_SAPLING.plant));
	public static final DeferredHolder<Feature<?>, YellowHavenTreeFeature> YELLOW_HAVEN_TREE = register("yellow_haven_tree", () -> new YellowHavenTreeFeature(BlockStatePlacementConfig.CODEC, (DeferredHolder)AoABlocks.YELLOW_HAVEN_SAPLING.plant));
*/

	public static void init() {}

	private static <C extends FeatureConfiguration, F extends Feature<C>> DeferredHolder<Feature<?>, F> register(String id, Supplier<F> feature) {
		return AoARegistries.FEATURES.register(id, feature);
	}
}
