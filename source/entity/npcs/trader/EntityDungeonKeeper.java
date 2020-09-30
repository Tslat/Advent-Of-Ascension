package net.tslat.aoa3.entity.npcs.trader;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.base.AoATraderRecipe;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;

public class EntityDungeonKeeper extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityDungeonKeeper(World world) {
		super(world, entityWidth, 2.0f);

		this.setEntityInvulnerable(true);
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
	protected boolean isFixedTradesList() {
		return true;
	}

	@Override
	protected Enums.ModGuis getTraderGui() {
		return Enums.ModGuis.TRADER_DUNGEON_KEEPER;
	}

	@Override
	protected boolean canDespawn() {
		return world.provider.getDimension() != ConfigurationUtil.MainConfig.dimensionIds.immortallis;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.SILVER_COIN, 5), new ItemStack(ItemRegister.PROGRESS_COIN0, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.DUNGEON_TOKENS, 1), new ItemStack(BlockRegister.ARCHAIC_TILES_BREAKABLE, 16)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.DUNGEON_TOKENS, 1), new ItemStack(BlockRegister.ARCHAIC_SQUARES_BREAKABLE, 12)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.DUNGEON_TOKENS, 1), new ItemStack(BlockRegister.ARCHAIC_RECTANGLES_BREAKABLE, 12)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.DUNGEON_TOKENS, 1), new ItemStack(BlockRegister.ARCHAIC_STREAM_HORIZONTAL_BREAKABLE, 12)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.DUNGEON_TOKENS, 1), new ItemStack(BlockRegister.ARCHAIC_STREAM_VERTICAL_BREAKABLE, 12)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.DUNGEON_TOKENS, 1), new ItemStack(BlockRegister.ARCHAIC_DIRT_BREAKABLE, 16)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.DUNGEON_TOKENS, 1), new ItemStack(BlockRegister.ARCHAIC_LADDER_BREAKABLE, 8)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.DUNGEON_TOKENS, 1), new ItemStack(BlockRegister.ARCHAIC_GLASS_BREAKABLE, 8)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.DUNGEON_TOKENS, 1), new ItemStack(BlockRegister.ARCHAIC_LIGHT_BREAKABLE, 4)));
	}
}
