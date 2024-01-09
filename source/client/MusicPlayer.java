package net.tslat.aoa3.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.common.registration.AoARegistries;
import org.apache.logging.log4j.Level;

import java.util.HashMap;

public final class MusicPlayer {
	private static final HashMap<ResourceLocation, SoundInstance> playingSounds = new HashMap<ResourceLocation, SoundInstance>();

	public static void playMusic(ResourceLocation id) {
		if (playingSounds.containsKey(id)) {
			if (!Minecraft.getInstance().getSoundManager().isActive(playingSounds.get(id))) {
				playingSounds.remove(id);
			}
			else {
				return;
			}
		}

		SoundEvent soundEvent = AoARegistries.SOUNDS.getEntry(id);

		if (soundEvent == null) {
			Logging.logMessage(Level.DEBUG, "Unable to find sound event with ID: " + id.toString());

			return;
		}

		SoundInstance sound = SimpleSoundInstance.forMusic(soundEvent);
		Minecraft.getInstance().getSoundManager().play(sound);
		playingSounds.put(id, sound);
	}

	public static void stopMusic(ResourceLocation id) {
		SoundInstance sound = playingSounds.get(id);

		if (sound != null) {
			SoundManager soundHandler = Minecraft.getInstance().getSoundManager();

			if (soundHandler.isActive(sound))
				soundHandler.stop(sound);

			playingSounds.remove(id);
		}
	}
}
