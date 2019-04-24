package net.nevermine.mob.render.deeplands;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.deeplands.modelCaseConstruct;

@SideOnly(Side.CLIENT)
public class RenderCaseConstruct extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelCaseConstruct model;

	public RenderCaseConstruct(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelCaseConstruct)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderCaseConstruct.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/caseConstruct.png");
	}
}
