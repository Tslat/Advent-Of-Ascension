package net.nevermine.npc.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.nevermine.npc.model.modelBipedClear;

@SideOnly(Side.CLIENT)
public class RenderCorruptedTraveller extends RenderLiving {
	private static final ResourceLocation EntityTexture = new ResourceLocation("nevermine:textures/mobs/corruptedTraveller.png");
	protected modelBipedClear model;

	public RenderCorruptedTraveller(final ModelBase base, final float ren) {
		super(base, ren);
		model = (modelBipedClear)mainModel;
	}

	protected ResourceLocation getEntityTexture(final EntityLiving par1EntityLiving) {
		return RenderCorruptedTraveller.EntityTexture;
	}

	protected ResourceLocation getEntityTexture(final Entity par1Entity) {
		return RenderCorruptedTraveller.EntityTexture;
	}
}
