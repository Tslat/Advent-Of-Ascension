package net.nevermine.npc.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.Plantizer;

import java.util.ArrayList;

public class EntityNaturalist extends EntityNevermineVillager {
	private static ArrayList<RestockedRecipe> trades = new ArrayList<RestockedRecipe>();

	public EntityNaturalist(final World var1) {
		super(var1);
	}

	public boolean getCanSpawnHere() {
		return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	@Override
	public void interaction(final EntityPlayer p) {
		p.addChatMessage(StringUtil.getLocale("message.dialogue.naturalist." + rand.nextInt(5)));
	}

	@Override
	public int guiID() {
		return 31;
	}

	@Override
	protected boolean canDespawn() {
		return true;
	}

	@Override
	public void addRecipies(final MerchantRecipeList list) {
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CopperCoin, 64, 1), new ItemStack(Plantizer.BubbleBerrySeeds, 2)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CopperCoin, 64, 1), new ItemStack(Plantizer.HeartFruitSeeds, 2)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CopperCoin, 64, 1), new ItemStack(Plantizer.MagicMarangSeeds, 2)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CopperCoin, 64, 1), new ItemStack(Plantizer.ThornyPlantSeeds, 2)));
	}

	@Override
	public String mobName() {
		return "Naturalist";
	}

	static {

	}
}
