package net.tslat.aoa3.common.misc;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.level.block.ComposterBlock;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.item.AoAItems;

public class NativePatching {
	public static void doEarlyPatches() {
		((RangedAttribute)Attributes.MAX_HEALTH).maxValue = Double.MAX_VALUE;
		((RangedAttribute)Attributes.ATTACK_KNOCKBACK).maxValue = Double.MAX_VALUE;
	}

	public static void postInit() {
		patchInComposterBlocks();
	}

	private static void patchInComposterBlocks() {
		ComposterBlock.add(0.3f, AoABlocks.ACHONY_LEAVES.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.BLOOD_LEAVES.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.BLUE_CELEVUS_LEAVES.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.GREEN_CELEVUS_LEAVES.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.PURPLE_CELEVUS_LEAVES.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.RED_CELEVUS_LEAVES.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.WHITE_CELEVUS_LEAVES.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.YELLOW_CELEVUS_LEAVES.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.CHURRY_LEAVES.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.CREEP_LEAVES.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.DAWN_LEAVES.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.HAUNTED_LEAVES.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.HAUNTED_EYES_LEAVES.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.BLUE_HAVEN_LEAVES.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.PINK_HAVEN_LEAVES.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.PURPLE_HAVEN_LEAVES.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.RED_HAVEN_LEAVES.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.TURQUOISE_HAVEN_LEAVES.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.YELLOW_HAVEN_LEAVES.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.IRODUST_LEAVES.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.IROGOLD_LEAVES.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.LELYETIAN_LEAVES.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.LUCALUS_LEAVES.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.LUNICIA_LEAVES.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.LUNOSSO_LEAVES.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.RUNIC_LEAVES.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.SHADOW_LEAVES.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.SHADOWBLOOD_LEAVES.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.SHYRE_LEAVES.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.BRIGHT_SHYRE_LEAVES.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.STRANGLEWOOD_LEAVES.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.VEIN_LEAVES.get().asItem());

		ComposterBlock.add(0.3f, AoABlocks.ACHONY_SAPLING.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.BLOODTWISTER_SAPLING.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.BLUE_CELEVUS_SAPLING.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.BLUE_HAVEN_SAPLING.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.BRIGHT_SHYRE_SAPLING.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.CHURRY_SAPLING.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.CREEP_SAPLING.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.DAWNWOOD_SAPLING.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.EYEBUSH_SAPLING.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.EYE_HANGER_SAPLING.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.GREEN_CELEVUS_SAPLING.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.HAUNTED_SAPLING.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.IRODUST_SAPLING.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.IROGOLD_SAPLING.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.LUCALUS_SAPLING.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.LUNICIA_SAPLING.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.LUNOSSO_SAPLING.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.PINK_HAVEN_SAPLING.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.PURPLE_CELEVUS_SAPLING.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.PURPLE_HAVEN_SAPLING.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.RED_CELEVUS_SAPLING.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.RED_HAVEN_SAPLING.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.RUNIC_SAPLING.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.SHADOW_SAPLING.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.SHYRE_SAPLING.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.STRANGLEWOOD_SAPLING.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.TURQUOISE_HAVEN_SAPLING.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.YELLOW_CELEVUS_SAPLING.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.YELLOW_HAVEN_SAPLING.get().asItem());

		ComposterBlock.add(0.3f, AoAItems.BUBBLE_BERRY_SEEDS.get());
		ComposterBlock.add(0.3f, AoAItems.CHILLI_SEEDS.get());
		ComposterBlock.add(0.3f, AoAItems.FLORACLE_SEEDS.get());
		ComposterBlock.add(0.3f, AoAItems.GOLDICAP_SEEDS.get());
		ComposterBlock.add(0.3f, AoAItems.HEART_FRUIT_SEEDS.get());
		ComposterBlock.add(0.3f, AoAItems.LUNACRIKE_SEEDS.get());
		ComposterBlock.add(0.3f, AoAItems.LUNALON_SEEDS.get());
		ComposterBlock.add(0.3f, AoAItems.LUNA_GLOBE_SEEDS.get());
		ComposterBlock.add(0.3f, AoAItems.ROSIDON_SEEDS.get());
		ComposterBlock.add(0.3f, AoAItems.TEA_SEEDS.get());
		ComposterBlock.add(0.3f, AoAItems.THORNY_PLANT_SEEDS.get());
		ComposterBlock.add(0.3f, AoAItems.TRILLIAD_SEEDS.get());
		
		ComposterBlock.add(0.3f, AoABlocks.BLUE_CANDY_GRASS.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.BUREAL_STOCKS.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.CREEP_BUSH.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.CREEP_FLOWERS.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.DAWN_GRASS.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.DEAD_GRASS.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.DEEP_BLOOMS.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.DEEP_GRASS.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.GREEN_WATERWEEDS.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.HAVEN_GRASS_PLANT.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.IROTOPS.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.IRO_GRASS.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.LELYETIAN_WEEDS.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.LELYETIAN_WEEDS_DOWN.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.LUCON_GRASS.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.LUNALIP.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.LUNTAR.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.LURCHIANS.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.MYSTIC_BUSH.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.MYSTIC_FERNS.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.PINK_CANDY_GRASS.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.RAINBOW_GRASS.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.RED_CRYSTAL_PLANT.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.RED_WATERWEEDS.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.RUNE_BULBS.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.TUBEICLES.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.WHITE_WATERWEEDS.get().asItem());
		ComposterBlock.add(0.3f, AoABlocks.YELLOW_WATERWEEDS.get().asItem());
		
		ComposterBlock.add(0.5f, AoABlocks.ANCIENT_VINES.get().asItem());
		ComposterBlock.add(0.5f, AoABlocks.ANCIENT_VINES_CAP.get().asItem());
		ComposterBlock.add(0.5f, AoABlocks.CREEP_VINES.get().asItem());

		ComposterBlock.add(0.5f, AoABlocks.BLOOD_PINE_STEM.get().asItem());
		ComposterBlock.add(0.5f, AoABlocks.BLOOD_PINE.get().asItem());
		ComposterBlock.add(0.5f, AoABlocks.BLOOD_SPIKES.get().asItem());
		ComposterBlock.add(0.5f, AoABlocks.BLOOD_STRANDS.get().asItem());
		ComposterBlock.add(0.5f, AoABlocks.BULB_STOCK.get().asItem());
		ComposterBlock.add(0.5f, AoABlocks.BULB_STOCK_CAP.get().asItem());
		ComposterBlock.add(0.5f, AoABlocks.CELEBULBS_STEM.get().asItem());
		ComposterBlock.add(0.5f, AoABlocks.GREEN_CELEBULBS.get().asItem());
		ComposterBlock.add(0.5f, AoABlocks.YELLOW_CELEBULBS.get().asItem());
		ComposterBlock.add(0.5f, AoABlocks.CORAL_CAGE.get().asItem());
		ComposterBlock.add(0.5f, AoABlocks.DAWNWOOD_BARS.get().asItem());
		ComposterBlock.add(0.5f, AoABlocks.EYE_SHRUB_STEM.get().asItem());
		ComposterBlock.add(0.5f, AoABlocks.EYE_SHRUB.get().asItem());
		ComposterBlock.add(0.5f, AoABlocks.GARDEN_GRASS.get().asItem());
		ComposterBlock.add(0.5f, AoABlocks.BLUE_HAVENDALES_STEM.get().asItem());
		ComposterBlock.add(0.5f, AoABlocks.BLUE_HAVENDALES.get().asItem());
		ComposterBlock.add(0.5f, AoABlocks.PINK_HAVENDALES_STEM.get().asItem());
		ComposterBlock.add(0.5f, AoABlocks.PINK_HAVENDALES.get().asItem());
		ComposterBlock.add(0.5f, AoABlocks.YELLOW_HAVENDALES_STEM.get().asItem());
		ComposterBlock.add(0.5f, AoABlocks.YELLOW_HAVENDALES.get().asItem());
		ComposterBlock.add(0.5f, AoABlocks.LELYETIAN_STEM.get().asItem());
		ComposterBlock.add(0.5f, AoABlocks.LELYETIAN_STEM_CAP.get().asItem());
		ComposterBlock.add(0.5f, AoABlocks.LELYETIAN_STEM_CAP_DOWN.get().asItem());
		ComposterBlock.add(0.5f, AoABlocks.LELYETIAN_WIGGLER.get().asItem());
		ComposterBlock.add(0.5f, AoABlocks.LELYETIAN_WIGGLER_BOTTOM.get().asItem());
		ComposterBlock.add(0.5f, AoABlocks.LELYETIAN_WIGGLER_TOP.get().asItem());
		ComposterBlock.add(0.5f, AoABlocks.SHYRE_STOCK.get().asItem());
		ComposterBlock.add(0.5f, AoABlocks.SHYRE_CAP.get().asItem());
		ComposterBlock.add(0.5f, AoABlocks.SHYRE_CAP_DOWN.get().asItem());
		ComposterBlock.add(0.5f, AoABlocks.VOX_FUNGI_STEM.get().asItem());
		ComposterBlock.add(0.5f, AoABlocks.VOX_FUNGI.get().asItem());
		ComposterBlock.add(0.5f, AoABlocks.VOX_TENTACLES_STEM.get().asItem());
		ComposterBlock.add(0.5f, AoABlocks.VOX_TENTACLES.get().asItem());
		
		ComposterBlock.add(0.65f, AoABlocks.ARCBULB.get().asItem());
		ComposterBlock.add(0.65f, AoABlocks.ARCFLOWER.get().asItem());
		ComposterBlock.add(0.65f, AoABlocks.BLUE_CELEVIANS.get().asItem());
		ComposterBlock.add(0.65f, AoABlocks.BLUE_DAYLOOMS.get().asItem());
		ComposterBlock.add(0.65f, AoABlocks.BLUE_GLOWSHROOM.get().asItem());
		ComposterBlock.add(0.65f, AoABlocks.BLUE_OCEALITES.get().asItem());
		ComposterBlock.add(0.65f, AoABlocks.DAILEERS.get().asItem());
		ComposterBlock.add(0.65f, AoABlocks.DAWN_BUSH.get().asItem());
		ComposterBlock.add(0.65f, AoABlocks.DAWN_FLOWER.get().asItem());
		ComposterBlock.add(0.65f, AoABlocks.GREEN_GLOWSHROOM.get().asItem());
		ComposterBlock.add(0.65f, AoABlocks.HAUNTED_FLOWER.get().asItem());
		ComposterBlock.add(0.65f, AoABlocks.HORIZON_DAISIES.get().asItem());
		ComposterBlock.add(0.65f, AoABlocks.LYLIPS.get().asItem());
		ComposterBlock.add(0.65f, AoABlocks.MAGIAS.get().asItem());
		ComposterBlock.add(0.65f, AoABlocks.ORANGE_GLOWSHROOM.get().asItem());
		ComposterBlock.add(0.65f, AoABlocks.PINK_DAYLOOMS.get().asItem());
		ComposterBlock.add(0.65f, AoABlocks.PURPLE_CELEVIANS.get().asItem());
		ComposterBlock.add(0.65f, AoABlocks.PURPLE_GLOWSHROOM.get().asItem());
		ComposterBlock.add(0.65f, AoABlocks.RED_CELEVIANS.get().asItem());
		ComposterBlock.add(0.65f, AoABlocks.RUNIC_BUSH.get().asItem());
		ComposterBlock.add(0.65f, AoABlocks.SHYRE_WEED.get().asItem());
		ComposterBlock.add(0.65f, AoABlocks.WHITE_CELEVIANS.get().asItem());
		ComposterBlock.add(0.65f, AoABlocks.YELLOW_DAYLOOMS.get().asItem());
		ComposterBlock.add(0.65f, AoABlocks.YELLOW_GLOWSHROOM.get().asItem());
		ComposterBlock.add(0.65f, AoABlocks.TANGLE_THORNS.get().asItem());
		ComposterBlock.add(0.65f, AoABlocks.TRILLIAD_BLOOM.get().asItem());

		ComposterBlock.add(0.65f, AoAItems.TRILLIAD_LEAVES.get());
		ComposterBlock.add(0.65f, AoAItems.ROSIDONS.get());
		ComposterBlock.add(0.65f, AoAItems.LUNACRIKE.get());
		ComposterBlock.add(0.65f, AoAItems.LUNA_GLOBE.get());
		ComposterBlock.add(0.65f, AoAItems.LUNALONS.get());
		ComposterBlock.add(0.65f, AoAItems.MAGIC_MARANG.get());
		ComposterBlock.add(0.65f, AoAItems.MYSTIC_SHROOMS.get());
		ComposterBlock.add(0.65f, AoAItems.GOLDICAP_PETALS.get());
		ComposterBlock.add(0.65f, AoAItems.HEART_FRUIT.get());
		ComposterBlock.add(0.65f, AoAItems.CHILLI.get());
		ComposterBlock.add(0.65f, AoAItems.BUBBLE_BERRIES.get());
		
		ComposterBlock.add(0.85f, AoABlocks.BLACK_MUSHROOM_BLOCK.get().asItem());
		ComposterBlock.add(0.85f, AoABlocks.BLUE_MUSHROOM_BLOCK.get().asItem());
		ComposterBlock.add(0.85f, AoABlocks.GREEN_MUSHROOM_BLOCK.get().asItem());
		ComposterBlock.add(0.85f, AoABlocks.ORANGE_MUSHROOM_BLOCK.get().asItem());
		ComposterBlock.add(0.85f, AoABlocks.PURPLE_MUSHROOM_BLOCK.get().asItem());
		ComposterBlock.add(0.85f, AoABlocks.YELLOW_MUSHROOM_BLOCK.get().asItem());
	}
}
