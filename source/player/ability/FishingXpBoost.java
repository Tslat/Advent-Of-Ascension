package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;

public class FishingXpBoost extends AoAAbility.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.GAIN_VANILLA_XP};

	private final float amount;
	private final float perLevelAmount;
	private final boolean useAddition;

	public FishingXpBoost(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.FISHING_XP_BOOST.get(), skill, data);

		this.amount = JSONUtils.getAsFloat(data, "amount", 0f);
		this.perLevelAmount = JSONUtils.getAsFloat(data, "per_level_amount", 0f);
		this.useAddition = JSONUtils.getAsBoolean(data, "use_addition", false);
	}

	public FishingXpBoost(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.FISHING_XP_BOOST.get(), skill, data);

		this.amount = data.getFloat("amount");
		this.perLevelAmount = data.getFloat("per_level_amount");
		this.useAddition = data.getBoolean("use_addition");
	}

	@Override
	protected void updateDescription(TranslationTextComponent defaultDescription) {
		String value = useAddition ? NumberUtil.roundToNthDecimalPlace((int)amount, 0) : NumberUtil.roundToNthDecimalPlace(amount * 100, 2);
		String perLevelValue = useAddition ? NumberUtil.roundToNthDecimalPlace((int)perLevelAmount, 0) : NumberUtil.roundToNthDecimalPlace(perLevelAmount * 100, 2);

		super.updateDescription(new TranslationTextComponent(defaultDescription.getKey(), LocaleUtil.getAbilityValueDesc(amount != 0, perLevelAmount != 0, !useAddition, value, perLevelValue)));
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleVanillaXpGain(PlayerXpEvent.XpChange ev) {
		if (getPlayer().fishing != null) {
			float xp = ev.getAmount();

			if (useAddition) {
				xp += (amount + perLevelAmount * skill.getLevel(false));
			}
			else {
				xp *= 1 + (amount + perLevelAmount * skill.getLevel(false));
			}

			ev.setAmount((int)xp);
		}
	}

	@Override
	public CompoundNBT getSyncData(boolean forClientSetup) {
		CompoundNBT data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			data.putFloat("amount", this.amount);
			data.putFloat("per_level_amount", this.perLevelAmount);
			data.putBoolean("use_addition", this.useAddition);
		}

		return data;
	}
}
