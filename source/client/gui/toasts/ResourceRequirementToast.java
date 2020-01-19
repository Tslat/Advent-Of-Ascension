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
public class ResourceRequirementToast implements IToast {
	private static final ResourceLocation resourcesTextures = new ResourceLocation("aoa3", "textures/gui/maingui/resources.png");

	private final Enums.Resources resource;
	private int iconUvX = 0;
	private int iconUvY = 0;
	private final float resourceAmount;
	private final String title;
	private final String subtitle;

	public ResourceRequirementToast(Enums.Resources relevantResource, float resourceRequirement) {
		this.resource = relevantResource;
		this.resourceAmount = resourceRequirement;
		this.title = StringUtil.getColourLocaleString("gui.aoatoast.resourceReq.title", TextFormatting.DARK_RED);
		this.subtitle = StringUtil.getLocaleStringWithArguments("gui.aoatoast.resourceReq.subtitle", StringUtil.capitaliseFirstLetter(relevantResource.toString()), StringUtil.roundToNthDecimalPlace(resourceRequirement, 2));

		applyIconUvs(relevantResource);
	}

	public Enums.Resources getResource() {
		return resource;
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
		RenderUtil.drawScaledCustomSizeModalRect(6, 6, iconUvX, iconUvY, 50, 50, 20, 20, 400, 590);
		toastGui.getMinecraft().fontRenderer.drawString(title, 30, 7, -11534256);
		toastGui.getMinecraft().fontRenderer.drawString(subtitle, 30, 18, Enums.RGBIntegers.WHITE);

		return delta >= 3000 ? Visibility.HIDE : Visibility.SHOW;
	}

	private void applyIconUvs(Enums.Resources skill) {
		switch (skill) {
			case CREATION:
				iconUvX = 0;
				iconUvY = 340;
				break;
			case ENERGY:
				iconUvX = 0;
				iconUvY = 140;
				break;
			case RAGE:
				iconUvX = 0;
				iconUvY = 50;
				break;
			case SOUL:
				iconUvX = 0;
				iconUvY = 440;
				break;
		}
	}
}
