package net.nevermine.item.slab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.Itemizer;
import net.nevermine.resource.creation.creationHelper;
import net.nevermine.skill.creation.creationSkillHelper;

import java.util.List;
import java.util.Random;

import static net.nevermine.container.PlayerContainer.Skills.Creation;

public abstract class BaseSlab extends Item {
	private int cost;
	private int level;
	private EntityTameable minion;

	public BaseSlab(final int price, final int req) {
		cost = price;
		level = req;
		setCreativeTab(Itemizer.SlabTab);
	}

	public ItemStack onItemRightClick(final ItemStack stack, final World world, final EntityPlayer player) {
		if (!world.isRemote && PlayerContainer.getProperties(player).getLevel(Creation) >= level && (player.capabilities.isCreativeMode || (creationHelper.getProperties(player).useBar(cost) && player.inventory.consumeInventoryItem(this)))) {
			useSlab(world, stack, player);
			player.worldObj.playSoundAtEntity(player, "nevermine:Slab", 1.0f, 1.0f);
		}

		return stack;
	}

	public abstract void useSlab(final World p0, final ItemStack p1, final EntityPlayer p2);

	public void addBuff(final EntityTameable min, final EntityPlayer p) {
		if (creationSkillHelper.isWearingArmour(p)) {
			final int pick = new Random().nextInt(4);

			if (pick == 1) {
				min.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 1200, 1));
			}
			else if (pick == 2) {
				min.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 1200, 1));
			}
			else if (pick == 3) {
				min.addPotionEffect(new PotionEffect(Potion.regeneration.id, 1200, 1));
			}
			else {
				min.addPotionEffect(new PotionEffect(Potion.resistance.id, 1200, 1));
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.slab.cost", EnumChatFormatting.LIGHT_PURPLE, Integer.toString(cost / 600)));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.skillRequirement", EnumChatFormatting.RED, Integer.toString(level), StringUtil.getLocaleString("skills.creation.name")));
	}
}
