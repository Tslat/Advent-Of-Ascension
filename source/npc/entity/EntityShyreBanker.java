package net.nevermine.npc.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;

import java.util.ArrayList;

public class EntityShyreBanker extends EntityNevermineVillager {
	private static ArrayList<RestockedRecipe> trades = new ArrayList<RestockedRecipe>();

	public EntityShyreBanker(World var1) {
		super(var1);
	}

	public boolean getCanSpawnHere() {
		return (worldObj.checkNoEntityCollision(boundingBox)) && (worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty()) && (!worldObj.isAnyLiquid(boundingBox));
	}

	public void interaction(EntityPlayer p) {
		p.addChatMessage(StringUtil.getLocale("message.dialogue.shyreBanker." + rand.nextInt(5)));
	}

	public int guiID() {
		return 40;
	}

	protected boolean canDespawn() {
		return false;
	}

	public void addRecipies(MerchantRecipeList list) {
		list.add(new RestockedRecipe(new ItemStack(Itemizer.LunaverCoin, 1, 1), new ItemStack(Itemizer.GoldCoin, 20)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.GoldCoin, 1, 1), new ItemStack(Itemizer.SilverCoin, 20)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.SilverCoin, 1, 1), new ItemStack(Itemizer.CopperCoin, 20)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.GoldCoin, 20, 1), new ItemStack(Itemizer.LunaverCoin, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.SilverCoin, 20, 1), new ItemStack(Itemizer.GoldCoin, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CopperCoin, 20, 1), new ItemStack(Itemizer.SilverCoin, 1)));
	}

	public String mobName() {
		return "ShyreBanker";
	}

	static {

	}
}
