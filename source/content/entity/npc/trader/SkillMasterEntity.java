package net.tslat.aoa3.content.entity.npc.trader;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.base.AoATrader;
import software.bernie.geckolib3.core.manager.AnimationData;

import javax.annotation.Nullable;

public class SkillMasterEntity extends AoATrader {
	private static final EntityDataAccessor<Boolean> TRADING = SynchedEntityData.defineId(SkillMasterEntity.class, EntityDataSerializers.BOOLEAN);

	private boolean trading = false;

	private static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.trade(AoAItems.SMALL_SKILL_CRYSTAL).cost(AoAItems.SILVER_COIN).xp(150).stock(32),
					BuildableTrade.trade(AoAItems.MEDIUM_SKILL_CRYSTAL).cost(AoAItems.GOLD_COIN).xp(150).stock(24),
					BuildableTrade.trade(AoAItems.LARGE_SKILL_CRYSTAL).cost(AoAItems.GOLD_COIN, 12).xp(150).stock(16),
					BuildableTrade.trade(AoAItems.GIANT_SKILL_CRYSTAL).cost(AoAItems.LUNAVER_COIN).xp(150).stock(12)).build();

	public SkillMasterEntity(EntityType<? extends AoATrader> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();

		getEntityData().define(TRADING, false);
	}

	@Override
	public int getMaxTradesToUnlock(int newProfessionLevel) {
		return 4;
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ItemListing[]> getTradesMap() {
		return TRADES;
	}

	@Override
	public void setTradingPlayer(@Nullable Player player) {
		super.setTradingPlayer(player);

		this.trading = isTrading();
		getEntityData().set(TRADING, this.trading);
	}

	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
		super.onSyncedDataUpdated(key);

		if (key.equals(TRADING) && level.isClientSide()) {
			this.trading = getEntityData().get(TRADING);
		}
	}

	@Override
	public void registerControllers(AnimationData animationData) {
		animationData.addAnimationController(AoAAnimations.genericIdleController(this));
		animationData.addAnimationController(AoAAnimations.genericHeldPoseController(this, AoAAnimations.INTERACT, AoAAnimations.INTERACT_END, entity -> this.trading));
	}
}
