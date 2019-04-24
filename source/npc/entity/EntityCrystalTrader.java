package net.nevermine.npc.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Armorizer;
import net.nevermine.izer.equipment.Weaponizer;

import java.util.ArrayList;

public class EntityCrystalTrader extends EntityNevermineVillager {
	private static ArrayList<RestockedRecipe> trades = new ArrayList<RestockedRecipe>();

	public EntityCrystalTrader(final World var1) {
		super(var1);
	}

	public boolean getCanSpawnHere() {
		return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	@Override
	public void interaction(final EntityPlayer p) {
		p.addChatMessage(StringUtil.getLocale("message.dialogue.crystalTrader." + rand.nextInt(5)));
	}

	@Override
	public int guiID() {
		return 29;
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	public void addRecipies(final MerchantRecipeList list) {
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CrystalsBlue, 5, 1), new ItemStack(Itemizer.CoinsCrystevia, 32)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CrystalsGreen, 5, 1), new ItemStack(Itemizer.CoinsCrystevia, 32)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CrystalsPurple, 5, 1), new ItemStack(Itemizer.CoinsCrystevia, 32)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CrystalsRed, 5, 1), new ItemStack(Itemizer.CoinsCrystevia, 32)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CrystalsWhite, 5, 1), new ItemStack(Itemizer.CoinsCrystevia, 32)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CrystalsYellow, 5, 1), new ItemStack(Itemizer.CoinsCrystevia, 32)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CrystalsYellow, 60, 1), new ItemStack(Itemizer.CrystalsGreen, 60), new ItemStack(Weaponizer.Construct, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CrystalsBlue, 60, 1), new ItemStack(Itemizer.CrystalsPurple, 60), new ItemStack(Weaponizer.CrystalCarver, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CrystalsBlue, 60, 1), new ItemStack(Itemizer.CrystalsWhite, 60), new ItemStack(Weaponizer.BlastBarrel, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CrystalsPurple, 60, 1), new ItemStack(Itemizer.CrystalsYellow, 60), new ItemStack(Weaponizer.CrystalGreatblade, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CrystalsPurple, 40, 1), new ItemStack(Itemizer.CrystalsRed, 40), new ItemStack(Itemizer.GiantCrystal, 2)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CrystalsGreen, 40, 1), new ItemStack(Itemizer.CrystalsRed, 40), new ItemStack(Armorizer.CrystallisChestplate, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CrystalsBlue, 40, 1), new ItemStack(Itemizer.CrystalsPurple, 40), new ItemStack(Armorizer.CrystallisHelmet, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CrystalsYellow, 40, 1), new ItemStack(Itemizer.CrystalsWhite, 40), new ItemStack(Armorizer.CrystallisBoots, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CrystalsWhite, 40, 1), new ItemStack(Itemizer.CrystalsRed, 40), new ItemStack(Armorizer.CrystallisLeggings, 1)));
	}

	@Override
	public String mobName() {
		return "ToyMerchant";
	}

	static {

	}
}
