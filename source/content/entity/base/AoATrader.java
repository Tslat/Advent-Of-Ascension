package net.tslat.aoa3.content.entity.base;

import com.mojang.serialization.Dynamic;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.neoforged.neoforge.common.CommonHooks;
import net.tslat.aoa3.common.registration.entity.AoAProfessions;
import net.tslat.aoa3.content.entity.ai.trader.TraderFaceCustomerGoal;
import net.tslat.aoa3.content.entity.ai.trader.TraderPlayerTradeGoal;
import net.tslat.aoa3.content.entity.ai.trader.TraderRestockGoal;
import net.tslat.aoa3.util.WorldUtil;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.HashMap;

public abstract class AoATrader extends Villager implements GeoEntity {
	private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

	public AoATrader(EntityType<? extends AoATrader> entityType, Level world) {
		super(entityType, world);

		((GroundPathNavigation)getNavigation()).setCanOpenDoors(true);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new FloatGoal(this));
		goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Monster.class, 8f, 0.8d, 1.2d));
		goalSelector.addGoal(1, new TraderPlayerTradeGoal(this));
		goalSelector.addGoal(1, new TraderFaceCustomerGoal(this));
		goalSelector.addGoal(2, new OpenDoorGoal(this, true));
		goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 3f, 1f));
		goalSelector.addGoal(3, new TraderRestockGoal(this));
		goalSelector.addGoal(4, new RandomStrollGoal(this, 0.5f));
		goalSelector.addGoal(5, new RandomLookAroundGoal(this));
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag dataTag) {
		SpawnGroupData data = super.finalizeSpawn(world, difficulty, reason, spawnData, dataTag);

		setVillagerData(getVillagerData().setProfession(AoAProfessions.WANDERER.get()));

		return data;
	}

	@Override
	public Brain<?> makeBrain(Dynamic<?> dynamic) {
		return brainProvider().makeBrain(dynamic);
	}

	@Override
	public void refreshBrain(ServerLevel world) {}

	@Override
	protected void ageBoundaryReached() {}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack itemStack = player.getItemInHand(hand);

		if (itemStack.getItem() != Items.VILLAGER_SPAWN_EGG && isAlive() && !isTrading() && !isBaby()) {
			if (hand == InteractionHand.MAIN_HAND)
				player.awardStat(Stats.TALKED_TO_VILLAGER);

			if (!getOffers().isEmpty()) {
				if (!level().isClientSide) {
					updateSpecialPrices(player);
					setTradingPlayer(player);
					openTradingScreen(player, getDisplayName(), getVillagerData().getLevel());
				}
			}
			return InteractionResult.sidedSuccess(level().isClientSide);
		}
		else {
			return InteractionResult.PASS;
		}
	}

	protected void updateSpecialPrices(Player player) {
		int reputation = getPlayerReputation(player);

		if (reputation != 0) {
			for(MerchantOffer offer : getOffers()) {
				offer.addToSpecialPriceDiff(-Mth.floor((float)reputation * offer.getPriceMultiplier()));
			}
		}
	}

	@Override
	protected void updateTrades() {
		Int2ObjectMap<VillagerTrades.ItemListing[]> trades = getTradesMap();

		if (trades != null && !trades.isEmpty()) {
			int professionLevel = getVillagerData().getLevel();
			VillagerTrades.ItemListing[] currentLevelOffers = trades.get(professionLevel);

			if (currentLevelOffers != null)
				addOffersFromItemListings(getOffers(), currentLevelOffers, getMaxTradesToUnlock(professionLevel));
		}
	}

	@Override
	protected Component getTypeName() {
		return getType().getDescription();
	}

	public int getMaxTradesToUnlock(int newProfessionLevel) {
		return 2;
	}

	protected boolean isOverworldNPC() {
		return false;
	}

	@Override
	public boolean canPickUpLoot() {
		return false;
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return 1;
	}

	@Override
	public SoundEvent getNotifyTradeSound() {
		return null;
	}

	@Override
	protected void rewardTradeXp(MerchantOffer offer) {
		int xp = 3 + this.random.nextInt(4);
		this.villagerXp += offer.getXp();
		this.lastTradedPlayer = this.getTradingPlayer();

		if (shouldIncreaseLevel()) {
			this.updateMerchantTimer = 40;
			this.increaseProfessionLevelOnUpdate = true;
			xp += 5;
		}

		xp /= (offer.getMaxUses() / 16f);

		if (offer.shouldRewardExp())
			level().addFreshEntity(new ExperienceOrb(level(), getX(), getY() + 0.5D, getZ(), xp));
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return null;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource) {
		return SoundEvents.GENERIC_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.GENERIC_DEATH;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !isOverworldNPC() || !WorldUtil.isWorld(level(), Level.OVERWORLD) || tickCount >= 48000;
	}

	@Override
	public Villager getBreedOffspring(ServerLevel world, AgeableMob mate) {
		return null;
	}

	@Override
	public void thunderHit(ServerLevel world, LightningBolt lightning) {
		setRemainingFireTicks(getRemainingFireTicks() + 1);

		if (getRemainingFireTicks() == 0)
			setSecondsOnFire(8);

		hurt(level().damageSources().lightningBolt(), 5.0F);
	}

	@Override
	public void die(DamageSource cause) {
		if (cause.getEntity() != null)
			tellWitnessesThatIWasMurdered(cause.getEntity());

		if (!CommonHooks.onLivingDeath(this, cause)) {
			if (!isRemoved() && !dead) {
				Entity entity = cause.getEntity();
				LivingEntity killer = getKillCredit();

				if (deathScore >= 0 && killer != null)
					killer.awardKillScore(this, deathScore, cause);

				dead = true;

				getCombatTracker().recheckStatus();

				if (this.level() instanceof ServerLevel) {
					dropAllDeathLoot(cause);
					createWitherRose(killer);
				}

				level().broadcastEntityEvent(this, (byte)3);
				setPose(Pose.DYING);
			}
		}

		stopTrading();
	}

	@Nullable
	public abstract Int2ObjectMap<VillagerTrades.ItemListing[]> getTradesMap();

	public static class TradeListBuilder {
		private final HashMap<Integer, VillagerTrades.ItemListing[]> trades = new HashMap<>();

		public TradeListBuilder trades(int professionLevel, BuildableTrade... offers) {
			this.trades.put(professionLevel, offers);

			return this;
		}

		public Int2ObjectMap<VillagerTrades.ItemListing[]> build() {
			return new Int2ObjectOpenHashMap<>(trades);
		}
	}

	public static class BuildableTrade implements VillagerTrades.ItemListing {
		private final ItemStack item;
		private ItemStack cost1 = null;
		@Nullable
		private ItemStack cost2 = null;

		private int xpValue = 2;
		private float priceMultiplier = 0.05f;
		private int maxUses = 16;

		private boolean isLocked = false;

		private BuildableTrade(ItemStack item) {
			this.item = item;
		}

		public static BuildableTrade trade(ItemLike item) {
			return trade(item, 1);
		}

		public static BuildableTrade trade(ItemLike item, int amount) {
			return trade(new ItemStack(item, amount));
		}

		public static BuildableTrade trade(ItemStack stack) {
			return new BuildableTrade(stack);
		}

		public BuildableTrade locked() {
			this.isLocked = true;
			this.maxUses = 0;

			return this;
		}

		public BuildableTrade cost(ItemLike item) {
			return cost(item, 1);
		}

		public BuildableTrade cost(ItemLike item, int amount) {
			return cost(new ItemStack(item, amount));
		}

		public BuildableTrade cost(ItemStack cost) {
			if (this.cost1 == null) {
				this.cost1 = cost;
			}
			else if (this.cost2 == null) {
				this.cost2 = cost;
			}

			return this;
		}

		public BuildableTrade xp(int tradeXp) {
			this.xpValue = tradeXp;

			return this;
		}

		public BuildableTrade priceMultiplier(float multiplier) {
			this.priceMultiplier = multiplier;

			return this;
		}

		public BuildableTrade stock(int stock) {
			this.maxUses = stock;

			return this;
		}

		@Nullable
		@Override
		public MerchantOffer getOffer(Entity trader, RandomSource rand) {
			MerchantOffer offer;

			if (cost2 != null) {
				offer = new MerchantOffer(cost1.copy(), cost2.copy(), item.copy(), maxUses, xpValue, priceMultiplier);
			}
			else if (cost1 != null) {
				offer = new MerchantOffer(cost1.copy(), item.copy(), maxUses, xpValue, priceMultiplier);
			}
			else {
				return null;
			}

			if (isLocked)
				offer.setToOutOfStock();

			return offer;
		}
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.geoCache;
	}
}