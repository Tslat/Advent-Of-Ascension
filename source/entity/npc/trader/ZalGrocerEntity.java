package net.tslat.aoa3.entity.npc.trader;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.npc.AoATraderRecipe;
import net.tslat.aoa3.util.WorldUtil;

public class ZalGrocerEntity extends AoATrader {
	public ZalGrocerEntity(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 0.6875f;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level, AoADimensions.LUNALUS.key);
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get(), 1), new ItemStack(Items.CARROT, 3)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get(), 1), new ItemStack(Items.POTATO, 2)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get(), 1), new ItemStack(Items.MELON_SLICE, 4)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get(), 3), new ItemStack(Items.COD)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get(), 3), new ItemStack(Items.SALMON)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get(), 1), new ItemStack(Items.BEETROOT, 2)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get(), 1), new ItemStack(Items.COOKIE, 3)));
	}
}
