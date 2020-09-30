package net.tslat.aoa3.client.render.entities.mobs.overworld;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.model.entities.mobs.overworld.ModelCharger;
import net.tslat.aoa3.client.render.entities.AoAMeleeMobRenderer;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.mobs.overworld.EntityCharger;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class ChargerRenderer extends AoAMeleeMobRenderer {
	private final ResourceLocation texture;

	public ChargerRenderer(RenderManager renderManager, final ResourceLocation resource) {
		super(renderManager, new ModelCharger(), EntityCharger.entityWidth, 1, resource);

		texture = ModelCharger.getChargerTexture((ModelCharger)mainModel, resource);
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(AoAMeleeMob mob) {
		return texture;
	}
}