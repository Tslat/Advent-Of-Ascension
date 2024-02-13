package net.tslat.aoa3.integration.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IModIdHelper;
import mezz.jei.api.registration.*;
import mezz.jei.api.runtime.IIngredientManager;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoARecipes;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.entity.AoAMobs;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.item.AoATools;
import net.tslat.aoa3.content.recipe.*;
import net.tslat.aoa3.integration.IntegrationManager;
import net.tslat.aoa3.integration.jei.ingredient.subtype.TrophySubtypeInterpreter;
import net.tslat.aoa3.integration.jei.ingredient.type.imbuing.ImbuingIngredientHelper;
import net.tslat.aoa3.integration.jei.ingredient.type.imbuing.ImbuingIngredientRenderer;
import net.tslat.aoa3.integration.jei.ingredient.type.imbuing.ImbuingIngredientType;
import net.tslat.aoa3.integration.jei.recipe.ashferncooking.AshfernCookingRecipeExtension;
import net.tslat.aoa3.integration.jei.recipe.framebench.FrameBenchRecipeCategory;
import net.tslat.aoa3.integration.jei.recipe.framebench.FrameBenchRecipeTransferInfo;
import net.tslat.aoa3.integration.jei.recipe.imbuing.ImbuingRecipeCategory;
import net.tslat.aoa3.integration.jei.recipe.imbuing.ImbuingRecipeTransferInfo;
import net.tslat.aoa3.integration.jei.recipe.infusion.InfusionRecipeCategory;
import net.tslat.aoa3.integration.jei.recipe.infusion.InfusionRecipeTransferInfo;
import net.tslat.aoa3.integration.jei.recipe.toolinteraction.ToolInteractionRecipeExtension;
import net.tslat.aoa3.integration.jei.recipe.upgradekit.UpgradeKitRecipeCategory;
import net.tslat.aoa3.integration.jei.recipe.upgradekit.UpgradeKitRecipeTransferInfo;
import net.tslat.aoa3.integration.jei.recipe.whitewashing.WhitewashingRecipeCategory;
import net.tslat.aoa3.integration.jei.recipe.whitewashing.WhitewashingRecipeTransferInfo;
import net.tslat.aoa3.util.LocaleUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

