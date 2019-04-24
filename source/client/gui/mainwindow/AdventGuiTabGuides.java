package net.tslat.aoa3.client.gui.mainwindow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.gui.ScrollablePane;
import net.tslat.aoa3.library.Enums;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class AdventGuiTabGuides extends GuiScreen {
	public static ArrayList<InfoBundle> infoBundles;
	public static String currentLanguage = FMLCommonHandler.instance().getCurrentLanguage();
	private BundlesMenu scrollMenu;

	private int openBundleIndex = -1;
	private long lastSelectionTime = 0;
	private int openBundleHeight = 0;
	private List<String> openBundleLines = null;

	private int adjustedMouseX;
	private int adjustedMouseY;

	AdventGuiTabGuides() {
		if (languageChanged())
			prepAvailableBundles();
	}

	@Override
	public void initGui() {
		super.initGui();

		if (scrollMenu == null)
			scrollMenu = new BundlesMenu(mc, AdventMainGui.scaledTabRootY, AdventMainGui.scaledTabRootX, 340, 764, AdventMainGui.scale);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);

		this.adjustedMouseX = (int)(mouseX * AdventMainGui.scaleInverse);
		this.adjustedMouseY = (int)(mouseY * AdventMainGui.scaleInverse);

		scrollMenu.drawScreen(adjustedMouseX, adjustedMouseY, partialTicks);
	}

	@Override
	public void handleMouseInput() throws IOException {
		super.handleMouseInput();

		scrollMenu.handleMouseInput(-1, -1);
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		if (scrollMenu != null)
			scrollMenu.handleMouseInput(adjustedMouseX, adjustedMouseY);
	}

	private enum AvailableBundles {
		Getting_Started,
		Creature_Icons,
		What_To_Do_First,
		Screen_Elements,
		Alchemy,
		Anima,
		Augury,
		Butchery,
		Creation,
		Engineering,
		Expedition,
		Extraction,
		Foraging,
		Hauling,
		Hunter,
		Infusion,
		Innervation,
		Logging,
		Runation
	}

	protected static boolean languageChanged() {
		return FMLCommonHandler.instance().getCurrentLanguage().equalsIgnoreCase(currentLanguage);
	}

	public static void prepAvailableBundles() {
		infoBundles = new ArrayList<InfoBundle>(AvailableBundles.values().length);
		ClassLoader classLoader = AdventOfAscension.instance.getClass().getClassLoader();
		InputStream streamIn;
		BufferedReader reader = null;
		InputStreamReader streamReader = null;

		for (AvailableBundles bundleEnum : AvailableBundles.values()) {
			try {
				streamIn = classLoader.getResourceAsStream("assets/aoa3/lang/guides/" + currentLanguage + "/" + bundleEnum.toString().toLowerCase() + ".txt");

				if (streamIn == null)
					streamIn = classLoader.getResourceAsStream("assets/aoa3/lang/guides/en_us/" + bundleEnum.toString().toLowerCase() + ".txt");

				if (streamIn == null)
					continue;

				final StringBuilder content = new StringBuilder();
				streamReader = new InputStreamReader(streamIn);
				reader = new BufferedReader(streamReader);

				reader.lines().forEach(line -> {content.append(line); content.append("\n");});
				infoBundles.add(new InfoBundle(content.toString()));
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				if (streamReader != null)
					IOUtils.closeQuietly(streamReader);

				if (reader != null)
					IOUtils.closeQuietly(reader);
			}
		}
	}

	public static class InfoBundle {
		protected final String title;
		protected final String info;

		public InfoBundle(String lines) {
			String[] data = lines.split("\n", 2);
			this.title = data[0];
			this.info = data[1];
		}
	}

	@Override
	public void onResize(Minecraft mcIn, int w, int h) {
		super.onResize(mcIn, w, h);

		if (scrollMenu != null)
			scrollMenu.onResize(mc, AdventMainGui.scaledTabRootX, AdventMainGui.scaledTabRootY, 764, 340);
	}

	private class BundlesMenu extends ScrollablePane {
		public BundlesMenu(Minecraft mc, int top, int left, int viewHeight, int viewWidth, float... currentScale) {
			super(mc, top, left, viewHeight, viewWidth, currentScale);
		}

		@Override
		public int getFullPaneHeight() {
			return openBundleIndex < 0 ? infoBundles.size() * 30 : 30 + openBundleHeight;
		}

		@Override
		public void drawPaneContents(int top, int left, int right, int bottom, float scrollDistance) {
			int timeAdjustedTop = 0;
			float selectedPercentSwitched = 0;

			if (openBundleIndex >= 0) {
				selectedPercentSwitched = Math.min(1, (System.currentTimeMillis() - lastSelectionTime) / 400f);
				timeAdjustedTop = top + (int)((openBundleIndex * 20) * (1 - selectedPercentSwitched));
			}

			if (timeAdjustedTop != top) {
				for (int i = Math.max(0, (int)((scrollDistance - 29) / 30f)); i * 30 <= bottom - top && i < infoBundles.size(); i++) {
					InfoBundle bundle = infoBundles.get(i);
					int rowTop = top + i * 30;
					int rowBottom = rowTop + 30;

					drawRect(left, rowTop, right, rowBottom, i % 2 == 0 ? 0xFF010101 : 0xFF202020);
					AdventMainGui.drawCenteredScaledString(mc.fontRenderer, bundle.title, left + (int)(viewWidth / 2f), rowTop + 8, 2f, Enums.RGBIntegers.WHITE, AdventMainGui.StringRenderType.NORMAL);
					AdventMainGui.drawScaledString(mc.fontRenderer, "V", left + 5, rowTop + 18, 1.5f, Enums.RGBIntegers.SILVER, AdventMainGui.StringRenderType.OUTLINED);
					AdventMainGui.drawScaledString(mc.fontRenderer, "V", right - 20, rowTop + 18, 1.5f, Enums.RGBIntegers.SILVER, AdventMainGui.StringRenderType.OUTLINED);
				}
			}

			if (openBundleIndex >= 0) {
				InfoBundle bundle = infoBundles.get(openBundleIndex);

				if (openBundleLines == null) {
					openBundleLines = mc.fontRenderer.listFormattedStringToWidth(bundle.info, (int)((viewWidth - 30) / 1.5f));
					openBundleHeight = Math.max(viewHeight - 30, 25 + (int)(openBundleLines.size() * (9 * 1.5f)));
				}

				int timeAdjustedBottom = (int)(timeAdjustedTop + 30 + openBundleHeight * selectedPercentSwitched);

				drawRect(left, timeAdjustedTop, right, timeAdjustedTop + 30, openBundleIndex % 2 == 0 ? 0xFF010101 : 0xFF202020);
				AdventMainGui.drawCenteredScaledString(mc.fontRenderer, bundle.title, left + (int)(viewWidth / 2f), timeAdjustedTop + 8, 2f, Enums.RGBIntegers.WHITE, AdventMainGui.StringRenderType.NORMAL);
				AdventMainGui.drawScaledString(mc.fontRenderer, "^", left + 5,  timeAdjustedTop + 18, 1.5f, Enums.RGBIntegers.SILVER, AdventMainGui.StringRenderType.OUTLINED);
				AdventMainGui.drawScaledString(mc.fontRenderer, "^", right - 20, timeAdjustedTop + 18, 1.5f, Enums.RGBIntegers.SILVER, AdventMainGui.StringRenderType.OUTLINED);
				drawRect(left, timeAdjustedTop + 30, right, timeAdjustedBottom, 0xFF505050);

				if (selectedPercentSwitched == 1) {
					int lineOffset = 0;

					GlStateManager.pushMatrix();
					GlStateManager.scale(1.5f, 1.5f, 1.5f);

					for (String s : openBundleLines) {
						mc.fontRenderer.drawString(s, (int)((left + 20) / 1.5f), (int)((top + 30 + lineOffset) / 1.5f), Enums.RGBIntegers.WHITE);
						lineOffset += 14;
					}

					GlStateManager.popMatrix();
				}
			}
		}

		@Override
		public void drawBackground() {}

		@Override
		public void handleMouseInput(int mouseX, int mouseY) {
			super.handleMouseInput(mouseX, mouseY);

			if (mouseX != -1) {
				int relativeMouseX = mouseX - left + 2;

				if (relativeMouseX < 0 || relativeMouseX > viewWidth - 6)
					return;

				int newTop = top - Math.max(0, (int)distanceScrolled);
				int relativeMouseY = mouseY - newTop + 2;
				System.out.println();
				if (relativeMouseY < 0 || mouseY > top + viewHeight)
					return;

				if (openBundleIndex < 0) {
					int selectedIndex = relativeMouseY / 30;

					if (infoBundles.size() > selectedIndex) {
						openBundleIndex = selectedIndex;
						lastSelectionTime = System.currentTimeMillis();
						openBundleHeight = 600;
						distanceScrolled = 0;
					}
				}
				else  {
					if (relativeMouseY <= 30) {
						openBundleIndex = -1;
						openBundleHeight = 0;
						openBundleLines = null;
					}
				}
			}
		}
	}
}
