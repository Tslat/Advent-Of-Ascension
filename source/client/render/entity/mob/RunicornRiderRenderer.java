package net.tslat.aoa3.client.render.entity.mob;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.model.entity.mob.runandor.RunicornRiderModel;
import net.tslat.aoa3.client.render.entity.AoAMobRenderer;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.util.ColourUtil;

public class RunicornRiderRenderer extends AoAMobRenderer {
	public RunicornRiderRenderer(EntityRendererManager renderManager) {
		super(renderManager, new RunicornRiderModel(), AoAEntities.Mobs.RUNICORN_RIDER.get().getWidth() / 3f, 1f, new ResourceLocation("aoa3", "textures/entity/mob/runandor/runicorn_rider.png"));

	}

	@Override
	public void render(MobEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entity, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);

		if (entity.getHealth() > 0 && entity.isAlive() && (entity.getDeltaMovement().x() > 0 || entity.getDeltaMovement().z() > 0 || entity.getDeltaMovement().y() > 0)) {
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, ColourUtil.CYAN), entity.getX(), entity.getY() + 1.5, entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, ColourUtil.WHITE), entity.getX(), entity.getY() + 1.25, entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, ColourUtil.BLUE), entity.getX(), entity.getY() + 1.05, entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, ColourUtil.WHITE), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, ColourUtil.BLUE), entity.getX(), entity.getY() + 0.95, entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, ColourUtil.WHITE), entity.getX(), entity.getY() + 0.75, entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, ColourUtil.CYAN), entity.getX(), entity.getY() + 0.5, entity.getZ(), 0, 0, 0);
		}
	}
}