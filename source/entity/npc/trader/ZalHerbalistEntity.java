package net.tslat.aoa3.entity.npc.trader;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.npc.AoATraderRecipe;
import net.tslat.aoa3.util.WorldUtil;

public class ZalHerbalistEntity extends AoATrader {
	public ZalHerbalistEntity(EntityType<? extends CreatureEntity> entityType, World world) {
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
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.SILVER_COIN.get(), 5), new ItemStack(AoAItems.LUNACRIKE_SEEDS.get(), 5)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.SILVER_COIN.get(), 5), new ItemStack(AoAItems.LUNA_GLOBE_SEEDS.get(), 5)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.SILVER_COIN.get(), 5), new ItemStack(AoAItems.LUNALON_SEEDS.get(), 5)));
	}
}
