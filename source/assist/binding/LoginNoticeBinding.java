package net.nevermine.assist.binding;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;

public class LoginNoticeBinding {
	public static KeyBinding lognot;

	public static void init() {
		ClientRegistry.registerKeyBinding(LoginNoticeBinding.lognot = new KeyBinding("key.lognot", 210, "key.categories.gameplay"));
	}
}
