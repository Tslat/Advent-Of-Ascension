package net.tslat.aoa3.common.registration;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.tslat.aoa3.advent.AdventOfAscension;

@SuppressWarnings("ConstantConditions")
public class RecipeRegister {
	public static void registerRecipes() {
		AdventOfAscension.logOptionalMessage("Adding smelting recipes");

		GameRegistry.addSmelting(BlockRegister.oreAmethyst, new ItemStack(ItemRegister.gemAmethyst), 3.0f);
		GameRegistry.addSmelting(BlockRegister.oreBaronyte, new ItemStack(ItemRegister.ingotBaronyte), 3.0f);
		GameRegistry.addSmelting(BlockRegister.oreBlazium, new ItemStack(ItemRegister.ingotBlazium), 3.0f);
		GameRegistry.addSmelting(BlockRegister.oreBloodstone, new ItemStack(ItemRegister.gemBloodstone), 3.0f);
		GameRegistry.addSmelting(BlockRegister.oreChargedRunium, new ItemStack(ItemRegister.chargedRuniumChunk), 2.0f);
		GameRegistry.addSmelting(BlockRegister.oreCrystallite, new ItemStack(ItemRegister.gemCrystallite), 3.0f);
		GameRegistry.addSmelting(BlockRegister.oreElecanium, new ItemStack(ItemRegister.ingotElecanium), 3.0f);
		GameRegistry.addSmelting(BlockRegister.oreEmberstone, new ItemStack(ItemRegister.ingotEmberstone), 3.0f);
		GameRegistry.addSmelting(BlockRegister.oreGemenyte, new ItemStack(ItemRegister.gemGemenyte), 2.0f);
		GameRegistry.addSmelting(BlockRegister.oreGhastly, new ItemStack(ItemRegister.ingotGhastly), 3.0f);
		GameRegistry.addSmelting(BlockRegister.oreGhoulish, new ItemStack(ItemRegister.ingotGhoulish), 3.0f);
		GameRegistry.addSmelting(BlockRegister.oreJade, new ItemStack(ItemRegister.gemJade), 3.0f);
		GameRegistry.addSmelting(BlockRegister.oreJewelyte, new ItemStack(ItemRegister.gemJewelyte), 2.0f);
		GameRegistry.addSmelting(BlockRegister.oreLimonite, new ItemStack(ItemRegister.ingotLimonite), 3.0f);
		GameRegistry.addSmelting(BlockRegister.oreLyon, new ItemStack(ItemRegister.ingotLyon), 3.0f);
		GameRegistry.addSmelting(BlockRegister.oreMystite, new ItemStack(ItemRegister.ingotMystite), 3.0f);
		GameRegistry.addSmelting(BlockRegister.oreOrnamyte, new ItemStack(ItemRegister.gemOrnamyte), 3.0f);
		GameRegistry.addSmelting(BlockRegister.oreRosite, new ItemStack(ItemRegister.ingotRosite), 3.0f);
		GameRegistry.addSmelting(BlockRegister.oreRunium, new ItemStack(ItemRegister.runiumChunk), 2.0f);
		GameRegistry.addSmelting(BlockRegister.oreSapphire, new ItemStack(ItemRegister.gemSapphire), 3.0f);
		GameRegistry.addSmelting(BlockRegister.oreBlueGemstone, new ItemStack(ItemRegister.gemstonesBlue), 3.0f);
		GameRegistry.addSmelting(BlockRegister.oreGreenGemstone, new ItemStack(ItemRegister.gemstonesGreen), 3.0f);
		GameRegistry.addSmelting(BlockRegister.orePurpleGemstone, new ItemStack(ItemRegister.gemstonesPurple), 3.0f);
		GameRegistry.addSmelting(BlockRegister.oreRedGemstone, new ItemStack(ItemRegister.gemstonesRed), 3.0f);
		GameRegistry.addSmelting(BlockRegister.oreWhiteGemstone, new ItemStack(ItemRegister.gemstonesWhite), 3.0f);
		GameRegistry.addSmelting(BlockRegister.oreYellowGemstone, new ItemStack(ItemRegister.gemstonesYellow), 3.0f);
		GameRegistry.addSmelting(BlockRegister.oreShyregem, new ItemStack(ItemRegister.gemShyregem), 5.0f);
		GameRegistry.addSmelting(BlockRegister.oreShyrestone, new ItemStack(ItemRegister.ingotShyrestone), 2.0f);
		GameRegistry.addSmelting(BlockRegister.oreVarsium, new ItemStack(ItemRegister.ingotVarsium), 3.0f);
		GameRegistry.addSmelting(ItemRegister.boneFragmentChestbone, new ItemStack(ItemRegister.ingotSkeletal), 3.0f);
		GameRegistry.addSmelting(ItemRegister.boneFragmentFootbone, new ItemStack(ItemRegister.ingotSkeletal), 3.0f);
		GameRegistry.addSmelting(ItemRegister.boneFragmentLegbone, new ItemStack(ItemRegister.ingotSkeletal), 3.0f);
		GameRegistry.addSmelting(ItemRegister.boneFragmentSkullbone, new ItemStack(ItemRegister.ingotSkeletal), 3.0f);
		GameRegistry.addSmelting(ItemRegister.candlefishRaw, new ItemStack(ItemRegister.candlefish), 3.0f);
		GameRegistry.addSmelting(ItemRegister.chargerShankRaw, new ItemStack(ItemRegister.chargerShank), 3.0f);
		GameRegistry.addSmelting(ItemRegister.chimeraChopRaw, new ItemStack(ItemRegister.chimeraChop), 3.0f);
		GameRegistry.addSmelting(ItemRegister.crimsonSkipperRaw, new ItemStack(ItemRegister.crimsonSkipper), 3.0f);
		GameRegistry.addSmelting(ItemRegister.crimsonStripefishRaw, new ItemStack(ItemRegister.crimsonStripefish), 3.0f);
		GameRegistry.addSmelting(ItemRegister.darkHatchetfishRaw, new ItemStack(ItemRegister.darkHatchetfish), 3.0f);
		GameRegistry.addSmelting(ItemRegister.fingerfishRaw, new ItemStack(ItemRegister.fingerfish), 3.0f);
		GameRegistry.addSmelting(ItemRegister.furlionChopRaw, new ItemStack(ItemRegister.furlionChop), 3.0f);
		GameRegistry.addSmelting(ItemRegister.goldenGullfishRaw, new ItemStack(ItemRegister.goldenGullfish), 3.0f);
		GameRegistry.addSmelting(ItemRegister.halyconBeefRaw, new ItemStack(ItemRegister.halyconBeef), 3.0f);
		GameRegistry.addSmelting(ItemRegister.ingotRustedIron, new ItemStack(ItemRegister.scrapMetal), 1.0f);
		GameRegistry.addSmelting(ItemRegister.ironbackRaw, new ItemStack(ItemRegister.ironback), 3.0f);
		GameRegistry.addSmelting(ItemRegister.limefishRaw, new ItemStack(ItemRegister.limefish), 3.0f);
		GameRegistry.addSmelting(ItemRegister.pearlStripefishRaw, new ItemStack(ItemRegister.pearlStripefish), 3.0f);
		GameRegistry.addSmelting(ItemRegister.rainbowfishRaw, new ItemStack(ItemRegister.rainbowfish), 3.0f);
		GameRegistry.addSmelting(ItemRegister.razorfishRaw, new ItemStack(ItemRegister.razorfish), 3.0f);
		GameRegistry.addSmelting(ItemRegister.rocketfishRaw, new ItemStack(ItemRegister.rocketfish), 3.0f);
		GameRegistry.addSmelting(ItemRegister.sailbackRaw, new ItemStack(ItemRegister.sailback), 3.0f);
		GameRegistry.addSmelting(ItemRegister.sapphireStriderRaw, new ItemStack(ItemRegister.sapphireStrider), 3.0f);
		GameRegistry.addSmelting(ItemRegister.scrapMetal, new ItemStack(Items.IRON_NUGGET), 1.0f);
		GameRegistry.addSmelting(ItemRegister.turquoiseStripefishRaw, new ItemStack(ItemRegister.turquoiseStripefish), 3.0f);
		GameRegistry.addSmelting(ItemRegister.ursaMeatRaw, new ItemStack(ItemRegister.ursaMeat), 3.0f);
		GameRegistry.addSmelting(ItemRegister.violetSkipperRaw, new ItemStack(ItemRegister.violetSkipper), 3.0f);
	}
}
