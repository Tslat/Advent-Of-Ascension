package net.nevermine.mob.render.nether;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.entity.nether.EntitySkeletalCowman;

@SideOnly(Side.CLIENT)
public class RenderSkeletalCowman extends RenderBiped {
	private static final ResourceLocation EntityTexture;

	public RenderSkeletalCowman() {
		super(new ModelZombie(), 0.5f);
	}

	public void renderMob(final EntitySkeletalCowman var1, final double var2, final double var4, final double var6, final float var8, final float var9) {
		super.doRender(var1, var2, var4, var6, var8, var9);
	}

	public void doRender(final Entity var1, final double var2, final double var4, final double var6, final float var8, final float var9) {
		renderMob((EntitySkeletalCowman)var1, var2, var4, var6, var8, var9);
	}

	protected ResourceLocation getEntityTexture(final EntityLiving par1EntityLiving) {
		return RenderSkeletalCowman.EntityTexture;
	}

	protected ResourceLocation getEntityTexture(final Entity par1Entity) {
		return RenderSkeletalCowman.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/skeletalcowman.png");
	}
}
