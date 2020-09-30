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
		newTradesList.add(new AoATraderRecipe(new ItemStack(Blocks.MELON_BLOCK, 8), new ItemStack(ItemRegister.COPPER_COIN, 18)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(Blocks.PUMPKIN, 8), new ItemStack(ItemRegister.COPPER_COIN, 15)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.BUBBLE_BERRIES, 8), new ItemStack(ItemRegister.COPPER_COIN, 12)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.CHILLI, 8), new ItemStack(ItemRegister.COPPER_COIN, 10)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.EYE_BULB, 8), new ItemStack(ItemRegister.COPPER_COIN, 11)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.FLORACLE_STICKS, 8), new ItemStack(ItemRegister.COPPER_COIN, 16)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.GOLDICAP_PETALS, 8), new ItemStack(ItemRegister.COPPER_COIN, 14)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.HEART_FRUIT, 8), new ItemStack(ItemRegister.COPPER_COIN, 19)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.HOLLY_TOP_PETALS, 8), new ItemStack(ItemRegister.COPPER_COIN, 17)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.LUNACRIKE, 8), new ItemStack(ItemRegister.COPPER_COIN, 12)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.LUNA_GLOBE, 8), new ItemStack(ItemRegister.COPPER_COIN, 12)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.LUNALONS, 8), new ItemStack(ItemRegister.COPPER_COIN, 12)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.MAGIC_MARANG, 8), new ItemStack(ItemRegister.COPPER_COIN, 13)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.MYSTIC_SHROOMS, 8), new ItemStack(ItemRegister.COPPER_COIN, 15)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.ROSIDONS, 8), new ItemStack(ItemRegister.COPPER_COIN, 16)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.TEA_SHREDDINGS, 8), new ItemStack(ItemRegister.COPPER_COIN, 14)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.TRILLIAD_LEAVES, 8), new ItemStack(ItemRegister.COPPER_COIN, 13)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(Items.BEETROOT, 8), new ItemStack(ItemRegister.COPPER_COIN, 10)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(Items.CARROT, 8), new ItemStack(ItemRegister.COPPER_COIN, 8)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(Items.MELON, 8), new ItemStack(ItemRegister.COPPER_COIN, 2)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(Items.POTATO, 8), new ItemStack(ItemRegister.COPPER_COIN, 12)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(Items.WHEAT, 8), new ItemStack(ItemRegister.COPPER_COIN, 9)));
	}
}
