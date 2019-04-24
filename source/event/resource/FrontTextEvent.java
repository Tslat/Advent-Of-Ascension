package net.nevermine.event.resource;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringTranslate;
import net.nevermine.assist.ConfigurationHelper;
import net.nevermine.assist.StringUtil;
import net.nevermine.assist.WebRequest;
import net.nevermine.assist.binding.LoginNoticeBinding;
import net.nevermine.assist.binding.SkillShowBinding;
import net.nevermine.common.nevermine;
import net.nevermine.event.player.KeyPressEvent;
import org.lwjgl.input.Keyboard;

public class FrontTextEvent {
	private static final String versionMsg = StringUtil.getLocaleStringWithArguments("gui.notice.version", nevermine.version);
	private static final String tipTitle = StringUtil.getLocaleString("gui.notice.tip.title");
	private static final String tipMsg = getTipMsg();
	private static String closeMsg;

	Minecraft mc;

	public FrontTextEvent() {
		mc = Minecraft.getMinecraft();
	}

	@SubscribeEvent
	public void onRender(final TickEvent.RenderTickEvent event) {
		onTickRender();
		noticeRender();
	}

	private void noticeRender() {
		if (mc.currentScreen == null && KeyPressEvent.ShowNotice) {
			closeMsg = StringUtil.getLocaleStringWithArguments("gui.notice.close", Keyboard.getKeyName(LoginNoticeBinding.lognot.getKeyCode()));
			final GuiIngame gig = mc.ingameGUI;
			final ScaledResolution scaledresolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
			final int i = scaledresolution.getScaledWidth();
			final int k = scaledresolution.getScaledHeight();
			mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/noticeBoard.png"));
			int x = i / 2 - i / 3;
			int y = 5;
			gig.drawTexturedModalRect(x - 5, y - 3, 0, 0, 255, 72);
			y += 13;
			Minecraft.getMinecraft().fontRenderer.drawString(versionMsg, x, y + 1, 0);
			Minecraft.getMinecraft().fontRenderer.drawString(versionMsg, x, y - 1, 0);
			Minecraft.getMinecraft().fontRenderer.drawString(versionMsg, x + 1, y, 0);
			Minecraft.getMinecraft().fontRenderer.drawString(versionMsg, x - 1, y, 0);
			Minecraft.getMinecraft().fontRenderer.drawString(versionMsg, x, y, 16777215);
			y += 18;
			x += 95;
			Minecraft.getMinecraft().fontRenderer.drawString(tipTitle, x, y + 1, 0);
			Minecraft.getMinecraft().fontRenderer.drawString(tipTitle, x, y - 1, 0);
			Minecraft.getMinecraft().fontRenderer.drawString(tipTitle, x + 1, y, 0);
			Minecraft.getMinecraft().fontRenderer.drawString(tipTitle, x - 1, y, 0);
			Minecraft.getMinecraft().fontRenderer.drawString(tipTitle, x, y, 51711);
			x -= 95;
			y += 12;
			Minecraft.getMinecraft().fontRenderer.drawString(tipMsg, x, y + 1, 0);
			Minecraft.getMinecraft().fontRenderer.drawString(tipMsg, x, y - 1, 0);
			Minecraft.getMinecraft().fontRenderer.drawString(tipMsg, x + 1, y, 0);
			Minecraft.getMinecraft().fontRenderer.drawString(tipMsg, x - 1, y, 0);
			Minecraft.getMinecraft().fontRenderer.drawString(tipMsg, x, y, 10551295);
			y += 15;
			x += 15;
			Minecraft.getMinecraft().fontRenderer.drawString(closeMsg, x, y + 1, 0);
			Minecraft.getMinecraft().fontRenderer.drawString(closeMsg, x, y - 1, 0);
			Minecraft.getMinecraft().fontRenderer.drawString(closeMsg, x + 1, y, 0);
			Minecraft.getMinecraft().fontRenderer.drawString(closeMsg, x - 1, y, 0);
			Minecraft.getMinecraft().fontRenderer.drawString(closeMsg, x, y, 16751259);
		}
	}

