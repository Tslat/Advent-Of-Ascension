package net.nevermine.npc.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;

public class EntityPortalMaster extends EntityNevermineVillager {
	public EntityPortalMaster(final World var1) {
		super(var1);
	}

	public boolean getCanSpawnHere() {
		return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	@Override
	public void interaction(final EntityPlayer p) {
		p.addChatMessage(StringUtil.getLocale("message.dialogue.portalMaster." + rand.nextInt(5)));
	}

	@Override
	public int guiID() {
		return 4;
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	public void addRecipies(final MerchantRecipeList var2) {
		var2.add(new RestockedRecipe(new ItemStack(Itemizer.RuneStone, 2, 1), new ItemStack(Itemizer.LunaverCoin, 1), new ItemStack(Itemizer.DimensionTicket, 1)));
	}

	@Override
	public String mobName() {
		return "Portal Master";
	}
}
