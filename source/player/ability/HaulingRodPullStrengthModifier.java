package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.text.TranslationTextComponent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.event.custom.events.HaulingRodPullEntityEvent;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;

import static net.tslat.aoa3.player.AoAPlayerEventListener.ListenerType.HAULING_ROD_PULL_ENTITY;

public class HaulingRodPullStrengthModifier extends AoAAbility.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {HAULING_ROD_PULL_ENTITY};

	private final float amount;
	private final float perLevelAmount;

	public HaulingRodPullStrengthModifier(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.HAULING_ROD_PULL_STRENGTH.get(), skill, data);

		this.amount = JSONUtils.getAsFloat(data, "amount", 0);
		this.perLevelAmount = JSONUtils.getAsFloat(data, "per_level_amount", 0);
	}

	public HaulingRodPullStrengthModifier(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.HAULING_ROD_PULL_STRENGTH.get(), skill, data);

		this.amount = data.getFloat("amount");
		this.perLevelAmount = data.getFloat("per_level_amount");
	}

	@Override
	protected void updateDescription(TranslationTextComponent defaultDescription) {
		super.updateDescription(new TranslationTextComponent(defaultDescription.getKey(),
				LocaleUtil.getAbilityValueDesc(amount != 0, perLevelAmount != 0, true, NumberUtil.roundToNthDecimalPlace(amount * 100, 2), NumberUtil.roundToNthDecimalPlace(perLevelAmount * 100, 2))));
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleHaulingRodPullEntity(HaulingRodPullEntityEvent ev) {
		System.out.println(amount + perLevelAmount * skill.getLevel(false));
		ev.setPullStrength(ev.getPullStrength() * (1 + (amount + perLevelAmount * skill.getLevel(false))));
	}

	@Override
	public CompoundNBT getSyncData(boolean forClientSetup) {
		CompoundNBT data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			data.putFloat("amount", this.amount);
			data.putFloat("per_level_amount", this.perLevelAmount);
		}

		return data;
	}
}
