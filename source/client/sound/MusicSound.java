package net.tslat.aoa3.client.sound;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class MusicSound extends MovingSound {
	private static final SoundHandler soundHandler = Minecraft.getMinecraft().getSoundHandler();
	private static MusicSound currentlyPlaying = null;

	private EntityPlayerSP player = null;
	private Entity linkedEntity = null;

	public MusicSound(SoundEvent soundEvent, Entity linkedEntity) {
		super(soundEvent, SoundCategory.MUSIC);

		this.linkedEntity = linkedEntity;
	}

	protected MusicSound(SoundEvent soundEvent) {
		super(soundEvent, SoundCategory.MUSIC);
	}

	@Override
	public void update() {
		if (player == null)
			player = Minecraft.getMinecraft().player;

		if (currentlyPlaying != this) {
			if (currentlyPlaying != null)
				currentlyPlaying.donePlaying = true;

			currentlyPlaying = this;
		}

		xPosF = (float)player.posX;
		yPosF = (float)player.posY;
		zPosF = (float)player.posZ;
	}

	@Override
	public boolean isDonePlaying() {
		if (donePlaying || !soundHandler.isSoundPlaying(this) || (linkedEntity != null && linkedEntity.isDead)) {
			if (currentlyPlaying != null)
				currentlyPlaying.donePlaying = true;

			currentlyPlaying = null;

			return true;
		}

		return false;
	}

	public static boolean currentlyBlockingMusic() {
		return currentlyPlaying != null && !currentlyPlaying.isDonePlaying();
	}

	public static boolean isPlaying(SoundEvent soundEvent, @Nullable Entity linkedEntity) {
		return currentlyPlaying != null && currentlyPlaying.getSoundLocation().equals(soundEvent.getSoundName()) && (linkedEntity == null ? currentlyPlaying.linkedEntity == null : currentlyPlaying.linkedEntity != null && currentlyPlaying.linkedEntity.getClass() == linkedEntity.getClass());
	}
}
