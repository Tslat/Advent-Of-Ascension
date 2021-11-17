package net.tslat.aoa3.player.ability;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.StringUtil;

import java.util.HashSet;
import java.util.function.Consumer;

public class PotionDurationReducer extends AoAAbility.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.POTION_APPLIED};

	private final float modifier;
	private final boolean useAddition;

	private final EffectType matchType;
	private final HashSet<ResourceLocation> matchEffects;

	private final Consumer<EffectInstance> modifierConsumer;

	public PotionDurationReducer(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.POTION_DURATION_REDUCER.get(), skill, data);

		this.modifier = JSONUtils.getAsFloat(data, "modifier");
		this.useAddition = JSONUtils.getAsBoolean(data, "use_addition", false);

		if (data.has("match_effect_type")) {
			matchType = EffectType.valueOf(data.get("match_effect_type").getAsString());
			matchEffects = null;
		}
		else {
			matchEffects = new HashSet<ResourceLocation>();
			JsonElement effects = data.get("match_effects");

			if (effects.isJsonPrimitive()) {
				matchEffects.add(new ResourceLocation(effects.getAsString()));
			}
			else if (effects.isJsonArray()) {
				for (JsonElement element : effects.getAsJsonArray()) {
					matchEffects.add(new ResourceLocation(element.getAsString()));
				}
			}

			matchType = null;
		}

		this.modifierConsumer = effect -> {
			if (useAddition) {
				effect.duration += (int)modifier;
			}
			else {
				effect.duration *= modifier;
			}
		};
	}

	public PotionDurationReducer(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.POTION_DURATION_REDUCER.get(), skill, data);

		this.modifier = data.getFloat("modifier");
		this.useAddition = data.getBoolean("use_addition");

		if (data.contains("match_effect_type")) {
			matchType = EffectType.valueOf(data.getString("match_effect_type"));
			matchEffects = null;
		}
		else {
			matchEffects = new HashSet<ResourceLocation>();
			INBT effects = data.get("match_effects");

			if (effects.getType() == StringNBT.TYPE) {
				matchEffects.add(new ResourceLocation(effects.getAsString()));
			}
			else if (effects.getType() == ListNBT.TYPE) {
				for (INBT element : ((ListNBT)effects)) {
					matchEffects.add(new ResourceLocation(element.getAsString()));
				}
			}

			matchType = null;
		}

		this.modifierConsumer = null;
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public TranslationTextComponent getDescription() {
		String operationSuffix = useAddition ? ".addition" : ".multiply";
		String value = useAddition ? NumberUtil.roundToNthDecimalPlace(modifier, 2) : NumberUtil.roundToNthDecimalPlace((modifier - 1) * 100, 2);

		if (matchType != null) {
			return new TranslationTextComponent(super.getDescription().getKey() + operationSuffix + ".type", StringUtil.toTitleCase(matchType.toString()), value);
		}
		else {
			return new TranslationTextComponent(super.getDescription().getKey() + operationSuffix + ".list", value);
		}
	}

	@Override
	public void handleAppliedPotion(PotionEvent.PotionAddedEvent ev) {
		EffectInstance effect = ev.getPotionEffect();

		if (!effectMatches(effect))
			return;

		reduceEffectDuration(effect, this.modifierConsumer);
	}

	private boolean effectMatches(EffectInstance effect) {
		if (matchType != null)
			return effect.getEffect().getCategory() == matchType;

		return matchEffects.contains(effect.getEffect().getRegistryName());
	}

	private void reduceEffectDuration(EffectInstance effect, Consumer<EffectInstance> modifier) {
		modifier.accept(effect);

		if (effect.hiddenEffect != null)
			reduceEffectDuration(effect.hiddenEffect, modifierConsumer);
	}

	@Override
	public CompoundNBT getSyncData(boolean forClientSetup) {
		CompoundNBT data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			data.putFloat("modifier", this.modifier);
			data.putBoolean("use_addition", this.useAddition);

			if (this.matchType != null) {
				data.putString("match_effect_type", this.matchType.toString());
			}
			else {
				ListNBT effects = new ListNBT();

				for (ResourceLocation id : this.matchEffects) {
					effects.add(StringNBT.valueOf(id.toString()));
				}

				data.put("match_effects", effects);
			}
		}

		return data;
	}
}
