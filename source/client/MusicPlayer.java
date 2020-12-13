package net.tslat.aoa3.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.Logging;
import org.apache.logging.log4j.Level;

import java.util.HashMap;

public final class MusicPlayer {
	private static final HashMap<ResourceLocation, ISound> playingSounds = new HashMap<ResourceLocation, ISound>();

	public static void playMusic(ResourceLocation id) {
		if (playingSounds.containsKey(id)) {
			if (!Minecraft.getInstance().getSoundHandler().isPlaying(playingSounds.get(id))) {
				playingSounds.remove(id);
			}
			else {
				return;
			}
		}

		SoundEvent soundEvent = ForgeRegistries.SOUND_EVENTS.getValue(id);

		if (soundEvent == null) {
			Logging.logMessage(Level.DEBUG, "Unable to find sound event with ID: " + id.toString());

			return;
		}

		ISound sound = SimpleSound.music(soundEvent);
		Minecraft.getInstance().getSoundHandler().play(sound);
		playingSounds.put(id, sound);
	}

	public static void stopMusic(ResourceLocation id) {
		ISound sound = playingSounds.get(id);

		if (sound != null) {
			SoundHandler soundHandler = Minecraft.getInstance().getSoundHandler();

			if (soundHandler.isPlaying(sound))
				soundHandler.stop(sound);

			playingSounds.remove(id);
		}
	}
}
