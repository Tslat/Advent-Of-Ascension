package net.tslat.aoa3.client.render.entities.projectiles.staff;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.fx.FXFlickeringFluffyTrail;
import net.tslat.aoa3.client.fx.FXSwirlyTrail;
import net.tslat.aoa3.entity.projectiles.staff.EntityRosidianShot;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RosidianShotRenderer extends Render<EntityRosidianShot> {
	private final ResourceLocation texture;

	public RosidianShotRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityRosidianShot entity, double x, double y, double z, float entityYaw, float partialTicks) {
		if (entity.motionY > 0.98) {
			for (int i = 0; i < 8; i++) {
				new FXSwirlyTrail(entity.world, entity.posX, entity.posY + 0.25d, entity.posZ, 0, 0, 0, Enums.RGBIntegers.BROWN, 3, 1).create();
			}
		}
		else {
			new FXFlickeringFluffyTrail(entity.world, entity.posX, entity.posY + 0.25d, entity.posZ, 0, 0, 0, Enums.RGBIntegers.GREEN, 3, 1).create();
		}
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityRosidianShot entity) {
		return texture;
	}
}
