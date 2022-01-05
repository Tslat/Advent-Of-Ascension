package net.tslat.aoa3.object.entity.npc.trader;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.object.entity.base.AoATrader;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class DungeonKeeperEntity extends AoATrader {
	public DungeonKeeperEntity(EntityType<? extends AoATrader> entityType, World world) {
		super(entityType, world);

		this.setInvulnerable(true);
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level, AoADimensions.NOWHERE.key);
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ITrade[]> getTradesMap() {
		return null;
	}

	/*@Override TODO
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.SILVER_COIN.get(), 5), ItemStack.EMPTY, new ItemStack(AoAItems.PROGRESS_TOKEN.get(), 1), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.DUNGEON_TOKENS.get(), 1), ItemStack.EMPTY, new ItemStack(AoABlocks.ARCHAIC_TILES.get(), 16), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.DUNGEON_TOKENS.get(), 1), ItemStack.EMPTY, new ItemStack(AoABlocks.ARCHAIC_SQUARES.get(), 12), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.DUNGEON_TOKENS.get(), 1), ItemStack.EMPTY, new ItemStack(AoABlocks.ARCHAIC_RECTANGLES.get(), 12), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.DUNGEON_TOKENS.get(), 1), ItemStack.EMPTY, new ItemStack(AoABlocks.ARCHAIC_STREAM_HORIZONTAL.get(), 12), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.DUNGEON_TOKENS.get(), 1), ItemStack.EMPTY, new ItemStack(AoABlocks.ARCHAIC_STREAM_VERTICAL.get(), 12), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.DUNGEON_TOKENS.get(), 1), ItemStack.EMPTY, new ItemStack(AoABlocks.ARCHAIC_DIRT.get(), 16), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.DUNGEON_TOKENS.get(), 1), ItemStack.EMPTY, new ItemStack(AoABlocks.ARCHAIC_LADDER.get(), 8), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.DUNGEON_TOKENS.get(), 1), ItemStack.EMPTY, new ItemStack(AoABlocks.ARCHAIC_GLASS.get(), 8), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.DUNGEON_TOKENS.get(), 1), ItemStack.EMPTY, new ItemStack(AoABlocks.ARCHAIC_LIGHT.get(), 4), 0, 9999));
	}*/
}
