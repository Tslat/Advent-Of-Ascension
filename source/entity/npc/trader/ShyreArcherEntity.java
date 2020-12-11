package net.tslat.aoa3.entity.npc.trader;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoAWeapons;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.npc.AoATraderRecipe;

public class ShyreArcherEntity extends AoATrader {
	public ShyreArcherEntity(EntityType<? extends CreatureEntity> entityType, World world) {
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
	public boolean canDespawn(double distanceToClosestPlayer) {
		return world.getDimension().getType() != AoADimensions.SHYRELANDS.type();
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.SHYREGEM.get(), 7), new ItemStack(AoAItems.SHYRESTONE_INGOT.get(), 12), new ItemStack(AoAWeapons.SUNSHINE_BOW.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get(), 6), new ItemStack(Items.SPECTRAL_ARROW)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get(), 1), new ItemStack(Items.ARROW)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.SILVER_COIN.get(), 9), new ItemStack(AoAWeapons.ULTRA_CANNON.get(), 1), new ItemStack(AoAWeapons.GIGA_CANNON.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.SHYREGEM.get(), 2), new ItemStack(AoAItems.SHYRESTONE_INGOT.get(), 2), new ItemStack(AoAItems.ANCIENT_RING.get())));
	}
}
