package net.nevermine.npc.render.artist;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderAnimaMaster extends RenderBiped {
	private static final ResourceLocation EntityTexture;

	public RenderAnimaMaster() {
		super(new ModelBiped(), 0.5f);
	}

	protected ResourceLocation getEntityTexture(final EntityLiving par1EntityLiving) {
		return RenderAnimaMaster.EntityTexture;
	}

	protected ResourceLocation getEntityTexture(final Entity par1Entity) {
		return RenderAnimaMaster.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/masterAnima.png");
	}
}
