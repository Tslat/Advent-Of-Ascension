package net.tslat.aoa3.util;

import com.google.common.collect.Multimap;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Block;
import net.tslat.aoa3.common.registration.AoAEnchantments;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.content.item.armour.AdventArmour;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Supplier;

public final class ItemUtil {
	public static Tier customItemTier(int durability, float efficiency, float attackDamage, int harvestLevel, int enchantability, final @Nullable Supplier<Item> repairMaterial, @Nullable TagKey<Block> toolTypeTag) {
		return new Tier() {
			@Override
			public int getUses() {
				return durability;
			}

			@Override
			public float getSpeed() {
				return efficiency;
			}

			@Override
			public float getAttackDamageBonus() {
				return attackDamage;
			}

			@Override
			public int getLevel() {
				return harvestLevel;
			}

			@Override
			public int getEnchantmentValue() {
				return enchantability;
			}

			@Override
			public Ingredient getRepairIngredient() {
				return repairMaterial == null ? Ingredient.EMPTY : Ingredient.of(repairMaterial.get());
			}

			@Nullable
			@Override
			public TagKey<Block> getTag() {
				return toolTypeTag;
			}
		};
	}

	public static ArmorMaterial customArmourMaterial(String name, int durabilityBase, int[] protectionValues, int enchantability, SoundEvent equipSound, float toughness) {
		return new ArmorMaterial() {
			@Override
			public int getDurabilityForSlot(EquipmentSlot slot) {
				return switch (slot) {
					case HEAD -> 11 * durabilityBase;
					case CHEST -> 16 * durabilityBase;
					case LEGS -> 15 * durabilityBase;
					case FEET -> 13 * durabilityBase;
					default -> 0;
				};
			}

			@Override
			public int getDefenseForSlot(EquipmentSlot slot) {
				return protectionValues[slot.getIndex()];
			}

			@Override
			public int getEnchantmentValue() {
				return enchantability;
			}

			@Override
			public SoundEvent getEquipSound() {
				return equipSound;
			}

			@Override
			public Ingredient getRepairIngredient() {
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

			@Override
			public float getKnockbackResistance() {
				return 0;
			}
		};
	}

	public static boolean hasEnchantment(Enchantment enchant, ItemStack stack) {
		return EnchantmentHelper.getItemEnchantmentLevel(enchant, stack) > 0;
	}

	public static void damageItem(ItemStack stack, LivingEntity entity, InteractionHand hand) {
		damageItem(stack, entity, hand, 1);
	}

	public static void damageItem(ItemStack stack, LivingEntity entity, InteractionHand hand, int amount) {
		damageItem(stack, entity, amount, PlayerUtil.handToEquipmentSlotType(hand));
	}

	public static void damageItem(ItemStack stack, LivingEntity entity, int amount, EquipmentSlot slot) {
		stack.hurtAndBreak(amount, entity, user -> user.broadcastBreakEvent(slot));
	}

	public static void givePlayerMultipleItems(Player pl, ItemStack... stacks) {
		givePlayerMultipleItems(pl, Arrays.asList(stacks));
	}

	public static void givePlayerMultipleItems(Player pl, Collection<ItemStack> stacks) {
		for (ItemStack stack : stacks) {
			if (!pl.getInventory().add(stack))
				pl.spawnAtLocation(stack, 0.5f);
		}

		pl.inventoryMenu.broadcastChanges();
	}

	public static void givePlayerItemOrDrop(Player player, ItemStack stack) {
		if (stack.isEmpty())
			return;

		if (!player.getInventory().add(stack))
			player.spawnAtLocation(stack, 0.5f);

		player.inventoryMenu.broadcastChanges();
	}

	public static boolean isHoldingItem(LivingEntity entity, Item item) {
		return entity.getMainHandItem().getItem() == item || entity.getOffhandItem().getItem() == item;
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

	public static void setAttribute(Multimap<Attribute, AttributeModifier> map, Attribute att, UUID id, double value) {
		final Collection<AttributeModifier> modifiers = map.get(att);
		final Optional<AttributeModifier> mod = modifiers.stream().filter(attributeModifier -> attributeModifier.getId().equals(id)).findFirst();

		if (mod.isPresent()) {
			final AttributeModifier existingMod = mod.get();
			modifiers.remove(existingMod);
			modifiers.add(new AttributeModifier(existingMod.getId(), existingMod.getName(), value, existingMod.getOperation()));
		}
	}

	public static void clearInventoryOfItems(Player player, ItemStack... stacks) {
		if (player.isCreative())
			return;

		ItemStack checkStack;

		if (!(checkStack = player.getItemInHand(InteractionHand.MAIN_HAND)).isEmpty()) {
			for (ItemStack stack : stacks) {
				if (areStacksFunctionallyEqual(checkStack, stack)) {
					checkStack.setCount(0);

					break;
				}
			}
		}

		if (!(checkStack = player.getItemInHand(InteractionHand.OFF_HAND)).isEmpty()) {
			for (ItemStack stack : stacks) {
				if (areStacksFunctionallyEqual(checkStack, stack)) {
					checkStack.setCount(0);

					break;
				}
			}
		}

		for (ItemStack checkStack2 : player.getInventory().items) {
			if (!checkStack2.isEmpty()) {
				for (ItemStack stack : stacks) {
					if (areStacksFunctionallyEqual(checkStack2, stack)) {
						checkStack2.setCount(0);

						break;
					}
				}
			}
		}

		for (ItemStack checkStack2 : player.getInventory().armor) {
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

	public static boolean findItemByTag(Player player, TagKey<Item> tag, boolean consumeItem, int amount) {
		if (amount <= 0 || player.isCreative())
			return true;

		if (amount == 1) {
			ItemStack checkStack;

			if ((checkStack = player.getItemInHand(InteractionHand.MAIN_HAND)).is(tag) && !checkStack.isEmpty()) {
				if (consumeItem)
					checkStack.shrink(1);

				return true;
			}
			else if ((checkStack = player.getItemInHand(InteractionHand.OFF_HAND)).is(tag) && !checkStack.isEmpty()) {
				if (consumeItem)
					checkStack.shrink(1);

				return true;
			}
			else {
				for (ItemStack checkStack2 : player.getInventory().items) {
					if (!checkStack2.isEmpty() && checkStack2.is(tag)) {
						if (consumeItem)
							checkStack2.shrink(1);

						return true;
					}
				}

				for (ItemStack checkStack2 : player.getInventory().armor) {
					if (!checkStack2.isEmpty() && checkStack2.is(tag)) {
						if (consumeItem)
							checkStack2.shrink(1);

						return true;
					}
				}
			}

			return false;
		}
		else {
			ArrayList<ItemStack> matchedStacks = new ArrayList<>();
			int foundCount = 0;
			ItemStack checkStack;

			if ((checkStack = player.getItemInHand(InteractionHand.MAIN_HAND)).is(tag) && !checkStack.isEmpty()) {
				matchedStacks.add(checkStack);
				foundCount += checkStack.getCount();
			}

			if (foundCount < amount && (checkStack = player.getItemInHand(InteractionHand.OFF_HAND)).is(tag) && !checkStack.isEmpty()) {
				matchedStacks.add(checkStack);
				foundCount += checkStack.getCount();
			}

			if (foundCount < amount) {
				for (ItemStack checkStack2 : player.getInventory().items) {
					if (!checkStack2.isEmpty() && checkStack2.is(tag)) {
						matchedStacks.add(checkStack2);
						foundCount += checkStack2.getCount();

						if (foundCount >= amount)
							break;
					}
				}
			}

			if (foundCount < amount) {
				for (ItemStack checkStack2 : player.getInventory().armor) {
					if (!checkStack2.isEmpty() && checkStack2.is(tag)) {
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

	public static boolean findInventoryItem(Player player, ItemStack stack, boolean consumeItem, int amount) {
		if (stack.isEmpty())
			return false;

		if (amount <= 0 || player.isCreative())
			return true;

		if (amount == 1) {
			ItemStack checkStack;

			if (areStacksFunctionallyEqual((checkStack = player.getItemInHand(InteractionHand.MAIN_HAND)), stack) && !checkStack.isEmpty()) {
				if (consumeItem)
					checkStack.shrink(1);

				return true;
			}
			else if (areStacksFunctionallyEqual((checkStack = player.getItemInHand(InteractionHand.OFF_HAND)), stack) && !checkStack.isEmpty()) {
				if (consumeItem)
					checkStack.shrink(1);

				return true;
			}
			else {
				for (ItemStack checkStack2 : player.getInventory().items) {
					if (!checkStack2.isEmpty() && areStacksFunctionallyEqual(stack, checkStack2)) {
						if (consumeItem)
							checkStack2.shrink(1);

						return true;
					}
				}

				for (ItemStack checkStack2 : player.getInventory().armor) {
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
			ArrayList<ItemStack> matchedStacks = new ArrayList<>();
			int foundCount = 0;
			ItemStack checkStack;

			if (areStacksFunctionallyEqual((checkStack = player.getItemInHand(InteractionHand.MAIN_HAND)), stack) && !checkStack.isEmpty()) {
				matchedStacks.add(checkStack);
				foundCount += checkStack.getCount();
			}

			if (foundCount < amount && areStacksFunctionallyEqual((checkStack = player.getItemInHand(InteractionHand.OFF_HAND)), stack) && !checkStack.isEmpty()) {
				matchedStacks.add(checkStack);
				foundCount += checkStack.getCount();
			}

			if (foundCount < amount) {
				for (ItemStack checkStack2 : player.getInventory().items) {
					if (!checkStack2.isEmpty() && areStacksFunctionallyEqual(stack, checkStack2)) {
						matchedStacks.add(checkStack2);
						foundCount += checkStack2.getCount();

						if (foundCount >= amount)
							break;
					}
				}
			}

			if (foundCount < amount) {
				for (ItemStack checkStack2 : player.getInventory().armor) {
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

	public static boolean findAndConsumeRunes(HashMap<Item, Integer> runeMap, ServerPlayer player, boolean allowBuffs, @Nonnull ItemStack heldItem) {
		if (player.isCreative())
			return true;

		AdventArmour.Type armour = PlayerUtil.getAdventPlayer(player).equipment().getCurrentFullArmourSet();
		int archmage = allowBuffs ? EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.ARCHMAGE.get(), heldItem) : 0;
		boolean nightmareArmour = allowBuffs && armour == AdventArmour.Type.NIGHTMARE;
		boolean greed = EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.GREED.get(), heldItem) > 0;
		HashMap<Item, Integer> requiredRunes = new HashMap<>();

		for (Map.Entry<Item, Integer> runeEntry : runeMap.entrySet()) {
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

		HashSet<Integer> runeSlots = new HashSet<>();
		HashMap<Item, Integer> runeCounter = new HashMap<>(requiredRunes);

		ItemStack mainHandStack = player.getItemInHand(InteractionHand.OFF_HAND);
		ItemStack offHandStack = player.getItemInHand(InteractionHand.MAIN_HAND);

		if (mainHandStack.is(AoATags.Items.ADVENT_RUNE)) {
			Item type = mainHandStack.getItem();

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

		if (!runeCounter.isEmpty() && offHandStack.is(AoATags.Items.ADVENT_RUNE)) {
			Item type = offHandStack.getItem();

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
			for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
				ItemStack stack = player.getInventory().getItem(i);

				if (stack.is(AoATags.Items.ADVENT_RUNE)) {
					Item type = stack.getItem();

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
				ItemStack rune = player.getItemInHand(InteractionHand.MAIN_HAND);
				Item type = rune.getItem();
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
				ItemStack rune = player.getItemInHand(InteractionHand.OFF_HAND);
				Item type = rune.getItem();
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
				ItemStack rune = player.getInventory().getItem(slotId);
				Item type = rune.getItem();
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

	public static boolean hasItemInHotbar(Player player, Item item) {
		return getStackFromInventory(player, item) != null;
	}

	@Nullable
	public static ItemStack getStackFromHotbar(Player player, Item item) {
		for (int i = 0; i < 9; i++) {
			ItemStack stack;

			if ((stack = player.getInventory().getItem(i)).getItem() == item)
				return stack;
		}

		return null;
	}

	public static boolean hasItemInOffhand(Player player, Item item) {
		return player.getItemInHand(InteractionHand.OFF_HAND).getItem() == item;
	}

	@Nullable
	public static ItemStack getStackFromInventory(Player player, Item item) {
		ItemStack stack = new ItemStack(item);
		ItemStack checkStack;

		if (areStacksFunctionallyEqual((checkStack = player.getItemInHand(InteractionHand.MAIN_HAND)), stack) && !checkStack.isEmpty()) {
			return checkStack;
		}
		else if (areStacksFunctionallyEqual((checkStack = player.getItemInHand(InteractionHand.OFF_HAND)), stack) && !checkStack.isEmpty()) {
			return checkStack;
		}
		else {
			for (ItemStack checkStack2 : player.getInventory().items) {
				if (!checkStack2.isEmpty() && areStacksFunctionallyEqual(stack, checkStack2))
					return checkStack2;
			}

			for (ItemStack checkStack2 : player.getInventory().armor) {
				if (!checkStack2.isEmpty() && areStacksFunctionallyEqual(stack, checkStack2))
					return checkStack2;
			}
		}

		return null;
	}

	public static boolean areStacksFunctionallyEqual(ItemStack a, ItemStack b) {
		if (a.getItem() != b.getItem())
			return false;

		if (a.isDamageableItem() ^ b.isDamageableItem())
			return false;

		return a.isDamageableItem() || a.getDamageValue() == b.getDamageValue();
	}

	public static List<ItemStack> increaseStackSize(ItemStack stack, int addAmount) {
		int maxCount = stack.getMaxStackSize();

		if (stack.getCount() + addAmount <= maxCount) {
			stack.setCount(stack.getCount() + addAmount);

			return Collections.emptyList();
		}

		ArrayList<ItemStack> newStacks = new ArrayList<>((int)((addAmount + stack.getCount()) / (float)maxCount));

		while (addAmount > 0) {
			ItemStack copy = stack.copy();

			copy.setCount(Math.min(maxCount, addAmount));
			newStacks.add(copy);

			addAmount -= copy.getCount();
		}

		return newStacks;
	}

	public static boolean isHoe(Item item) {
		return item instanceof DiggerItem digger && (digger.blocks == BlockTags.MINEABLE_WITH_HOE || digger instanceof HoeItem);
	}

	public static boolean isPickaxe(Item item) {
		return item instanceof DiggerItem digger && (digger.blocks == BlockTags.MINEABLE_WITH_PICKAXE || digger instanceof PickaxeItem);
	}

	public static boolean isAxe(Item item) {
		return item instanceof DiggerItem digger && (digger.blocks == BlockTags.MINEABLE_WITH_AXE || digger instanceof AxeItem);
	}

	public static boolean isShovel(Item item) {
		return item instanceof DiggerItem digger && (digger.blocks == BlockTags.MINEABLE_WITH_SHOVEL || digger instanceof ShovelItem);
	}
}
