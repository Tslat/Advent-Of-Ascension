package net.tslat.aoa3.client.render.entities.mobs.creeponia;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.render.entities.layers.RenderLayerCustomCreeperCharge;
import net.tslat.aoa3.entity.mobs.creeponia.EntityCreeponiaCreeper;
import net.tslat.aoa3.utils.ConfigurationUtil;

import javax.annotation.Nullable;

import static net.tslat.aoa3.client.render.AoAMeleeMobRenderer.chargerModel;
import static net.tslat.aoa3.client.render.AoAMeleeMobRenderer.chargerTexture;

@SideOnly(Side.CLIENT)
public class CreeponiaCreeperRenderer extends RenderLiving<EntityCreeponiaCreeper> {
	private final ResourceLocation texture;
	private final ModelBase model;

	public CreeponiaCreeperRenderer(RenderManager renderManager, ModelBase model, float entityWidth, ResourceLocation textureResource) {
		super(renderManager, model, entityWidth / 3);

		this.model = model;
		this.texture = textureResource;

		addLayer(new RenderLayerCustomCreeperCharge(this));
	}

	@Override
	public ModelBase getMainModel() {
		return ConfigurationUtil.MainConfig.funOptions.alwaysChargers ? chargerModel : super.getMainModel();
	}

	@Override
	protected void preRenderCallback(EntityCreeponiaCreeper creeper, float partialTickTime) {
		float flashIntensity = creeper.getCreeperFlashIntensity(partialTickTime);
		float scaleRotationMod = 1.0F + MathHelper.sin(flashIntensity * 100.0F) * flashIntensity * 0.01F;
		flashIntensity = (float)Math.pow(MathHelper.clamp(flashIntensity, 0.0F, 1.0F), 3);
		float scaleHorizontal = (1.0F + flashIntensity * 0.4F) * scaleRotationMod;
		float scaleVertical = (1.0F + flashIntensity * 0.1F) / scaleRotationMod;

		GlStateManager.scale(scaleHorizontal, scaleVertical, scaleHorizontal);
	}


	@Override
	protected int getColorMultiplier(EntityCreeponiaCreeper creeper, float lightBrightness, float partialTickTime) {
		float flashIntensity = creeper.getCreeperFlashIntensity(partialTickTime);

		if ((int)(flashIntensity * 10.0F) % 2 == 0) {
			return 0;
		}
		else {
			int flashColourMod = MathHelper.clamp((int)(flashIntensity * 0.2F * 255.0F), 0, 255);

			return flashColourMod << 24 | 822083583;
		}
	}

	@Override
	protected void renderModel(EntityCreeponiaCreeper entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
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
	protected ResourceLocation getEntityTexture(EntityCreeponiaCreeper mob) {
		return ConfigurationUtil.MainConfig.funOptions.alwaysChargers ? chargerTexture : texture;
	}
}
