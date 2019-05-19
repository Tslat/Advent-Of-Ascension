package net.tslat.aoa3.client.render.entities.boss.baroness;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.gui.render.BossBarRenderer;
import net.tslat.aoa3.client.model.entities.boss.baroness.ModelBaroness;
import net.tslat.aoa3.client.render.entities.layers.RenderLayerInvulnerabilityAura;
import net.tslat.aoa3.entity.boss.baroness.EntityBaroness;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class BaronessRenderer extends RenderLiving<EntityBaroness> {
	private final ResourceLocation texture;
	private final ResourceLocation invulnTexture;

	public BaronessRenderer(RenderManager renderManager, final ResourceLocation texture, final ResourceLocation invulnerableTexture, final ResourceLocation armourTexture) {
		super(renderManager, new ModelBaroness(), EntityBaroness.entityWidth / 3);
		this.texture = texture;
		this.invulnTexture = invulnerableTexture;
		this.addLayer(new RenderLayerInvulnerabilityAura(this, armourTexture));
	}

	@Override
	public void doRender(EntityBaroness entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		if (x + y + z != 0)
			BossBarRenderer.boss = entity;

		if (partialTicks < 0.3)
			entity.checkMusicStatus();
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityBaroness baroness) {
		return baroness.getIsInvulnerable() ? invulnTexture : texture;
	}
}