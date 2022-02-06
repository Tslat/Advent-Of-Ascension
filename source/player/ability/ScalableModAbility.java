package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.Util;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.RandomUtil;

public abstract class ScalableModAbility extends AoAAbility.Instance {
	private final float baseChance;
	private final float perLevelMod;

	public ScalableModAbility(AoAAbility ability, AoASkill.Instance skill, JsonObject data) {
		super(ability, skill, data);

		this.baseChance = JSONUtils.getAsFloat(data, "base_value", 0);
		this.perLevelMod = JSONUtils.getAsFloat(data, "per_level_mod", 0);
	}

	public ScalableModAbility(AoAAbility ability, AoASkill.Instance skill, CompoundNBT data) {
		super(ability, skill, data);

		this.baseChance = data.getFloat("base_value");
		this.perLevelMod = data.getFloat("per_level_mod");
	}

	@Override
	protected void updateDescription(TranslationTextComponent defaultDescription) {
		String defaultKey = Util.makeDescriptionId("ability", type().getRegistryName()) + ".description";

		if (defaultDescription.getKey().equals(defaultKey)) {
			TranslationTextComponent component = new TranslationTextComponent(defaultDescription.getKey(), getChanceDescriptionComponent(4));

			for (ITextComponent child : defaultDescription.getSiblings()) {
				component.append(child);
			}

			super.updateDescription(component);

			return;
		}

		super.updateDescription(defaultDescription);
	}

	protected TranslationTextComponent getChanceDescriptionComponent(int precision) {
		return LocaleUtil.getAbilityValueDesc(baseChance > 0, perLevelMod > 0, true,
				NumberUtil.roundToNthDecimalPlace(baseChance * 100, precision),
				NumberUtil.roundToNthDecimalPlace(perLevelMod * 100, precision));
	}

	protected boolean testAsChance() {
		return RandomUtil.percentChance(getScaledValue());
	}

	protected float getScaledValue() {
		if (perLevelMod == 0)
			return baseChance;

		return baseChance + skill.getLevel(false) * perLevelMod;
	}

	@Override
	public CompoundNBT getSyncData(boolean forClientSetup) {
		CompoundNBT data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			data.putFloat("base_value", this.baseChance);
			data.putFloat("per_level_mod", this.perLevelMod);
		}

		return data;
	}
}
