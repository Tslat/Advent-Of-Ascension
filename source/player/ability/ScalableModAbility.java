package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.Util;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.tslat.aoa3.library.object.DynamicTextComponent;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.RandomUtil;

public abstract class ScalableModAbility extends AoAAbility.Instance {
	protected final float baseValue;
	protected final float perLevelMod;

	public ScalableModAbility(AoAAbility ability, AoASkill.Instance skill, JsonObject data) {
		super(ability, skill, data);

		this.baseValue = JSONUtils.getAsFloat(data, "base_value", 0);
		this.perLevelMod = JSONUtils.getAsFloat(data, "per_level_mod", 0);
	}

	public ScalableModAbility(AoAAbility ability, AoASkill.Instance skill, CompoundNBT data) {
		super(ability, skill, data);

		this.baseValue = data.getFloat("base_value");
		this.perLevelMod = data.getFloat("per_level_mod");
	}

	@Override
	protected void updateDescription(TranslationTextComponent defaultDescription) {
		String defaultKey = Util.makeDescriptionId("ability", type().getRegistryName()) + ".description";

		if (defaultDescription.getKey().equals(defaultKey) && defaultDescription.getArgs().length == 0) {
			TranslationTextComponent component = new TranslationTextComponent(defaultDescription.getKey(), getScalingDescriptionComponent(4));

			for (ITextComponent child : defaultDescription.getSiblings()) {
				component.append(child);
			}

			super.updateDescription(component);

			return;
		}

		super.updateDescription(defaultDescription);
	}

	protected TranslationTextComponent getScalingDescriptionComponent(int precision) {
		return LocaleUtil.getAbilityValueDesc(baseValue != 0, perLevelMod != 0, isPercent(),
				NumberUtil.roundToNthDecimalPlace(baseValue * (isPercent() ? 100 : 1), precision),
				NumberUtil.roundToNthDecimalPlace(perLevelMod * (isPercent() ? 100 : 1), precision),
				new DynamicTextComponent(() -> NumberUtil.roundToNthDecimalPlace(getScaledValue() * (isPercent() ? 100 : 1), precision)));
	}

	protected boolean isPercent() {
		return true;
	}

	protected boolean testAsChance() {
		return RandomUtil.percentChance(getScaledValue());
	}

	protected float getScaledValue() {
		if (perLevelMod == 0)
			return baseValue;

		return baseValue + skill.getLevel(false) * perLevelMod;
	}

	@Override
	public CompoundNBT getSyncData(boolean forClientSetup) {
		CompoundNBT data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			data.putFloat("base_value", this.baseValue);
			data.putFloat("per_level_mod", this.perLevelMod);
		}

		return data;
	}
}
