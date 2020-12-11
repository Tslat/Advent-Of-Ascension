package net.tslat.aoa3.client.render.entity.projectile.cannonshots;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.model.entity.projectile.CobblestoneProjectileModel;
import net.tslat.aoa3.client.render.entity.projectile.ModelledProjectileRenderer;
import net.tslat.aoa3.entity.projectile.cannon.RockFragmentEntity;

public class RockFragmentRenderer extends ModelledProjectileRenderer<RockFragmentEntity> {
	public RockFragmentRenderer(final EntityRendererManager manager, final ResourceLocation textureResource) {
		super(manager, new CobblestoneProjectileModel(), textureResource);
	}
}
