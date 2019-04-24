package net.nevermine.assist.binding;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;

public class CreatureInfoBinding {
	public static KeyBinding cinfo;

	public static void init() {

		ClientRegistry.registerKeyBinding(CreatureInfoBinding.cinfo = new KeyBinding("key.cinfo", 49, "key.categories.gameplay"));
	}
}
