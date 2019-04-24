package net.nevermine.resource.boss;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class bossBarRenderer {
	Minecraft mc;

	public bossBarRenderer() {
		mc = Minecraft.getMinecraft();
	}

	@SubscribeEvent
	public void onRender(final TickEvent.RenderTickEvent event) {
		onTickRender();
	}

	private void onTickRender() {
		final ResourceLocation r = null;
		if (EternalBossStatus.statusBarTime > 0) {
			--EternalBossStatus.statusBarTime;
			final FontRenderer fontrenderer = mc.fontRenderer;
			final ScaledResolution scaledresolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
			final GuiIngame gig = mc.ingameGUI;
			final int i = scaledresolution.getScaledWidth();
			final short short1 = 182;
			final int j = i / 2 - short1 / 2;
			final int k = (int)(EternalBossStatus.healthScale * (short1 + 1));
			final byte b0 = 5;
			if (EternalBossStatus.selected == 1) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/smashbar.png"));
			}
			else if (EternalBossStatus.selected == 2) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/kingbambambambar.png"));
			}
			else if (EternalBossStatus.selected == 3) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/nethengeicwitherbar.png"));
			}
			else if (EternalBossStatus.selected == 4) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/rockriderbar.png"));
			}
			else if (EternalBossStatus.selected == 5) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/guardianredbar.png"));
			}
			else if (EternalBossStatus.selected == 6) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/guardianyellowbar.png"));
			}
			else if (EternalBossStatus.selected == 7) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/guardiangreenbar.png"));
			}
			else if (EternalBossStatus.selected == 8) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/guardianbluebar.png"));
			}
			else if (EternalBossStatus.selected == 9) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/shadowlordbar.png"));
			}
			else if (EternalBossStatus.selected == 10) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/elusivebar.png"));
			}
			else if (EternalBossStatus.selected == 11) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/skeletronbar.png"));
			}
			else if (EternalBossStatus.selected == 12) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/corallusbar.png"));
			}
			else if (EternalBossStatus.selected == 13) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/shroomusbar.png"));
			}
			else if (EternalBossStatus.selected == 14) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/silverfootbar.png"));
			}
			else if (EternalBossStatus.selected == 15) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/visualentbar.png"));
			}
			else if (EternalBossStatus.selected == 16) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/banebar.png"));
			}
			else if (EternalBossStatus.selected == 17) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/krorbar.png"));
			}
			else if (EternalBossStatus.selected == 18) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/vinocornebar.png"));
			}
			else if (EternalBossStatus.selected == 19) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/clunkheadbar.png"));
			}
			else if (EternalBossStatus.selected == 20) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/hivekingbar.png"));
			}
			else if (EternalBossStatus.selected == 21) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/dracyonbar.png"));
			}
			else if (EternalBossStatus.selected == 22) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/necromancerbar.png"));
			}
			else if (EternalBossStatus.selected == 23) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/miskelbar.png"));
			}
			else if (EternalBossStatus.selected == 24) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/raxxanbar.png"));
			}
			else if (EternalBossStatus.selected == 25) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/kajarosbar.png"));
			}
			else if (EternalBossStatus.selected == 26) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/harkosbar.png"));
			}
			else if (EternalBossStatus.selected == 27) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/okazorbar.png"));
			}
			else if (EternalBossStatus.selected == 28) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/voxxulonbar.png"));
			}
			else if (EternalBossStatus.selected == 29) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/runicbar.png"));
			}
			else if (EternalBossStatus.selected == 30) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/hydroliskbar.png"));
			}
			else if (EternalBossStatus.selected == 31) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/hydroliskarmorbar.png"));
			}
			else if (EternalBossStatus.selected == 32) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/grawbar.png"));
			}
			else if (EternalBossStatus.selected == 33) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/horonbar.png"));
			}
			else if (EternalBossStatus.selected == 34) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/coniferonbar.png"));
			}
			else if (EternalBossStatus.selected == 35) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/penumbrabar.png"));
			}
			else if (EternalBossStatus.selected == 36) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/goldorthbar.png"));
			}
			else if (EternalBossStatus.selected == 37) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/gyrobar.png"));
			}
			else if (EternalBossStatus.selected == 38) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/crystocorebar.png"));
			}
			else if (EternalBossStatus.selected == 39) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/cottoncandorbar.png"));
			}
			else if (EternalBossStatus.selected == 40) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/creepbar.png"));
			}
			else if (EternalBossStatus.selected == 41) {
				this.mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/craexxeusbar.png"));
			}
			else if (EternalBossStatus.selected == 42) {
				this.mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/xxeusbar.png"));
			}
			else if (EternalBossStatus.selected == 43) {
				this.mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/tyrosaurbar.png"));
			}
			else if (EternalBossStatus.selected == 44) {
				this.mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/mechbotbar.png"));
			}
			else if (EternalBossStatus.selected == 45) {
				this.mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/baronessbar.png"));
			}

			gig.drawTexturedModalRect(j, (int)b0, 0, 0, short1 + 1, 20);
			gig.drawTexturedModalRect(j, (int)b0, 0, 20, short1 + 1, 40);
			if (k > 0) {
				gig.drawTexturedModalRect(j, (int)b0, 0, 0, k, 20);
			}
			GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		}
	}

	public ResourceLocation set(final String name) {
		return new ResourceLocation("nevermine:textures/gui/smashbar.png");
	}
}
