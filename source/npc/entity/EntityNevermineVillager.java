package net.nevermine.npc.entity;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.INpc;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.village.Village;
import net.minecraft.world.World;
import net.nevermine.common.nevermine;

import java.util.Iterator;

public abstract class EntityNevermineVillager extends EntityVillager implements INpc, IMerchant {
	private int randomTickDivider;
	private Village villageObj;
	private String lastBuyingPlayer;
	private EntityPlayer buyingPlayer;
	private MerchantRecipeList buyingList;
	private int timeUntilReset;
	private boolean needsInitilization;
	private int wealth;
	private String buyersName;
	private float buying;

	public EntityNevermineVillager(final World var1) {
		super(var1);
		setSize(1.0f, 2.0f);
		randomTickDivider = 0;
		villageObj = null;
		getNavigator().setBreakDoors(true);
		getNavigator().setAvoidsWater(true);
		tasks.addTask(1, new EntityAIAvoidEntity(this, EntityZombie.class, 8.0f, 0.30000001192092896, 0.3499999940395355));
		tasks.addTask(1, new EntityAITradePlayer(this));
		tasks.addTask(1, new EntityAILookAtTradePlayer(this));
		tasks.addTask(2, new EntityAIMoveIndoors(this));
		tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
		tasks.addTask(4, new EntityAIOpenDoor(this, true));
		tasks.addTask(5, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0f, 1.0f));
		tasks.addTask(5, new EntityAIWander(this, 0.0));
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(250.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0);
	}

	public boolean isAIEnabled() {
		return true;
	}

	protected boolean canDespawn() {
		return false;
	}

	protected String getLivingSound() {
		return null;
	}

	protected String getDeathSound() {
		return null;
	}

	protected String getHurtSound() {
		return null;
	}

	protected void updateAITick() {
		if (randomTickDivider-- <= 0) {
			randomTickDivider = 70 + rand.nextInt(50);
			villageObj = worldObj.villageCollectionObj.findNearestVillage(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ), 32);
			if (villageObj == null) {
				detachHome();
			}
			else {
				villageObj.setDefaultPlayerReputation(30);
			}
		}
		if (timeUntilReset > 0 && timeUntilReset <= 0) {
			if (buyingList.size() > 1) {
				final Iterator iterator = buyingList.iterator();
				if (needsInitilization) {
					while (iterator.hasNext()) {
						final MerchantRecipe merchantrecipe = (MerchantRecipe)iterator.next();
						if (merchantrecipe.isRecipeDisabled()) {
							merchantrecipe.func_82783_a(rand.nextInt(6) + rand.nextInt(6) + 2);
						}
					}
				}
				addDefaultEquipmentAndRecipies(30);
				needsInitilization = false;
				if (villageObj != null && lastBuyingPlayer != null) {
					villageObj.setReputationForPlayer(lastBuyingPlayer, 30);
				}
			}
			addPotionEffect(new PotionEffect(Potion.regeneration.id, 200, 0));
		}
		super.updateAITick();
	}

	public boolean interact(final EntityPlayer var1) {
		if (!worldObj.isRemote) {
			interaction(var1);
			var1.openGui(nevermine.instance, guiID(), worldObj, getEntityId(), 0, 0);
			return true;
		}
		return super.interact(var1);
	}

	public abstract void interaction(final EntityPlayer p0);

	public abstract int guiID();

	public abstract void addRecipies(final MerchantRecipeList p0);

	public void writeEntityToNBT(final NBTTagCompound var1) {
		super.writeEntityToNBT(var1);
		var1.setInteger("Profession", getProfession());
		var1.setInteger("Riches", wealth);
		if (buyingList != null) {
			var1.setTag("Offers", buyingList.getRecipiesAsTags());
		}
	}

	public void readEntityFromNBT(final NBTTagCompound var1) {
		super.readEntityFromNBT(var1);
		setProfession(var1.getInteger("Profession"));
		wealth = var1.getInteger("Riches");
		if (var1.hasKey("Offers")) {
			final NBTTagCompound var2 = var1.getCompoundTag("Offers");
			buyingList = new RestockedRecipeList(var2);
		}
	}

	public void useRecipe(final MerchantRecipe var1) {
		var1.incrementToolUses();
		if (var1.hasSameIDsAs((MerchantRecipe)buyingList.get(buyingList.size() - 1))) {
			timeUntilReset = 40;
			needsInitilization = true;
			if (buyingPlayer != null) {
				buyersName = buyingPlayer.getCommandSenderName();
			}
			else {
				buyersName = null;
			}
		}
		if (var1.getItemToBuy().getItem() == Items.emerald) {
			wealth += var1.getItemToBuy().stackSize;
		}
	}

	public void func_110297_a_(final ItemStack par1ItemStack) {
	}

	public MerchantRecipeList getRecipes(final EntityPlayer var1) {
		if (buyingList == null) {
			addDefaultEquipmentAndRecipies(30);
		}
		return buyingList;
	}

	private void addDefaultEquipmentAndRecipies(final int par1) {
		if (buyingList != null) {
			buying = MathHelper.sqrt_float((float)buyingList.size()) * 0.2f;
		}
		else {
			buying = 0.0f;
		}
		final MerchantRecipeList rec = new MerchantRecipeList();
		addRecipies(rec);
		if (buyingList == null) {
			buyingList = new MerchantRecipeList();
		}
		for (int var3 = 0; var3 < par1 && var3 < rec.size(); ++var3) {
			buyingList.addToListWithCheck((MerchantRecipe)rec.get(var3));
		}
	}

	public abstract String mobName();

	@Override
	public void onStruckByLightning(EntityLightningBolt bolt) {
		dealFireDamage(5);
		setFire(8);
	}

	public void onDeath(final DamageSource d) {
		super.onDeath(d);
		if (!worldObj.isRemote && d.getSourceOfDamage() != null && d.getSourceOfDamage() instanceof EntityPlayer) {
			final EntityPlayer p = (EntityPlayer)d.getSourceOfDamage();
			final String name = (mobName() != null) ? mobName() : "null";
		}
	}
}
