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


public class PrimordialWizardEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.trade(AoAItems.COPPER_COIN, 15).cost(AoAItems.PRIMORDIAL_SKULL).xp(12))
			.trades(2,
					BuildableTrade.trade(AoAWeapons.HELLFIRE, 2).cost(AoAItems.COPPER_COIN, 4),
					BuildableTrade.trade(AoAWeapons.VULKRAM, 2).cost(AoAItems.COPPER_COIN, 3))
			.trades(3,
					BuildableTrade.trade(AoAWeapons.PRIMORDIAL_STAFF).cost(AoAItems.GOLD_COIN, 2).xp(50).stock(5),
					BuildableTrade.trade(Items.DRAGON_BREATH).cost(AoAItems.LUNAVER_COIN).xp(100).stock(3)).build();

	public PrimordialWizardEntity(EntityType<? extends AoATrader> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 1.73125f;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level(), AoADimensions.DUSTOPIA.key);
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ItemListing[]> getTradesMap() {
		return TRADES;
	}
}
