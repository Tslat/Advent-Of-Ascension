package net.tslat.aoa3.client.render.entity.minion;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.model.entity.animal.RainicornModel;
import net.tslat.aoa3.client.render.entity.AoAMobRenderer;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.util.NumberUtil;

public class AlluricornRenderer extends AoAMobRenderer {
	public AlluricornRenderer(EntityRendererManager renderManager) {
		super(renderManager, new RainicornModel(), AoAEntities.Minions.ALLURICORN.get().getWidth(), 1f, new ResourceLocation("aoa3", "textures/entities/minions/alluricorn.png"));
	}

	@Override
	public void render(MobEntity entity, float yaw, float partialTicks, MatrixStack matrix, IRenderTypeBuffer buffer, int packedLight) {
		super.render(entity, yaw, partialTicks, matrix, buffer, packedLight);
		
		if (entity.getDeltaMovement().x() > 0 || entity.getDeltaMovement().z() > 0 || entity.getDeltaMovement().y() > 0) {
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, NumberUtil.RGB(223, 153, 0)), entity.getX(), entity.getY() + 1.5, entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, NumberUtil.RGB(255, 255, 255)), entity.getX(), entity.getY() + 1.25, entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, NumberUtil.RGB(223, 153, 0)), entity.getX(), entity.getY() + 1.05, entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, NumberUtil.RGB(255, 255, 255)), entity.getX(), entity.getY() + 0.95, entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, NumberUtil.RGB(223, 153, 0)), entity.getX(), entity.getY() + 0.75, entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, NumberUtil.RGB(255, 255, 255)), entity.getX(), entity.getY() + 0.5, entity.getZ(), 0, 0, 0);
		}
	}
}