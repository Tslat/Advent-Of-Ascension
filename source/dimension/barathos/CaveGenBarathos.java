package net.tslat.aoa3.dimension.barathos;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenCaves;
import net.tslat.aoa3.common.registration.BlockRegister;

public class CaveGenBarathos extends MapGenCaves {
	@Override
	protected void recursiveGenerate(World worldIn, int chunkX, int chunkZ, int originalX, int originalZ, ChunkPrimer chunkPrimerIn) {
		int i = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(15) + 1) + 1);

		for (int j = 0; j < i; ++j) {
			double d0 = (double)(chunkX * 16 + this.rand.nextInt(16));
			double d1 = (double)this.rand.nextInt(this.rand.nextInt(120) + 8);
			double d2 = (double)(chunkZ * 16 + this.rand.nextInt(16));
			int k = 1;

			if (this.rand.nextInt(4) == 0) {
				this.addRoom(this.rand.nextLong(), originalX, originalZ, chunkPrimerIn, d0, d1, d2);
				k += this.rand.nextInt(4);
			}

			for (int l = 0; l < k; ++l) {
				float f = this.rand.nextFloat() * ((float)Math.PI * 2F);
				float f1 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
				float f2 = this.rand.nextFloat() * 2.0F + this.rand.nextFloat();

				if (this.rand.nextInt(10) == 0)
					f2 *= this.rand.nextFloat() * this.rand.nextFloat() * 3.0F + 1.0F;

				addTunnel(this.rand.nextLong(), originalX, originalZ, chunkPrimerIn, d0, d1, d2, f2, f, f1, 0, 0, 1.0D);
			}
		}
	}

	@Override
	protected boolean canReplaceBlock(IBlockState targetBlock, IBlockState replacementBlock) {
		return targetBlock.getBlock() == BlockRegister.stoneBaron;
	}
}
