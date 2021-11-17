package net.tslat.aoa3.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.gui.adventgui.AdventMainGui;
import net.tslat.aoa3.player.ClientPlayerDataManager;
import org.lwjgl.glfw.GLFW;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = AdventOfAscension.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
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

		if (ev.getAction() == GLFW.GLFW_PRESS && Minecraft.getInstance().screen == null)
			ClientPlayerDataManager.handleKeyInput(ev.getKey());
	}
}
