package net.tslat.aoa3.entity.base;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.INPC;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.IMerchant;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.item.MerchantOffers;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.ai.trader.TraderFaceCustomerGoal;
import net.tslat.aoa3.entity.ai.trader.TraderPlayerTradeGoal;
import net.tslat.aoa3.entity.npc.AoATraderRecipe;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;
import java.util.Random;

public abstract class AoATrader extends CreatureEntity implements INPC, IMerchant {
	private static final DataParameter<String> TRADE_STATUSES = EntityDataManager.<String>defineId(AoATrader.class, DataSerializers.STRING);
	private static final DataParameter<Integer> ADDITIONAL_TRADES = EntityDataManager.<Integer>defineId(AoATrader.class, DataSerializers.INT);

	private MerchantOffers trades;
	private int maxTradesCount = 0;

	private String currentTradesInfo = "";
	private int additionalTrades = 0;
	private PlayerEntity latestCustomer;

	public AoATrader(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);

		((GroundPathNavigator)getNavigation()).setCanOpenDoors(true);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new SwimGoal(this));
		goalSelector.addGoal(1, new AvoidEntityGoal<AoAMeleeMob>(this, AoAMeleeMob.class, 8f, 0.8d, 1d));
		goalSelector.addGoal(1, new TraderPlayerTradeGoal(this));
		goalSelector.addGoal(1, new TraderFaceCustomerGoal(this));
		goalSelector.addGoal(2, new OpenDoorGoal(this, true));
		goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 3f, 1f));
		goalSelector.addGoal(4, new RandomWalkingGoal(this, 0.6d));
		goalSelector.addGoal(5, new LookRandomlyGoal(this));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();

		entityData.define(TRADE_STATUSES, "");
		entityData.define(ADDITIONAL_TRADES, 0);
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return 1;
	}

	protected boolean isFixedTradesList() {
		return false;
	}

	@Override
	public boolean showProgressBar() {
		return false;
	}

	@Override
	public void overrideOffers(@Nullable MerchantOffers offers) {
		this.trades = offers;
	}

	@Override
	public void overrideXp(int xpIn) {}

	@Override
	public int getVillagerXp() {
		return 0;
	}

	@Override
	public SoundEvent getNotifyTradeSound() {
		return null;
	}

	@Override
	public void setTradingPlayer(@Nullable PlayerEntity player) {
		latestCustomer = player;
	}

	@Nullable
	@Override
	public PlayerEntity getTradingPlayer() {
		return latestCustomer;
	}

	public boolean isTrading() {
		return latestCustomer != null;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !isOverworldNPC() || !WorldUtil.isWorld(level, World.OVERWORLD) || tickCount >= 72000;
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);

		compound.putString("TradeStatuses", currentTradesInfo);
		compound.putInt("AdditionalTrades", additionalTrades);
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);

		currentTradesInfo = compound.contains("TradeStatuses") ? compound.getString("TradeStatuses") : "";
		additionalTrades = compound.contains("AdditionalTrades") ? compound.getInt("AdditionalTrades") : 0;

		if (!level.isClientSide) {
			entityData.set(TRADE_STATUSES, currentTradesInfo);
			entityData.set(ADDITIONAL_TRADES, additionalTrades);
			deserializeTradeStatuses(currentTradesInfo);
		}
	}

	@Override
	public void tick() {
		super.tick();

		if (level.isClientSide) {
			String currentInfo = entityData.get(TRADE_STATUSES);

			if (!currentInfo.equals(currentTradesInfo)) {
				currentTradesInfo = currentInfo;
				int extraTrades = entityData.get(ADDITIONAL_TRADES);

				if (extraTrades != additionalTrades) {
					additionalTrades = extraTrades;

					generateTrades();
				}

				deserializeTradeStatuses(currentInfo);
			}
		}
	}

	protected boolean isOverworldNPC() {
		return false;
	}

	protected int getSpawnChanceFactor() {
		return 10;
	}

	private boolean checkSpawnChance(SpawnReason reason) {
		return EntityUtil.isNaturalSpawnReason(reason) || getSpawnChanceFactor() <= 1 || random.nextInt(getSpawnChanceFactor()) == 0;
	}

	@Override
	protected ActionResultType mobInteract(PlayerEntity player, Hand hand) {
		ItemStack heldStack = player.getItemInHand(hand);

		if (heldStack.getItem() == Items.NAME_TAG) {
			ActionResultType result = heldStack.interactLivingEntity(player, this, hand);

			if (result.consumesAction())
				return result;
		}

		if (isAlive() && !player.isShiftKeyDown()) {
			if (!level.isClientSide) {
				getOffers();
				setTradingPlayer(player);
				openGui(player);
			}

			return ActionResultType.sidedSuccess(level.isClientSide);
		}

		return super.mobInteract(player, hand);
	}

	protected void openGui(PlayerEntity player) {
		openTradingScreen(player, getDisplayName(), 0);
	}

	@Override
	public MerchantOffers getOffers() {
		if (trades == null)
			generateTrades();

		return trades;
	}

	private void generateTrades() {
		Random rand = new Random(1 + getUUID().getMostSignificantBits() + getUUID().getLeastSignificantBits());
		NonNullList<AoATraderRecipe> allTrades = NonNullList.<AoATraderRecipe>create();
		trades = new MerchantOffers();

		getTradesList(allTrades);

		maxTradesCount = allTrades.size();

		if (!isFixedTradesList()) {
			int newTradesSize = Math.max(rand.nextInt(allTrades.size()), 1);

			if (additionalTrades > 0 && newTradesSize < allTrades.size())
				newTradesSize = Math.min(newTradesSize + additionalTrades, allTrades.size());

			for (int i = 0; i < newTradesSize; i++) {
				int pick = rand.nextInt(allTrades.size());

				trades.add(allTrades.get(pick));
				allTrades.remove(pick);
			}
		}
		else {
			trades.addAll(allTrades);
		}
	}

	protected abstract void getTradesList(final NonNullList<AoATraderRecipe> newTradesList);

	public void setTrades(MerchantOffers trades) {}

	@Override
	public void notifyTrade(MerchantOffer recipe) {
		if (isFixedTradesList() || trades.size() >= maxTradesCount)
			return;

		recipe.increaseUses();

		if (!level.isClientSide) {
			if (recipe.getUses() >= recipe.getMaxUses()) {
				boolean tradesComplete = true;

				for (MerchantOffer trade : trades) {
					if (!trade.isOutOfStock()) {
						tradesComplete = false;

						break;
					}
				}

				if (tradesComplete)
					addNewTrade();
			}

			currentTradesInfo = serializeTradeStatuses();

			entityData.set(TRADE_STATUSES, currentTradesInfo);
			entityData.set(ADDITIONAL_TRADES, additionalTrades);
		}
	}

	@Override
	public void notifyTradeUpdated(ItemStack stack) {}

	@Override
	public World getLevel() {
		return level;
	}

	private void addNewTrade() {
		additionalTrades++;

		generateTrades();
	}

	private String serializeTradeStatuses() {
		if (trades == null)
			return "";

		StringBuilder builder = new StringBuilder();

		for (MerchantOffer trade : trades) {
			builder.append("|");
			builder.append(trade.getUses());
		}

		return builder.substring(1);
	}

	private void deserializeTradeStatuses(String string) {
		if (!currentTradesInfo.equals(string))
			currentTradesInfo = string;

		if (trades == null)
			generateTrades();

		int tradeIndex = 0;

		if (!trades.isEmpty() && !currentTradesInfo.isEmpty()) {
			for (String s : currentTradesInfo.split("\\|")) {
				MerchantOffer trade = trades.get(tradeIndex);

				for (int i = 0; i < Integer.parseInt(s) - trade.getUses(); i++) {
					trade.increaseUses();
				}

				tradeIndex++;
			}
		}
	}
}