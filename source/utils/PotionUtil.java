package net.tslat.aoa3.utils;

import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;

import java.util.ArrayList;

public class PotionUtil {


	public static class PotionBuilder {
		Item potionItem;
		String displayName = null;
		boolean translatable = false;
		ArrayList<PotionEffect> effects = null;
		Integer colour = null;
		boolean dynamicColour = false;

		public PotionBuilder(Item potionItem) {
			this.potionItem = potionItem;
		}

		public PotionBuilder withName(String name) {
			this.displayName = name;

			return this;
		}

		public PotionBuilder withTranslationKey(String nameLangKey) {
			this.displayName = nameLangKey;
			this.translatable = true;

			return this;
		}

		public PotionBuilder addEffect(PotionEffect effect) {
			if (effects == null)
				effects = new ArrayList<PotionEffect>(1);

			this.effects.add(effect);

			return this;
		}

		public PotionBuilder withColour(int colour) {
			this.colour = Integer.parseInt(String.valueOf(colour), 16);

			return this;
		}

		public PotionBuilder enableDynamicColour() {
			this.dynamicColour = true;

			return this;
		}

		public ItemStack build() {
			ItemStack stack = new ItemStack(potionItem);
			NBTTagCompound nbt = new NBTTagCompound();

			if (displayName != null) {
				NBTTagCompound displayCompound = new NBTTagCompound();

				displayCompound.setString(translatable ? "LocName" : "Name", displayName);

				nbt.setTag("display", displayCompound);
			}

			if (dynamicColour && effects != null)
				colour = PotionUtils.getPotionColorFromEffectList(effects);

			if (colour != null) {
				NBTTagCompound displayCompound = new NBTTagCompound();

				if (nbt.hasKey("display"))
					displayCompound = (NBTTagCompound)nbt.getTag("display");

				displayCompound.setString("Color", "#" + Integer.toHexString(colour));
			}

			stack.setTagCompound(nbt);

			if (effects != null && !effects.isEmpty()) {
				PotionEffect primaryEffect = effects.get(0);
				PotionType attemptedType = PotionType.REGISTRY.getObject(primaryEffect.getPotion().getRegistryName());

				if (attemptedType != PotionTypes.EMPTY) {
					PotionUtils.addPotionToItemStack(stack, attemptedType);
					effects.remove(0);
				}

				PotionUtils.appendEffects(stack, effects);
			}

			return stack;
		}
	}
}
