package net.tslat.aoa3.client.render.entities.animals;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.entity.base.AoAAnimal;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class AoAAnimalRenderer extends RenderLiving<AoAAnimal> {
	private final ResourceLocation texture;

	public AoAAnimalRenderer(RenderManager renderManager, ModelBase model, float entityWidth, final ResourceLocation resource) {
		super(renderManager, model, entityWidth / 3);
		texture = resource;
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(AoAAnimal entity) {
		return texture;
	}
}