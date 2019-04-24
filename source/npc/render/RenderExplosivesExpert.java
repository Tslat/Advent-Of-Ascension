package net.nevermine.npc.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderExplosivesExpert extends RenderBiped {
	private static final ResourceLocation EntityTexture;

	public RenderExplosivesExpert() {
		super(new ModelBiped(), 0.5f);
	}

	protected ResourceLocation getEntityTexture(final EntityLiving par1EntityLiving) {
		return RenderExplosivesExpert.EntityTexture;
	}

	protected ResourceLocation getEntityTexture(final Entity par1Entity) {
		return RenderExplosivesExpert.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/explosivesExpert.png");
	}
}
