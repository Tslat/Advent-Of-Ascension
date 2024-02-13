package net.tslat.aoa3.content.entity.npc.trader;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.item.AoAWeapons;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.entity.base.AoATrader;
import net.tslat.aoa3.util.WorldUtil;
import org.jetbrains.annotations.Nullable;


public class GorbArmsDealerEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 2).itemCost(Items.ARROW))
			.trades(2,
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 7).itemCost(AoAWeapons.RUNIC_BOMB).xp(5),
					BuildableTrade.forItem(AoAWeapons.GRENADE, 5).itemCost(AoAItems.SILVER_COIN).xp(15))
			.trades(3,
					BuildableTrade.forItem(AoAWeapons.LASER_BLASTER).itemCost(AoAItems.GOLD_COIN, 3).xp(50).stock(5),
					BuildableTrade.forItem(AoAItems.WEAPON_PARTS).itemCost(AoAItems.GOLD_COIN, 10).xp(100).stock(1)).build();

	public GorbArmsDealerEntity(EntityType<? extends AoATrader> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 1.5f;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level(), AoADimensions.MYSTERIUM);
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ItemListing[]> getTradesMap() {
		return TRADES;
	}
}
