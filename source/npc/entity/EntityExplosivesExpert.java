package net.nevermine.npc.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;

import java.util.ArrayList;

public class EntityExplosivesExpert extends EntityNevermineVillager {
	private static ArrayList<RestockedRecipe> trades = new ArrayList<RestockedRecipe>();

	public EntityExplosivesExpert(final World var1) {
		super(var1);
	}

	@Override
	public void interaction(final EntityPlayer p) {
		p.addChatMessage(StringUtil.getLocale("message.dialogue.explosivesExpert." + rand.nextInt(5)));
	}

	@Override
	public int guiID() {
		return 35;
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	public void addRecipies(final MerchantRecipeList list) {
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CopperCoin, 2, 1), new ItemStack(Weaponizer.Grenade, 8)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CopperCoin, 2, 1), new ItemStack(Itemizer.DischargeCapsule, 8)));
		list.add(new RestockedRecipe(new ItemStack(Itemizer.CopperCoin, 2, 1), new ItemStack(Blocks.tnt, 8)));
	}

	@Override
	public String mobName() {
		return "ExplosivesExpert";
	}

	static {

	}
}
