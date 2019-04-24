package net.tslat.aoa3.client.render.entities.mobs.precasia;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.model.entities.mobs.precasia.ModelTerradon;
import net.tslat.aoa3.entity.mobs.precasia.EntityTerradon;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class TerradonRenderer extends RenderLiving<EntityTerradon> {
	private final ResourceLocation texture;
	private final ResourceLocation invulnTexture;

	public TerradonRenderer(RenderManager renderManager, final ResourceLocation texture, final ResourceLocation invulnTexture) {
		super(renderManager, new ModelTerradon(), EntityTerradon.entityWidth / 3);
		this.texture = texture;
		this.invulnTexture = invulnTexture;
	}

	@Override
	protected void preRenderCallback(EntityTerradon entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(2.0f, 2.0f, 2.0f);
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityTerradon terradon) {
		return terradon.getIsInvulnerable() ? invulnTexture : texture;
	}
}