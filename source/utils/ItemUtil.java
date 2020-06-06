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
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.hooks.ThirdPartyInteractions;
import net.tslat.aoa3.hooks.ic2.IC2Compat;
import net.tslat.aoa3.item.SkillItem;
import net.tslat.aoa3.item.misc.ItemTieredBullet;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
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

	public static Item findAndConsumeBullet(final EntityLivingBase shooter, final BaseGun gun, boolean consume, ItemStack weaponStack) {
		if (!(shooter instanceof EntityPlayer)) {
			return ItemRegister.LIMONITE_BULLET;
		}
		else {
			EntityPlayer pl = (EntityPlayer)shooter;

			int greed = 1 + EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.GREED, weaponStack);

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
				return ItemRegister.LIMONITE_BULLET;
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
			int amount = 1 + EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.GREED, weaponStack);

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

	public static ItemStack cloneItemStackForComparisons(ItemStack stackToClone) {
		ItemStack newStack = new ItemStack(stackToClone.getItem(), 1, stackToClone.isItemStackDamageable() ? 0 : stackToClone.getItemDamage(), null);

		if (stackToClone.getTagCompound() != null)
			newStack.setTagCompound(stackToClone.getTagCompound());

		return newStack;
	}

	public static ItemStack shallowCloneItemStack(ItemStack stackToClone) {
		return new ItemStack(stackToClone.getItem(), 1, stackToClone.isItemStackDamageable() ? 0 : stackToClone.getItemDamage(), null);
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

	public static boolean hasAllItems(EntityPlayer player, Item... items) {
		HashSet<Item> itemSet = new HashSet<Item>(Arrays.asList(items));

		for (ItemStack stack : player.inventory.mainInventory) {
			itemSet.removeIf(item -> item == stack.getItem());

			if (itemSet.isEmpty())
				return true;
		}

		for (ItemStack stack : player.inventory.armorInventory) {
			itemSet.removeIf(item -> item == stack.getItem());

			if (itemSet.isEmpty())
				return true;
		}

		for (ItemStack stack : player.inventory.offHandInventory) {
			itemSet.removeIf(item -> item == stack.getItem());

			if (itemSet.isEmpty())
				return true;
		}

		return false;
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
