package net.nevermine.mob.render.voxponds;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.voxponds.modelGadgetoid;

@SideOnly(Side.CLIENT)
public class RenderGadgetoid extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelGadgetoid model;

	public RenderGadgetoid(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelGadgetoid)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderGadgetoid.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/gadgetoid.png");
	}
}
