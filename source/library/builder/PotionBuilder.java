package net.tslat.aoa3.library.builder;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;

import java.util.ArrayList;

public class PotionBuilder {
	Item potionItem;
	String displayName = null;
	boolean translatable = false;
	ArrayList<MobEffectInstance> effects = null;
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

	public PotionBuilder addEffect(MobEffectInstance effect) {
		if (effects == null)
			effects = new ArrayList<MobEffectInstance>(1);

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
		CompoundTag nbt = stack.getOrCreateTag();
		CompoundTag displayTag = stack.getOrCreateTagElement("display");

		if (displayName != null)
			stack.setHoverName(translatable ? Component.translatable(displayName) : Component.literal(displayName));

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
