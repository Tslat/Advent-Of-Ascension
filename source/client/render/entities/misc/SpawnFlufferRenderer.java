package net.tslat.aoa3.client.render.entities.misc;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.model.entities.misc.ModelNothing;
import net.tslat.aoa3.entity.misc.EntitySpawnFluffer;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class SpawnFlufferRenderer extends RenderLiving<EntitySpawnFluffer> {
	private final ResourceLocation texture;

	public SpawnFlufferRenderer(RenderManager renderManager, final ResourceLocation resource) {
		super(renderManager, new ModelNothing(), 0);
		texture = resource;
	}

	@Override
	public void doRender(EntitySpawnFluffer entity, double x, double y, double z, float entityYaw, float partialTicks) {}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntitySpawnFluffer entity) {
		return texture;
	}
}