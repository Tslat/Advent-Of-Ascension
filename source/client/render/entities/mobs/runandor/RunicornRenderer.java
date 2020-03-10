package net.tslat.aoa3.client.render.entities.mobs.runandor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.fx.FXFluffyTrail;
import net.tslat.aoa3.client.model.entities.mobs.overworld.ModelCharger;
import net.tslat.aoa3.client.model.entities.mobs.runandor.ModelRunicorn;
import net.tslat.aoa3.entity.mobs.runandor.EntityRunicorn;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RunicornRenderer extends RenderLiving<EntityRunicorn> {
	public static final ModelCharger chargerModel = new ModelCharger();
	public static final ResourceLocation chargerTexture = new ResourceLocation("aoa3", "textures/entities/mobs/overworld/charger.png");

	private final ResourceLocation texture;

	public RunicornRenderer(RenderManager renderManager, ResourceLocation textureResource) {
		super(renderManager, new ModelRunicorn(), EntityRunicorn.entityWidth / 3);

		this.texture = textureResource;
	}
	@Override
	public ModelBase getMainModel() {
		return ConfigurationUtil.MainConfig.funOptions.alwaysChargers ? chargerModel : super.getMainModel();
	}

	@Override
	protected void renderModel(EntityRunicorn entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
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
	public void doRender(EntityRunicorn entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);

		if (!ConfigurationUtil.MainConfig.funOptions.alwaysChargers && (entity.motionX > 0 || entity.motionZ > 0 || entity.motionY > 0)) {
			new FXFluffyTrail(entity.world, entity.posX, entity.posY + 1.5, entity.posZ, 0, 0, 0, Enums.RGBIntegers.CYAN, 5, 0.75f).create();
			new FXFluffyTrail(entity.world, entity.posX, entity.posY + 1.25, entity.posZ, 0, 0, 0, Enums.RGBIntegers.WHITE, 5, 0.75f).create();
			new FXFluffyTrail(entity.world, entity.posX, entity.posY + 1.05, entity.posZ, 0, 0, 0, Enums.RGBIntegers.BLUE, 5, 0.75f).create();
			new FXFluffyTrail(entity.world, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.WHITE, 5, 0.75f).create();
			new FXFluffyTrail(entity.world, entity.posX, entity.posY + 0.95, entity.posZ, 0, 0, 0, Enums.RGBIntegers.BLUE, 5, 0.75f).create();
			new FXFluffyTrail(entity.world, entity.posX, entity.posY + 0.75, entity.posZ, 0, 0, 0, Enums.RGBIntegers.WHITE, 5, 0.75f).create();
			new FXFluffyTrail(entity.world, entity.posX, entity.posY + 0.5, entity.posZ, 0, 0, 0, Enums.RGBIntegers.CYAN, 5, 0.75f).create();
		}
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityRunicorn mob) {
		return ConfigurationUtil.MainConfig.funOptions.alwaysChargers ? chargerTexture : texture;
	}
}