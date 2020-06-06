package net.tslat.aoa3.client.render.entities.mobs.overworld;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.fx.FXFlickeringFluffyTrail;
import net.tslat.aoa3.client.model.entities.mobs.overworld.ModelBloodmist;
import net.tslat.aoa3.client.render.entities.AoAMeleeMobRenderer;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.mobs.overworld.bloodhunt.EntityBloodmist;
import net.tslat.aoa3.library.Enums;

@SideOnly(Side.CLIENT)
public class BloodmistRenderer extends AoAMeleeMobRenderer {
	public BloodmistRenderer(RenderManager renderManager, final ResourceLocation textureResource) {
		super(renderManager, new ModelBloodmist(), EntityBloodmist.entityWidth, 1.0f, textureResource);
	}

	@Override
	public void doRender(AoAMeleeMob entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);

		new FXFlickeringFluffyTrail(entity.world, entity.posX, entity.posY - 0.2, entity.posZ, 0, 0, 0, Enums.RGBIntegers.RED, 5, 0.75f).create();
		new FXFlickeringFluffyTrail(entity.world, entity.posX, entity.posY + 0.2, entity.posZ, 0, 0, 0, Enums.RGBIntegers.RED, 5, 0.75f).create();
	}
}