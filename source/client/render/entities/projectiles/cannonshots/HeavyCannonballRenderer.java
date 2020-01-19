package net.tslat.aoa3.client.render.entities.projectiles.cannonshots;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.entity.projectiles.cannon.EntityHeavyCannonball;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class HeavyCannonballRenderer extends Render<EntityHeavyCannonball> {
	private final ResourceLocation texture;

	public HeavyCannonballRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityHeavyCannonball entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		bindEntityTexture(entity);
		GlStateManager.translate((float)x, (float)y,(float)z);
		GlStateManager.enableRescaleNormal();
		GlStateManager.scale(0.5f, 0.5f, 0.5f);

		Tessellator tess = Tessellator.getInstance();
		BufferBuilder buffer = tess.getBuffer();

		GlStateManager.rotate(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);

		if (renderOutlines) {
			GlStateManager.enableColorMaterial();
			GlStateManager.enableOutlineMode(getTeamColor(entity));
		}

		buffer.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
		buffer.pos(-0.5D, -0.25D, 0.0D).tex(0.0D, 1.0D).normal(0.0F, 1.0F, 0.0F).endVertex();
		buffer.pos(0.5D, -0.25D, 0.0D).tex(1.0D, 1.0D).normal(0.0F, 1.0F, 0.0F).endVertex();
		buffer.pos(0.5D, 0.75D, 0.0D).tex(1.0D, 0.0D).normal(0.0F, 1.0F, 0.0F).endVertex();
		buffer.pos(-0.5D, 0.75D, 0.0D).tex(0.0D, 0.0D).normal(0.0F, 1.0F, 0.0F).endVertex();
		tess.draw();

		if (renderOutlines) {
			GlStateManager.disableOutlineMode();
			GlStateManager.disableColorMaterial();
		}

		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityHeavyCannonball entity) {
		return texture;
	}
}
