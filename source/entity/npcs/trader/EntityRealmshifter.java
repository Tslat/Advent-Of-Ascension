package net.tslat.aoa3.entity.npcs.trader;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.base.AoATraderRecipe;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class EntityRealmshifter extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityRealmshifter(World world) {
		super(world, entityWidth, 2.0f);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityRealmshifter;
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
		return Enums.ModGuis.TRADER_REALMSHIFTER;
	}

	@Override
	protected boolean isOverworldNPC() {
		return true;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 1), new ItemStack(BlockRegister.carvedRuneTravel, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 1), new ItemStack(BlockRegister.carvedRuneSpace, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 1), new ItemStack(BlockRegister.carvedRuneReality, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 1), new ItemStack(BlockRegister.carvedRuneDirection, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 1), new ItemStack(BlockRegister.ancientRock, 64)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 10), new ItemStack(BlockRegister.ancientRock, 30)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 1), new ItemStack(BlockRegister.ancientRock, 3)));
	}
}
