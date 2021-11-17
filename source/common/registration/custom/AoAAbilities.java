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

	public static final RegistryObject<AoAAbility> PASSIVE_ATTRIBUTE_MODIFICATION = register("passive_attribute_modification", () -> new AoAAbility(PassiveAttributeModification::new, PassiveAttributeModification::new));
	public static final RegistryObject<AoAAbility> SCALING_ATTRIBUTE_MODIFICATION = register("scaling_attribute_modification", () -> new AoAAbility(ScalingAttributeModification::new, ScalingAttributeModification::new));
	public static final RegistryObject<AoAAbility> FLAT_XP_BOOST = register("flat_xp_boost", () -> new AoAAbility(FlatXpBoost::new, FlatXpBoost::new));
	public static final RegistryObject<AoAAbility> DUMMY_ABILITY = register("dummy_ability", () -> new AoAAbility(DummyAbility::new, DummyAbility::new));
	public static final RegistryObject<AoAAbility> SCALING_FALL_DAMAGE_REDUCTION = register("scaling_fall_damage_reduction", () -> new AoAAbility(ScalingFallDamageReduction::new, ScalingFallDamageReduction::new));
	public static final RegistryObject<AoAAbility> SCALING_JUMP_BOOST = register("scaling_jump_boost", () -> new AoAAbility(ScalingJumpBoost::new, ScalingJumpBoost::new));
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
