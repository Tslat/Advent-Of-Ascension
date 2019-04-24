package net.nevermine.mob.render.dustopia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.dustopia.modelLurker;

@SideOnly(Side.CLIENT)
public class RenderLurker extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelLurker model;

	public RenderLurker(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelLurker)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderLurker.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/lurker.png");
	}
}
