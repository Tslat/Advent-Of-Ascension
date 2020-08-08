package net.tslat.aoa3.client.render.entities.projectiles.cannonshots;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.model.entities.projectiles.ModelCobblestoneProjectile;
import net.tslat.aoa3.common.registration.ParticleRegister;
import net.tslat.aoa3.entity.projectiles.cannon.EntityRockFragmentFungal;
import net.tslat.aoa3.library.Enums;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RockFragmentFungalRenderer extends Render<EntityRockFragmentFungal> {
	private final ResourceLocation texture;
	private final ModelBase model = new ModelCobblestoneProjectile();

	public RockFragmentFungalRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityRockFragmentFungal entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.disableCull();

		try {
			GlStateManager.translate(x, y + 1, z);

			float rotation = entity.ticksExisted + partialTicks;

			GlStateManager.enableAlpha();
			GlStateManager.rotate(180, 0, 0, 0);
			this.renderModel(entity, 0, rotation, entity.ticksExisted, 0, 0, 0.0625F);
			GlStateManager.depthMask(true);
			GlStateManager.disableRescaleNormal();
			GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
			GlStateManager.enableTexture2D();
			GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
		}
		catch (Exception ex) {
			AdventOfAscension.logMessage(Level.ERROR, "Failed Rock Fragment Render", ex);
			ex.printStackTrace();
		}


		GlStateManager.enableCull();
		GlStateManager.popMatrix();

		for (int i = 0; i < 8; i++) {
			switch (AdventOfAscension.rand.nextInt(4)) {
				case 0:
					entity.world.spawnParticle(ParticleRegister.FLUFFY, true, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.GREEN, 100, 3, 5);
					break;
				case 1:
					entity.world.spawnParticle(ParticleRegister.FLUFFY, true, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.YELLOW, 100, 3, 5);
					break;
				case 2:
					entity.world.spawnParticle(ParticleRegister.FLUFFY, true, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.BLUE, 100, 3, 5);
					break;
				case 3:
					entity.world.spawnParticle(ParticleRegister.FLUFFY, true, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.PURPLE, 100, 3, 5);
					break;
			}
		}
	}

	private void renderModel(EntityRockFragmentFungal entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
		boolean flag = !entity.isInvisible() || renderOutlines;
		boolean flag1 = !flag && !entity.isInvisibleToPlayer(Minecraft.getMinecraft().player);

		if (flag || flag1) {
			if (!this.bindEntityTexture(entity))
				return;

			if (flag1)
				GlStateManager.enableBlendProfile(GlStateManager.Profile.TRANSPARENT_MODEL);

			this.model.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);

			if (flag1)
				GlStateManager.disableBlendProfile(GlStateManager.Profile.TRANSPARENT_MODEL);
		}
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityRockFragmentFungal entity) {
		return texture;
	}
}
