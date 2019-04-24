package net.nevermine.npc.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;

import java.util.ArrayList;

public class EntityMetalloid extends EntityNevermineVillager {
	private static ArrayList<RestockedRecipe> trades = new ArrayList<RestockedRecipe>();

	public EntityMetalloid(final World var1) {
		super(var1);
	}

	public boolean getCanSpawnHere() {
		return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	@Override
	public void interaction(final EntityPlayer p) {
		p.addChatMessage(StringUtil.getLocale("message.dialogue.metalloid." + rand.nextInt(5)));
	}

	@Override
	public int guiID() {
		return 32;
	}

	@Override
	protected boolean canDespawn() {
		return true;
	}

	@Override
	public void addRecipies(final MerchantRecipeList list) {
		list.add(new RestockedRecipe(new ItemStack(Itemizer.SilverCoin, 4, 1), new ItemStack(Itemizer.AmethystIngot, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.SilverCoin, 8, 1), new ItemStack(Itemizer.IngotJade, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.SilverCoin, 10, 1), new ItemStack(Itemizer.IngotRosite, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.GoldCoin, 5, 1), new ItemStack(Itemizer.IngotSapphire, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.AmethystIngot, 1, 1), new ItemStack(Itemizer.SilverCoin, 2)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.IngotJade, 1, 1), new ItemStack(Itemizer.SilverCoin, 4)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.IngotRosite, 1, 1), new ItemStack(Itemizer.SilverCoin, 5)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.IngotSapphire, 1, 1), new ItemStack(Itemizer.GoldCoin, 3)));
	}

	@Override
	public String mobName() {
		return "Metalloid";
	}

	static {

	}
}
