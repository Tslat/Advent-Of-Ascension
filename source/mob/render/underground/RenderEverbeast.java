package net.nevermine.mob.render.underground;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.underground.modelEverbeast;

@SideOnly(Side.CLIENT)
public class RenderEverbeast extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelEverbeast model;

	public RenderEverbeast(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelEverbeast)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderEverbeast.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/everbeast.png");
	}
}
