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
import net.tslat.aoa3.entity.projectiles.cannon.EntityMiniGreenBall;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class GreenBallRenderer extends Render<EntityMiniGreenBall> {
	private final ResourceLocation texture;
	private final float scale;

	public GreenBallRenderer(final RenderManager manager, final float scale, final ResourceLocation textureResource) {
		super(manager);

		this.texture = textureResource;
		this.scale = scale;
	}

	@Override
	public void doRender(EntityMiniGreenBall entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.scale(0.5f, 0.5f, 0.5f);
		bindEntityTexture(entity);
		GlStateManager.translate((float)x, (float)y,(float)z);
		GlStateManager.enableRescaleNormal();

		Tessellator tess = Tessellator.getInstance();
		BufferBuilder buffer = tess.getBuffer();

		GlStateManager.rotate(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);

		if (renderOutlines) {
			GlStateManager.enableColorMaterial();
			GlStateManager.enableOutlineMode(getTeamColor(entity));
		}

		GlStateManager.scale(scale, scale, scale);
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
		GlStateManager.scale(1 / scale, 1 / scale, 1 / scale);
		GlStateManager.popMatrix();
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityMiniGreenBall entity) {
		return texture;
	}
}
