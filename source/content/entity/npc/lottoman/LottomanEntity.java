package net.tslat.aoa3.content.entity.npc.lottoman;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.base.AoATrader;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;


public class LottomanEntity extends AoATrader {
	protected static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.forItem(AoAItems.WEAPONS_CASE).itemCost(AoAItems.COPPER_COIN, 31).xp(12),
					BuildableTrade.forItem(AoAItems.RUNE_BOX).itemCost(AoAItems.COPPER_COIN, 14).xp(8),
					BuildableTrade.forItem(AoAItems.TREASURE_BOX).itemCost(AoAItems.COPPER_COIN, 41).xp(20),
					BuildableTrade.forItem(AoAItems.LOTTO_TOTEM).itemCost(AoAItems.SILVER_COIN, 3).xp(25)).build();

	public LottomanEntity(EntityType<? extends AoATrader> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected boolean isOverworldNPC() {
		return true;
	}

	@Override
	public int getMaxTradesToUnlock(int newProfessionLevel) {
		return newProfessionLevel == 1 ? 4 : 2;
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ItemListing[]> getTradesMap() {
		return TRADES;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(DefaultAnimations.genericWalkController(this));
	}
}
