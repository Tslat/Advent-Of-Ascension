package net.nevermine.mob.render.runandor.templar;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.runandor.modelRuneTemplar;

@SideOnly(Side.CLIENT)
public class RenderRuneTemplarGreenDisabled extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelRuneTemplar model;

	public RenderRuneTemplarGreenDisabled(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelRuneTemplar)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderRuneTemplarGreenDisabled.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/runeTemplarGreenDisabled.png");
	}
}
