package net.nevermine.mob.render.immortallis;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.immortallis.modelSkeledon;

@SideOnly(Side.CLIENT)
public class RenderSkeledon extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelSkeledon model;

	public RenderSkeledon(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelSkeledon)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderSkeledon.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/skeledon.png");
	}
}
