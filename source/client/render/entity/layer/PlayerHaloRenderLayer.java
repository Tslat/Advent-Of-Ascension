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
import net.tslat.aoa3.library.misc.AoAHalos;

public class PlayerHaloRenderLayer extends LayerRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("aoa3", "textures/entities/player/halo_texture.png");
	private static final PlayerHaloModel MODEL = new PlayerHaloModel();
	private final PlayerRenderer renderer;

	public PlayerHaloRenderLayer(PlayerRenderer playerRenderer) {
		super(playerRenderer);

		this.renderer = playerRenderer;
	}

	@Override
	public void render(MatrixStack matrix, IRenderTypeBuffer buffer, int packedLightIn, AbstractClientPlayerEntity player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if (player.hasPlayerInfo() && !player.isInvisible() && AoAConfig.CLIENT.showPlayerHalos.get()) {
			AoAHalos.Type chosenHalo = AoAHalos.getHalo(player.getUniqueID());
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

			getEntityModel().halo.copyModelAngles(renderer.getEntityModel().bipedHead);
			IVertexBuilder vertexBuilder = buffer.getBuffer(getRenderType(getEntityTexture(player)));
			getEntityModel().render(matrix, vertexBuilder, 15728640, OverlayTexture.NO_OVERLAY, red, green, blue, 1f);
		}
	}

	@Override
	public PlayerHaloModel getEntityModel() {
		return MODEL;
	}

	@Override
	protected ResourceLocation getEntityTexture(AbstractClientPlayerEntity entityIn) {
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
			FogRenderer.applyFog();
			RenderSystem.disableFog();
		});

		return RenderType.makeType("halo", DefaultVertexFormats.ENTITY, 7, 256, false, true, RenderType.State.getBuilder().texture(renderState).transparency(transparencyState).writeMask(new RenderState.WriteMaskState(true, false)).depthTest(new RenderState.DepthTestState(515)).diffuseLighting(new RenderState.DiffuseLightingState(true)).fog(fogState).build(false));
	}
}
