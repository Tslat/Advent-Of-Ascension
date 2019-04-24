package net.nevermine.npc.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;

import java.util.ArrayList;

public class EntityToyMerchant extends EntityNevermineVillager {
	private static ArrayList<RestockedRecipe> trades = new ArrayList<RestockedRecipe>();

	public EntityToyMerchant(final World var1) {
		super(var1);
	}

	public boolean getCanSpawnHere() {
		return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	@Override
	public void interaction(final EntityPlayer p) {
		p.addChatMessage(StringUtil.getLocale("message.dialogue.toyMerchant." + rand.nextInt(5)));
	}

	@Override
	public int guiID() {
		return 28;
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	public void addRecipies(final MerchantRecipeList list) {
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CoinCircus, 20, 1), new ItemStack(Itemizer.ToyGyrocopter, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CoinCircus, 1, 1), new ItemStack(Itemizer.Balloon, 64)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CoinCircus, 5, 1), new ItemStack(Weaponizer.ConfettiCluster, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CoinCircus, 20, 1), new ItemStack(Weaponizer.PartyPopper, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CoinCircus, 30, 1), new ItemStack(Weaponizer.BalloonBomber, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CoinCircus, 45, 1), new ItemStack(Weaponizer.BozoBlaster, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CoinCircus, 60, 1), new ItemStack(Weaponizer.ShowStaff, 1)));
	}

	@Override
	public String mobName() {
		return "ToyMerchant";
	}

	static {

	}
}
