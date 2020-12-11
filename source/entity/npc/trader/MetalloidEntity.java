package net.tslat.aoa3.entity.npc.trader;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.npc.AoATraderRecipe;

public class MetalloidEntity extends AoATrader {
	public MetalloidEntity(EntityType<? extends CreatureEntity> entityType, World world) {
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
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get(), 15), new ItemStack(AoAItems.LIMONITE_INGOT.get(), 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get(), 20), new ItemStack(AoAItems.ROSITE_INGOT.get(), 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get(), 30), new ItemStack(AoAItems.JADE.get(), 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get(), 40), new ItemStack(AoAItems.AMETHYST.get(), 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get(), 60), new ItemStack(AoAItems.SAPPHIRE.get(), 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get(), 45), new ItemStack(AoAItems.BARONYTE_INGOT.get(), 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get(), 50), new ItemStack(AoAItems.BLAZIUM_INGOT.get(), 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get(), 47), new ItemStack(AoAItems.VARSIUM_INGOT.get(), 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get(), 59), new ItemStack(AoAItems.LYON_INGOT.get(), 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.LIMONITE_INGOT.get(), 1), new ItemStack(AoAItems.COPPER_COIN.get(), 2)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.ROSITE_INGOT.get(), 1), new ItemStack(AoAItems.COPPER_COIN.get(), 4)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.JADE.get(), 1), new ItemStack(AoAItems.COPPER_COIN.get(), 6)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.AMETHYST.get(), 1), new ItemStack(AoAItems.COPPER_COIN.get(), 8)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.SAPPHIRE.get(), 1), new ItemStack(AoAItems.COPPER_COIN.get(), 10)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.BARONYTE_INGOT.get(), 1), new ItemStack(AoAItems.COPPER_COIN.get(), 8)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.BLAZIUM_INGOT.get(), 1), new ItemStack(AoAItems.COPPER_COIN.get(), 9)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.ELECANIUM_INGOT.get(), 1), new ItemStack(AoAItems.COPPER_COIN.get(), 12)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.EMBERSTONE_INGOT.get(), 1), new ItemStack(AoAItems.COPPER_COIN.get(), 10)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.GHASTLY_INGOT.get(), 1), new ItemStack(AoAItems.COPPER_COIN.get(), 9)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.GHOULISH_INGOT.get(), 1), new ItemStack(AoAItems.COPPER_COIN.get(), 9)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.LUNAR_INGOT.get(), 1), new ItemStack(AoAItems.COPPER_COIN.get(), 8)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.LYON_INGOT.get(), 1), new ItemStack(AoAItems.COPPER_COIN.get(), 9)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.SHYRESTONE_INGOT.get(), 1), new ItemStack(AoAItems.COPPER_COIN.get(), 13)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.SKELETAL_INGOT.get(), 1), new ItemStack(AoAItems.COPPER_COIN.get(), 8)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.VARSIUM_INGOT.get(), 1), new ItemStack(AoAItems.COPPER_COIN.get(), 8)));
	}
}
