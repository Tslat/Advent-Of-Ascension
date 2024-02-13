package net.tslat.aoa3.content.entity.npc.trader;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.item.AoATools;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.entity.base.AoATrader;
import net.tslat.aoa3.util.WorldUtil;
import org.jetbrains.annotations.Nullable;


public class PrimordialSpellbinderEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.forItem(AoAItems.STORM_RUNE).itemCost(AoAItems.COPPER_COIN).xp(1).stock(64),
					BuildableTrade.forItem(AoAItems.POWER_RUNE).itemCost(AoAItems.COPPER_COIN).xp(1).stock(64),
					BuildableTrade.forItem(AoAItems.WITHER_RUNE).itemCost(AoAItems.COPPER_COIN).xp(1).stock(64),
					BuildableTrade.forItem(AoAItems.COMPASS_RUNE).itemCost(AoAItems.COPPER_COIN).xp(1).stock(64))
			.trades(4,
					BuildableTrade.forItem(AoATools.ENERGISTIC_AXE).itemCost(AoAItems.GOLD_COIN, 8).xp(100).stock(3),
					BuildableTrade.forItem(AoATools.ENERGISTIC_PICKAXE).itemCost(AoAItems.GOLD_COIN, 10).xp(100).stock(3),
					BuildableTrade.forItem(AoATools.ENERGISTIC_SHOVEL).itemCost(AoAItems.GOLD_COIN, 9).xp(100).stock(3),
					BuildableTrade.forStack(() -> EnchantedBookItem.createForEnchantment(new EnchantmentInstance(Enchantments.SILK_TOUCH, 1))).itemCost(AoAItems.LUNAVER_COIN).xp(150).stock(5)).build();

	public PrimordialSpellbinderEntity(EntityType<? extends AoATrader> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 1.73125f;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level(), AoADimensions.DUSTOPIA);
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ItemListing[]> getTradesMap() {
		return TRADES;
	}
}
