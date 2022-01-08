package net.tslat.aoa3.common.registration.custom;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryManager;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.player.ability.*;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class AoAAbilities {
	private static IForgeRegistry<AoAAbility> REGISTRY = null;
	public static final DeferredRegister<AoAAbility> ABILITIES = DeferredRegister.create(AoAAbility.class, AdventOfAscension.MOD_ID);

	public static final RegistryObject<AoAAbility> ATTRIBUTE_MODIFICATION = register("attribute_modification", () -> new AoAAbility(AttributeModification::new, AttributeModification::new));
	public static final RegistryObject<AoAAbility> FLAT_XP_BOOST = register("flat_xp_boost", () -> new AoAAbility(FlatXpBoost::new, FlatXpBoost::new));
	public static final RegistryObject<AoAAbility> DUMMY_ABILITY = register("dummy_ability", () -> new AoAAbility(DummyAbility::new, DummyAbility::new, false));
	public static final RegistryObject<AoAAbility> LEVEL_RESTRICTION = register("level_restriction", () -> new AoAAbility(LevelRestriction::new, LevelRestriction::new, false));
	public static final RegistryObject<AoAAbility> FALL_DAMAGE_REDUCTION = register("fall_damage_reduction", () -> new AoAAbility(FallDamageReduction::new, FallDamageReduction::new));
	public static final RegistryObject<AoAAbility> JUMP_BOOST = register("jump_boost", () -> new AoAAbility(JumpBoost::new, JumpBoost::new));
	public static final RegistryObject<AoAAbility> DOUBLE_JUMP = register("double_jump", () -> new AoAAbility(DoubleJump::new, DoubleJump::new));
	public static final RegistryObject<AoAAbility> ONE_SHOT_DAMAGE_LIMITER = register("one_shot_damage_limiter", () -> new AoAAbility(OneShotDamageLimiter::new, OneShotDamageLimiter::new));
	public static final RegistryObject<AoAAbility> POTION_DURATION_REDUCER = register("potion_duration_reducer", () -> new AoAAbility(PotionDurationReducer::new, PotionDurationReducer::new));
	public static final RegistryObject<AoAAbility> BOW_DAMAGE_INCREASE = register("bow_damage_increase", () -> new AoAAbility(BowDamageIncrease::new, BowDamageIncrease::new));
	public static final RegistryObject<AoAAbility> INNERVATION_MOB_LURE = register("innervation_mob_lure", () -> new AoAAbility(InnervationMobLure::new, InnervationMobLure::new));
	public static final RegistryObject<AoAAbility> ON_KILL_DAMAGE_BOOST = register("on_kill_damage_boost", () -> new AoAAbility(OnKillDamageBoost::new, OnKillDamageBoost::new));
	public static final RegistryObject<AoAAbility> VULCANE_DAMAGE_INCREASE = register("vulcane_damage_increase", () -> new AoAAbility(VulcaneDamageIncrease::new, VulcaneDamageIncrease::new));
	public static final RegistryObject<AoAAbility> DOUBLE_DROPS_CHANCE = register("double_drops_chance", () -> new AoAAbility(DoubleDropsChance::new, DoubleDropsChance::new));
	public static final RegistryObject<AoAAbility> INNERVATION_DODGE = register("innervation_dodge", () -> new AoAAbility(InnervationDodge::new, InnervationDodge::new));
	public static final RegistryObject<AoAAbility> STUN_STRIKE = register("stun_strike", () -> new AoAAbility(StunStrike::new, StunStrike::new));
	public static final RegistryObject<AoAAbility> HAULING_GLOWING_FISH = register("fishing_hostile_targeting_immunity", () -> new AoAAbility(FishingHostileTargetingImmunity::new, FishingHostileTargetingImmunity::new));
	public static final RegistryObject<AoAAbility> FISHING_XP_BOOST = register("fishing_xp_boost", () -> new AoAAbility(FishingXpBoost::new, FishingXpBoost::new));
	public static final RegistryObject<AoAAbility> HAULING_ROD_PULL_STRENGTH = register("hauling_rod_pull_strength", () -> new AoAAbility(HaulingRodPullStrengthModifier::new, HaulingRodPullStrengthModifier::new));
	public static final RegistryObject<AoAAbility> HAULING_ROD_PULL_DAMAGE = register("hauling_rod_pull_damage", () -> new AoAAbility(HaulingRodPullDamage::new, HaulingRodPullDamage::new));
	public static final RegistryObject<AoAAbility> FISHING_TRAP_SPAWN = register("fishing_trap_spawn", () -> new AoAAbility(FishingTrapSpawn::new, FishingTrapSpawn::new));
	public static final RegistryObject<AoAAbility> BLOCK_CONVERSION = register("block_conversion", () -> new AoAAbility(BlockConversion::new, BlockConversion::new));
	public static final RegistryObject<AoAAbility> HOE_AREA_HARVEST = register("hoe_area_harvest", () -> new AoAAbility(HoeAreaHarvest::new, HoeAreaHarvest::new));

	private static RegistryObject<AoAAbility> register(String id, Supplier<AoAAbility> ability) {
		return ABILITIES.register(id, ability);
	}

	@Nullable
	public static AoAAbility getAbility(ResourceLocation id) {
		return getRegistry().getValue(id);
	}

	public static IForgeRegistry<AoAAbility> getRegistry() {
		if (REGISTRY == null)
			REGISTRY = RegistryManager.ACTIVE.getRegistry(AoAAbility.class);

		return REGISTRY;
	}
}
