package net.tslat.aoa3.content.item.weapon.blaster;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponRayTrace;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.internal.networking.packet.TMEParticlePacket;

public class LaserBlaster extends AoABlaster<WeaponRayTrace> {
	public LaserBlaster(Item.Properties properties) {
		super(properties);
	}

	@Override
	void fireBlaster(ServerLevel level, WeaponFiringContext context) {
		fireRayTrace(level, context);
	}

	@Override
	protected void doFiringEffects(ServerLevel level, WeaponRayTrace effect, Vec3 pos, WeaponFiringContext context) {
		super.doFiringEffects(level, effect, pos, context);

		TMEParticlePacket packet = new TMEParticlePacket();
		Vec3 originPos = effect.visualStartPos();
		Vec3 hitPos = effect.endPos();

		packet.particle(ParticleBuilder.forPositionsInLine(ParticleTypes.SONIC_BOOM, originPos, hitPos, 6)
								.lifespan(1)
								.ignoreDistanceAndLimits()
								.scaleMod(0.05f)
								.colourTint(255, 0, 0, 255));
		packet.particle(ParticleBuilder.forPositionsInLine(ParticleTypes.END_ROD, originPos, hitPos, 6)
								.lifespan(1)
								.ignoreDistanceAndLimits()
								.scaleMod(0.4f)
								.colourTint(255, 0, 0, 255));
		packet.particle(ParticleBuilder.forPositions(ParticleTypes.WARPED_SPORE, hitPos)
								.lifespan(5)
								.ignoreDistanceAndLimits()
								.colourTint(255, 0, 0, 255)
								.spawnNTimes(2));

		packet.sendToAllPlayersTrackingEntity(context.getShooter());
	}
}
