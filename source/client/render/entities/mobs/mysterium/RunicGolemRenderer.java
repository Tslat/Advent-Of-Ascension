package net.tslat.aoa3.client.render.entities.mobs.mysterium;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.model.entities.mobs.mysterium.ModelRunicGolem;
import net.tslat.aoa3.client.render.entities.layers.RenderLayerInvulnerabilityAura;
import net.tslat.aoa3.entity.mobs.mysterium.EntityRunicGolem;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RunicGolemRenderer extends RenderLiving<EntityRunicGolem> {
	private final ResourceLocation texture;
	private final ResourceLocation invulnTexture;

	public RunicGolemRenderer(RenderManager renderManager, final ResourceLocation texture, final ResourceLocation armourTexture) {
		super(renderManager, new ModelRunicGolem(), EntityRunicGolem.entityWidth / 3);
		this.texture = texture;
		this.invulnTexture = armourTexture;
		this.addLayer(new RenderLayerInvulnerabilityAura(this, armourTexture));
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityRunicGolem runicGolem) {
		return texture;
	}
}