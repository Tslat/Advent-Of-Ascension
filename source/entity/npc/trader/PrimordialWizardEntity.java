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

public class PrimordialWizardEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ITrade[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.trade(AoAItems.COPPER_COIN, 15).cost(AoAItems.PRIMORDIAL_SKULL).xp(12))
			.trades(2,
					BuildableTrade.trade(AoAWeapons.HELLFIRE, 2).cost(AoAItems.COPPER_COIN, 4),
					BuildableTrade.trade(AoAWeapons.VULKRAM, 2).cost(AoAItems.COPPER_COIN, 3))
			.trades(3,
					BuildableTrade.trade(AoAWeapons.PRIMORDIAL_STAFF).cost(AoAItems.GOLD_COIN, 2).xp(50).stock(5),
					BuildableTrade.trade(Items.DRAGON_BREATH).cost(AoAItems.LUNAVER_COIN).xp(100).stock(3)).build();

	public PrimordialWizardEntity(EntityType<? extends AoATrader> entityType, World world) {
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
