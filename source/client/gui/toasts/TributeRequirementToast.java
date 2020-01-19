package net.tslat.aoa3.client.gui.toasts;

import net.minecraft.client.gui.toasts.GuiToast;
import net.minecraft.client.gui.toasts.IToast;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.RenderUtil;
import net.tslat.aoa3.utils.StringUtil;

@SideOnly(Side.CLIENT)
public class TributeRequirementToast implements IToast {
	private static final ResourceLocation resourcesTextures = new ResourceLocation("aoa3", "textures/gui/maingui/resources.png");

	private final Enums.Deities deity;
	private final float resourceAmount;
	private final String title;
	private final String subtitle;

	public TributeRequirementToast(Enums.Deities releventDeity, int tributeRequirement) {
		this.deity = releventDeity;
		this.resourceAmount = tributeRequirement;
		this.title = StringUtil.getColourLocaleStringWithArguments("gui.aoatoast.tributeReq.title", TextFormatting.DARK_RED, StringUtil.capitaliseFirstLetter(releventDeity.toString()));
		this.subtitle = StringUtil.getLocaleStringWithArguments("gui.aoatoast.tributeReq.subtitle", String.valueOf(tributeRequirement));
	}

	public Enums.Deities getDeity() {
		return deity;
	}

	public float getAmountRequired() {
		return resourceAmount;
	}

	@Override
	public Visibility draw(GuiToast toastGui, long delta) {
		toastGui.getMinecraft().getTextureManager().bindTexture(TEXTURE_TOASTS);
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		toastGui.drawTexturedModalRect(0, 0, 0, 0, 160, 32);
		toastGui.getMinecraft().getTextureManager().bindTexture(resourcesTextures);
		RenderUtil.drawScaledCustomSizeModalRect(6, 6, 0, 540, 50, 50, 20, 20, 400, 590);
		toastGui.getMinecraft().fontRenderer.drawString(title, 30, 7, -11534256);
		toastGui.getMinecraft().fontRenderer.drawString(subtitle, 30, 18, Enums.RGBIntegers.WHITE);

		return delta >= 3000 ? Visibility.HIDE : Visibility.SHOW;
	}
}
