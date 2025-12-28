package net.tslat.aoa3.client.render.entity.projectile.mob;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.base.NonPhysicalWeaponProjectile;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.api.util.RandomUtil;

public class UltimatumShotRenderer extends ParticleProjectileRenderer<NonPhysicalWeaponProjectile> {
	public UltimatumShotRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(NonPhysicalWeaponProjectile entity, float partialTicks) {
		ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position())
				.scaleMod(0.25f)
				.colourTint(ColourUtil.CYAN)
				.spawnClientParticles(entity.level());

		float colourMod = entity.level().random.nextFloat() * 0.7f + 0.3f;

		ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position())
				.colourTint(colourMod, colourMod, colourMod, 1f)
				.spawnClientParticles(entity.level());

		colourMod = entity.level().random.nextFloat() * 0.7f + 0.3f;

		ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position().add(RandomUtil.valueBetween(-0.5d, 0.5d), RandomUtil.valueBetween(-0.5d, 0.5d), RandomUtil.valueBetween(-0.5d, 0.5d)))
				.scaleMod(0.5f)
				.colourTint(colourMod, colourMod, 0, 1f)
				.spawnClientParticles(entity.level());
	}
}