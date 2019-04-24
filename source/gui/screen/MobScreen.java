package net.nevermine.gui.screen;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

public class MobScreen {
	public static int showTicks;
	public static int image;
	private static ScaledResolution res;

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void renderOverlay(final RenderGameOverlayEvent.Post event) {
		if (event.isCanceled() || event.type != RenderGameOverlayEvent.ElementType.HELMET) {
			return;
		}
		if (MobScreen.showTicks > 0) {
			drawOverlay();
		}
	}

	public static void drawOverlay() {
		MobScreen.res = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
		GL11.glDisable(2929);
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		GL11.glDisable(3008);
		switch (MobScreen.image) {
			case 1:
				Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/scratchedScreen.png"));
				break;
			case 2:
				Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/bloodyScreen.png"));
				break;
			case 3:
				Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/static.png"));
				break;
			case 4:
				Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/grillfaceAppear.png"));
				break;
			case 5:
				Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/darkScreen.png"));
				break;
			case 6:
				Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/eilosapienFace.png"));
				break;
			case 7:
				Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/purpleFog.png"));
				break;
			case 8:
				Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/circles.png"));
				break;
			case 9:
				Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/coniferonBlock.png"));
				break;
			case 10:
				Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/circlesSpikey.png"));
				break;
			case 11:
				Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/shyreDizzy.png"));
				break;
			case 12:
				Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/shyreBlind.png"));
				break;
			case 13:
				Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/lightwalkerBlind.png"));
				break;
		}

		final Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(0.0, (double)MobScreen.res.getScaledHeight(), -90.0, 0.0, 1.0);
		tessellator.addVertexWithUV((double)MobScreen.res.getScaledWidth(), (double)MobScreen.res.getScaledHeight(), -90.0, 1.0, 1.0);
		tessellator.addVertexWithUV((double)MobScreen.res.getScaledWidth(), 0.0, -90.0, 1.0, 0.0);
		tessellator.addVertexWithUV(0.0, 0.0, -90.0, 0.0, 0.0);
		tessellator.draw();
		GL11.glDepthMask(true);
		GL11.glEnable(2929);
		GL11.glEnable(3008);
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
	}

	static {
		MobScreen.showTicks = 0;
	}
}
