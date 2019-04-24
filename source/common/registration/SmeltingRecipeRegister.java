package net.tslat.aoa3.common.registration;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SmeltingRecipeRegister {
	public static void registerRecipes() {
		GameRegistry.addSmelting(BlockRegister.oreAmethyst, new ItemStack(ItemRegister.amethyst), 3.0f);
		GameRegistry.addSmelting(BlockRegister.oreBaronyte, new ItemStack(ItemRegister.ingotBaronyte), 3.0f);
		GameRegistry.addSmelting(BlockRegister.oreBlazium, new ItemStack(ItemRegister.ingotBlazium), 3.0f);
		GameRegistry.addSmelting(BlockRegister.oreChargedRunium, new ItemStack(ItemRegister.chargedRuniumChunk), 2.0f);
		GameRegistry.addSmelting(BlockRegister.oreElecanium, new ItemStack(ItemRegister.ingotElecanium), 3.0f);
		GameRegistry.addSmelting(BlockRegister.oreEmberstone, new ItemStack(ItemRegister.ingotEmberstone), 3.0f);
		GameRegistry.addSmelting(BlockRegister.oreGemenyte, new ItemStack(ItemRegister.gemGemenyte), 2.0f);
		GameRegistry.addSmelting(BlockRegister.oreGhastly, new ItemStack(ItemRegister.ingotGhastly), 3.0f);
		GameRegistry.addSmelting(BlockRegister.oreGhoulish, new ItemStack(ItemRegister.ingotGhoulish), 3.0f);
		GameRegistry.addSmelting(BlockRegister.oreJade, new ItemStack(ItemRegister.jade), 3.0f);
		GameRegistry.addSmelting(BlockRegister.oreJewelyte, new ItemStack(ItemRegister.gemJewelyte), 2.0f);
		GameRegistry.addSmelting(BlockRegister.oreLimonite, new ItemStack(ItemRegister.ingotLimonite), 3.0f);
		GameRegistry.addSmelting(BlockRegister.oreLyon, new ItemStack(ItemRegister.ingotLyon), 3.0f);
		GameRegistry.addSmelting(BlockRegister.oreMystite, new ItemStack(ItemRegister.ingotMystite), 3.0f);
		GameRegistry.addSmelting(BlockRegister.oreOrnamyte, new ItemStack(ItemRegister.gemOrnamyte), 3.0f);
		GameRegistry.addSmelting(BlockRegister.oreRosite, new ItemStack(ItemRegister.ingotRosite), 3.0f);
		GameRegistry.addSmelting(BlockRegister.oreRunium, new ItemStack(ItemRegister.runiumChunk), 2.0f);
		GameRegistry.addSmelting(BlockRegister.oreSapphire, new ItemStack(ItemRegister.sapphire), 3.0f);
		GameRegistry.addSmelting(BlockRegister.oreShyregem, new ItemStack(ItemRegister.shyregem), 5.0f);
		GameRegistry.addSmelting(BlockRegister.oreShyrestone, new ItemStack(ItemRegister.ingotShyrestone), 2.0f);
		GameRegistry.addSmelting(BlockRegister.oreVarsium, new ItemStack(ItemRegister.ingotVarsium), 3.0f);
		GameRegistry.addSmelting(ItemRegister.chimeraChopRaw, new ItemStack(ItemRegister.chimeraChop), 3.0f);
		GameRegistry.addSmelting(ItemRegister.furlionChopRaw, new ItemStack(ItemRegister.furlionChop), 3.0f);
		GameRegistry.addSmelting(ItemRegister.halyconBeefRaw, new ItemStack(ItemRegister.halyconBeef), 3.0f);
		GameRegistry.addSmelting(ItemRegister.ursaMeatRaw, new ItemStack(ItemRegister.ursaMeat), 3.0f);
		GameRegistry.addSmelting(ItemRegister.chargerShankRaw, new ItemStack(ItemRegister.chargerShank), 3.0f);
	}
}
