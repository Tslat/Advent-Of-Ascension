package net.tslat.aoa3.client.render.entity.projectile.blasters;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.base.NonPhysicalWeaponProjectile;
import net.tslat.tme.api.util.RandomUtil;
import net.tslat.tme.api.particle.ParticleBuilder;

public class ConfettiShotRenderer extends ParticleProjectileRenderer<NonPhysicalWeaponProjectile> {
	public ConfettiShotRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(NonPhysicalWeaponProjectile entity, float partialTicks) {
		for (int i = 0; i < 8; i++) {
			ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position())
					.scaleMod(0.15f)
					.lifespan(Mth.ceil(10 / RandomUtil.valueBetween(0.2f, 1)))
					.power(new Vec3(0, -0.05f, 0))
					.colourTint((float)RandomUtil.gaussianValue(), (float)RandomUtil.gaussianValue(), (float)RandomUtil.gaussianValue(), 1f)
					.spawnClientParticles(entity.level());
		}
	}
}