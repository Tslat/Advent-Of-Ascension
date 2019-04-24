package net.nevermine.event.creature;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BossClear {
	public static void clear(final int x, final int y, final int z, final World w, final EntityLivingBase e) {
		final EntityPlayer var1 = w.getClosestVulnerablePlayerToEntity(e, 40.0);
		int count = 0;

		if (var1 == null || var1.getDistanceToEntity(e) > 40.0f)
			return;

		if (!w.isRemote) {
			for (int i = x - 10; i < x + 5; ++i) {
				for (int j = y - 3; j < y + 5; ++j) {
					for (int k = z - 10; k < z + 5; ++k) {
						if (w.getBlock(i, j, k) != Blocks.bedrock) {
							w.setBlock(i, j, k, Blocks.air);
							count++;
						}
					}
				}
			}
		}

		if (count == 0)
			e.setPositionAndUpdate(var1.posX, var1.posY, var1.posZ);
	}
}
