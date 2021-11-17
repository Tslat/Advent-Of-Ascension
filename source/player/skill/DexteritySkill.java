package net.tslat.aoa3.player.skill;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.PlayerDataManager;
import net.tslat.aoa3.util.PlayerUtil;

public class DexteritySkill extends AoASkill.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.PLAYER_TICK, ListenerType.PLAYER_JUMP, ListenerType.CRITICAL_HIT};

	private double lastX = 0;
	private double lastZ = 0;
	private double cumulativeDistance = 0;

	private float cumulativeXp = 0;

	public DexteritySkill(PlayerDataManager plData, JsonObject jsonData) {
		super(AoASkills.DEXTERITY.get(), plData, jsonData);
	}

	public DexteritySkill(CompoundNBT nbtData) {
		super(AoASkills.DEXTERITY.get(), nbtData);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handlePlayerTick(TickEvent.PlayerTickEvent ev) {
		int level = getLevel(true);

		if (level >= 1000 || ev.player.isCreative() || ev.player.isSpectator() || ev.player.isPassenger())
			return;

		Vector3d pos = ev.player.position();

		if (ev.player.isSprinting()) {
			if (ev.player.onGround || ev.player.isSwimming()) {
				if (lastX != 0 && lastZ != 0) {
					double distX = pos.x() - this.lastX;
					double distZ = pos.z() - this.lastZ;
					double dist = Math.sqrt(distX * distX + distZ * distZ);

					if (dist < 1)
						cumulativeDistance += dist;
				}

				this.lastX = pos.x();
				this.lastZ = pos.z();
			}
		}
		else {
			this.lastX = 0;
			this.lastZ = 0;
		}

		if (ev.player.tickCount % 200 == 0) {
			if (cumulativeDistance > 0) {
				cumulativeXp += PlayerUtil.getTimeBasedXpForLevel(level, 200) * Math.min(1.75f, (float)(cumulativeDistance / 56f));
				cumulativeDistance = 0;
			}

			if (cumulativeXp > 0) {
				adjustXp(cumulativeXp, false, false);

				cumulativeXp = 0;
			}
		}
	}

	@Override
	public void handlePlayerJump(LivingEvent.LivingJumpEvent ev) {
		int level = getLevel(true);

		if (level >= 1000 || ev.getEntityLiving().isPassenger())
			return;

		float xp = PlayerUtil.getTimeBasedXpForLevel(level, 12);

		if (ev.getEntityLiving().isSprinting())
			xp *= 1.3f;

		cumulativeXp += xp;
	}

	@Override
	public void handleCriticalHit(CriticalHitEvent ev) {
		int level = getLevel(true);

		if (level >= 1000 || !ev.isVanillaCritical())
			return;

		cumulativeXp += ev.getDamageModifier() * PlayerUtil.getTimeBasedXpForLevel(level, 12);
	}
}
