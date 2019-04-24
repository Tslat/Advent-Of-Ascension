package net.nevermine.mob.render.precasia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.precasia.modelIosaur;

@SideOnly(Side.CLIENT)
public class RenderIosaur extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelIosaur model;

	public RenderIosaur(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelIosaur)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderIosaur.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/iosaur.png");
	}
}
