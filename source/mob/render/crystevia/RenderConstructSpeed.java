package net.nevermine.mob.render.crystevia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.crystevia.modelConstructSpeed;

@SideOnly(Side.CLIENT)
public class RenderConstructSpeed extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelConstructSpeed model;

	public RenderConstructSpeed(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelConstructSpeed)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderConstructSpeed.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/constructSpeed.png");
	}
}
