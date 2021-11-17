package net.tslat.aoa3.client.render.entity.mob;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.tslat.aoa3.client.model.entity.mob.abyss.OcculentModel;
import net.tslat.aoa3.client.render.entity.AoAMobRenderer;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.mob.abyss.OcculentEntity;
import net.tslat.aoa3.event.GlobalEvents;

import java.util.concurrent.CopyOnWriteArrayList;

public class OcculentRenderer extends AoAMobRenderer {
	public OcculentRenderer(EntityRendererManager renderManager) {
		super(renderManager, new OcculentModel(), AoAEntities.Mobs.OCCULENT.get().getWidth() / 3f, 1f, new ResourceLocation("aoa3", "textures/entity/mobs/abyss/occulent.png"));
	}

	@Override
	public void render(MobEntity entity, float entityYaw, float partialTicks, MatrixStack matrix, IRenderTypeBuffer buffer, int packedLight) {
		super.render(entity, entityYaw, partialTicks, matrix, buffer, packedLight);

		CopyOnWriteArrayList<Pair<Integer, Vector3d>> clones = ((OcculentEntity)entity).clones;
		BlockPos.Mutable mutablePos = new BlockPos.Mutable();

		if (!clones.isEmpty())
			clones.removeIf(clone -> clone.getFirst() <= GlobalEvents.tick);

		for (Pair<Integer, Vector3d> clone : clones) {
			Vector3d cloneOffset = clone.getSecond();
			double lerpX = MathHelper.lerp(partialTicks, entity.xOld, entity.getX());
			double lerpZ = MathHelper.lerp(partialTicks, entity.zOld, entity.getZ());

			if (!entity.level.getBlockState(mutablePos.set(lerpX + cloneOffset.x(), entity.blockPosition().getY() - 1, lerpZ + cloneOffset.z())).entityCanStandOn(entity.level, mutablePos, entity))
				continue;

			if (entity.level.getBlockState(mutablePos.move(Direction.UP)).getMaterial().blocksMotion())
				continue;

			matrix.pushPose();
			matrix.translate(cloneOffset.x(), 0, cloneOffset.z());

			super.render(entity, entityYaw, partialTicks, matrix, buffer, packedLight);

			matrix.popPose();
		}
	}
}