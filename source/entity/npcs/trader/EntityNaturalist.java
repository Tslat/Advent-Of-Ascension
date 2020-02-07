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
		newTradesList.add(new AoATraderRecipe(new ItemStack(Blocks.MELON_BLOCK, 8), new ItemStack(ItemRegister.coinCopper, 18)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(Blocks.PUMPKIN, 8), new ItemStack(ItemRegister.coinCopper, 15)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.bubbleBerries, 8), new ItemStack(ItemRegister.coinCopper, 12)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.chilli, 8), new ItemStack(ItemRegister.coinCopper, 10)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.eyeBulb, 8), new ItemStack(ItemRegister.coinCopper, 11)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.floracleSticks, 8), new ItemStack(ItemRegister.coinCopper, 16)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.goldicapPetals, 8), new ItemStack(ItemRegister.coinCopper, 14)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.heartFruit, 8), new ItemStack(ItemRegister.coinCopper, 19)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.hollyTopPetals, 8), new ItemStack(ItemRegister.coinCopper, 17)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.lunacrike, 8), new ItemStack(ItemRegister.coinCopper, 12)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.lunaGlobe, 8), new ItemStack(ItemRegister.coinCopper, 12)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.lunalons, 8), new ItemStack(ItemRegister.coinCopper, 12)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.magicMarang, 8), new ItemStack(ItemRegister.coinCopper, 13)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.mysticShrooms, 8), new ItemStack(ItemRegister.coinCopper, 15)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.rosidons, 8), new ItemStack(ItemRegister.coinCopper, 16)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.teaShreddings, 8), new ItemStack(ItemRegister.coinCopper, 14)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.thornyPetals, 8), new ItemStack(ItemRegister.coinCopper, 12)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.trilliadLeaves, 8), new ItemStack(ItemRegister.coinCopper, 13)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(Items.BEETROOT, 8), new ItemStack(ItemRegister.coinCopper, 10)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(Items.CARROT, 8), new ItemStack(ItemRegister.coinCopper, 8)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(Items.MELON, 8), new ItemStack(ItemRegister.coinCopper, 2)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(Items.POTATO, 8), new ItemStack(ItemRegister.coinCopper, 12)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(Items.WHEAT, 8), new ItemStack(ItemRegister.coinCopper, 9)));
	}
}
