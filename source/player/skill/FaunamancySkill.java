/*
package net.tslat.aoa3.player.skill;

import com.google.gson.JsonObject;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.PlayerUtil;

public class FaunamancySkill extends AoASkill.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.OUTGOING_ATTACK_AFTER};

	public FaunamancySkill(ServerPlayerDataManager plData, JsonObject jsonData) {
		super(AoASkills.FAUNAMANCY.get(), plData, jsonData);
	}

	public FaunamancySkill(CompoundNBT nbtData) {
		super(AoASkills.FAUNAMANCY.get(), nbtData);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handlePostOutgoingAttack(LivingDamageEvent ev) {
		if (canGainXp(true) && isValidSacrifice(ev.getEntityLiving(), getPlayer()))
			adjustXp(PlayerUtil.getTimeBasedXpForLevel(getLevel(true), (int)(ev.getEntityLiving().getMaxHealth() / 30f * 20)), false, false);
	}

	public static boolean isValidSacrifice(LivingEntity target, PlayerEntity attacker) {
		if (target.getHealth() > 0)
			return false;

		if (!attacker.getMainHandItem().getItem().is(AoATags.Items.FAUNAMANCER_TOOL))
			return false;

		Vector3d targetVelocity = target.getDeltaMovement();

		return targetVelocity.x() == 0 && targetVelocity.z() == 0 && !NumberUtil.numberIsBetween(targetVelocity.y(), -0.08, -0.07);
	}
}
*/
