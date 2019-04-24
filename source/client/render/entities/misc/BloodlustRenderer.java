package net.tslat.aoa3.client.render.entities.misc;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.fx.FXSwirlyTrail;
import net.tslat.aoa3.client.model.entities.misc.ModelBloodlust;
import net.tslat.aoa3.entity.misc.EntityBloodlust;
import net.tslat.aoa3.library.Enums;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class BloodlustRenderer extends RenderLiving<EntityBloodlust> {
	private final ResourceLocation texture;

	public BloodlustRenderer(RenderManager renderManager, final ResourceLocation resource) {
		super(renderManager, new ModelBloodlust(), EntityBloodlust.entityWidth / 3);
		texture = resource;
	}

	@Override
	public void doRender(EntityBloodlust entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);

		new FXSwirlyTrail(entity.world, entity.posX, entity.posY + 0.5, entity.posZ, 0, 0, 0, Enums.RGBIntegers.RED, 5).create();
	}

	@Override
	protected void preRenderCallback(EntityBloodlust entitylivingbaseIn, float partialTickTime) {
		GL11.glScaled(0.5f, 0.5f, 0.5f);
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityBloodlust entity) {
		return texture;
	}
}