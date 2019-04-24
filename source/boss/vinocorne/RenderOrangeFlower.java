package net.nevermine.boss.vinocorne;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.gardencia.modelFlowerface;

@SideOnly(Side.CLIENT)
public class RenderOrangeFlower extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelFlowerface model;

	public RenderOrangeFlower(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelFlowerface)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderOrangeFlower.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/vineMinionOrange.png");
	}
}
