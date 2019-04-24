package net.nevermine.npc.entity.zal;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;
import net.nevermine.npc.entity.EntityNevermineVillager;
import net.nevermine.npc.entity.RestockedRecipe;

import java.util.ArrayList;

public class EntityZalFoodMarket extends EntityNevermineVillager {
	private static ArrayList<RestockedRecipe> trades = new ArrayList<RestockedRecipe>();

	public EntityZalFoodMarket(final World var1) {
		super(var1);
	}

	public boolean getCanSpawnHere() {
		return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	@Override
	public void interaction(final EntityPlayer p) {
		p.addChatMessage(StringUtil.getLocale("message.dialogue.zalFoodMarket." + rand.nextInt(5)));
	}

	@Override
	public int guiID() {
		return 13;
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	public void addRecipies(final MerchantRecipeList list) {
		list.add(new RestockedRecipe(new ItemStack(Itemizer.SilverCoin, 5, 1), new ItemStack(Items.cookie, 5)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.SilverCoin, 15, 1), new ItemStack(Items.cookie, 20)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.SilverCoin, 40, 1), new ItemStack(Items.cookie, 64)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.SilverCoin, 10, 1), new ItemStack(Items.beef, 5)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.SilverCoin, 50, 1), new ItemStack(Items.beef, 30)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.GoldCoin, 1, 1), new ItemStack(Items.beef, 64)));
	}

	@Override
	public String mobName() {
		return "Zal Grocer";
	}

	static {

	}
}
