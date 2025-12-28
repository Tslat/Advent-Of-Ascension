/*
package net.tslat.aoa3.client.render.entity.projectile.blasters;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.client.render.entity.projectile.ModelledProjectileRenderer;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.blaster.ArcwormShotEntity;
import net.tslat.aoa3.util.ColourUtil;

public class ArcwormShotRenderer extends ModelledProjectileRenderer<ArcwormShotEntity> {
	public ArcwormShotRenderer(final EntityRendererProvider.Context manager, final ResourceLocation textureResource) {
		super(manager, new ArcwormShotModel(), textureResource);
	}

	@Override
	protected void preRenderCallback(ArcwormShotEntity entity, PoseStack matrix, float partialTicks) {
		matrix.mulPose(new Quaternion(Vector3f.YP, 180 + entity.yRotO + (entity.getYRot() - entity.yRotO) * partialTicks, true));
		matrix.mulPose(new Quaternion(Vector3f.XP, entity.xRotO + (entity.getXRot() - entity.xRotO) * partialTicks, true));
	}

	@Override
	public void render(ArcwormShotEntity entity, float entityYaw, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int packedLight) {
		super.render(entity, entityYaw, partialTicks, matrix, buffer, packedLight);

		for (int i = 0; i < 3; i++) {
			ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position().add(0, 0.45f, 0))
					.scaleMod(0.75f)
					.lifespan(Mth.ceil(20 / RandomUtil.valueBetween(0.2f, 1)))
					.colourTint(ColourUtil.RED)
					.spawnClientParticles(entity.level());
			ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position().add(0, 0.3f, 0))
					.scaleMod(0.75f)
					.lifespan(Mth.ceil(20 / RandomUtil.valueBetween(0.2f, 1)))
					.colourTint(0xDF9900)
					.spawnClientParticles(entity.level());
			ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position().add(0, 0.15f, 0))
					.scaleMod(0.75f)
					.lifespan(Mth.ceil(20 / RandomUtil.valueBetween(0.2f, 1)))
					.colourTint(ColourUtil.YELLOW)
					.spawnClientParticles(entity.level());
			ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position())
					.scaleMod(0.75f)
					.lifespan(Mth.ceil(20 / RandomUtil.valueBetween(0.2f, 1)))
					.colourTint(ColourUtil.GREEN)
					.spawnClientParticles(entity.level());
			ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position().subtract(0, 0.15f, 0))
					.scaleMod(0.75f)
					.lifespan(Mth.ceil(20 / RandomUtil.valueBetween(0.2f, 1)))
					.colourTint(ColourUtil.CYAN)
					.spawnClientParticles(entity.level());
			ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position().subtract(0, 0.3f, 0))
					.scaleMod(0.75f)
					.lifespan(Mth.ceil(20 / RandomUtil.valueBetween(0.2f, 1)))
					.colourTint(ColourUtil.BLUE)
					.spawnClientParticles(entity.level());
			ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position().subtract(0, 0.45f, 0))
					.scaleMod(0.75f)
					.lifespan(Mth.ceil(20 / RandomUtil.valueBetween(0.2f, 1)))
					.colourTint(0xC140D7)
					.spawnClientParticles(entity.level());
		}
	}
}
*/
