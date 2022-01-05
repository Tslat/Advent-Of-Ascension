package net.tslat.aoa3.client.render.entity.mob;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.model.entity.animal.RainicornModel;
import net.tslat.aoa3.client.render.entity.AoAMobRenderer;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.NumberUtil;

public class RainicornRenderer extends AoAMobRenderer {
	public RainicornRenderer(EntityRendererManager renderManager) {
		super(renderManager, new RainicornModel(), AoAEntities.Animals.RAINICORN.get().getWidth() / 3f, 1f, new ResourceLocation("aoa3", "textures/entity/animals/rainicorn.png"));
	}

	@Override
	public void render(MobEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entity, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);

		if (entity.getDeltaMovement().x() > 0 || entity.getDeltaMovement().z() > 0 || entity.getDeltaMovement().y() > 0) {
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, ColourUtil.RED), entity.getX(), entity.getY() + 1.5, entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, ColourUtil.RGB(223, 153, 0)), entity.getX(), entity.getY() + 1.25, entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, ColourUtil.YELLOW), entity.getX(), entity.getY() + 1.05, entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, ColourUtil.GREEN), entity.getX(), entity.getY() + 0.95, entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, ColourUtil.CYAN), entity.getX(), entity.getY() + 0.95, entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, ColourUtil.BLUE), entity.getX(), entity.getY() + 0.75, entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, ColourUtil.RGB(193, 64, 215)), entity.getX(), entity.getY() + 0.5, entity.getZ(), 0, 0, 0);
		}
	}
}