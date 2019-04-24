package net.nevermine.boss.hiveking;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.minion.model.modelHiveSoldier;

@SideOnly(Side.CLIENT)
public class RenderHiveSoldier extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelHiveSoldier model;

	public RenderHiveSoldier(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelHiveSoldier)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderHiveSoldier.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/hivesoldier.png");
	}
}
