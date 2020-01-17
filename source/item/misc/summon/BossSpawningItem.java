package net.tslat.aoa3.item.misc.summon;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.item.misc.SimpleItem;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class BossSpawningItem extends SimpleItem {
	private final EnumParticleTypes[] timerParticles;
	private final SoundEvent throwingSound;

	public BossSpawningItem(String name, String registryName, @Nullable SoundEvent throwSound, @Nonnull EnumParticleTypes... timerParticles) {
		super(name, registryName);

		this.timerParticles = timerParticles;
		this.throwingSound = throwSound;
	}

	public void handleTimerParticles(EntityItem entityItem, double posX, double posY, double posZ, int lifespan, int ticksExisted) {
		int index = (int)(ticksExisted / (float)lifespan * timerParticles.length);

		if (itemRand.nextInt(1 + (lifespan - ticksExisted) / 20) == 0)
			entityItem.world.spawnParticle(timerParticles[index], posX, posY + 0.25d, posZ, 0, 0, 0);
	}

	public abstract void spawnBoss(World world, EntityPlayer summoner, double posX, double posY, double posZ);

	public abstract boolean canSpawnHere(World world, EntityPlayer player, double posX, double posY, double posZ);

	public SoundEvent getThrowingSound() {
		return throwingSound;
	}
}
