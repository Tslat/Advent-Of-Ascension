package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.projectile.AbstractArrow;
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

		this.requireFullyCharged = GsonHelper.getAsBoolean(data, "require_full_charge", true);
		this.modifier = GsonHelper.getAsFloat(data, "modifier");
	}

	public BowDamageIncrease(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.BOW_DAMAGE_INCREASE.get(), skill, data);

		this.requireFullyCharged = data.getBoolean("require_full_charge");
		this.modifier = data.getFloat("modifier");
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	protected void updateDescription(MutableComponent defaultDescription) {
		super.updateDescription(Component.translatable(((TranslatableContents)defaultDescription.getContents()).getKey() + (requireFullyCharged ? ".fullCharge" : ""), NumberUtil.roundToNthDecimalPlace((modifier - 1) * 100, 2)));
	}

	@Override
	public void handleOutgoingAttack(LivingHurtEvent ev) {
		DamageSource source = ev.getSource();

		if (source.isProjectile() && source.getDirectEntity() instanceof AbstractArrow && (!requireFullyCharged || ((AbstractArrow)source.getDirectEntity()).isCritArrow()))
			ev.setAmount(ev.getAmount() * modifier);
	}

	@Override
	public CompoundTag getSyncData(boolean forClientSetup) {
		CompoundTag data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			data.putBoolean("require_full_charge", this.requireFullyCharged);
			data.putFloat("modifier", this.modifier);
		}

		return data;
	}
}
