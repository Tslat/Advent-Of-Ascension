package net.nevermine.block.modelblocks.utility;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.nevermine.block.modelblocks.ModelEternalBlock;
import org.lwjgl.opengl.GL11;

public class RenderUtilityBlockItem implements IItemRenderer {
	private ModelEternalBlock model;
	private ResourceLocation texture;

	public RenderUtilityBlockItem(final BlockUtilityBlock UtilityBlockBlock) {
		model = UtilityBlockBlock.model;
		texture = UtilityBlockBlock.texture;
	}

	public boolean handleRenderType(final ItemStack item, final IItemRenderer.ItemRenderType type) {
		return true;
	}

	public boolean shouldUseRenderHelper(final IItemRenderer.ItemRenderType type, final ItemStack item, final IItemRenderer.ItemRendererHelper helper) {
		return true;
	}

	public void renderItem(final IItemRenderer.ItemRenderType type, final ItemStack item, final Object... data) {
		GL11.glPushMatrix();
		GL11.glScalef(-1.2f, -1.2f, 1.2f);
		switch (type) {
			case INVENTORY: {
				GL11.glTranslatef(0.0f, -1.0f, 0.0f);
				break;
			}
			case EQUIPPED: {
				GL11.glTranslatef(-0.8f, -1.2f, 0.7f);
				break;
			}
			case EQUIPPED_FIRST_PERSON: {
				GL11.glTranslatef(0.0f, -2.0f, 0.7f);
				break;
			}
		}
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		model.render(0.0625f);
		GL11.glPopMatrix();
	}
}
