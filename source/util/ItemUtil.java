package net.tslat.aoa3.util;

import com.google.common.collect.Multimap;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.Tag;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.common.registration.AoAEnchantments;
import net.tslat.aoa3.item.SkillItem;
import net.tslat.aoa3.item.armour.AdventArmour;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Supplier;

public abstract class ItemUtil {
	public static IItemTier customItemTier(int durability, float efficiency, float attackDamage, int harvestLevel, int enchantability, @Nullable Supplier<Item> repairMaterial) {
		final Supplier<Item> repairItem = repairMaterial;

		return new IItemTier() {
			@Override
			public int getMaxUses() {
				return durability;
			}

			@Override
			public float getEfficiency() {
				return efficiency;
			}

			@Override
			public float getAttackDamage() {
				return attackDamage;
			}

			@Override
			public int getHarvestLevel() {
				return harvestLevel;
			}

			@Override
			public int getEnchantability() {
				return enchantability;
			}

			@Override
			public Ingredient getRepairMaterial() {
				return repairItem == null ? Ingredient.EMPTY : Ingredient.fromItems(repairItem.get());
			}
		};
	}

	public static IArmorMaterial customArmourMaterial(String name, int durabilityBase, int[] protectionValues, int enchantability, SoundEvent equipSound, float toughness) {
		return new IArmorMaterial() {
			@Override
			public int getDurability(EquipmentSlotType slot) {
				switch (slot) {
					case HEAD:
						return 11 * durabilityBase;
					case CHEST:
						return 16 * durabilityBase;
					case LEGS:
						return 15 * durabilityBase;
					case FEET:
						return 13 * durabilityBase;
					default:
						return 0;
				}
			}

			@Override
			public int getDamageReductionAmount(EquipmentSlotType slot) {
				return protectionValues[slot.getIndex()];
			}

			@Override
			public int getEnchantability() {
				return enchantability;
			}

			@Override
			public SoundEvent getSoundEvent() {
				return equipSound;
			}

			@Override
			public Ingredient getRepairMaterial() {
				return Ingredient.EMPTY;
			}

			@Override
			public String getName() {
				return name;
			}

			@Override
			public float getToughness() {
				return toughness;
			}
		};
	}

	public static boolean hasEnchantment(Enchantment enchant, ItemStack stack) {
		return EnchantmentHelper.getEnchantmentLevel(enchant, stack) > 0;
	}

	public static void damageItem(ItemStack stack, LivingEntity entity, Hand hand) {
		damageItem(stack, entity, 1, PlayerUtil.handToEquipmentSlotType(hand));
	}

	public static void damageItem(ItemStack stack, LivingEntity entity, int amount, EquipmentSlotType slot) {
		stack.damageItem(amount, entity, user -> user.sendBreakAnimation(slot));
	}

	public static void givePlayerMultipleItems(PlayerEntity pl, ItemStack... stacks) {
		givePlayerMultipleItems(pl, Arrays.asList(stacks));
	}

	public static void givePlayerMultipleItems(PlayerEntity pl, Collection<ItemStack> stacks) {
		for (ItemStack stack : stacks) {
			if (!pl.inventory.addItemStackToInventory(stack))
				pl.entityDropItem(stack, 0.5f);
		}

		pl.container.detectAndSendChanges();
	}

	public static void givePlayerItemOrDrop(PlayerEntity player, ItemStack stack) {
		if (stack.isEmpty())
			return;

		if (!player.inventory.addItemStackToInventory(stack))
			player.entityDropItem(stack, 0.5f);

		player.container.detectAndSendChanges();
	}

	public static boolean isHoldingItem(LivingEntity entity, Item item) {
		return entity.getHeldItemMainhand().getItem() == item || entity.getHeldItemOffhand().getItem() == item;
	}

