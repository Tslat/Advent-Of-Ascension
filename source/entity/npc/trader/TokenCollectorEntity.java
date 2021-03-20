package net.tslat.aoa3.entity.npc.trader;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoAWeapons;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.npc.AoATraderRecipe;
import net.tslat.aoa3.util.WorldUtil;

public class TokenCollectorEntity extends AoATrader {
	public TokenCollectorEntity(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);

		this.setInvulnerable(true);
	}

	@Override
	protected boolean isFixedTradesList() {
		return true;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level, AoADimensions.IMMORTALLIS.key);
	}

	@Override
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
	}
}
