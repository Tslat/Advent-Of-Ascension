package net.tslat.aoa3.utils.skills;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.player.PlayerDataManager;
import net.tslat.aoa3.utils.player.PlayerUtil;

public class InfusionUtil {
	public static void infuseStack(EntityPlayer pl, ItemStack stack, Enchantment enchant) {
		PlayerDataManager plData = PlayerUtil.getAdventPlayer(pl);

		int lvl = plData.stats().getLevel(Enums.Skills.INFUSION);

		switch (enchant.getRegistryName().getPath()) {
			case "sharpness":
				if (lvl < 16) {
					stack.addEnchantment(enchant, 2);
				}
				else if (lvl < 41) {
					stack.addEnchantment(enchant, 3);
				}
				else if (lvl < 81) {
					stack.addEnchantment(enchant, 4);
				}
				else {
					stack.addEnchantment(enchant, 5);
				}
				break;
			case "shell":
				if (lvl < 37) {
					stack.addEnchantment(enchant, 1);
				}
				else if (lvl < 78) {
					stack.addEnchantment(enchant, 2);
				}
				else {
					stack.addEnchantment(enchant, 3);
				}
				break;
			case "power":
				if (lvl < 16) {
					stack.addEnchantment(enchant, 2);
				}
				else if (lvl < 41) {
					stack.addEnchantment(enchant, 3);
				}
				else if (lvl < 81) {
					stack.addEnchantment(enchant, 4);
				}
				else {
					stack.addEnchantment(enchant, 5);
				}
				break;
			case "sever":
				if (lvl < 61) {
					stack.addEnchantment(enchant, 1);
				}
				else if (lvl < 86) {
					stack.addEnchantment(enchant, 2);
				}
				else {
					stack.addEnchantment(enchant, 3);
				}
				break;
			case "slice":
				if (lvl < 61) {
					stack.addEnchantment(enchant, 1);
				}
				else if (lvl < 86) {
					stack.addEnchantment(enchant, 2);
				}
				else {
					stack.addEnchantment(enchant, 3);
				}
				break;
			case "unbreaking":
				if (lvl < 61) {
					stack.addEnchantment(enchant, 1);
				}
				else if (lvl < 81) {
					stack.addEnchantment(enchant, 2);
				}
				else if (lvl < 96) {
					stack.addEnchantment(enchant, 3);
				}
				else {
					stack.addEnchantment(enchant, 4);
				}
				break;
			case "knockback":
				if (lvl < 21) {
					stack.addEnchantment(enchant, 1);
				}
				else if (lvl < 46) {
					stack.addEnchantment(enchant, 2);
				}
				else if (lvl < 66) {
					stack.addEnchantment(enchant, 3);
				}
				else {
					stack.addEnchantment(enchant, 4);
				}
				break;
			case "punch":
				if (lvl < 21) {
					stack.addEnchantment(enchant, 1);
				}
				else if (lvl < 46) {
					stack.addEnchantment(enchant, 2);
				}
				else if (lvl < 66) {
					stack.addEnchantment(enchant, 3);
				}
				else {
					stack.addEnchantment(enchant, 4);
				}
				break;
			case "control":
				if (lvl < 56) {
					stack.addEnchantment(enchant, 1);
				}
				else {
					stack.addEnchantment(enchant, 2);
				}
				break;
			case "crush":
				if (lvl < 51) {
					stack.addEnchantment(enchant, 1);
				}
				else {
					stack.addEnchantment(enchant, 2);
				}
				break;
			case "efficiency":
				if (lvl < 61) {
					stack.addEnchantment(enchant, 3);
				}
				else if (lvl < 81) {
					stack.addEnchantment(enchant, 4);
				}
				else if (lvl < 92) {
					stack.addEnchantment(enchant, 5);
				}
				else {
					stack.addEnchantment(enchant, 6);
				}
				break;
			case "recharge":
				if (lvl < 61) {
					stack.addEnchantment(enchant, 1);
				}
				else {
					stack.addEnchantment(enchant, 2);
				}
				break;
			case "protection":
				if (lvl < 47) {
					stack.addEnchantment(enchant, 1);
				}
				else if (lvl < 67) {
					stack.addEnchantment(enchant, 2);
				}
				else if (lvl < 87) {
					stack.addEnchantment(enchant, 3);
				}
				else {
					stack.addEnchantment(enchant, 4);
				}
				break;
			case "archmage":
				if (lvl < 85) {
					stack.addEnchantment(enchant, 1);
				}
				else {
					stack.addEnchantment(enchant, 2);
				}
				break;
			case "lure":
				if (lvl < 16) {
					stack.addEnchantment(enchant, 2);
				}
				else if (lvl < 41) {
					stack.addEnchantment(enchant, 3);
				}
				else if (lvl < 81) {
					stack.addEnchantment(enchant, 4);
				}
				else {
					stack.addEnchantment(enchant, 5);
				}
				break;
			case "luck_of_the_sea":
				if (lvl < 21) {
					stack.addEnchantment(enchant, 2);
				}
				else if (lvl < 46) {
					stack.addEnchantment(enchant, 3);
				}
				else if (lvl < 66) {
					stack.addEnchantment(enchant, 4);
				}
				else {
					stack.addEnchantment(enchant, 5);
				}
				break;
			default:
				break;
		}
	}
}
