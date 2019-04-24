package net.nevermine.npc.entity.zal;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Blockizer;
import net.nevermine.izer.Itemizer;
import net.nevermine.npc.entity.EntityNevermineVillager;
import net.nevermine.npc.entity.RestockedRecipe;

import java.util.ArrayList;

public class EntityZalSpellbinder extends EntityNevermineVillager {
	private static ArrayList<RestockedRecipe> trades = new ArrayList<RestockedRecipe>();

	public EntityZalSpellbinder(final World var1) {
		super(var1);
	}

	public boolean getCanSpawnHere() {
		return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	@Override
	public void interaction(final EntityPlayer p) {
		p.addChatMessage(StringUtil.getLocale("message.dialogue.zalSpellbinder." + rand.nextInt(5)));
	}

	@Override
	public int guiID() {
		return 15;
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	public void addRecipies(final MerchantRecipeList list) {
		list.add(new RestockedRecipe(new ItemStack(Itemizer.GoldCoin, 10, 1), new ItemStack(Itemizer.UnchargedOrb, 1), new ItemStack(Blockizer.OrbMoonlight, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.GoldCoin, 25, 1), new ItemStack(Itemizer.UnchargedOrb, 1), new ItemStack(Blockizer.OrbLunar, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.LunaverCoin, 1, 1), new ItemStack(Itemizer.UnchargedOrb, 1), new ItemStack(Blockizer.OrbSunfire, 1)));
	}

	@Override
	public String mobName() {
		return "Zal Spellbinder";
	}

	static {

	}
}
