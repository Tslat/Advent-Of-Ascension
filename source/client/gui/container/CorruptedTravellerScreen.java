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
	public void render(int mouseX, int mouseY, float partialTicks) {
		renderBackground();
		super.render(mouseX, mouseY, partialTicks);
		renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
		mc.getTextureManager().bindTexture(guiTexture);

		final int centerX = (width - xSize) / 2;
		final int centerY = (height - ySize) / 2;

		RenderUtil.renderCustomSizedTexture(centerX, centerY, 0, 0, xSize, ySize, 256, 256);
		renderGhostlyFood(centerX, centerY);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		int titleWidth = 4 + mc.fontRenderer.getStringWidth(title.getFormattedText());

		RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
		mc.getTextureManager().bindTexture(guiTexture);
		RenderUtil.renderCustomSizedTexture(28, 4, 176, 0, 1, 12, 256, 256);

		for (int i = 0; i < titleWidth - 2; i++) {
			RenderUtil.renderCustomSizedTexture(29 + i, 4, 177, 0, 1, 12, 256, 256);
		}

		RenderUtil.renderCustomSizedTexture(28 + titleWidth - 2, 4, 178, 0, 1, 12, 256, 256);
		RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
		mc.fontRenderer.drawString(title.getFormattedText(), 30, 6, NumberUtil.RGB(255, 255, 255));
	}

	private void renderGhostlyFood(int centerX, int centerY) {
		Slot slot = container.getSlot(0);

		if (!slot.getHasStack()) {
			ItemStack stack = new ItemStack(getGhostlyFood());
			IBakedModel model = mc.getItemRenderer().getItemModelWithOverrides(stack, null, null);

			RenderSystem.pushMatrix();
			mc.textureManager.bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
			mc.textureManager.getTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE).setBlurMipmapDirect(false, false);
			RenderSystem.enableRescaleNormal();
			RenderSystem.enableAlphaTest();
			RenderSystem.defaultAlphaFunc();
			RenderSystem.enableBlend();
			RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
			RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
			RenderSystem.translatef(slot.xPos + centerX, slot.yPos + centerY, 100.0F);
			RenderSystem.translatef(8.0F, 8.0F, 0.0F);
			RenderSystem.scalef(1.0F, -1.0F, 1.0F);
			RenderSystem.scalef(16.0F, 16.0F, 16.0F);

			MatrixStack matrixstack = new MatrixStack();
			IRenderTypeBuffer.Impl renderTypeBuffer = Minecraft.getInstance().getRenderTypeBuffers().getBufferSource();
			boolean diffuseLighting = !model.func_230044_c_();

			if (diffuseLighting)
				RenderHelper.setupGuiFlatDiffuseLighting();

			mc.getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.GUI, false, matrixstack, renderTypeBuffer, 100, OverlayTexture.NO_OVERLAY, model);
			renderTypeBuffer.finish();
			RenderSystem.enableDepthTest();

			if (diffuseLighting)
				RenderHelper.setupGui3DDiffuseLighting();

			RenderSystem.disableAlphaTest();
			RenderSystem.disableRescaleNormal();
			RenderSystem.popMatrix();
		}
	}

	private void compileFoodList() {
		for (Item item : ForgeRegistries.ITEMS.getValues()) {
			if (item.isFood())
				validFoods.add(item);
		}
	}

	@Nonnull
	private Item getGhostlyFood() {
		if (validFoods.isEmpty())
			compileFoodList();

		long worldTick = mc.world.getGameTime();

		if (worldTick >= nextFoodTick) {
			currentGhostlyFood = validFoods.get(RandomUtil.randomNumberUpTo(validFoods.size()));
			nextFoodTick = worldTick + 20;
		}

		return currentGhostlyFood;
	}
}
