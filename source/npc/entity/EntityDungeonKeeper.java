package net.nevermine.npc.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;

public class EntityDungeonKeeper extends EntityNevermineVillager {
	public EntityDungeonKeeper(final World var1) {
		super(var1);
	}

	@Override
	public void interaction(final EntityPlayer p) {
		p.addChatMessage(StringUtil.getLocale("message.dialogue.dungeonKeeper"));
	}

	@Override
	public int guiID() {
		return 37;
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	public boolean isEntityInvulnerable() {
		return true;
	}

	@Override
	public void addRecipies(final MerchantRecipeList var2) {
		var2.add(new RestockedRecipe(new ItemStack(Itemizer.GoldCoin, 10, 1), new ItemStack(Itemizer.StartingCoin, 1)));
		var2.add(new RestockedRecipe(new ItemStack(Itemizer.GoldCoin, 2, 1), new ItemStack(Weaponizer.Vulcane, 1)));
	}

	@Override
	public String mobName() {
		return "DungeonKeeper";
	}
}
