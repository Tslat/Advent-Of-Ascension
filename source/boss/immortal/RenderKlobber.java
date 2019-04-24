package net.nevermine.boss.immortal;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderKlobber extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelKlobber model;

	public RenderKlobber(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelKlobber)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderKlobber.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/immortalWarrior.png");
	}
}
