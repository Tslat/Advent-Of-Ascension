package net.tslat.aoa3.client.render.entities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.model.entities.mobs.overworld.ModelCharger;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.utils.ConfigurationUtil;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class AoAMeleeMobRenderer extends RenderLiving<AoAMeleeMob> {
	public static final ModelCharger chargerModel = new ModelCharger();
	public static final ResourceLocation chargerTexture = ModelCharger.getChargerTexture(chargerModel, null);

	private final ResourceLocation texture;
	private final float scale;

	public AoAMeleeMobRenderer(RenderManager renderManager, ModelBase model, float entityWidth, float scale, ResourceLocation textureResource) {
		super(renderManager, model, entityWidth / 3);
		this.texture = textureResource;
		this.scale = scale;
	}

	@Override
	public ModelBase getMainModel() {
		return ConfigurationUtil.MainConfig.funOptions.alwaysChargers ? chargerModel : super.getMainModel();
	}

	@Override
	protected void renderModel(AoAMeleeMob entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
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

	@Override
	protected void preRenderCallback(AoAMeleeMob mob, float partialTickTime) {
		GlStateManager.scale(scale, scale, scale);
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(AoAMeleeMob mob) {
		return ConfigurationUtil.MainConfig.funOptions.alwaysChargers ? chargerTexture : texture;
	}
}