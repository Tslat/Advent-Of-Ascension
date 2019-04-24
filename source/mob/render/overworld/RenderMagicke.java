package net.nevermine.mob.render.overworld;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.overworld.modelMagicke;

@SideOnly(Side.CLIENT)
public class RenderMagicke extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelMagicke model;

	public RenderMagicke(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelMagicke)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderMagicke.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/magicke.png");
	}
}
