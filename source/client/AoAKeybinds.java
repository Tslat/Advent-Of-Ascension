package net.tslat.aoa3.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.gui.adventgui.AdventMainGui;
import net.tslat.aoa3.player.ClientPlayerDataManager;
import org.lwjgl.glfw.GLFW;

public class AoAKeybinds {
	private static final String CATEGORY = "key.categories." + AdventOfAscension.MOD_ID;

	public static KeyBinding RESOURCE_GUI;
	public static KeyBinding SKILL_GUI;
	public static KeyBinding ADVENT_GUI;
	public static KeyBinding ABILITY_ACTION;

	public static boolean statusResourceGui = false;
	public static boolean statusSkillGui = false;
	public static boolean statusResourceGuiMessage = true;
	public static boolean statusSkillGuiMessage = true;

	public static void init() {
		MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, InputEvent.KeyInputEvent.class, AoAKeybinds::onKeyDown);
	}

	public static void registerKeybinds() {
		ClientRegistry.registerKeyBinding(RESOURCE_GUI = new KeyBinding(keyName("resources"), KeyConflictContext.IN_GAME, getKey(GLFW.GLFW_KEY_O), CATEGORY));
		ClientRegistry.registerKeyBinding(SKILL_GUI = new KeyBinding(keyName("skills"), KeyConflictContext.IN_GAME, getKey(GLFW.GLFW_KEY_UNKNOWN), CATEGORY));
		ClientRegistry.registerKeyBinding(ADVENT_GUI = new KeyBinding(keyName("adventGui"), KeyConflictContext.IN_GAME, getKey(GLFW.GLFW_KEY_DELETE), CATEGORY));
		ClientRegistry.registerKeyBinding(ABILITY_ACTION = new KeyBinding(keyName("abilityAction"), KeyConflictContext.IN_GAME, getKey(GLFW.GLFW_KEY_V), CATEGORY));
	}

	private static String keyName(String id) {
		return "key." + AdventOfAscension.MOD_ID + "." + id;
	}

	private static InputMappings.Input getKey(int keyCode) {
		return InputMappings.Type.KEYSYM.getOrCreate(keyCode);
	}

	private static void onKeyDown(final InputEvent.KeyInputEvent ev) {
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

		if (ev.getAction() == GLFW.GLFW_PRESS && Minecraft.getInstance().screen == null)
			ClientPlayerDataManager.get().handleKeyInput(ev.getKey());
	}
}
