package net.tslat.aoa3.entity.npc.trader;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MerchantOffer;
import net.minecraft.item.MerchantOffers;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class UndeadHeraldEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ITrade[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.trade(AoABlocks.CREATION_BANNER).cost(AoAItems.SILVER_COIN, 4).xp(20).stock(9),
					BuildableTrade.trade(AoABlocks.ENERGY_BANNER).cost(AoAItems.SILVER_COIN, 4).xp(20).stock(9),
					BuildableTrade.trade(AoABlocks.SOUL_BANNER).cost(AoAItems.SILVER_COIN, 4).xp(20).stock(9),
					BuildableTrade.trade(AoABlocks.BLOOD_BANNER).cost(AoAItems.SILVER_COIN, 4).xp(20).stock(9)).build();

	public UndeadHeraldEntity(EntityType<? extends AoATrader> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public boolean canBreatheUnderwater() {
		return WorldUtil.isWorld(level, AoADimensions.LBOREAN.key);
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return tickCount > 72000;
	}

	@Override
	public int getMaxTradesToUnlock(int newProfessionLevel) {
		return newProfessionLevel == 1 ? 4 : 2;
	}

	@Override
	protected void updateTrades() {
		Int2ObjectMap<VillagerTrades.ITrade[]> trades = getTradesMap();

		if (trades != null && !trades.isEmpty() && getVillagerData().getLevel() == 1) {
			MerchantOffers offers = getOffers();
			MerchantOffer additionalOffer = getAdditionalBannerTrade(level);

			addOffersFromItemListings(offers, trades.get(1), getMaxTradesToUnlock(getVillagerData().getLevel()));

			if (additionalOffer != null)
				offers.add(additionalOffer);
		}
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ITrade[]> getTradesMap() {
		return TRADES;
	}

	public MerchantOffer getAdditionalBannerTrade(World world) {
		AoADimensions.AoADimension dim = AoADimensions.getDim(world.dimension());

		if (dim == null)
			return null;

		switch (dim) {
			case ABYSS:
				return new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(),  4), new ItemStack(AoABlocks.SHADOW_BANNER.get()), 9, 20, 0.05f);
			case BARATHOS:
				return new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(),  4), new ItemStack(AoABlocks.BARON_BANNER.get()), 9, 20, 0.05f);
			case CANDYLAND:
				return new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(),  4), new ItemStack(AoABlocks.CANDY_BANNER.get()), 9, 20, 0.05f);
			case CELEVE:
				return new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(),  4), new ItemStack(AoABlocks.CLOWN_BANNER.get()), 9, 20, 0.05f);
			case CREEPONIA:
				return new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(),  4), new ItemStack(AoABlocks.CREEPY_BANNER.get()), 9, 20, 0.05f);
			case CRYSTEVIA:
				return new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(),  4), new ItemStack(AoABlocks.CRYSTAL_BANNER.get()), 9, 20, 0.05f);
			case DEEPLANDS:
				return new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(),  4), new ItemStack(AoABlocks.DEEP_BANNER.get()), 9, 20, 0.05f);
			case DUSTOPIA:
				return new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(),  4), new ItemStack(AoABlocks.DUSTOPIAN_BANNER.get()), 9, 20, 0.05f);
			case GARDENCIA:
				return new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(),  4), new ItemStack(AoABlocks.ROSIDIAN_BANNER.get()), 9, 20, 0.05f);
			case GRECKON:
				return new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(),  4), new ItemStack(AoABlocks.HAUNTED_BANNER.get()), 9, 20, 0.05f);
			case HAVEN:
				return new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(),  4), new ItemStack(AoABlocks.UTOPIAN_BANNER.get()), 9, 20, 0.05f);
			case IROMINE:
				return new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(),  4), new ItemStack(AoABlocks.MECHA_BANNER.get()), 9, 20, 0.05f);
			case LELYETIA:
				return new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(),  4), new ItemStack(AoABlocks.LELYETIAN_BANNER.get()), 9, 20, 0.05f);
			case LUNALUS:
				return new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(),  4), new ItemStack(AoABlocks.LUNAR_BANNER.get()), 9, 20, 0.05f);
			case MYSTERIUM:
				return new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(),  4), new ItemStack(AoABlocks.FUNGAL_BANNER.get()), 9, 20, 0.05f);
			case NETHER:
				return new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(),  4), new ItemStack(AoABlocks.NETHER_BANNER.get()), 9, 20, 0.05f);
			case OVERWORLD:
				return new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(),  4), new ItemStack(AoABlocks.VOID_BANNER.get()), 9, 20, 0.05f);
			case PRECASIA:
				return new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(),  4), new ItemStack(AoABlocks.ANCIENT_BANNER.get()), 9, 20, 0.05f);
			case RUNANDOR:
				return new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(),  4), new ItemStack(AoABlocks.RUNIC_BANNER.get()), 9, 20, 0.05f);
			case SHYRELANDS:
				return new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(),  4), new ItemStack(AoABlocks.SHYRE_BANNER.get()), 9, 20, 0.05f);
			case VOX_PONDS:
				return new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(),  4), new ItemStack(AoABlocks.VOX_BANNER.get()), 9, 20, 0.05f);
			case NOWHERE:
			default:
				return null;
		}
	}
}
