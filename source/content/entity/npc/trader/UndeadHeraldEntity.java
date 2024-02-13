package net.tslat.aoa3.content.entity.npc.trader;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidType;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.entity.variant.UndeadHeraldTrade;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.base.AoATrader;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;


public class UndeadHeraldEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.forItem(AoABlocks.ENERGY_BANNER.base()).itemCost(AoAItems.SILVER_COIN, 4).xp(20).stock(729),
					BuildableTrade.forItem(AoABlocks.SPIRIT_BANNER.base()).itemCost(AoAItems.SILVER_COIN, 4).xp(20).stock(729),
					BuildableTrade.forItem(AoABlocks.RAGE_BANNER.base()).itemCost(AoAItems.SILVER_COIN, 4).xp(20).stock(729)).build();

	public UndeadHeraldEntity(EntityType<? extends AoATrader> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 1.5625f;
	}

	@Override
	public boolean canDrownInFluidType(FluidType type) {
		return false;
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEAD;
	}

	@Nullable
	@Override
	public SoundEvent getAmbientSound() {
		return SoundEvents.SKELETON_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource) {
		return SoundEvents.SKELETON_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.SKELETON_DEATH;
	}

	@Override
	public SoundEvent getNotifyTradeSound() {
		return SoundEvents.SKELETON_AMBIENT;
	}

	@Override
	public void notifyTradeUpdated(ItemStack stack) {
		if (!level().isClientSide && this.ambientSoundTime > -getAmbientSoundInterval() + 20)
			this.ambientSoundTime = -getAmbientSoundInterval();
	}

	@Override
	public void playCelebrateSound() {}

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
		Int2ObjectMap<VillagerTrades.ItemListing[]> trades = getTradesMap();

		if (trades != null && !trades.isEmpty() && getVillagerData().getLevel() == 1) {
			MerchantOffers offers = getOffers();

			addOffersFromItemListings(offers, trades.get(1), getMaxTradesToUnlock(getVillagerData().getLevel()));
			offers.addAll(UndeadHeraldTrade.getExtraTradesFor(this));
		}
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ItemListing[]> getTradesMap() {
		return TRADES;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(DefaultAnimations.genericWalkIdleController(this).setAnimationSpeed(1.35f));
		// TODO Greet anim
	}
}
