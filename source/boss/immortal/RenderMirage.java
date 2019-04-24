package net.nevermine.boss.immortal;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderMirage extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelMirage model;

	public RenderMirage(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelMirage)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderMirage.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/immortalWizard.png");
	}
}
