package net.tslat.aoa3.client.render.entity.projectile;

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
import net.tslat.aoa3.util.ColourUtil;

import javax.annotation.Nullable;

public class ModelledProjectileRenderer<T extends Entity> extends EntityRenderer<T> {
	private final ResourceLocation texture;
	protected final EntityModel<? super T> model;

	public ModelledProjectileRenderer(final EntityRendererManager renderManager, final EntityModel<? super T> model, final ResourceLocation texture) {
		super(renderManager);

		this.model = model;
		this.texture = texture;
	}

	@Override
	public void render(T entity, float yaw, float partialTicks, MatrixStack matrix, IRenderTypeBuffer buffer, int packedLight) {
		matrix.pushPose();
		matrix.scale(-1.0F, -1.0F, 1.0F);
		preRenderCallback(entity, matrix, partialTicks);

		float pitch = MathHelper.lerp(partialTicks, entity.xRotO, entity.xRot);

		model.prepareMobModel(entity, 0, 0, partialTicks);
		model.setupAnim(entity, 0, partialTicks, entity.tickCount, 0, pitch);
		matrix.translate(0.0D, -1.3f, 0.0D);

		boolean visible = !entity.isInvisible();
		boolean shade = !visible && !entity.isInvisibleTo(Minecraft.getInstance().player);
		RenderType rendertype = getRenderType(entity, visible, shade);

		if (rendertype != null) {
			IVertexBuilder ivertexbuilder = buffer.getBuffer(rendertype);

			this.model.renderToBuffer(matrix, ivertexbuilder, packedLight, ColourUtil.WHITE, 1f, 1f, 1f, shade ? 0.15f : 1f);
		}

		matrix.popPose();
		super.render(entity, yaw, partialTicks, matrix, buffer, packedLight);
	}

	@Nullable
	protected RenderType getRenderType(T entity, boolean visible, boolean shade) {
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

	protected void preRenderCallback(T entity, MatrixStack matrix, float partialTicks) {}

	@Override
	public ResourceLocation getTextureLocation(T entity) {
		return texture;
	}
}
