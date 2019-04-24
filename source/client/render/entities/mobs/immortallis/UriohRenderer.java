package net.tslat.aoa3.client.render.entities.mobs.immortallis;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.model.entities.mobs.immortallis.ModelUrioh;
import net.tslat.aoa3.entity.mobs.immortallis.EntityUrioh;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class UriohRenderer extends RenderLiving<EntityUrioh> {
	private final ResourceLocation texture;
	private static final float shadowScale = EntityUrioh.entityWidth / 3;

	public UriohRenderer(RenderManager renderManager, ResourceLocation textureResource) {
		super(renderManager, new ModelUrioh(), shadowScale);

		this.texture = textureResource;
	}

	@Override
	public void doRender(EntityUrioh entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected void preRenderCallback(EntityUrioh entity, float partialTickTime) {
		float health = entity.getHealth();

		if (health < 10) {
			GlStateManager.scale(0.1, 0.1, 0.1);
			this.shadowSize = shadowScale * 0.1f;
		}
		else if (health < 25) {
			GlStateManager.scale(0.2, 0.2, 0.2);
			this.shadowSize = shadowScale * 0.2f;
		}
		else if (health < 40) {
			GlStateManager.scale(0.4, 0.4, 0.4);
			this.shadowSize = shadowScale * 0.4f;
		}
		else if (health < 60) {
			GlStateManager.scale(0.6, 0.6, 0.6);
			this.shadowSize = shadowScale * 0.6f;
		}
		else {
			GlStateManager.scale(1, 1, 1);
			this.shadowSize = shadowScale;
		}
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityUrioh entity) {
		return texture;
	}
}