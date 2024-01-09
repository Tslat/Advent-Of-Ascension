package net.tslat.aoa3.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.neoforged.neoforge.common.NeoForge;
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
		NeoForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, InputEvent.Key.class, AoAKeybinds::onKeyDown);

		AdventOfAscension.getModEventBus().addListener(EventPriority.NORMAL, false, RegisterKeyMappingsEvent.class, ev -> {
			ev.register(RESOURCE_GUI = new KeyMapping(keyName("resources"), KeyConflictContext.IN_GAME, getKey(GLFW.GLFW_KEY_O), CATEGORY));
			ev.register(SKILL_GUI = new KeyMapping(keyName("skills"), KeyConflictContext.IN_GAME, getKey(GLFW.GLFW_KEY_UNKNOWN), CATEGORY));
			ev.register(ADVENT_GUI = new KeyMapping(keyName("adventGui"), KeyConflictContext.IN_GAME, getKey(GLFW.GLFW_KEY_DELETE), CATEGORY));
			ev.register(ABILITY_ACTION = new KeyMapping(keyName("abilityAction"), KeyConflictContext.IN_GAME, getKey(GLFW.GLFW_KEY_V), CATEGORY));
		});
	}

	private static String keyName(String id) {
		return "key." + AdventOfAscension.MOD_ID + "." + id;
	}

	private static InputConstants.Key getKey(int keyCode) {
		return InputConstants.Type.KEYSYM.getOrCreate(keyCode);
	}

	private static void onKeyDown(final InputEvent.Key ev) {
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
