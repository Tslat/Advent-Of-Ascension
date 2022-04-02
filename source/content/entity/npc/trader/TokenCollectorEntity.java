package net.tslat.aoa3.content.entity.npc.trader;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.content.entity.base.AoATrader;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class TokenCollectorEntity extends AoATrader {
	public TokenCollectorEntity(EntityType<? extends AoATrader> entityType, Level world) {
		super(entityType, world);

		this.setInvulnerable(true);
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level, AoADimensions.NOWHERE.key);
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ItemListing[]> getTradesMap() {
		return null;
	}

	/*@Override TODO
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.DUNGEON_TOKENS.get(), 1), ItemStack.EMPTY, new ItemStack(AoABlocks.IMMORTAL_BANNER.get(), 1), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.DUNGEON_TOKENS.get(), 1), ItemStack.EMPTY, new ItemStack(AoAWeapons.VULCANE.get(), 1), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.DUNGEON_TOKENS.get(), 13), ItemStack.EMPTY, new ItemStack(AoAItems.FIRE_VULCANE_AUGMENT.get()), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.DUNGEON_TOKENS.get(), 9), ItemStack.EMPTY, new ItemStack(AoAItems.IMPAIRMENT_VULCANE_AUGMENT.get()), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.DUNGEON_TOKENS.get(), 10), ItemStack.EMPTY, new ItemStack(AoAItems.POISON_VULCANE_AUGMENT.get()), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.DUNGEON_TOKENS.get(), 5), ItemStack.EMPTY, new ItemStack(AoAItems.POWER_VULCANE_AUGMENT.get()), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.DUNGEON_TOKENS.get(), 15), ItemStack.EMPTY, new ItemStack(AoAItems.WITHER_VULCANE_AUGMENT.get()), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.DUNGEON_TOKENS.get(), 25), ItemStack.EMPTY, new ItemStack(AoAItems.EQUALITY_VULCANE_AUGMENT.get()), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.DUNGEON_TOKENS.get(), 20), ItemStack.EMPTY, new ItemStack(AoAItems.BATTLE_VULCANE_AUGMENT.get()), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.DUNGEON_TOKENS.get(), 28), ItemStack.EMPTY, new ItemStack(AoAWeapons.VULCAMMER_MAUL.get()), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.DUNGEON_TOKENS.get(), 26), ItemStack.EMPTY, new ItemStack(AoAWeapons.METEOR_STAFF.get()), 0, 9999));
	} */
}
