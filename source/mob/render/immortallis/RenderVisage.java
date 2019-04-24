package net.nevermine.mob.render.immortallis;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.immortallis.modelVisage;

@SideOnly(Side.CLIENT)
public class RenderVisage extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelVisage model;

	public RenderVisage(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelVisage)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderVisage.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/visage.png");
	}
}
