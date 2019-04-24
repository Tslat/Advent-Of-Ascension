package net.nevermine.npc.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;

public class EntitySoulAgent extends EntityNevermineVillager {
	public EntitySoulAgent(final World var1) {
		super(var1);
	}

	public boolean getCanSpawnHere() {
		return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	@Override
	public void interaction(final EntityPlayer p) {
		p.addChatMessage(StringUtil.getLocale("message.dialogue.soulAgent." + rand.nextInt(5)));
	}

	@Override
	public int guiID() {
		return 33;
	}

	@Override
	protected boolean canDespawn() {
		return true;
	}

	@Override
	public void addRecipies(final MerchantRecipeList var2) {
		var2.add(new RestockedRecipe(new ItemStack(Itemizer.CopperCoin, 25, 1), new ItemStack(Itemizer.FragmentedAnimaStone, 2)));
	}

	@Override
	public String mobName() {
		return "Soul Agent";
	}
}
