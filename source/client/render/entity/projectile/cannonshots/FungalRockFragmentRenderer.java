package net.tslat.aoa3.client.render.entity.projectile.cannonshots;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.model.entity.projectile.CobblestoneProjectileModel;
import net.tslat.aoa3.client.render.entity.projectile.ModelledProjectileRenderer;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.object.entity.projectile.cannon.FungalRockFragmentEntity;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.RandomUtil;

public class FungalRockFragmentRenderer extends ModelledProjectileRenderer<FungalRockFragmentEntity> {
	public FungalRockFragmentRenderer(final EntityRendererManager manager, final ResourceLocation textureResource) {
		super(manager, new CobblestoneProjectileModel(), textureResource);
	}

	@Override
	protected void preRenderCallback(FungalRockFragmentEntity entity, MatrixStack matrix, float partialTicks) {
		model.setupAnim(entity, 0, entity.tickCount + partialTicks, entity.getAge(), 0, 0);

		super.preRenderCallback(entity, matrix, partialTicks);
	}

	@Override
	public void render(FungalRockFragmentEntity entity, float entityYaw, float partialTicks, MatrixStack matrix, IRenderTypeBuffer buffer, int packedLight) {
		super.render(entity, entityYaw, partialTicks, matrix, buffer, packedLight);

		for (int i = 0; i < 8; i++) {
			switch (RandomUtil.randomNumberUpTo(4)) {
				case 0:
					entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 1, 3, ColourUtil.GREEN), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
					break;
				case 1:
					entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 1, 3, ColourUtil.YELLOW), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
					break;
				case 2:
					entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 1, 3, ColourUtil.BLUE), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
					break;
				case 3:
					entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 1, 3, ColourUtil.RGB(193, 64, 215)), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
					break;
			}
		}
	}
}