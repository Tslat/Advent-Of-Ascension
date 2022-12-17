package net.tslat.aoa3.client.gui.container;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.common.container.CorruptedTravellerContainer;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.RenderUtil;
import net.tslat.smartbrainlib.util.RandomUtil;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public class CorruptedTravellerScreen extends AbstractContainerScreen<CorruptedTravellerContainer> {
	private static final ResourceLocation guiTexture = new ResourceLocation("aoa3", "textures/gui/containers/corrupted_traveller.png");
	private static final ArrayList<Item> validFoods = new ArrayList<Item>();

	private long nextFoodTick = 0;
	private Item currentGhostlyFood = Items.APPLE;
	private final Minecraft mc;

	public CorruptedTravellerScreen(CorruptedTravellerContainer container, Inventory inv, Component guiTitle) {
		super(container, inv, guiTitle);

		this.mc = Minecraft.getInstance();
	}

	@Override
	public void render(PoseStack matrix, int mouseX, int mouseY, float partialTicks) {
		renderBackground(matrix);
		super.render(matrix, mouseX, mouseY, partialTicks);
		renderTooltip(matrix, mouseX, mouseY);
	}

	@Override
	protected void renderBg(PoseStack matrix, float partialTicks, int mouseX, int mouseY) {
		final int centerX = (width - imageWidth) / 2;
		final int centerY = (height - imageHeight) / 2;

		RenderUtil.prepRenderTexture(guiTexture);
		RenderUtil.renderCustomSizedTexture(matrix, centerX, centerY, 0, 0, imageWidth, imageHeight, 256, 256);
		renderGhostlyFood(matrix, centerX, centerY);
	}

	@Override
	protected void renderLabels(PoseStack matrix, int mouseX, int mouseY) {
		int titleWidth = 4 + mc.font.width(title);

		RenderUtil.prepRenderTexture(guiTexture);
		RenderUtil.renderCustomSizedTexture(matrix, 28, 4, 176, 0, 1, 12, 256, 256);

		for (int i = 0; i < titleWidth - 2; i++) {
			RenderUtil.renderCustomSizedTexture(matrix, 29 + i, 4, 177, 0, 1, 12, 256, 256);
		}

		RenderUtil.renderCustomSizedTexture(matrix, 28 + titleWidth - 2, 4, 178, 0, 1, 12, 256, 256);
		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		mc.font.draw(matrix, title, 30, 6, ColourUtil.WHITE);
	}

	private void renderGhostlyFood(PoseStack matrix, int centerX, int centerY) {
		Slot slot = menu.getSlot(0);

		if (!slot.hasItem()) {
			ItemStack stack = new ItemStack(getGhostlyFood());
			ItemRenderer itemRenderer = mc.getItemRenderer();
			BakedModel model = itemRenderer.getModel(stack, null, null, 0);

			mc.textureManager.getTexture(TextureAtlas.LOCATION_BLOCKS).setFilter(false, false);
			RenderUtil.setRenderingTexture(TextureAtlas.LOCATION_BLOCKS);
			RenderSystem.enableBlend();
			RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
			RenderUtil.resetShaderColour();
			PoseStack modelViewPose = RenderSystem.getModelViewStack();

			modelViewPose.pushPose();
			modelViewPose.translate(slot.x + centerX, slot.y + centerY, 100f + itemRenderer.blitOffset);
			modelViewPose.translate(8, 8, 0);
			modelViewPose.scale(1, -1, 1);
			modelViewPose.scale(16, 16, 16);
			RenderSystem.applyModelViewMatrix();

			PoseStack poseStack = new PoseStack();
			MultiBufferSource.BufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();
			boolean changeLighting = !model.usesBlockLight();

			if (changeLighting)
				Lighting.setupForFlatItems();

			itemRenderer.render(stack, ItemTransforms.TransformType.GUI, false, poseStack, bufferSource, 40, OverlayTexture.NO_OVERLAY, model);
			bufferSource.endBatch();
			RenderSystem.enableDepthTest();

			if (changeLighting)
				Lighting.setupFor3DItems();

			modelViewPose.popPose();
			RenderSystem.applyModelViewMatrix();
		}
	}

	private void compileFoodList() {
		for (Item item : ForgeRegistries.ITEMS.getValues()) {
			if (item.isEdible())
				validFoods.add(item);
		}
	}

	@Nonnull
	private Item getGhostlyFood() {
		if (validFoods.isEmpty())
			compileFoodList();

		long worldTick = mc.level.getGameTime();

		if (worldTick >= nextFoodTick) {
			currentGhostlyFood = validFoods.get(RandomUtil.randomNumberUpTo(validFoods.size()));
			nextFoodTick = worldTick + 20;
		}

		return currentGhostlyFood;
	}
}
