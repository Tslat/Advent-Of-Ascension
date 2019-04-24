package net.tslat.aoa3.client.gui.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.tslat.aoa3.client.gui.KeyBinder;
import net.tslat.aoa3.client.gui.mainwindow.AdventGuiTabPlayer;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.skills.AuguryUtil;
import org.lwjgl.input.Keyboard;

public class ResourcesRenderer {
	private final Minecraft mc;

	public static boolean revengeActive = false;

	private static final ResourceLocation resourceRevenge = new ResourceLocation("aoa3:textures/gui/resource/vulcanism_active.png");
	private static final ResourceLocation resourceRage = new ResourceLocation("aoa3:textures/gui/resource/rage_square.png");
	private static final ResourceLocation resourceRageActive = new ResourceLocation("aoa3:textures/gui/resource/rage_square_active.png");
	private static final ResourceLocation resourceEnergy = new ResourceLocation("aoa3:textures/gui/resource/energy_square.png");
	private static final ResourceLocation resourceCreation = new ResourceLocation("aoa3:textures/gui/resource/creation_square.png");
	private static final ResourceLocation resourceSoul = new ResourceLocation("aoa3:textures/gui/resource/soulpower_square.png");
	private static final ResourceLocation resourceSelyan = new ResourceLocation("aoa3:textures/gui/resource/tribute_selyan.png");
	private static final ResourceLocation resourceLuxon = new ResourceLocation("aoa3:textures/gui/resource/tribute_luxon.png");
	private static final ResourceLocation resourceErebon = new ResourceLocation("aoa3:textures/gui/resource/tribute_erebon.png");
	private static final ResourceLocation resourcePluton = new ResourceLocation("aoa3:textures/gui/resource/tribute_pluton.png");

	public ResourcesRenderer() {
		mc = Minecraft.getMinecraft();
		revengeActive = false;
	}

