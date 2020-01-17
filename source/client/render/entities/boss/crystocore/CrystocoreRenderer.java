package net.tslat.aoa3.client.render.entities.boss.crystocore;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.gui.render.BossBarRenderer;
import net.tslat.aoa3.client.model.entities.boss.crystocore.ModelCrystocore;
import net.tslat.aoa3.entity.boss.crystocore.EntityCrystocore;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class CrystocoreRenderer extends RenderLiving<EntityCrystocore> {
	private final ResourceLocation poisonTexture;
	private final ResourceLocation blindnessTexture;
	private final ResourceLocation weaknessTexture;
	private final ResourceLocation nauseaTexture;
	private final ResourceLocation witherTexture;
	private final ResourceLocation slownessTexture;

	public CrystocoreRenderer(RenderManager renderManager, ResourceLocation poisonTexture, ResourceLocation blindnessTexture, ResourceLocation weaknessTexture, ResourceLocation nauseaTexture, ResourceLocation witherTexture, ResourceLocation slownessTexture) {
		super(renderManager, new ModelCrystocore(), EntityCrystocore.entityWidth / 3);
		this.poisonTexture = poisonTexture;
		this.blindnessTexture = blindnessTexture;
		this.weaknessTexture = weaknessTexture;
		this.nauseaTexture = nauseaTexture;
		this.witherTexture = witherTexture;
		this.slownessTexture = slownessTexture;
	}

	@Override
	protected void preRenderCallback(EntityCrystocore entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(2f, 2f, 2f);
	}

	@Override
	public void doRender(EntityCrystocore entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);

		if (x + y + z != 0)
			BossBarRenderer.boss = entity;
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityCrystocore crystocore) {
		switch (crystocore.getType()) {
			case 0:
				return poisonTexture;
			case 1:
				return blindnessTexture;
			case 2:
				return weaknessTexture;
			case 3:
				return nauseaTexture;
			case 4:
				return witherTexture;
			case 5:
			default:
				return slownessTexture;
		}
	}
}