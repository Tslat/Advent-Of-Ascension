package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.entity.IAngerable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.player.skill.AoASkill;

public class FishingHostileTargetingImmunity extends AoAAbility.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.ENTITY_TARGET};

	public FishingHostileTargetingImmunity(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.FISHING_HOSTILE_TARGETING_IMMUNITY.get(), skill, data);
	}

	public FishingHostileTargetingImmunity(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.FISHING_HOSTILE_TARGETING_IMMUNITY.get(), skill, data);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleEntityTarget(LivingSetAttackTargetEvent ev) {
		PlayerEntity player = getPlayer();
		LivingEntity entity = ev.getEntityLiving();

		if (player.fishing != null && entity.getLastHurtByMob() != player) {
			if (entity instanceof MobEntity) {
				((MobEntity)entity).setTarget(null);
			}
			else if (entity instanceof IAngerable) {
				((IAngerable)entity).setTarget(null);
			}
		}
	}
}
