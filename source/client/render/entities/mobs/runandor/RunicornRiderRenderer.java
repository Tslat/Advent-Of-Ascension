package net.tslat.aoa3.client.render.entities.mobs.runandor;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.fx.FXFluffyTrail;
import net.tslat.aoa3.client.model.entities.mobs.runandor.ModelRunicornRider;
import net.tslat.aoa3.entity.mobs.runandor.EntityRunicornRider;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RunicornRiderRenderer extends RenderLiving<EntityRunicornRider> {
	private final ResourceLocation texture;

	public RunicornRiderRenderer(RenderManager renderManager, ResourceLocation textureResource) {
		super(renderManager, new ModelRunicornRider(), EntityRunicornRider.entityWidth / 3);

		this.texture = textureResource;
	}

	@Override
	public void doRender(EntityRunicornRider entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);

		if (entity.getHealth() > 0 && !entity.isDead && (entity.motionX > 0 || entity.motionZ > 0 || entity.motionY > 0)) {
			new FXFluffyTrail(entity.world, entity.posX, entity.posY + 1.5, entity.posZ, 0, 0, 0, Enums.RGBIntegers.CYAN, 5, 0.75f).create();
			new FXFluffyTrail(entity.world, entity.posX, entity.posY + 1.25, entity.posZ, 0, 0, 0, Enums.RGBIntegers.WHITE, 5, 0.75f).create();
			new FXFluffyTrail(entity.world, entity.posX, entity.posY + 1.05, entity.posZ, 0, 0, 0, Enums.RGBIntegers.BLUE, 5, 0.75f).create();
			new FXFluffyTrail(entity.world, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.WHITE, 5, 0.75f).create();
			new FXFluffyTrail(entity.world, entity.posX, entity.posY + 0.95, entity.posZ, 0, 0, 0, Enums.RGBIntegers.BLUE, 5, 0.75f).create();
			new FXFluffyTrail(entity.world, entity.posX, entity.posY + 0.75, entity.posZ, 0, 0, 0, Enums.RGBIntegers.WHITE, 5, 0.75f).create();
			new FXFluffyTrail(entity.world, entity.posX, entity.posY + 0.5, entity.posZ, 0, 0, 0, Enums.RGBIntegers.CYAN, 5, 0.75f).create();
		}
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityRunicornRider entity) {
		return texture;
	}
}