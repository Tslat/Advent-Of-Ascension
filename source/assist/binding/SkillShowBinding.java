package net.nevermine.assist.binding;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;

public class SkillShowBinding {
	public static KeyBinding skills;

	public static void init() {
		ClientRegistry.registerKeyBinding(SkillShowBinding.skills = new KeyBinding("key.skills", 23, "key.categories.gameplay"));
	}
}
