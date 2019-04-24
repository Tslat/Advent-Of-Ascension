package net.nevermine.mob.render.overworld;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.overworld.modelBombCarrier;

@SideOnly(Side.CLIENT)
public class RenderBombCarrier extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelBombCarrier model;

	public RenderBombCarrier(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelBombCarrier)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderBombCarrier.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/bombcarrier.png");
	}
}
