package net.tslat.aoa3.client.model.entities.player;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.handlers.PlayerHaloHandler;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;

@SideOnly(Side.CLIENT)
public class LayerPlayerHalo implements LayerRenderer<AbstractClientPlayer> {
	private final RenderPlayer playerRenderer;

	private static final ResourceLocation haloTexture = new ResourceLocation("aoa3", "textures/entities/player/halo_texture.png");
	private static final ModelPlayerHalo haloModel = new ModelPlayerHalo();

	public LayerPlayerHalo(RenderPlayer playerRenderer) {
		this.playerRenderer = playerRenderer;
	}

	@Override
	public void doRenderLayer(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netheadYaw, float headPitch, float scale) {
		if (player.hasPlayerInfo() && !player.isInvisible() && ConfigurationUtil.MainConfig.showPlayerHalos) {
			Enums.PlayerHaloTypes chosenHalo = PlayerHaloHandler.getHalo(player.getUniqueID());

			if (chosenHalo == null)
				return;

			switch (chosenHalo) {
				case Donator:
					GlStateManager.color(1, 1, 0, 1);
					break;
				case Super_Donator:
				case Crazy_Donator:
					GlStateManager.color(1, 0, 0, 1);
					break;
				case Tslat:
					GlStateManager.color(0.1647f, 1f, 0f, 1f);
					break;
				case Staff:
					GlStateManager.color(0.6f, 1f, 1f, 1f);
					break;
				case Wiki_Editor:
					GlStateManager.color(0, 0, 1, 1);
					break;
			}

			playerRenderer.bindTexture(haloTexture);
			GlStateManager.pushMatrix();
			GlStateManager.pushAttrib();
			GlStateManager.enableBlend();
			GlStateManager.disableAlpha();
			GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
			GlStateManager.depthMask(true);
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 61680, 0);
			Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
			ModelRenderer playerHeadModelRenderer = playerRenderer.getMainModel().bipedHead;
			ModelBase.copyModelAngles(playerHeadModelRenderer, haloModel.halo);
			haloModel.halo.renderWithRotation(scale);
			Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, player.getBrightnessForRender() % 65536, player.getBrightnessForRender() / 65536f);
			GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
			GlStateManager.disableBlend();
			GlStateManager.enableAlpha();
			GlStateManager.popAttrib();
			GlStateManager.popMatrix();
		}
	}

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}
}
