package net.nevermine.assist.binding;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;

public class SkillNameBinding {
	public static KeyBinding skillnamesleft;
	public static KeyBinding skillnamesright;

	public static void init() {
		ClientRegistry.registerKeyBinding(SkillNameBinding.skillnamesleft = new KeyBinding("key.skillnameleft", 203, "key.categories.gameplay"));
		ClientRegistry.registerKeyBinding(SkillNameBinding.skillnamesright = new KeyBinding("key.skillnameright", 205, "key.categories.gameplay"));
	}
}
