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
import net.tslat.aoa3.common.registration.item.AoAWeapons;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.entity.base.AoATrader;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class ZalSpellbinderEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.trade(AoAItems.LUNAR_RUNE).cost(AoAItems.COPPER_COIN).xp(1).stock(64),
					BuildableTrade.trade(AoAItems.DISTORTION_RUNE).cost(AoAItems.COPPER_COIN).xp(1).stock(64),
					BuildableTrade.trade(AoAItems.LIFE_RUNE).cost(AoAItems.COPPER_COIN).xp(1).stock(64),
					BuildableTrade.trade(AoAItems.KINETIC_RUNE).cost(AoAItems.COPPER_COIN).xp(1).stock(64))
			.trades(2,
					BuildableTrade.trade(AoATools.SOULSTONE_AXE).cost(AoAItems.GOLD_COIN, 10).xp(100).stock(3),
					BuildableTrade.trade(AoATools.SOULSTONE_SHOVEL).cost(AoAItems.GOLD_COIN, 11).xp(100).stock(3),
					BuildableTrade.trade(AoATools.SOULSTONE_PICKAXE).cost(AoAItems.GOLD_COIN, 12).xp(100).stock(3),
					BuildableTrade.trade(AoAWeapons.ULTRA_CANNON).cost(AoAWeapons.SUPER_CANNON).cost(AoAItems.SILVER_COIN, 7),
					BuildableTrade.trade(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(Enchantments.MENDING, 1))).cost(AoAItems.LUNAVER_COIN, 2).xp(150).stock(5)).build();

	public ZalSpellbinderEntity(EntityType<? extends AoATrader> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 0.6875f;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level, AoADimensions.LUNALUS.key);
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ItemListing[]> getTradesMap() {
		return TRADES;
	}
}