	private void onTickRender() {
		final ConfigurationHelper cfg = new ConfigurationHelper();
		if (mc.currentScreen == null) {
			if (KeyPressEvent.skills) {
				final int skill = KeyPressEvent.sshow;
				final ScaledResolution scaledresolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
				final int i = scaledresolution.getScaledWidth();

				int x = 0;
				int y = 0;
				String skillName = "";

				switch (skill) {
					case 1:
						x = i - ConfigurationHelper.innervationX - 15;
						y = ConfigurationHelper.innervationY + 25;
						skillName = StringUtil.getLocaleString("skills.innervation.name");
						break;
					case 2:
						x = i - ConfigurationHelper.robberyX - 7;
						y = ConfigurationHelper.robberyY + 25;
						skillName = StringUtil.getLocaleString("skills.robbery.name");
						break;
					case 3:
						x = i - ConfigurationHelper.runationX - 8;
						y = ConfigurationHelper.runationY + 25;
						skillName = StringUtil.getLocaleString("skills.runation.name");
						break;
					case 4:
						x = i - ConfigurationHelper.infusionX - 8;
						y = ConfigurationHelper.infusionY + 25;
						skillName = StringUtil.getLocaleString("skills.infusion.name");
						break;
					case 5:
						x = i - ConfigurationHelper.foragingX - 9;
						y = ConfigurationHelper.foragingY + 25;
						skillName = StringUtil.getLocaleString("skills.foraging.name");
						break;
					case 6:
						x = i - ConfigurationHelper.auguryX - 6;
						y = ConfigurationHelper.auguryY + 25;
						skillName = StringUtil.getLocaleString("skills.augury.name");
						break;
					case 7:
						x = i - ConfigurationHelper.creationskillX - 8;
						y = ConfigurationHelper.creationskillY + 25;
						skillName = StringUtil.getLocaleString("skills.creation.name");
						break;
					case 8:
						x = i - ConfigurationHelper.hunterX - 6;
						y = ConfigurationHelper.hunterY + 25;
						skillName = StringUtil.getLocaleString("skills.hunter.name");
						break;
					case 9:
						x = i - ConfigurationHelper.haulingX - 7;
						y = ConfigurationHelper.haulingY + 18;
						skillName = StringUtil.getLocaleString("skills.hauling.name");
						break;
					case 10:
						x = i - ConfigurationHelper.butcheryX - 8;
						y = ConfigurationHelper.butcheryY + 18;
						skillName = StringUtil.getLocaleString("skills.butchery.name");
						break;
					case 11:
						x = i - ConfigurationHelper.hermetismX - 10;
						y = ConfigurationHelper.hermetismY + 18;
						skillName = StringUtil.getLocaleString("skills.hermetism.name");
						break;
					case 12:
						x = i - ConfigurationHelper.loggingX - 7;
						y = ConfigurationHelper.loggingY + 18;
						skillName = StringUtil.getLocaleString("skills.logging.name");
						break;
					case 13:
						x = i - ConfigurationHelper.vulcanismX - 10;
						y = ConfigurationHelper.vulcanismY + 18;
						skillName = StringUtil.getLocaleString("skills.vulcanism.name");
						break;
					case 14:
						x = i - ConfigurationHelper.expeditionX - 13;
						y = ConfigurationHelper.expeditionY + 18;
						skillName = StringUtil.getLocaleString("skills.expedition.name");
						break;
					case 15:
						x = i - ConfigurationHelper.extractionX - 13;
						y = ConfigurationHelper.extractionY + 18;
						skillName = StringUtil.getLocaleString("skills.extraction.name");
						break;
					case 16:
						x = i - ConfigurationHelper.animaX - 1;
						y = ConfigurationHelper.animaY + 18;
						skillName = StringUtil.getLocaleString("skills.anima.name");
						break;
					default:
						break;
				}

				Minecraft.getMinecraft().fontRenderer.drawString(skillName, x, y + 1, 0);
				Minecraft.getMinecraft().fontRenderer.drawString(skillName, x, y - 1, 0);
				Minecraft.getMinecraft().fontRenderer.drawString(skillName, x + 1, y, 0);
				Minecraft.getMinecraft().fontRenderer.drawString(skillName, x - 1, y, 0);
				Minecraft.getMinecraft().fontRenderer.drawString(skillName, x, y, 16777215);
			}
			else if (KeyPressEvent.ShowMessage) {
				final ScaledResolution scaledresolution2 = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
				final int j = scaledresolution2.getScaledWidth();
				final int x2 = j - ConfigurationHelper.rageBarX;
				final int y2 = ConfigurationHelper.rageBarY + 26;
				String tip = StringUtil.getLocaleStringWithArguments("gui.skills.tip", Keyboard.getKeyName(SkillShowBinding.skills.getKeyCode()));
				Minecraft.getMinecraft().fontRenderer.drawString(tip, x2, y2 + 1, 0);
				Minecraft.getMinecraft().fontRenderer.drawString(tip, x2, y2 - 1, 0);
				Minecraft.getMinecraft().fontRenderer.drawString(tip, x2 + 1, y2, 0);
				Minecraft.getMinecraft().fontRenderer.drawString(tip, x2 - 1, y2, 0);
				Minecraft.getMinecraft().fontRenderer.drawString(tip, x2, y2, 16777215);
			}
		}
	}

	private static String getTipMsg() {
		if (WebRequest.isUpdateAvailable()) {
			return "New update available: " + WebRequest.webVer;
		}

		int pick = nevermine.rand.nextInt(13);

		switch (pick) {
			case 12:
				String randomDonator = WebRequest.getRandomDonator();

				if (randomDonator == null) {
					return StringUtil.getLocaleString("gui.notice.tip." + 1);
				}
				else {
					return "Thanks for donating, " + randomDonator + "!";
				}
			default:
				return StringUtil.getLocaleString("gui.notice.tip." + pick);
		}
	}
}
