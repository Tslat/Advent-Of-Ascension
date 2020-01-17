package net.tslat.aoa3.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.utils.ConfigurationUtil;

import javax.annotation.Nullable;

import static net.tslat.aoa3.client.render.AoAMeleeMobRenderer.chargerModel;
import static net.tslat.aoa3.client.render.AoAMeleeMobRenderer.chargerTexture;

@SideOnly(Side.CLIENT)
public class AoARangedMobRenderer extends RenderLiving<AoARangedMob> {
	private final ResourceLocation texture;
	private final float scale;

	public AoARangedMobRenderer(RenderManager renderManager, ModelBase model, float entityWidth, float scale, ResourceLocation textureResource) {
		super(renderManager, model, entityWidth / 3);
		this.texture = textureResource;
		this.scale = scale;
	}

	@Override
	public ModelBase getMainModel() {
		return ConfigurationUtil.MainConfig.funOptions.alwaysChargers ? chargerModel : super.getMainModel();
	}

	@Override
	protected void preRenderCallback(AoARangedMob entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(scale, scale, scale);
	}

	@Override
	protected void renderModel(AoARangedMob entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
		boolean visible = isVisible(entity);
		boolean notInvisible = !visible && !entity.isInvisibleToPlayer(Minecraft.getMinecraft().player);

		if (visible || notInvisible) {
			if (!bindEntityTexture(entity))
				return;

			if (notInvisible)
				GlStateManager.enableBlendProfile(GlStateManager.Profile.TRANSPARENT_MODEL);

			(ConfigurationUtil.MainConfig.funOptions.alwaysChargers ? chargerModel : mainModel).render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);

			if (notInvisible)
				GlStateManager.disableBlendProfile(GlStateManager.Profile.TRANSPARENT_MODEL);
		}
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(AoARangedMob mob) {
		return ConfigurationUtil.MainConfig.funOptions.alwaysChargers ? chargerTexture : texture;
	}
}
