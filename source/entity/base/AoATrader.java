package net.tslat.aoa3.entity.base;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.entity.base.ai.npc.EntityAIFaceCustomer;
import net.tslat.aoa3.entity.base.ai.npc.EntityAITradeWithPlayer;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.PlayerUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Random;

public abstract class AoATrader extends EntityCreature implements INpc, IMerchant {
	@Nullable
	private EntityPlayer currentCustomer;
	private MerchantRecipeList trades;

	public AoATrader(World world, float entityWidth, float entityHeight) {
		super(world);
		setSize(entityWidth, entityHeight);
		((PathNavigateGround)this.getNavigator()).setBreakDoors(true);

		if (world.provider.getDimension() == 0)
			this.enablePersistence();
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAvoidEntity(this, AoAMeleeMob.class, 8.0f, 0.8f, 1.0f));
		this.tasks.addTask(1, new EntityAITradeWithPlayer(this));
		this.tasks.addTask(1, new EntityAIFaceCustomer(this));
		this.tasks.addTask(2, new EntityAIMoveIndoors(this));
		this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
		this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
		this.tasks.addTask(5, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0f, 1.0f));
		this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 0.6));
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 1;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(getBaseMaxHealth());
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(getBaseMovementSpeed());
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16);
	}

	protected abstract double getBaseMaxHealth();

	protected abstract double getBaseMovementSpeed();

	@Nullable
	protected abstract ITextComponent getInteractMessage();

	protected boolean isFixedTradesList() {
		return false;
	}

	@Override
	public boolean isCreatureType(EnumCreatureType type, boolean forSpawnCount) {
		return type == EnumCreatureType.AMBIENT;
	}

	@Override
	public boolean getCanSpawnHere() {
		if (world.getWorldType() == WorldType.FLAT)
			return false;

		return checkSpawnChance() && checkWorldSpawnConditions() && isValidLightLevel() && canSpawnOnBlock(world.getBlockState(getPosition().down()));
	}

	protected boolean canSpawnOnBlock(IBlockState block) {
		return block.canEntitySpawn(this);
	}

	protected boolean checkWorldSpawnConditions() {
		if (isOverworldNPC() && world.provider.getDimension() != 0) {
			EntityRegistry.removeSpawn(getClass(), EnumCreatureType.AMBIENT, world.getBiome(getPosition()));

			return false;
		}

		return true;
	}

	protected boolean isOverworldNPC() {
		return false;
	}

	protected int getSpawnChanceFactor() {
		return 150;
	}

	private boolean checkSpawnChance() {
		return getSpawnChanceFactor() <= 1 || rand.nextInt(getSpawnChanceFactor()) == 0;
	}

	protected boolean isValidLightLevel() {
		BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);

		if (this.world.getLightFor(EnumSkyBlock.SKY, blockpos) > this.rand.nextInt(32)) {
			return true;
		}
		else {
			int i = this.world.getLightFromNeighbors(blockpos);

			if (this.world.isThundering()) {
				int j = this.world.getSkylightSubtracted();
				this.world.setSkylightSubtracted(10);
				i = this.world.getLightFromNeighbors(blockpos);
				this.world.setSkylightSubtracted(j);
			}

			return i > this.rand.nextInt(8);
		}
	}

	@Override
	public void setCustomer(@Nullable EntityPlayer player) {
		this.currentCustomer = player;
	}

	public boolean isTrading() {
		return currentCustomer != null;
	}

	@Nullable
	@Override
	public EntityPlayer getCustomer() {
		return currentCustomer;
	}

	@Override
	protected boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack heldStack = player.getHeldItem(hand);

		if (heldStack.getItem() == Items.NAME_TAG) {
			heldStack.interactWithEntity(player, this, hand);

			return true;
		}

		if (isEntityAlive() && !isTrading() && !player.isSneaking()) {
			if (!world.isRemote) {
				if (hand == EnumHand.MAIN_HAND) {
					ITextComponent msg = getInteractMessage();

					if (msg != null) {
						msg.setStyle(new Style().setColor(TextFormatting.GRAY));
						PlayerUtil.getAdventPlayer(player).sendPlayerMessage(msg);
					}
				}

				getRecipes(player);
				setCustomer(player);
				player.openGui(AdventOfAscension.instance, getTraderGui().guiId, world, getEntityId(), 0, 0);
			}

			return true;
		}

		return super.processInteract(player, hand);
	}

	protected abstract Enums.ModGuis getTraderGui();

	@Nullable
	@Override
	public MerchantRecipeList getRecipes(EntityPlayer player) {
		if (this.trades == null) {
			Random rand = new Random(1 + getUniqueID().getMostSignificantBits() + getUniqueID().getLeastSignificantBits());

			generateTrades(rand);
		}

		return trades;
	}

	private void generateTrades(Random rand) {
		ArrayList<AoATraderRecipe> tradesList = getNewTrades(new ArrayList<AoATraderRecipe>());

		if (trades == null)
			trades = new MerchantRecipeList();

		if (!isFixedTradesList()) {
			int newTradesSize = Math.max(rand.nextInt(tradesList.size()), 1);

			for (int i = 0; i < newTradesSize; i++) {
				int pick = rand.nextInt(tradesList.size());

				trades.add(tradesList.get(pick));
				tradesList.remove(pick);
			}
		}
		else {
			trades.addAll(tradesList);
		}
	}

	@Override
	protected boolean canDespawn() {
		return world.provider.getDimension() == 0;
	}

	protected abstract ArrayList<AoATraderRecipe> getNewTrades(final ArrayList<AoATraderRecipe> newList);

	@SideOnly(Side.CLIENT)
	@Override
	public void setRecipes(@Nullable MerchantRecipeList recipeList) {}

	@Override
	public void useRecipe(MerchantRecipe recipe) {}

	@Override
	public void verifySellingItem(ItemStack stack) {}

	@Override
	public World getWorld() {
		return world;
	}

	@Override
	public BlockPos getPos() {
		return new BlockPos(this);
	}
}
