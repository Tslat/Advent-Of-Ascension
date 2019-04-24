package net.nevermine.mob.render.overworld;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.overworld.ghostly.modelGhostlyBugeye;

@SideOnly(Side.CLIENT)
public class RenderGhostlyBugeye extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelGhostlyBugeye model;

	public RenderGhostlyBugeye(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelGhostlyBugeye)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderGhostlyBugeye.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/bugeyeGhostly.png");
	}
}
