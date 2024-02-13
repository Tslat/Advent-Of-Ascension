package net.tslat.aoa3.common.registration.entity.variant;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.entity.base.AoATrader;
import net.tslat.aoa3.content.entity.npc.trader.UndeadHeraldEntity;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public record UndeadHeraldTrade(Predicate<UndeadHeraldEntity> isApplicable, VillagerTrades.ItemListing trade) {
    public UndeadHeraldTrade(ResourceKey<Level> dimKey, ItemLike banner) {
        this(herald -> herald.level().dimension() == dimKey, AoATrader.BuildableTrade.forItem(banner).itemCost(AoAItems.SILVER_COIN, 4).stock(729).xp(20).priceMultiplier(0.05f));
    }

    public static final DeferredHolder<UndeadHeraldTrade, UndeadHeraldTrade> SHADOW_BANNER = register("shadow_banner", () -> new UndeadHeraldTrade(AoADimensions.ABYSS, AoABlocks.SHADOW_BANNER.base));
    public static final DeferredHolder<UndeadHeraldTrade, UndeadHeraldTrade> BARON_BANNER = register("baron_banner", () -> new UndeadHeraldTrade(AoADimensions.BARATHOS, AoABlocks.BARON_BANNER.base));
    public static final DeferredHolder<UndeadHeraldTrade, UndeadHeraldTrade> CANDY_BANNER = register("candy_banner", () -> new UndeadHeraldTrade(AoADimensions.CANDYLAND, AoABlocks.CANDY_BANNER.base));
    public static final DeferredHolder<UndeadHeraldTrade, UndeadHeraldTrade> CLOWN_BANNER = register("clown_banner", () -> new UndeadHeraldTrade(AoADimensions.CELEVE, AoABlocks.CLOWN_BANNER.base));
    public static final DeferredHolder<UndeadHeraldTrade, UndeadHeraldTrade> CREEPY_BANNER = register("creepy_banner", () -> new UndeadHeraldTrade(AoADimensions.CREEPONIA, AoABlocks.CREEPY_BANNER.base));
    public static final DeferredHolder<UndeadHeraldTrade, UndeadHeraldTrade> CRYSTAL_BANNER = register("crystal_banner", () -> new UndeadHeraldTrade(AoADimensions.CRYSTEVIA, AoABlocks.CRYSTAL_BANNER.base));
    public static final DeferredHolder<UndeadHeraldTrade, UndeadHeraldTrade> DEEP_BANNER = register("deep_banner", () -> new UndeadHeraldTrade(AoADimensions.DEEPLANDS, AoABlocks.DEEP_BANNER.base));
    public static final DeferredHolder<UndeadHeraldTrade, UndeadHeraldTrade> DUSTOPIAN_BANNER = register("dustopian_banner", () -> new UndeadHeraldTrade(AoADimensions.DUSTOPIA, AoABlocks.DUSTOPIAN_BANNER.base));
    public static final DeferredHolder<UndeadHeraldTrade, UndeadHeraldTrade> ROSIDIAN_BANNER = register("rosidian_banner", () -> new UndeadHeraldTrade(AoADimensions.GARDENCIA, AoABlocks.ROSIDIAN_BANNER.base));
    public static final DeferredHolder<UndeadHeraldTrade, UndeadHeraldTrade> HAUNTED_BANNER = register("haunted_banner", () -> new UndeadHeraldTrade(AoADimensions.GRECKON, AoABlocks.HAUNTED_BANNER.base));
    public static final DeferredHolder<UndeadHeraldTrade, UndeadHeraldTrade> UTOPIAN_BANNER = register("utopian_banner", () -> new UndeadHeraldTrade(AoADimensions.HAVEN, AoABlocks.UTOPIAN_BANNER.base));
    public static final DeferredHolder<UndeadHeraldTrade, UndeadHeraldTrade> MECHA_BANNER = register("mecha_banner", () -> new UndeadHeraldTrade(AoADimensions.IROMINE, AoABlocks.MECHA_BANNER.base));
    public static final DeferredHolder<UndeadHeraldTrade, UndeadHeraldTrade> BOREIC_BANNER = register("boreic_banner", () -> new UndeadHeraldTrade(AoADimensions.LBOREAN, AoABlocks.BOREIC_BANNER.base));
    public static final DeferredHolder<UndeadHeraldTrade, UndeadHeraldTrade> LELYETIAN_BANNER = register("lelyetian_banner", () -> new UndeadHeraldTrade(AoADimensions.LELYETIA, AoABlocks.LELYETIAN_BANNER.base));
    public static final DeferredHolder<UndeadHeraldTrade, UndeadHeraldTrade> LUNAR_BANNER = register("lunar_banner", () -> new UndeadHeraldTrade(AoADimensions.LUNALUS, AoABlocks.LUNAR_BANNER.base));
    public static final DeferredHolder<UndeadHeraldTrade, UndeadHeraldTrade> FUNGAL_BANNER = register("fungal_banner", () -> new UndeadHeraldTrade(AoADimensions.MYSTERIUM, AoABlocks.FUNGAL_BANNER.base));
    public static final DeferredHolder<UndeadHeraldTrade, UndeadHeraldTrade> NETHER_BANNER = register("nether_banner", () -> new UndeadHeraldTrade(AoADimensions.NETHER, AoABlocks.NETHER_BANNER.base));
    public static final DeferredHolder<UndeadHeraldTrade, UndeadHeraldTrade> VOID_BANNER = register("void_banner", () -> new UndeadHeraldTrade(AoADimensions.OVERWORLD, AoABlocks.VOID_BANNER.base));
    public static final DeferredHolder<UndeadHeraldTrade, UndeadHeraldTrade> ANCIENT_BANNER = register("ancient_banner", () -> new UndeadHeraldTrade(AoADimensions.PRECASIA, AoABlocks.ANCIENT_BANNER.base));
    public static final DeferredHolder<UndeadHeraldTrade, UndeadHeraldTrade> RUNIC_BANNER = register("runic_banner", () -> new UndeadHeraldTrade(AoADimensions.RUNANDOR, AoABlocks.RUNIC_BANNER.base));
    public static final DeferredHolder<UndeadHeraldTrade, UndeadHeraldTrade> SHYRE_BANNER = register("shyre_banner", () -> new UndeadHeraldTrade(AoADimensions.SHYRELANDS, AoABlocks.SHYRE_BANNER.base));
    public static final DeferredHolder<UndeadHeraldTrade, UndeadHeraldTrade> VOX_BANNER = register("vox_banner", () -> new UndeadHeraldTrade(AoADimensions.VOX_PONDS, AoABlocks.VOX_BANNER.base));

    private static DeferredHolder<UndeadHeraldTrade, UndeadHeraldTrade> register(String id, Supplier<UndeadHeraldTrade> trade) {
        return AoARegistries.UNDEAD_HERALD_TRADES.register(id, trade);
    }

    public static List<MerchantOffer> getExtraTradesFor(UndeadHeraldEntity herald) {
        return AoARegistries.UNDEAD_HERALD_TRADES.getAllRegisteredObjects().filter(trade -> trade.isApplicable().test(herald)).map(UndeadHeraldTrade::trade).map(listing -> listing.getOffer(herald, herald.getRandom())).toList();
    }

    public static void init() {}
}
