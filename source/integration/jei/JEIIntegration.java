package net.tslat.aoa3.integration.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.*;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoARecipes;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.entity.AoAMobs;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.recipe.*;
import net.tslat.aoa3.integration.IntegrationManager;
import net.tslat.aoa3.integration.jei.ingredient.subtype.TrophySubtypeInterpreter;
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
	public void registerVanillaCategoryExtensions(IVanillaCategoryExtensionRegistration registration) {
		registration.getCraftingCategory().addCategoryExtension(ToolInteractionRecipe.class, ToolInteractionRecipeExtension::new);
		registration.getCraftingCategory().addCategoryExtension(AshfernCookingRecipe.class, AshfernCookingRecipeExtension::new);
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		if (!IntegrationManager.isJEIActive())
			return;

		registration.addRecipeCatalyst(VanillaTypes.ITEM_STACK, new ItemStack(AoABlocks.DIVINE_STATION.get()), UpgradeKitRecipeCategory.RECIPE_TYPE);
		registration.addRecipeCatalyst(VanillaTypes.ITEM_STACK, new ItemStack(AoABlocks.INFUSION_TABLE.get()), InfusionRecipeCategory.RECIPE_TYPE, ImbuingRecipeCategory.RECIPE_TYPE);
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
	public void registerCategories(IRecipeCategoryRegistration registration) {
		if (!IntegrationManager.isJEIActive())
			return;

		registration.addRecipeCategories(
				new UpgradeKitRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
				new ImbuingRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
				new InfusionRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
				new FrameBenchRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
				new WhitewashingRecipeCategory(registration.getJeiHelpers().getGuiHelper())
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
		return recipeManager.getAllRecipesFor(AoARecipes.UPGRADE_KIT.type().get());
	}

	private List<WhitewashingRecipe> compileWhitewashingRecipes(RecipeManager recipeManager) {
		return recipeManager.getAllRecipesFor(AoARecipes.WHITEWASHING.type().get());
	}

	private ArrayList<FrameBenchRecipe> compileFrameBenchRecipes(RecipeManager recipeManager) {
		ArrayList<FrameBenchRecipe> frameRecipes = new ArrayList<>(10);

		frameRecipes.add(new FrameBenchRecipe(new ResourceLocation(AdventOfAscension.MOD_ID, "frame_bench_crossbow"), AoAItems.CROSSBOW_FRAME.get()));
		frameRecipes.add(new FrameBenchRecipe(new ResourceLocation(AdventOfAscension.MOD_ID, "frame_bench_blaster"), AoAItems.BLASTER_FRAME.get()));
		frameRecipes.add(new FrameBenchRecipe(new ResourceLocation(AdventOfAscension.MOD_ID, "frame_bench_cannon"), AoAItems.CANNON_FRAME.get()));
		frameRecipes.add(new FrameBenchRecipe(new ResourceLocation(AdventOfAscension.MOD_ID, "frame_bench_helmet"), AoAItems.HELMET_FRAME.get()));
		frameRecipes.add(new FrameBenchRecipe(new ResourceLocation(AdventOfAscension.MOD_ID, "frame_bench_chestplate"), AoAItems.CHESTPLATE_FRAME.get()));
		frameRecipes.add(new FrameBenchRecipe(new ResourceLocation(AdventOfAscension.MOD_ID, "frame_bench_leggings"), AoAItems.LEGGINGS_FRAME.get()));
		frameRecipes.add(new FrameBenchRecipe(new ResourceLocation(AdventOfAscension.MOD_ID, "frame_bench_boots"), AoAItems.BOOTS_FRAME.get()));
		frameRecipes.add(new FrameBenchRecipe(new ResourceLocation(AdventOfAscension.MOD_ID, "frame_bench_gun"), AoAItems.GUN_FRAME.get()));
		frameRecipes.add(new FrameBenchRecipe(new ResourceLocation(AdventOfAscension.MOD_ID, "frame_bench_shotgun"), AoAItems.SHOTGUN_FRAME.get()));
		frameRecipes.add(new FrameBenchRecipe(new ResourceLocation(AdventOfAscension.MOD_ID, "frame_bench_sniper"), AoAItems.SNIPER_FRAME.get()));

		return frameRecipes;
	}

	private ArrayList<InfusionRecipe> compileImbuingRecipes(RecipeManager recipeManager) {
		ArrayList<InfusionRecipe> imbuingRecipes = new ArrayList<>();

		for (InfusionRecipe recipe : recipeManager.getAllRecipesFor(AoARecipes.INFUSION.type().get())) {
			if (recipe.isEnchanting())
				imbuingRecipes.add(recipe);
		}

		return imbuingRecipes;
	}

	private ArrayList<InfusionRecipe> compileInfusionRecipes(RecipeManager recipeManager) {
		ArrayList<InfusionRecipe> infusionRecipes = new ArrayList<>();

		for (InfusionRecipe recipe : recipeManager.getAllRecipesFor(AoARecipes.INFUSION.type().get())) {
			if (!recipe.isEnchanting())
				infusionRecipes.add(recipe);
		}

		return infusionRecipes;
	}

	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
		if (!IntegrationManager.isJEIActive())
			return;

		/*if (!IntegrationManager.isTinkersConstructActive()) {
			Function<FluidUtil.RegisteredFluidHolder, FluidStack> fluidStackGen = holder -> new FluidStack(holder.fluid().get(), FluidAttributes.BUCKET_VOLUME);

			jeiRuntime.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.FLUID, Arrays.asList(
					fluidStackGen.apply(TinkersFluids.MOLTEN_BARONYTE),
					fluidStackGen.apply(TinkersFluids.MOLTEN_BLAZIUM),
					fluidStackGen.apply(TinkersFluids.MOLTEN_ELECANIUM),
					fluidStackGen.apply(TinkersFluids.MOLTEN_EMBERSTONE),
					fluidStackGen.apply(TinkersFluids.MOLTEN_GHASTLY),
					fluidStackGen.apply(TinkersFluids.MOLTEN_GHOULISH),
					fluidStackGen.apply(TinkersFluids.MOLTEN_LIMONITE),
					fluidStackGen.apply(TinkersFluids.MOLTEN_LUNAR),
					fluidStackGen.apply(TinkersFluids.MOLTEN_LYON),
					fluidStackGen.apply(TinkersFluids.MOLTEN_MYSTITE),
					fluidStackGen.apply(TinkersFluids.MOLTEN_ROSITE),
					fluidStackGen.apply(TinkersFluids.MOLTEN_SHYRESTONE),
					fluidStackGen.apply(TinkersFluids.MOLTEN_SKELETAL),
					fluidStackGen.apply(TinkersFluids.MOLTEN_VARSIUM),
					fluidStackGen.apply(TinkersFluids.MOLTEN_CHARGER)));
		}*/

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
	}

	private static void addItemDescription(IRecipeRegistration registration, Supplier<Item> item, Component... description) {
		registration.addItemStackInfo(item.get().getDefaultInstance(), description);
	}

	public static String jeiInfoLocaleKey(String subPath) {
		return LocaleUtil.createGenericLocaleKey("description.jei", subPath);
	}
}