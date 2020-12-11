package net.tslat.aoa3.client.render.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.tslat.aoa3.block.tileentity.TrophyTileEntity;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.library.misc.CustomisableParticleType;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.RandomUtil;

public class TrophyRenderer extends TileEntityRenderer<TrophyTileEntity> {
	public TrophyRenderer(TileEntityRendererDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	public void render(TrophyTileEntity tileEntity, float partialTicks, MatrixStack matrix, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
		Entity entity = tileEntity.getCachedEntity();

		if (entity != null) {
			matrix.push();
			matrix.translate(0.5f, 0, 0.5f);

			float scale = 0.53125F;
			float maxScale = Math.max(entity.getWidth(), entity.getHeight());
			double hover = (MathHelper.sin((float)tileEntity.hoverStep + partialTicks) / 30d);
			tileEntity.hoverStep += hover * 0.01f;

			if (maxScale > 1.0D)
				scale /= maxScale;

			matrix.translate(0, -0.1, 0);
			matrix.scale(scale, scale, scale);
			matrix.translate(0, (1 / scale), 0);

			if (tileEntity.isOriginal() && partialTicks > 0.95f)
				entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.FLICKERING_SPARKLER.get(), 0.005f, 10, NumberUtil.RGB(255, 200, 0)), tileEntity.getPos().getX() + 0.5f, tileEntity.getPos().getY() + 0.9 + ((entity.getHeight() / 2f) * scale), tileEntity.getPos().getZ() + 0.5f, RandomUtil.randomGaussianValue() * 0.5f, RandomUtil.randomGaussianValue() * 0.5f, RandomUtil.randomGaussianValue() * 0.5f);

			if (AoAConfig.CLIENT.rotatingTrophies.get())
				matrix.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, tileEntity.getPrevMobRotation(), tileEntity.getMobRotation()) * 30.0F));

			Minecraft.getInstance().getRenderManager().renderEntityStatic(entity, 0, 0, 0, 0, 0, matrix, buffer, combinedLight);
			matrix.pop();
		}
	}
}
