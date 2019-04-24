package net.tslat.aoa3.utils;

import com.google.common.collect.Multimap;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.capabilities.providers.AdventPlayerProvider;
import net.tslat.aoa3.common.registration.EnchantmentsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.misc.ItemTieredBullet;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.library.Enums;

import java.util.*;

public class ItemUtil {

	public static void setAttribute(Multimap<String, AttributeModifier> map, IAttribute att, UUID id, double value) {
		final Collection<AttributeModifier> modifiers = map.get(att.getName());
		final Optional<AttributeModifier> mod = modifiers.stream().filter(attributeModifier -> attributeModifier.getID().equals(id)).findFirst();

		if (mod.isPresent()) {
			final AttributeModifier existingMod = mod.get();
			modifiers.remove(existingMod);
			modifiers.add(new AttributeModifier(existingMod.getID(), existingMod.getName(), value, existingMod.getOperation()));
		}
	}

	public static void multiplyAttribute(Multimap<String, AttributeModifier> map, IAttribute att, UUID id, double multiplier) {
		final Collection<AttributeModifier> modifiers = map.get(att.getName());
		final Optional<AttributeModifier> mod = modifiers.stream().filter(attributeModifier -> attributeModifier.getID().equals(id)).findFirst();

		if (mod.isPresent()) {
			final AttributeModifier existingMod = mod.get();
			modifiers.remove(existingMod);
			modifiers.add(new AttributeModifier(existingMod.getID(), existingMod.getName(), existingMod.getAmount() * multiplier, existingMod.getOperation()));
		}
	}

	public static double getStackAttributeSpeedValue(ItemStack stack, IAttribute attribute, EntityPlayer player, EntityEquipmentSlot equipmentSlot, UUID attributeUUID) {
		for (Map.Entry<String, AttributeModifier> entry : stack.getItem().getAttributeModifiers(equipmentSlot, stack).entries()) {
			AttributeModifier mod = entry.getValue();

			if (mod.getID().equals(attributeUUID)) {
				double value = mod.getAmount();

				value += player.getEntityAttribute(attribute).getBaseValue();

				return mod.getOperation() != 1 && mod.getOperation() != 2 ? value : value * 100;

			}
		}

		return 0;
	}

	public static boolean checkCooledItemProc(EntityLivingBase holder, float percentageChance) {
		float percent = (holder instanceof EntityPlayer ? AdventOfAscension.rand.nextFloat() / ((EntityPlayer)holder).getCooledAttackStrength(0.000001f) : AdventOfAscension.rand.nextFloat());

		return percentageChance > percent;
	}

