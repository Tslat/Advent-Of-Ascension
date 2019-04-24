package net.nevermine.mob.render.abyss;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.abyss.modelJawe;

@SideOnly(Side.CLIENT)
public class RenderJawe extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelJawe model;

	public RenderJawe(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelJawe)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderJawe.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/jawe.png");
	}
}
