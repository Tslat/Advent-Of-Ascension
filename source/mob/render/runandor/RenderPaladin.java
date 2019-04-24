package net.nevermine.mob.render.runandor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.runandor.modelPaladin;

@SideOnly(Side.CLIENT)
public class RenderPaladin extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelPaladin model;

	public RenderPaladin(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelPaladin)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderPaladin.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/paladin.png");
	}
}
