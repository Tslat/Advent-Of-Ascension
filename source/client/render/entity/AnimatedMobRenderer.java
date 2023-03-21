package net.tslat.aoa3.client.render.entity;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.LivingEntity;
import net.tslat.aoa3.client.render.entity.layer.GeoEntityChargeLayer;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class AnimatedMobRenderer<T extends LivingEntity & GeoEntity> extends GeoEntityRenderer<T> {
	private static final RenderType TRIS = RenderType.create("solid_tris", DefaultVertexFormat.BLOCK, VertexFormat.Mode.TRIANGLES, 2097152, true, false, RenderType.CompositeState.builder().setLightmapState(RenderType.LIGHTMAP).setShaderState(RenderType.RENDERTYPE_SOLID_SHADER).setTextureState(RenderType.BLOCK_SHEET_MIPPED).createCompositeState(true));

	public AnimatedMobRenderer(EntityRendererProvider.Context renderManager, GeoModel<T> model, float shadowSize) {
		super(renderManager, model);

		this.shadowRadius = shadowSize;

		addRenderLayer(new GeoEntityChargeLayer<>(this));
	}

	@Override
	public float getMotionAnimThreshold(T animatable) {
		return 0.009f;
	}
}
