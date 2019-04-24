package net.nevermine.npc.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;

import java.util.ArrayList;

public class EntityAssassin extends EntityNevermineVillager {
	private static ArrayList<RestockedRecipe> trades = new ArrayList<RestockedRecipe>();

	public EntityAssassin(final World var1) {
		super(var1);
	}

	public boolean getCanSpawnHere() {
		return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	@Override
	public void interaction(final EntityPlayer p) {
		p.addChatMessage(StringUtil.getLocale("message.dialogue.assassin." + rand.nextInt(5)));
	}

	@Override
	public int guiID() {
		return 30;
	}

	@Override
	protected boolean canDespawn() {
		return true;
	}

	@Override
	public void addRecipies(final MerchantRecipeList list) {
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CopperCoin, 2, 1), new ItemStack(Weaponizer.SliceStar, 8)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CopperCoin, 2, 1), new ItemStack(Weaponizer.GooBall, 8)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CopperCoin, 2, 1), new ItemStack(Weaponizer.Chakram, 8)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CopperCoin, 2, 1), new ItemStack(Weaponizer.HellFire, 8)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CopperCoin, 2, 1), new ItemStack(Weaponizer.Vulkram, 4)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.ThornyPetals, 4, 1), new ItemStack(Weaponizer.SliceStar, 12)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.ThornyPetals, 4, 1), new ItemStack(Weaponizer.Chakram, 12)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.ThornyPetals, 4, 1), new ItemStack(Weaponizer.Vulkram, 6)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CopperCoin, 2, 1), new ItemStack(Itemizer.MetalPellet, 16)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CopperCoin, 2, 1), new ItemStack(Itemizer.MetalSlug, 16)));
	}

	@Override
	public String mobName() {
		return "Assassin";
	}

	static {

	}
}
