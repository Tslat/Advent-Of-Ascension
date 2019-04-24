package net.nevermine.rendering.indiv;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class StaffRender implements IItemRenderer {
	private final float scale;

	public StaffRender(final float scale) {
		this.scale = scale;
	}

	public boolean handleRenderType(final ItemStack item, final IItemRenderer.ItemRenderType type) {
		return type == IItemRenderer.ItemRenderType.EQUIPPED || type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON;
	}

	public boolean shouldUseRenderHelper(final IItemRenderer.ItemRenderType type, final ItemStack item, final IItemRenderer.ItemRendererHelper helper) {
		return false;
	}

	public void renderItem(final IItemRenderer.ItemRenderType type, final ItemStack item, final Object... data) {
		switch (type) {
			case EQUIPPED_FIRST_PERSON: {
				renderEquippedItem(item, (EntityLivingBase)data[1], true);
				break;
			}
			case EQUIPPED: {
				renderEquippedItem(item, (EntityLivingBase)data[1], false);
				break;
			}
		}
	}

	private void renderEquippedItem(final ItemStack stack, final EntityLivingBase entity, final boolean firstPerson) {
		GL11.glPushMatrix();
		float f = scale;
		if (firstPerson) {
			f *= 1.75f;
			GL11.glTranslatef(-0.6f, -0.3f * scale, 0.0f);
			GL11.glScalef(f, f, f);
			GL11.glRotatef(15.0f, 0.0f, 0.0f, 0.3f);
		}
		else {
			f *= ((entity instanceof EntityPlayer) ? 2.0f : 1.75f);
			GL11.glTranslatef(3.3f - f, -0.125f * scale, 0.05f * scale);
			GL11.glScalef(f, f, f);
			GL11.glRotatef(90.0f, 0.0f, 0.0f, 1.0f);
		}
		final IIcon icon = stack.getItem().getIcon(stack, 0);
		final Tessellator tessellator = Tessellator.instance;
		ItemRenderer.renderItemIn2D(tessellator, icon.getMaxU(), icon.getMinV(), icon.getMinU(), icon.getMaxV(), icon.getIconWidth(), icon.getIconHeight(), 0.0625f);
		GL11.glPopMatrix();
	}
}
