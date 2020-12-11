package net.tslat.aoa3.entity.base;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
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
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.tslat.aoa3.common.registration.AoAEntitySpawns;
import net.tslat.aoa3.entity.ai.trader.TraderFaceCustomerGoal;
import net.tslat.aoa3.entity.ai.trader.TraderPlayerTradeGoal;
import net.tslat.aoa3.entity.npc.AoATraderRecipe;
import net.tslat.aoa3.util.EntityUtil;

import javax.annotation.Nullable;
import java.util.Random;

public abstract class AoATrader extends CreatureEntity implements INPC, IMerchant {
	private static final DataParameter<String> TRADE_STATUSES = EntityDataManager.<String>createKey(AoATrader.class, DataSerializers.STRING);
	private static final DataParameter<Integer> ADDITIONAL_TRADES = EntityDataManager.<Integer>createKey(AoATrader.class, DataSerializers.VARINT);

	private MerchantOffers trades;
	private int maxTradesCount = 0;

	private String currentTradesInfo = "";
	private int additionalTrades = 0;
	private PlayerEntity latestCustomer;

	public AoATrader(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);

		((GroundPathNavigator)getNavigator()).setBreakDoors(true);
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
	protected void registerData() {
		super.registerData();

		dataManager.register(TRADE_STATUSES, "");
		dataManager.register(ADDITIONAL_TRADES, 0);
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(getBaseMaxHealth());
		getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(getBaseMovementSpeed());
		getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16);
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 1;
	}

	protected abstract double getBaseMaxHealth();

	protected abstract double getBaseMovementSpeed();

	protected boolean isFixedTradesList() {
		return false;
	}

	@Override
	public boolean func_213705_dZ() {
		return false;
	}

	@Override
	public void setClientSideOffers(@Nullable MerchantOffers offers) {
		this.trades = offers;
	}

	@Override
	public void setXP(int xpIn) {}

	@Override
	public int getXp() {
		return 0;
	}

	@Override
	public SoundEvent getYesSound() {
		return null;
	}

	@Override
	public void setCustomer(@Nullable PlayerEntity player) {
		latestCustomer = player;
	}

	@Nullable
	@Override
	public PlayerEntity getCustomer() {
		return latestCustomer;
	}

	public boolean isTrading() {
		return latestCustomer != null;
	}

	@Override
	public boolean canSpawn(IWorld world, SpawnReason reason) {
		return checkSpawnChance(reason) && checkWorldRequirements(reason) && isValidLightLevel(reason) && canSpawnAt(reason, world.getBlockState(getPosition().down()));
	}

	protected boolean canSpawnAt(SpawnReason reason, BlockState blockState) {
		return reason == SpawnReason.SPAWNER || blockState.canEntitySpawn(world, getPosition(), getType());
	}

	protected boolean checkWorldRequirements(SpawnReason reason) {
		if (isOverworldNPC() && world.getDimension().getType() != DimensionType.OVERWORLD) {
			AoAEntitySpawns.removeSpawn(getType(), world.getBiome(getPosition()));

			return false;
		}

		return true;
	}

	@Override
	public boolean canDespawn(double distanceToClosestPlayer) {
		return !isOverworldNPC() || world.dimension.getType() != DimensionType.OVERWORLD;
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);

		compound.putString("TradeStatuses", currentTradesInfo);
		compound.putInt("AdditionalTrades", additionalTrades);
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);

		currentTradesInfo = compound.contains("TradeStatuses") ? compound.getString("TradeStatuses") : "";
		additionalTrades = compound.contains("AdditionalTrades") ? compound.getInt("AdditionalTrades") : 0;

		if (!world.isRemote) {
			dataManager.set(TRADE_STATUSES, currentTradesInfo);
			dataManager.set(ADDITIONAL_TRADES, additionalTrades);
			deserializeTradeStatuses(currentTradesInfo);
		}
	}

	@Override
	public void tick() {
		super.tick();

		if (world.isRemote) {
			String currentInfo = dataManager.get(TRADE_STATUSES);

			if (!currentInfo.equals(currentTradesInfo)) {
				currentTradesInfo = currentInfo;
				int extraTrades = dataManager.get(ADDITIONAL_TRADES);

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
		return EntityUtil.isNaturalSpawnReason(reason) || getSpawnChanceFactor() <= 1 || rand.nextInt(getSpawnChanceFactor()) == 0;
	}

	protected boolean isValidLightLevel(SpawnReason reason) {
		if (world.getDimension().getType() != DimensionType.OVERWORLD)
			return true;

		BlockPos blockpos = new BlockPos(getPosX(), getBoundingBox().minY, getPosZ());

		if (world.getLightFor(LightType.SKY, blockpos) > rand.nextInt(32)) {
			return true;
		}
		else {
			int light = world.isThundering() ? world.getNeighborAwareLightSubtracted(blockpos, 10) : (int)world.getBrightness(blockpos) * 15;

			return light > rand.nextInt(8);
		}
	}

	@Override
	protected boolean processInteract(PlayerEntity player, Hand hand) {
		ItemStack heldStack = player.getHeldItem(hand);

		if (heldStack.getItem() == Items.NAME_TAG) {
			heldStack.interactWithEntity(player, this, hand);

			return true;
		}

		if (isAlive() && !player.isSneaking()) {
			if (!world.isRemote) {
				getOffers();
				setCustomer(player);
				openGui(player);
			}

			return true;
		}

		return super.processInteract(player, hand);
	}

	protected void openGui(PlayerEntity player) {
		openMerchantContainer(player, getDisplayName(), 0);
	}

	@Override
	public MerchantOffers getOffers() {
		if (trades == null)
			generateTrades();

		return trades;
	}

	private void generateTrades() {
		Random rand = new Random(1 + getUniqueID().getMostSignificantBits() + getUniqueID().getLeastSignificantBits());
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
	public void onTrade(MerchantOffer recipe) {
		if (isFixedTradesList() || trades.size() >= maxTradesCount)
			return;

		recipe.increaseUses();

		if (!world.isRemote) {
			if (recipe.getUses() >= recipe.func_222214_i()) {
				boolean tradesComplete = true;

				for (MerchantOffer trade : trades) {
					if (!trade.hasNoUsesLeft()) {
						tradesComplete = false;

						break;
					}
				}

				if (tradesComplete)
					addNewTrade();
			}

			currentTradesInfo = serializeTradeStatuses();

			dataManager.set(TRADE_STATUSES, currentTradesInfo);
			dataManager.set(ADDITIONAL_TRADES, additionalTrades);
		}
	}

	@Override
	public void verifySellingItem(ItemStack stack) {}

	@Override
	public World getWorld() {
		return world;
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