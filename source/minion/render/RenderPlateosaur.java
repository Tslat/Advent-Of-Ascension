package net.nevermine.minion.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.minion.model.modelPlateosaur;

@SideOnly(Side.CLIENT)
public class RenderPlateosaur extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelPlateosaur model;

	public RenderPlateosaur(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelPlateosaur)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderPlateosaur.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/plateosaur.png");
	}
}
