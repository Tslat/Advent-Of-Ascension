package net.tslat.aoa3.content.entity.npc.trader;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.item.AoAWeapons;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.entity.base.AoATrader;
import net.tslat.aoa3.util.WorldUtil;
import org.jetbrains.annotations.Nullable;


public class ProfessorEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.forItem(AoAItems.SCRAP_METAL).itemCost(AoAItems.COPPER_COIN, 15).xp(11))
			.trades(2,
					BuildableTrade.forItem(AoAItems.DISCHARGE_CAPSULE, 8).itemCost(AoAItems.SILVER_COIN).itemCost(Items.IRON_NUGGET, 5).xp(20))
			.trades(3,
					BuildableTrade.forItem(AoAItems.MECHA_GEAR).itemCost(AoAItems.GOLD_COIN).itemCost(AoAItems.LYON_INGOT, 5).xp(30),
					BuildableTrade.forItem(AoAItems.GOLD_SPRING).itemCost(AoABlocks.IRO_CRATE).xp(30),
					BuildableTrade.forItem(AoAWeapons.SUPER_CANNON).itemCost(AoAWeapons.MINI_CANNON).itemCost(AoAItems.SILVER_COIN, 5).xp(40).stock(9999),
					BuildableTrade.forItem(AoAWeapons.DEMOLISHER).itemCost(AoAItems.LYON_INGOT, 7).itemCost(AoAItems.SILVER_COIN, 17).xp(25).stock(5)).build();

	public ProfessorEntity(EntityType<? extends AoATrader> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 1.7625f;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level(), AoADimensions.IROMINE);
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ItemListing[]> getTradesMap() {
		return TRADES;
	}
}
