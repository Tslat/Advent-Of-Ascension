package net.tslat.aoa3.content.entity.npc.trader;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.common.registration.item.AoAArmour;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.item.AoAWeapons;
import net.tslat.aoa3.content.entity.base.AoATrader;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class PrimordialMerchantEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.trade(Items.COOKED_BEEF).cost(AoAItems.COPPER_COIN, 8).xp(5).stock(32),
					BuildableTrade.trade(Blocks.TORCH, 2).cost(AoAItems.COPPER_COIN).xp(1),
					BuildableTrade.trade(AoAItems.LIMONITE_BULLET, 30).cost(AoAItems.COPPER_COIN, 14).xp(9))
			.trades(4,
					BuildableTrade.trade(AoAWeapons.DAYBREAKER_BOW).cost(AoAItems.DARKLY_POWDER, 7).xp(40).stock(5),
					BuildableTrade.trade(AoAArmour.PRIMORDIAL_ARMOUR.helmet).cost(AoAItems.DARKLY_POWDER, 2).cost(AoAItems.SILVER_COIN, 2).xp(50).stock(5),
					BuildableTrade.trade(AoAArmour.PRIMORDIAL_ARMOUR.chestplate).cost(AoAItems.DARKLY_POWDER, 3).cost(AoAItems.SILVER_COIN, 3).xp(50).stock(5),
					BuildableTrade.trade(AoAArmour.PRIMORDIAL_ARMOUR.leggings).cost(AoAItems.DARKLY_POWDER, 2).cost(AoAItems.SILVER_COIN, 3).xp(50).stock(5),
					BuildableTrade.trade(AoAArmour.PRIMORDIAL_ARMOUR.boots).cost(AoAItems.DARKLY_POWDER, 2).cost(AoAItems.SILVER_COIN, 2).xp(50).stock(5)).build();

	public PrimordialMerchantEntity(EntityType<? extends AoATrader> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 1.73125f;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level, AoADimensions.DUSTOPIA.key);
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ItemListing[]> getTradesMap() {
		return TRADES;
	}
}
