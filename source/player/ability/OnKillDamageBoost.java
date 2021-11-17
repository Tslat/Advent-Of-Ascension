package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.NumberUtil;

public class OnKillDamageBoost extends AoAAbility.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.OUTGOING_ATTACK_DURING, ListenerType.ENTITY_KILL};

	private long boostExpiry;

	private final float modifier;
	private final int postKillDuration;

	public OnKillDamageBoost(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.ON_KILL_DAMAGE_BOOST.get(), skill, data);

		this.modifier = JSONUtils.getAsFloat(data, "modifier");
		this.postKillDuration = JSONUtils.getAsInt(data, "post_kill_ticks");
	}

	public OnKillDamageBoost(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.ON_KILL_DAMAGE_BOOST.get(), skill, data);

		this.modifier = data.getFloat("modifier");
		this.postKillDuration = data.getInt("post_kill_ticks");
	}

	@Override
	public TranslationTextComponent getDescription() {
		return new TranslationTextComponent(super.getDescription().getKey(), NumberUtil.roundToNthDecimalPlace((modifier - 1) * 100, 2), NumberUtil.roundToNthDecimalPlace(postKillDuration / 20f, 2));
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleOutgoingAttack(LivingHurtEvent ev) {
		if (ev.getEntityLiving().level.getGameTime() < boostExpiry && !ev.getSource().isExplosion())
			ev.setAmount(ev.getAmount() * this.modifier);
	}

	@Override
	public void handleEntityKill(LivingDeathEvent ev) {
		this.boostExpiry = ev.getEntityLiving().level.getGameTime() + this.postKillDuration;
	}

	@Override
	public CompoundNBT getSyncData(boolean forClientSetup) {
		CompoundNBT data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			data.putFloat("modifier", this.modifier);
			data.putInt("post_kill_ticks", this.postKillDuration);
		}

		return data;
	}
}
