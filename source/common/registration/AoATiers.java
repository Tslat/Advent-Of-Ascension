package net.tslat.aoa3.common.registration;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.advent.AdventOfAscension;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public final class AoATiers {
	public static final CompactTier EMBERSTONE = new CompactTier("emberstone").durability(2050).digSpeed(10f).damage(12f).enchantValue(12).repairsWith(AoATags.Items.INGOTS_EMBERSTONE).netheriteEquivalent();
	public static final CompactTier JADE = new CompactTier("jade").durability(950).digSpeed(8.5f).damage(7.5f).enchantValue(12).repairsWith(AoATags.Items.GEMS_JADE).diamondEquivalent();
	public static final CompactTier LIMONITE = new CompactTier("limonite").durability(176).digSpeed(7f).damage(6.5f).enchantValue(3).repairsWith(AoATags.Items.INGOTS_LIMONITE).ironEquivalent();
	public static final CompactTier ORNAMYTE = new CompactTier("ornamyte").durability(2110).digSpeed(10f).damage(10f).enchantValue(10).repairsWith(AoATags.Items.GEMS_ORNAMYTE).netheriteEquivalent();
	public static final CompactTier SKELETAL = new CompactTier("skeletal").durability(1900).digSpeed(10f).damage(10f).enchantValue(12).repairsWith(AoATags.Items.INGOTS_SKELETAL).netheriteEquivalent();

	public static final CompactTier ENERGISTIC = new CompactTier("energistic").durability(2600).digSpeed(11).damage(12f).enchantValue(13).netheriteEquivalent();
	public static final CompactTier GEMCRACKER = new CompactTier("gemcracker").durability(2350).digSpeed(10).damage(11f).enchantValue(11).netheriteEquivalent();
	public static final CompactTier GOOFY = new CompactTier("goofy").durability(1500).digSpeed(8).damage(0).enchantValue(15).netheriteEquivalent();
	public static final CompactTier OCCULT = new CompactTier("occult").durability(2650).digSpeed(11f).damage(12f).enchantValue(15).netheriteEquivalent();
	public static final CompactTier PICKMAX = new CompactTier("pickmax").durability(3000).digSpeed(8).damage(12f).enchantValue(14).netheriteEquivalent();
	public static final CompactTier SOULSTONE = new CompactTier("soulstone").durability(2570).digSpeed(11).damage(12f).enchantValue(12).netheriteEquivalent();

	public static final CompactTier CHAINSAW = new CompactTier("chainsaw").durability(2500).digSpeed(18).damage(9f).enchantValue(0).netheriteEquivalent();
	public static final CompactTier DRYADS_BLESSING = new CompactTier("dryads_blessing").durability(3020).digSpeed(10).damage(10f).enchantValue(18).netheriteEquivalent();

	public static final CompactTier BARON = new CompactTier("baron").durability(1975).damage(12f).enchantValue(15).repairsWith(AoATags.Items.INGOTS_BARONYTE);
	public static final CompactTier BLOODFURY = new CompactTier("bloodfury").durability(1990).damage(13f).enchantValue(11);
	public static final CompactTier BLOODSTONE = new CompactTier("bloodstone").durability(1990).damage(15f).enchantValue(11).repairsWith(AoATags.Items.GEMS_BLOODSTONE);
	public static final CompactTier CANDLEFIRE = new CompactTier("candlefire").durability(2985).damage(18f).enchantValue(15);
	public static final CompactTier CARAMEL_CARVER = new CompactTier("caramel_carver").durability(2400).damage(16.5f).enchantValue(15);
	public static final CompactTier CORALSTORM = new CompactTier("coralstorm").durability(1800).damage(9f).enchantValue(15);
	public static final CompactTier CREEPIFIED = new CompactTier("creepified").durability(2090).damage(14.5f).enchantValue(15);
	public static final CompactTier CRYSTALLITE = new CompactTier("crystallite").durability(2310).damage(15f).enchantValue(12).repairsWith(AoATags.Items.GEMS_CRYSTALLITE);
	public static final CompactTier EXPLOCHRON = new CompactTier("explochron").durability(2360).damage(16f).enchantValue(15);
	public static final CompactTier FIREBORNE = new CompactTier("fireborne").durability(1995).damage(13f).enchantValue(15);
	public static final CompactTier GUARDIAN = new CompactTier("guardian").durability(2420).damage(16f).enchantValue(15);
	public static final CompactTier HARVESTER = new CompactTier("harvester").durability(2550).damage(18.5f).enchantValue(15);
	public static final CompactTier HOLY = new CompactTier("holy").durability(2500).damage(1).enchantValue(18);
	public static final CompactTier ILLUSION = new CompactTier("illusion").durability(2300).damage(15.5f).enchantValue(15);
	public static final CompactTier LEGBONE = new CompactTier("legbone").durability(2020).damage(12.5f).enchantValue(15);
	public static final CompactTier LIGHTS_WAY = new CompactTier("lights_way").durability(2900).damage(8.5f).enchantValue(12).repairsWith(AoATags.Items.INGOTS_SHYRESTONE);
	public static final CompactTier NETHENGEIC = new CompactTier("nethengeic").durability(2120).damage(15f).enchantValue(15);
	public static final CompactTier PRIMAL = new CompactTier("primal").durability(2060).damage(14f).enchantValue(15);
	public static final CompactTier ROCKBASHER = new CompactTier("rockbasher").durability(2430).damage(17f).enchantValue(15);
	public static final CompactTier ROCK_PICK = new CompactTier("rock_pick").durability(2100).digSpeed(9.5f).damage(13f).enchantValue(15).netheriteEquivalent();
	public static final CompactTier ROSIDIAN = new CompactTier("rosidian").durability(2430).damage(16.5f).enchantValue(15);
	public static final CompactTier RUNIC = new CompactTier("runic").durability(2690).damage(18.5f).enchantValue(15);
	public static final CompactTier SHADOW = new CompactTier("shadow").durability(2585).damage(18f).enchantValue(15);
	public static final CompactTier SHROOMUS = new CompactTier("shroomus").durability(2295).damage(16f).enchantValue(15);
	public static final CompactTier SUPREMACY = new CompactTier("supremacy").durability(2270).damage(15.5f).enchantValue(15);
	public static final CompactTier SWEET = new CompactTier("sweet").durability(2360).damage(16f).enchantValue(15);
	public static final CompactTier TROLL_BASHER = new CompactTier("troll_basher").durability(1800).digSpeed(9.5f).damage(13f).enchantValue(15).netheriteEquivalent();
	public static final CompactTier ULTRAFLAME = new CompactTier("ultraflame").durability(2350).damage(16f).enchantValue(15);
	public static final CompactTier VOID = new CompactTier("void").durability(1750).damage(11.5f).enchantValue(11);

	public static final CompactTier BARON_GREATBLADE = new CompactTier("baron_greatblade").durability(1200).damage(20.5f).enchantValue(13);
	public static final CompactTier BLOODLURKER_GREATBLADE = new CompactTier("bloodlurker_greatblade").durability(1350).damage(22f).enchantValue(13);
	public static final CompactTier CANDY_BLADE_GREATBLADE = new CompactTier("candy_blade_greatblade").durability(1450).damage(24f).enchantValue(13);
	public static final CompactTier CORAL_GREATBLADE = new CompactTier("coral_greatblade").durability(1800).damage(25.5f).enchantValue(13);
	public static final CompactTier COTTON_CRUSHER_GREATBLADE = new CompactTier("cotton_crusher_greatblade").durability(1600).damage(25.0f).enchantValue(13);
	public static final CompactTier CREEPOID_GREATBLADE = new CompactTier("creepoid_greatblade").durability(1080).damage(20f).enchantValue(13);
	public static final CompactTier CRYSTAL_GREATBLADE = new CompactTier("crystal_greatblade").durability(1480).damage(23f).enchantValue(13);
	public static final CompactTier EREBON_SCYTHE = new CompactTier("erebon_scythe").durability(1750).damage(20f).enchantValue(13);
	public static final CompactTier GODS_GREATBLADE = new CompactTier("gods_greatblade").durability(2000).damage(30.5f).enchantValue(13);
	public static final CompactTier GOOFY_GREATBLADE = new CompactTier("goofy_greatblade").durability(1300).damage(23.0f).enchantValue(13);
	public static final CompactTier HAUNTED_GREATBLADE = new CompactTier("haunted_greatblade").durability(1875).damage(27f).enchantValue(13);
	public static final CompactTier KNIGHTS_GUARD_GREATBLADE = new CompactTier("knights_guard_greatblade").durability(2050).damage(27.5f).enchantValue(13);
	public static final CompactTier LELYETIAN_GREATBLADE = new CompactTier("lelyetian_greatblade").durability(1100).damage(19.5f).enchantValue(13);
	public static final CompactTier LUNAR_GREATBLADE = new CompactTier("lunar_greatblade").durability(1850).damage(13.5f).enchantValue(13);
	public static final CompactTier LUXON_SCYTHE = new CompactTier("luxon_scythe").durability(1750).damage(18.5f).enchantValue(13);
	public static final CompactTier LYONIC_GREATBLADE = new CompactTier("lyonic_greatblade").durability(1420).damage(20f).enchantValue(13);
	public static final CompactTier MILLENNIUM_GREATBLADE = new CompactTier("millennium_greatblade").durability(2050).damage(14.25f).enchantValue(13);
	public static final CompactTier NOXIOUS_GREATBLADE = new CompactTier("noxious_greatblade").durability(1580).damage(24f).enchantValue(13);
	public static final CompactTier PLUTON_SCYTHE = new CompactTier("pluton_scythe").durability(1750).damage(20f).enchantValue(13);
	public static final CompactTier PRIMORDIAL_GREATBLADE = new CompactTier("primordial_greatblade").durability(1900).damage(26.5f).enchantValue(13);
	public static final CompactTier ROSIDIAN_GREATBLADE = new CompactTier("rosidian_greatblade").durability(1470).damage(23.5f).enchantValue(13);
	public static final CompactTier ROYAL_GREATBLADE = new CompactTier("royal_greatblade").durability(1130).damage(20f).enchantValue(13);
	public static final CompactTier RUNIC_GREATBLADE = new CompactTier("runic_greatblade").durability(1800).damage(25.5f).enchantValue(13);
	public static final CompactTier SELYAN_SCYTHE = new CompactTier("selyan_scythe").durability(1750).damage(20f).enchantValue(13);
	public static final CompactTier SHROOMIC_GREATBLADE = new CompactTier("shroomic_greatblade").durability(1300).damage(22.5f).enchantValue(13);
	public static final CompactTier SHYRE_SWORD_GREATBLADE = new CompactTier("shyre_sword_greatblade").durability(2000).damage(27f).enchantValue(13);
	public static final CompactTier SUBTERRANEAN_GREATBLADE = new CompactTier("subterranean_greatblade").durability(1160).damage(22.5f).enchantValue(13);
	public static final CompactTier TIDAL_GREATBLADE = new CompactTier("tidal_greatblade").durability(1750).damage(25f).enchantValue(13);
	public static final CompactTier UNDERWORLD_GREATBLADE = new CompactTier("underworld_greatblade").durability(1050).damage(19.5f).enchantValue(13);

	public static class CompactTier implements Tier {
		private final ResourceLocation name;

		private int durability;
		private float speed;
		private float damageBonus;
		private int level;
		private int enchantmentValue;
		private Supplier<Ingredient> repairIngredient = () -> Ingredient.EMPTY;
		private TagKey<Block> toolTier;

		private List<Object> betterThan;
		private List<Object> worseThan;

		public CompactTier(String name) {
			this(AdventOfAscension.id(name));
		}

		public CompactTier(ResourceLocation id) {
			this.name = id;
		}

		public CompactTier durability(int durability) {
			this.durability = durability;

			return this;
		}

		public CompactTier digSpeed(float speed) {
			this.speed = speed;

			return this;
		}

		public CompactTier damage(float damage) {
			this.damageBonus = damage - 1;

			return this;
		}

		public CompactTier level(int level) {
			this.level = level;

			return this;
		}

		public CompactTier enchantValue(int enchantValue) {
			this.enchantmentValue = enchantValue;

			return this;
		}

		public CompactTier repairsWith(RegistryObject<Item> item) {
			return repairsWith(() -> Ingredient.of(item.get()));
		}

		public CompactTier repairsWith(Item item) {
			return repairsWith(() -> Ingredient.of(item));
		}

		public CompactTier repairsWith(TagKey<Item> tag) {
			return repairsWith(() -> Ingredient.of(tag));
		}

		public CompactTier repairsWith(Supplier<Ingredient> ingredient) {
			this.repairIngredient = ingredient;

			return this;
		}

		public CompactTier woodEquivalent() {
			level(0);

			return toolTier(Tags.Blocks.NEEDS_WOOD_TOOL);
		}

		public CompactTier stoneEquivalent() {
			level(1);

			return toolTier(BlockTags.NEEDS_STONE_TOOL);
		}

		public CompactTier ironEquivalent() {
			level(2);

			return toolTier(BlockTags.NEEDS_IRON_TOOL);
		}

		public CompactTier goldEquivalent() {
			level(3);

			return toolTier(Tags.Blocks.NEEDS_GOLD_TOOL);
		}

		public CompactTier diamondEquivalent() {
			level(4);

			return toolTier(BlockTags.NEEDS_DIAMOND_TOOL);
		}

		public CompactTier netheriteEquivalent() {
			level(5);

			return toolTier(Tags.Blocks.NEEDS_NETHERITE_TOOL);
		}

		public CompactTier toolTier(TagKey<Block> tag) {
			this.toolTier = tag;

			return this;
		}

		public CompactTier betterThan(Tier... tiers) {
			if (this.betterThan == null)
				this.betterThan = new ArrayList<>(tiers.length);

			this.betterThan.addAll(Arrays.asList(tiers));

			return this;
		}

		public CompactTier betterThan(String... tiers) {
			if (this.betterThan == null)
				this.betterThan = new ArrayList<>(tiers.length);

			this.betterThan.addAll(Arrays.asList(tiers));

			return this;
		}

		public CompactTier betterThan(ResourceLocation... tiers) {
			if (this.betterThan == null)
				this.betterThan = new ArrayList<>(tiers.length);

			this.betterThan.addAll(Arrays.asList(tiers));

			return this;
		}

		public CompactTier worseThan(Tier... tiers) {
			if (this.worseThan == null)
				this.worseThan = new ArrayList<>(tiers.length);

			this.worseThan.addAll(Arrays.asList(tiers));

			return this;
		}

		public CompactTier worseThan(String... tiers) {
			if (this.worseThan == null)
				this.worseThan = new ArrayList<>(tiers.length);

			this.worseThan.addAll(Arrays.asList(tiers));

			return this;
		}

		public CompactTier worseThan(ResourceLocation... tiers) {
			if (this.worseThan == null)
				this.worseThan = new ArrayList<>(tiers.length);

			this.worseThan.addAll(Arrays.asList(tiers));

			return this;
		}

		@Override
		public int getUses() {
			return this.durability;
		}

		@Override
		public float getSpeed() {
			return this.speed;
		}

		@Override
		public float getAttackDamageBonus() {
			return this.damageBonus;
		}

		@Override
		public int getLevel() {
			return this.level;
		}

		@Override
		public int getEnchantmentValue() {
			return this.enchantmentValue;
		}

		@Override
		public Ingredient getRepairIngredient() {
			return this.repairIngredient.get();
		}

		@Nullable
		@Override
		public TagKey<Block> getTag() {
			return this.toolTier;
		}

		public CompactTier registerNonVanillaMiningTier() {
			TierSortingRegistry.registerTier(this, this.name, this.betterThan == null ? List.of() : this.betterThan, this.worseThan == null ? List.of() : List.of(this.worseThan));

			return this;
		}

		public static CompactTier basedOn(CompactTier tier, ResourceLocation name) {
			CompactTier newTier = new CompactTier(name);

			newTier.durability = tier.durability;
			newTier.speed = tier.speed;
			newTier.damageBonus = tier.damageBonus;
			newTier.level = tier.level;
			newTier.enchantmentValue = tier.enchantmentValue;
			newTier.repairIngredient = tier.repairIngredient;
			newTier.toolTier = tier.toolTier;

			newTier.betterThan = tier.betterThan == null ? null : List.copyOf(tier.betterThan);
			newTier.worseThan = tier.worseThan == null ? null : List.copyOf(tier.worseThan);

			return newTier;
		}

		public CompactTier adjusted(ResourceLocation newName) {
			return basedOn(this, newName);
		}
	}
}
