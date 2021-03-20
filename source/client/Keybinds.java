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
	public static KeyBinding RESOURCE_GUI;
	public static KeyBinding SKILL_GUI;
	public static KeyBinding ADVENT_GUI;

	public static boolean statusResourceGui = false;
	public static boolean statusSkillGui = false;
	public static boolean statusResourceGuiMessage = true;
	public static boolean statusSkillGuiMessage = true;

	public static void registerKeybinds() {
		ClientRegistry.registerKeyBinding(RESOURCE_GUI = new KeyBinding("key.aoa3.resources", GLFW.GLFW_KEY_O, "key.categories.aoa3"));
		ClientRegistry.registerKeyBinding(SKILL_GUI = new KeyBinding("key.aoa3.skills", -1, "key.categories.aoa3"));
		ClientRegistry.registerKeyBinding(ADVENT_GUI = new KeyBinding("key.aoa3.adventGui", GLFW.GLFW_KEY_DELETE, "key.categories.aoa3"));
	}

	@SubscribeEvent
	public static void onKeyDown(final InputEvent.KeyInputEvent ev) {
		if (RESOURCE_GUI.consumeClick()) {
			statusResourceGui = !statusResourceGui;
			statusResourceGuiMessage = false;
		}

		if (SKILL_GUI.consumeClick()) {
			statusSkillGui = !statusSkillGui;
			statusSkillGuiMessage = false;
		}

		Minecraft mc = Minecraft.getInstance();

		if (ADVENT_GUI.consumeClick() && mc.player != null) {
			if (mc.screen instanceof AdventMainGui) {
				mc.setScreen(null);
			}
			else if (mc.screen == null) {
				mc.setScreen(new AdventMainGui(mc.player));
			}
		}
	}
}
