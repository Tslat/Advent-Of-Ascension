package net.tslat.aoa3.client.render.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import javax.annotation.Nullable;

public class TranslucentAnimatedMobRenderer<T extends LivingEntity & IAnimatable> extends AnimatedMobRenderer<T> {
	public TranslucentAnimatedMobRenderer(EntityRendererManager renderManager, AnimatedGeoModel<T> model, float shadowSize) {
		super(renderManager, model, shadowSize);
	}

	@Override
	public RenderType getRenderType(T animatable, float partialTicks, MatrixStack stack, @Nullable IRenderTypeBuffer renderTypeBuffer, @Nullable IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
		return RenderType.entityTranslucent(textureLocation);
	}
}
