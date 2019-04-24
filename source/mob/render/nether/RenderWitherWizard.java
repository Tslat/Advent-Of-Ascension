package net.nevermine.mob.render.nether;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.nether.modelWitherWizard;

@SideOnly(Side.CLIENT)
public class RenderWitherWizard extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelWitherWizard model;

	public RenderWitherWizard(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelWitherWizard)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderWitherWizard.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/witherwizard.png");
	}
}
