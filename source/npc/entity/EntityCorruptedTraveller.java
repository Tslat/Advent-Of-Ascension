package net.nevermine.npc.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;

import java.util.ArrayList;

public class EntityCorruptedTraveller extends EntityNevermineVillager {
	private static ArrayList<RestockedRecipe> trades = new ArrayList<RestockedRecipe>();

	public EntityCorruptedTraveller(final World var1) {
		super(var1);
	}

	public boolean getCanSpawnHere() {
		return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	@Override
	public void interaction(final EntityPlayer p) {
		p.addChatMessage(StringUtil.getLocale("message.dialogue.corruptedTraveller." + rand.nextInt(5)));
	}

	@Override
	public int guiID() {
		return 39;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(400.0);
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	public void addRecipies(final MerchantRecipeList list) {
		list.add(new RestockedRecipe(new ItemStack(Items.cooked_beef, 1), new ItemStack(Itemizer.WornBook, 1)));
		list.add(new RestockedRecipe(new ItemStack(Items.cooked_porkchop, 1), new ItemStack(Itemizer.WornBook, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.UrsaCooked, 1), new ItemStack(Itemizer.WornBook, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.ChimeraChopCooked, 1), new ItemStack(Itemizer.WornBook, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.FurlionChop, 1), new ItemStack(Itemizer.WornBook, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.HalyconBeef, 1), new ItemStack(Itemizer.WornBook, 1)));
	}

	@Override
	public String mobName() {
		return "CorruptedTraveller";
	}

	static {

	}
}
