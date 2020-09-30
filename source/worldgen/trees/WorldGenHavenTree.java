package net.tslat.aoa3.worldgen.trees;

import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.block.functional.saplings.SaplingBlock;
import net.tslat.aoa3.block.generation.leaves.LeavesBlock;
import net.tslat.aoa3.common.registration.BlockRegister;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Random;

public class WorldGenHavenTree extends WorldGenTree {
	private final IBlockState leavesBlock;

	public WorldGenHavenTree(@Nullable SaplingBlock sapling, @Nullable LeavesBlock leaves, Random rand) {
		super(sapling);

		if (leaves == null) {
			switch (rand.nextInt(6)) {
				case 0:
					leavesBlock = BlockRegister.BLUE_HAVEN_LEAVES.getDefaultState();
					break;
				case 1:
					leavesBlock = BlockRegister.PINK_HAVEN_LEAVES.getDefaultState();
					break;
				case 2:
					leavesBlock = BlockRegister.PURPLE_HAVEN_LEAVES.getDefaultState();
					break;
				case 3:
					leavesBlock = BlockRegister.RED_HAVEN_LEAVES.getDefaultState();
					break;
				case 4:
					leavesBlock = BlockRegister.TURQUOISE_HAVEN_LEAVES.getDefaultState();
					break;
				case 5:
				default:
					leavesBlock = BlockRegister.YELLOW_HAVEN_LEAVES.getDefaultState();
					break;
			}
		}
		else {
			leavesBlock = leaves.getDefaultState();
		}
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		BlockPos multiSaplingPos = findMultiSaplingPosition(world, rand, pos, 2);

		switch (rand.nextInt(3) + (multiSaplingPos == null ? 0 : 3)) {
			case 0:
				return generateTree1(world, rand, pos);
			case 1:
				return generateTree2(world, rand, pos);
			case 2:
				return generateTree3(world, rand, pos);
			case 3:
				return generateTree4(world, rand, multiSaplingPos);
			case 4:
				return generateTree5(world, rand, multiSaplingPos);
			case 5:
				return generateTree6(world, rand, multiSaplingPos);
		}

		return false;
	}

	private boolean generateTree1(World world, Random rand, BlockPos pos) {
		int trunkHeight = 10 + rand.nextInt(6);

		if (!checkSafeHeight(world, pos, trunkHeight + 2, 1))
			return false;

		if (!checkAndPrepSoil(world, pos, 1))
			return false;

		BlockPos.MutableBlockPos movablePos = new BlockPos.MutableBlockPos(pos.down());
		IBlockState log = Blocks.LOG.getDefaultState();
		boolean builtLeafRing = false;

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(world, movablePos.move(EnumFacing.UP), log);

			if (!builtLeafRing && i > 1 && (i >= trunkHeight - 4 || rand.nextInt(3) == 0)) {
				BlockPos leafPosBase = movablePos.toImmutable();

				placeBlock(world, leafPosBase.offset(EnumFacing.NORTH), leavesBlock);
				placeBlock(world, leafPosBase.offset(EnumFacing.EAST), leavesBlock);
				placeBlock(world, leafPosBase.offset(EnumFacing.SOUTH), leavesBlock);
				placeBlock(world, leafPosBase.offset(EnumFacing.WEST), leavesBlock);

				leafPosBase = leafPosBase.up();

				for (int x = -2; x <= 2; x++) {
					for (int z = -2; z <= 2; z++) {
						if (Math.abs(x * z) < 2)
							placeBlock(world, leafPosBase.add(x, 0, z), leavesBlock);
					}
				}

				if (rand.nextBoolean()) {
					leafPosBase = leafPosBase.up();

					for (int x = -2; x <= 2; x++) {
						for (int z = -2; z <= 2; z++) {
							if (Math.abs(x * z) < 2)
								placeBlock(world, leafPosBase.add(x, 0, z), leavesBlock);
						}
					}
				}

				leafPosBase = leafPosBase.up();

				placeBlock(world, leafPosBase.offset(EnumFacing.NORTH), leavesBlock);
				placeBlock(world, leafPosBase.offset(EnumFacing.EAST), leavesBlock);
				placeBlock(world, leafPosBase.offset(EnumFacing.SOUTH), leavesBlock);
				placeBlock(world, leafPosBase.offset(EnumFacing.WEST), leavesBlock);

				builtLeafRing = true;
			}
		}

