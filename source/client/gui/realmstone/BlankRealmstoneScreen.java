package net.tslat.aoa3.client.gui.realmstone;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.util.Lazy;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.data.client.RealmstoneInsertsManager;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.RenderUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

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

	private final HashMap<String, RealmstoneWorldInsert> worldInserts = new HashMap<String, RealmstoneWorldInsert>(23);

	private static RealmstoneWorldInsert currentlyHoveredInsert = null;
	private boolean isPanning = false;
	private int panMouseX = 0;
	private int panMouseY = 0;
	private int offsetX = 827 - viewSpaceWidth + 50;
	private int offsetY = 695 - viewSpaceHeight + 50;

	public BlankRealmstoneScreen() {
		super(LocaleUtil.getLocaleMessage("gui.aoa3.realmstone.title"));
		scale = 0.5f;

		worldInserts.putAll(RealmstoneInsertsManager.INSERTS);
	}

	@Override
	public void render(MatrixStack matrix, int mouseX, int mouseY, float partialTicks) {
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

		renderBackground(matrix);
		matrix.pushPose();
		matrix.scale(scale, scale, scale);
		minecraft.getTextureManager().bind(background);
		RenderUtil.renderCustomSizedTexture(matrix, (int)((x + 5) / scale), (int)((y + 7) / scale), offsetX, offsetY, (int)(viewSpaceWidth / scale), (int)(viewSpaceHeight / scale), backgroundWidth, backgroundHeight);

		for (RealmstoneWorldInsert insert : worldInserts.values()) {
			insert.render(matrix, minecraft, worldInserts, (int)((x + 5) / scale), (int)((y + 7) / scale), offsetX, offsetY, (int)(mouseX / scale), (int)(mouseY / scale));
		}

		matrix.popPose();
		drawWindowFrame(matrix, x, y);

		if (currentlyHoveredInsert != null && !currentlyHoveredInsert.getHoverTexts().isEmpty())
			renderTooltip(matrix, currentlyHoveredInsert.getHoverTexts(), mouseX, mouseY);
	}

	private void drawWindowFrame(MatrixStack matrix, int x, int y) {
		RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
		RenderSystem.enableBlend();
		RenderHelper.turnOff();
		minecraft.getTextureManager().bind(windowFrame);
		RenderUtil.renderCustomSizedTexture(matrix, x, y, 0, 0, 256, 187, 256, 256);
		minecraft.font.draw(matrix, title, x + 8, y + 6, NumberUtil.RGB(181, 181, 181));
	}

	public static class RealmstoneWorldInsert {
		private static final int iconSize = 100;
		private final String id;
		private final ResourceLocation texture;

		private final int posX;
		private final int posY;
		private final String[] parentNodes;

		private final Lazy<ArrayList<IReorderingProcessor>> hoverTexts;

		public RealmstoneWorldInsert(String id, int posX, int posY, String... parentNodes) {
			this.id = id;
			this.posX = posX;
			this.posY = posY;
			this.parentNodes = parentNodes;
			this.texture = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/realmstonegui/worlds/" + id + ".png");

			hoverTexts = Lazy.of(() -> {
				ArrayList<IReorderingProcessor> texts = new ArrayList<IReorderingProcessor>(2);

				texts.add(LocaleUtil.getLocaleMessage("dimension." + AdventOfAscension.MOD_ID + "." + id, TextFormatting.BLUE).getVisualOrderText());
				texts.addAll(Minecraft.getInstance().font.split(LocaleUtil.getLocaleMessage("gui.realmstoneMenu.hover." + id), 200));

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

		private void render(MatrixStack matrix, Minecraft mc, HashMap<String, RealmstoneWorldInsert> worldInserts, int baseX, int baseY, int scrollX, int scrollY, int mouseX, int mouseY) {
			int renderX = baseX + posX - scrollX;
			int renderY = baseY + posY - scrollY;
			int overlapLeft = MathHelper.clamp(baseX - renderX, 0, iconSize);

			if (overlapLeft < 100) {
				int overlapTop = MathHelper.clamp(baseY - renderY, 0, iconSize);

				if (overlapTop < 100) {
					int overlapRight = MathHelper.clamp(renderX + 100 - (baseX + (int)(viewSpaceWidth / scale)), 0, iconSize);

					if (overlapRight < 100) {
						int overlapBottom = MathHelper.clamp(renderY + 100 - (baseY + (int)(viewSpaceHeight / scale)), 0, iconSize);

						if (overlapBottom < 100) {
							renderX += overlapLeft;
							renderY += overlapTop;
							int width = iconSize - overlapLeft - overlapRight;
							int height = iconSize - overlapTop - overlapBottom;

							GlStateManager._enableBlend();
							mc.getTextureManager().bind(texture);
							RenderUtil.renderCustomSizedTexture(matrix, renderX, renderY, overlapLeft, overlapTop, width, height, 100, 100);
							mc.getTextureManager().bind(widgets);
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

		private void renderLinks(MatrixStack matrix, Minecraft mc, HashMap<String, RealmstoneWorldInsert> nodes, int baseX, int baseY, int scrollX, int scrollY) {
			rand.setSeed(0);
			GlStateManager._enableBlend();
			mc.getTextureManager().bind(widgets);

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
				GlStateManager._color4f(rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), 1);
				matrix.mulPose(new Quaternion(0, 0, (float)angle, false));
				RenderUtil.renderScaledCustomSizedTexture(matrix, 40, -7.5f, u, rand.nextInt(5) * 15, uWidth, 15, renderWidth, 15, 210, 100);
				GlStateManager._color4f(1, 1, 1, 1);
				matrix.popPose();
			}

			GlStateManager._disableBlend();
		}

		private ArrayList<IReorderingProcessor> getHoverTexts() {
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
	public boolean mouseScrolled(double mouseX, double mouseY, double scrollAmount) {
		scale += scrollAmount / 100f;
		scale = (float)MathHelper.clamp(scale, 0.14, 1.3);

		offsetX = Math.min(offsetX, backgroundWidth - (int)(viewSpaceWidth / scale));
		offsetY = Math.min(offsetY, backgroundHeight - (int)(viewSpaceHeight / scale));

		return true;
	}
}
