package net.tslat.aoa3.client.render.entity.mob;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.model.entity.mob.haven.RainicornModel;
import net.tslat.aoa3.client.render.entity.AoAMobRenderer;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.library.misc.CustomisableParticleType;
import net.tslat.aoa3.util.NumberUtil;

public class RainicornRenderer extends AoAMobRenderer {
	public RainicornRenderer(EntityRendererManager renderManager) {
		super(renderManager, new RainicornModel(), AoAEntities.Mobs.RAINICORN.get().getWidth() / 3f, 1f, new ResourceLocation("aoa3", "textures/entities/mobs/haven/rainicorn.png"));
	}

	@Override
	public void render(MobEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entity, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);

		if (entity.getMotion().getX() > 0 || entity.getMotion().getZ() > 0 || entity.getMotion().getY() > 0) {
			entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, NumberUtil.RGB(255, 0, 0)), entity.getPosX(), entity.getPosY() + 1.5, entity.getPosZ(), 0, 0, 0);
			entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, NumberUtil.RGB(223, 153, 0)), entity.getPosX(), entity.getPosY() + 1.25, entity.getPosZ(), 0, 0, 0);
			entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, NumberUtil.RGB(255, 255, 0)), entity.getPosX(), entity.getPosY() + 1.05, entity.getPosZ(), 0, 0, 0);
			entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, NumberUtil.RGB(0, 255, 0)), entity.getPosX(), entity.getPosY() + 0.95, entity.getPosZ(), 0, 0, 0);
			entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, NumberUtil.RGB(0, 255, 255)), entity.getPosX(), entity.getPosY() + 0.95, entity.getPosZ(), 0, 0, 0);
			entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, NumberUtil.RGB(0, 0, 255)), entity.getPosX(), entity.getPosY() + 0.75, entity.getPosZ(), 0, 0, 0);
			entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, NumberUtil.RGB(193, 64, 215)), entity.getPosX(), entity.getPosY() + 0.5, entity.getPosZ(), 0, 0, 0);
		}
	}
}