		placeBlock(world, movablePos.add(1, -2, 0), leavesBlock);
		placeBlock(world, movablePos.add(0, -2, 1), leavesBlock);
		placeBlock(world, movablePos.add(0, -2, -1), leavesBlock);
		placeBlock(world, movablePos.add(-1, -2, 0), leavesBlock);

		for (int x = -1; x <= 1; x++) {
			for (int z = -1; z <= 1; z++) {
				placeBlock(world, movablePos.add(x, -1, z), leavesBlock);
				placeBlock(world, movablePos.add(x, 1, z), leavesBlock);
			}
		}

		boolean cornerLeaves = rand.nextBoolean();

		for (int x = -2; x <= 2; x++) {
			for (int z = -2; z <= 2; z++) {
				if (cornerLeaves || Math.abs(x * z) != 4)
					placeBlock(world, movablePos.add(x, 0, z), leavesBlock);
			}
		}

		placeBlock(world, movablePos.add(0, 2, 0), leavesBlock);

		return true;
	}

	private boolean generateTree2(World world, Random rand, BlockPos pos) {
		int trunkHeight = 7 + rand.nextInt(6);

		if (!checkSafeHeight(world, pos, trunkHeight + 2, 1))
			return false;

		if (!checkAndPrepSoil(world, pos, 1))
			return false;

		BlockPos.MutableBlockPos movablePos = new BlockPos.MutableBlockPos(pos.down());
		IBlockState log = Blocks.LOG.getDefaultState();


		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(world, movablePos.move(EnumFacing.UP), log);
		}

		movablePos.move(EnumFacing.DOWN);

		for (int x = -3; x <= 3; x++) {
			for (int y = -3; y <= 3; y++) {
				for (int z = -3; z <= 3; z++) {
					int x2 = Math.abs(x);
					int y2 = Math.abs(y);
					int z2 = Math.abs(z);
					int max = Math.max(x2, Math.max(y2, z2));

					if ((max == 3 && x2 + y2 + z2 == 3) || (max == 2 && x2 + y2 + z2 < 4) || max == 1)
						placeBlock(world, movablePos.add(x, y, z), leavesBlock);
				}
			}
		}

		int leafDrop = 2 + rand.nextInt(3);

		for (int y = 1; y <= leafDrop; y++) {
			placeBlock(world, movablePos.add(3, -y, 0), leavesBlock);
			placeBlock(world, movablePos.add(-3, -y, 0), leavesBlock);
			placeBlock(world, movablePos.add(0, -y, 3), leavesBlock);
			placeBlock(world, movablePos.add(0, -y, -3), leavesBlock);
		}

		return true;
	}

	private boolean generateTree3(World world, Random rand, BlockPos pos) {
		int trunkHeight = 5 + rand.nextInt(4);

		if (!checkSafeHeight(world, pos, trunkHeight + 8, 1))
			return false;

		if (!checkAndPrepSoil(world, pos, 1))
			return false;

		BlockPos.MutableBlockPos movablePos = new BlockPos.MutableBlockPos(pos.down());
		IBlockState log = Blocks.LOG.getDefaultState();
		IBlockState barkLog = log.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.NONE);

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(world, movablePos.move(EnumFacing.UP), log);
		}

		placeBlock(world, movablePos, barkLog);

		BlockPos.MutableBlockPos branchMovablePos;

		for (EnumFacing direction : EnumFacing.HORIZONTALS) {
			branchMovablePos = new BlockPos.MutableBlockPos(movablePos);
			ArrayList<EnumFacing> availableDirections = new ArrayList<EnumFacing>(5);

			for (EnumFacing dir : EnumFacing.VALUES) {
				if (dir != direction)
					availableDirections.add(dir);
			}

			placeBlock(world, branchMovablePos.move(direction), barkLog);

			EnumFacing branchDir = direction;

			for (int i = 0; i <= 3; i++) {
				branchDir = rand.nextBoolean() ? availableDirections.get(rand.nextInt(5)) : branchDir;
				availableDirections.clear();

				for (EnumFacing dir : EnumFacing.VALUES) {
					if (dir != branchDir)
						availableDirections.add(dir);
				}

				placeBlock(world, branchMovablePos.move(branchDir), barkLog);
			}

			placeBlock(world, branchMovablePos.add(0, 1, 0), log);
			placeBlock(world, branchMovablePos.add(0, 2, 0), log);

			if (rand.nextBoolean()) {
				for (int x = -1; x <= 1; x++) {
					for (int y = 1; y <= 4; y++) {
						for (int z = -1; z <= 1; z++) {
							placeBlock(world, branchMovablePos.add(x, y, z), leavesBlock);
						}
					}
				}

				placeBlock(world, branchMovablePos.add(0, 5, 0), leavesBlock);
				placeBlock(world, branchMovablePos.add(2, 2, 0), leavesBlock);
				placeBlock(world, branchMovablePos.add(-2, 2, 0), leavesBlock);
				placeBlock(world, branchMovablePos.add(0, 2, 2), leavesBlock);
				placeBlock(world, branchMovablePos.add(0, 2, -2), leavesBlock);
				placeBlock(world, branchMovablePos.add(2, 3, 0), leavesBlock);
				placeBlock(world, branchMovablePos.add(-2, 3, 0), leavesBlock);
				placeBlock(world, branchMovablePos.add(0, 3, 2), leavesBlock);
				placeBlock(world, branchMovablePos.add(0, 3, -2), leavesBlock);
			}
			else {
				for (int x = -1; x <= 1; x++) {
					for (int y = 1; y <= 3; y++) {
						for (int z = -1; z <= 1; z++) {
							placeBlock(world, branchMovablePos.add(x, y, z), leavesBlock);
						}
					}
				}

				placeBlock(world, branchMovablePos.add(0, 4, 0), leavesBlock);
				placeBlock(world, branchMovablePos.add(2, 2, 0), leavesBlock);
				placeBlock(world, branchMovablePos.add(-2, 2, 0), leavesBlock);
				placeBlock(world, branchMovablePos.add(0, 2, 2), leavesBlock);
				placeBlock(world, branchMovablePos.add(0, 2, -2), leavesBlock);
			}
		}

		return true;
	}

	private boolean generateTree4(World world, Random rand, BlockPos pos) {
		int trunkHeight = 7 + rand.nextInt(5);

		if (!checkSafeHeight(world, pos, trunkHeight + 4, 2))
			return false;

		if (!checkAndPrepSoil(world, pos, 2))
			return false;

		IBlockState log = Blocks.LOG.getDefaultState();

		for (int i = 0; i < trunkHeight; i++) {
			for (int x = 0; x <= 1; x++) {
				for (int z = 0; z <= 1; z++) {
					placeBlock(world, pos.add(x, i, z), log);
				}
			}
		}

		pos = pos.up(trunkHeight - 3);
		boolean thickBranch = rand.nextBoolean();

		for (int x = -1; x <= 2; x++) {
			for (int z = -1; z <= 2; z++) {
				if (!((z == -1 || z == 2) && (x == -1 || x == 2))) {
					placeBlock(world, pos.add(x, 0, z), log);

					if (thickBranch)
						placeBlock(world, pos.add(x, 1, z), log);
				}
			}
		}

		placeBlock(world, pos.add(-2, 1, 0), log);
		placeBlock(world, pos.add(-2, 1, 1), log);
		placeBlock(world, pos.add(0, 1, -2), log);
		placeBlock(world, pos.add(1, 1, -2), log);
		placeBlock(world, pos.add(0, 1, 3), log);
		placeBlock(world, pos.add(1, 1, 3), log);
		placeBlock(world, pos.add(3, 1, 0), log);
		placeBlock(world, pos.add(3, 1, 1), log);

		for (int x = -3; x <= 4; x++) {
			for (int z = -3; z <= 4; z++) {
				if ((x >= -1 && x <= 2) || (z >= -1 && z <= 2)) {
					placeBlock(world, pos.add(x, 1, z), leavesBlock);

					if (!(((x == 0 || x == 1) && (z > -3 && z <= 3)) || ((z == 0 || z == 1) && (x > -3 && x <= 3)))) {
						int x2 = x;
						int z2 = z;

						if (x <= 0) {
							x2 -=1;
						}
						else {
							x2 += 1;
						}

						if (z <= 0) {
							z2 -= 1;
						}
						else {
							z2 += 1;
						}

						placeBlock(world, pos.add(x2, 0, z2), leavesBlock);
					}
				}
			}
		}

		placeBlock(world, pos.add(-4, 0, 0), leavesBlock);
		placeBlock(world, pos.add(-4, 0, 1), leavesBlock);
		placeBlock(world, pos.add(5, 0, 0), leavesBlock);
		placeBlock(world, pos.add(5, 0, 1), leavesBlock);
		placeBlock(world, pos.add(0, 0, -4), leavesBlock);
		placeBlock(world, pos.add(1, 0, -4), leavesBlock);
		placeBlock(world, pos.add(0, 0, 5), leavesBlock);
		placeBlock(world, pos.add(1, 0, 5), leavesBlock);

		for (int x = 0; x <= 1; x++) {
			for (int z = -2; z <= 3; z++) {
				placeBlock(world, pos.add(x, 2, z), leavesBlock);
			}
		}

		placeBlock(world, pos.add(-2, 2, 0), leavesBlock);
		placeBlock(world, pos.add(-1, 2, 0), leavesBlock);
		placeBlock(world, pos.add(-2, 2, 1), leavesBlock);
		placeBlock(world, pos.add(-1, 2, 1), leavesBlock);
		placeBlock(world, pos.add(3, 2, 0), leavesBlock);
		placeBlock(world, pos.add(2, 2, 0), leavesBlock);
		placeBlock(world, pos.add(3, 2, 1), leavesBlock);
		placeBlock(world, pos.add(2, 2, 1), leavesBlock);
		placeBlock(world, pos.add(0, 3, 0), leavesBlock);
		placeBlock(world, pos.add(0, 3, 1), leavesBlock);
		placeBlock(world, pos.add(1, 3, 0), leavesBlock);
		placeBlock(world, pos.add(1, 3, 1), leavesBlock);

		return true;
	}

	private boolean generateTree5(World world, Random rand, BlockPos pos) {
		int trunkHeight = 20 + rand.nextInt(15);

		if (!checkSafeHeight(world, pos, trunkHeight + 2, 2))
			return false;

		if (!checkAndPrepSoil(world, pos, 2))
			return false;

		IBlockState log = Blocks.LOG.getDefaultState();
		int leafRingGap = 0;

		for (int i = 0; i < trunkHeight; i++) {
			for (int x = 0; x <= 1; x++) {
				for (int z = 0; z <= 1; z++) {
					placeBlock(world, pos.add(x, i, z), log);
				}
			}

			leafRingGap++;

			if (leafRingGap > 5 && i < trunkHeight - 6 && rand.nextInt(5) == 0) {
				if (rand.nextBoolean()) {
					leafRingGap = 0;

					for (int x2 = -1; x2 <= 2; x2++) {
						for (int z2 = -1; z2 <= 2; z2++) {
							placeBlock(world, pos.add(x2, i, z2), leavesBlock);
							placeBlock(world, pos.add(x2, i + 2, z2), leavesBlock);
						}
					}

					for (int x2 = -2; x2 <= 3; x2++) {
						for (int z2 = -2; z2 <= 3; z2++) {
							placeBlock(world, pos.add(x2, i + 1, z2), leavesBlock);
						}
					}
				}
				else {
					leafRingGap = -2;

					for (int x2 = -1; x2 <= 2; x2++) {
						for (int z2 = -1; z2 <= 2; z2++) {
							placeBlock(world, pos.add(x2, i, z2), leavesBlock);
							placeBlock(world, pos.add(x2, i + 4, z2), leavesBlock);
						}
					}

					for (int x2 = -2; x2 <= 3; x2++) {
						for (int z2 = -2; z2 <= 3; z2++) {
							placeBlock(world, pos.add(x2, i + 1, z2), leavesBlock);
							placeBlock(world, pos.add(x2, i + 3, z2), leavesBlock);
						}
					}

					for (int x2 = -3; x2 <= 4; x2++) {
						for (int z2 = -3; z2 <= 4; z2++) {
							placeBlock(world, pos.add(x2, i + 2, z2), leavesBlock);
						}
					}
				}
			}
		}

		pos = pos.up(trunkHeight - 3);

		for (int x = -4; x <= 5; x++) {
			for (int z = 0; z <= 1; z++) {
				placeBlock(world, pos.add(x, 0, z), leavesBlock);
				placeBlock(world, pos.add(x, 4, z), leavesBlock);

				if (x == 0 || x == 1) {
					if (z == 0) {
						for (int z2 = -4; z2 < 0; z2++) {
							placeBlock(world, pos.add(x, 0, z2), leavesBlock);
							placeBlock(world, pos.add(x, 4, z2), leavesBlock);
						}

						for (int z2 = 2; z2 < 6; z2++) {
							placeBlock(world, pos.add(x, 0, z2), leavesBlock);
							placeBlock(world, pos.add(x, 4, z2), leavesBlock);
						}
					}
				}
			}
		}

		for (int x = -5; x <= 6; x++) {
			for (int z = -1; z <= 2; z++) {
				placeBlock(world, pos.add(x, 1, z), leavesBlock);
				placeBlock(world, pos.add(x, 3, z), leavesBlock);

				if (x >= -1 && x <= 2) {
					if (z == 0) {
						for (int z2 = -5; z2 < 0; z2++) {
							placeBlock(world, pos.add(x, 1, z2), leavesBlock);
							placeBlock(world, pos.add(x, 3, z2), leavesBlock);
						}

						for (int z2 = 3; z2 < 7; z2++) {
							placeBlock(world, pos.add(x, 1, z2), leavesBlock);
							placeBlock(world, pos.add(x, 3, z2), leavesBlock);
						}
					}
				}
			}
		}

		for (int x = -6; x <= 7; x++) {
			for (int z = -2; z <= 3; z++) {
				placeBlock(world, pos.add(x, 2, z), leavesBlock);

				if (x >= -2 && x <= 3) {
					if (z == 0) {
						for (int z2 = -6; z2 < 0; z2++) {
							placeBlock(world, pos.add(x, 2, z2), leavesBlock);
						}

						for (int z2 = 4; z2 < 8; z2++) {
							placeBlock(world, pos.add(x, 2, z2), leavesBlock);
						}
					}
				}
			}
		}

		return true;
	}

	private boolean generateTree6(World world, Random rand, BlockPos pos) {
		int trunkHeight = 15 + rand.nextInt(8);

		if (!checkSafeHeight(world, pos, trunkHeight + 4, 2))
			return false;

		if (!checkAndPrepSoil(world, pos, 2))
			return false;

		IBlockState log = Blocks.LOG.getDefaultState();
		int leafRingGap = -1;

		for (int i = 0; i < trunkHeight; i++) {
			for (int x = 0; x <= 1; x++) {
				for (int z = 0; z <= 1; z++) {
					placeBlock(world, pos.add(x, i, z), log);
				}
			}

			leafRingGap++;

			if (leafRingGap > 3 && i < trunkHeight - 7 && rand.nextBoolean()) {
				leafRingGap = 0;
				int ringWidth = 2 + rand.nextInt(3);
				boolean fullTopRing = rand.nextBoolean();
				boolean fullBottomRing = rand.nextBoolean();

				for (int x2 = -1; x2 <= 2; x2++) {
					for (int z2 = -1; z2 <= 2; z2++) {
						if (fullTopRing || ((x2 == 0 || x2 == 1) || (z2 == 0 || z2 == 1)))
							placeBlock(world, pos.add(x2, i + 2, z2), leavesBlock);

						if (fullBottomRing || ((x2 == 0 || x2 == 1) || (z2 == 0 || z2 == 1)))
							placeBlock(world, pos.add(x2, i, z2), leavesBlock);
					}
				}

				for (int x2 = -ringWidth; x2 <= ringWidth + 1; x2++) {
					for (int z2 = -ringWidth; z2 <= ringWidth + 1; z2++) {
						int x3 = x2;
						int z3 = z2;

						if (x3 < 0)
							x3 -= 1;

						if (z3 < 0)
							z3 -= 1;

						if (Math.abs(x3) <= ringWidth - (Math.abs(z3) - 3))
							placeBlock(world, pos.add(x2, i + 1, z2), leavesBlock);
					}
				}
			}
		}

		pos = pos.up(trunkHeight - 3);
		boolean fullRing = rand.nextBoolean();

		for (int x = -1; x <= 2; x++) {
			for (int z = -1; z <= 2; z++) {
				if (fullRing || ((x == 0 || x == 1) || (z == 0 || z == 1)))
					placeBlock(world, pos.add(x, 0, z), leavesBlock);
			}
		}

		for (int i = 4; i > 0; i--) {
			for (int x2 = -i; x2 <= i + 1; x2++) {
				for (int z2 = -i; z2 <= i + 1; z2++) {
					int x3 = x2;
					int z3 = z2;

					if (x3 < 0)
						x3 -= 1;

					if (z3 < 0)
						z3 -= 1;

					if (Math.abs(x3) <= i - (Math.abs(z3) - 3))
						placeBlock(world, pos.add(x2, 5 - i, z2), leavesBlock);
				}
			}
		}

		placeBlock(world, pos.add(0, 5, 0), leavesBlock);
		placeBlock(world, pos.add(1, 5, 0), leavesBlock);
		placeBlock(world, pos.add(0, 5, 1), leavesBlock);
		placeBlock(world, pos.add(1, 5, 1), leavesBlock);

		return true;
	}
}
