package net.nevermine.npc.entity.primordial;

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

public class EntityPrimordialMerchant extends EntityNevermineVillager {
	private static ArrayList<RestockedRecipe> trades = new ArrayList<RestockedRecipe>();

	public EntityPrimordialMerchant(final World var1) {
		super(var1);
	}

	public boolean getCanSpawnHere() {
		return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	@Override
	public void interaction(final EntityPlayer p) {
		p.addChatMessage(StringUtil.getLocale("message.dialogue.primordialMerchant." + rand.nextInt(5)));
	}

	@Override
	public int guiID() {
		return 9;
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	public void addRecipies(final MerchantRecipeList list) {
		list.add(new RestockedRecipe(new ItemStack(Itemizer.SilverCoin, 1, 1), new ItemStack(Items.beef, 2)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.PrimordialSkull, 5, 1), new ItemStack(Itemizer.PrimordialDust, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.GoldCoin, 3, 1), new ItemStack(Itemizer.IngotRosite, 1)));
	}

	@Override
	public String mobName() {
		return "Primordial Merchant";
	}

	static {

	}
}
