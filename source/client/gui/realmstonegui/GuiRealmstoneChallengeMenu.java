package net.tslat.aoa3.client.gui.realmstonegui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;
import org.lwjgl.input.Mouse;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;

@SideOnly(Side.CLIENT)
public class GuiRealmstoneChallengeMenu extends GuiScreen {
	private static final ResourceLocation background = new ResourceLocation("aoa3", "textures/gui/realmstonegui/background.png");
	private static final ResourceLocation windowFrame = new ResourceLocation("aoa3", "textures/gui/realmstonegui/window_frame.png");
	private static final int backgroundHeight = 1515;
	private static final int backgroundWidth = 1889;
	private static final int viewSpaceWidth = 234;
	private static final int viewSpaceHeight = 155;
	private static float scale = 0.5f;

	private final ArrayList<GuiRealmstoneWorldInsert> worldInserts = new ArrayList<GuiRealmstoneWorldInsert>(23);
	private final String title = StringUtil.getLocaleString("gui.realmstoneMenu.title");

	private static GuiRealmstoneWorldInsert currentlyHoveredInsert = null;
	private boolean isPanning = false;
	private int panMouseX = 0;
	private int panMouseY = 0;
	private int offsetX = 827 - viewSpaceWidth + 50;
	private int offsetY = 695 - viewSpaceHeight + 50;

	public GuiRealmstoneChallengeMenu() {
		scale = 0.5f;

		worldInserts.add(new GuiRealmstoneWorldInsert("dimension.aoa.abyss", "gui.realmstoneMenu.hover.abyss", 1228, 694, 100, 200));
		worldInserts.add(new GuiRealmstoneWorldInsert("dimension.aoa.ancientCavern", "gui.realmstoneMenu.hover.ancientCavern", 727, 1295, 300, 300));
		worldInserts.add(new GuiRealmstoneWorldInsert("dimension.aoa.barathos", "gui.realmstoneMenu.hover.barathos", 627, 895, 400, 100));
		worldInserts.add(new GuiRealmstoneWorldInsert("dimension.aoa.candyland", "gui.realmstoneMenu.hover.candyland", 427, 595, 200, 200));
		worldInserts.add(new GuiRealmstoneWorldInsert("dimension.aoa.celeve", "gui.realmstoneMenu.hover.celeve", 1252, 970, 500, 300));
		worldInserts.add(new GuiRealmstoneWorldInsert("dimension.aoa.creeponia", "gui.realmstoneMenu.hover.creeponia", 827, 495, 0, 100));
		worldInserts.add(new GuiRealmstoneWorldInsert("dimension.aoa.crystevia", "gui.realmstoneMenu.hover.crystevia", 827, 1095, 300, 200));
		worldInserts.add(new GuiRealmstoneWorldInsert("dimension.aoa.deeplands", "gui.realmstoneMenu.hover.deeplands", 827, 895, 300, 100));
		worldInserts.add(new GuiRealmstoneWorldInsert("dimension.aoa.dustopia", "gui.realmstoneMenu.hover.dustopia", 1428, 795, 100, 400));
		worldInserts.add(new GuiRealmstoneWorldInsert("dimension.aoa.gardencia", "gui.realmstoneMenu.hover.gardencia", 427, 795, 200, 300));
		worldInserts.add(new GuiRealmstoneWorldInsert("dimension.aoa.greckon", "gui.realmstoneMenu.hover.greckon", 1428, 595, 100, 300));
		worldInserts.add(new GuiRealmstoneWorldInsert("dimension.aoa.haven", "gui.realmstoneMenu.hover.haven", 1102, 1120, 500, 200));
		worldInserts.add(new GuiRealmstoneWorldInsert("dimension.aoa.immortallis", "gui.realmstoneMenu.hover.immortallis", 927, 1295, 300, 400));
		worldInserts.add(new GuiRealmstoneWorldInsert("dimension.aoa.iromine", "gui.realmstoneMenu.hover.iromine", 427, 1095, 400, 200));
		worldInserts.add(new GuiRealmstoneWorldInsert("dimension.aoa.lborean", "gui.realmstoneMenu.hover.lborean", 227, 695, 200, 400));
		worldInserts.add(new GuiRealmstoneWorldInsert("dimension.aoa.lelyetia", "gui.realmstoneMenu.hover.lelyetia", 1027, 895, 500, 100));
		worldInserts.add(new GuiRealmstoneWorldInsert("dimension.aoa.lunalus", "gui.realmstoneMenu.hover.lunalus", 1327, 1195, 500, 400));
		worldInserts.add(new GuiRealmstoneWorldInsert("dimension.aoa.mysterium", "gui.realmstoneMenu.hover.mysterium", 927, 295, 0, 200));
		worldInserts.add(new GuiRealmstoneWorldInsert("dimension.aoa.nether", "gui.realmstoneMenu.hover.nether", 1027, 695, 100, 100));
		worldInserts.add(new GuiRealmstoneWorldInsert("dimension.aoa.overworld", "gui.realmstoneMenu.hover.overworld", 827, 695, 200, 0));
		worldInserts.add(new GuiRealmstoneWorldInsert("dimension.aoa.precasia", "gui.realmstoneMenu.hover.precasia", 627, 695, 200, 100));
		worldInserts.add(new GuiRealmstoneWorldInsert("dimension.aoa.runandor", "gui.realmstoneMenu.hover.runandor", 827, 95, 0, 400));
		worldInserts.add(new GuiRealmstoneWorldInsert("dimension.aoa.shyrelands", "gui.realmstoneMenu.hover.shyrelands", 1628, 695, 100, 500));
		worldInserts.add(new GuiRealmstoneWorldInsert("dimension.aoa.voxPonds", "gui.realmstoneMenu.hover.voxPonds", 727, 295, 0, 300));
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		int x = (this.width - 252) / 2;
		int y = (this.height - 182) / 2;
		currentlyHoveredInsert = null;

		if (Mouse.isButtonDown(0)) {
			if (!isPanning) {
				panMouseX = mouseX;
				panMouseY = mouseY;
			}

			isPanning = true;
			offsetX = MathHelper.clamp(offsetX + (int)((panMouseX - mouseX) / scale), 0, backgroundWidth - (int)(viewSpaceWidth / scale));
			offsetY = MathHelper.clamp(offsetY + (int)((panMouseY - mouseY) / scale), 0, backgroundHeight - (int)(viewSpaceHeight / scale));
			panMouseX = mouseX;
			panMouseY = mouseY;
		}
		else {
			isPanning = false;
			panMouseX = 0;
			panMouseY = 0;
		}

		drawDefaultBackground();
		GlStateManager.pushMatrix();
		GlStateManager.scale(scale, scale, scale);
		mc.getTextureManager().bindTexture(background);
		Gui.drawModalRectWithCustomSizedTexture((int)((x + 9) / scale), (int)((y + 18) / scale), offsetX, offsetY, (int)(234 / scale), (int)(155 / scale), backgroundWidth, backgroundHeight);

		for (GuiRealmstoneWorldInsert insert : worldInserts) {
			insert.render(mc, (int)((x + 9) / scale), (int)((y + 18) / scale), offsetX, offsetY, (int)(mouseX / scale), (int)(mouseY / scale));
		}

		GlStateManager.popMatrix();
		drawWindowFrame(x, y);

		if (currentlyHoveredInsert != null && !currentlyHoveredInsert.getHoverTexts().isEmpty())
			drawHoveringText(currentlyHoveredInsert.getHoverTexts(), mouseX, mouseY);
	}

