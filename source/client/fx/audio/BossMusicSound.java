package net.tslat.aoa3.client.fx.audio;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.entity.properties.BossEntity;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class BossMusicSound extends MovingSound {
	@Nullable
	private static BossMusicSound currentSound = null;
	private final EntityPlayerSP player;
	private final EntityLivingBase currentBoss;

	public BossMusicSound(SoundEvent music, BossEntity boss) {
		this(music, boss, 0);
	}

	public BossMusicSound(SoundEvent music, BossEntity boss, int repeatDelay) {
		super(music, SoundCategory.MUSIC);
		if (boss instanceof EntityLivingBase && ((EntityLivingBase)boss).isEntityAlive()) {
			this.currentBoss = (EntityLivingBase)boss;
		}
		else {
			this.currentBoss = null;
			this.donePlaying = true;
		}

		this.player = Minecraft.getMinecraft().player;
		this.attenuationType = AttenuationType.NONE;
		this.repeat = true;
		this.repeatDelay = repeatDelay;
		this.volume = 0.75f;
	}

	public static boolean isAvailable() {
		return currentSound == null || currentSound.donePlaying || currentSound.currentBoss == null || !currentSound.currentBoss.isEntityAlive();
	}

	@Override
	public void update() {
		if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().gameSettings.getSoundLevel(SoundCategory.MUSIC) <= 0) {
			this.donePlaying = true;
			this.volume = 0;

			return;
		}

		if (currentBoss != null) {
			if (currentBoss.isEntityAlive() && currentBoss.world == player.world && player.isEntityAlive()) {
				currentSound = this;

				this.xPosF = (float)player.posX;
				this.yPosF = (float)player.posY;
				this.zPosF = (float)player.posZ;

				return;
			}
		}

		this.donePlaying = true;
		currentSound = null;
	}
}
