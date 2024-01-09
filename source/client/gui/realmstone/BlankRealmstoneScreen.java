package net.tslat.aoa3.client.gui.realmstone;

import com.google.common.base.Suppliers;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.data.client.RealmstoneInsertsReloadListener;
import net.tslat.aoa3.library.object.RenderContext;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.RenderUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.function.Supplier;

public class BlankRealmstoneScreen extends Screen {
	private static final ResourceLocation background = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/realmstonegui/background.png");
	private static final ResourceLocation windowFrame = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/realmstonegui/window_frame.png");
	private static final ResourceLocation widgets = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/realmstonegui/widgets.png");
	private static final Random rand = new Random(0);
	private static final int backgroundHeight = 2000;
	private static final int backgroundWidth = 2000;
	private static final int viewSpaceWidth = 238;
	private static final int viewSpaceHeight = 166;
	private static float scale = 0.5f;

	private final HashMap<String, RealmstoneWorldInsert> worldInserts = new HashMap<>(23);

	private static RealmstoneWorldInsert currentlyHoveredInsert = null;
	private boolean isPanning = false;
	private int panMouseX = 0;
	private int panMouseY = 0;
	private int offsetX = 827 - viewSpaceWidth + 50;
	private int offsetY = 695 - viewSpaceHeight + 50;

	public BlankRealmstoneScreen() {
		super(LocaleUtil.getLocaleMessage("gui.aoa3.realmstone.title"));
		scale = 0.5f;

		worldInserts.putAll(RealmstoneInsertsReloadListener.INSERTS);
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		if (minecraft == null)
			return;

		int x = (this.width - 252) / 2;
		int y = (this.height - 182) / 2;
		currentlyHoveredInsert = null;
		PoseStack poseStack = guiGraphics.pose();

		if (isDragging()) {
			if (!isPanning) {
				panMouseX = mouseX;
				panMouseY = mouseY;
			}

			isPanning = true;
			offsetX = Mth.clamp(offsetX + (int)((panMouseX - mouseX) / scale), 0, backgroundWidth - (int)(viewSpaceWidth / scale));
			offsetY = Mth.clamp(offsetY + (int)((panMouseY - mouseY) / scale), 0, backgroundHeight - (int)(viewSpaceHeight / scale));
			panMouseX = mouseX;
			panMouseY = mouseY;
		}
		else {
			isPanning = false;
			panMouseX = 0;
			panMouseY = 0;
		}

		renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
		poseStack.pushPose();
		poseStack.scale(scale, scale, scale);
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderTexture(0, background);
		RenderUtil.renderCustomSizedTexture(poseStack, (int)((x + 5) / scale), (int)((y + 7) / scale), offsetX, offsetY, (int)(viewSpaceWidth / scale), (int)(viewSpaceHeight / scale), backgroundWidth, backgroundHeight);

		for (RealmstoneWorldInsert insert : worldInserts.values()) {
			insert.render(poseStack, minecraft, worldInserts, (int)((x + 5) / scale), (int)((y + 7) / scale), offsetX, offsetY, (int)(mouseX / scale), (int)(mouseY / scale));
		}

		poseStack.popPose();
		drawWindowFrame(RenderContext.of(guiGraphics), x, y);

		if (currentlyHoveredInsert != null && !currentlyHoveredInsert.getHoverTexts().isEmpty())
			guiGraphics.renderTooltip(Minecraft.getInstance().font, currentlyHoveredInsert.getHoverTexts(), mouseX, mouseY);
	}

	private void drawWindowFrame(RenderContext renderContext, int x, int y) {
		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		RenderSystem.enableBlend();
		//Lighting.turnOff();
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderTexture(0, windowFrame);
		RenderUtil.renderCustomSizedTexture(renderContext.getGuiGraphics().pose(), x, y, 0, 0, 256, 187, 256, 256);
		renderContext.renderText(title, x + 8, y + 6, ColourUtil.RGB(181, 181, 181), RenderUtil.TextRenderType.NORMAL);
	}

	public static class RealmstoneWorldInsert {
		private static final int iconSize = 100;
		private final String id;
		private final ResourceLocation texture;

		private final int posX;
		private final int posY;
		private final String[] parentNodes;

		private final Supplier<ArrayList<FormattedCharSequence>> hoverTexts;

