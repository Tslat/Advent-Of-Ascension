package net.tslat.aoa3.client.gui.container;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ChestScreen;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.client.ForgeHooksClient;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.function.Consumer;

public class SelectInventoryItemScreen extends ChestScreen {
	@Nullable
	private final Item currentItem;
	private final Consumer<Item> selectionConsumer;

	public SelectInventoryItemScreen(Minecraft minecraft, @Nullable Item currentItem, Consumer<Item> selectionConsumer) {
		super(new ChestContainer(ContainerType.GENERIC_9x1, 0, minecraft.player.inventory, new Inventory(0), 0), minecraft.player.inventory, LocaleUtil.getLocaleMessage("gui." + AdventOfAscension.MOD_ID + ".selectItem.title"));

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
	protected void renderTooltip(MatrixStack matrixStack, int x, int y) {
		if (this.hoveredSlot != null) {
			TranslationTextComponent component = null;

			if (this.hoveredSlot.hasItem()) {
				if (this.hoveredSlot.getItem().getItem() != currentItem)
					component = LocaleUtil.getLocaleMessage("gui." + AdventOfAscension.MOD_ID + ".selectItem.select", this.hoveredSlot.getItem().getHoverName());
			}
			else if (currentItem != null) {
				component = LocaleUtil.getLocaleMessage("gui." + AdventOfAscension.MOD_ID + ".selectItem.remove");
			}

			if (component != null)
				this.renderWrappedToolTip(matrixStack, Collections.singletonList(component), x, y, (font == null ? this.font : font));
		}
	}
}