	public static Item findAndConsumeBullet(final EntityLivingBase shooter, final BaseGun gun, boolean consume, ItemStack weaponStack) {
		if (!(shooter instanceof EntityPlayer)) {
			return ItemRegister.bulletLimonite;
		}
		else {
			EntityPlayer pl = (EntityPlayer)shooter;

			int greed = 1 + EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.greed, weaponStack);

			if (pl.getHeldItem(EnumHand.OFF_HAND).getItem() instanceof ItemTieredBullet) {
				return consumeBullet(pl.getHeldItem(EnumHand.OFF_HAND), pl, gun, consume, greed);
			}
			else if (pl.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemTieredBullet) {
				return consumeBullet(pl.getHeldItem(EnumHand.MAIN_HAND), pl, gun, consume, greed);
			}
			else {
				ItemStack stack;

				for (int i = 0; i < pl.inventory.getSizeInventory(); ++i) {
					stack = pl.inventory.getStackInSlot(i);

					if (stack.getItem() instanceof ItemTieredBullet)
						return consumeBullet(stack, pl, gun, consume, greed);
				}
			}

			if (pl.capabilities.isCreativeMode)
				return ItemRegister.bulletLimonite;
		}
		return null;
	}

	private static Item consumeBullet(ItemStack stack, EntityPlayer player, BaseGun gun, boolean consume, int amount) {
		if (!player.capabilities.isCreativeMode && consume)
			stack.shrink(amount);

		return stack.getItem();
	}

	public static Item findAndConsumeSpecialBullet(final EntityLivingBase shooter, final BaseGun gun, boolean consume, Item ammo, ItemStack weaponStack) {
		if (shooter instanceof EntityPlayer && !((EntityPlayer)shooter).capabilities.isCreativeMode) {
			EntityPlayer pl = (EntityPlayer)shooter;
			ItemStack stack = ItemStack.EMPTY;
			int amount = 1 + EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.greed, weaponStack);

			if (pl.getHeldItem(EnumHand.OFF_HAND).getItem() == ammo) {
				stack = pl.getHeldItem(EnumHand.OFF_HAND);

				if (stack.isEmpty())
					return null;

				return consumeBullet(stack, pl, gun, consume, amount);
			}
			else if (pl.getHeldItem(EnumHand.MAIN_HAND).getItem() == ammo) {
				stack = pl.getHeldItem(EnumHand.MAIN_HAND);

				if (stack.isEmpty())
					return null;

				return consumeBullet(stack, pl, gun, consume, amount);
			}
			else {
				for (int i = 0; i < pl.inventory.getSizeInventory(); ++i) {
					if (pl.inventory.getStackInSlot(i).getItem() == ammo) {
						stack = pl.inventory.getStackInSlot(i);
						break;
					}
				}

				if (stack.isEmpty())
					return null;

				return consumeBullet(stack, pl, gun, consume, amount);
			}
		}

		return ammo;
	}

	public static boolean findAndConsumeRunes(HashMap<RuneItem, Integer> runeMap, EntityPlayer player, boolean allowBuffs, ItemStack heldItem) {
		if (player.capabilities.isCreativeMode)
			return true;


		Enums.ArmourSets armour = player.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null).getArmourSetType();
		int archmage = allowBuffs ? EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.archmage, heldItem) : 0;
		boolean nightmareArmour = allowBuffs && (armour == Enums.ArmourSets.NIGHTMARE || armour == Enums.ArmourSets.RUNATION);
		boolean greed = EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.greed, heldItem) > 0;
		HashMap<RuneItem, Integer> requiredRunes = new HashMap<RuneItem, Integer>();

		for (Map.Entry<RuneItem, Integer> runeEntry : runeMap.entrySet()) {
			if (!allowBuffs || (archmage == 0  && !nightmareArmour && !greed)) {
				requiredRunes.putAll(runeMap);
				break;
			}

			int amount = runeEntry.getValue();

			if (greed)
				amount += 2;

			if (archmage > 0)
				amount -= archmage;

			if (nightmareArmour)
				--amount;

			if (amount <= 0)
				amount = 1;

			requiredRunes.put(runeEntry.getKey(), amount);
		}

		if (requiredRunes.isEmpty())
			return true;

		HashSet<Integer> runeSlots = new HashSet<Integer>();
		HashMap<RuneItem, Integer> runeCounter = new HashMap<RuneItem, Integer>(requiredRunes);

		ItemStack mainHandStack = player.getHeldItem(EnumHand.OFF_HAND);
		ItemStack offHandStack = player.getHeldItem(EnumHand.MAIN_HAND);

		if (mainHandStack.getItem() instanceof RuneItem) {
			RuneItem type = (RuneItem)mainHandStack.getItem();

			if (runeCounter.containsKey(type)) {
				int amount = runeCounter.get(type);

				runeSlots.add(-1);
				amount -= mainHandStack.getCount();

				if (amount > 0) {
					runeCounter.put(type, amount);
				}
				else {
					runeCounter.remove(type);
				}
			}
		}

		if (offHandStack.getItem() instanceof RuneItem) {
			RuneItem type = (RuneItem)offHandStack.getItem();

			if (runeCounter.containsKey(type)) {
				int amount = runeCounter.get(type);

				runeSlots.add(-2);
				amount -= offHandStack.getCount();

				if (amount > 0) {
					runeCounter.put(type, amount);
				}
				else {
					runeCounter.remove(type);
				}
			}
		}

		for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
			ItemStack stack = player.inventory.getStackInSlot(i);

			if (stack.getItem() instanceof RuneItem) {
				RuneItem type = (RuneItem)stack.getItem();

				if (runeCounter.containsKey(type)) {
					int amount = runeCounter.get(type);

					runeSlots.add(i);
					amount -= stack.getCount();

					if (amount > 0) {
						runeCounter.put(type, amount);
					}
					else {
						runeCounter.remove(type);
					}

					if (runeCounter.isEmpty())
						break;
				}
			}
		}

		if (runeCounter.isEmpty()) {
			if (runeSlots.contains(-1)) {
				ItemStack rune = player.getHeldItem(EnumHand.MAIN_HAND);
				RuneItem type = (RuneItem)rune.getItem();
				int amount = requiredRunes.get(type);
				int remaining = amount - rune.getCount();

				rune.shrink(amount);

				if (remaining <= 0) {
					requiredRunes.remove(type);
				}
				else {
					requiredRunes.put(type, remaining);
				}

				runeSlots.remove(-1);
			}

			if (runeSlots.contains(-2)) {
				ItemStack rune = player.getHeldItem(EnumHand.OFF_HAND);
				RuneItem type = (RuneItem)rune.getItem();
				int amount = requiredRunes.get(type);
				int remaining = amount - rune.getCount();

				rune.shrink(amount);

				if (remaining <= 0) {
					requiredRunes.remove(type);
				}
				else {
					requiredRunes.put(type, remaining);
				}

				runeSlots.remove(-2);
			}

			for (int slotId : runeSlots) {
				ItemStack rune = player.inventory.getStackInSlot(slotId);
				RuneItem type = (RuneItem)rune.getItem();
				int amount = requiredRunes.get(type);
				int remaining = amount - rune.getCount();

				rune.shrink(amount);

				if (remaining <= 0) {
					requiredRunes.remove(type);
				}
				else {
					requiredRunes.put(type, remaining);
				}
			}

			return true;
		}

		return false;
	}

	public static ItemStack removeEnchantment(ItemStack stack, Enchantment ench) {
		ItemStack newStack = stack.copy();
		Map<Enchantment, Integer> enchants =  EnchantmentHelper.getEnchantments(newStack);

		if (enchants.containsKey(ench)) {
			enchants.remove(ench);
			EnchantmentHelper.setEnchantments(enchants, newStack);
		}

		return newStack;
	}

	public static void givePlayerItemOrDrop(EntityPlayer player, ItemStack stack) {
		if (!player.inventory.addItemStackToInventory(stack))
			player.entityDropItem(stack, 0.5f);

		player.inventoryContainer.detectAndSendChanges();
	}

	public static boolean consumeItem(EntityPlayer player, ItemStack stack) {
		for (ItemStack invStack : player.inventory.mainInventory) {
			if (!invStack.isEmpty() && areStacksFunctionallyEqual(invStack, stack)) {
				if (!player.capabilities.isCreativeMode)
					invStack.shrink(1);

				return true;
			}
		}

		return false;
	}

	public static boolean consumeMultipleItemsSafely(EntityPlayer player, ItemStack... stacks) {
		if (stacks != null && stacks.length > 0) {
			HashSet<ItemStack> foundStacks = new HashSet<ItemStack>();

			for (ItemStack invStack : player.inventory.mainInventory) {
				if (!invStack.isEmpty()) {
					for (int i = 0; i < stacks.length; i++) {
						if (areStacksFunctionallyEqual(invStack, stacks[i])) {
							foundStacks.add(invStack);
							stacks[i] = ItemStack.EMPTY;
						}
					}
				}

				if (foundStacks.size() == stacks.length)
					break;
			}

			if (foundStacks.size() == stacks.length) {
				if (!player.capabilities.isCreativeMode) {
					for (ItemStack stack : foundStacks) {
						stack.shrink(1);
					}
				}

				return true;
			}
		}

		return false;
	}

	public static boolean areStacksFunctionallyEqual(ItemStack a, ItemStack b) {
		if (a.getItem() != b.getItem())
			return false;

		if (a.getItemDamage() != b.getItemDamage())
			return false;

		return a.getTagCompound() == null ? b.getTagCompound() == null : b.getTagCompound() != null && a.getTagCompound().equals(b.getTagCompound());
	}
}
