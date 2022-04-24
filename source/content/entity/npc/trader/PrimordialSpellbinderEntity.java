package net.tslat.aoa3.content.entity.npc.trader;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoATools;
import net.tslat.aoa3.content.entity.base.AoATrader;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class PrimordialSpellbinderEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ITrade[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.trade(AoAItems.STORM_RUNE).cost(AoAItems.COPPER_COIN).xp(1).stock(64),
					BuildableTrade.trade(AoAItems.POWER_RUNE).cost(AoAItems.COPPER_COIN).xp(1).stock(64),
					BuildableTrade.trade(AoAItems.WITHER_RUNE).cost(AoAItems.COPPER_COIN).xp(1).stock(64),
					BuildableTrade.trade(AoAItems.COMPASS_RUNE).cost(AoAItems.COPPER_COIN).xp(1).stock(64))
			.trades(3,
					BuildableTrade.trade(AoAItems.BATTLE_VULCANE_AUGMENT).cost(AoAItems.SILVER_COIN, 5).xp(50).stock(10),
					BuildableTrade.trade(AoAItems.EQUALITY_VULCANE_AUGMENT).cost(AoAItems.SILVER_COIN, 5).xp(50).stock(10),
					BuildableTrade.trade(AoAItems.FIRE_VULCANE_AUGMENT).cost(AoAItems.SILVER_COIN, 5).xp(50).stock(10),
					BuildableTrade.trade(AoAItems.IMPAIRMENT_VULCANE_AUGMENT).cost(AoAItems.SILVER_COIN, 5).xp(50).stock(10),
					BuildableTrade.trade(AoAItems.POWER_VULCANE_AUGMENT).cost(AoAItems.SILVER_COIN, 5).xp(50).stock(10),
					BuildableTrade.trade(AoAItems.WITHER_VULCANE_AUGMENT).cost(AoAItems.SILVER_COIN, 5).xp(50).stock(10),
					BuildableTrade.trade(AoAItems.POISON_VULCANE_AUGMENT).cost(AoAItems.SILVER_COIN, 5).xp(50).stock(10))
			.trades(4,
					BuildableTrade.trade(AoATools.ENERGISTIC_AXE).cost(AoAItems.GOLD_COIN, 8).xp(100).stock(3),
					BuildableTrade.trade(AoATools.ENERGISTIC_PICKAXE).cost(AoAItems.GOLD_COIN, 10).xp(100).stock(3),
					BuildableTrade.trade(AoATools.ENERGISTIC_SHOVEL).cost(AoAItems.GOLD_COIN, 9).xp(100).stock(3),
					BuildableTrade.trade(EnchantedBookItem.createForEnchantment(new EnchantmentData(Enchantments.SILK_TOUCH, 1))).cost(AoAItems.LUNAVER_COIN).xp(150).stock(5)).build();

	public PrimordialSpellbinderEntity(EntityType<? extends AoATrader> entityType, World world) {
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
