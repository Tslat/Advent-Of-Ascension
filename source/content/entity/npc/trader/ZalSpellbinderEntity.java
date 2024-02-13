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
import org.jetbrains.annotations.Nullable;


public class ZalSpellbinderEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.forItem(AoAItems.LUNAR_RUNE).itemCost(AoAItems.COPPER_COIN).xp(1).stock(64),
					BuildableTrade.forItem(AoAItems.DISTORTION_RUNE).itemCost(AoAItems.COPPER_COIN).xp(1).stock(64),
					BuildableTrade.forItem(AoAItems.LIFE_RUNE).itemCost(AoAItems.COPPER_COIN).xp(1).stock(64),
					BuildableTrade.forItem(AoAItems.KINETIC_RUNE).itemCost(AoAItems.COPPER_COIN).xp(1).stock(64))
			.trades(2,
					BuildableTrade.forItem(AoATools.SOULSTONE_AXE).itemCost(AoAItems.GOLD_COIN, 10).xp(100).stock(3),
					BuildableTrade.forItem(AoATools.SOULSTONE_SHOVEL).itemCost(AoAItems.GOLD_COIN, 11).xp(100).stock(3),
					BuildableTrade.forItem(AoATools.SOULSTONE_PICKAXE).itemCost(AoAItems.GOLD_COIN, 12).xp(100).stock(3),
					BuildableTrade.forItem(AoAWeapons.ULTRA_CANNON).itemCost(AoAWeapons.SUPER_CANNON).itemCost(AoAItems.SILVER_COIN, 7),
					BuildableTrade.forStack(() -> EnchantedBookItem.createForEnchantment(new EnchantmentInstance(Enchantments.MENDING, 1))).itemCost(AoAItems.LUNAVER_COIN, 2).xp(150).stock(5)).build();

	public ZalSpellbinderEntity(EntityType<? extends AoATrader> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 0.6875f;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level(), AoADimensions.LUNALUS);
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ItemListing[]> getTradesMap() {
		return TRADES;
	}
}
