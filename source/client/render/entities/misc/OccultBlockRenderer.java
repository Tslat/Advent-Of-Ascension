package net.tslat.aoa3.client.render.entities.misc;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class OccultBlockRenderer extends RenderEntity {
	private final ResourceLocation texture;

	public OccultBlockRenderer(RenderManager renderManager, ResourceLocation texture) {
		super(renderManager);

		this.texture = texture;
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		if (!entity.isDead) {
			GlStateManager.pushMatrix();
			renderOffsetAABB(entity.getEntityBoundingBox(), x - entity.lastTickPosX, y - entity.lastTickPosY, z - entity.lastTickPosZ, getTeamColor(entity));
			GlStateManager.popMatrix();

			if (!this.renderOutlines)
				this.renderName(entity, x, y, z);
		}
	}

	public void renderOffsetAABB(AxisAlignedBB boundingBox, double x, double y, double z, int colour) {
		GlStateManager.disableTexture2D();
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		float red = (float)(colour >> 16 & 255) / 255.0F;
		float green = (float)(colour >> 8 & 255) / 255.0F;
		float blue = (float)(colour & 255) / 255.0F;

		GlStateManager.color(red, green, blue, 1f);
		bufferbuilder.setTranslation(x, y, z);
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_NORMAL);
		bufferbuilder.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).normal(0.0F, 0.0F, -1.0F).endVertex();
		bufferbuilder.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ).normal(0.0F, 0.0F, -1.0F).endVertex();
		bufferbuilder.pos(boundingBox.maxX, boundingBox.minY, boundingBox.minZ).normal(0.0F, 0.0F, -1.0F).endVertex();
		bufferbuilder.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ).normal(0.0F, 0.0F, -1.0F).endVertex();
		bufferbuilder.pos(boundingBox.minX, boundingBox.minY, boundingBox.maxZ).normal(0.0F, 0.0F, 1.0F).endVertex();
		bufferbuilder.pos(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ).normal(0.0F, 0.0F, 1.0F).endVertex();
		bufferbuilder.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ).normal(0.0F, 0.0F, 1.0F).endVertex();
		bufferbuilder.pos(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ).normal(0.0F, 0.0F, 1.0F).endVertex();
		bufferbuilder.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ).normal(0.0F, -1.0F, 0.0F).endVertex();
		bufferbuilder.pos(boundingBox.maxX, boundingBox.minY, boundingBox.minZ).normal(0.0F, -1.0F, 0.0F).endVertex();
		bufferbuilder.pos(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ).normal(0.0F, -1.0F, 0.0F).endVertex();
		bufferbuilder.pos(boundingBox.minX, boundingBox.minY, boundingBox.maxZ).normal(0.0F, -1.0F, 0.0F).endVertex();
		bufferbuilder.pos(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ).normal(0.0F, 1.0F, 0.0F).endVertex();
		bufferbuilder.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ).normal(0.0F, 1.0F, 0.0F).endVertex();
		bufferbuilder.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ).normal(0.0F, 1.0F, 0.0F).endVertex();
		bufferbuilder.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).normal(0.0F, 1.0F, 0.0F).endVertex();
		bufferbuilder.pos(boundingBox.minX, boundingBox.minY, boundingBox.maxZ).normal(-1.0F, 0.0F, 0.0F).endVertex();
		bufferbuilder.pos(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ).normal(-1.0F, 0.0F, 0.0F).endVertex();
		bufferbuilder.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).normal(-1.0F, 0.0F, 0.0F).endVertex();
		bufferbuilder.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ).normal(-1.0F, 0.0F, 0.0F).endVertex();
		bufferbuilder.pos(boundingBox.maxX, boundingBox.minY, boundingBox.minZ).normal(1.0F, 0.0F, 0.0F).endVertex();
		bufferbuilder.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ).normal(1.0F, 0.0F, 0.0F).endVertex();
		bufferbuilder.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ).normal(1.0F, 0.0F, 0.0F).endVertex();
		bufferbuilder.pos(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ).normal(1.0F, 0.0F, 0.0F).endVertex();
		tessellator.draw();
		bufferbuilder.setTranslation(0.0D, 0.0D, 0.0D);
		GlStateManager.enableTexture2D();
	}

	@Override
	protected int getTeamColor(Entity entityIn) {
		IBlockState block = entityIn.world.getBlockState(entityIn.getPosition());

		switch (block.getBlock().getHarvestLevel(block)) {
			case 0:
				return Enums.RGBIntegers.RED;
			case 1:
				return Enums.RGBIntegers.ORANGE;
			case 2:
				return Enums.RGBIntegers.YELLOW;
			case 3:
				return Enums.RGBIntegers.PIGMENT_GREEN;
			case 4:
			default:
				return Enums.RGBIntegers.DARK_LIME_GREEN;
		}
	}

	@Nullable
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}
