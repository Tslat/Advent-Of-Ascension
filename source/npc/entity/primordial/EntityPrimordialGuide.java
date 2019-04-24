package net.nevermine.npc.entity.primordial;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.INpc;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.village.Village;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.npc.entity.lottoman.EntityLottoman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EntityPrimordialGuide extends EntityVillager implements INpc, IMerchant {
	private int randomTickDivider;
	private boolean isMating;
	private boolean isPlaying;
	Village villageObj;
	private EntityPlayer buyingPlayer;
	private MerchantRecipeList buyingList;
	private int timeUntilReset;
	private boolean needsInitilization;
	private int wealth;
	EntityMob s;
	private String lastBuyingPlayer;
	private boolean isLookingForHome;
	private float field_82191_bN;
	public static final Map villagerStockList;
	public static final Map blacksmithSellingList;

	public EntityPrimordialGuide(final World var1) {
		super(var1);
		randomTickDivider = 0;
		isMating = false;
		isPlaying = false;
		villageObj = null;
		getNavigator().setBreakDoors(true);
		getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIAvoidEntity(this, EntityZombie.class, 8.0f, 0.30000001192092896, 0.3499999940395355));
		tasks.addTask(2, new EntityAITradePlayer(this));
		tasks.addTask(3, new EntityAILookAtTradePlayer(this));
		tasks.addTask(4, new EntityAIMoveIndoors(this));
		tasks.addTask(5, new EntityAIRestrictOpenDoor(this));
		tasks.addTask(6, new EntityAIOpenDoor(this, true));
		tasks.addTask(7, new EntityAIPlay(this, 0.3199999928474426));
		tasks.addTask(8, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0f, 1.0f));
		tasks.addTask(9, new EntityAIWatchClosest2(this, EntityLottoman.class, 5.0f, 0.02f));
		tasks.addTask(10, new EntityAIWander(this, 0.30000001192092896));
		tasks.addTask(11, new EntityAIWatchClosest(this, EntityLiving.class, 8.0f));
	}

	public boolean isAIEnabled() {
		return true;
	}

	protected void updateAITick() {
		final int randomTickDivider = this.randomTickDivider - 1;
		this.randomTickDivider = randomTickDivider;
		if (randomTickDivider <= 0) {
			worldObj.villageCollectionObj.addVillagerPosition(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ));
			this.randomTickDivider = 70 + rand.nextInt(50);
			villageObj = worldObj.villageCollectionObj.findNearestVillage(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ), 32);
			if (villageObj == null) {
				detachHome();
			}
			else {
				final ChunkCoordinates var1 = villageObj.getCenter();
				setHomeArea(var1.posX, var1.posY, var1.posZ, (int)(villageObj.getVillageRadius() * 0.6f));
				if (isLookingForHome) {
					isLookingForHome = false;
					villageObj.setDefaultPlayerReputation(5);
				}
			}
		}
		if (!isTrading() && timeUntilReset > 0) {
			--timeUntilReset;
			if (timeUntilReset <= 0) {
				if (needsInitilization) {
					if (buyingList.size() > 1) {
						for (final MerchantRecipe var3 : (ArrayList<MerchantRecipe>)buyingList) {
							if (var3.isRecipeDisabled()) {
								var3.func_82783_a(rand.nextInt(6) + rand.nextInt(6) + 2);
							}
						}
					}
					addDefaultEquipmentAndRecipies(1);
					needsInitilization = false;
					if (villageObj != null && lastBuyingPlayer != null) {
						worldObj.setEntityState(this, (byte)14);
						villageObj.setReputationForPlayer(lastBuyingPlayer, 1);
					}
				}
				addPotionEffect(new PotionEffect(Potion.regeneration.id, 200, 0));
			}
		}
		super.updateAITick();
	}

	public boolean interact(final EntityPlayer var1) {
		if (isEntityAlive() && !isTrading() && !isChild()) {
			if (!worldObj.isRemote) {
				setCustomer(var1);

				var1.addChatMessage(StringUtil.getLocale("message.dialogue.primordialGuide." + rand.nextInt(8)));
			}
			return true;
		}
		return super.interact(var1);
	}

	protected void entityInit() {
		super.entityInit();
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.05000000074505806);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1000.0);
	}

	public void writeEntityToNBT(final NBTTagCompound var1) {
		super.writeEntityToNBT(var1);
		var1.setInteger("Profession", getProfession());
		var1.setInteger("Riches", wealth);
		if (buyingList != null) {
		}
	}

	public void readEntityFromNBT(final NBTTagCompound var1) {
		super.readEntityFromNBT(var1);
		setProfession(var1.getInteger("Profession"));
		wealth = var1.getInteger("Riches");
		if (var1.hasKey("Offers")) {
			final NBTTagCompound var2 = var1.getCompoundTag("Offers");
			buyingList = new MerchantRecipeList(var2);
		}
	}

	protected boolean canDespawn() {
		return false;
	}

	protected String getLivingSound() {
		return isTrading() ? "mob.villager.haggle" : "mob.villager.idle";
	}

	protected String getHurtSound() {
		return "mob.villager.hit";
	}

	protected String getDeathSound() {
		return "mob.villager.death";
	}

	public void setProfession(final int var1) {
		dataWatcher.updateObject(16, var1);
	}

	public int getProfession() {
		return dataWatcher.getWatchableObjectInt(16);
	}

	public boolean isMating() {
		return isMating;
	}

	public void setMating(final boolean var1) {
		isMating = var1;
	}

	public void setPlaying(final boolean var1) {
		isPlaying = var1;
	}

	public boolean isPlaying() {
		return isPlaying;
	}

	public void setRevengeTarget(final EntityLiving var1) {
		super.setRevengeTarget(var1);
		if (villageObj != null && var1 != null) {
			villageObj.addOrRenewAgressor(var1);
			if (isEntityAlive()) {
				worldObj.setEntityState(this, (byte)13);
			}
		}
	}

	public void setCustomer(final EntityPlayer var1) {
		buyingPlayer = var1;
	}

	public EntityPlayer getCustomer() {
		return buyingPlayer;
	}

	public boolean isTrading() {
		return buyingPlayer != null;
	}

	public void useRecipe(final MerchantRecipe var1) {
		var1.incrementToolUses();
		if (var1.hasSameIDsAs((MerchantRecipe)buyingList.get(buyingList.size() - 1))) {
			timeUntilReset = 40;
			needsInitilization = true;
			if (buyingPlayer != null) {
				lastBuyingPlayer = buyingPlayer.getCommandSenderName();
			}
			else {
				lastBuyingPlayer = null;
			}
		}
		wealth += var1.getItemToBuy().stackSize;
	}

	public MerchantRecipeList getRecipes(final EntityPlayer var1) {
		if (buyingList == null) {
			addDefaultEquipmentAndRecipies(13);
		}
		return buyingList;
	}

	private float adjustProbability(final float var1) {
		final float var2 = var1 + field_82191_bN;
		return (var2 > 0.9f) ? (0.9f - (var2 - 0.9f)) : var2;
	}

	private void addDefaultEquipmentAndRecipies(final int var1) {
		if (buyingList != null) {
			field_82191_bN = MathHelper.sqrt_float((float)buyingList.size()) * 0.2f;
		}
		else {
			field_82191_bN = 0.0f;
		}
		final MerchantRecipeList var2 = new MerchantRecipeList();
		getProfession();
		if (buyingList == null) {
			buyingList = new MerchantRecipeList();
		}
		for (int var3 = 0; var3 < var1 && var3 < var2.size(); ++var3) {
			buyingList.addToListWithCheck((MerchantRecipe)var2.get(var3));
		}
	}

	@SideOnly(Side.CLIENT)
	public void handleHealthUpdate(final byte var1) {
		if (var1 == 12) {
			generateRandomParticles("heart");
		}
		else if (var1 == 13) {
			generateRandomParticles("angryVillager");
		}
		else if (var1 == 14) {
			generateRandomParticles("happyVillager");
		}
		else {
			super.handleHealthUpdate(var1);
		}
	}

	@SideOnly(Side.CLIENT)
	private void generateRandomParticles(final String var1) {
		for (int var2 = 0; var2 < 5; ++var2) {
			final double var3 = rand.nextGaussian() * 0.02;
			final double var4 = rand.nextGaussian() * 0.02;
			final double var5 = rand.nextGaussian() * 0.02;
			if (worldObj.isRemote) {
				worldObj.spawnParticle(var1, posX + rand.nextFloat() * width * 2.0f - width, posY + 1.0 + rand.nextFloat() * height, posZ + rand.nextFloat() * width * 2.0f - width, var3, var4, var5);
			}
		}
	}

	public void setLookingForHome() {
		isLookingForHome = true;
	}

	public IMerchant leornaInv() {
		return this;
	}

	static {
		villagerStockList = new HashMap();
		blacksmithSellingList = new HashMap();
	}
}