		public RealmstoneWorldInsert(String id, int posX, int posY, String... parentNodes) {
			this.id = id;
			this.posX = posX;
			this.posY = posY;
			this.parentNodes = parentNodes;
			this.texture = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/realmstonegui/worlds/" + id + ".png");

			hoverTexts = Suppliers.memoize(() -> {
				ArrayList<FormattedCharSequence> texts = new ArrayList<>(2);

				texts.add(LocaleUtil.getLocaleMessage(LocaleUtil.createGenericLocaleKey("dimension", id), ChatFormatting.BLUE).getVisualOrderText());
				texts.addAll(Minecraft.getInstance().font.split(LocaleUtil.getLocaleMessage(LocaleUtil.createGenericLocaleKey("gui", "realmstoneMenu.hover." + id)), 200));

				return texts;
			});
		}

		public String getId() {
			return this.id;
		}

		public String[] getParents() {
			return this.parentNodes;
		}

		public JsonObject toJson() {
			JsonObject obj = new JsonObject();

			obj.addProperty("id", id);
			obj.addProperty("gui_x", posX);
			obj.addProperty("gui_y", posY);

			if (parentNodes.length > 0) {
				JsonArray parentNodes = new JsonArray();

				for (String str : this.parentNodes) {
					parentNodes.add(str);
				}

				obj.add("parents", parentNodes);
			}

			return obj;
		}

		public static RealmstoneWorldInsert fromJson(JsonObject jsonObject) {
			String[] parentNodes;

			if (jsonObject.has("parents")) {
				JsonArray parents = jsonObject.get("parents").getAsJsonArray();
				parentNodes = new String[parents.size()];

				int i = 0;

				for (JsonElement element : parents) {
					parentNodes[i] = element.getAsString();
					i++;
				}
			}
			else {
				parentNodes = new String[] {};
			}

			return new RealmstoneWorldInsert(jsonObject.get("id").getAsString(), jsonObject.get("gui_x").getAsInt(), jsonObject.get("gui_y").getAsInt(), parentNodes);
		}

		private void render(PoseStack matrix, Minecraft mc, HashMap<String, RealmstoneWorldInsert> worldInserts, int baseX, int baseY, int scrollX, int scrollY, int mouseX, int mouseY) {
			int renderX = baseX + posX - scrollX;
			int renderY = baseY + posY - scrollY;
			int overlapLeft = Mth.clamp(baseX - renderX, 0, iconSize);

			if (overlapLeft < 100) {
				int overlapTop = Mth.clamp(baseY - renderY, 0, iconSize);

				if (overlapTop < 100) {
					int overlapRight = Mth.clamp(renderX + 100 - (baseX + (int)(viewSpaceWidth / scale)), 0, iconSize);

					if (overlapRight < 100) {
						int overlapBottom = Mth.clamp(renderY + 100 - (baseY + (int)(viewSpaceHeight / scale)), 0, iconSize);

						if (overlapBottom < 100) {
							renderX += overlapLeft;
							renderY += overlapTop;
							int width = 100 - overlapLeft - overlapRight;
							int height = 100 - overlapTop - overlapBottom;

							GlStateManager._enableBlend();
							RenderSystem.setShader(GameRenderer::getPositionTexShader);
							RenderSystem.setShaderTexture(0, texture);
							RenderUtil.renderCustomSizedTexture(matrix, renderX, renderY, overlapLeft, overlapTop, width, height, 100, 100);
							RenderSystem.setShaderTexture(0, widgets);
							RenderUtil.renderCustomSizedTexture(matrix, renderX, renderY, overlapLeft, overlapTop, width, height, 210, 100);
							GlStateManager._disableBlend();

							if (currentlyHoveredInsert == null && NumberUtil.isWithinArea(mouseX, mouseY, renderX + 5, renderY + 5, renderX + width - 5, renderY + height - 5))
								currentlyHoveredInsert = this;
						}
					}
				}
			}

			renderLinks(matrix, mc, worldInserts, baseX, baseY, scrollX, scrollY);
		}

