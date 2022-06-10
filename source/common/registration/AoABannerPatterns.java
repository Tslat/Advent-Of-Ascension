package net.tslat.aoa3.common.registration;

import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.advent.AdventOfAscension;

public final class AoABannerPatterns {
	public static void init() {}

	public static final BannerPatternHolder COMPASS_RUNE = register("compass_rune", "aoacomr");
	public static final BannerPatternHolder DISTORTION_RUNE = register("distortion_rune", "aoadisr");
	public static final BannerPatternHolder ENERGY_RUNE = register("energy_rune", "aoaener");
	public static final BannerPatternHolder FIRE_RUNE = register("fire_rune", "aoafirr");
	public static final BannerPatternHolder KINETIC_RUNE = register("kinetic_rune", "aoakinr");
	public static final BannerPatternHolder LIFE_RUNE = register("life_rune", "aoalifr");
	public static final BannerPatternHolder LUNAR_RUNE = register("lunar_rune", "aoalunr");
	public static final BannerPatternHolder POISON_RUNE = register("poison_rune", "aoapoir");
	public static final BannerPatternHolder POWER_RUNE = register("power_rune", "aoapowr");
	public static final BannerPatternHolder STORM_RUNE = register("storm_rune", "aoastor");
	public static final BannerPatternHolder STRIKE_RUNE = register("strike_rune", "aoastrr");
	public static final BannerPatternHolder WATER_RUNE = register("water_rune", "aoawatr");
	public static final BannerPatternHolder WIND_RUNE = register("wind_rune", "aoawinr");
	public static final BannerPatternHolder WITHER_RUNE = register("wither_rune", "aoawithr");

	public static BannerPatternHolder register(String id, String pattern) {
		return new BannerPatternHolder(AoARegistries.BANNER_PATTERNS.register(id, () -> new BannerPattern(pattern)), TagKey.create(Registry.BANNER_PATTERN_REGISTRY, AdventOfAscension.id("pattern_item/" + id)));
	}

	public record BannerPatternHolder(RegistryObject<BannerPattern> pattern, TagKey<BannerPattern> tag) {}
}
