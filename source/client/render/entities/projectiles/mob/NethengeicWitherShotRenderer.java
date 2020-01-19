package net.tslat.aoa3.client.render.entities.projectiles.mob;

import net.minecraft.client.model.ModelSkeletonHead;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.entity.projectiles.mob.EntityNethengeicWitherShot;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class NethengeicWitherShotRenderer extends Render<EntityNethengeicWitherShot> {
	private final ResourceLocation texture;
	private final ModelSkeletonHead headModel = new ModelSkeletonHead();

	public NethengeicWitherShotRenderer(RenderManager renderManager, ResourceLocation textureResource) {
		super(renderManager);

		this.texture = textureResource;
	}

	private float getRenderYaw(float prevRotationYaw, float rotationYaw, float partialTicks) {
		float rot;

		for (rot = rotationYaw - prevRotationYaw; rot < -180; rot += 360) {
			;
		}

		while (rot >= 180) {
			rot -= 360;
		}

		return prevRotationYaw + partialTicks * rot;
	}

	@Override
	public void doRender(EntityNethengeicWitherShot entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.disableCull();

		float renderYaw = this.getRenderYaw(entity.prevRotationYaw, entity.rotationYaw, partialTicks);
		float rotation = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;

		GlStateManager.translate((float)x, (float)y, (float)z);
		GlStateManager.enableRescaleNormal();
		GlStateManager.scale(-1.0F, -1.0F, 1.0F);
		GlStateManager.enableAlpha();
		this.bindEntityTexture(entity);

		if (renderOutlines) {
			GlStateManager.enableColorMaterial();
			GlStateManager.enableOutlineMode(this.getTeamColor(entity));
		}

		this.headModel.render(entity, 0.0F, 0.0F, 0.0F, renderYaw, rotation, 0.0625F);

		if (renderOutlines) {
			GlStateManager.disableOutlineMode();
			GlStateManager.disableColorMaterial();
		}

		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityNethengeicWitherShot entity) {
		return texture;
	}
}
