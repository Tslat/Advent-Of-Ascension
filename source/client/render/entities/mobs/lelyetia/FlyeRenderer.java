package net.tslat.aoa3.client.render.entities.mobs.lelyetia;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.model.entities.mobs.lelyetia.ModelFlye;
import net.tslat.aoa3.entity.mobs.lelyetia.EntityFlye;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class FlyeRenderer extends RenderLiving<EntityFlye> {
	private final ResourceLocation texture;

	public FlyeRenderer(RenderManager renderManager, ResourceLocation textureResource) {
		super(renderManager, new ModelFlye(), EntityFlye.entityWidth / 3);

		this.texture = textureResource;
	}

	@Override
	protected int getTeamColor(EntityFlye flye) {
		if (flye.getGrawAltarPos() != null)
			return Enums.RGBIntegers.GREEN;

		return super.getTeamColor(flye);
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityFlye entity) {
		return texture;
	}
}