		private void renderLinks(PoseStack matrix, Minecraft mc, HashMap<String, RealmstoneWorldInsert> nodes, int baseX, int baseY, int scrollX, int scrollY) {
			rand.setSeed(0);
			RenderSystem.enableBlend();
			RenderSystem.setShader(GameRenderer::getPositionTexShader);
			RenderSystem.setShaderTexture(0, widgets);

			int minX = baseX - (int)(1.5f / scale);
			int minY = baseY - (int)(3 / scale);
			int maxX = baseX + (int)((viewSpaceWidth + 8.5f) / scale);
			int maxY = baseY + (int)((viewSpaceHeight + 8.5f) / scale);

			for (String id : parentNodes) {
				rand.setSeed(posX + (long)posY * parentNodes.length * id.length());

				RealmstoneWorldInsert parent = nodes.get(id);
				int x = baseX + parent.posX + 50 - scrollX;
				int y = baseY + parent.posY + 50 - scrollY;
				int x2 = baseX + posX + 50 - scrollX;
				int y2 = baseY + posY + 50 - scrollY;
				int distX = x - x2;
				int distY = y - y2;
				float originalWidth = (float)Math.sqrt(distX * distX + distY * distY) - 80;
				int startClipVal = calcLineCornerCutoff(x, y, minX, minY, maxX, maxY);
				int endClipVal = calcLineCornerCutoff(x2, y2, minX, minY, maxX, maxY);
				boolean shouldRender = false;
				boolean trimStart = false;
				int i = 3;

				while(i > 0) {
					if (startClipVal + endClipVal == 0) {
						shouldRender = true;

						break;
					}

					if (startClipVal != 0 && endClipVal != 0)
						break;

					double testX = x;
					double testY = y;
					int testVal = Math.max(endClipVal, startClipVal);

					if (startClipVal > endClipVal)
						trimStart = true;

					if ((testVal & 8) == 8) {
						testX = x + (x2 - x) * (maxY - y) / (float)(y2 - y);
						testY = maxY;
					}
					else if ((testVal & 4) == 4) {
						testX = x + (x2 - x) * (baseY - y) / (float)(y2 - y);
						testY = baseY;
					}
					else if ((testVal & 2) == 2) {
						testX = maxX;
						testY = y + (y2 - y) * (maxX - x) / (float)(x2 - x);
					}
					else if ((testVal & 1) == 1) {
						testX = baseX;
						testY = y + (y2 - y) * (baseX - x) / (float)(x2 - x);
					}

					if (testVal == startClipVal) {
						x = (int)testX;
						y = (int)testY;
						startClipVal = calcLineCornerCutoff(x, y, minX, minY, maxX, maxY);

					}
					else {
						x2 = (int)testX;
						y2 = (int)testY;
						endClipVal = calcLineCornerCutoff(x2, y2, minX, minY, maxX, maxY);
					}

					i--;
				}

				if (!shouldRender)
					continue;

				distX = x - x2;
				distY = y - y2;
				double angle = Math.atan2(distY, distX) + Math.PI;
				float renderWidth = (float)Math.sqrt(distX * distX + distY * distY) - 80;
				float uWidth = 110 * (renderWidth / originalWidth);
				float u = 100;

				if (uWidth < 110 && trimStart)
					u += 110 - uWidth;

				matrix.pushPose();
				matrix.translate(x, y, 0);
				RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
				RenderSystem.setShaderColor(rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), 1);
				matrix.mulPose(Axis.ZP.rotation((float)angle));
				RenderUtil.renderScaledCustomSizedTexture(matrix, 40, -7.5f, u, rand.nextInt(5) * 15, uWidth, 15, renderWidth, 15, 210, 100);
				RenderSystem.setShaderColor(1, 1, 1, 1);
				matrix.popPose();
			}

			GlStateManager._disableBlend();
		}

		private ArrayList<FormattedCharSequence> getHoverTexts() {
			return hoverTexts.get();
		}

		private int calcLineCornerCutoff(int x, int y, int minX, int minY, int maxX, int maxY) {
			int val = 0;

			if (x < minX) {
				val = 1;
			}
			else if (x > maxX) {
				val = 2;
			}

			if (y < minY) {
				val += 4;
			}
			else if (y > maxY) {
				val += 8;
			}

			return val;
		}
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		int left = (this.width - 256) / 2;
		int bottom = (this.height - 186) / 2;

		if (mouseX >= left && mouseX <= left + 256 && mouseY >= bottom && mouseY <= bottom + 186) {
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
	public boolean mouseScrolled(double mouseX, double mouseY, double lateralScroll, double verticalScroll) {
		scale += verticalScroll / 100f;
		scale = (float)Mth.clamp(scale, 0.14, 1.3);

		offsetX = Math.min(offsetX, backgroundWidth - (int)(viewSpaceWidth / scale));
		offsetY = Math.min(offsetY, backgroundHeight - (int)(viewSpaceHeight / scale));

		return true;
	}
}
