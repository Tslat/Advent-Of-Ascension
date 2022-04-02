/*
package net.tslat.aoa3.client.render.entity.mob;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.entity.Mob;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.client.model.entity.mob.abyss.OcculentModel;
import net.tslat.aoa3.client.render.entity.AoAMobRenderer;

import net.tslat.aoa3.content.entity.mob.abyss.OcculentEntity;
import net.tslat.aoa3.event.GlobalEvents;

import java.util.concurrent.CopyOnWriteArrayList;

public class OcculentRenderer extends AoAMobRenderer {
	public OcculentRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new OcculentModel(), AoAMobs.OCCULENT.get().getWidth() / 3f, 1f, new ResourceLocation("aoa3", "textures/entity/mob/abyss/occulent.png"));
	}

	@Override
	public void render(Mob entity, float entityYaw, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int packedLight) {
		super.render(entity, entityYaw, partialTicks, matrix, buffer, packedLight);

		CopyOnWriteArrayList<Pair<Integer, Vec3>> clones = ((OcculentEntity)entity).clones;
		BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

		if (!clones.isEmpty())
			clones.removeIf(clone -> clone.getFirst() <= GlobalEvents.tick);

		for (Pair<Integer, Vec3> clone : clones) {
			Vec3 cloneOffset = clone.getSecond();
			double lerpX = Mth.lerp(partialTicks, entity.xOld, entity.getX());
			double lerpZ = Mth.lerp(partialTicks, entity.zOld, entity.getZ());

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
}*/
