package net.tslat.aoa3.common.registration.custom;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.player.ability.*;
import net.tslat.aoa3.player.ability.dexterity.DoubleJump;
import net.tslat.aoa3.player.ability.dexterity.FallDamageReduction;
import net.tslat.aoa3.player.ability.dexterity.JumpBoost;
import net.tslat.aoa3.player.ability.extraction.*;
import net.tslat.aoa3.player.ability.farming.*;
import net.tslat.aoa3.player.ability.generic.*;
import net.tslat.aoa3.player.ability.hauling.*;
import net.tslat.aoa3.player.ability.imbuing.EnchantContainerContents;
import net.tslat.aoa3.player.ability.imbuing.EnchantEntityEquipment;
import net.tslat.aoa3.player.ability.imbuing.GrindstoneCursesRemoval;
import net.tslat.aoa3.player.ability.imbuing.ImbuingLevelRestriction;
import net.tslat.aoa3.player.ability.innervation.*;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class AoAAbilities {
	public static void init() {}

	public static final DeferredHolder<AoAAbility, AoAAbility> ATTRIBUTE_MODIFICATION = register("attribute_modification", () -> new AoAAbility(AttributeModification::new, AttributeModification::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> FLAT_XP_BOOST = register("flat_xp_boost", () -> new AoAAbility(FlatXpBoost::new, FlatXpBoost::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> DUMMY_ABILITY = register("dummy_ability", () -> new AoAAbility(DummyAbility::new, DummyAbility::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> LEVEL_RESTRICTION = register("level_restriction", () -> new AoAAbility(LevelRestriction::new, LevelRestriction::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> FALL_DAMAGE_REDUCTION = register("fall_damage_reduction", () -> new AoAAbility(FallDamageReduction::new, FallDamageReduction::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> JUMP_BOOST = register("jump_boost", () -> new AoAAbility(JumpBoost::new, JumpBoost::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> DOUBLE_JUMP = register("double_jump", () -> new AoAAbility(DoubleJump::new, DoubleJump::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> ONE_SHOT_DAMAGE_LIMITER = register("one_shot_damage_limiter", () -> new AoAAbility(OneShotDamageLimiter::new, OneShotDamageLimiter::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> POTION_DURATION_REDUCER = register("potion_duration_reducer", () -> new AoAAbility(PotionDurationReducer::new, PotionDurationReducer::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> BOW_DAMAGE_INCREASE = register("bow_damage_increase", () -> new AoAAbility(BowDamageIncrease::new, BowDamageIncrease::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> INNERVATION_MOB_LURE = register("innervation_mob_lure", () -> new AoAAbility(InnervationMobLure::new, InnervationMobLure::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> ON_KILL_DAMAGE_BOOST = register("on_kill_damage_boost", () -> new AoAAbility(OnKillDamageBoost::new, OnKillDamageBoost::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> VULCANE_DAMAGE_INCREASE = register("vulcane_damage_increase", () -> new AoAAbility(VulcaneDamageIncrease::new, VulcaneDamageIncrease::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> DOUBLE_DROPS_CHANCE = register("double_drops_chance", () -> new AoAAbility(DoubleDropsChance::new, DoubleDropsChance::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> INNERVATION_DODGE = register("innervation_dodge", () -> new AoAAbility(InnervationDodge::new, InnervationDodge::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> STUN_STRIKE = register("stun_strike", () -> new AoAAbility(StunStrike::new, StunStrike::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> HAULING_GLOWING_FISH = register("hauling_glowing_fish", () -> new AoAAbility(HaulingGlowingFish::new, HaulingGlowingFish::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> FISHING_XP_BOOST = register("fishing_xp_boost", () -> new AoAAbility(FishingXpBoost::new, FishingXpBoost::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> HAULING_ROD_PULL_STRENGTH = register("hauling_rod_pull_strength", () -> new AoAAbility(HaulingRodPullStrengthModifier::new, HaulingRodPullStrengthModifier::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> HAULING_ROD_PULL_DAMAGE = register("hauling_rod_pull_damage", () -> new AoAAbility(HaulingRodPullDamage::new, HaulingRodPullDamage::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> FISHING_TRAP_SPAWN = register("fishing_trap_spawn", () -> new AoAAbility(FishingTrapSpawn::new, FishingTrapSpawn::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> BLOCK_CONVERSION = register("block_conversion", () -> new AoAAbility(BlockConversion::new, BlockConversion::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> HOE_AREA_HARVEST = register("hoe_area_harvest", () -> new AoAAbility(HoeAreaHarvest::new, HoeAreaHarvest::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> HARVEST_REPLANT = register("harvest_replant", () -> new AoAAbility(HarvestReplant::new, HarvestReplant::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> FERTILISE_FARMLAND = register("fertilise_farmland", () -> new AoAAbility(FertiliseFarmland::new, FertiliseFarmland::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> DRYAD_SPRITE_SPAWN = register("dryad_sprite_spawn", () -> new AoAAbility(DryadSpriteSpawn::new, DryadSpriteSpawn::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> BREEDING_BONUS = register("breeding_bonus", () -> new AoAAbility(BreedingBonus::new, BreedingBonus::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> AUTO_HARVESTING_TRASH = register("auto_harvesting_trash", () -> new AoAAbility(AutoHarvestingTrash::new, AutoHarvestingTrash::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> HARD_BLOCK_SPEED_INCREASE = register("hard_block_speed_increase", () -> new AoAAbility(HardBlockSpeedIncrease::new, HardBlockSpeedIncrease::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> HARVEST_SPEED_BOOST = register("harvest_speed_boost", () -> new AoAAbility(HarvestSpeedBoost::new, HarvestSpeedBoost::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> BONUS_SMELT_RESULT = register("bonus_smelt_result", () -> new AoAAbility(BonusSmeltResult::new, BonusSmeltResult::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> BONUS_MINING_RESULT = register("bonus_mining_result", () -> new AoAAbility(BonusMiningResult::new, BonusMiningResult::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> AUTO_ENCHANT_CRAFTING = register("auto_enchant_crafting", () -> new AoAAbility(AutoEnchantCrafting::new, AutoEnchantCrafting::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> RARE_TABLE_HARVESTING_CHANCE = register("rare_table_harvesting_chance", () -> new AoAAbility(RareTableHarvestingChance::new, RareTableHarvestingChance::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> BONUS_CRAFTING_OUTPUT = register("bonus_crafting_output", () -> new AoAAbility(BonusCraftingOutput::new, BonusCraftingOutput::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> ENCHANT_ENTITY_EQUIPMENT = register("enchant_entity_equipment", () -> new AoAAbility(EnchantEntityEquipment::new, EnchantEntityEquipment::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> ENCHANT_CONTAINER_CONTENTS = register("enchant_container_contents", () -> new AoAAbility(EnchantContainerContents::new, EnchantContainerContents::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> GRINDSTONE_CURSES_REMOVAL = register("grindstone_curses_removal", () -> new AoAAbility(GrindstoneCursesRemoval::new, GrindstoneCursesRemoval::new));
	public static final DeferredHolder<AoAAbility, AoAAbility> IMBUING_LEVEL_RESTRICTION = register("imbuing_level_restriction", () -> new AoAAbility(ImbuingLevelRestriction::new, ImbuingLevelRestriction::new));

	private static DeferredHolder<AoAAbility, AoAAbility> register(String id, Supplier<AoAAbility> ability) {
		return AoARegistries.AOA_ABILITIES.register(id, ability);
	}

	@Nullable
	public static AoAAbility getAbility(ResourceLocation id) {
		return AoARegistries.AOA_ABILITIES.getEntry(id);
	}
}
