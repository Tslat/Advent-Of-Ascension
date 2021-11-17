package net.tslat.aoa3.scheduling.sync;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.RandomUtil;

import java.util.UUID;

public class RuneCreationTask implements Runnable {
	private final ServerWorld level;
	private final BlockPos pos;
	private final Item rune;
	private final int count;
	private final UUID owner;

	private int ticker;

	public RuneCreationTask(ServerWorld level, BlockPos pos, Item rune, int count, UUID owner) {
		this.level = level;
		this.pos = pos;
		this.rune = rune;
		this.count = count;
		this.owner = owner;
		this.ticker = 20 * (3 + count / 10);
	}

	@Override
	public void run() {
		ticker -= 5;

		if (ticker > 0) {
			level.sendParticles(ParticleTypes.END_ROD, pos.getX() + 0.5d + RandomUtil.randomGaussianValue() * 0.1d, pos.getY() + 1d + (0.85 - (0.85 * ticker / (20 * (3 + count / 10f)))), pos.getZ() + 0.5d + RandomUtil.randomGaussianValue() * 0.1d, 1, 0, 0, 0, 0.01d);

			AoAScheduler.scheduleSyncronisedTask(this, 5);
		}
		else {
			for (int spawned = 0; spawned < count;) {
				ItemEntity entity = new ItemEntity(level, pos.getX() + 0.5f, pos.getY() + 1.85f, pos.getZ() + 0.5f);

				entity.setDeltaMovement(0, 0, 0);
				entity.setOwner(this.owner);
				entity.setNoPickUpDelay();
				entity.setNoGravity(true);
				entity.setItem(new ItemStack(rune, Math.min(64, count)));
				level.addFreshEntity(entity);

				spawned += entity.getItem().getCount();
			}
		}
	}
}
