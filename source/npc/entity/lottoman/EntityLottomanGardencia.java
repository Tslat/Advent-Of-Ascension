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

public class EntityLottomanGardencia extends EntityNevermineVillager {
	private static ArrayList<RestockedRecipe> trades = new ArrayList<RestockedRecipe>();

	public EntityLottomanGardencia(final World var1) {
		super(var1);
	}

	public boolean getCanSpawnHere() {
		return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	@Override
	public void interaction(final EntityPlayer p) {
		p.addChatMessage(StringUtil.getLocale("message.dialogue.lottoManGardencia." + rand.nextInt(5)));
	}

	@Override
	public int guiID() {
		return 5;
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	public void addRecipies(final MerchantRecipeList list) {
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CoinsGardencia, 10, 1), new ItemStack(Totemizer.TotemRosidianArchergun)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CoinsGardencia, 10, 1), new ItemStack(Totemizer.TotemFlowercorne)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CoinsGardencia, 40, 1), new ItemStack(Itemizer.CoinsGardencia, 35), new ItemStack(Totemizer.TotemRosidianArchergun, 10)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CoinsGardencia, 40, 1), new ItemStack(Itemizer.CoinsGardencia, 35), new ItemStack(Totemizer.TotemFlowercorne, 10)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.GoldCoin, 5, 1), new ItemStack(Itemizer.MagicRepairDust, 1)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.GoldCoin, 50, 1), new ItemStack(Itemizer.MagicRepairDust, 15)));
	}

	@Override
	public String mobName() {
		return "Lottoman";
	}

	static {

	}
}
