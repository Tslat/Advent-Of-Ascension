package net.tslat.aoa3.entity.base;

import com.mojang.serialization.Dynamic;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.RegistryObject;
import net.tslat.aoa3.common.registration.AoAProfessions;
import net.tslat.aoa3.entity.ai.trader.TraderFaceCustomerGoal;
import net.tslat.aoa3.entity.ai.trader.TraderPlayerTradeGoal;
import net.tslat.aoa3.entity.ai.trader.TraderRestockGoal;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Random;

public abstract class AoATrader extends VillagerEntity {
	public AoATrader(EntityType<? extends AoATrader> entityType, World world) {
		super(entityType, world);

		((GroundPathNavigator)getNavigation()).setCanOpenDoors(true);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new SwimGoal(this));
		goalSelector.addGoal(1, new AvoidEntityGoal<MonsterEntity>(this, MonsterEntity.class, 8f, 0.8d, 1d));
		goalSelector.addGoal(1, new TraderPlayerTradeGoal(this));
		goalSelector.addGoal(1, new TraderFaceCustomerGoal(this));
		goalSelector.addGoal(2, new OpenDoorGoal(this, true));
		goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 3f, 1f));
		goalSelector.addGoal(3, new TraderRestockGoal(this));
		goalSelector.addGoal(4, new RandomWalkingGoal(this, 0.6d));
		goalSelector.addGoal(5, new LookRandomlyGoal(this));
	}

	@Nullable
	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
		ILivingEntityData data = super.finalizeSpawn(world, difficulty, reason, spawnData, dataTag);

		setVillagerData(getVillagerData().setProfession(AoAProfessions.WANDERER.get()));

		return data;
	}

	@Override
	protected Brain<?> makeBrain(Dynamic<?> dynamic) {
		return brainProvider().makeBrain(dynamic);
	}

	@Override
	public void refreshBrain(ServerWorld world) {}

	@Override
	protected void ageBoundaryReached() {}

	@Override
	public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
		ItemStack itemStack = player.getItemInHand(hand);

		if (itemStack.getItem() != Items.VILLAGER_SPAWN_EGG && isAlive() && !isTrading() && !isBaby()) {
			if (hand == Hand.MAIN_HAND)
				player.awardStat(Stats.TALKED_TO_VILLAGER);

			if (!getOffers().isEmpty()) {
				if (!level.isClientSide) {
					updateSpecialPrices(player);
					setTradingPlayer(player);
					openTradingScreen(player, getDisplayName(), getVillagerData().getLevel());
				}
			}
			return ActionResultType.sidedSuccess(level.isClientSide);
		}
		else {
			return ActionResultType.PASS;
		}
	}

	protected void updateSpecialPrices(PlayerEntity player) {
		int reputation = getPlayerReputation(player);

		if (reputation != 0) {
			for(MerchantOffer offer : getOffers()) {
				offer.addToSpecialPriceDiff(-MathHelper.floor((float)reputation * offer.getPriceMultiplier()));
			}
		}
	}

	@Override
	protected void updateTrades() {
		Int2ObjectMap<VillagerTrades.ITrade[]> trades = getTradesMap();

		if (trades != null && !trades.isEmpty()) {
			int professionLevel = getVillagerData().getLevel();
			VillagerTrades.ITrade[] currentLevelOffers = trades.get(professionLevel);

			if (currentLevelOffers != null)
				addOffersFromItemListings(getOffers(), currentLevelOffers, getMaxTradesToUnlock(professionLevel));
		}
	}

	@Override
	protected ITextComponent getTypeName() {
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

		if (this.shouldIncreaseLevel()) {
			this.updateMerchantTimer = 40;
			this.increaseProfessionLevelOnUpdate = true;
			xp += 5;
		}

		xp /= (offer.getMaxUses() / 16f);

		if (offer.shouldRewardExp())
			level.addFreshEntity(new ExperienceOrbEntity(level, getX(), getY() + 0.5D, getZ(), xp));
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
		return !isOverworldNPC() || !WorldUtil.isWorld(level, World.OVERWORLD) || tickCount >= 48000;
	}

	@Override
	public VillagerEntity getBreedOffspring(ServerWorld world, AgeableEntity mate) {
		return null;
	}

	@Override
	public void thunderHit(ServerWorld world, LightningBoltEntity lightning) {
		setRemainingFireTicks(getRemainingFireTicks() + 1);

		if (getRemainingFireTicks() == 0)
			setSecondsOnFire(8);

		hurt(DamageSource.LIGHTNING_BOLT, 5.0F);
	}

	@Override
	public void die(DamageSource cause) {
		if (cause.getEntity() != null)
			tellWitnessesThatIWasMurdered(cause.getEntity());

		if (!ForgeHooks.onLivingDeath(this, cause)) {
			if (!removed && !dead) {
				Entity entity = cause.getEntity();
				LivingEntity killer = getKillCredit();

				if (deathScore >= 0 && killer != null)
					killer.awardKillScore(this, deathScore, cause);

				dead = true;

				getCombatTracker().recheckStatus();

				if (this.level instanceof ServerWorld) {
					if (entity != null) {
						entity.killed((ServerWorld)this.level, this);
					}

					dropAllDeathLoot(cause);
					createWitherRose(killer);
				}

				level.broadcastEntityEvent(this, (byte)3);
				setPose(Pose.DYING);
			}
		}

		stopTrading();
	}

	@Nullable
	public abstract Int2ObjectMap<VillagerTrades.ITrade[]> getTradesMap();

	public static class TradeListBuilder {
		private final HashMap<Integer, VillagerTrades.ITrade[]> trades = new HashMap<Integer, VillagerTrades.ITrade[]>();

		public TradeListBuilder trades(int professionLevel, BuildableTrade... offers) {
			this.trades.put(professionLevel, offers);

			return this;
		}

		public Int2ObjectMap<VillagerTrades.ITrade[]> build() {
			return new Int2ObjectOpenHashMap<VillagerTrades.ITrade[]>(trades);
		}
	}

	public static class BuildableTrade implements VillagerTrades.ITrade {
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

		public static BuildableTrade trade(RegistryObject<? extends IItemProvider> item) {
			return trade(item, 1);
		}

		public static BuildableTrade trade(RegistryObject<? extends IItemProvider> item, int amount) {
			return trade(item.get(), amount);
		}

		public static BuildableTrade trade(IItemProvider item) {
			return trade(item, 1);
		}

		public static BuildableTrade trade(IItemProvider item, int amount) {
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

		public BuildableTrade cost(RegistryObject<? extends IItemProvider> item) {
			return cost(item, 1);
		}

		public BuildableTrade cost(RegistryObject<? extends IItemProvider> item, int amount) {
			return cost(item.get(), amount);
		}

		public BuildableTrade cost(IItemProvider item) {
			return cost(item, 1);
		}

		public BuildableTrade cost(IItemProvider item, int amount) {
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
		public MerchantOffer getOffer(Entity trader, Random rand) {
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
}