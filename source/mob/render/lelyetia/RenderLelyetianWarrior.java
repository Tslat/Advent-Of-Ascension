package net.nevermine.mob.render.lelyetia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.lelyetia.modelLelyetianWarrior;

@SideOnly(Side.CLIENT)
public class RenderLelyetianWarrior extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelLelyetianWarrior model;

	public RenderLelyetianWarrior(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelLelyetianWarrior)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderLelyetianWarrior.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/lelyetianWarrior.png");
	}
}
