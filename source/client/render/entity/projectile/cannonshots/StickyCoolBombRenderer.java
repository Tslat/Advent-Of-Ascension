package net.tslat.aoa3.client.render.entity.projectile.cannonshots;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.render.entity.projectile.TexturedProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.entity.projectile.cannon.StickyCoolBombEntity;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.util.NumberUtil;

public class StickyCoolBombRenderer extends TexturedProjectileRenderer<StickyCoolBombEntity> {
	public StickyCoolBombRenderer(final EntityRendererManager manager, final ResourceLocation textureResource) {
		super(manager, textureResource);
	}

	@Override
	public void render(StickyCoolBombEntity entity, float entityYaw, float partialTicks, MatrixStack matrix, IRenderTypeBuffer buffer, int packedLight) {
		super.render(entity, entityYaw, partialTicks, matrix, buffer, packedLight);

		for (int i = 0; i < 8; i++) {
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SWIRLY.get(), 1, 3, NumberUtil.RGB(193, 64, 215)), entity.getX(), entity.getY() + 0.25D, entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SWIRLY.get(), 1, 3, NumberUtil.RGB(0, 0, 255)), entity.getX(), entity.getY() - 0.25D, entity.getZ(), 0, 0, 0);
		}
	}
}
