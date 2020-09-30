package net.tslat.aoa3.entity.npcs.trader;

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

public class EntityMetalloid extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityMetalloid(World world) {
		super(world, entityWidth, 2.0f);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityMetalloid;
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
		return Enums.ModGuis.TRADER_METALLOID;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.COPPER_COIN, 15), new ItemStack(ItemRegister.LIMONITE_INGOT, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.COPPER_COIN, 20), new ItemStack(ItemRegister.ROSITE_INGOT, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.COPPER_COIN, 30), new ItemStack(ItemRegister.JADE, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.COPPER_COIN, 40), new ItemStack(ItemRegister.AMETHYST, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.COPPER_COIN, 60), new ItemStack(ItemRegister.SAPPHIRE, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.COPPER_COIN, 45), new ItemStack(ItemRegister.BARONYTE_INGOT, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.COPPER_COIN, 50), new ItemStack(ItemRegister.BLAZIUM_INGOT, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.COPPER_COIN, 47), new ItemStack(ItemRegister.VARSIUM_INGOT, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.COPPER_COIN, 59), new ItemStack(ItemRegister.LYON_INGOT, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.LIMONITE_INGOT, 1), new ItemStack(ItemRegister.COPPER_COIN, 2)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.ROSITE_INGOT, 1), new ItemStack(ItemRegister.COPPER_COIN, 4)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.JADE, 1), new ItemStack(ItemRegister.COPPER_COIN, 6)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.AMETHYST, 1), new ItemStack(ItemRegister.COPPER_COIN, 8)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.SAPPHIRE, 1), new ItemStack(ItemRegister.COPPER_COIN, 10)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.BARONYTE_INGOT, 1), new ItemStack(ItemRegister.COPPER_COIN, 8)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.BLAZIUM_INGOT, 1), new ItemStack(ItemRegister.COPPER_COIN, 9)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.ELECANIUM_INGOT, 1), new ItemStack(ItemRegister.COPPER_COIN, 12)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.EMBERSTONE_INGOT, 1), new ItemStack(ItemRegister.COPPER_COIN, 10)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.GHASTLY_INGOT, 1), new ItemStack(ItemRegister.COPPER_COIN, 9)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.GHOULISH_INGOT, 1), new ItemStack(ItemRegister.COPPER_COIN, 9)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.LUNAR_INGOT, 1), new ItemStack(ItemRegister.COPPER_COIN, 8)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.LYON_INGOT, 1), new ItemStack(ItemRegister.COPPER_COIN, 9)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.SHYRESTONE_INGOT, 1), new ItemStack(ItemRegister.COPPER_COIN, 13)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.SKELETAL_INGOT, 1), new ItemStack(ItemRegister.COPPER_COIN, 8)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.VARSIUM_INGOT, 1), new ItemStack(ItemRegister.COPPER_COIN, 8)));
	}
}
