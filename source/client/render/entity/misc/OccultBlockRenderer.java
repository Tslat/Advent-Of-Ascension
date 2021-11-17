package net.tslat.aoa3.client.render.entity.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector4f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.event.GlobalEvents;
import net.tslat.aoa3.util.RenderUtil;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.lwjgl.opengl.GL11.GL_LINES;

@Mod.EventBusSubscriber(modid = AdventOfAscension.MOD_ID, value = Dist.CLIENT)
public class OccultBlockRenderer {
	private static final ConcurrentHashMap<Integer, ArrayList<Pair<BlockPos, BlockState>>> occultBlockMap = new ConcurrentHashMap<Integer, ArrayList<Pair<BlockPos, BlockState>>>();

	public static void addOccultBlocks(int renderUntil, ArrayList<Pair<BlockPos, BlockState>> blocks) {
		occultBlockMap.put(renderUntil, blocks);
	}

	@SubscribeEvent
	public static void worldRender(final RenderWorldLastEvent ev) {
		if (occultBlockMap.isEmpty())
			return;

		ArrayList<Integer> expiredBlocks = null;

		for (Map.Entry<Integer, ArrayList<Pair<BlockPos, BlockState>>> entry : occultBlockMap.entrySet()) {
			if (GlobalEvents.tick >= entry.getKey()) {
				if (expiredBlocks == null)
					expiredBlocks = new ArrayList<Integer>();

				expiredBlocks.add(entry.getKey());
			}
			else {
				ArrayList<Pair<BlockPos, BlockState>> invalidBlocks = null;
				Minecraft mc = Minecraft.getInstance();
				MatrixStack matrix = ev.getMatrixStack();
				Vector3d cameraPos = mc.gameRenderer.getMainCamera().getPosition();
				Tessellator tess = Tessellator.getInstance();
				BufferBuilder buff = tess.getBuilder();

				matrix.pushPose();
				matrix.translate(-cameraPos.x(), -cameraPos.y(), -cameraPos.z());

				RenderSystem.pushMatrix();
				RenderSystem.multMatrix(matrix.last().pose());

				RenderSystem.disableTexture();
				RenderSystem.disableDepthTest();
				RenderSystem.depthMask(false);
				RenderSystem.polygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
				RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
				RenderSystem.enableBlend();
				RenderSystem.lineWidth(4f);

				for (Pair<BlockPos, BlockState> block : entry.getValue()) {
					if (GlobalEvents.tick % 2 == 1 && mc.player.level.getBlockState(block.getFirst()) != block.getSecond()) {
						if (invalidBlocks == null)
							invalidBlocks = new ArrayList<Pair<BlockPos, BlockState>>();

						invalidBlocks.add(block);

						continue;
					}

					Vector4f colour = getColourForBlock(block.getSecond());
					BlockPos pos = block.getFirst();

					RenderSystem.pushMatrix();
					RenderSystem.translated(pos.getX(), pos.getY(), pos.getZ());
					buff.begin(GL_LINES, DefaultVertexFormats.POSITION_COLOR);
					RenderUtil.renderBlockOutline(buff, colour.x(), colour.y(), colour.z(), colour.w());
					tess.end();
					RenderSystem.popMatrix();
				}

				RenderSystem.polygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
				RenderSystem.disableBlend();
				RenderSystem.enableDepthTest();
				RenderSystem.depthMask(true);
				RenderSystem.enableTexture();

				RenderSystem.popMatrix();
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
		switch (block.getBlock().getHarvestLevel(block)) {
			case 0:
				return new Vector4f(1, 0, 0, 0.5f);
			case 1:
				return new Vector4f(223 / 255f, 153 / 255f, 0, 0.5f);
			case 2:
				return new Vector4f(1, 1, 0, 0.5f);
			case 3:
				return new Vector4f(0, 147 / 255f, 66 / 255f, 0.5f);
			case 4:
			default:
				return new Vector4f(0, 153 / 255f, 0, 0.5f);
		}
	}
}
