package net.nevermine.npc.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;

import java.util.ArrayList;

public class EntityProfessor extends EntityNevermineVillager {
	private static ArrayList<RestockedRecipe> trades = new ArrayList<RestockedRecipe>();

	public EntityProfessor(final World var1) {
		super(var1);
	}

	public boolean getCanSpawnHere() {
		return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	@Override
	public void interaction(final EntityPlayer p) {
		p.addChatMessage(StringUtil.getLocale("message.dialogue.professor." + rand.nextInt(5)));
	}

	@Override
	public int guiID() {
		return 10;
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	public void addRecipies(final MerchantRecipeList list) {
		list.add(new RestockedRecipe(new ItemStack(Itemizer.SilverCoin, 50, 1), new ItemStack(Itemizer.IngotLyon, 5), new ItemStack(Itemizer.MechaGear, 2)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.SilverCoin, 1, 1), new ItemStack(Itemizer.IngotLyon, 20), new ItemStack(Itemizer.CoinSilvro, 1)));
	}

	@Override
	public String mobName() {
		return "Professor";
	}

	static {

	}
}
