package net.tslat.aoa3.content.item.weapon.blaster;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.effectslib.api.particle.ParticleBuilder;
import net.tslat.effectslib.networking.packet.TELParticlePacket;
import org.jetbrains.annotations.Nullable;


public class LaserBlaster extends BaseBlaster {
	public LaserBlaster(double baseDmg, int durability, int fireDelayTicks, int chargeTime, float energyCost) {
		super(baseDmg, durability, fireDelayTicks, chargeTime, energyCost);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_ILLUSION_SMG_FIRE.get();
	}

	@Override
	public float getBeamDistance(ItemStack stack, @Nullable LivingEntity shooter) {
		return 40;
	}

	// TODO Remove
	@Override
	protected ShotInfo fireBlaster(ServerLevel level, LivingEntity shooter, ItemStack blaster, boolean temp) {
		return super.fireBlaster(level, shooter, blaster, false);
	}

	@Override
	protected void doFiringEffects(ServerLevel level, LivingEntity shooter, ItemStack stack, ShotInfo shotInfo) {
		TELParticlePacket packet = new TELParticlePacket();
		Vec3 originPos = shotInfo.shotOrBarrelPosForVfx();
		Vec3 hitPos = shotInfo.getHitPos().orElse(originPos);

		packet.particle(ParticleBuilder.forPositionsInLine(ParticleTypes.SONIC_BOOM, originPos, hitPos, 6)
				.lifespan(1)
				.ignoreDistanceAndLimits()
				.scaleMod(0.05f)
				.colourOverride(255, 0, 0, 255));
		packet.particle(ParticleBuilder.forPositionsInLine(ParticleTypes.END_ROD, originPos, hitPos, 6)
				.lifespan(1)
				.ignoreDistanceAndLimits()
				.scaleMod(0.4f)
				.colourOverride(255, 0, 0, 255));
		packet.particle(ParticleBuilder.forPositions(ParticleTypes.WARPED_SPORE, hitPos)
				.lifespan(5)
				.ignoreDistanceAndLimits()
				.colourOverride(255, 0, 0, 255)
				.spawnNTimes(2));

		packet.sendToAllPlayersTrackingEntity(level, shooter);
	}
}
