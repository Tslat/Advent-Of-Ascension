package net.tslat.aoa3.util;

import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.event.EventHooks;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.common.registration.item.AoAArmourMaterials;
import net.tslat.aoa3.common.registration.item.AoAEnchantments;
import net.tslat.aoa3.common.registration.item.AoAItems;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public final class ItemUtil {
	public static void consume(LivingEntity entity, ItemStack stack) {
		consume(entity, stack, 1);
	}

	public static void consume(LivingEntity entity, ItemStack stack, int amount) {
		if (!entity.level().isClientSide && !entity.hasInfiniteMaterials())
			consume(stack, amount);
	}

	public static void consume(ItemStack stack) {
		consume(stack, 1);
	}

	public static void consume(ItemStack stack, int amount) {
		stack.shrink(amount);
	}

	public static void damageItemForUser(Player player, InteractionHand hand) {
		damageItemForUser(player, 1, hand);
	}

	public static void damageItemForUser(Player player, EquipmentSlot slot) {
		damageItemForUser(player, 1, slot);
	}

	public static void damageItemForUser(Player player, ItemStack stack, InteractionHand hand) {
		damageItemForUser(player, stack, 1, hand);
	}

	public static void damageItemForUser(Player player, ItemStack stack, EquipmentSlot slot) {
		damageItemForUser(player, stack, 1, slot);
	}

	public static void damageItemForUser(Player player, int amount, InteractionHand hand) {
		if (player instanceof ServerPlayer pl && !pl.hasInfiniteMaterials())
			damageItemForUser(pl.serverLevel(), pl, amount, hand);
	}

	public static void damageItemForUser(Player player, int amount, EquipmentSlot slot) {
		if (player instanceof ServerPlayer pl && !pl.hasInfiniteMaterials())
			damageItemForUser(pl.serverLevel(), pl, amount, slot);
	}

	public static void damageItemForUser(Player player, ItemStack stack, int amount, InteractionHand hand) {
		if (player instanceof ServerPlayer pl && !pl.hasInfiniteMaterials())
			damageItemForUser(pl.serverLevel(), stack, amount, pl, LivingEntity.getSlotForHand(hand));
	}

	public static void damageItemForUser(Player player, ItemStack stack, int amount, EquipmentSlot slot) {
		if (player instanceof ServerPlayer pl && !pl.hasInfiniteMaterials())
			damageItemForUser(pl.serverLevel(), stack, amount, pl, slot);
	}

	public static void damageItemForUser(ServerLevel level, LivingEntity user, InteractionHand hand) {
		damageItemForUser(level, user, 1, hand);
	}

	public static void damageItemForUser(ServerLevel level, LivingEntity user, int amount, InteractionHand hand) {
		final EquipmentSlot slot = LivingEntity.getSlotForHand(hand);

		damageItemForUser(level, user.getItemBySlot(slot), amount, user, slot);
	}

	public static void damageItemForUser(ServerLevel level, LivingEntity user, EquipmentSlot slot) {
		damageItemForUser(level, user.getItemBySlot(slot), user, slot);
	}

	public static void damageItemForUser(ServerLevel level, LivingEntity user, int amount, EquipmentSlot slot) {
		damageItemForUser(level, user.getItemBySlot(slot), amount, user, slot);
	}

	public static void damageItemForUser(ServerLevel level, ItemStack stack, LivingEntity user, EquipmentSlot slot) {
		damageItemForUser(level, stack, 1, user, slot);
	}

	public static void damageItemForUser(ServerLevel level, ItemStack stack, LivingEntity user, InteractionHand hand) {
		damageItemForUser(level, stack, 1, user, LivingEntity.getSlotForHand(hand));
	}

	public static void damageItemForUser(ServerLevel level, ItemStack stack, int amount, LivingEntity user, InteractionHand hand) {
		damageItemForUser(level, stack, amount, user, LivingEntity.getSlotForHand(hand));
	}

	public static void damageItemForUser(ServerLevel level, ItemStack stack, int amount, LivingEntity user, EquipmentSlot slot) {
		damageItem(level, stack, amount, user, item -> {
			user.onEquippedItemBroken(item, slot);

			if (user instanceof ServerPlayer player)
				EventHooks.onPlayerDestroyItem(player, stack, slot.isArmor() ? null : slot == EquipmentSlot.MAINHAND ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND);
		});
	}

	public static void damageItemForUser(ServerLevel level, ItemStack stack, int amount, LivingEntity user, Consumer<Item> onBreak) {
		damageItem(level, stack, amount, user, onBreak);
	}

	public static void damageItemNoUser(ServerLevel level, ItemStack stack) {
		damageItemNoUser(level, stack, 1);
	}

	public static void damageItemNoUser(ServerLevel level, ItemStack stack, int amount) {
		damageItemNoUser(level, stack, amount, item -> {});
	}

	public static void damageItemNoUser(ServerLevel level, ItemStack stack, int amount, Consumer<Item> onBreak) {
		damageItem(level, stack, amount, null, onBreak);
	}

	public static void damageItem(ServerLevel level, ItemStack stack, int amount, @Nullable LivingEntity entity, Consumer<Item> onBreak) {
		stack.hurtAndBreak(amount, level, entity, onBreak);
	}

	public static ItemStack damageAndConvertIfBroken(ServerLevel level, ItemStack stack, ItemLike convertTo) {
		return damageAndConvertIfBroken(level, stack, 1, convertTo, null, item -> {});
	}

	public static ItemStack damageAndConvertIfBroken(ServerLevel level, ItemStack stack, ItemLike convertTo, LivingEntity user, EquipmentSlot slot) {
		return damageAndConvertIfBroken(level, stack, 1, convertTo, user, slot);
	}

	public static ItemStack damageAndConvertIfBroken(ServerLevel level, ItemStack stack, int amount, ItemLike convertTo, LivingEntity user, EquipmentSlot slot) {
		return damageAndConvertIfBroken(level, stack, amount, convertTo, user, item -> {
			user.onEquippedItemBroken(item, slot);

			if (user instanceof ServerPlayer player)
				EventHooks.onPlayerDestroyItem(player, stack, slot.isArmor() ? null : slot == EquipmentSlot.MAINHAND ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND);
		});
	}

	public static ItemStack damageAndConvertIfBroken(ServerLevel level, ItemStack stack, int amount, ItemLike convertTo, @Nullable LivingEntity entity, Consumer<Item> onBreak) {
		damageItem(level, stack, amount, entity, onBreak);

		if (stack.isEmpty()) {
			ItemStack copy = new ItemStack(convertTo.asItem().builtInRegistryHolder(), 1, stack.getComponentsPatch());

			if (copy.isDamageableItem())
				copy.setDamageValue(0);

			return copy;
		}

		return stack;
	}

	public static boolean findAndConsumeRunes(Reference2IntMap<Item> runeMap, ServerPlayer player, boolean allowBuffs, @NotNull ItemStack heldItem) {
		if (player.hasInfiniteMaterials())
			return true;

		Reference2IntOpenHashMap<Item> requiredRunes = new Reference2IntOpenHashMap<>(runeMap);
		boolean nightmareArmour = allowBuffs && PlayerUtil.isWearingFullSet(player, AoAArmourMaterials.NIGHTMARE);

		for (Item item : requiredRunes.keySet()) {
			requiredRunes.computeIntIfPresent(item, (rune, cost) -> {
				if (allowBuffs) {
					cost = Mth.ceil(AoAEnchantments.modifyRuneCost(player.serverLevel(), heldItem, cost));

					if (nightmareArmour) {
						if (rune == AoAItems.DISTORTION_RUNE.get())
							return null;

						cost = Math.max(cost - 1, 1);
					}
				}

				return Mth.ceil(AoAEnchantments.modifyAmmoCost(player.serverLevel(), heldItem, cost));
			});
		}

		if (requiredRunes.isEmpty())
			return true;

		IntSet runeSlots = new IntOpenHashSet();
		Reference2IntOpenHashMap<Item> runeCounter = new Reference2IntOpenHashMap<>(requiredRunes);

		ItemStack mainHandStack = player.getItemInHand(InteractionHand.MAIN_HAND);
		ItemStack offHandStack = player.getItemInHand(InteractionHand.OFF_HAND);

		if (mainHandStack.is(AoATags.Items.ADVENT_RUNE)) {
			Item type = mainHandStack.getItem();

			if (runeCounter.containsKey(type)) {
				int amount = runeCounter.getInt(type);

				runeSlots.add(-1);
				amount -= mainHandStack.getCount();

				if (amount > 0) {
					runeCounter.put(type, amount);
				}
				else {
					runeCounter.removeInt(type);
				}
			}
		}

		if (!runeCounter.isEmpty() && offHandStack.is(AoATags.Items.ADVENT_RUNE)) {
			Item type = offHandStack.getItem();

			if (runeCounter.containsKey(type)) {
				int amount = runeCounter.getInt(type);

				runeSlots.add(-2);
				amount -= offHandStack.getCount();

				if (amount > 0) {
					runeCounter.put(type, amount);
				}
				else {
					runeCounter.removeInt(type);
				}
			}
		}

		if (!runeCounter.isEmpty()) {
			for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
				ItemStack stack = player.getInventory().getItem(i);

				if (stack.is(AoATags.Items.ADVENT_RUNE)) {
					Item type = stack.getItem();

					if (runeCounter.containsKey(type)) {
						int amount = runeCounter.getInt(type);

						runeSlots.add(i);
						amount -= stack.getCount();

						if (amount > 0) {
							runeCounter.put(type, amount);
						}
						else {
							runeCounter.removeInt(type);
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
				int amount = requiredRunes.getInt(type);
				int remaining = amount - rune.getCount();

				rune.shrink(amount);

				if (remaining <= 0) {
					requiredRunes.removeInt(type);
				}
				else {
					requiredRunes.put(type, remaining);
				}

				runeSlots.remove(-1);
			}

			if (runeSlots.contains(-2)) {
				ItemStack rune = player.getItemInHand(InteractionHand.OFF_HAND);
				Item type = rune.getItem();
				int amount = requiredRunes.getInt(type);
				int remaining = amount - rune.getCount();

				rune.shrink(amount);

				if (remaining <= 0) {
					requiredRunes.removeInt(type);
				}
				else {
					requiredRunes.put(type, remaining);
				}

				runeSlots.remove(-2);
			}

			for (int slotId : runeSlots) {
				ItemStack rune = player.getInventory().getItem(slotId);
				Item type = rune.getItem();
				int amount = requiredRunes.getInt(type);
				int remaining = amount - rune.getCount();

				rune.shrink(amount);

				if (remaining <= 0) {
					requiredRunes.removeInt(type);
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

	public static List<ItemStack> increaseStackSize(ItemStack stack, int addAmount) {
		int maxCount = stack.getMaxStackSize();

		if (stack.getCount() + addAmount <= maxCount) {
			stack.grow(addAmount);

			return Collections.emptyList();
		}

		List<ItemStack> newStacks = new ObjectArrayList<>((int)((addAmount + stack.getCount()) / (float)maxCount));

		while (addAmount > 0) {
			ItemStack copy = stack.copy();

			copy.setCount(Math.min(maxCount, addAmount));
			newStacks.add(copy);

			addAmount -= copy.getCount();
		}

		return newStacks;
	}
}
