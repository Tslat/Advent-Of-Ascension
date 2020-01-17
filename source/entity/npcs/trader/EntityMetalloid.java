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
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 4), new ItemStack(ItemRegister.gemAmethyst, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 8), new ItemStack(ItemRegister.gemJade, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 10), new ItemStack(ItemRegister.ingotRosite, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 5), new ItemStack(ItemRegister.gemSapphire, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.gemAmethyst, 1), new ItemStack(ItemRegister.coinSilver, 2)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.gemJade, 1), new ItemStack(ItemRegister.coinSilver, 4)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.ingotRosite, 1), new ItemStack(ItemRegister.coinSilver, 5)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.gemSapphire, 1), new ItemStack(ItemRegister.coinGold, 3)));
	}
}
