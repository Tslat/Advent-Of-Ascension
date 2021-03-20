package net.tslat.aoa3.entity.npc.trader;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoAWeapons;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.npc.AoATraderRecipe;
import net.tslat.aoa3.util.WorldUtil;

public class CrystalTraderEntity extends AoATrader {
	public CrystalTraderEntity(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected boolean isFixedTradesList() {
		return true;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level, AoADimensions.CRYSTEVIA.key);
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.BLUE_CRYSTAL.get(), 1), ItemStack.EMPTY, new ItemStack(AoAItems.CRYSTEVIA_TOKENS.get(), 3), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.GREEN_CRYSTAL.get(), 1), ItemStack.EMPTY, new ItemStack(AoAItems.CRYSTEVIA_TOKENS.get(), 3), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.PURPLE_CRYSTAL.get(), 1), ItemStack.EMPTY, new ItemStack(AoAItems.CRYSTEVIA_TOKENS.get(), 3), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.RED_CRYSTAL.get(), 1), ItemStack.EMPTY, new ItemStack(AoAItems.CRYSTEVIA_TOKENS.get(), 3), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.WHITE_CRYSTAL.get(), 1), ItemStack.EMPTY, new ItemStack(AoAItems.CRYSTEVIA_TOKENS.get(), 3), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.YELLOW_CRYSTAL.get(), 1), ItemStack.EMPTY, new ItemStack(AoAItems.CRYSTEVIA_TOKENS.get(), 3), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.BLUE_CRYSTAL.get(), 16), ItemStack.EMPTY, new ItemStack(AoAItems.BLUE_DRUSE.get()), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.GREEN_CRYSTAL.get(), 16), ItemStack.EMPTY, new ItemStack(AoAItems.GREEN_DRUSE.get()), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.PURPLE_CRYSTAL.get(), 16), ItemStack.EMPTY, new ItemStack(AoAItems.PURPLE_DRUSE.get()), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.RED_CRYSTAL.get(), 16), ItemStack.EMPTY, new ItemStack(AoAItems.RED_DRUSE.get()), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.WHITE_CRYSTAL.get(), 16), ItemStack.EMPTY, new ItemStack(AoAItems.WHITE_DRUSE.get()), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.YELLOW_CRYSTAL.get(), 16), ItemStack.EMPTY, new ItemStack(AoAItems.YELLOW_DRUSE.get()), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.RAINBOW_DRUSE.get(), 12), ItemStack.EMPTY, new ItemStack(AoAWeapons.CRYSTAL_MAUL.get()), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.RAINBOW_DRUSE.get(), 12), ItemStack.EMPTY, new ItemStack(AoAWeapons.CRYSTAL_GREATBLADE.get()), 0, 9999));
	}
}
