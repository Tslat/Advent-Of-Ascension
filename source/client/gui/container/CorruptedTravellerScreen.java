package net.tslat.aoa3.client.gui.container;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.common.container.CorruptedTravellerContainer;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.RenderUtil;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public class CorruptedTravellerScreen extends ContainerScreen<CorruptedTravellerContainer> {
	private static final ResourceLocation guiTexture = new ResourceLocation("aoa3", "textures/gui/containers/corrupted_traveller.png");
	private static final ArrayList<Item> validFoods = new ArrayList<Item>();

	private long nextFoodTick = 0;
	private Item currentGhostlyFood = Items.APPLE;
	private final Minecraft mc;

	public CorruptedTravellerScreen(CorruptedTravellerContainer container, PlayerInventory inv, ITextComponent guiTitle) {
		super(container, inv, guiTitle);

		this.mc = Minecraft.getInstance();
	}

	@Override
	public void render(MatrixStack matrix, int mouseX, int mouseY, float partialTicks) {
		renderBackground(matrix);
		super.render(matrix, mouseX, mouseY, partialTicks);
		renderTooltip(matrix, mouseX, mouseY);
	}

	@Override
	protected void renderBg(MatrixStack matrix, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
		mc.getTextureManager().bind(guiTexture);

		final int centerX = (width - imageWidth) / 2;
		final int centerY = (height - imageHeight) / 2;

		RenderUtil.renderCustomSizedTexture(matrix, centerX, centerY, 0, 0, imageWidth, imageHeight, 256, 256);
		renderGhostlyFood(matrix, centerX, centerY);
	}

	@Override
	protected void renderLabels(MatrixStack matrix, int mouseX, int mouseY) {
		int titleWidth = 4 + mc.font.width(title);

		RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
		mc.getTextureManager().bind(guiTexture);
		RenderUtil.renderCustomSizedTexture(matrix, 28, 4, 176, 0, 1, 12, 256, 256);

		for (int i = 0; i < titleWidth - 2; i++) {
			RenderUtil.renderCustomSizedTexture(matrix, 29 + i, 4, 177, 0, 1, 12, 256, 256);
		}

		RenderUtil.renderCustomSizedTexture(matrix, 28 + titleWidth - 2, 4, 178, 0, 1, 12, 256, 256);
		RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
		mc.font.draw(matrix, title, 30, 6, NumberUtil.RGB(255, 255, 255));
	}

	private void renderGhostlyFood(MatrixStack matrix, int centerX, int centerY) {
		Slot slot = menu.getSlot(0);

		if (!slot.hasItem()) {
			ItemStack stack = new ItemStack(getGhostlyFood());
			IBakedModel model = mc.getItemRenderer().getModel(stack, null, null);

			matrix.pushPose();
			mc.textureManager.bind(AtlasTexture.LOCATION_BLOCKS);
			mc.textureManager.getTexture(AtlasTexture.LOCATION_BLOCKS).setFilter(false, false);
			RenderSystem.enableRescaleNormal();
			RenderSystem.enableAlphaTest();
			RenderSystem.defaultAlphaFunc();
			RenderSystem.enableBlend();
			RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
			RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
			matrix.translate(slot.x + centerX, slot.y + centerY, 100.0F);
			matrix.translate(8.0F, 8.0F, 0.0F);
			matrix.scale(1.0F, -1.0F, 1.0F);
			matrix.scale(16.0F, 16.0F, 16.0F);

			IRenderTypeBuffer.Impl renderTypeBuffer = Minecraft.getInstance().renderBuffers().bufferSource();
			boolean diffuseLighting = !model.usesBlockLight();

			if (diffuseLighting)
				RenderHelper.setupForFlatItems();

			mc.getItemRenderer().render(stack, ItemCameraTransforms.TransformType.GUI, false, matrix, renderTypeBuffer, 100, OverlayTexture.NO_OVERLAY, model);
			renderTypeBuffer.endBatch();
			RenderSystem.enableDepthTest();

			if (diffuseLighting)
				RenderHelper.setupFor3DItems();

			RenderSystem.disableAlphaTest();
			RenderSystem.disableRescaleNormal();
			matrix.popPose();
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
