package net.nevermine.npc.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;

import java.util.ArrayList;

public class EntityGorbArmsDealer extends EntityNevermineVillager {
	private static ArrayList<RestockedRecipe> trades = new ArrayList<RestockedRecipe>();

	public EntityGorbArmsDealer(final World var1) {
		super(var1);
	}

	public boolean getCanSpawnHere() {
		return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	@Override
	public void interaction(final EntityPlayer p) {
		p.addChatMessage(StringUtil.getLocale("message.dialogue.gorbArmsDealer." + rand.nextInt(5)));
	}

	@Override
	public int guiID() {
		return 2;
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	public void addRecipies(final MerchantRecipeList list) {
		list.add(new RestockedRecipe(new ItemStack(Itemizer.NightmareFlakes, 15, 1), new ItemStack(Itemizer.GhostlyPowder, 15), new ItemStack(Weaponizer.NightmareBow, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.Phantasm, 15, 1), new ItemStack(Itemizer.GhostlyPowder, 15), new ItemStack(Weaponizer.ScreamerBow, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.RuneStone, 20, 1), new ItemStack(Weaponizer.RunicBow)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.NightmareFlakes, 15, 1), new ItemStack(Itemizer.TrollSkull, 10), new ItemStack(Weaponizer.NightmareStaff, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.Phantasm, 15, 1), new ItemStack(Itemizer.TrollSkull, 10), new ItemStack(Weaponizer.PhantomStaff, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.RuneStone, 20, 1), new ItemStack(Weaponizer.RunicStaff)));
	}

	@Override
	public String mobName() {
		return "Gorb Arms Dealer";
	}

	static {

	}
}
