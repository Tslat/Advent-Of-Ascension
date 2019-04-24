package net.tslat.aoa3.client.gui.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.tslat.aoa3.entity.properties.BossEntity;

import javax.annotation.Nullable;

public class BossBarRenderer {
	private final Minecraft mc;
	@Nullable
	public static BossEntity boss;

	public BossBarRenderer() {
		this.mc = Minecraft.getMinecraft();
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onRender(TickEvent.RenderTickEvent ev) {
		if (boss == null || mc.gameSettings.hideGUI || mc.currentScreen != null)
			return;

		EntityLivingBase bossEntity;

		try {
			bossEntity = (EntityLivingBase)boss;
		}
		catch (ClassCastException ex) {
			boss = null;

			return;
		}

		if (bossEntity.world == null || bossEntity.isDead || bossEntity.world != mc.player.world || boss.getBossBarTexture() == null) {
			boss = null;

			return;
		}

		GlStateManager.pushMatrix();
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		GlStateManager.disableDepth();
		final ScaledResolution res = new ScaledResolution(mc);
		final GuiIngame gui = mc.ingameGUI;
		final short xOffset = 182;
		final int x = res.getScaledWidth() / 2 - xOffset / 2;
		final int percentHealth = (int)(bossEntity.getHealth() / bossEntity.getMaxHealth() * (xOffset + 1));

		mc.getTextureManager().bindTexture(boss.getBossBarTexture());
		gui.drawTexturedModalRect(x, 5, 0, 0, xOffset + 1, 20);
		gui.drawTexturedModalRect(x, 5, 0, 20, xOffset + 1, 40);

		if (percentHealth > 0)
			gui.drawTexturedModalRect(x, 5, 0, 0, percentHealth, 20);

		GlStateManager.enableDepth();
		GlStateManager.popMatrix();
	}
}
