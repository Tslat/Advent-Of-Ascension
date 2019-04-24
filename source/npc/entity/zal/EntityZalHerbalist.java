package net.nevermine.npc.entity.zal;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.Plantizer;
import net.nevermine.npc.entity.EntityNevermineVillager;
import net.nevermine.npc.entity.RestockedRecipe;

import java.util.ArrayList;

public class EntityZalHerbalist extends EntityNevermineVillager {
	private static ArrayList<RestockedRecipe> trades = new ArrayList<RestockedRecipe>();

	public EntityZalHerbalist(final World var1) {
		super(var1);
	}

	public boolean getCanSpawnHere() {
		return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	@Override
	public void interaction(final EntityPlayer p) {
		p.addChatMessage(StringUtil.getLocale("message.dialogue.zalHerbalist." + rand.nextInt(5)));
	}

	@Override
	public int guiID() {
		return 14;
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	public void addRecipies(final MerchantRecipeList list) {
		list.add(new RestockedRecipe(new ItemStack(Itemizer.SilverCoin, 5, 1), new ItemStack(Plantizer.LunacrikeSeeds, 5)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.SilverCoin, 5, 1), new ItemStack(Plantizer.LunaglobeSeeds, 5)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.SilverCoin, 5, 1), new ItemStack(Plantizer.LunalonSeeds, 5)));
	}

	@Override
	public String mobName() {
		return "Zal Herbalist";
	}

	static {

	}
}
