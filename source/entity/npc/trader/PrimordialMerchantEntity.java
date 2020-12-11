package net.tslat.aoa3.entity.npc.trader;

import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAArmour;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoAWeapons;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.npc.AoATraderRecipe;

public class PrimordialMerchantEntity extends AoATrader {
	public PrimordialMerchantEntity(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 1.73125f;
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
		return world.getDimension().getType() != AoADimensions.DUSTOPIA.type();
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.DARKLY_POWDER.get(), 7), new ItemStack(AoAWeapons.DAYBREAKER_BOW.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get(), 8), new ItemStack(Items.BEEF)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get()), new ItemStack(Blocks.TORCH, 2)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get(), 14), new ItemStack(AoAItems.LIMONITE_BULLET.get(), 4)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.DARKLY_POWDER.get(), 2), new ItemStack(AoAItems.SILVER_COIN.get(), 2), new ItemStack(AoAArmour.PRIMORDIAL_ARMOUR.helmet.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.DARKLY_POWDER.get(), 3), new ItemStack(AoAItems.SILVER_COIN.get(), 3), new ItemStack(AoAArmour.PRIMORDIAL_ARMOUR.chestplate.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.DARKLY_POWDER.get(), 2), new ItemStack(AoAItems.SILVER_COIN.get(), 3), new ItemStack(AoAArmour.PRIMORDIAL_ARMOUR.leggings.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.DARKLY_POWDER.get(), 2), new ItemStack(AoAItems.SILVER_COIN.get(), 2), new ItemStack(AoAArmour.PRIMORDIAL_ARMOUR.boots.get())));
	}
}
