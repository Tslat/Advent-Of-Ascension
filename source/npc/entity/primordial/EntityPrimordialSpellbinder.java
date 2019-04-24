package net.nevermine.npc.entity.primordial;

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

public class EntityPrimordialSpellbinder extends EntityNevermineVillager {
	private static ArrayList<RestockedRecipe> trades = new ArrayList<RestockedRecipe>();

	public EntityPrimordialSpellbinder(final World var1) {
		super(var1);
	}

	public boolean getCanSpawnHere() {
		return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	@Override
	public void interaction(final EntityPlayer p) {
		p.addChatMessage(StringUtil.getLocale("message.dialogue.primordialSpellbinder." + rand.nextInt(5)));
	}

	@Override
	public int guiID() {
		return 7;
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	public void addRecipies(final MerchantRecipeList list) {
		list.add(new RestockedRecipe(new ItemStack(Itemizer.GoldCoin, 10, 1), new ItemStack(Itemizer.UnchargedOrb, 1), new ItemStack(Blockizer.OrbDusk, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.GoldCoin, 25, 1), new ItemStack(Itemizer.UnchargedOrb, 1), new ItemStack(Blockizer.OrbDarklight, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.LunaverCoin, 1, 1), new ItemStack(Itemizer.UnchargedOrb, 1), new ItemStack(Blockizer.OrbSunfire, 1)));
	}

	@Override
	public String mobName() {
		return "Primordial Spellbinder";
	}

	static {

	}
}
