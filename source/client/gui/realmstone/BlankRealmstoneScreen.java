package net.tslat.aoa3.client.gui.realmstone;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.RenderUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class BlankRealmstoneScreen extends Screen {
	private static final ResourceLocation background = new ResourceLocation("aoa3", "textures/gui/realmstonegui/background.png");
	private static final ResourceLocation windowFrame = new ResourceLocation("aoa3", "textures/gui/realmstonegui/window_frame.png");
	private static final int backgroundHeight = 1515;
	private static final int backgroundWidth = 1889;
	private static final int viewSpaceWidth = 234;
	private static final int viewSpaceHeight = 155;
	private static float scale = 0.5f;

	private final ArrayList<RealmstoneWorldInsert> worldInserts = new ArrayList<RealmstoneWorldInsert>(23);

	private static RealmstoneWorldInsert currentlyHoveredInsert = null;
	private boolean isPanning = false;
	private int panMouseX = 0;
	private int panMouseY = 0;
	private int offsetX = 827 - viewSpaceWidth + 50;
	private int offsetY = 695 - viewSpaceHeight + 50;

	public BlankRealmstoneScreen() {
		super(LocaleUtil.getLocaleMessage("gui.aoa3.realmstone.title"));
		scale = 0.5f;

		worldInserts.add(new RealmstoneWorldInsert(LocaleUtil.Constants.ABYSS, "gui.realmstoneMenu.hover.abyss", 1228, 694, 100, 200));
		worldInserts.add(new RealmstoneWorldInsert(LocaleUtil.Constants.ANCIENT_CAVERN, "gui.realmstoneMenu.hover.ancient_cavern", 727, 1295, 300, 300));
		worldInserts.add(new RealmstoneWorldInsert(LocaleUtil.Constants.BARATHOS, "gui.realmstoneMenu.hover.barathos", 627, 895, 400, 100));
		worldInserts.add(new RealmstoneWorldInsert(LocaleUtil.Constants.CANDYLAND, "gui.realmstoneMenu.hover.candyland", 427, 595, 200, 200));
		worldInserts.add(new RealmstoneWorldInsert(LocaleUtil.Constants.CELEVE, "gui.realmstoneMenu.hover.celeve", 1252, 970, 500, 300));
		worldInserts.add(new RealmstoneWorldInsert(LocaleUtil.Constants.CREEPONIA, "gui.realmstoneMenu.hover.creeponia", 827, 495, 0, 100));
		worldInserts.add(new RealmstoneWorldInsert(LocaleUtil.Constants.CRYSTEVIA, "gui.realmstoneMenu.hover.crystevia", 827, 1095, 300, 200));
		worldInserts.add(new RealmstoneWorldInsert(LocaleUtil.Constants.DEEPLANDS, "gui.realmstoneMenu.hover.deeplands", 827, 895, 300, 100));
		worldInserts.add(new RealmstoneWorldInsert(LocaleUtil.Constants.DUSTOPIA, "gui.realmstoneMenu.hover.dustopia", 1428, 795, 100, 400));
		worldInserts.add(new RealmstoneWorldInsert(LocaleUtil.Constants.GARDENCIA, "gui.realmstoneMenu.hover.gardencia", 427, 795, 200, 300));
		worldInserts.add(new RealmstoneWorldInsert(LocaleUtil.Constants.GRECKON, "gui.realmstoneMenu.hover.greckon", 1428, 595, 100, 300));
		worldInserts.add(new RealmstoneWorldInsert(LocaleUtil.Constants.HAVEN, "gui.realmstoneMenu.hover.haven", 1102, 1120, 500, 200));
		worldInserts.add(new RealmstoneWorldInsert(LocaleUtil.Constants.IMMORTALLIS, "gui.realmstoneMenu.hover.immortallis", 927, 1295, 300, 400));
		worldInserts.add(new RealmstoneWorldInsert(LocaleUtil.Constants.IROMINE, "gui.realmstoneMenu.hover.iromine", 427, 1095, 400, 200));
		worldInserts.add(new RealmstoneWorldInsert(LocaleUtil.Constants.LBOREAN, "gui.realmstoneMenu.hover.lborean", 227, 695, 200, 400));
		worldInserts.add(new RealmstoneWorldInsert(LocaleUtil.Constants.LELYETIA, "gui.realmstoneMenu.hover.lelyetia", 1027, 895, 500, 100));
		worldInserts.add(new RealmstoneWorldInsert(LocaleUtil.Constants.LUNALUS, "gui.realmstoneMenu.hover.lunalus", 1327, 1195, 500, 400));
		worldInserts.add(new RealmstoneWorldInsert(LocaleUtil.Constants.MYSTERIUM, "gui.realmstoneMenu.hover.mysterium", 927, 295, 0, 200));
		worldInserts.add(new RealmstoneWorldInsert(LocaleUtil.Constants.NETHER, "gui.realmstoneMenu.hover.nether", 1027, 695, 100, 100));
		worldInserts.add(new RealmstoneWorldInsert(LocaleUtil.Constants.OVERWORLD, "gui.realmstoneMenu.hover.overworld", 827, 695, 200, 0));
		worldInserts.add(new RealmstoneWorldInsert(LocaleUtil.Constants.PRECASIA, "gui.realmstoneMenu.hover.precasia", 627, 695, 200, 100));
		worldInserts.add(new RealmstoneWorldInsert(LocaleUtil.Constants.RUNANDOR, "gui.realmstoneMenu.hover.runandor", 827, 95, 0, 400));
		worldInserts.add(new RealmstoneWorldInsert(LocaleUtil.Constants.SHYRELANDS, "gui.realmstoneMenu.hover.shyrelands", 1628, 695, 100, 500));
		worldInserts.add(new RealmstoneWorldInsert(LocaleUtil.Constants.VOX_PONDS, "gui.realmstoneMenu.hover.vox_ponds", 727, 295, 0, 300));
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		if (minecraft == null)
			return;

		int x = (this.width - 252) / 2;
		int y = (this.height - 182) / 2;
		currentlyHoveredInsert = null;

		if (isDragging()) {
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

		renderBackground();
		RenderSystem.pushMatrix();
		RenderSystem.scalef(scale, scale, scale);
		minecraft.getTextureManager().bindTexture(background);
		RenderUtil.renderCustomSizedTexture((int)((x + 9) / scale), (int)((y + 18) / scale), offsetX, offsetY, (int)(234 / scale), (int)(155 / scale), backgroundWidth, backgroundHeight);

		for (RealmstoneWorldInsert insert : worldInserts) {
			insert.render(minecraft, (int)((x + 9) / scale), (int)((y + 18) / scale), offsetX, offsetY, (int)(mouseX / scale), (int)(mouseY / scale));
		}

		RenderSystem.popMatrix();
		drawWindowFrame(x, y);

		if (currentlyHoveredInsert != null && !currentlyHoveredInsert.getHoverTexts().isEmpty())
			renderTooltip(currentlyHoveredInsert.getHoverTexts(), mouseX, mouseY);
	}

	private void drawWindowFrame(int x, int y) {
		RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
		RenderSystem.enableBlend();
		RenderHelper.disableStandardItemLighting();
		minecraft.getTextureManager().bindTexture(windowFrame);
		RenderUtil.renderCustomSizedTexture(x, y, 0, 0, 256, 187, 256, 256);
		minecraft.fontRenderer.drawString(title.getFormattedText(), x + 8, y + 6, NumberUtil.RGB(181, 181, 181));
	}

	private static class RealmstoneWorldInsert {
		private static final ResourceLocation worldImages = new ResourceLocation("aoa3", "textures/gui/realmstonegui/world_images.png");
		private static final int iconSize = 100;

		private final int uvX;
		private final int uvY;
		private final int posX;
		private final int posY;

		private final ArrayList<String> hoverTexts = new ArrayList<String>(2);

		private RealmstoneWorldInsert(String worldNameKey, @Nullable String hoverTextKey, int posX, int posY, int uvX, int uvY) {
			hoverTexts.add(LocaleUtil.getLocaleString(worldNameKey, TextFormatting.BLUE));

			if (hoverTextKey != null)
				hoverTexts.add(LocaleUtil.getLocaleString(hoverTextKey));

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
			mc.getTextureManager().bindTexture(RealmstoneWorldInsert.worldImages);
			RenderUtil.renderCustomSizedTexture(renderX, renderY, uvX + overlapLeft, uvY + overlapTop, width, height, 1000, 1000);
			RenderUtil.renderCustomSizedTexture(renderX, renderY, overlapLeft, overlapTop, width, height, 1000, 1000);
			GlStateManager.disableBlend();

			if (currentlyHoveredInsert == null && mouseX > renderX + 5 && mouseX < renderX + width - 5 && mouseY > renderY + 5 && mouseY < renderY + height - 5)
				currentlyHoveredInsert = this;
		}

		private ArrayList<String> getHoverTexts() {
			return hoverTexts;
		}
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		int left = (this.width - 252) / 2;
		int bottom = (this.height - 182) / 2;

		if (mouseX >= left && mouseX <= left + 252 && mouseY >= bottom && mouseY <= bottom + 182) {
			setDragging(true);

			return true;
		}

		return false;
	}

	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int button) {
		setDragging(false);

		return true;
	}

	@Override
	public boolean mouseScrolled(double mouseX, double mouseY, double scrollAmount) {
		scale += scrollAmount / 100f;
		scale = (float)MathHelper.clamp(scale, 0.14, 1.3);

		offsetX = Math.min(offsetX, backgroundWidth - (int)(viewSpaceWidth / scale));
		offsetY = Math.min(offsetY, backgroundHeight - (int)(viewSpaceHeight / scale));



		return true;
	}
}
