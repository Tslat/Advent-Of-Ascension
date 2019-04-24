package net.nevermine.npc.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Armorizer;

import java.util.ArrayList;

public class EntityGorbEngineer extends EntityNevermineVillager {
	private static ArrayList<RestockedRecipe> trades = new ArrayList<RestockedRecipe>();

	public EntityGorbEngineer(final World var1) {
		super(var1);
	}

	public boolean getCanSpawnHere() {
		return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	@Override
	public void interaction(final EntityPlayer p) {
		p.addChatMessage(StringUtil.getLocale("message.dialogue.gorbEngineer." + rand.nextInt(5)));
	}

	@Override
	public int guiID() {
		return 3;
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	public void addRecipies(final MerchantRecipeList list) {
		list.add(new RestockedRecipe(new ItemStack(Itemizer.NightmareFlakes, 10, 1), new ItemStack(Itemizer.GhostlyPowder, 10), new ItemStack(Armorizer.NightmareBoots)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.NightmareFlakes, 10, 1), new ItemStack(Itemizer.GhostlyPowder, 10), new ItemStack(Armorizer.NightmareHelmet, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.NightmareFlakes, 10, 1), new ItemStack(Itemizer.GhostlyPowder, 10), new ItemStack(Armorizer.NightmareChestplate, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.NightmareFlakes, 10, 1), new ItemStack(Itemizer.GhostlyPowder, 10), new ItemStack(Armorizer.NightmareLeggings, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.Phantasm, 10, 1), new ItemStack(Itemizer.TrollSkull, 10), new ItemStack(Armorizer.PhantasmBoots)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.Phantasm, 10, 1), new ItemStack(Itemizer.TrollSkull, 10), new ItemStack(Armorizer.PhantasmHelmet, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.Phantasm, 10, 1), new ItemStack(Itemizer.TrollSkull, 10), new ItemStack(Armorizer.PhantasmChestplate, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.Phantasm, 10, 1), new ItemStack(Itemizer.TrollSkull, 10), new ItemStack(Armorizer.PhantasmLeggings, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.RuneStone, 10, 1), new ItemStack(Armorizer.RunicBoots)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.RuneStone, 10, 1), new ItemStack(Armorizer.RunicHelmet)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.RuneStone, 10, 1), new ItemStack(Armorizer.RunicChestplate)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.RuneStone, 10, 1), new ItemStack(Armorizer.RunicLeggings)));
	}

	@Override
	public String mobName() {
		return "GorbEngineer";
	}

	static {

	}
}
