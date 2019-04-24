package net.nevermine.npc.entity.lottoman;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.Totemizer;
import net.nevermine.npc.entity.EntityNevermineVillager;
import net.nevermine.npc.entity.RestockedRecipe;

import java.util.ArrayList;

public class EntityLottomanShyrelands extends EntityNevermineVillager {
	private static ArrayList<RestockedRecipe> trades = new ArrayList<RestockedRecipe>();

	public EntityLottomanShyrelands(World var1) {
		super(var1);
	}

	public boolean getCanSpawnHere() {
		if (worldObj.checkNoEntityCollision(boundingBox)) {
		}

		return (worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty()) && (!worldObj.isAnyLiquid(boundingBox));
	}

	public void interaction(EntityPlayer p) {
		p.addChatMessage(StringUtil.getLocale("message.dialogue.lottoManShyrelands." + rand.nextInt(5)));
	}

	public int guiID() {
		return 5;
	}

	protected boolean canDespawn() {
		return false;
	}

	public void addRecipies(MerchantRecipeList list) {
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CoinsShyrelands, 10, 1), new ItemStack(Totemizer.TotemSkyStaff)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CoinsShyrelands, 10, 1), new ItemStack(Totemizer.TotemSoulBone)));

		list.add(new RestockedRecipe(new ItemStack(Itemizer.CoinsShyrelands, 40, 1), new ItemStack(Itemizer.CoinsShyrelands, 35), new ItemStack(Totemizer.TotemSkyStaff, 10)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CoinsShyrelands, 40, 1), new ItemStack(Itemizer.CoinsShyrelands, 35), new ItemStack(Totemizer.TotemSoulBone, 10)));

		list.add(new RestockedRecipe(new ItemStack(Itemizer.GoldCoin, 5, 1), new ItemStack(Itemizer.MagicRepairDust, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.GoldCoin, 50, 1), new ItemStack(Itemizer.MagicRepairDust, 15)));

		list.add(new RestockedRecipe(new ItemStack(Itemizer.CoinsShyrelands, 64, 1), new ItemStack(Itemizer.IngotShyregem, 5), new ItemStack(Itemizer.StrangeStone1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CoinsShyrelands, 64, 1), new ItemStack(Itemizer.IngotShyregem, 5), new ItemStack(Itemizer.StrangeStone2)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CoinsShyrelands, 64, 1), new ItemStack(Itemizer.IngotShyregem, 5), new ItemStack(Itemizer.StrangeStone3)));
	}

	public String mobName() {
		return "Lottoman";
	}

	static {

	}
}
