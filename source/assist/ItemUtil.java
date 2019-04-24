package net.nevermine.assist;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.nevermine.gui.pouch.ItemRunePouch;
import net.nevermine.item.ItemRune;
import net.nevermine.izer.Itemizer;

import java.util.*;

public class ItemUtil {
	private static Random rand = new Random();

	public static HashSet<ItemStack> getRandomRunes(final int amount) {
		HashMap<Item, Integer> runeMap = new HashMap<Item, Integer>();

		for (int i = 0; i < amount; i++) {
			Item rune = getRandomRune();

			if (runeMap.containsKey(rune)) {
				runeMap.put(rune, runeMap.get(rune) + 1);
			}
			else {
				runeMap.put(rune, 1);
			}
		}

		HashSet<ItemStack> stacks = new HashSet<ItemStack>();

		for (Item it : runeMap.keySet()) {
			ItemStack stack = new ItemStack(it, runeMap.get(it));

			stacks.add(stack);
		}

		return stacks;
	}

	public static Item getRandomRune() {
		switch (rand.nextInt(14)) {
			case 0:
				return Itemizer.WindRune;
			case 1:
				return Itemizer.FireRune;
			case 2:
				return Itemizer.WaterRune;
			case 3:
				return Itemizer.WitherRune;
			case 4:
				return Itemizer.EnergyRune;
			case 5:
				return Itemizer.LunarRune;
			case 6:
				return Itemizer.KineticRune;
			case 7:
				return Itemizer.DistortionRune;
			case 8:
				return Itemizer.PoisonRune;
			case 9:
				return Itemizer.StrikeRune;
			case 10:
				return Itemizer.PowerRune;
			case 11:
				return Itemizer.StormRune;
			case 12:
				return Itemizer.LifeRune;
			case 13:
				return Itemizer.CompassRune;
			default:
				break;
		}

		return Itemizer.WindRune;
	}

	public static boolean tryConsumeRunes(HashMap<ItemRune, Integer> runes, EntityPlayer player, boolean applyBoosts, ItemStack heldItem) {
		if (player.capabilities.isCreativeMode)
			return true;

		final ArrayList<Integer> pouchSlots = new ArrayList<Integer>();
		final ArrayList<Integer> runeSlots = new ArrayList<Integer>();
		final HashMap<ItemRune, Integer> runeCounter = new HashMap<ItemRune, Integer>();
		final HashMap<ItemRune, Integer> finalCounter = new HashMap<ItemRune, Integer>();

		boolean archmage = applyBoosts && EnchantmentHelper.getEnchantmentLevel(ConfigurationHelper.earchmage, heldItem) > 0;
		boolean nightmareArmor = applyBoosts && ArmorUtil.isNightmareArmor(player);

		for (ItemRune r : runes.keySet()) {
			int cnt = runes.get(r);

			if (applyBoosts && nightmareArmor) {
				cnt--;

				if (cnt <= 0)
					continue;
			}

			if (applyBoosts && archmage) {
				cnt = Math.max(1, cnt / 2);
			}

			runeCounter.put(r, cnt);
			finalCounter.put(r, cnt);
		}

		runeFinder:
		for (int i = 0; i < 35; i++) {
			ItemStack st = player.inventory.getStackInSlot(i);

			if (st != null && st.getItem() instanceof ItemRunePouch) {
				for (int j = 0; j < 18; j++) {
					ItemStack st2 = ItemRunePouch.getStackInSlot(st, j);

					if (st2 != null && runeCounter.containsKey(st2.getItem())) {
						pouchSlots.add(i);
						runeSlots.add(j);

						int cnt = runeCounter.get(st2.getItem());

						cnt -= st2.stackSize;

						if (cnt > 0) {
							runeCounter.put((ItemRune)st2.getItem(), cnt);
						}
						else {
							runeCounter.remove(st2.getItem());
						}
					}

					if (runeCounter.isEmpty())
						break runeFinder;
				}
			}
		}

		if (runeCounter.isEmpty()) {
			for (ItemRune r : finalCounter.keySet()) {
				int cnt = finalCounter.get(r);

				for (int i = 0; i < pouchSlots.size(); i++) {
					ItemStack pouch = player.inventory.getStackInSlot(pouchSlots.get(i));
					ItemStack rune = ItemRunePouch.getStackInSlot(pouch, runeSlots.get(i));

					if (rune == null || rune.getItem() != r)
						continue;

					if (cnt - rune.stackSize >= 0) {
						cnt -= rune.stackSize;
						ItemRunePouch.setStackInSlot(pouch, runeSlots.get(i), null);
					}
					else {
						ItemRunePouch.decrStackSize(pouch, runeSlots.get(i), cnt);
						break;
					}
				}
			}

			return true;
		}

		return false;
	}

	public static ItemStack removeEnchantment(ItemStack stack, int id) {
		ItemStack newStack = stack.copy();
		Map<Enchantment, Integer> enchants =  EnchantmentHelper.getEnchantments(newStack);

		if (enchants.containsKey(id)) {
			enchants.remove(id);
			EnchantmentHelper.setEnchantments(enchants, newStack);
		}

		return newStack;
	}
}
