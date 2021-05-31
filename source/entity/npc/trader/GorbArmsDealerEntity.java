package net.tslat.aoa3.entity.npc.trader;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoAWeapons;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class GorbArmsDealerEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ITrade[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.trade(AoAItems.COPPER_COIN, 2).cost(Items.ARROW))
			.trades(2,
					BuildableTrade.trade(AoAItems.COPPER_COIN, 7).cost(AoAWeapons.RUNIC_BOMB).xp(5),
					BuildableTrade.trade(AoAWeapons.GRENADE, 5).cost(AoAItems.SILVER_COIN).xp(15))
			.trades(3,
					BuildableTrade.trade(AoAWeapons.LASER_BLASTER).cost(AoAItems.GOLD_COIN, 3).xp(50).stock(5),
					BuildableTrade.trade(AoAItems.WEAPON_PARTS).cost(AoAItems.GOLD_COIN, 10).xp(100).stock(1)).build();

	public GorbArmsDealerEntity(EntityType<? extends AoATrader> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 1.5f;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level, AoADimensions.MYSTERIUM.key);
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ITrade[]> getTradesMap() {
		return TRADES;
	}
}