	public static boolean hasLevelForItem(Item item, PlayerDataManager plData) {
		if (item == null)
			return false;

		if (!PlayerUtil.shouldPlayerBeAffected(plData.player()) || !(item instanceof SkillItem))
			return true;

		SkillItem skillItem = (SkillItem)item;

		return skillItem.getLevelReq() <= plData.stats().getLevel(skillItem.getSkill());
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

	public static void setAttribute(Multimap<String, AttributeModifier> map, IAttribute att, UUID id, double value) {
		final Collection<AttributeModifier> modifiers = map.get(att.getName());
		final Optional<AttributeModifier> mod = modifiers.stream().filter(attributeModifier -> attributeModifier.getID().equals(id)).findFirst();

		if (mod.isPresent()) {
			final AttributeModifier existingMod = mod.get();
			modifiers.remove(existingMod);
			modifiers.add(new AttributeModifier(existingMod.getID(), existingMod.getName(), value, existingMod.getOperation()));
		}
	}

	public static void clearInventoryOfItems(PlayerEntity player, ItemStack... stacks) {
		if (player.isCreative())
			return;

		ItemStack checkStack;

		if (!(checkStack = player.getHeldItem(Hand.MAIN_HAND)).isEmpty()) {
			for (ItemStack stack : stacks) {
				if (areStacksFunctionallyEqual(checkStack, stack)) {
					checkStack.setCount(0);

					break;
				}
			}
		}

		if (!(checkStack = player.getHeldItem(Hand.OFF_HAND)).isEmpty()) {
			for (ItemStack stack : stacks) {
				if (areStacksFunctionallyEqual(checkStack, stack)) {
					checkStack.setCount(0);

					break;
				}
			}
		}

		for (ItemStack checkStack2 : player.inventory.mainInventory) {
			if (!checkStack2.isEmpty()) {
				for (ItemStack stack : stacks) {
					if (areStacksFunctionallyEqual(checkStack2, stack)) {
						checkStack2.setCount(0);

						break;
					}
				}
			}
		}

		for (ItemStack checkStack2 : player.inventory.armorInventory) {
			if (!checkStack2.isEmpty()) {
				for (ItemStack stack : stacks) {
					if (areStacksFunctionallyEqual(checkStack2, stack)) {
						checkStack2.setCount(0);

						break;
					}
				}
			}
		}
	}

	public static boolean findItemByTag(PlayerEntity player, Tag<Item> tag, boolean consumeItem, int amount) {
		if (amount <= 0 || player.isCreative())
			return true;

		if (amount == 1) {
			ItemStack checkStack;

			if ((checkStack = player.getHeldItem(Hand.MAIN_HAND)).getItem().isIn(tag) && !checkStack.isEmpty()) {
				if (consumeItem)
					checkStack.shrink(1);

				return true;
			}
			else if ((checkStack = player.getHeldItem(Hand.OFF_HAND)).getItem().isIn(tag) && !checkStack.isEmpty()) {
				if (consumeItem)
					checkStack.shrink(1);

				return true;
			}
			else {
				for (ItemStack checkStack2 : player.inventory.mainInventory) {
					if (!checkStack2.isEmpty() && checkStack2.getItem().isIn(tag)) {
						if (consumeItem)
							checkStack2.shrink(1);

						return true;
					}
				}

				for (ItemStack checkStack2 : player.inventory.armorInventory) {
					if (!checkStack2.isEmpty() && checkStack2.getItem().isIn(tag)) {
						if (consumeItem)
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

			if ((checkStack = player.getHeldItem(Hand.MAIN_HAND)).getItem().isIn(tag) && !checkStack.isEmpty()) {
				matchedStacks.add(checkStack);
				foundCount += checkStack.getCount();
			}

			if (foundCount < amount && (checkStack = player.getHeldItem(Hand.OFF_HAND)).getItem().isIn(tag) && !checkStack.isEmpty()) {
				matchedStacks.add(checkStack);
				foundCount += checkStack.getCount();
			}

			if (foundCount < amount) {
				for (ItemStack checkStack2 : player.inventory.mainInventory) {
					if (!checkStack2.isEmpty() && checkStack2.getItem().isIn(tag)) {
						matchedStacks.add(checkStack2);
						foundCount += checkStack2.getCount();

						if (foundCount >= amount)
							break;
					}
				}
			}

			if (foundCount < amount) {
				for (ItemStack checkStack2 : player.inventory.armorInventory) {
					if (!checkStack2.isEmpty() && checkStack2.getItem().isIn(tag)) {
						matchedStacks.add(checkStack2);
						foundCount += checkStack2.getCount();

						if (foundCount >= amount)
							break;
					}
				}
			}

			if (foundCount < amount)
				return false;

			if (!consumeItem)
				return true;

			for (ItemStack matchedStack : matchedStacks) {
				int consumeAmount = Math.min(matchedStack.getCount(), Math.min(amount, foundCount));

				matchedStack.shrink(consumeAmount);
				foundCount -= consumeAmount;
			}

			return true;
		}
	}

	public static boolean findInventoryItem(PlayerEntity player, ItemStack stack, boolean consumeItem, int amount) {
		if (stack.isEmpty())
			return false;

		if (amount <= 0 || player.isCreative())
			return true;

		if (amount == 1) {
			ItemStack checkStack;

			if (areStacksFunctionallyEqual((checkStack = player.getHeldItem(Hand.MAIN_HAND)), stack) && !checkStack.isEmpty()) {
				if (consumeItem)
					checkStack.shrink(1);

				return true;
			}
			else if (areStacksFunctionallyEqual((checkStack = player.getHeldItem(Hand.OFF_HAND)), stack) && !checkStack.isEmpty()) {
				if (consumeItem)
					checkStack.shrink(1);

				return true;
			}
			else {
				for (ItemStack checkStack2 : player.inventory.mainInventory) {
					if (!checkStack2.isEmpty() && areStacksFunctionallyEqual(stack, checkStack2)) {
						if (consumeItem)
							checkStack2.shrink(1);

						return true;
					}
				}

				for (ItemStack checkStack2 : player.inventory.armorInventory) {
					if (!checkStack2.isEmpty() && areStacksFunctionallyEqual(stack, checkStack2)) {
						if (consumeItem)
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

			if (areStacksFunctionallyEqual((checkStack = player.getHeldItem(Hand.MAIN_HAND)), stack) && !checkStack.isEmpty()) {
				matchedStacks.add(checkStack);
				foundCount += checkStack.getCount();
			}

			if (foundCount < amount && areStacksFunctionallyEqual((checkStack = player.getHeldItem(Hand.OFF_HAND)), stack) && !checkStack.isEmpty()) {
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

			if (!consumeItem)
				return true;

			for (ItemStack matchedStack : matchedStacks) {
				int consumeAmount = Math.min(matchedStack.getCount(), Math.min(amount, foundCount));

				matchedStack.shrink(consumeAmount);
				foundCount -= consumeAmount;
			}

			return true;
		}
	}

	public static boolean findAndConsumeRunes(HashMap<RuneItem, Integer> runeMap, ServerPlayerEntity player, boolean allowBuffs, @Nonnull ItemStack heldItem) {
		if (player.isCreative())
			return true;

		AdventArmour.Type armour = PlayerUtil.getAdventPlayer(player).equipment().getCurrentFullArmourSet();
		int archmage = allowBuffs ? EnchantmentHelper.getEnchantmentLevel(AoAEnchantments.ARCHMAGE.get(), heldItem) : 0;
		boolean nightmareArmour = allowBuffs && armour == AdventArmour.Type.NIGHTMARE;
		boolean greed = EnchantmentHelper.getEnchantmentLevel(AoAEnchantments.GREED.get(), heldItem) > 0;
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

		ItemStack mainHandStack = player.getHeldItem(Hand.OFF_HAND);
		ItemStack offHandStack = player.getHeldItem(Hand.MAIN_HAND);

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
				ItemStack rune = player.getHeldItem(Hand.MAIN_HAND);
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
				ItemStack rune = player.getHeldItem(Hand.OFF_HAND);
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

	@Nullable
	public static ItemStack getStackFromHotbar(PlayerEntity player, Item item) {
		for (int i = 0; i < 9; i++) {
			ItemStack stack;

			if ((stack = player.inventory.getStackInSlot(i)).getItem() == item)
				return stack;
		}

		return null;
	}

	@Nullable
	public static ItemStack getStackFromInventory(PlayerEntity player, Item item) {
		ItemStack stack = new ItemStack(item);
		ItemStack checkStack;

		if (areStacksFunctionallyEqual((checkStack = player.getHeldItem(Hand.MAIN_HAND)), stack) && !checkStack.isEmpty()) {
			return checkStack;
		}
		else if (areStacksFunctionallyEqual((checkStack = player.getHeldItem(Hand.OFF_HAND)), stack) && !checkStack.isEmpty()) {
			return checkStack;
		}
		else {
			for (ItemStack checkStack2 : player.inventory.mainInventory) {
				if (!checkStack2.isEmpty() && areStacksFunctionallyEqual(stack, checkStack2))
					return checkStack2;
			}

			for (ItemStack checkStack2 : player.inventory.armorInventory) {
				if (!checkStack2.isEmpty() && areStacksFunctionallyEqual(stack, checkStack2))
					return checkStack2;
			}
		}

		return null;
	}

	public static boolean areStacksFunctionallyEqual(ItemStack a, ItemStack b) {
		if (a.getItem() != b.getItem())
			return false;

		if (a.isDamageable() ^ b.isDamageable())
			return false;

		if (!a.isDamageable() && a.getDamage() != b.getDamage())
			return false;

		return !a.hasTag() ? !b.hasTag() : b.hasTag() && a.getTag().equals(b.getTag());
	}
}
