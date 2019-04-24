package net.nevermine.minion.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.minion.model.modelGnawer;

@SideOnly(Side.CLIENT)
public class RenderGnawer extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelGnawer model;

	public RenderGnawer(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelGnawer)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderGnawer.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/gnawer.png");
	}
}
