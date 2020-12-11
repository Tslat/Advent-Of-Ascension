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

public class CrystalTraderEntity extends AoATrader {
	public CrystalTraderEntity(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);
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
	public boolean canDespawn(double distanceToClosestPlayer) {
		return world.getDimension().getType() != AoADimensions.CRYSTEVIA.type();
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
