package net.nevermine.mob.render.runandor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.runandor.modelEyeCreature;

@SideOnly(Side.CLIENT)
public class RenderEyeCreature extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelEyeCreature model;

	public RenderEyeCreature(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelEyeCreature)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderEyeCreature.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/eyecreature.png");
	}
}
