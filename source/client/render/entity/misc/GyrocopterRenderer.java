package net.tslat.aoa3.client.render.entity.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.tslat.aoa3.client.model.entity.misc.GyrocopterModel;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.entity.misc.GyrocopterEntity;
import net.tslat.aoa3.library.misc.CustomisableParticleType;
import net.tslat.aoa3.util.NumberUtil;

import javax.annotation.Nullable;

public class GyrocopterRenderer extends EntityRenderer<GyrocopterEntity> {
	private final ResourceLocation texture = new ResourceLocation("aoa3", "textures/entities/boss/gyro/gyro.png");
	private final EntityModel<Entity> model = new GyrocopterModel();

	public GyrocopterRenderer(EntityRendererManager renderManager) {
		super(renderManager);
	}

	@Override
	public void render(GyrocopterEntity entity, float yaw, float partialTicks, MatrixStack matrix, IRenderTypeBuffer buffer, int packedLight) {
		matrix.push();
		matrix.scale(-1.0F, -1.0F, 1.0F);

		float pitch = MathHelper.lerp(partialTicks, entity.prevRotationPitch, entity.rotationPitch);

		model.setLivingAnimations(entity, 0, 0, partialTicks);
		model.setRotationAngles(entity, 0, 0, entity.ticksExisted, 0, pitch);
		matrix.translate(0.0D, -1.5f, 0.0D);

		boolean visible = !entity.isInvisible();
		boolean shade = !visible && !entity.isInvisibleToPlayer(Minecraft.getInstance().player);
		RenderType rendertype = getRenderType(entity, visible, shade);

		if (rendertype != null) {
			IVertexBuilder ivertexbuilder = buffer.getBuffer(rendertype);

			this.model.render(matrix, ivertexbuilder, packedLight, NumberUtil.RGB(255, 255, 255), 1f, 1f, 1f, shade ? 0.15f : 1f);
			entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.FLICKERING_SPARKLER.get(), 0.5f, 3, NumberUtil.RGB(0, 255, 255)), entity.getPosX(), entity.getPosY() + 0.3f, entity.getPosZ(), 0, 0, 0);
		}

		matrix.pop();
		super.render(entity, yaw, partialTicks, matrix, buffer, packedLight);
	}

	@Nullable
	protected RenderType getRenderType(GyrocopterEntity entity, boolean visible, boolean shade) {
		ResourceLocation texture = getEntityTexture(entity);

		if (shade) {
			return RenderType.getEntityTranslucent(texture);
		}
		else if (visible) {
			return model.getRenderType(texture);
		}
		else {
			return entity.isGlowing() ? RenderType.getOutline(texture) : null;
		}
	}

	@Nullable
	@Override
	public ResourceLocation getEntityTexture(GyrocopterEntity entity) {
		return texture;
	}
}