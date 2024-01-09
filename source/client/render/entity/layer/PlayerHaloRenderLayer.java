package net.tslat.aoa3.client.render.entity.layer;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.TheEndPortalRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.client.model.misc.PlayerHaloModel;
import net.tslat.aoa3.common.registration.AoAConfigs;
import net.tslat.aoa3.player.halo.HaloTypes;
import net.tslat.aoa3.player.halo.PlayerHaloManager;

public class PlayerHaloRenderLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("aoa3", "textures/entity/player/halo_texture.png");
	private static final RenderType TSLAT_HALO_RENDER_TYPE = getTslatHaloRenderType();

	private final PlayerHaloModel model;

	public PlayerHaloRenderLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> renderer, EntityModelSet modelSet) {
		super(renderer);

		this.model = new PlayerHaloModel(modelSet.bakeLayer(PlayerHaloModel.LAYER_LOCATION));
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float netHeadPitch) {
		if (!player.isInvisible() && AoAConfigs.CLIENT.showPlayerHalos.get()) {
			PlayerHaloManager.getHaloForRender(player.getUUID()).ifPresent(halo -> {
				if (halo == HaloTypes.TSLAT) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(TSLAT_HALO_RENDER_TYPE);

					this.model.root.copyFrom(getParentModel().getHead());
					this.model.renderToBuffer(poseStack, vertexConsumer, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY, 0, 0, 0, 0.1f);

					return;
				}

				float red = 0;
				float green = 0;
				float blue = 0;

				switch (halo) {
					case DONATOR -> {
						red = 1;
						green = 1;
					}
					case SUPER_DONATOR -> red = 1;
					case STAFF -> {
						red = 0.6f;
						green = 1;
						blue = 1;
					}
					case WIKI_EDITOR -> blue = 1;
				}

				RenderSystem.enableBlend();
				RenderSystem.disableDepthTest();
				this.model.root.copyFrom(getParentModel().getHead());
				this.model.renderToBuffer(poseStack, bufferSource.getBuffer(RenderType.entityTranslucent(getTextureLocation(player))), LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY, red, green, blue, 0.1f);
				RenderSystem.enableDepthTest();
				RenderSystem.disableBlend();
			});
		}
	}

	@Override
	protected ResourceLocation getTextureLocation(AbstractClientPlayer entity) {
		return TEXTURE;
	}

	private static RenderType getTslatHaloRenderType() {
		RenderStateShard.TransparencyStateShard transparencyState = new RenderStateShard.TransparencyStateShard("additive_transparency", () -> {
			RenderSystem.enableBlend();
			RenderSystem.blendFunc(GlStateManager.SourceFactor.ONE_MINUS_SRC_COLOR, GlStateManager.DestFactor.ONE_MINUS_DST_COLOR);
		}, () -> {
			RenderSystem.disableBlend();
			RenderSystem.defaultBlendFunc();
		});

		return RenderType.create("halo", DefaultVertexFormat.POSITION, VertexFormat.Mode.QUADS, 256, false, false, RenderType.CompositeState.builder().setShaderState(new RenderStateShard.ShaderStateShard(GameRenderer::getRendertypeEndGatewayShader)).setTextureState(RenderStateShard.MultiTextureStateShard.builder().add(TheEndPortalRenderer.END_PORTAL_LOCATION, true, false).add(TheEndPortalRenderer.END_SKY_LOCATION, true, false).add(TheEndPortalRenderer.END_SKY_LOCATION, true, false).build()).setTransparencyState(transparencyState).createCompositeState(false));
	}
}