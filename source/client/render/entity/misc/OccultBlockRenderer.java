package net.tslat.aoa3.client.render.entity.misc;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import it.unimi.dsi.fastutil.ints.IntObjectPair;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.TierSortingRegistry;
import net.tslat.aoa3.event.GlobalEvents;
import net.tslat.aoa3.util.ColourUtil;
import org.lwjgl.opengl.GL11;

import java.util.Iterator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.concurrent.CopyOnWriteArrayList;

public final class OccultBlockRenderer {
	private static final RenderType CUSTOM_LINES_RENDER_TYPE = RenderType.create(
			"occult_block_lines", DefaultVertexFormat.POSITION_COLOR_NORMAL, VertexFormat.Mode.LINES, 256, true, false, RenderType.CompositeState.builder()
					.setShaderState(RenderStateShard.RENDERTYPE_LINES_SHADER)
					.setLineState(new RenderStateShard.LineStateShard(OptionalDouble.of(5)))
					.setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
					.setOutputState(RenderStateShard.OUTLINE_TARGET)
					.setWriteMaskState(RenderStateShard.COLOR_WRITE)
					.setCullState(RenderStateShard.NO_CULL)
					.createCompositeState(false));
	private static final CopyOnWriteArrayList<IntObjectPair<List<OccultBlock>>> OCCULT_BLOCKS = new CopyOnWriteArrayList<>();

	public static void init() {
		NeoForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, RenderLevelStageEvent.class, OccultBlockRenderer::onWorldRender);
	}

	public static void addOccultBlocks(int renderUntil, List<OccultBlock> blocks) {
		OCCULT_BLOCKS.add(IntObjectPair.of(renderUntil, blocks));
	}

	private static void onWorldRender(final RenderLevelStageEvent ev) {
		if (ev.getStage() != RenderLevelStageEvent.Stage.AFTER_PARTICLES || OCCULT_BLOCKS.isEmpty())
			return;

		Minecraft mc = Minecraft.getInstance();
		boolean rendered = false;
		PoseStack matrix = ev.getPoseStack();
		Vec3 cameraPos = mc.gameRenderer.getMainCamera().getPosition();
		VertexConsumer buffer = mc.renderBuffers().bufferSource().getBuffer(CUSTOM_LINES_RENDER_TYPE);

		matrix.pushPose();
		matrix.translate(-cameraPos.x, -cameraPos.y, -cameraPos.z);

		PoseStack.Pose pose = matrix.last();

		RenderSystem.disableDepthTest();
		RenderSystem.depthMask(false);
		RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		RenderSystem.enableBlend();

		OCCULT_BLOCKS.removeIf(entry -> GlobalEvents.tick >= entry.leftInt());

		for (IntObjectPair<List<OccultBlock>> entry : OCCULT_BLOCKS) {
			for (Iterator<OccultBlock> iterator = entry.right().iterator(); iterator.hasNext();) {
				OccultBlock block = iterator.next();

				if (GlobalEvents.tick % 2 == 1 && mc.player.level().getBlockState(block.pos) != block.state) {
					iterator.remove();

					continue;
				}

				BlockPos pos = block.pos;
				VoxelShape shape = block.state.getShape(mc.level, pos, CollisionContext.of(mc.player));
				ColourUtil.Colour colour = block.colour;
				rendered = true;

				shape.forAllEdges((minX, minY, minZ, maxX, maxY, maxZ) -> {
					float lengthX = (float)(maxX - minX);
					float lengthY = (float)(maxY - minY);
					float lengthZ = (float)(maxZ - minZ);
					float length = Mth.sqrt(lengthX * lengthX + lengthY * lengthY + lengthZ * lengthZ);
					lengthX /= length;
					lengthY /= length;
					lengthZ /= length;

					buffer.vertex(pose.pose(), (float)minX + pos.getX(), (float)minY + pos.getY(), (float)minZ + pos.getZ()).color(colour.red(), colour.green(), colour.blue(), colour.alpha()).normal(pose.normal(), lengthX, lengthY, lengthZ).endVertex();
					buffer.vertex(pose.pose(), (float)maxX + pos.getX(), (float)maxY + pos.getY(), (float)maxZ + pos.getZ()).color(colour.red(), colour.green(), colour.blue(), colour.alpha()).normal(pose.normal(), lengthX, lengthY, lengthZ).endVertex();
				});
			}
		}

		matrix.popPose();
		RenderSystem.polygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
		RenderSystem.disableBlend();
		RenderSystem.enableDepthTest();
		RenderSystem.depthMask(true);

		if (rendered)
			mc.renderBuffers().bufferSource().endBatch();
	}

	private static ColourUtil.Colour getColourForBlock(BlockState block) {
		List<Tier> tiers = TierSortingRegistry.getSortedTiers();
		float tierCount = tiers.size();

		for (int i = 0; i < tierCount; i++) {
			Tier tier = tiers.get(i);

			if (TierSortingRegistry.isCorrectTierForDrops(tier, block))
				return new ColourUtil.Colour(1 - (i / tierCount), i / tierCount, 0, 0.85f);
		}

		return new ColourUtil.Colour(0, 1, 0, 1);
	}

	public record OccultBlock(ColourUtil.Colour colour, BlockPos pos, BlockState state) {
		public OccultBlock(BlockPos pos, BlockState state) {
			this(getColourForBlock(state), pos, state);
		}
	}
}
