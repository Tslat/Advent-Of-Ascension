package net.nevermine.boss.bane;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderBaneClone extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelBane model;

	public RenderBaneClone(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelBane)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderBaneClone.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/bane.png");
	}
}
