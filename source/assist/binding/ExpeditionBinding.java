package net.nevermine.assist.binding;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;

public class ExpeditionBinding {
	public static KeyBinding exped;

	public static void init() {
		ClientRegistry.registerKeyBinding(ExpeditionBinding.exped = new KeyBinding("key.exped", 200, "key.categories.gameplay"));
	}
}
