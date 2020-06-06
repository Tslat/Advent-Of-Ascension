package net.tslat.aoa3.entity.npcs.trader;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.base.AoATraderRecipe;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class EntityTrollTrader extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityTrollTrader(World world) {
		super(world, entityWidth, 1.8125f);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityTrollTrader;
	}

	@Override
	public float getEyeHeight() {
		return 1.625f;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 30;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Override
	protected Enums.ModGuis getTraderGui() {
		return Enums.ModGuis.TRADER_TROLL_TRADER;
	}

	@Override
	protected boolean isFixedTradesList() {
		return true;
	}

	@Override
	protected boolean isOverworldNPC() {
		return true;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.AMPHIBIYTE_LUNG, 1), new ItemStack(ItemRegister.COPPER_COIN, 6)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.AMPHIBIYTE_LUNG, 30), new ItemStack(WeaponRegister.CORALSTORM_SWORD)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.COPPER_COIN, 10), new ItemStack(Blocks.SAND, 64)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.SILVER_COIN, 1), new ItemStack(Items.PRISMARINE_SHARD, 4)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.SILVER_COIN, 1), new ItemStack(Items.PRISMARINE_CRYSTALS, 2)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.COPPER_COIN, 3), new ItemStack(Items.DYE, 1, 15)));
	}
}
