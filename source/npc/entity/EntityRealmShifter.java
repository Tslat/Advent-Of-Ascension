package net.nevermine.npc.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;

import java.util.ArrayList;

public class EntityRealmShifter extends EntityNevermineVillager {
	private static ArrayList<RestockedRecipe> trades = new ArrayList<RestockedRecipe>();

	public EntityRealmShifter(final World var1) {
		super(var1);
	}

	public boolean getCanSpawnHere() {
		return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	@Override
	public void interaction(final EntityPlayer p) {
		p.addChatMessage(StringUtil.getLocale("message.dialogue.realmShifter." + rand.nextInt(5)));
	}

	@Override
	public int guiID() {
		return 34;
	}

	@Override
	protected boolean canDespawn() {
		return true;
	}

	@Override
	public void addRecipies(final MerchantRecipeList list) {
		list.add(new RestockedRecipe(new ItemStack(Itemizer.RealmstoneAbyss, 1, 1), new ItemStack(Itemizer.SilverCoin, 6)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.RealmstoneAncientCavern, 1, 1), new ItemStack(Itemizer.SilverCoin, 9)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.RealmstoneBarathos, 1, 1), new ItemStack(Itemizer.SilverCoin, 8)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.RealmstoneBorean, 1, 1), new ItemStack(Itemizer.SilverCoin, 7)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.RealmstoneCeleve, 1, 1), new ItemStack(Itemizer.SilverCoin, 4)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.RealmstoneCrystevia, 1, 1), new ItemStack(Itemizer.SilverCoin, 6)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.RealmstoneDeeplands, 1, 1), new ItemStack(Itemizer.CopperCoin, 9)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.RealmstoneDustopia, 1, 1), new ItemStack(Itemizer.SilverCoin, 6)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.RealmstoneGardencia, 1, 1), new ItemStack(Itemizer.SilverCoin, 2)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.RealmstoneHaven, 1, 1), new ItemStack(Itemizer.SilverCoin, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.RealmstoneIromine, 1, 1), new ItemStack(Itemizer.CopperCoin, 4)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.RealmstoneLelyetia, 1, 1), new ItemStack(Itemizer.SilverCoin, 3)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.RealmstoneMysterium, 1, 1), new ItemStack(Itemizer.SilverCoin, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.RealmstonePrecasia, 1, 1), new ItemStack(Itemizer.SilverCoin, 2)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.RealmstoneVoxPonds, 1, 1), new ItemStack(Itemizer.SilverCoin, 4)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.RealmstoneCandyland, 1, 1), new ItemStack(Itemizer.SilverCoin, 5)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.RealmstoneCreeponia, 1, 1), new ItemStack(Itemizer.SilverCoin, 6)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.RealmstoneImmortallis, 1, 1), new ItemStack(Itemizer.SilverCoin, 6)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.RealmstoneShyrelands, 1, 1), new ItemStack(Itemizer.SilverCoin, 9)));
	}

	@Override
	public String mobName() {
		return "RealmShifter";
	}

	static {

	}
}
