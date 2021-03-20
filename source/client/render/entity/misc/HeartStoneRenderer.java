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
import net.tslat.aoa3.client.model.entity.misc.HeartStoneModel;
import net.tslat.aoa3.entity.misc.HeartStoneEntity;
import net.tslat.aoa3.util.NumberUtil;

import javax.annotation.Nullable;

public class HeartStoneRenderer extends EntityRenderer<HeartStoneEntity> {
	private final ResourceLocation texture = new ResourceLocation("aoa3", "textures/entities/misc/heart_stone.png");
	private final EntityModel<Entity> model = new HeartStoneModel();

	public HeartStoneRenderer(EntityRendererManager renderManager) {
		super(renderManager);
	}

	@Override
	public void render(HeartStoneEntity entity, float yaw, float partialTicks, MatrixStack matrix, IRenderTypeBuffer buffer, int packedLight) {
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

			this.model.renderToBuffer(matrix, ivertexbuilder, packedLight, NumberUtil.RGB(255, 255, 255), 1f, 1f, 1f, shade ? 0.15f : 1f);
		}

		matrix.popPose();
		super.render(entity, yaw, partialTicks, matrix, buffer, packedLight);
	}

	@Nullable
	protected RenderType getRenderType(HeartStoneEntity entity, boolean visible, boolean shade) {
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
	public ResourceLocation getTextureLocation(HeartStoneEntity entity) {
		return texture;
	}
}