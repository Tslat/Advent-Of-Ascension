package net.tslat.aoa3.player.ability.hauling;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.common.registration.entity.AoADamageTypes;
import net.tslat.aoa3.event.custom.events.HaulingRodPullEntityEvent;
import net.tslat.aoa3.player.ability.generic.ScalableModAbility;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.DamageUtil;

import static net.tslat.aoa3.player.AoAPlayerEventListener.ListenerType.HAULING_ROD_PULL_ENTITY;

public class HaulingRodPullDamage extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {HAULING_ROD_PULL_ENTITY};


	public HaulingRodPullDamage(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.HAULING_ROD_PULL_DAMAGE.get(), skill, data);
	}

	public HaulingRodPullDamage(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.HAULING_ROD_PULL_DAMAGE.get(), skill, data);
	}

	@Override
	protected boolean isPercent() {
		return false;
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleHaulingRodPullEntity(HaulingRodPullEntityEvent ev) {
		if (ev.getHookedEntity() instanceof LivingEntity)
			DamageUtil.safelyDealDamage(DamageUtil.indirectEntityDamage(AoADamageTypes.HAULING, getPlayer(), ev.getBobber()), ev.getHookedEntity(), getScaledValue());
	}
}
