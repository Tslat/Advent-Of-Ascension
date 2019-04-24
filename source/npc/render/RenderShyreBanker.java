package net.nevermine.npc.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderShyreBanker extends RenderBiped {
	private static final ResourceLocation EntityTexture = new ResourceLocation("nevermine:textures/mobs/shyreBanker.png");

	public RenderShyreBanker() {
		super(new ModelBiped(), 0.5F);
	}

	protected ResourceLocation getEntityTexture(EntityLiving par1EntityLiving) {
		return EntityTexture;
	}

	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return EntityTexture;
	}
}
