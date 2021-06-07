package net.tslat.aoa3.client.render.entity.layer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.model.entity.misc.PlayerHaloModel;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.util.AoAHaloUtil;

public class PlayerHaloRenderLayer extends LayerRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("aoa3", "textures/entity/player/halo_texture.png");
	private static final PlayerHaloModel MODEL = new PlayerHaloModel();
	private final PlayerRenderer renderer;

	public PlayerHaloRenderLayer(PlayerRenderer playerRenderer) {
		super(playerRenderer);

		this.renderer = playerRenderer;
	}

	@Override
	public void render(MatrixStack matrix, IRenderTypeBuffer buffer, int packedLightIn, AbstractClientPlayerEntity player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if (player.isCapeLoaded() && !player.isInvisible() && AoAConfig.CLIENT.showPlayerHalos.get()) {
			AoAHaloUtil.Type chosenHalo = AoAHaloUtil.getHalo(player.getUUID());
			float red = 0;
			float green = 0;
			float blue = 0;

			if (chosenHalo == null)
				return;

			switch (chosenHalo) {
				case Donator:
					red = 1;
					green = 1;
					break;
				case Super_Donator:
				case Crazy_Donator:
					red = 1;
					break;
				case Tslat:
					red = 0.1647f;
					green = 1;
					break;
				case Staff:
					red = 0.6f;
					green = 1;
					blue = 1;
					break;
				case Wiki_Editor:
					blue = 1;
					break;
			}

			getParentModel().halo.copyFrom(renderer.getModel().head);
			IVertexBuilder vertexBuilder = buffer.getBuffer(getRenderType(getTextureLocation(player)));
			getParentModel().renderToBuffer(matrix, vertexBuilder, 15728640, OverlayTexture.NO_OVERLAY, red, green, blue, 1f);
		}
	}

	@Override
	public PlayerHaloModel getParentModel() {
		return MODEL;
	}

	@Override
	protected ResourceLocation getTextureLocation(AbstractClientPlayerEntity entityIn) {
		return TEXTURE;
	}

	private static RenderType getRenderType(ResourceLocation texture) {
		RenderState.TextureState renderState = new RenderState.TextureState(texture, false, false);
		RenderState.TransparencyState transparencyState = new RenderState.TransparencyState("additive_transparency", () -> {
			RenderSystem.enableBlend();
			RenderSystem.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
		}, () -> {
			RenderSystem.disableBlend();
			RenderSystem.defaultBlendFunc();
		});
		RenderState.FogState fogState = new RenderState.FogState("black_fog", () -> {
			RenderSystem.fog(2918, 0.0F, 0.0F, 0.0F, 1.0F);
			RenderSystem.enableFog();
		}, () -> {
			FogRenderer.levelFogColor();
			RenderSystem.disableFog();
		});

		return RenderType.create("halo", DefaultVertexFormats.NEW_ENTITY, 7, 256, false, true, RenderType.State.builder().setTextureState(renderState).setTransparencyState(transparencyState).setWriteMaskState(new RenderState.WriteMaskState(true, false)).setDepthTestState(new RenderState.DepthTestState("==", 515)).setDiffuseLightingState(new RenderState.DiffuseLightingState(true)).setFogState(fogState).createCompositeState(false));
	}
}
