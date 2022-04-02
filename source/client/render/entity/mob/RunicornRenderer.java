/*
package net.tslat.aoa3.client.render.entity.mob;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.entity.Mob;
import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.client.model.entity.mob.runandor.RunicornModel;
import net.tslat.aoa3.client.render.entity.AoAMobRenderer;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;

import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.util.ColourUtil;

public class RunicornRenderer extends AoAMobRenderer {
	public RunicornRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new RunicornModel(), AoAMobs.RUNICORN.get().getWidth() / 3f, 1.0f, new ResourceLocation("aoa3", "textures/entity/mob/runandor/runicorn.png"));
	}

	@Override
	public void render(Mob entity, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
		super.render(entity, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);

		if (entity.getDeltaMovement().x() > 0 || entity.getDeltaMovement().z() > 0 || entity.getDeltaMovement().y() > 0) {
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, ColourUtil.CYAN), entity.getX(), entity.getY() + 1.5, entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, ColourUtil.WHITE), entity.getX(), entity.getY() + 1.25, entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, ColourUtil.BLUE), entity.getX(), entity.getY() + 1.05, entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, ColourUtil.WHITE), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, ColourUtil.BLUE), entity.getX(), entity.getY() + 0.95, entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, ColourUtil.WHITE), entity.getX(), entity.getY() + 0.75, entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, ColourUtil.CYAN), entity.getX(), entity.getY() + 0.5, entity.getZ(), 0, 0, 0);
		}
	}
}*/
