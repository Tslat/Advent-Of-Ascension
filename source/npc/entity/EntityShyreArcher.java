package net.nevermine.npc.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;

import java.util.ArrayList;

public class EntityShyreArcher extends EntityNevermineVillager {
	private static ArrayList<RestockedRecipe> trades = new ArrayList<RestockedRecipe>();

	public EntityShyreArcher(World var1) {
		super(var1);
	}

	public boolean getCanSpawnHere() {
		return (worldObj.checkNoEntityCollision(boundingBox)) && (worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty()) && (!worldObj.isAnyLiquid(boundingBox));
	}

	public void interaction(EntityPlayer p) {
		p.addChatMessage(StringUtil.getLocale("message.dialogue.shyreArcher." + rand.nextInt(5)));
	}

	public int guiID() {
		return 41;
	}

	protected boolean canDespawn() {
		return false;
	}

	public void addRecipies(MerchantRecipeList list) {
		list.add(new RestockedRecipe(new ItemStack(Itemizer.IngotShyregem, 7, 1), new ItemStack(Itemizer.IngotShyrestone, 20), new ItemStack(Weaponizer.JusticeBow)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.IngotShyregem, 7, 1), new ItemStack(Itemizer.IngotShyrestone, 20), new ItemStack(Weaponizer.ShyregemBow)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.IngotShyregem, 7, 1), new ItemStack(Itemizer.IngotShyrestone, 20), new ItemStack(Weaponizer.SunshineBow)));
	}

	public String mobName() {
		return "ShyreArcher";
	}

	static {

	}
}
