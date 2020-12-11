package net.tslat.aoa3.entity.npc.trader;

import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.npc.AoATraderRecipe;

public class NaturalistEntity extends AoATrader {
	public NaturalistEntity(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);
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
	protected boolean isOverworldNPC() {
		return true;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(Blocks.MELON, 8), new ItemStack(AoAItems.COPPER_COIN.get(), 18)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(Blocks.PUMPKIN, 8), new ItemStack(AoAItems.COPPER_COIN.get(), 15)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.BUBBLE_BERRIES.get(), 8), new ItemStack(AoAItems.COPPER_COIN.get(), 12)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.CHILLI.get(), 8), new ItemStack(AoAItems.COPPER_COIN.get(), 10)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.EYE_BULB.get(), 8), new ItemStack(AoAItems.COPPER_COIN.get(), 11)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.FLORACLE_STICKS.get(), 8), new ItemStack(AoAItems.COPPER_COIN.get(), 16)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.GOLDICAP_PETALS.get(), 8), new ItemStack(AoAItems.COPPER_COIN.get(), 14)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.HEART_FRUIT.get(), 8), new ItemStack(AoAItems.COPPER_COIN.get(), 19)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(Items.ARROW, 8), new ItemStack(AoAItems.COPPER_COIN.get(), 17)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.LUNACRIKE.get(), 8), new ItemStack(AoAItems.COPPER_COIN.get(), 12)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.LUNA_GLOBE.get(), 8), new ItemStack(AoAItems.COPPER_COIN.get(), 12)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.LUNALONS.get(), 8), new ItemStack(AoAItems.COPPER_COIN.get(), 12)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.MAGIC_MARANG.get(), 8), new ItemStack(AoAItems.COPPER_COIN.get(), 13)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.MYSTIC_SHROOMS.get(), 8), new ItemStack(AoAItems.COPPER_COIN.get(), 15)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.ROSIDONS.get(), 8), new ItemStack(AoAItems.COPPER_COIN.get(), 16)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.TEA_SHREDDINGS.get(), 8), new ItemStack(AoAItems.COPPER_COIN.get(), 14)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.TRILLIAD_LEAVES.get(), 8), new ItemStack(AoAItems.COPPER_COIN.get(), 13)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(Items.BEETROOT, 8), new ItemStack(AoAItems.COPPER_COIN.get(), 10)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(Items.CARROT, 8), new ItemStack(AoAItems.COPPER_COIN.get(), 8)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(Items.MELON_SLICE, 8), new ItemStack(AoAItems.COPPER_COIN.get(), 2)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(Items.POTATO, 8), new ItemStack(AoAItems.COPPER_COIN.get(), 12)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(Items.WHEAT, 8), new ItemStack(AoAItems.COPPER_COIN.get(), 9)));
	}
}
