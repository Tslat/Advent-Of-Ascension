package net.tslat.aoa3.client.render.entity.projectile.blasters;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.model.entity.projectile.ArcwormShotModel;
import net.tslat.aoa3.client.render.entity.projectile.ModelledProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.entity.projectile.blaster.ArcwormShotEntity;
import net.tslat.aoa3.library.misc.CustomisableParticleType;
import net.tslat.aoa3.util.NumberUtil;

public class ArcwormShotRenderer extends ModelledProjectileRenderer<ArcwormShotEntity> {
	public ArcwormShotRenderer(final EntityRendererManager manager, final ResourceLocation textureResource) {
		super(manager, new ArcwormShotModel(), textureResource);
	}

	@Override
	protected void preRenderCallback(ArcwormShotEntity entity, MatrixStack matrix, float partialTicks) {
		matrix.rotate(new Quaternion(Vector3f.YP, 180 + entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks, true));
		matrix.rotate(new Quaternion(Vector3f.XP, entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, true));
	}

	@Override
	public void render(ArcwormShotEntity entity, float entityYaw, float partialTicks, MatrixStack matrix, IRenderTypeBuffer buffer, int packedLight) {
		super.render(entity, entityYaw, partialTicks, matrix, buffer, packedLight);

		for (int i = 0; i < 3; i++) {
			entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 20, NumberUtil.RGB(255, 0, 0)), true, entity.getPosX(), entity.getPosY() + 0.45, entity.getPosZ(), 0, 0, 0);
			entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 20, NumberUtil.RGB(223, 153, 0)), true, entity.getPosX(), entity.getPosY() + 0.3, entity.getPosZ(), 0, 0, 0);
			entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 20, NumberUtil.RGB(255, 255, 0)), true, entity.getPosX(), entity.getPosY() + 0.15, entity.getPosZ(), 0, 0, 0);
			entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 20, NumberUtil.RGB(0, 255, 0)), true, entity.getPosX(), entity.getPosY(), entity.getPosZ(), 0, 0, 0);
			entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 20, NumberUtil.RGB(0, 255, 255)), true, entity.getPosX(), entity.getPosY() - 0.15, entity.getPosZ(), 0, 0, 0);
			entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 20, NumberUtil.RGB(0, 0, 255)), true, entity.getPosX(), entity.getPosY() - 0.3, entity.getPosZ(), 0, 0, 0);
			entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 20, NumberUtil.RGB(193, 64, 215)), true, entity.getPosX(), entity.getPosY() - 0.45, entity.getPosZ(), 0, 0, 0);
		}
	}
}
