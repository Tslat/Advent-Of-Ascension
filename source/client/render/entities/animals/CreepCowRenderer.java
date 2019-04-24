package net.tslat.aoa3.client.render.entities.animals;

import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.entity.animals.EntityCreepCow;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class CreepCowRenderer extends RenderLiving<EntityCreepCow> {
	private final ResourceLocation texture;

	public CreepCowRenderer(RenderManager renderManager, final ResourceLocation resource) {
		super(renderManager, new ModelCow(), EntityCreepCow.entityWidth / 3);
		texture = resource;
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityCreepCow entity) {
		return texture;
	}
}