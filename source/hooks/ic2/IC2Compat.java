package net.tslat.aoa3.hooks.ic2;

import ic2.api.item.IC2Items;
import ic2.api.recipe.Recipes;
import ic2.core.recipe.RecipeInputBase;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.item.misc.SimpleItem;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.ItemUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class IC2Compat {
	public static final IC2CompatUtil util = new IC2CompatUtil();
	private static ArrayList<IC2CompatMaterial> materials = new ArrayList<IC2CompatMaterial>();

	public static void preInit() {
		MinecraftForge.EVENT_BUS.register(new IC2Compat());

		if (ConfigurationUtil.IntegrationsConfig.ic2.materials) {
			materials.add(new IC2CompatMaterial("Baronyte", "baronyte", "baronyte_ore", "baronyte_block", "baronyte_ingot"));
			materials.add(new IC2CompatMaterial("Blazium", "blazium", "blazium_ore", "blazium_block",  "blazium_ingot"));
			materials.add(new IC2CompatMaterial("Elecanium", "elecanium", "elecanium_ore", "elecanium_block", "elecanium_ingot"));
			materials.add(new IC2CompatMaterial("Emberstone", "emberstone", "emberstone_ore", "emberstone_block", "emberstone_ingot"));
			materials.add(new IC2CompatMaterial("Ghastly", "ghastly", "ghastly_ore", "ghastly_block", "ghastly_ingot"));
			materials.add(new IC2CompatMaterial("Ghoulish", "ghoulish", "ghoulish_ore", "ghoulish_block", "ghoulish_ingot"));
			materials.add(new IC2CompatMaterial("Limonite", "limonite", "limonite_ore", "limonite_block", "limonite_ingot"));
			materials.add(new IC2CompatMaterial("Lyon", "lyon", "lyon_ore", "lyon_block", "lyon_ingot"));
			materials.add(new IC2CompatMaterial("Mystite", "mystite", "mystite_ore", "mystite_block", "mystite_ingot"));
			materials.add(new IC2CompatMaterial("Rosite", "rosite", "rosite_ore", "rosite_block", "rosite_ingot"));
			materials.add(new IC2CompatMaterial("Shyrestone", "shyrestone", "shyrestone_ore", "shyrestone_block", "shyrestone_ingot"));
			materials.add(new IC2CompatMaterial("Varsium", "varsium", "varsium_ore", "varsium_block", "varsium_ingot"));
		}
	}

	public static void init() {
		for (IC2CompatMaterial material : materials) {
			material.addRecipes();
		}

		if (ConfigurationUtil.IntegrationsConfig.ic2.extractorRecipes) {
			Recipes.extractor.addRecipe(new IC2RecipeInputItemStack(new ItemStack(ItemRegister.UNPOWERED_RUNE, 16)), new NBTTagCompound(), false, new ItemStack(ItemRegister.RUNIUM_CHUNK, 1));
			Recipes.extractor.addRecipe(new IC2RecipeInputItemStack(new ItemStack(ItemRegister.CHARGED_RUNE, 16)), new NBTTagCompound(), false, new ItemStack(ItemRegister.CHARGED_RUNIUM_CHUNK, 1));
			Recipes.extractor.addRecipe(new IC2RecipeInputItemStack(new ItemStack(ItemRegister.RUSTED_IRON_INGOT)), new NBTTagCompound(), false, new ItemStack(ItemRegister.SCRAP_METAL, 1));
			Recipes.extractor.addRecipe(new IC2RecipeInputItemStack(new ItemStack(WeaponRegister.GRENADE, 2)), new NBTTagCompound(), false, new ItemStack(Items.GUNPOWDER, 1));
			Recipes.extractor.addRecipe(new IC2RecipeInputItemStack(new ItemStack(ItemRegister.CHESTBONE_FRAGMENT)), new NBTTagCompound(), false, new ItemStack(Items.DYE, 5, 15));
			Recipes.extractor.addRecipe(new IC2RecipeInputItemStack(new ItemStack(ItemRegister.LEGBONE_FRAGMENT)), new NBTTagCompound(), false, new ItemStack(Items.DYE, 5, 15));
			Recipes.extractor.addRecipe(new IC2RecipeInputItemStack(new ItemStack(ItemRegister.SKULLBONE_FRAGMENT)), new NBTTagCompound(), false, new ItemStack(Items.DYE, 5, 15));
			Recipes.extractor.addRecipe(new IC2RecipeInputItemStack(new ItemStack(ItemRegister.FOOTBONE_FRAGMENT)), new NBTTagCompound(), false, new ItemStack(Items.DYE, 5, 15));
			Recipes.extractor.addRecipe(new IC2RecipeInputItemStack(new ItemStack(ItemRegister.GOLD_COIN)), new NBTTagCompound(), false, new ItemStack(Items.GOLD_INGOT, 10));
		}

		if (ConfigurationUtil.IntegrationsConfig.ic2.compressorRecipes) {
			Recipes.compressor.addRecipe(new IC2RecipeInputItemStack(new ItemStack(ItemRegister.CHITIN, 2)), new NBTTagCompound(), false, new ItemStack(ItemRegister.ARMOUR_PLATING));
			Recipes.compressor.addRecipe(new IC2RecipeInputItemStack(new ItemStack(ItemRegister.ICE_CRYSTAL, 4)), new NBTTagCompound(), false, new ItemStack(Blocks.PACKED_ICE, 8));
		}

		materials = null;
	}

	public static IC2CompatUtil getCompatTool() {
		return util;
	}

	private static class IC2CompatMaterial {
		private final String registryPrefix;

		private final Supplier<Block> oreBlock;
		private final Supplier<Block> mineralBlock;
		private final Supplier<Item> ingot;
		private final Item crushedOre;
		private final Item purifiedCrushedOre;
		private final Item dust;
		private final Item tinyDust;
		private final Item plate;
		private final Item densePlate;

		private IC2CompatMaterial(String namePrefix, String registryPrefix, String oreBlockId, String mineralBlockId, String ingotId) {
			this.registryPrefix = registryPrefix;
			this.mineralBlock = new Supplier<Block>() {
				private Block block = null;

				@Override
				public Block get() {
					if (block != null)
						return block;

					block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("aoa3", mineralBlockId));

					return block;
				}
			};
			this.oreBlock = new Supplier<Block>() {
				private Block block = null;

				@Override
				public Block get() {
					if (block != null)
						return block;

					block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("aoa3", oreBlockId));

					return block;
				}
			};
			this.ingot = new Supplier<Item>() {
				private Item ingot = null;

				@Override
				public Item get() {
					if (ingot != null)
						return ingot;

					ingot = ForgeRegistries.ITEMS.getValue(new ResourceLocation("aoa3", ingotId));

					return ingot;
				}
			};
			this.crushedOre = new IC2CompatItem("CrushedOre", "Crushed" + namePrefix + "Ore", "crushed_" + registryPrefix + "_ore", registryPrefix);
			this.purifiedCrushedOre = new IC2CompatItem("PurifiedCrushedOre", "PurifiedCrushed" + namePrefix + "Ore", "purified_crushed_" + registryPrefix + "_ore", registryPrefix);
			this.dust = new IC2CompatItem("Dust", namePrefix + "Dust", registryPrefix + "_dust", registryPrefix);
			this.tinyDust = new IC2CompatItem("TinyDust", "Tiny" + namePrefix + "Dust", "tiny_" + registryPrefix + "_dust", registryPrefix);
			this.plate = new IC2CompatItem("Plate", namePrefix + "Plate", registryPrefix + "_plate", registryPrefix);
			this.densePlate = new IC2CompatItem("DensePlate", "Dense" + namePrefix + "Plate", "dense_" + registryPrefix + "_plate", registryPrefix);
		}

		private void addRecipes() {
			addMaceratorRecipes();
			addSmeltingRecipes();
			addMiscRecipes();
		}

		private void addMaceratorRecipes() {
			Recipes.macerator.addRecipe(new IC2RecipeInputItemStack(new ItemStack(oreBlock.get())), new NBTTagCompound(), false, new ItemStack(crushedOre, 2));
			Recipes.macerator.addRecipe(new IC2RecipeInputItemStack(new ItemStack(ingot.get())), new NBTTagCompound(), false, new ItemStack(dust));
			Recipes.macerator.addRecipe(new IC2RecipeInputItemStack(new ItemStack(plate)), new NBTTagCompound(), false, new ItemStack(tinyDust, 8));
			Recipes.macerator.addRecipe(new IC2RecipeInputItemStack(new ItemStack(densePlate)), new NBTTagCompound(), false, new ItemStack(dust, 8));
		}

		private void addSmeltingRecipes() {
			GameRegistry.addSmelting(crushedOre, new ItemStack(ingot.get()), 0);
			GameRegistry.addSmelting(purifiedCrushedOre, new ItemStack(ingot.get()), 0);
			GameRegistry.addSmelting(dust, new ItemStack(ingot.get()), 0);
		}

		private void addMiscRecipes() {
			GameRegistry.addShapelessRecipe(new ResourceLocation("aoa3", "ic2_" + registryPrefix + "_plate"), new ResourceLocation(""), new ItemStack(plate), Ingredient.fromItem(ingot.get()), Ingredient.fromStacks(new ItemStack(IC2Items.getItemAPI().getItem("forge_hammer"), 1, 32767)));
			GameRegistry.addShapelessRecipe(new ResourceLocation("aoa3", "ic2_" + registryPrefix + "_dust"), new ResourceLocation(""), new ItemStack(dust), Ingredient.fromItem(tinyDust), Ingredient.fromItem(tinyDust), Ingredient.fromItem(tinyDust), Ingredient.fromItem(tinyDust), Ingredient.fromItem(tinyDust), Ingredient.fromItem(tinyDust), Ingredient.fromItem(tinyDust), Ingredient.fromItem(tinyDust), Ingredient.fromItem(tinyDust));
			Recipes.compressor.addRecipe(new IC2RecipeInputItemStack(new ItemStack(tinyDust), 9), new NBTTagCompound(), false, new ItemStack(dust));
			Recipes.compressor.addRecipe(new IC2RecipeInputItemStack(new ItemStack(plate), 9), new NBTTagCompound(), false, new ItemStack(densePlate));
			Recipes.metalformerRolling.addRecipe(new IC2RecipeInputItemStack(new ItemStack(ingot.get())), new NBTTagCompound(), false, new ItemStack(plate));
			Recipes.blockcutter.addRecipe(new IC2RecipeInputItemStack(new ItemStack(mineralBlock.get())), new NBTTagCompound(), false, new ItemStack(plate, 9));
			Recipes.oreWashing.addRecipe(new IC2RecipeInputItemStack(new ItemStack(crushedOre)), new NBTTagCompound(), false, new ItemStack(purifiedCrushedOre), new ItemStack(tinyDust, 2), new ItemStack(IC2Items.getItemAPI().getItem("dust"), 1, 15));
			Recipes.centrifuge.addRecipe(new IC2RecipeInputItemStack(new ItemStack(crushedOre)), new NBTTagCompound(), false, new ItemStack(tinyDust), new ItemStack(dust), new ItemStack(IC2Items.getItemAPI().getItem("dust"), 1, 15));
			Recipes.centrifuge.addRecipe(new IC2RecipeInputItemStack(new ItemStack(purifiedCrushedOre)), new NBTTagCompound(), false, new ItemStack(tinyDust), new ItemStack(dust));
		}

		private void register(IForgeRegistry<Item> registry) {
			registry.registerAll(
					crushedOre,
					purifiedCrushedOre,
					dust,
					tinyDust,
					plate,
					densePlate
			);
		}

		private void registerRenders() {
			ModelLoader.setCustomModelResourceLocation(crushedOre, 0, new ModelResourceLocation(new ResourceLocation("aoa3", "ic2/crushedore/" + crushedOre.getRegistryName().getPath()), "inventory"));
			ModelLoader.setCustomModelResourceLocation(purifiedCrushedOre, 0, new ModelResourceLocation(new ResourceLocation("aoa3", "ic2/purifiedcrushedore/" + purifiedCrushedOre.getRegistryName().getPath()), "inventory"));
			ModelLoader.setCustomModelResourceLocation(dust, 0, new ModelResourceLocation(new ResourceLocation("aoa3", "ic2/dust/" + dust.getRegistryName().getPath()), "inventory"));
			ModelLoader.setCustomModelResourceLocation(tinyDust, 0, new ModelResourceLocation(new ResourceLocation("aoa3", "ic2/tinydust/" + tinyDust.getRegistryName().getPath()), "inventory"));
			ModelLoader.setCustomModelResourceLocation(plate, 0, new ModelResourceLocation(new ResourceLocation("aoa3", "ic2/plate/" + plate.getRegistryName().getPath()), "inventory"));
			ModelLoader.setCustomModelResourceLocation(densePlate, 0, new ModelResourceLocation(new ResourceLocation("aoa3", "ic2/denseplate/" + densePlate.getRegistryName().getPath()), "inventory"));
		}
	}

	@SubscribeEvent
	public void registerIc2Items(final RegistryEvent.Register<Item> ev) {
		for (IC2CompatMaterial material : materials) {
			material.register(ev.getRegistry());
		}
	}

	@SubscribeEvent
	public void registerItemRenders(final ModelRegistryEvent ev) {
		for (IC2CompatMaterial material : materials) {
			material.registerRenders();
		}
	}

	private static class IC2CompatItem extends SimpleItem {
		private final String materialTypeName;
		private final String type;

		private IC2CompatItem(String type, String name, String registryName, String registryPrefix) {
			super(name, registryName);

			this.materialTypeName = "material." + registryPrefix + ".name";
			this.type = type;
		}

		@Override
		public String getItemStackDisplayName(ItemStack stack) {
			return I18n.translateToLocal("item.IC2" + type + ".name").replace("%1$s", I18n.translateToLocal(materialTypeName));
		}
	}

	public static class IC2RecipeInputItemStack extends RecipeInputBase {
		public final ItemStack input;
		public final int amount;

		public IC2RecipeInputItemStack(ItemStack input) {
			this(input, 1);
		}

		public IC2RecipeInputItemStack(ItemStack input, int amount) {
			this.input = input;
			this.amount = amount;
		}

		@Override
		public int getAmount() {
			return amount;
		}

		@Override
		public boolean matches(ItemStack itemStack) {
			return ItemUtil.areStacksFunctionallyEqual(input, itemStack);
		}

		@Override
		public List<ItemStack> getInputs() {
			return Arrays.asList(input);
		}

		@Override
		public boolean equals(Object obj) {
			return obj != null && getClass() == obj.getClass() && matches(((IC2RecipeInputItemStack)obj).input);
		}
	}
}
