package net.tslat.aoa3.content.item.weapon.blaster;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.packet.AoANetworking;
import net.tslat.aoa3.common.packet.packets.ServerParticlePacket;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.library.builder.ParticleBuilder;
import net.tslat.aoa3.util.MathUtil;
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
		ServerParticlePacket packet = new ServerParticlePacket();
		Vec3 originPos = shotInfo.shotOrBarrelPosForVfx();
		Vec3 hitPos = shotInfo.getHitPos().orElse(originPos);

		for (Vec3 linePos : MathUtil.inLine(originPos, hitPos, originPos.distanceTo(hitPos) * 6)) {
			packet.particle(ParticleBuilder.forPos(ParticleTypes.SONIC_BOOM, linePos)
					.lifespan(1)
					.ignoreDistanceAndLimits()
					.scaleMod(0.05f)
					.colourOverride(1, 0, 0, 1f));
			packet.particle(ParticleBuilder.forPos(ParticleTypes.END_ROD, linePos)
					.lifespan(1)
					.ignoreDistanceAndLimits()
					.scaleMod(0.4f)
					.colourOverride(1, 0, 0, 1f));
		}

		packet.particle(ParticleBuilder.forPos(ParticleTypes.WARPED_SPORE, hitPos)
				.lifespan(5)
				.ignoreDistanceAndLimits()
				.colourOverride(1, 0, 0, 1f)
				.spawnNTimes(2));

		AoANetworking.sendToAllPlayersTrackingEntity(packet, shooter);
	}
}
