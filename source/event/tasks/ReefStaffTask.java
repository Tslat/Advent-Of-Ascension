package net.nevermine.event.tasks;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.nevermine.event.player.Ticker;
import net.nevermine.izer.Blockizer;

import java.util.concurrent.TimeUnit;

public class ReefStaffTask implements Runnable {
	private int ticker;

	private World world;
	private double startX;
	private double startY;
	private double startZ;

	public ReefStaffTask(World w, double posX, double posY, double posZ) {
		world = w;
		startX = posX;
		startY = posY;
		startZ = posZ;
	}

	@Override
	public void run() {
		for (int x = (int)startX - 2; x < (int)startX + 2; x++) {
			for (int z = (int)startZ - 2; z < (int)startZ + 3; z++) {
				if (world.getBlock(x, (int)startY - 2, z) == Blockizer.CoralOrange) {
					world.setBlock(x, (int)startY - 2, z, Blocks.air);
				}

				if (world.getBlock(x, (int)startY + 2, z) == Blockizer.CoralOrange) {
					world.setBlock(x, (int)startY + 2, z, Blocks.air);
				}
			}

			for (int y = (int)startY - 2; y < (int)startY + 3; y++) {
				if (world.getBlock(x, y, (int)startZ - 2) == Blockizer.CoralOrange) {
					world.setBlock(x, y, (int)startZ - 2, Blocks.air);
				}

				if (world.getBlock(x, y, (int)startZ + 2) == Blockizer.CoralOrange) {
					world.setBlock(x, y, (int)startZ + 2, Blocks.air);
				}
			}
		}

		for (int z = (int)startZ - 2; z < (int)startZ + 3; z++) {
			for (int y = (int)startY - 2; y < (int)startY + 3; y++) {
				if (world.getBlock((int)startX - 2, y, z) == Blockizer.CoralOrange) {
					world.setBlock((int)startX - 2, y, z, Blocks.air);
				}

				if (world.getBlock((int)startX + 2, y, z) == Blockizer.CoralOrange) {
					world.setBlock((int)startX + 2, y, z, Blocks.air);
				}
			}
		}
	}

	public void schedule(Integer time, TimeUnit units) {
		Ticker.scheduleRequiredTask(this, time, units);
	}
}
