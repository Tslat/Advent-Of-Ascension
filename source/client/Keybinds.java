package net.tslat.aoa3.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.gui.adventgui.AdventMainGui;
import org.lwjgl.glfw.GLFW;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = AdventOfAscension.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class Keybinds {
	public static KeyBinding keyResourceGui;
	public static KeyBinding keySkillGui;
	public static KeyBinding keyAdventGui;

	public static boolean statusResourceGui = false;
	public static boolean statusSkillGui = false;
	public static boolean statusResourceGuiMessage = true;
	public static boolean statusSkillGuiMessage = true;

	public static void registerKeybinds() {
		ClientRegistry.registerKeyBinding(keyResourceGui = new KeyBinding("key.aoa3.resources", GLFW.GLFW_KEY_O, "key.categories.aoa3"));
		ClientRegistry.registerKeyBinding(keySkillGui = new KeyBinding("key.aoa3.skills", -1, "key.categories.aoa3"));
		ClientRegistry.registerKeyBinding(keyAdventGui = new KeyBinding("key.aoa3.adventGui", GLFW.GLFW_KEY_DELETE, "key.categories.aoa3"));
	}

	@SubscribeEvent
	public static void onKeyDown(final InputEvent.KeyInputEvent ev) {
		if (keyResourceGui.isPressed()) {
			statusResourceGui = !statusResourceGui;
			statusResourceGuiMessage = false;
		}

		if (keySkillGui.isPressed()) {
			statusSkillGui = !statusSkillGui;
			statusSkillGuiMessage = false;
		}

		Minecraft mc = Minecraft.getInstance();

		if (keyAdventGui.isPressed() && mc.player != null) {
			if (mc.currentScreen instanceof AdventMainGui) {
				mc.displayGuiScreen(null);
			}
			else if (mc.currentScreen == null) {
				mc.displayGuiScreen(new AdventMainGui(mc.player));
			}
		}
	}
}