@JeiPlugin
public class JEIIntegration implements IModPlugin {
	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation(AdventOfAscension.MOD_ID, "core");
	}

	@Override
	public void registerIngredients(IModIngredientRegistration registration) {
		registration.register(new ImbuingIngredientType(), List.of(), new ImbuingIngredientHelper(ImbuingIngredientType.INSTANCE), new ImbuingIngredientRenderer());
	}

	@Override
	public void registerVanillaCategoryExtensions(IVanillaCategoryExtensionRegistration registration) {
		registration.getCraftingCategory().addExtension(ToolInteractionRecipe.class, new ToolInteractionRecipeExtension());
		registration.getCraftingCategory().addExtension(AshfernCookingRecipe.class, new AshfernCookingRecipeExtension());
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		if (!IntegrationManager.isJEIActive())
			return;

		registration.addRecipeCatalyst(VanillaTypes.ITEM_STACK, new ItemStack(AoABlocks.DIVINE_STATION.get()), UpgradeKitRecipeCategory.RECIPE_TYPE);
		registration.addRecipeCatalyst(VanillaTypes.ITEM_STACK, new ItemStack(AoABlocks.INFUSION_TABLE.get()), InfusionRecipeCategory.RECIPE_TYPE);
		registration.addRecipeCatalyst(VanillaTypes.ITEM_STACK, new ItemStack(AoABlocks.IMBUING_CHAMBER.get()), ImbuingRecipeCategory.RECIPE_TYPE);
		registration.addRecipeCatalyst(VanillaTypes.ITEM_STACK, new ItemStack(AoABlocks.FRAME_BENCH.get()), FrameBenchRecipeCategory.RECIPE_TYPE);
		registration.addRecipeCatalyst(VanillaTypes.ITEM_STACK, new ItemStack(AoABlocks.WHITEWASHING_TABLE.get()), WhitewashingRecipeCategory.RECIPE_TYPE);
	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
		if (!IntegrationManager.isJEIActive())
			return;

		registration.addRecipeTransferHandler(new UpgradeKitRecipeTransferInfo());
		registration.addRecipeTransferHandler(new ImbuingRecipeTransferInfo());
		registration.addRecipeTransferHandler(new InfusionRecipeTransferInfo());
		registration.addRecipeTransferHandler(new FrameBenchRecipeTransferInfo());
		registration.addRecipeTransferHandler(new WhitewashingRecipeTransferInfo());
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		if (Minecraft.getInstance().getConnection() == null || !IntegrationManager.isJEIActive())
			return;

		RecipeManager recipeManager = Minecraft.getInstance().getConnection().getRecipeManager();

		registration.addRecipes(UpgradeKitRecipeCategory.RECIPE_TYPE, compileUpgradeKitRecipes(recipeManager));
		registration.addRecipes(ImbuingRecipeCategory.RECIPE_TYPE, compileImbuingRecipes(recipeManager));
		registration.addRecipes(InfusionRecipeCategory.RECIPE_TYPE, compileInfusionRecipes(recipeManager));
		registration.addRecipes(FrameBenchRecipeCategory.RECIPE_TYPE, compileFrameBenchRecipes(recipeManager));
		registration.addRecipes(WhitewashingRecipeCategory.RECIPE_TYPE, compileWhitewashingRecipes(recipeManager));

		addInfoRecipes(registration);
	}

	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
		jeiRuntime.getIngredientManager().addIngredientsAtRuntime(ImbuingIngredientType.INSTANCE, jeiRuntime.getRecipeManager().createRecipeLookup(ImbuingRecipeCategory.RECIPE_TYPE).get().map(recipe -> new EnchantmentInstance(recipe.getEnchant().left(), recipe.getEnchant().rightInt())).toList());
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		if (!IntegrationManager.isJEIActive())
			return;

		final IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();
		final IModIdHelper idHelper = registration.getJeiHelpers().getModIdHelper();
		final IIngredientManager ingredientHelper = registration.getJeiHelpers().getIngredientManager();

		registration.addRecipeCategories(
				new UpgradeKitRecipeCategory(guiHelper),
				new ImbuingRecipeCategory(guiHelper, idHelper, ingredientHelper),
				new InfusionRecipeCategory(guiHelper),
				new FrameBenchRecipeCategory(guiHelper),
				new WhitewashingRecipeCategory(guiHelper)
		);
	}

	@Override
	public void registerItemSubtypes(ISubtypeRegistration registration) {
		if (!IntegrationManager.isJEIActive())
			return;

		registration.registerSubtypeInterpreter(VanillaTypes.ITEM_STACK, AoABlocks.TROPHY.get().asItem(), TrophySubtypeInterpreter.INSTANCE);
		registration.registerSubtypeInterpreter(VanillaTypes.ITEM_STACK, AoABlocks.GOLD_TROPHY.get().asItem(), TrophySubtypeInterpreter.INSTANCE);
		registration.registerSubtypeInterpreter(VanillaTypes.ITEM_STACK, AoABlocks.ORNATE_TROPHY.get().asItem(), TrophySubtypeInterpreter.INSTANCE);
	}

	private List<UpgradeKitRecipe> compileUpgradeKitRecipes(RecipeManager recipeManager) {
		return recipeManager.getAllRecipesFor(AoARecipes.UPGRADE_KIT.type().get()).stream().map(RecipeHolder::value).toList();
	}

	private List<WhitewashingRecipe> compileWhitewashingRecipes(RecipeManager recipeManager) {
		return recipeManager.getAllRecipesFor(AoARecipes.WHITEWASHING.type().get()).stream().map(RecipeHolder::value).toList();
	}

	private ArrayList<FrameBenchRecipe> compileFrameBenchRecipes(RecipeManager recipeManager) {
		ArrayList<FrameBenchRecipe> frameRecipes = new ArrayList<>(10);

		frameRecipes.add(new FrameBenchRecipe(AoAItems.CROSSBOW_FRAME.get()));
		frameRecipes.add(new FrameBenchRecipe(AoAItems.BLASTER_FRAME.get()));
		frameRecipes.add(new FrameBenchRecipe(AoAItems.CANNON_FRAME.get()));
		frameRecipes.add(new FrameBenchRecipe(AoAItems.HELMET_FRAME.get()));
		frameRecipes.add(new FrameBenchRecipe(AoAItems.CHESTPLATE_FRAME.get()));
		frameRecipes.add(new FrameBenchRecipe(AoAItems.LEGGINGS_FRAME.get()));
		frameRecipes.add(new FrameBenchRecipe(AoAItems.BOOTS_FRAME.get()));
		frameRecipes.add(new FrameBenchRecipe(AoAItems.GUN_FRAME.get()));
		frameRecipes.add(new FrameBenchRecipe(AoAItems.SHOTGUN_FRAME.get()));
		frameRecipes.add(new FrameBenchRecipe(AoAItems.SNIPER_FRAME.get()));

		return frameRecipes;
	}

	private List<ImbuingRecipe> compileImbuingRecipes(RecipeManager recipeManager) {
		return recipeManager.getAllRecipesFor(AoARecipes.IMBUING.type().get()).stream().sorted(Comparator.comparing(RecipeHolder::id)).map(RecipeHolder::value).toList();
	}

	private List<InfusionRecipe> compileInfusionRecipes(RecipeManager recipeManager) {
		return recipeManager.getAllRecipesFor(AoARecipes.INFUSION.type().get()).stream().map(RecipeHolder::value).toList();
	}

	private static void addInfoRecipes(IRecipeRegistration registration) {
		final BiConsumer<Supplier<Item>, Component[]> infoRecipes = (item, entries) -> addItemDescription(registration, item, entries);

		infoRecipes.accept(AoAItems.BARATHOS_REALMSTONE, new Component[] {Component.translatable(jeiInfoLocaleKey("realmstone.1")), Component.translatable(jeiInfoLocaleKey("realmstone.2"))});
		infoRecipes.accept(AoAItems.PRECASIA_REALMSTONE, new Component[] {Component.translatable(jeiInfoLocaleKey("realmstone.1")), Component.translatable(jeiInfoLocaleKey("realmstone.2"))});
		infoRecipes.accept(AoAItems.NOWHERE_REALMSTONE, new Component[] {Component.translatable(jeiInfoLocaleKey("realmstone.1")), Component.translatable(jeiInfoLocaleKey("realmstone.2"))});
		infoRecipes.accept(AoAItems.NETHER_REALMSTONE, new Component[] {Component.translatable(jeiInfoLocaleKey("realmstone.1")), Component.translatable(jeiInfoLocaleKey("realmstone.2"))});
		infoRecipes.accept(AoAItems.TROLL_IDOL, new Component[] {Component.translatable(jeiInfoLocaleKey("boss_totem.1"), AoAMobs.SMASH.get().getDescription()), Component.translatable(jeiInfoLocaleKey("troll_idol")), Component.translatable(jeiInfoLocaleKey("boss_totem.2"))});
		infoRecipes.accept(AoAItems.BONE_HORN, new Component[] {Component.translatable(jeiInfoLocaleKey("boss_totem.1"), AoAMobs.TYROSAUR.get().getDescription()), Component.translatable(jeiInfoLocaleKey("bone_horn")), Component.translatable(jeiInfoLocaleKey("boss_totem.2"))});
		infoRecipes.accept(AoAItems.WARPED_HORN, new Component[] {Component.translatable(jeiInfoLocaleKey("boss_totem.1"), AoAMobs.SKELETRON.get().getDescription()), Component.translatable(jeiInfoLocaleKey("boss_totem.2"))});
		infoRecipes.accept(AoAItems.EXPLOSIVE_IDOL, new Component[] {Component.translatable(jeiInfoLocaleKey("boss_totem.1"), AoAMobs.KING_BAMBAMBAM.get().getDescription()), Component.translatable(jeiInfoLocaleKey("boss_totem.2"))});
		infoRecipes.accept(AoAItems.NETHENGEIC_CALLSTONE, new Component[] {Component.translatable(jeiInfoLocaleKey("boss_totem.1"), AoAMobs.NETHENGEIC_WITHER.get().getDescription()), Component.translatable(jeiInfoLocaleKey("nethengeic_callstone")), Component.translatable(jeiInfoLocaleKey("boss_totem.2"))});
		infoRecipes.accept(AoABlocks.GOLD_TROPHY.get()::asItem, new Component[] {Component.translatable(jeiInfoLocaleKey("trophy"))});
		infoRecipes.accept(AoABlocks.CARVED_RUNE_OF_DIRECTION.get()::asItem, new Component[] {Component.translatable(jeiInfoLocaleKey("portal_frame_block"))});
		infoRecipes.accept(AoABlocks.TEA_SINK.get()::asItem, new Component[] {Component.translatable(jeiInfoLocaleKey("tea_sink"))});
		infoRecipes.accept(AoABlocks.RUNE_RANDOMIZER.get()::asItem, new Component[] {Component.translatable(jeiInfoLocaleKey("rune_randomizer"))});
		infoRecipes.accept(AoABlocks.MINERALIZATION_STATION.get()::asItem, new Component[] {Component.translatable(jeiInfoLocaleKey("mineralization_station"))});
		infoRecipes.accept(AoABlocks.LUNAR_CREATION_TABLE.get()::asItem, new Component[] {Component.translatable(jeiInfoLocaleKey("lunar_creation_table"))});
		infoRecipes.accept(AoABlocks.MENDING_TABLE.get()::asItem, new Component[] {Component.translatable(jeiInfoLocaleKey("mending_table"))});
		infoRecipes.accept(AoAItems.MAGIC_REPAIR_DUST, new Component[] {Component.translatable(jeiInfoLocaleKey("magic_repair_dust"))});
		infoRecipes.accept(AoAItems.MAGIC_MENDING_COMPOUND, new Component[] {Component.translatable(jeiInfoLocaleKey("magic_mending_compound"))});
		infoRecipes.accept(AoABlocks.BOSS_ALTAR.get()::asItem, new Component[] {Component.translatable(jeiInfoLocaleKey("boss_altar"))});
		infoRecipes.accept(AoATools.ATTUNING_BOWL, new Component[] {Component.translatable(jeiInfoLocaleKey("attuning_bowl"))});
		infoRecipes.accept(AoAItems.AMBIENT_ENERGY_STONE, new Component[] {Component.translatable(jeiInfoLocaleKey("infusion_stone"))});
	}

	private static void addItemDescription(IRecipeRegistration registration, Supplier<Item> item, Component... description) {
		registration.addItemStackInfo(item.get().getDefaultInstance(), description);
	}

	public static String jeiInfoLocaleKey(String subPath) {
		return LocaleUtil.createGenericLocaleKey("description.jei", subPath);
	}
}