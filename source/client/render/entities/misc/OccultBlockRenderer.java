package net.tslat.aoa3.client.render.entities.misc;

import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class OccultBlockRenderer extends RenderEntity {
	private final ResourceLocation texture;

	public OccultBlockRenderer(RenderManager renderManager, ResourceLocation texture) {
		super(renderManager);

		this.texture = texture;
	}

	@Nullable
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}