	@SubscribeEvent(priority = EventPriority.LOW)
	public void onRenderTick(final TickEvent.RenderTickEvent ev) {
		if (mc.currentScreen == null && !mc.gameSettings.hideGUI && !mc.player.isSpectator()) {
			if (KeyBinder.statusResourceGui) {
				final ScaledResolution res = new ScaledResolution(mc);
				final TextureManager textures = mc.getTextureManager();
				int x;
				int y;

				/* Revenge */
				if (revengeActive) {
					textures.bindTexture(resourceRevenge);
					mc.ingameGUI.drawTexturedModalRect(res.getScaledWidth() - ConfigurationUtil.guiXResourceRevenge, ConfigurationUtil.guiYResourceRevenge, 0, 0, 25, 25);
				}

				/* Rage */
				x = res.getScaledWidth() - ConfigurationUtil.guiXResourceRage;
				y = ConfigurationUtil.guiYResourceRage;

				if (AdventGuiTabPlayer.resourceRage >= 150.0f) {
					textures.bindTexture(resourceRageActive);
				}
				else {
					textures.bindTexture(resourceRage);
				}

				mc.ingameGUI.drawTexturedModalRect(x, y, 0, 0, 25, 25);
				mc.ingameGUI.drawTexturedModalRect(x, y, 25, 0, (int)(25 * (AdventGuiTabPlayer.resourceRage / 200f)), 50);
				x += 10;
				y += 14;

				if (AdventGuiTabPlayer.resourceRage >= 10.0f) {
					if (AdventGuiTabPlayer.resourceRage >= 100.0f) {
						x -= 6;
					}
					else {
						x -= 3;
					}
				}

				drawOutlinedText(Integer.toString((int)AdventGuiTabPlayer.resourceRage), x, y, 16777215, 2f);

				/* Tribute */
				x = res.getScaledWidth() - ConfigurationUtil.guiXTribute;
				y = ConfigurationUtil.guiYTribute;

				textures.bindTexture(resourceSelyan);
				mc.ingameGUI.drawTexturedModalRect(x, y, 0, 0, 25, 8);
				mc.ingameGUI.drawTexturedModalRect(x, y, 0, 8, (int)(25 * (AdventGuiTabPlayer.tributeSelyan / 200f)), 16);

				y += 8;

				textures.bindTexture(resourceLuxon);
				mc.ingameGUI.drawTexturedModalRect(x, y, 0, 0, 25, 5);
				mc.ingameGUI.drawTexturedModalRect(x, y, 0, 5, (int)(25 * (AdventGuiTabPlayer.tributeLuxon / 200f)), 10);

				y += 5;

				textures.bindTexture(resourceErebon);
				mc.ingameGUI.drawTexturedModalRect(x, y, 0, 0, 25, 5);
				mc.ingameGUI.drawTexturedModalRect(x, y, 0, 5, (int)(25 * (AdventGuiTabPlayer.tributeErebon / 200f)), 10);

				y += 5;

				textures.bindTexture(resourcePluton);
				mc.ingameGUI.drawTexturedModalRect(x, y, 0, 0, 25, 7);
				mc.ingameGUI.drawTexturedModalRect(x, y, 0, 7, (int)(25 * (AdventGuiTabPlayer.tributePluton / 200f)), 14);

				/* Energy */
				x = res.getScaledWidth() - ConfigurationUtil.guiXResourceEnergy;
				y = ConfigurationUtil.guiYResourceEnergy;

				textures.bindTexture(resourceEnergy);
				mc.ingameGUI.drawTexturedModalRect(x, y, 0, 0, 25, 25);
				mc.ingameGUI.drawTexturedModalRect(x, y, 25, 0, (int)(25 * (AdventGuiTabPlayer.resourceEnergy / 200f)), 25);
				x += 10;
				y += 14;

				if (AdventGuiTabPlayer.resourceEnergy >= 10.0f) {
					if (AdventGuiTabPlayer.resourceEnergy >= 100.0f) {
						x -= 6;
					}
					else {
						x -= 3;
					}
				}

				drawOutlinedText(Integer.toString((int)AdventGuiTabPlayer.resourceEnergy), x, y, 16777215, 2f);

				/* Creation */
				x = res.getScaledWidth() - ConfigurationUtil.guiXResourceCreation;
				y = ConfigurationUtil.guiYResourceCreation;

				textures.bindTexture(resourceCreation);
				mc.ingameGUI.drawTexturedModalRect(x, y, 0, 0, 25, 25);
				mc.ingameGUI.drawTexturedModalRect(x, y, 25, 0, (int)(25 * (AdventGuiTabPlayer.resourceCreation / AuguryUtil.getMaxCreation(AdventGuiTabPlayer.getSkillLevel(Enums.Skills.AUGURY)))), 50);

				y += 14;

				String creationValue = Integer.toString((int)AdventGuiTabPlayer.resourceCreation);

				drawOutlinedText(creationValue, (int)(x + 13.5 - mc.fontRenderer.getStringWidth(creationValue) / 2f), y, 16777215, 2f);

				/* Soul */
				x = res.getScaledWidth() - ConfigurationUtil.guiXResourceSoul;
				y = ConfigurationUtil.guiYResourceSoul;

				textures.bindTexture(resourceSoul);
				mc.ingameGUI.drawTexturedModalRect(x, y, 0, 0, 25, 25);
				mc.ingameGUI.drawTexturedModalRect(x, y, 25, 0, (int)(25 * (AdventGuiTabPlayer.resourceSoul / AuguryUtil.getMaxSoul(AdventGuiTabPlayer.getSkillLevel(Enums.Skills.AUGURY)))), 50);

				y += 14;
				String soulValue = Integer.toString((int)AdventGuiTabPlayer.resourceSoul);

				drawOutlinedText(soulValue, (int)(x + 13.5 - mc.fontRenderer.getStringWidth(soulValue) / 2f), y, 16777215, 2f);
			}
			else if (KeyBinder.statusResourceGuiMessage) {
				GlStateManager.pushMatrix();
				GlStateManager.disableDepth();
				GlStateManager.scale(0.75f, 0.75f, 0.75f);
				final ScaledResolution res = new ScaledResolution(mc);
				int x = res.getScaledWidth() - ConfigurationUtil.guiXResourceRage;
				int y = ConfigurationUtil.guiYResourceRage;

				drawOutlinedText(StringUtil.getLocaleStringWithArguments("gui.resources.showTip", Keyboard.getKeyName(KeyBinder.keyResourceGui.getKeyCode())), (int)(x / 0.75f), (int)(y / 0.75f), 16777215, 2f);
				GlStateManager.enableDepth();
				GlStateManager.popMatrix();
			}
		}
	}

	private void drawOutlinedText(String msg, int x, int y, int colour, float currentScale) {
		if (!Minecraft.getMinecraft().isUnicode())
			currentScale = 1;

		mc.fontRenderer.drawString(msg, x, y + (1 / currentScale), 0, false);
		mc.fontRenderer.drawString(msg, x, y - (1 / currentScale), 0, false);
		mc.fontRenderer.drawString(msg, x + (1 / currentScale), y, 0, false);
		mc.fontRenderer.drawString(msg, x - (1 / currentScale), y, 0, false);
		mc.fontRenderer.drawString(msg, x, y, colour, false);
		GlStateManager.color(255, 255, 255);
	}
}
