package net.tslat.aoa3.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.gui.adventgui.AdventMainGui;
import net.tslat.aoa3.player.ClientPlayerDataManager;
import org.lwjgl.glfw.GLFW;

public class AoAKeybinds {
	private static final String CATEGORY = "key.categories." + AdventOfAscension.MOD_ID;

	public static KeyMapping RESOURCE_GUI;
	public static KeyMapping SKILL_GUI;
	public static KeyMapping ADVENT_GUI;
	public static KeyMapping ABILITY_ACTION;

	public static boolean statusResourceGui = false;
	public static boolean statusSkillGui = false;
	public static boolean statusResourceGuiMessage = true;
	public static boolean statusSkillGuiMessage = true;

	public static void init() {
		MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, InputEvent.KeyInputEvent.class, AoAKeybinds::onKeyDown);
	}

	public static void registerKeybinds() {
		ClientRegistry.registerKeyBinding(RESOURCE_GUI = new KeyMapping(keyName("resources"), KeyConflictContext.IN_GAME, getKey(GLFW.GLFW_KEY_O), CATEGORY));
		ClientRegistry.registerKeyBinding(SKILL_GUI = new KeyMapping(keyName("skills"), KeyConflictContext.IN_GAME, getKey(GLFW.GLFW_KEY_UNKNOWN), CATEGORY));
		ClientRegistry.registerKeyBinding(ADVENT_GUI = new KeyMapping(keyName("adventGui"), KeyConflictContext.IN_GAME, getKey(GLFW.GLFW_KEY_DELETE), CATEGORY));
		ClientRegistry.registerKeyBinding(ABILITY_ACTION = new KeyMapping(keyName("abilityAction"), KeyConflictContext.IN_GAME, getKey(GLFW.GLFW_KEY_V), CATEGORY));
	}

	private static String keyName(String id) {
		return "key." + AdventOfAscension.MOD_ID + "." + id;
	}

	private static InputConstants.Key getKey(int keyCode) {
		return InputConstants.Type.KEYSYM.getOrCreate(keyCode);
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
				mc.setScreen(new AdventMainGui(Minecraft.getInstance().player));
			}
		}

		if (ev.getAction() == GLFW.GLFW_PRESS && Minecraft.getInstance().screen == null)
			ClientPlayerDataManager.get().handleKeyInput(ev.getKey());
	}
}
