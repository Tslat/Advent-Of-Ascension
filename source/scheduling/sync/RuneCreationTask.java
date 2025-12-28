package net.tslat.aoa3.scheduling.sync;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.tme.api.util.RandomUtil;
import net.tslat.tme.api.scheduling.TickScheduler;

public class RuneCreationTask implements TickScheduler.Task {
	private final ServerLevel level;
	private final BlockPos pos;
	private final Item rune;
	private final int count;
	private final Player owner;

	private int ticker;

	public RuneCreationTask(ServerLevel level, BlockPos pos, Item rune, int count, Player owner) {
		this.level = level;
		this.pos = pos;
		this.rune = rune;
		this.count = count;
		this.owner = owner;
		this.ticker = 20 * (3 + count / 10);
	}

	@Override
	public void run(int tick) {
		ticker -= 5;

		if (ticker > 0) {
			level.sendParticles(ParticleTypes.END_ROD, pos.getX() + 0.5d + RandomUtil.gaussianValue() * 0.1d, pos.getY() + 1d + (0.85 - (0.85 * ticker / (20 * (3 + count / 10f)))), pos.getZ() + 0.5d + RandomUtil.gaussianValue() * 0.1d, 1, 0, 0, 0, 0.01d);

			AoAScheduler.schedule(5, this);
		}
		else {
			for (int spawned = 0; spawned < count;) {
				ItemEntity entity = new ItemEntity(level, pos.getX() + 0.5f, pos.getY() + 1.85f, pos.getZ() + 0.5f, new ItemStack(rune, Math.min(64, count)));

				entity.setDeltaMovement(0, 0, 0);
				entity.setThrower(this.owner);
				entity.setNoPickUpDelay();
				entity.setNoGravity(true);
				level.addFreshEntity(entity);

				spawned += entity.getItem().getCount();
			}
		}
	}
}
