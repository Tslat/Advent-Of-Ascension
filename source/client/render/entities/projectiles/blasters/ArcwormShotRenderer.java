package net.tslat.aoa3.client.render.entities.projectiles.blasters;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.fx.FXLastingFluffyTrail;
import net.tslat.aoa3.client.model.entities.mobs.shyrelands.ModelArcworm;
import net.tslat.aoa3.entity.projectiles.blaster.EntityArcwormShot;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class ArcwormShotRenderer extends Render<EntityArcwormShot> {
	private final ResourceLocation texture;
	private final ModelBase model = new ModelArcworm();

	public ArcwormShotRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityArcwormShot entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.disableCull();

		GlStateManager.translate((float)x, (float)y, (float)z);
		GlStateManager.rotate(180 + entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, 1.0F, 0.0F, 0.0F);
		GlStateManager.enableRescaleNormal();
		GlStateManager.scale(-1.0F, -1.0F, 1.0F);
		GlStateManager.enableAlpha();
		this.bindEntityTexture(entity);

		if (renderOutlines) {
			GlStateManager.enableColorMaterial();
			GlStateManager.enableOutlineMode(this.getTeamColor(entity));
		}

		model.render(entity, entity.ticksExisted + partialTicks, 1f, 0.0F, 0, 0, 0.0625F);

		if (renderOutlines) {
			GlStateManager.disableOutlineMode();
			GlStateManager.disableColorMaterial();
		}

		GlStateManager.popMatrix();

		for (int i = 0; i < 3; i++) {
			new FXLastingFluffyTrail(entity.world, entity.posX, entity.posY + 0.45, entity.posZ, 0, 0, 0, Enums.RGBIntegers.RED, 8, 0.75f).create();
			new FXLastingFluffyTrail(entity.world, entity.posX, entity.posY + 0.3, entity.posZ, 0, 0, 0, Enums.RGBIntegers.ORANGE, 8, 0.75f).create();
			new FXLastingFluffyTrail(entity.world, entity.posX, entity.posY + 0.15, entity.posZ, 0, 0, 0, Enums.RGBIntegers.YELLOW, 8, 0.75f).create();
			new FXLastingFluffyTrail(entity.world, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.GREEN, 8, 0.75f).create();
			new FXLastingFluffyTrail(entity.world, entity.posX, entity.posY - 0.15, entity.posZ, 0, 0, 0, Enums.RGBIntegers.CYAN, 8, 0.75f).create();
			new FXLastingFluffyTrail(entity.world, entity.posX, entity.posY - 0.3, entity.posZ, 0, 0, 0, Enums.RGBIntegers.BLUE, 8, 0.75f).create();
			new FXLastingFluffyTrail(entity.world, entity.posX, entity.posY - 0.45, entity.posZ, 0, 0, 0, Enums.RGBIntegers.PURPLE, 8, 0.75f).create();
		}

		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityArcwormShot entity) {
		return texture;
	}
}
