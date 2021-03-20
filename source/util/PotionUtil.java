package net.tslat.aoa3.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;

public abstract class PotionUtil {
	public static final int MAX_POTION_DURATION = 9999999;
	public static final int AMBIENT_POTION_DURATION = -1;

	public static boolean amplifyEffect(EffectInstance effect, int amplification) {
		return effect.update(new EffectInstance(effect.getEffect(), effect.getDuration(), effect.getAmplifier() + amplification, effect.isAmbient(), effect.isVisible(), effect.showIcon()));
	}

	public static class EffectBuilder {
		private final Effect effect;
		private final int duration;

		private int level = 1;
		private boolean ambient = false;
		private boolean showParticles = true;
		private boolean showIcon = true;

		public EffectBuilder(Effect effect) {
			this(effect, -1);
		}

		public EffectBuilder(Effect effect, int duration) {
			this.effect = effect;
			this.duration = duration;
		}

		public EffectBuilder isAmbient() {
			ambient = true;

			return this;
		}

		public EffectBuilder level(int level) {
			this.level = level;

			return this;
		}

		public EffectBuilder hideParticles() {
			showParticles = false;

			return this;
		}

		public EffectBuilder hideEffectIcon() {
			showIcon = false;

			return this;
		}

		public Effect getEffect() {
			return this.effect;
		}

		public EffectInstance build() {
			return new EffectInstance(effect, duration, level - 1, ambient, showParticles, showIcon);
		}
	}

	public static class PotionBuilder {
		Item potionItem;
		String displayName = null;
		boolean translatable = false;
		ArrayList<EffectInstance> effects = null;
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

		public PotionBuilder addEffect(EffectInstance effect) {
			if (effects == null)
				effects = new ArrayList<EffectInstance>(1);

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
			CompoundNBT nbt = stack.getOrCreateTag();
			CompoundNBT displayTag = stack.getOrCreateTagElement("display");

			if (displayName != null)
				stack.setHoverName(translatable ? new TranslationTextComponent(displayName) : new StringTextComponent(displayName));

			if (dynamicColour && effects != null)
				colour = PotionUtils.getColor(effects);

			if (colour != null)
				displayTag.putString("CustomPotionColor", Integer.toHexString(colour));

			if (effects != null && !effects.isEmpty())
				PotionUtils.setCustomEffects(stack, effects);

			if (!displayTag.isEmpty())
				nbt.put("display", displayTag);

			return stack;
		}
	}
}
