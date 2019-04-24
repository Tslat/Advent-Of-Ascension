package net.nevermine.mob.render.crystevia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.crystevia.modelConstructResistance;

@SideOnly(Side.CLIENT)
public class RenderConstructResistance extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelConstructResistance model;

	public RenderConstructResistance(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelConstructResistance)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderConstructResistance.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/constructResistance.png");
	}
}
