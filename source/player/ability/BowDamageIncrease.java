package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.NumberUtil;

public class BowDamageIncrease extends AoAAbility.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.OUTGOING_ATTACK_DURING};

	private final boolean requireFullyCharged;
	private final float modifier;

	public BowDamageIncrease(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.BOW_DAMAGE_INCREASE.get(), skill, data);

		this.requireFullyCharged = JSONUtils.getAsBoolean(data, "require_full_charge", true);
		this.modifier = JSONUtils.getAsFloat(data, "modifier");
	}

	public BowDamageIncrease(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.BOW_DAMAGE_INCREASE.get(), skill, data);

		this.requireFullyCharged = data.getBoolean("require_full_charge");
		this.modifier = data.getFloat("modifier");
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	protected void updateDescription(TranslationTextComponent defaultDescription) {
		super.updateDescription(new TranslationTextComponent(defaultDescription.getKey() + (requireFullyCharged ? ".fullCharge" : ""), NumberUtil.roundToNthDecimalPlace((modifier - 1) * 100, 2)));
	}

	@Override
	public void handleOutgoingAttack(LivingHurtEvent ev) {
		DamageSource source = ev.getSource();

		if (source.isProjectile() && source.getDirectEntity() instanceof AbstractArrowEntity && (!requireFullyCharged || ((AbstractArrowEntity)source.getDirectEntity()).isCritArrow()))
			ev.setAmount(ev.getAmount() * modifier);
	}

	@Override
	public CompoundNBT getSyncData(boolean forClientSetup) {
		CompoundNBT data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			data.putBoolean("require_full_charge", this.requireFullyCharged);
			data.putFloat("modifier", this.modifier);
		}

		return data;
	}
}
