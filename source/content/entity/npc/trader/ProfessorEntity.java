package net.tslat.aoa3.content.entity.npc.trader;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.*;
import net.tslat.aoa3.content.entity.base.AoATrader;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class ProfessorEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ITrade[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.trade(AoAItems.SCRAP_METAL).cost(AoAItems.COPPER_COIN, 15).xp(11),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 5).cost(Items.IRON_INGOT).xp(18).stock(9999))
			.trades(2,
					BuildableTrade.trade(AoAItems.DISCHARGE_CAPSULE, 8).cost(AoAItems.SILVER_COIN).cost(Items.IRON_NUGGET, 5).xp(20),
					BuildableTrade.trade(AoAItems.SILVER_COIN, 1).cost(Items.GOLD_INGOT).xp(25).stock(9999))
			.trades(3,
					BuildableTrade.trade(AoAItems.MECHA_GEAR).cost(AoAItems.GOLD_COIN).cost(AoAItems.LYON_INGOT, 5).xp(30),
					BuildableTrade.trade(AoAItems.GOLD_SPRING).cost(AoABlocks.IRO_CRATE).xp(30),
					BuildableTrade.trade(AoAWeapons.SUPER_CANNON).cost(AoAWeapons.MINI_CANNON).cost(AoAItems.SILVER_COIN, 5).xp(40).stock(9999),
					BuildableTrade.trade(AoAWeapons.DEMOLISHER).cost(AoAItems.LYON_INGOT, 7).cost(AoAItems.SILVER_COIN, 17).xp(25).stock(5))
			.trades(4,
					BuildableTrade.trade(AoATools.EXP_FLASK).cost(AoAItems.GOLD_COIN, 5).xp(100)).build();

	public ProfessorEntity(EntityType<? extends AoATrader> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 1.7625f;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level, AoADimensions.IROMINE.key);
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ITrade[]> getTradesMap() {
		return TRADES;
	}
}
