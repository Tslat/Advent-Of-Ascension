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
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.entity.base.AoATrader;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;

import javax.annotation.Nullable;

public class UndeadHeraldEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.trade(AoABlocks.ENERGY_BANNER.base()).cost(AoAItems.SILVER_COIN, 4).xp(20).stock(729),
					BuildableTrade.trade(AoABlocks.SPIRIT_BANNER.base()).cost(AoAItems.SILVER_COIN, 4).xp(20).stock(729),
					BuildableTrade.trade(AoABlocks.RAGE_BANNER.base()).cost(AoAItems.SILVER_COIN, 4).xp(20).stock(729)).build();

	public UndeadHeraldEntity(EntityType<? extends AoATrader> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 1.5625f;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
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
			MerchantOffer additionalOffer = getAdditionalBannerTrade(level());

			addOffersFromItemListings(offers, trades.get(1), getMaxTradesToUnlock(getVillagerData().getLevel()));

			if (additionalOffer != null)
				offers.add(additionalOffer);
		}
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ItemListing[]> getTradesMap() {
		return TRADES;
	}

	public MerchantOffer getAdditionalBannerTrade(Level world) {
		AoADimensions.AoADimension dim = AoADimensions.getDim(world.dimension());

		if (dim == null)
			return null;

		return switch (dim) {
			case ABYSS -> new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(), 4), new ItemStack(AoABlocks.SHADOW_BANNER.base()), 729, 20, 0.05f);
			case BARATHOS -> new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(), 4), new ItemStack(AoABlocks.BARON_BANNER.base()), 729, 20, 0.05f);
			case CANDYLAND -> new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(), 4), new ItemStack(AoABlocks.CANDY_BANNER.base()), 729, 20, 0.05f);
			case CELEVE -> new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(), 4), new ItemStack(AoABlocks.CLOWN_BANNER.base()), 729, 20, 0.05f);
			case CREEPONIA -> new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(), 4), new ItemStack(AoABlocks.CREEPY_BANNER.base()), 729, 20, 0.05f);
			case CRYSTEVIA -> new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(), 4), new ItemStack(AoABlocks.CRYSTAL_BANNER.base()), 729, 20, 0.05f);
			case DEEPLANDS -> new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(), 4), new ItemStack(AoABlocks.DEEP_BANNER.base()), 729, 20, 0.05f);
			case DUSTOPIA -> new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(), 4), new ItemStack(AoABlocks.DUSTOPIAN_BANNER.base()), 729, 20, 0.05f);
			case GARDENCIA -> new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(), 4), new ItemStack(AoABlocks.ROSIDIAN_BANNER.base()), 729, 20, 0.05f);
			case GRECKON -> new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(), 4), new ItemStack(AoABlocks.HAUNTED_BANNER.base()), 729, 20, 0.05f);
			case HAVEN -> new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(), 4), new ItemStack(AoABlocks.UTOPIAN_BANNER.base()), 729, 20, 0.05f);
			case IROMINE -> new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(), 4), new ItemStack(AoABlocks.MECHA_BANNER.base()), 729, 20, 0.05f);
			case LBOREAN -> new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(), 4), new ItemStack(AoABlocks.BOREIC_BANNER.base()), 729, 20, 0.05f);
			case LELYETIA -> new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(), 4), new ItemStack(AoABlocks.LELYETIAN_BANNER.base()), 729, 20, 0.05f);
			case LUNALUS -> new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(), 4), new ItemStack(AoABlocks.LUNAR_BANNER.base()), 729, 20, 0.05f);
			case MYSTERIUM -> new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(), 4), new ItemStack(AoABlocks.FUNGAL_BANNER.base()), 729, 20, 0.05f);
			case NETHER -> new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(), 4), new ItemStack(AoABlocks.NETHER_BANNER.base()), 729, 20, 0.05f);
			case OVERWORLD -> new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(), 4), new ItemStack(AoABlocks.VOID_BANNER.base()), 729, 20, 0.05f);
			case PRECASIA -> new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(), 4), new ItemStack(AoABlocks.ANCIENT_BANNER.base()), 729, 20, 0.05f);
			case RUNANDOR -> new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(), 4), new ItemStack(AoABlocks.RUNIC_BANNER.base()), 729, 20, 0.05f);
			case SHYRELANDS -> new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(), 4), new ItemStack(AoABlocks.SHYRE_BANNER.base()), 729, 20, 0.05f);
			case VOX_PONDS -> new MerchantOffer(new ItemStack(AoAItems.SILVER_COIN.get(), 4), new ItemStack(AoABlocks.VOX_BANNER.base()), 729, 20, 0.05f);
			default -> null;
		};
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(DefaultAnimations.genericWalkIdleController(this).setAnimationSpeed(1.35f));
		// TODO Greet anim
	}
}
