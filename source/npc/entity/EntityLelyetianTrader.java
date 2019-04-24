package net.nevermine.npc.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;

import java.util.ArrayList;

public class EntityLelyetianTrader extends EntityNevermineVillager {
	private static ArrayList<RestockedRecipe> trades = new ArrayList<RestockedRecipe>();

	public EntityLelyetianTrader(final World var1) {
		super(var1);
	}

	public boolean getCanSpawnHere() {
		return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	@Override
	public void interaction(final EntityPlayer p) {
		p.addChatMessage(StringUtil.getLocale("message.dialogue.lelyetianTrader." + rand.nextInt(5)));
	}

	@Override
	public int guiID() {
		return 23;
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	public void addRecipies(final MerchantRecipeList list) {
		list.add(new RestockedRecipe(new ItemStack(Itemizer.ZhinxDust, 45, 1), new ItemStack(Weaponizer.Decimator, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.LunaverCoin, 2, 1), new ItemStack(Itemizer.ZhinxDust, 25), new ItemStack(Weaponizer.Cyclone, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.LunaverCoin, 3, 1), new ItemStack(Itemizer.ZhinxDust, 20), new ItemStack(Itemizer.WeaponParts, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.LunaverCoin, 3, 1), new ItemStack(Itemizer.DarkBones, 20), new ItemStack(Itemizer.WeaponParts, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.LunaverCoin, 3, 1), new ItemStack(Itemizer.FleshyBones, 20), new ItemStack(Itemizer.WeaponParts, 1)));
	}

	@Override
	public String mobName() {
		return "LelyetianTrader";
	}

	static {

	}
}
