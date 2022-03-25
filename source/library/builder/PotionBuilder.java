package net.tslat.aoa3.library.builder;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;

public class PotionBuilder {
	Item potionItem;
	String displayName = null;
	boolean translatable = false;
	ArrayList<EffectInstance> effects = null;
	Integer colour = null;
	boolean dynamicColour = true;

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

	public PotionBuilder addEffect(EffectInstance effect) {
		if (effects == null)
			effects = new ArrayList<EffectInstance>(1);

		this.effects.add(effect);

		return this;
	}

	public PotionBuilder withColour(int colour) {
		this.colour = Integer.parseInt(String.valueOf(colour), 16);
		this.dynamicColour = false;

		return this;
	}

	public ItemStack build() {
		ItemStack stack = new ItemStack(potionItem);
		CompoundNBT nbt = stack.getOrCreateTag();
		CompoundNBT displayTag = stack.getOrCreateTagElement("display");

		if (displayName != null)
			stack.setHoverName(translatable ? new TranslationTextComponent(displayName) : new StringTextComponent(displayName));

		if (dynamicColour && effects != null)
			colour = PotionUtils.getColor(effects);

		if (colour != null)
			nbt.putString("CustomPotionColor", String.valueOf(colour));

		if (effects != null && !effects.isEmpty())
			PotionUtils.setCustomEffects(stack, effects);

		if (!displayTag.isEmpty())
			nbt.put("display", displayTag);

		return stack;
	}
}
