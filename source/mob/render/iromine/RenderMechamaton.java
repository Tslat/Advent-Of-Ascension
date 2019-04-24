package net.nevermine.mob.render.iromine;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.iromine.modelMechamaton;

@SideOnly(Side.CLIENT)
public class RenderMechamaton extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelMechamaton model;

	public RenderMechamaton(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelMechamaton)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderMechamaton.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/mechamaton.png");
	}
}
