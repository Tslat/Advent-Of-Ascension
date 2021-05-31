package net.tslat.aoa3.entity.npc.trader;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.util.PotionUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class StoreKeeperEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ITrade[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.trade(AoABlocks.VOX_GLASS, 32).cost(AoAItems.GOLD_COIN).xp(25),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 16).cost(Items.POISONOUS_POTATO).xp(10),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 8).cost(AoAItems.TOXIC_LUMP).xp(5))
			.trades(3,
					BuildableTrade.trade(poisonPotionStack()).cost(AoAItems.SILVER_COIN, 4).xp(20)).build();

	public StoreKeeperEntity(EntityType<? extends AoATrader> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level, AoADimensions.VOX_PONDS.key);
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ITrade[]> getTradesMap() {
		return TRADES;
	}

	public static ItemStack poisonPotionStack() {
		return new PotionUtil.PotionBuilder(Items.LINGERING_POTION).addEffect(new PotionUtil.EffectBuilder(Effects.POISON, 600).build()).withTranslationKey("item.minecraft.lingering_potion.effect.poison").build();
	}
}
