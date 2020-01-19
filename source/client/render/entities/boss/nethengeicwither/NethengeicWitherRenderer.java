package net.tslat.aoa3.client.render.entities.boss.nethengeicwither;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.gui.render.BossBarRenderer;
import net.tslat.aoa3.client.model.entities.boss.nethengeicwither.ModelNethengeicWither;
import net.tslat.aoa3.entity.boss.nethengeicwither.EntityNethengeicWither;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class NethengeicWitherRenderer extends RenderLiving<EntityNethengeicWither> {
	private final ResourceLocation texture;
	private final ResourceLocation enragedTexture;
	private final ResourceLocation cataclysmicTexture;

	public NethengeicWitherRenderer(RenderManager renderManager, final ResourceLocation texture, final ResourceLocation enragedTexture, final ResourceLocation cataclysmicTexture) {
		super(renderManager, new ModelNethengeicWither(), EntityNethengeicWither.entityWidth / 3);
		this.texture = texture;
		this.enragedTexture = enragedTexture;
		this.cataclysmicTexture = cataclysmicTexture;
	}

	@Override
	public void doRender(EntityNethengeicWither entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);

		if (x + y + z != 0)
			BossBarRenderer.boss = entity;
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityNethengeicWither wither) {
		switch (wither.getStage()) {
			case 1:
			default:
				return texture;
			case 2:
				return enragedTexture;
			case 3:
				return cataclysmicTexture;
		}
	}
}