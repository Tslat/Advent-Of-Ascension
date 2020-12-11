package net.tslat.aoa3.client.render.entity.mob;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.model.entity.mob.overworld.BloodmistModel;
import net.tslat.aoa3.client.render.entity.AoAMobRenderer;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.library.misc.CustomisableParticleType;
import net.tslat.aoa3.util.NumberUtil;

public class BloodmistRenderer extends AoAMobRenderer {
	public BloodmistRenderer(EntityRendererManager renderManager) {
		super(renderManager, new BloodmistModel(), AoAEntities.Mobs.BLOODMIST.get().getWidth() / 3f, 1.0f, new ResourceLocation("aoa3", "textures/entities/mobs/overworld/bloodmist.png"));
	}

	@Override
	public void render(MobEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entity, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);

		entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.FLICKERING_SPARKLER.get(), 0.75f, 3, NumberUtil.RGB(255, 0, 0)), entity.getPosX(), entity.getPosY() - 0.2, entity.getPosZ(), 0, 0, 0);
		entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.FLICKERING_SPARKLER.get(), 0.75f, 3, NumberUtil.RGB(255, 0, 0)), entity.getPosX(), entity.getPosY() + 0.2, entity.getPosZ(), 0, 0, 0);
	}
}