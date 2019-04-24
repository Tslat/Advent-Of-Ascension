package net.nevermine.mob.render.immortallis;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.immortallis.modelFenix;

@SideOnly(Side.CLIENT)
public class RenderFenix extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelFenix model;

	public RenderFenix(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelFenix)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderFenix.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/fenix.png");
	}
}
