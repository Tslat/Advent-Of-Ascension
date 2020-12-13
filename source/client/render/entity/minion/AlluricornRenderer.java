package net.tslat.aoa3.client.render.entity.minion;

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

public class AlluricornRenderer extends AoAMobRenderer {
	public AlluricornRenderer(EntityRendererManager renderManager) {
		super(renderManager, new RainicornModel(), AoAEntities.Minions.ALLURICORN.get().getWidth(), 1f, new ResourceLocation("aoa3", "textures/entities/minions/alluricorn.png"));
	}

	@Override
	public void render(MobEntity entity, float yaw, float partialTicks, MatrixStack matrix, IRenderTypeBuffer buffer, int packedLight) {
		super.render(entity, yaw, partialTicks, matrix, buffer, packedLight);
		
		if (entity.getMotion().getX() > 0 || entity.getMotion().getZ() > 0 || entity.getMotion().getY() > 0) {
			entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, NumberUtil.RGB(223, 153, 0)), entity.getPosX(), entity.getPosY() + 1.5, entity.getPosZ(), 0, 0, 0);
			entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, NumberUtil.RGB(255, 255, 255)), entity.getPosX(), entity.getPosY() + 1.25, entity.getPosZ(), 0, 0, 0);
			entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, NumberUtil.RGB(223, 153, 0)), entity.getPosX(), entity.getPosY() + 1.05, entity.getPosZ(), 0, 0, 0);
			entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, NumberUtil.RGB(255, 255, 255)), entity.getPosX(), entity.getPosY() + 0.95, entity.getPosZ(), 0, 0, 0);
			entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, NumberUtil.RGB(223, 153, 0)), entity.getPosX(), entity.getPosY() + 0.75, entity.getPosZ(), 0, 0, 0);
			entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, NumberUtil.RGB(255, 255, 255)), entity.getPosX(), entity.getPosY() + 0.5, entity.getPosZ(), 0, 0, 0);
		}
	}
}