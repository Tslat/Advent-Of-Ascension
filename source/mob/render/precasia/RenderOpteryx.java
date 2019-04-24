package net.nevermine.mob.render.precasia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.precasia.modelOpteryx;

@SideOnly(Side.CLIENT)
public class RenderOpteryx extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelOpteryx model;

	public RenderOpteryx(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelOpteryx)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderOpteryx.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/opteryx.png");
	}
}
