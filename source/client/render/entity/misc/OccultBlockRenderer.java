package net.tslat.aoa3.client.render.entity.misc;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector4f;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraftforge.client.event.RenderLevelLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.eventbus.api.EventPriority;
import net.tslat.aoa3.event.GlobalEvents;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class OccultBlockRenderer {
	private static final ConcurrentHashMap<Integer, ArrayList<Pair<BlockPos, BlockState>>> occultBlockMap = new ConcurrentHashMap<>();

	public static void init() {
		MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, RenderLevelLastEvent.class, OccultBlockRenderer::onWorldRender);
	}

	public static void addOccultBlocks(int renderUntil, ArrayList<Pair<BlockPos, BlockState>> blocks) {
		occultBlockMap.put(renderUntil, blocks);
	}

	private static void onWorldRender(final RenderLevelLastEvent ev) {
		if (occultBlockMap.isEmpty())
			return;

		ArrayList<Integer> expiredBlocks = null;

		for (Map.Entry<Integer, ArrayList<Pair<BlockPos, BlockState>>> entry : occultBlockMap.entrySet()) {
			if (GlobalEvents.tick >= entry.getKey()) {
				if (expiredBlocks == null)
					expiredBlocks = new ArrayList<>();

				expiredBlocks.add(entry.getKey());
			}
			else {
				ArrayList<Pair<BlockPos, BlockState>> invalidBlocks = null;
				Minecraft mc = Minecraft.getInstance();
				PoseStack matrix = ev.getPoseStack();
				PoseStack cleanMatrix = RenderSystem.getModelViewStack();
				Vec3 cameraPos = mc.gameRenderer.getMainCamera().getPosition();
				Tesselator tess = Tesselator.getInstance();
				BufferBuilder buff = tess.getBuilder();

				matrix.pushPose();
				matrix.translate(-cameraPos.x(), -cameraPos.y(), -cameraPos.z());

				cleanMatrix.pushPose();
				cleanMatrix.mulPoseMatrix(matrix.last().pose());

				RenderSystem.disableTexture();
				RenderSystem.disableDepthTest();
				RenderSystem.depthMask(false);
				RenderSystem.polygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
				RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
				RenderSystem.enableBlend();

				for (Pair<BlockPos, BlockState> block : entry.getValue()) {
					if (GlobalEvents.tick % 2 == 1 && mc.player.level.getBlockState(block.getFirst()) != block.getSecond()) {
						if (invalidBlocks == null)
							invalidBlocks = new ArrayList<>();

						invalidBlocks.add(block);

						continue;
					}

					Vector4f colour = getColourForBlock(block.getSecond());
					BlockPos pos = block.getFirst();

					cleanMatrix.pushPose();
					cleanMatrix.translate(pos.getX(), pos.getY(), pos.getZ());
					buff.begin(VertexFormat.Mode.DEBUG_LINES, DefaultVertexFormat.POSITION_COLOR);

					Matrix4f cleanPose = cleanMatrix.last().pose();
					float red = colour.x();
					float green = colour.y();
					float blue = colour.z();
					float alpha = colour.w();

					block.getSecond().getShape(mc.level, block.getFirst(), CollisionContext.of(mc.player)).forAllEdges(((pMinX, pMinY, pMinZ, pMaxX, pMaxY, pMaxZ) -> {
						float xLength = (float)(pMaxX - pMinX);
						float yLength = (float)(pMaxY - pMinY);
						float zLength = (float)(pMaxZ - pMinZ);
						float avgLength = Mth.sqrt(xLength * xLength + yLength * yLength + zLength * zLength);
						xLength /= avgLength;
						yLength /= avgLength;
						zLength /= avgLength;

						buff.vertex(cleanPose, (float)pMinX, (float)pMinY, (float)pMinZ).color(red, green, blue, alpha).normal(cleanMatrix.last().normal(), xLength, yLength, zLength).endVertex();
						buff.vertex(cleanPose, (float)pMaxX, (float)pMaxY, (float)pMaxZ).color(red, green, blue, alpha).normal(cleanMatrix.last().normal(), xLength, yLength, zLength).endVertex();
					}));

					tess.end();
					cleanMatrix.popPose();
				}

				RenderSystem.polygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
				RenderSystem.disableBlend();
				RenderSystem.enableDepthTest();
				RenderSystem.depthMask(true);
				RenderSystem.enableTexture();

				cleanMatrix.popPose();
				matrix.popPose();

				if (invalidBlocks != null)
					entry.getValue().removeAll(invalidBlocks);
			}
		}

		if (expiredBlocks != null) {
			for (Integer tick : expiredBlocks) {
				occultBlockMap.remove(tick);
			}
		}
	}

	private static Vector4f getColourForBlock(BlockState block) {
		List<Tier> tiers = TierSortingRegistry.getSortedTiers();
		float tierCount = tiers.size();
		Vector4f fallback = new Vector4f(0, 1, 0, 1);

		for (int i = 0; i < tierCount; i++) {
			Tier tier = tiers.get(i);

			if (TierSortingRegistry.isCorrectTierForDrops(tier, block))
				return new Vector4f(1 - (i / tierCount), i / tierCount, 0, 1);
		}

		return fallback;
	}
}
