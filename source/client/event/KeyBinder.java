package net.tslat.aoa3.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.gui.mainwindow.AdventMainGui;
@SideOnly(Side.CLIENT)
public class KeyBinder {
	public static KeyBinding keyCreatureStats;
	public static KeyBinding keyResourceGui;
	public static KeyBinding keySkillGui;
	public static KeyBinding keyAdventGui;

	public static boolean statusCreatureStats = true;
	public static boolean statusResourceGui = false;
	public static boolean statusSkillGui = false;
	public static boolean statusResourceGuiMessage = true;
	public static boolean statusSkillGuiMessage = true;

	public static void init() {
		ClientRegistry.registerKeyBinding(keyCreatureStats = new KeyBinding("key.cstats", 0, "key.categories.advent"));
		ClientRegistry.registerKeyBinding(keyResourceGui = new KeyBinding("key.resources", 24, "key.categories.advent"));
		ClientRegistry.registerKeyBinding(keySkillGui = new KeyBinding("key.skills", 0, "key.categories.advent"));
		ClientRegistry.registerKeyBinding(keyAdventGui = new KeyBinding("key.adventGui", 211, "key.categories.advent"));
	}

	@SubscribeEvent
	public void onKeyDown(final InputEvent.KeyInputEvent ev) {
		if (keyCreatureStats.isPressed())
			statusCreatureStats = !statusCreatureStats;

		if (keyResourceGui.isPressed()) {
			statusResourceGui = !statusResourceGui;
			statusResourceGuiMessage = false;
		}

		if (keySkillGui.isPressed()) {
			statusSkillGui = !statusSkillGui;
			statusSkillGuiMessage = false;
		}

		if (keyAdventGui.isPressed() && Minecraft.getMinecraft().player != null) {
			if (Minecraft.getMinecraft().currentScreen instanceof AdventMainGui) {
				Minecraft.getMinecraft().displayGuiScreen(null);
			}
			else if (Minecraft.getMinecraft().currentScreen == null) {
				Minecraft.getMinecraft().displayGuiScreen(new AdventMainGui(Minecraft.getMinecraft().player));
			}
		}
	}
}
