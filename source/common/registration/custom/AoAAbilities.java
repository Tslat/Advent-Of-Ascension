package net.tslat.aoa3.common.registration.custom;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.player.ability.*;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class AoAAbilities {
	public static void init() {}

	public static final RegistryObject<AoAAbility> ATTRIBUTE_MODIFICATION = register("attribute_modification", () -> new AoAAbility(AttributeModification::new, AttributeModification::new));
	public static final RegistryObject<AoAAbility> FLAT_XP_BOOST = register("flat_xp_boost", () -> new AoAAbility(FlatXpBoost::new, FlatXpBoost::new));
	public static final RegistryObject<AoAAbility> DUMMY_ABILITY = register("dummy_ability", () -> new AoAAbility(DummyAbility::new, DummyAbility::new));
	public static final RegistryObject<AoAAbility> LEVEL_RESTRICTION = register("level_restriction", () -> new AoAAbility(LevelRestriction::new, LevelRestriction::new));
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
	public static final RegistryObject<AoAAbility> HAULING_GLOWING_FISH = register("hauling_glowing_fish", () -> new AoAAbility(HaulingGlowingFish::new, HaulingGlowingFish::new));
	public static final RegistryObject<AoAAbility> FISHING_XP_BOOST = register("fishing_xp_boost", () -> new AoAAbility(FishingXpBoost::new, FishingXpBoost::new));
	public static final RegistryObject<AoAAbility> HAULING_ROD_PULL_STRENGTH = register("hauling_rod_pull_strength", () -> new AoAAbility(HaulingRodPullStrengthModifier::new, HaulingRodPullStrengthModifier::new));
	public static final RegistryObject<AoAAbility> HAULING_ROD_PULL_DAMAGE = register("hauling_rod_pull_damage", () -> new AoAAbility(HaulingRodPullDamage::new, HaulingRodPullDamage::new));
	public static final RegistryObject<AoAAbility> FISHING_TRAP_SPAWN = register("fishing_trap_spawn", () -> new AoAAbility(FishingTrapSpawn::new, FishingTrapSpawn::new));
	public static final RegistryObject<AoAAbility> BLOCK_CONVERSION = register("block_conversion", () -> new AoAAbility(BlockConversion::new, BlockConversion::new));
	public static final RegistryObject<AoAAbility> HOE_AREA_HARVEST = register("hoe_area_harvest", () -> new AoAAbility(HoeAreaHarvest::new, HoeAreaHarvest::new));
	public static final RegistryObject<AoAAbility> HARVEST_REPLANT = register("harvest_replant", () -> new AoAAbility(HarvestReplant::new, HarvestReplant::new));
	public static final RegistryObject<AoAAbility> FERTILISE_FARMLAND = register("fertilise_farmland", () -> new AoAAbility(FertiliseFarmland::new, FertiliseFarmland::new));
	public static final RegistryObject<AoAAbility> DRYAD_SPRITE_SPAWN = register("dryad_sprite_spawn", () -> new AoAAbility(DryadSpriteSpawn::new, DryadSpriteSpawn::new));
	public static final RegistryObject<AoAAbility> BREEDING_BONUS = register("breeding_bonus", () -> new AoAAbility(BreedingBonus::new, BreedingBonus::new));
	public static final RegistryObject<AoAAbility> AUTO_HARVESTING_TRASH = register("auto_harvesting_trash", () -> new AoAAbility(AutoHarvestingTrash::new, AutoHarvestingTrash::new));
	public static final RegistryObject<AoAAbility> HARD_BLOCK_SPEED_INCREASE = register("hard_block_speed_increase", () -> new AoAAbility(HardBlockSpeedIncrease::new, HardBlockSpeedIncrease::new));
	public static final RegistryObject<AoAAbility> HARVEST_SPEED_BOOST = register("harvest_speed_boost", () -> new AoAAbility(HarvestSpeedBoost::new, HarvestSpeedBoost::new));
	public static final RegistryObject<AoAAbility> BONUS_SMELT_RESULT = register("bonus_smelt_result", () -> new AoAAbility(BonusSmeltResult::new, BonusSmeltResult::new));
	public static final RegistryObject<AoAAbility> BONUS_MINING_RESULT = register("bonus_mining_result", () -> new AoAAbility(BonusMiningResult::new, BonusMiningResult::new));
	public static final RegistryObject<AoAAbility> AUTO_ENCHANT_CRAFTING = register("auto_enchant_crafting", () -> new AoAAbility(AutoEnchantCrafting::new, AutoEnchantCrafting::new));
	public static final RegistryObject<AoAAbility> RARE_TABLE_HARVESTING_CHANCE = register("rare_table_harvesting_chance", () -> new AoAAbility(RareTableHarvestingChance::new, RareTableHarvestingChance::new));
	public static final RegistryObject<AoAAbility> BONUS_CRAFTING_OUTPUT = register("bonus_crafting_output", () -> new AoAAbility(BonusCraftingOutput::new, BonusCraftingOutput::new));
	public static final RegistryObject<AoAAbility> ENTITY_TAG_DAMAGE_BONUS = register("entity_tag_damage_bonus", () -> new AoAAbility(EntityTagDamageBonus::new, EntityTagDamageBonus::new));
	public static final RegistryObject<AoAAbility> ENTITY_TAG_DAMAGE_BONUS_MELEE = register("entity_tag_damage_bonus_melee", () -> new AoAAbility(EntityTagDamageBonusMelee::new, EntityTagDamageBonusMelee::new));
	public static final RegistryObject<AoAAbility> ENTITY_TAG_DAMAGE_BONUS_MAGIC = register("entity_tag_damage_bonus_magic", () -> new AoAAbility(EntityTagDamageBonusMagic::new, EntityTagDamageBonusMagic::new));
	public static final RegistryObject<AoAAbility> ENTITY_TAG_DAMAGE_BONUS_PROJECTILE = register("entity_tag_damage_bonus_projectile", () -> new AoAAbility(EntityTagDamageBonusProjectile::new, EntityTagDamageBonusProjectile::new));
	public static final RegistryObject<AoAAbility> ENTITY_TAG_DAMAGE_BONUS_EXPLOSION = register("entity_tag_damage_bonus_explosion", () -> new AoAAbility(EntityTagDamageBonusExplosion::new, EntityTagDamageBonusExplosion::new));

	private static RegistryObject<AoAAbility> register(String id, Supplier<AoAAbility> ability) {
		return AoARegistries.AOA_ABILITIES.register(id, ability);
	}

	@Nullable
	public static AoAAbility getAbility(ResourceLocation id) {
		return AoARegistries.AOA_ABILITIES.getObject(id);
	}
}
