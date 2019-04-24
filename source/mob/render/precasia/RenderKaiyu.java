package net.nevermine.mob.render.precasia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.precasia.modelKaiyu;

@SideOnly(Side.CLIENT)
public class RenderKaiyu extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelKaiyu model;

	public RenderKaiyu(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelKaiyu)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderKaiyu.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/kaiyu.png");
	}
}
