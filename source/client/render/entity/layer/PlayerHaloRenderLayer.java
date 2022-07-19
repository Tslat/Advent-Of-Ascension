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
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.TheEndPortalRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.client.model.misc.PlayerHaloModel;
import net.tslat.aoa3.common.registration.AoAConfigs;
import net.tslat.aoa3.util.AoAHaloUtil;

public class PlayerHaloRenderLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("aoa3", "textures/entity/player/halo_texture.png");
	private static final RenderType TSLAT_HALO_RENDER_TYPE = getTslatHaloRenderType();

	private final PlayerHaloModel model;

	public PlayerHaloRenderLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> renderer, EntityModelSet modelSet) {
		super(renderer);

		this.model = new PlayerHaloModel(modelSet.bakeLayer(PlayerHaloModel.LAYER_LOCATION));
	}

	@Override
	public void render(PoseStack matrixStack, MultiBufferSource buffer, int pPackedLight, AbstractClientPlayer player, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		if (player.isCapeLoaded() && !player.isInvisible() && AoAConfigs.CLIENT.showPlayerHalos.get()) {
			AoAHaloUtil.Type chosenHalo = AoAHaloUtil.getHalo(player.getUUID());
			float red = 0;
			float green = 0;
			float blue = 0;

			if (chosenHalo == null)
				return;

			if (chosenHalo == AoAHaloUtil.Type.Tslat) {
				VertexConsumer vertexConsumer = buffer.getBuffer(TSLAT_HALO_RENDER_TYPE);

				model.root.copyFrom(getParentModel().getHead());
				model.renderToBuffer(matrixStack, vertexConsumer, 15728880, OverlayTexture.NO_OVERLAY, 0, 0, 0, 0.1f);

				return;
			}

			switch (chosenHalo) {
				case Donator -> {
					red = 1;
					green = 1;
				}
				case Super_Donator, Crazy_Donator -> red = 1;
				case Staff -> {
					red = 0.6f;
					green = 1;
					blue = 1;
				}
				case Wiki_Editor -> blue = 1;
			}

			VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityTranslucent(getTextureLocation(player)));

			model.root.copyFrom(getParentModel().getHead());

			RenderSystem.enableBlend();
			RenderSystem.disableDepthTest();
			model.renderToBuffer(matrixStack, vertexConsumer, 15728880, OverlayTexture.NO_OVERLAY, red, green, blue, 0.1f);
			RenderSystem.enableDepthTest();
			RenderSystem.disableBlend();
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