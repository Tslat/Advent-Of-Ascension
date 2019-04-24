package net.nevermine.mob.render.haven;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.haven.modelSceptron;

@SideOnly(Side.CLIENT)
public class RenderSceptron extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelSceptron model;

	public RenderSceptron(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelSceptron)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderSceptron.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/sceptron.png");
	}
}
