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
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.misc.GyrocopterEntity;
import net.tslat.aoa3.util.ColourUtil;

import javax.annotation.Nullable;

public class GyrocopterRenderer extends EntityRenderer<GyrocopterEntity> {
	private final ResourceLocation texture = new ResourceLocation("aoa3", "textures/entity/boss/gyro/gyro.png");
	private final EntityModel<Entity> model = new GyrocopterModel();

	public GyrocopterRenderer(EntityRendererManager renderManager) {
		super(renderManager);
	}

	@Override
	public void render(GyrocopterEntity entity, float yaw, float partialTicks, MatrixStack matrix, IRenderTypeBuffer buffer, int packedLight) {
		matrix.pushPose();
		matrix.scale(-1.0F, -1.0F, 1.0F);

		float pitch = MathHelper.lerp(partialTicks, entity.xRotO, entity.xRot);

		model.prepareMobModel(entity, 0, 0, partialTicks);
		model.setupAnim(entity, 0, 0, entity.tickCount, 0, pitch);
		matrix.translate(0.0D, -1.5f, 0.0D);

		boolean visible = !entity.isInvisible();
		boolean shade = !visible && !entity.isInvisibleTo(Minecraft.getInstance().player);
		RenderType rendertype = getRenderType(entity, visible, shade);

		if (rendertype != null) {
			IVertexBuilder ivertexbuilder = buffer.getBuffer(rendertype);

			this.model.renderToBuffer(matrix, ivertexbuilder, packedLight, ColourUtil.WHITE, 1f, 1f, 1f, shade ? 0.15f : 1f);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.FLICKERING_SPARKLER.get(), 0.5f, 3, ColourUtil.CYAN), entity.getX(), entity.getY() + 0.3f, entity.getZ(), 0, 0, 0);
		}

		matrix.popPose();
		super.render(entity, yaw, partialTicks, matrix, buffer, packedLight);
	}

	@Nullable
	protected RenderType getRenderType(GyrocopterEntity entity, boolean visible, boolean shade) {
		ResourceLocation texture = getTextureLocation(entity);

		if (shade) {
			return RenderType.entityTranslucent(texture);
		}
		else if (visible) {
			return model.renderType(texture);
		}
		else {
			return entity.isGlowing() ? RenderType.outline(texture) : null;
		}
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(GyrocopterEntity entity) {
		return texture;
	}
}