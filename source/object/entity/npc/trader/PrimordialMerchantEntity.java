package net.tslat.aoa3.object.entity.npc.trader;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.*;
import net.tslat.aoa3.object.entity.base.AoATrader;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class PrimordialMerchantEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ITrade[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.trade(Items.COOKED_BEEF).cost(AoAItems.COPPER_COIN, 8).xp(5).stock(32),
					BuildableTrade.trade(Blocks.TORCH, 2).cost(AoAItems.COPPER_COIN).xp(1),
					BuildableTrade.trade(AoAItems.LIMONITE_BULLET, 4).cost(AoAItems.COPPER_COIN, 14).xp(9))
			.trades(4,
					BuildableTrade.trade(AoAWeapons.DAYBREAKER_BOW).cost(AoAItems.DARKLY_POWDER, 7).xp(40).stock(5),
					BuildableTrade.trade(AoAArmour.PRIMORDIAL_ARMOUR.helmet).cost(AoAItems.DARKLY_POWDER, 2).cost(AoAItems.SILVER_COIN, 2).xp(50).stock(5),
					BuildableTrade.trade(AoAArmour.PRIMORDIAL_ARMOUR.chestplate).cost(AoAItems.DARKLY_POWDER, 3).cost(AoAItems.SILVER_COIN, 3).xp(50).stock(5),
					BuildableTrade.trade(AoAArmour.PRIMORDIAL_ARMOUR.leggings).cost(AoAItems.DARKLY_POWDER, 2).cost(AoAItems.SILVER_COIN, 3).xp(50).stock(5),
					BuildableTrade.trade(AoAArmour.PRIMORDIAL_ARMOUR.boots).cost(AoAItems.DARKLY_POWDER, 2).cost(AoAItems.SILVER_COIN, 2).xp(50).stock(5)).build();

	public PrimordialMerchantEntity(EntityType<? extends AoATrader> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 1.73125f;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level, AoADimensions.DUSTOPIA.key);
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ITrade[]> getTradesMap() {
		return TRADES;
	}
}
