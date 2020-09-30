package net.tslat.aoa3.utils;

import com.google.common.collect.Multimap;
import net.minecraft.client.Minecraft;
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
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.gui.mainwindow.AdventGuiTabPlayer;
import net.tslat.aoa3.common.registration.ArmourRegister;
import net.tslat.aoa3.common.registration.EnchantmentsRegister;
import net.tslat.aoa3.hooks.ThirdPartyInteractions;
import net.tslat.aoa3.hooks.ic2.IC2Compat;
import net.tslat.aoa3.item.SkillItem;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.library.misc.AoAAttributes;
import net.tslat.aoa3.utils.player.PlayerDataManager;
import net.tslat.aoa3.utils.player.PlayerUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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

	public static double getStackAttributeValue(ItemStack stack, IAttribute baseAttribute, EntityPlayer player, EntityEquipmentSlot equipmentSlot, UUID attributeUUID) {
		for (Map.Entry<String, AttributeModifier> entry : stack.getItem().getAttributeModifiers(equipmentSlot, stack).entries()) {
			AttributeModifier mod = entry.getValue();

			if (mod.getID().equals(attributeUUID)) {
				double value = mod.getAmount();

				if (mod.getID().equals(AoAAttributes.VANILLA_ATTACK_SPEED))
					value += player.getEntityAttribute(baseAttribute).getBaseValue();

				return mod.getOperation() != 1 && mod.getOperation() != 2 ? value : value * 100;
			}
		}

		return 0;
	}

	public static boolean checkCooledItemProc(EntityLivingBase holder, float percentageChance) {
		float percent = (holder instanceof EntityPlayer ? AdventOfAscension.rand.nextFloat() / ((EntityPlayer)holder).getCooledAttackStrength(0.000001f) : AdventOfAscension.rand.nextFloat());

		return percentageChance > percent;
	}

	public static boolean findAndConsumeRunes(HashMap<RuneItem, Integer> runeMap, EntityPlayer player, boolean allowBuffs, @Nonnull ItemStack heldItem) {
		if (player.capabilities.isCreativeMode)
			return true;

		Enums.ArmourSets armour = PlayerUtil.getAdventPlayer(player).equipment().getCurrentFullArmourSet();
		int archmage = allowBuffs ? EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.ARCHMAGE, heldItem) : 0;
		boolean nightmareArmour = allowBuffs && armour == Enums.ArmourSets.NIGHTMARE;
		boolean greed = EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.GREED, heldItem) > 0;
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

		if (!runeCounter.isEmpty() && offHandStack.getItem() instanceof RuneItem) {
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

		if (!runeCounter.isEmpty()) {
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

				if (requiredRunes.isEmpty())
					break;
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

	public static void givePlayerMultipleItems(EntityPlayer pl, ItemStack... stacks) {
		givePlayerMultipleItems(pl, Arrays.asList(stacks));
	}

	public static void givePlayerMultipleItems(EntityPlayer pl, Collection<ItemStack> stacks) {
		for (ItemStack stack : stacks) {
			if (!pl.inventory.addItemStackToInventory(stack))
				pl.entityDropItem(stack, 0.5f);
		}

		pl.inventoryContainer.detectAndSendChanges();
	}

	public static void givePlayerItemOrDrop(EntityPlayer player, ItemStack stack) {
		if (stack.isEmpty())
			return;

		if (!player.inventory.addItemStackToInventory(stack))
			player.entityDropItem(stack, 0.5f);

		player.inventoryContainer.detectAndSendChanges();
	}

	@Nullable
	public static Item findInventoryItemType(EntityPlayer player, Class<? extends Item> itemType, boolean consumeItem, int amount) {
		if (amount <= 0)
			return null;

		if (amount == 1) {
			ItemStack checkStack;

			if (itemType.isInstance((checkStack = player.getHeldItem(EnumHand.MAIN_HAND)).getItem()) && !checkStack.isEmpty()) {
				if (consumeItem && !player.capabilities.isCreativeMode)
					checkStack.shrink(1);

				return checkStack.getItem();
			}
			else if (itemType.isInstance((checkStack = player.getHeldItem(EnumHand.OFF_HAND)).getItem()) && !checkStack.isEmpty()) {
				if (consumeItem && !player.capabilities.isCreativeMode)
					checkStack.shrink(1);

				return checkStack.getItem();
			}
			else {
				for (ItemStack checkStack2 : player.inventory.mainInventory) {
					if (!checkStack2.isEmpty() && itemType.isInstance(checkStack2.getItem())) {
						if (consumeItem && !player.capabilities.isCreativeMode)
							checkStack2.shrink(1);

						return checkStack2.getItem();
					}
				}

				for (ItemStack checkStack2 : player.inventory.armorInventory) {
					if (!checkStack2.isEmpty() && itemType.isInstance(checkStack2.getItem())) {
						if (consumeItem && !player.capabilities.isCreativeMode)
							checkStack2.shrink(1);

						return checkStack2.getItem();
					}
				}
			}

			return null;
		}
		else {
			ArrayList<ItemStack> matchedStacks = new ArrayList<ItemStack>();
			int foundCount = 0;
			ItemStack checkStack;

			if (itemType.isInstance((checkStack = player.getHeldItem(EnumHand.MAIN_HAND)).getItem()) && !checkStack.isEmpty()) {
				matchedStacks.add(checkStack);
				foundCount += checkStack.getCount();
			}

			if (foundCount < amount && itemType.isInstance((checkStack = player.getHeldItem(EnumHand.OFF_HAND)).getItem()) && !checkStack.isEmpty()) {
				matchedStacks.add(checkStack);
				foundCount += checkStack.getCount();
			}

			if (foundCount < amount) {
				for (ItemStack checkStack2 : player.inventory.mainInventory) {
					if (!checkStack2.isEmpty() && itemType.isInstance(checkStack2.getItem())) {
						matchedStacks.add(checkStack2);
						foundCount += checkStack2.getCount();

						if (foundCount >= amount)
							break;
					}
				}
			}

			if (foundCount < amount) {
				for (ItemStack checkStack2 : player.inventory.armorInventory) {
					if (!checkStack2.isEmpty() && itemType.isInstance(checkStack2.getItem())) {
						matchedStacks.add(checkStack2);
						foundCount += checkStack2.getCount();

						if (foundCount >= amount)
							break;
					}
				}
			}

			if (foundCount < amount)
				return null;

			Item matchedItem = matchedStacks.get(0).getItem();

			if (!consumeItem || player.capabilities.isCreativeMode)
				return matchedItem;

			for (ItemStack matchedStack : matchedStacks) {
				int consumeAmount = Math.min(matchedStack.getCount(), foundCount);

				matchedStack.shrink(consumeAmount);
				foundCount -= consumeAmount;
			}

			return matchedItem;
		}
	}

	public static boolean findInventoryItem(EntityPlayer player, ItemStack stack, boolean consumeItem, int amount) {
		if (stack.isEmpty())
			return false;

		if (amount <= 0 || player.isCreative())
			return true;

		if (amount == 1) {
			ItemStack checkStack;

			if (areStacksFunctionallyEqual((checkStack = player.getHeldItem(EnumHand.MAIN_HAND)), stack) && !checkStack.isEmpty()) {
				if (consumeItem && !player.capabilities.isCreativeMode)
					checkStack.shrink(1);

				return true;
			}
			else if (areStacksFunctionallyEqual((checkStack = player.getHeldItem(EnumHand.OFF_HAND)), stack) && !checkStack.isEmpty()) {
				if (consumeItem && !player.capabilities.isCreativeMode)
					checkStack.shrink(1);

				return true;
			}
			else {
				for (ItemStack checkStack2 : player.inventory.mainInventory) {
					if (!checkStack2.isEmpty() && areStacksFunctionallyEqual(stack, checkStack2)) {
						if (consumeItem && !player.capabilities.isCreativeMode)
							checkStack2.shrink(1);

						return true;
					}
				}

				for (ItemStack checkStack2 : player.inventory.armorInventory) {
					if (!checkStack2.isEmpty() && areStacksFunctionallyEqual(stack, checkStack2)) {
						if (consumeItem && !player.capabilities.isCreativeMode)
							checkStack2.shrink(1);

						return true;
					}
				}
			}

			return false;
		}
		else {
			ArrayList<ItemStack> matchedStacks = new ArrayList<ItemStack>();
			int foundCount = 0;
			ItemStack checkStack;

			if (areStacksFunctionallyEqual((checkStack = player.getHeldItem(EnumHand.MAIN_HAND)), stack) && !checkStack.isEmpty()) {
				matchedStacks.add(checkStack);
				foundCount += checkStack.getCount();
			}

			if (foundCount < amount && areStacksFunctionallyEqual((checkStack = player.getHeldItem(EnumHand.OFF_HAND)), stack) && !checkStack.isEmpty()) {
				matchedStacks.add(checkStack);
				foundCount += checkStack.getCount();
			}

			if (foundCount < amount) {
				for (ItemStack checkStack2 : player.inventory.mainInventory) {
					if (!checkStack2.isEmpty() && areStacksFunctionallyEqual(stack, checkStack2)) {
						matchedStacks.add(checkStack2);
						foundCount += checkStack2.getCount();

						if (foundCount >= amount)
							break;
					}
				}
			}

			if (foundCount < amount) {
				for (ItemStack checkStack2 : player.inventory.armorInventory) {
					if (!checkStack2.isEmpty() && areStacksFunctionallyEqual(stack, checkStack2)) {
						matchedStacks.add(checkStack2);
						foundCount += checkStack2.getCount();

						if (foundCount >= amount)
							break;
					}
				}
			}

			if (foundCount < amount)
				return false;

			if (!consumeItem || player.capabilities.isCreativeMode)
				return true;

			for (ItemStack matchedStack : matchedStacks) {
				int consumeAmount = Math.min(matchedStack.getCount(), Math.min(amount, foundCount));

				matchedStack.shrink(consumeAmount);
				foundCount -= consumeAmount;
			}

			return true;
		}
	}

	public static boolean areStacksFunctionallyEqual(ItemStack a, ItemStack b) {
		if (a.getItem() != b.getItem())
			return false;

		if (a.isItemStackDamageable() ^ b.isItemStackDamageable())
			return false;

		if (!a.isItemStackDamageable() && a.getItemDamage() != b.getItemDamage())
			return false;

		return !a.hasTagCompound() ? !b.hasTagCompound() : b.hasTagCompound() && a.getTagCompound().equals(b.getTagCompound());
	}

	public static String getFormattedDescriptionText(String langKey, Enums.ItemDescriptionType type, String... args) {
		return StringUtil.getColourLocaleStringWithArguments(langKey, type.format, args);
	}

	public static String getFormattedDescriptionText(String langKey, Enums.ItemDescriptionType type) {
		return StringUtil.getColourLocaleString(langKey, type.format);
	}

	@SideOnly(Side.CLIENT)
	public static String getFormattedLevelRestrictedDescriptionText(Enums.Skills skill, int levelReq) {
		boolean meetsReq = (Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().player.capabilities.isCreativeMode) || AdventGuiTabPlayer.getSkillLevel(skill) >= levelReq;

		return StringUtil.getColourLocaleStringWithArguments("items.description.skillRequirement", meetsReq ? TextFormatting.GREEN : TextFormatting.RED, Integer.toString(levelReq), StringUtil.getLocaleString("skills." + skill.toString().toLowerCase() + ".name"));
	}

	public static boolean hasLevelForItem(Item item, PlayerDataManager plData) {
		if (item == null)
			return false;

		if (plData.player().capabilities.isCreativeMode || !(item instanceof SkillItem))
			return true;

		SkillItem skillItem = (SkillItem)item;

		return skillItem.getLevelReq() <= plData.stats().getLevel(skillItem.getSkill());
	}

	@Nullable
	public static ItemStack getStackFromHotbar(EntityPlayer player, Item item) {
		for (int i = 0; i < 9; i++) {
			ItemStack stack;

			if ((stack = player.inventory.getStackInSlot(i)).getItem() == item)
				return stack;
		}

		return null;
	}

	public static int findItemInInventory(EntityPlayer player, Item item) {
		int i = 0;

		for (ItemStack stack : player.inventory.mainInventory) {
			if (stack.getItem() == item)
				return i;

			i++;
		}

		for (ItemStack stack : player.inventory.armorInventory) {
			if (stack.getItem() == item)
				return i;

			i++;
		}

		for (ItemStack stack : player.inventory.offHandInventory) {
			if (stack.getItem() == item)
				return i;

			i++;
		}

		return -1;
	}

	@Nullable
	public static ItemStack getStackFromInventory(EntityPlayer player, Item item) {
		int i = findItemInInventory(player, item);

		if (i < 0)
			return null;

		return player.inventory.getStackInSlot(i);
	}

	public static boolean isHoldingItem(EntityLivingBase entity, Item item) {
		return entity.getHeldItemMainhand().getItem() == item || entity.getHeldItemOffhand().getItem() == item;
	}

	public static boolean isPlayerEnvironmentallyProtected(EntityPlayer player) {
		if (PlayerUtil.isWearingFullSet(player, Enums.ArmourSets.HAZMAT) || player.inventory.armorInventory.get(EntityEquipmentSlot.HEAD.getIndex()).getItem() == ArmourRegister.FACE_MASK)
			return true;

		if (ThirdPartyInteractions.isIc2Active())
			return IC2Compat.getCompatTool().isPlayerEnvironmentallyProtected(player);

		return false;
	}
}
