package net.tslat.aoa3.entity.npcs.trader;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.base.AoATraderRecipe;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class EntityNaturalist extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityNaturalist(World world) {
		super(world, entityWidth, 2.0f);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityNaturalist;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 25;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Override
	protected Enums.ModGuis getTraderGui() {
		return Enums.ModGuis.TRADER_NATURALIST;
	}

	@Override
	protected boolean isOverworldNPC() {
		return true;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(Blocks.MELON_BLOCK, 1), new ItemStack(ItemRegister.coinCopper, 2)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(Blocks.PUMPKIN, 1), new ItemStack(ItemRegister.coinCopper, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.bubbleBerries, 1), new ItemStack(ItemRegister.coinCopper, 2)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.chilli, 1), new ItemStack(ItemRegister.coinCopper, 4)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.eyeBulb, 1), new ItemStack(ItemRegister.coinCopper, 3)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.floracleSticks, 1), new ItemStack(ItemRegister.coinCopper, 6)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.goldicapPetals, 1), new ItemStack(ItemRegister.coinCopper, 4)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.heartFruit, 1), new ItemStack(ItemRegister.coinCopper, 6)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.hollyTopPetals, 1), new ItemStack(ItemRegister.coinCopper, 5)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.lunacrike, 1), new ItemStack(ItemRegister.coinCopper, 4)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.lunaGlobe, 1), new ItemStack(ItemRegister.coinCopper, 5)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.lunalons, 1), new ItemStack(ItemRegister.coinCopper, 3)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.magicMarang, 1), new ItemStack(ItemRegister.coinCopper, 8)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.mysticShrooms, 1), new ItemStack(ItemRegister.coinCopper, 5)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.rosidons, 1), new ItemStack(ItemRegister.coinCopper, 4)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.teaShreddings, 1), new ItemStack(ItemRegister.coinCopper, 4)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.thornyPetals, 1), new ItemStack(ItemRegister.coinCopper, 3)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.trilliadLeaves, 1), new ItemStack(ItemRegister.coinCopper, 6)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(Items.BEETROOT, 2), new ItemStack(ItemRegister.coinCopper, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(Items.CARROT, 2), new ItemStack(ItemRegister.coinCopper, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(Items.MELON, 4), new ItemStack(ItemRegister.coinCopper, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(Items.POTATO, 3), new ItemStack(ItemRegister.coinCopper, 2)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(Items.WHEAT, 2), new ItemStack(ItemRegister.coinCopper, 1)));
	}
}
