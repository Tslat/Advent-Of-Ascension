package net.tslat.aoa3.client.gui.container;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.client.ForgeHooksClient;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.function.Consumer;

public class SelectInventoryItemScreen extends AbstractContainerScreen {
	@Nullable
	private final Item currentItem;
	private final Consumer<Item> selectionConsumer;

	public SelectInventoryItemScreen(Minecraft minecraft, @Nullable Item currentItem, Consumer<Item> selectionConsumer) {
		super(new ChestMenu(MenuType.GENERIC_9x1, 0, minecraft.player.getInventory(), new SimpleContainer(0), 0), minecraft.player.getInventory(), LocaleUtil.getLocaleMessage("gui." + AdventOfAscension.MOD_ID + ".selectItem.title"));

		this.currentItem = currentItem;
		this.selectionConsumer = selectionConsumer;
	}

	@Override
	protected boolean checkHotbarKeyPressed(int pKeyCode, int pScanCode) {
		return false;
	}

	@Override
	public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
		if (pKeyCode == 256)
			ForgeHooksClient.popGuiLayer(minecraft);

		return true;
	}

	@Override
	protected void slotClicked(Slot slot, int slotId, int button, ClickType clickType) {
		if (slot != null) {
			Item item = slot.getItem().getItem();

			if ((currentItem == null && item != Items.AIR) || (currentItem != null && currentItem != item)) {
				this.selectionConsumer.accept(item);
				ForgeHooksClient.popGuiLayer(minecraft);
			}
		}
	}

	@Override
	protected void renderBg(PoseStack poseStack, float partialTicks, int mouseX, int mouseY) {}

	@Override
	protected void renderTooltip(PoseStack matrixStack, int x, int y) {
		if (this.hoveredSlot != null) {
			TranslatableComponent component = null;

			if (this.hoveredSlot.hasItem()) {
				if (this.hoveredSlot.getItem().getItem() != currentItem)
					component = LocaleUtil.getLocaleMessage("gui." + AdventOfAscension.MOD_ID + ".selectItem.select", this.hoveredSlot.getItem().getHoverName());
			}
			else if (currentItem != null) {
				component = LocaleUtil.getLocaleMessage("gui." + AdventOfAscension.MOD_ID + ".selectItem.remove");
			}

			if (component != null)
				this.renderComponentTooltip(matrixStack, Collections.singletonList(component), x, y, (font == null ? this.font : font));
		}
	}
}
