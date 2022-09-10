package net.tslat.aoa3.player.skill;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.PlayerUtil;

public class DexteritySkill extends AoASkill.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.PLAYER_TICK, ListenerType.PLAYER_JUMP, ListenerType.CRITICAL_HIT};

	private double lastX = 0;
	private double lastZ = 0;
	private double cumulativeDistance = 0;

	private float cumulativeXp = 0;

	public DexteritySkill(ServerPlayerDataManager plData, JsonObject jsonData) {
		super(AoASkills.DEXTERITY.get(), plData, jsonData);
	}

	public DexteritySkill(CompoundTag nbtData) {
		super(AoASkills.DEXTERITY.get(), nbtData);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handlePlayerTick(TickEvent.PlayerTickEvent ev) {
		if (!canGainXp(true) || ev.player.isPassenger())
			return;

		Vec3 pos = ev.player.position();

		if (ev.player.isSprinting()) {
			if (ev.player.isOnGround() || ev.player.isSwimming()) {
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
				cumulativeXp += PlayerUtil.getTimeBasedXpForLevel(getLevel(true), 100) * Math.min(1.75f, (float)(cumulativeDistance / 56f));
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
		if (!canGainXp(true))
			return;

		float xp = PlayerUtil.getTimeBasedXpForLevel(getLevel(true), 5);

		if (ev.getEntity().isSprinting())
			xp *= 1.3f;

		cumulativeXp += xp;
	}

	@Override
	public void handleCriticalHit(CriticalHitEvent ev) {
		if (!canGainXp(true))
			return;

		cumulativeXp += ev.getDamageModifier() * PlayerUtil.getTimeBasedXpForLevel(getLevel(true), 12);
	}
}
