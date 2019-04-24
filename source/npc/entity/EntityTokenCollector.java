package net.nevermine.npc.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.izer.equipment.Weaponizer;

public class EntityTokenCollector extends EntityNevermineVillager {
	public EntityTokenCollector(final World var1) {
		super(var1);
	}

	@Override
	public void interaction(final EntityPlayer p) {
		p.addChatMessage(StringUtil.getLocale("message.dialogue.tokenCollector." + rand.nextInt(5)));
	}

	@Override
	public int guiID() {
		return 38;
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
		var2.add(new RestockedRecipe(new ItemStack(Itemizer.DungeonTokens, 3, 1), new ItemStack(Itemizer.AugmentFire, 1)));
		var2.add(new RestockedRecipe(new ItemStack(Itemizer.DungeonTokens, 6, 1), new ItemStack(Itemizer.AugmentImpairment, 1)));
		var2.add(new RestockedRecipe(new ItemStack(Itemizer.DungeonTokens, 10, 1), new ItemStack(Itemizer.AugmentPoison, 1)));
		var2.add(new RestockedRecipe(new ItemStack(Itemizer.DungeonTokens, 14, 1), new ItemStack(Itemizer.AugmentPower, 1)));
		var2.add(new RestockedRecipe(new ItemStack(Itemizer.DungeonTokens, 20, 1), new ItemStack(Itemizer.AugmentWither, 1)));
		var2.add(new RestockedRecipe(new ItemStack(Itemizer.DungeonTokens, 30, 1), new ItemStack(Itemizer.AugmentEquality, 1)));
		var2.add(new RestockedRecipe(new ItemStack(Itemizer.DungeonTokens, 60, 1), new ItemStack(Itemizer.AugmentBattle, 1)));
		var2.add(new RestockedRecipe(new ItemStack(Itemizer.DungeonTokens, 10, 1), new ItemStack(Weaponizer.VulcammerSword, 1)));
		var2.add(new RestockedRecipe(new ItemStack(Itemizer.DungeonTokens, 30, 1), new ItemStack(Weaponizer.EverfightStaff, 1)));
		var2.add(new RestockedRecipe(new ItemStack(Itemizer.DungeonTokens, 30, 1), new ItemStack(Weaponizer.EvermightStaff, 1)));
		var2.add(new RestockedRecipe(new ItemStack(Itemizer.DungeonTokens, 40, 1), new ItemStack(Weaponizer.Odious, 1)));
		var2.add(new RestockedRecipe(new ItemStack(Itemizer.DungeonTokens, 50, 1), new ItemStack(Weaponizer.Deadlock, 1)));
		var2.add(new RestockedRecipe(new ItemStack(Itemizer.DungeonTokens, 1, 1), new ItemStack(SpecialBlockizer.ImmortalBanner, 3)));
	}

	@Override
	public String mobName() {
		return "Token Trader";
	}
}
