package net.tslat.aoa3.client.gui.hud.toasts;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.tslat.aoa3.util.LocaleUtil;

public class ItemRequirementToast extends GenericToast {
	private final Ingredient ingredient;

	public ItemRequirementToast(Ingredient ingredient) {
		super(LocaleUtil.getLocaleMessage(LocaleUtil.createGenericLocaleKey("gui", "toast.itemReq.title"), ChatFormatting.DARK_RED), LocaleUtil.getLocaleMessage(LocaleUtil.createGenericLocaleKey("gui", "toast.itemReq.subtitle"), ingredient.getItems()[0].getHoverName()));

		this.ingredient = ingredient;
	}

	@Override
	protected void drawIcon(GuiGraphics guiGraphics, ToastComponent toastComponent, long timeSinceLastVisible) {
		final PoseStack poseStack = guiGraphics.pose();
		final ItemStack[] stacks = this.ingredient.getItems();
		final ItemStack stack = this.ingredient.getItems()[(int)(timeSinceLastVisible / Math.max(1, defaultLifeSpan() * toastComponent.getNotificationDisplayTimeMultiplier() / (double)stacks.length) % (double)stacks.length)];

		poseStack.pushPose();
		poseStack.translate(8.5f, 8, 0);
		guiGraphics.renderFakeItem(stack, 0, 0);
		poseStack.popPose();
	}
}