	private void drawWindowFrame(int x, int y) {
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		GlStateManager.enableBlend();
		RenderHelper.disableStandardItemLighting();
		mc.getTextureManager().bindTexture(windowFrame);
		drawTexturedModalRect(x, y, 0, 0, 252, 182);
		fontRenderer.drawString(title, x + 8, y + 6, Enums.RGBIntegers.SILVER);
	}

	private static class GuiRealmstoneWorldInsert {
		private static final ResourceLocation worldImages = new ResourceLocation("aoa3", "textures/gui/realmstonegui/world_images.png");
		private static final int iconSize = 100;

		private final int uvX;
		private final int uvY;
		private final int posX;
		private final int posY;

		private final ArrayList<String> hoverTexts = new ArrayList<String>(2);

		private GuiRealmstoneWorldInsert(String worldNameKey, @Nullable String hoverTextKey, int posX, int posY, int uvX, int uvY) {
			hoverTexts.add(StringUtil.getColourLocaleString(worldNameKey, TextFormatting.BLUE));

			if (hoverTextKey != null)
				hoverTexts.add(StringUtil.getLocaleString(hoverTextKey));

			this.posX = posX;
			this.posY = posY;
			this.uvX = uvX;
			this.uvY = uvY;
		}

		private void render(Minecraft mc, int baseX, int baseY, int scrollX, int scrollY, int mouseX, int mouseY) {
			int renderX = baseX + posX - scrollX;
			int renderY = baseY + posY - scrollY;
			int overlapLeft = MathHelper.clamp(baseX - renderX, 0, iconSize);

			if (overlapLeft >= 100)
				return;

			int overlapTop = MathHelper.clamp(baseY - renderY, 0, iconSize);

			if (overlapTop >= 100)
				return;

			int overlapRight = MathHelper.clamp(renderX + 100 - (baseX + (int)(234 / scale)), 0, iconSize);

			if (overlapRight >= 100)
				return;

			int overlapBottom = MathHelper.clamp(renderY + 100 - (baseY + (int)(155 / scale)), 0, iconSize);

			if (overlapBottom >= 100)
				return;

			renderX += overlapLeft;
			renderY += overlapTop;
			int width = iconSize - overlapLeft - overlapRight;
			int height = iconSize - overlapTop - overlapBottom;

			GlStateManager.enableBlend();
			mc.getTextureManager().bindTexture(GuiRealmstoneWorldInsert.worldImages);
			Gui.drawModalRectWithCustomSizedTexture(renderX, renderY, uvX + overlapLeft, uvY + overlapTop, width, height, 1000, 1000);
			Gui.drawModalRectWithCustomSizedTexture(renderX, renderY, overlapLeft, overlapTop, width, height, 1000, 1000);
			GlStateManager.disableBlend();

			if (currentlyHoveredInsert == null && mouseX > renderX + 5 && mouseX < renderX + width - 5 && mouseY > renderY + 5 && mouseY < renderY + height - 5)
				currentlyHoveredInsert = this;
		}

		private ArrayList<String> getHoverTexts() {
			return hoverTexts;
		}
	}

	@Override
	public void handleMouseInput() throws IOException {
		super.handleMouseInput();

		float scrollAdjust = Mouse.getEventDWheel() / 4000f;

		if (scrollAdjust != 0) {
			scale += Mouse.getEventDWheel() / 4000f;

			scale = (float)MathHelper.clamp(scale, 0.14, 1.3);
			offsetX = Math.min(offsetX, backgroundWidth - (int)(viewSpaceWidth / scale));
			offsetY = Math.min(offsetY, backgroundHeight - (int)(viewSpaceHeight / scale));
		}
	}
}
