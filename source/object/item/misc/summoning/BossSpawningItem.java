package net.tslat.aoa3.object.item.misc.summoning;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.object.entity.misc.BossItemEntity;
import net.tslat.aoa3.util.RandomUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Supplier;

public abstract class BossSpawningItem extends Item {
	private final IParticleData[] timerParticles;
	private final Supplier<SoundEvent> throwingSound;

	public BossSpawningItem(@Nullable Supplier<SoundEvent> throwSound, @Nonnull IParticleData... timerParticles) {
		super(new Item.Properties().tab(AoAItemGroups.MISC_ITEMS).rarity(Rarity.UNCOMMON));

		this.timerParticles = timerParticles;
		this.throwingSound = throwSound;
	}

	public void handleTimerParticles(ItemEntity entityItem, double posX, double posY, double posZ, int lifespan, int ticksExisted) {
		int index = (int)(ticksExisted / (float)lifespan * timerParticles.length);

		if (RandomUtil.oneInNChance(1 + (lifespan - ticksExisted) / 20))
			entityItem.level.addParticle(timerParticles[index], posX, posY + 0.25d, posZ, 0, 0, 0);
	}

	public abstract void spawnBoss(World world, ServerPlayerEntity summoner, double posX, double posY, double posZ);

	public abstract boolean canSpawnHere(World world, ServerPlayerEntity player, double posX, double posY, double posZ);

	@Nullable
	public SoundEvent getThrowingSound() {
		return throwingSound.get();
	}

	public static BossItemEntity newBossEntityItemFromExisting(ItemEntity item, PlayerEntity player) {
		BossItemEntity bossItem = new BossItemEntity(item.level, item.getX(), item.getY(), item.getZ(), item.getItem(), player);

		bossItem.setPickUpDelay(10);
		bossItem.setThrower(player.getUUID());
		bossItem.setOwner(player.getUUID());
		bossItem.setDeltaMovement(item.getDeltaMovement());

		return bossItem;
	}
}
