package net.nevermine.assist;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.nevermine.item.weapon.energy.BaseEnergy;
import net.nevermine.item.weapon.energy.BaseEnergyRapid;
import net.nevermine.item.weapon.greatblade.BaseGreatblade;
import net.nevermine.item.weapon.gun.BaseGun;
import net.nevermine.item.weapon.scythe.BaseScythe;
import net.nevermine.item.weapon.staff.BaseStaff;

public class RestrictedEnchant extends Enchantment {
	private int maxLevel;

	public RestrictedEnchant(final int id, final int weight, final EnumEnchantmentType x, final int max) {
		super(id, weight, x);
		maxLevel = max;
	}

	public boolean canApplyAtEnchantingTable(final ItemStack stack) {
		return false;
	}

	public boolean isAllowedOnBooks() {
		return false;
	}

	@Override
	public int getMaxLevel() {
		return maxLevel;
	}

	@Override
	public boolean canApply(ItemStack stack) {
		if (stack == null)
			return false;

		Item item = stack.getItem();
		switch (getName()) {
			case "enchantment.control":
				if (item instanceof BaseGun)
					return true;

				break;
			case "enchantment.shell":
				if (item instanceof BaseGun)
					return true;

				break;
			case "enchantment.recharge":
				if (item instanceof BaseEnergy || item instanceof BaseEnergyRapid)
					return true;

				break;
			case "enchantment.overpower":
				if (item instanceof BaseEnergy || item instanceof BaseEnergyRapid)
					return true;

				break;
			case "enchantment.crush":
				if (item instanceof BaseGreatblade)
					return true;

				break;
			case "enchantment.sever":
				if (item instanceof BaseGreatblade)
					return true;

				break;
			case "enchantment.archmage":
				if (item instanceof BaseStaff)
					return true;

				break;
			case "enchantment.windfury":
				if (item instanceof BaseScythe)
					return true;

				break;
			case "enchantment.slice":
				if (item instanceof BaseScythe)
					return true;

				break;
			case "enchantment.intervention":
				if (stack.getMaxStackSize() == 1)
					return true;

			default:
				break;
		}

		return false;
	}
}
