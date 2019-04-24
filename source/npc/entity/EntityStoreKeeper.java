package net.nevermine.npc.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;

import java.util.ArrayList;

public class EntityStoreKeeper extends EntityNevermineVillager {
	private static ArrayList<RestockedRecipe> trades = new ArrayList<RestockedRecipe>();

	public EntityStoreKeeper(final World var1) {
		super(var1);
	}

	public boolean getCanSpawnHere() {
		return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	@Override
	public void interaction(final EntityPlayer p) {
		p.addChatMessage(StringUtil.getLocale("message.dialogue.storeKeeper." + rand.nextInt(5)));
	}

	@Override
	public int guiID() {
		return 11;
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	public void addRecipies(final MerchantRecipeList list) {
		list.add(new RestockedRecipe(new ItemStack(Itemizer.GoldCoin, 50, 1), new ItemStack(Itemizer.SludgeParasite, 2), new ItemStack(Weaponizer.VoxCannon, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.GoldCoin, 50, 1), new ItemStack(Itemizer.ToxicLump, 2), new ItemStack(Weaponizer.PoisonPlunger, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.GoldCoin, 50, 1), new ItemStack(Itemizer.DoomStone, 2), new ItemStack(Weaponizer.GasBlaster, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.LunaverCoin, 4, 1), new ItemStack(Itemizer.SludgeParasite, 2), new ItemStack(Weaponizer.SludgeSniper, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.LunaverCoin, 4, 1), new ItemStack(Itemizer.ToxicLump, 2), new ItemStack(Weaponizer.NoxiousStaff, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.LunaverCoin, 4, 1), new ItemStack(Itemizer.ToxicLump, 2), new ItemStack(Weaponizer.ToxinBow, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.LunaverCoin, 4, 1), new ItemStack(Itemizer.DoomStone, 2), new ItemStack(Weaponizer.VileVanquisher, 1)));
	}

	@Override
	public String mobName() {
		return "Store Keeper";
	}

	static {

	}
}
