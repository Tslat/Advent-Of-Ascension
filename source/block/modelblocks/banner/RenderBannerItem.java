package net.nevermine.block.modelblocks.banner;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.nevermine.block.modelblocks.ModelEternalBlock;
import org.lwjgl.opengl.GL11;

public class RenderBannerItem implements IItemRenderer {
	private ModelEternalBlock model;
	private ResourceLocation texture;

	public RenderBannerItem(final BlockBanner bannerBlock) {
		model = bannerBlock.model;
		texture = bannerBlock.texture;
	}

	public boolean handleRenderType(final ItemStack item, final IItemRenderer.ItemRenderType type) {
		return true;
	}

	public boolean shouldUseRenderHelper(final IItemRenderer.ItemRenderType type, final ItemStack item, final IItemRenderer.ItemRendererHelper helper) {
		return true;
	}

	public void renderItem(final IItemRenderer.ItemRenderType type, final ItemStack item, final Object... data) {
		GL11.glPushMatrix();
		GL11.glScalef(-0.6f, -0.6f, 0.6f);
		switch (type) {
			case INVENTORY: {
				GL11.glTranslatef(0.0f, 0.12f, 0.0f);
				break;
			}
			case EQUIPPED: {
				GL11.glTranslatef(-0.8f, -0.2f, 0.7f);
				break;
			}
			case EQUIPPED_FIRST_PERSON: {
				GL11.glTranslatef(0.0f, -0.7f, 0.7f);
				break;
			}
		}
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		model.render(0.0625f);
		GL11.glPopMatrix();
	}
}
