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
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 20, ColourUtil.RED), true, entity.getX(), entity.getY() + 0.45, entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 20, ColourUtil.RGB(223, 153, 0)), true, entity.getX(), entity.getY() + 0.3, entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 20, ColourUtil.YELLOW), true, entity.getX(), entity.getY() + 0.15, entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 20, ColourUtil.GREEN), true, entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 20, ColourUtil.CYAN), true, entity.getX(), entity.getY() - 0.15, entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 20, ColourUtil.BLUE), true, entity.getX(), entity.getY() - 0.3, entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 20, ColourUtil.RGB(193, 64, 215)), true, entity.getX(), entity.getY() - 0.45, entity.getZ(), 0, 0, 0);
		}
	}
}
*/
