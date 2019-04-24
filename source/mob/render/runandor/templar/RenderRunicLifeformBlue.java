package net.nevermine.mob.render.runandor.templar;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.runandor.modelRunicLifeform;

@SideOnly(Side.CLIENT)
public class RenderRunicLifeformBlue extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelRunicLifeform model;

	public RenderRunicLifeformBlue(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelRunicLifeform)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderRunicLifeformBlue.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/runicLifeformBlue.png");
	}
}
