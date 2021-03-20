package net.tslat.aoa3.client.render.entity.projectile.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.entity.projectile.misc.PlutonSticklerStuckEntity;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.util.NumberUtil;

import javax.annotation.Nullable;

public class PlutonSticklerStuckRenderer extends EntityRenderer<PlutonSticklerStuckEntity> {
	private final ResourceLocation texture;

	public PlutonSticklerStuckRenderer(final EntityRendererManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void render(PlutonSticklerStuckEntity entity, float entityYaw, float partialTicks, MatrixStack matrix, IRenderTypeBuffer buffer, int packedLight) {
		for (int i = 0; i < 7; i++) {
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SWIRLY.get(), 1, 3, NumberUtil.RGB(255, 255, 0)), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
		}
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(PlutonSticklerStuckEntity entity) {
		return texture;
	}
}
