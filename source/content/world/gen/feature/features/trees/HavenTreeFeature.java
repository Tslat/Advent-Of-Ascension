package net.tslat.aoa3.content.world.gen.feature.features.trees;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.tslat.aoa3.content.block.functional.plant.SaplingBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Supplier;

public abstract class HavenTreeFeature extends AoAVariableLeafTreeFeature {
	public HavenTreeFeature(Codec<BlockStateFeatureConfig> codec, Supplier<SaplingBlock> saplingBlock) {
		super(codec, saplingBlock);
	}

	@Override
	protected boolean generateTree(ISeedReader reader, Random rand, BlockPos pos, BlockState leafBlock, boolean isWorldGen) {
		BlockPos multiSaplingPos = findMultiSaplingPosition(reader, rand, pos, 2, isWorldGen);
		boolean success = false;

		switch (rand.nextInt(3) + (multiSaplingPos == null ? 0 : 3)) {
			case 0:
				success = generateTree1(reader, rand, pos, leafBlock, isWorldGen);
				break;
			case 1:
				success = generateTree2(reader, rand, pos, leafBlock, isWorldGen);
				break;
			case 2:
				success = generateTree3(reader, rand, pos, leafBlock, isWorldGen);
				break;
			case 3:
				success = generateTree4(reader, rand, multiSaplingPos, leafBlock, isWorldGen);
				break;
			case 4:
				success = generateTree5(reader, rand, multiSaplingPos, leafBlock, isWorldGen);
				break;
			case 5:
				success = generateTree6(reader, rand, multiSaplingPos, leafBlock, isWorldGen);
				break;
		}

		return success;
	}

	private boolean generateTree1(ISeedReader reader, Random rand, BlockPos pos, BlockState leafBlock, boolean isWorldGen) {
		int trunkHeight = 10 + rand.nextInt(6);

		if (!checkSafeHeight(reader, pos, trunkHeight + 2, 1, isWorldGen))
			return false;

		if (!checkAndPrepSoil(reader, pos, 1, isWorldGen))
			return false;

		BlockPos.Mutable movablePos = new BlockPos.Mutable().set(pos.below());
		BlockState log = Blocks.OAK_LOG.defaultBlockState();
		boolean builtLeafRing = false;

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(reader, movablePos.move(Direction.UP), log);

			if (!builtLeafRing && i > 1 && (i >= trunkHeight - 4 || rand.nextInt(3) == 0)) {
				BlockPos leafPosBase = movablePos.immutable();

				placeBlock(reader, leafPosBase.relative(Direction.NORTH), leafBlock);
				placeBlock(reader, leafPosBase.relative(Direction.EAST), leafBlock);
				placeBlock(reader, leafPosBase.relative(Direction.SOUTH), leafBlock);
				placeBlock(reader, leafPosBase.relative(Direction.WEST), leafBlock);

				leafPosBase = leafPosBase.above();

				for (int x = -2; x <= 2; x++) {
					for (int z = -2; z <= 2; z++) {
						if (Math.abs(x * z) < 2)
							placeBlock(reader, leafPosBase.offset(x, 0, z), leafBlock);
					}
				}

				if (rand.nextBoolean()) {
					leafPosBase = leafPosBase.above();

					for (int x = -2; x <= 2; x++) {
						for (int z = -2; z <= 2; z++) {
							if (Math.abs(x * z) < 2)
								placeBlock(reader, leafPosBase.offset(x, 0, z), leafBlock);
						}
					}
				}

				leafPosBase = leafPosBase.above();

				placeBlock(reader, leafPosBase.relative(Direction.NORTH), leafBlock);
				placeBlock(reader, leafPosBase.relative(Direction.EAST), leafBlock);
				placeBlock(reader, leafPosBase.relative(Direction.SOUTH), leafBlock);
				placeBlock(reader, leafPosBase.relative(Direction.WEST), leafBlock);

				builtLeafRing = true;
			}
		}

		placeBlock(reader, movablePos.offset(1, -2, 0), leafBlock);
		placeBlock(reader, movablePos.offset(0, -2, 1), leafBlock);
		placeBlock(reader, movablePos.offset(0, -2, -1), leafBlock);
		placeBlock(reader, movablePos.offset(-1, -2, 0), leafBlock);

		for (int x = -1; x <= 1; x++) {
			for (int z = -1; z <= 1; z++) {
				placeBlock(reader, movablePos.offset(x, -1, z), leafBlock);
				placeBlock(reader, movablePos.offset(x, 1, z), leafBlock);
			}
		}

		boolean cornerLeaves = rand.nextBoolean();

		for (int x = -2; x <= 2; x++) {
			for (int z = -2; z <= 2; z++) {
				if (cornerLeaves || Math.abs(x * z) != 4)
					placeBlock(reader, movablePos.offset(x, 0, z), leafBlock);
			}
		}

		placeBlock(reader, movablePos.offset(0, 2, 0), leafBlock);

		return true;
	}

	private boolean generateTree2(ISeedReader reader, Random rand, BlockPos pos, BlockState leafBlock, boolean isWorldGen) {
		int trunkHeight = 7 + rand.nextInt(6);

		if (!checkSafeHeight(reader, pos, trunkHeight + 2, 1, isWorldGen))
			return false;

		if (!checkAndPrepSoil(reader, pos, 1, isWorldGen))
			return false;

		BlockPos.Mutable movablePos = new BlockPos.Mutable().set(pos.below());
		BlockState log = Blocks.OAK_LOG.defaultBlockState();


		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(reader, movablePos.move(Direction.UP), log);
		}

		movablePos.move(Direction.DOWN);

		for (int x = -3; x <= 3; x++) {
			for (int y = -3; y <= 3; y++) {
				for (int z = -3; z <= 3; z++) {
					int x2 = Math.abs(x);
					int y2 = Math.abs(y);
					int z2 = Math.abs(z);
					int max = Math.max(x2, Math.max(y2, z2));

					if ((max == 3 && x2 + y2 + z2 == 3) || (max == 2 && x2 + y2 + z2 < 4) || max == 1)
						placeBlock(reader, movablePos.offset(x, y, z), leafBlock);
				}
			}
		}

		int leafDrop = 2 + rand.nextInt(3);

		for (int y = 1; y <= leafDrop; y++) {
			placeBlock(reader, movablePos.offset(3, -y, 0), leafBlock);
			placeBlock(reader, movablePos.offset(-3, -y, 0), leafBlock);
			placeBlock(reader, movablePos.offset(0, -y, 3), leafBlock);
			placeBlock(reader, movablePos.offset(0, -y, -3), leafBlock);
		}

		return true;
	}

	private boolean generateTree3(ISeedReader reader, Random rand, BlockPos pos, BlockState leafBlock, boolean isWorldGen) {
		int trunkHeight = 5 + rand.nextInt(4);

		if (!checkSafeHeight(reader, pos, trunkHeight + 8, 1, isWorldGen))
			return false;

		if (!checkAndPrepSoil(reader, pos, 1, isWorldGen))
			return false;

		BlockPos.Mutable movablePos = new BlockPos.Mutable().set(pos.below());
		BlockState log = Blocks.OAK_LOG.defaultBlockState();
		BlockState barkLog = Blocks.OAK_WOOD.defaultBlockState();

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(reader, movablePos.move(Direction.UP), log);
		}

		placeBlock(reader, movablePos, barkLog);

		BlockPos.Mutable branchMovablePos;

		for (Direction direction : Direction.Plane.HORIZONTAL) {
			branchMovablePos = new BlockPos.Mutable().set(movablePos);
			ArrayList<Direction> availableDirections = new ArrayList<Direction>(5);

			for (Direction dir : Direction.values()) {
				if (dir != direction)
					availableDirections.add(dir);
			}

			placeBlock(reader, branchMovablePos.move(direction), barkLog);

			Direction branchDir = direction;

			for (int i = 0; i <= 3; i++) {
				branchDir = rand.nextBoolean() ? availableDirections.get(rand.nextInt(5)) : branchDir;
				availableDirections.clear();

				for (Direction dir : Direction.values()) {
					if (dir != branchDir)
						availableDirections.add(dir);
				}

				placeBlock(reader, branchMovablePos.move(branchDir), barkLog);
			}

			placeBlock(reader, branchMovablePos.offset(0, 1, 0), log);
			placeBlock(reader, branchMovablePos.offset(0, 2, 0), log);

			if (rand.nextBoolean()) {
				for (int x = -1; x <= 1; x++) {
					for (int y = 1; y <= 4; y++) {
						for (int z = -1; z <= 1; z++) {
							placeBlock(reader, branchMovablePos.offset(x, y, z), leafBlock);
						}
					}
				}

				placeBlock(reader, branchMovablePos.offset(0, 5, 0), leafBlock);
				placeBlock(reader, branchMovablePos.offset(2, 2, 0), leafBlock);
				placeBlock(reader, branchMovablePos.offset(-2, 2, 0), leafBlock);
				placeBlock(reader, branchMovablePos.offset(0, 2, 2), leafBlock);
				placeBlock(reader, branchMovablePos.offset(0, 2, -2), leafBlock);
				placeBlock(reader, branchMovablePos.offset(2, 3, 0), leafBlock);
				placeBlock(reader, branchMovablePos.offset(-2, 3, 0), leafBlock);
				placeBlock(reader, branchMovablePos.offset(0, 3, 2), leafBlock);
				placeBlock(reader, branchMovablePos.offset(0, 3, -2), leafBlock);
			}
			else {
				for (int x = -1; x <= 1; x++) {
					for (int y = 1; y <= 3; y++) {
						for (int z = -1; z <= 1; z++) {
							placeBlock(reader, branchMovablePos.offset(x, y, z), leafBlock);
						}
					}
				}

				placeBlock(reader, branchMovablePos.offset(0, 4, 0), leafBlock);
				placeBlock(reader, branchMovablePos.offset(2, 2, 0), leafBlock);
				placeBlock(reader, branchMovablePos.offset(-2, 2, 0), leafBlock);
				placeBlock(reader, branchMovablePos.offset(0, 2, 2), leafBlock);
				placeBlock(reader, branchMovablePos.offset(0, 2, -2), leafBlock);
			}
		}

		return true;
	}

	private boolean generateTree4(ISeedReader reader, Random rand, BlockPos pos, BlockState leafBlock, boolean isWorldGen) {
		int trunkHeight = 7 + rand.nextInt(5);

		if (!checkSafeHeight(reader, pos, trunkHeight + 4, 2, isWorldGen))
			return false;

		if (!checkAndPrepSoil(reader, pos, 2, isWorldGen))
			return false;

		BlockState baseSoil = reader.getBlockState(pos.below());

		for (int x = 0; x < 2; x++) {
			for (int z = 0; z < 2; z++) {
				BlockPos testPos = pos.offset(x, -1, z);

				if (reader.getBlockState(testPos).is(Blocks.AIR))
					reader.setBlock(testPos, baseSoil, 2);
			}
		}

		BlockState log = Blocks.OAK_LOG.defaultBlockState();

		for (int i = 0; i < trunkHeight; i++) {
			for (int x = 0; x <= 1; x++) {
				for (int z = 0; z <= 1; z++) {
					placeBlock(reader, pos.offset(x, i, z), log);
				}
			}
		}

		pos = pos.above(trunkHeight - 3);
		boolean thickBranch = rand.nextBoolean();

		for (int x = -1; x <= 2; x++) {
			for (int z = -1; z <= 2; z++) {
				if (!((z == -1 || z == 2) && (x == -1 || x == 2))) {
					placeBlock(reader, pos.offset(x, 0, z), log);

					if (thickBranch)
						placeBlock(reader, pos.offset(x, 1, z), log);
				}
			}
		}

		placeBlock(reader, pos.offset(-2, 1, 0), log);
		placeBlock(reader, pos.offset(-2, 1, 1), log);
		placeBlock(reader, pos.offset(0, 1, -2), log);
		placeBlock(reader, pos.offset(1, 1, -2), log);
		placeBlock(reader, pos.offset(0, 1, 3), log);
		placeBlock(reader, pos.offset(1, 1, 3), log);
		placeBlock(reader, pos.offset(3, 1, 0), log);
		placeBlock(reader, pos.offset(3, 1, 1), log);

		for (int x = -3; x <= 4; x++) {
			for (int z = -3; z <= 4; z++) {
				if ((x >= -1 && x <= 2) || (z >= -1 && z <= 2)) {
					placeBlock(reader, pos.offset(x, 1, z), leafBlock);

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

						placeBlock(reader, pos.offset(x2, 0, z2), leafBlock);
					}
				}
			}
		}

		placeBlock(reader, pos.offset(-4, 0, 0), leafBlock);
		placeBlock(reader, pos.offset(-4, 0, 1), leafBlock);
		placeBlock(reader, pos.offset(5, 0, 0), leafBlock);
		placeBlock(reader, pos.offset(5, 0, 1), leafBlock);
		placeBlock(reader, pos.offset(0, 0, -4), leafBlock);
		placeBlock(reader, pos.offset(1, 0, -4), leafBlock);
		placeBlock(reader, pos.offset(0, 0, 5), leafBlock);
		placeBlock(reader, pos.offset(1, 0, 5), leafBlock);

		for (int x = 0; x <= 1; x++) {
			for (int z = -2; z <= 3; z++) {
				placeBlock(reader, pos.offset(x, 2, z), leafBlock);
			}
		}

		placeBlock(reader, pos.offset(-2, 2, 0), leafBlock);
		placeBlock(reader, pos.offset(-1, 2, 0), leafBlock);
		placeBlock(reader, pos.offset(-2, 2, 1), leafBlock);
		placeBlock(reader, pos.offset(-1, 2, 1), leafBlock);
		placeBlock(reader, pos.offset(3, 2, 0), leafBlock);
		placeBlock(reader, pos.offset(2, 2, 0), leafBlock);
		placeBlock(reader, pos.offset(3, 2, 1), leafBlock);
		placeBlock(reader, pos.offset(2, 2, 1), leafBlock);
		placeBlock(reader, pos.offset(0, 3, 0), leafBlock);
		placeBlock(reader, pos.offset(0, 3, 1), leafBlock);
		placeBlock(reader, pos.offset(1, 3, 0), leafBlock);
		placeBlock(reader, pos.offset(1, 3, 1), leafBlock);

		return true;
	}

	private boolean generateTree5(ISeedReader reader, Random rand, BlockPos pos, BlockState leafBlock, boolean isWorldGen) {
		int trunkHeight = 20 + rand.nextInt(15);

		if (!checkSafeHeight(reader, pos, trunkHeight + 2, 2, isWorldGen))
			return false;

		if (!checkAndPrepSoil(reader, pos, 2, isWorldGen))
			return false;

		BlockState baseSoil = reader.getBlockState(pos.below());

		for (int x = 0; x < 2; x++) {
			for (int z = 0; z < 2; z++) {
				BlockPos testPos = pos.offset(x, -1, z);

				if (reader.getBlockState(testPos).is(Blocks.AIR))
					reader.setBlock(testPos, baseSoil, 2);
			}
		}

		BlockState log = Blocks.OAK_LOG.defaultBlockState();
		int leafRingGap = 0;

		for (int i = 0; i < trunkHeight; i++) {
			for (int x = 0; x <= 1; x++) {
				for (int z = 0; z <= 1; z++) {
					placeBlock(reader, pos.offset(x, i, z), log);
				}
			}

			leafRingGap++;

			if (leafRingGap > 5 && i < trunkHeight - 6 && rand.nextInt(5) == 0) {
				if (rand.nextBoolean()) {
					leafRingGap = 0;

					for (int x2 = -1; x2 <= 2; x2++) {
						for (int z2 = -1; z2 <= 2; z2++) {
							placeBlock(reader, pos.offset(x2, i, z2), leafBlock);
							placeBlock(reader, pos.offset(x2, i + 2, z2), leafBlock);
						}
					}

					for (int x2 = -2; x2 <= 3; x2++) {
						for (int z2 = -2; z2 <= 3; z2++) {
							placeBlock(reader, pos.offset(x2, i + 1, z2), leafBlock);
						}
					}
				}
				else {
					leafRingGap = -2;

					for (int x2 = -1; x2 <= 2; x2++) {
						for (int z2 = -1; z2 <= 2; z2++) {
							placeBlock(reader, pos.offset(x2, i, z2), leafBlock);
							placeBlock(reader, pos.offset(x2, i + 4, z2), leafBlock);
						}
					}

					for (int x2 = -2; x2 <= 3; x2++) {
						for (int z2 = -2; z2 <= 3; z2++) {
							placeBlock(reader, pos.offset(x2, i + 1, z2), leafBlock);
							placeBlock(reader, pos.offset(x2, i + 3, z2), leafBlock);
						}
					}

					for (int x2 = -3; x2 <= 4; x2++) {
						for (int z2 = -3; z2 <= 4; z2++) {
							placeBlock(reader, pos.offset(x2, i + 2, z2), leafBlock);
						}
					}
				}
			}
		}

		pos = pos.above(trunkHeight - 3);

		for (int x = -4; x <= 5; x++) {
			for (int z = 0; z <= 1; z++) {
				placeBlock(reader, pos.offset(x, 0, z), leafBlock);
				placeBlock(reader, pos.offset(x, 4, z), leafBlock);

				if (x == 0 || x == 1) {
					if (z == 0) {
						for (int z2 = -4; z2 < 0; z2++) {
							placeBlock(reader, pos.offset(x, 0, z2), leafBlock);
							placeBlock(reader, pos.offset(x, 4, z2), leafBlock);
						}

						for (int z2 = 2; z2 < 6; z2++) {
							placeBlock(reader, pos.offset(x, 0, z2), leafBlock);
							placeBlock(reader, pos.offset(x, 4, z2), leafBlock);
						}
					}
				}
			}
		}

		for (int x = -5; x <= 6; x++) {
			for (int z = -1; z <= 2; z++) {
				placeBlock(reader, pos.offset(x, 1, z), leafBlock);
				placeBlock(reader, pos.offset(x, 3, z), leafBlock);

				if (x >= -1 && x <= 2) {
					if (z == 0) {
						for (int z2 = -5; z2 < 0; z2++) {
							placeBlock(reader, pos.offset(x, 1, z2), leafBlock);
							placeBlock(reader, pos.offset(x, 3, z2), leafBlock);
						}

						for (int z2 = 3; z2 < 7; z2++) {
							placeBlock(reader, pos.offset(x, 1, z2), leafBlock);
							placeBlock(reader, pos.offset(x, 3, z2), leafBlock);
						}
					}
				}
			}
		}

		for (int x = -6; x <= 7; x++) {
			for (int z = -2; z <= 3; z++) {
				placeBlock(reader, pos.offset(x, 2, z), leafBlock);

				if (x >= -2 && x <= 3) {
					if (z == 0) {
						for (int z2 = -6; z2 < 0; z2++) {
							placeBlock(reader, pos.offset(x, 2, z2), leafBlock);
						}

						for (int z2 = 4; z2 < 8; z2++) {
							placeBlock(reader, pos.offset(x, 2, z2), leafBlock);
						}
					}
				}
			}
		}

		return true;
	}

	private boolean generateTree6(ISeedReader reader, Random rand, BlockPos pos, BlockState leafBlock, boolean isWorldGen) {
		int trunkHeight = 15 + rand.nextInt(8);

		if (!checkSafeHeight(reader, pos, trunkHeight + 4, 2, isWorldGen))
			return false;

		if (!checkAndPrepSoil(reader, pos, 2, isWorldGen))
			return false;

		BlockState log = Blocks.OAK_LOG.defaultBlockState();
		int leafRingGap = -1;

		for (int i = 0; i < trunkHeight; i++) {
			for (int x = 0; x <= 1; x++) {
				for (int z = 0; z <= 1; z++) {
					placeBlock(reader, pos.offset(x, i, z), log);
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
							placeBlock(reader, pos.offset(x2, i + 2, z2), leafBlock);

						if (fullBottomRing || ((x2 == 0 || x2 == 1) || (z2 == 0 || z2 == 1)))
							placeBlock(reader, pos.offset(x2, i, z2), leafBlock);
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
							placeBlock(reader, pos.offset(x2, i + 1, z2), leafBlock);
					}
				}
			}
		}

		pos = pos.above(trunkHeight - 3);
		boolean fullRing = rand.nextBoolean();

		for (int x = -1; x <= 2; x++) {
			for (int z = -1; z <= 2; z++) {
				if (fullRing || ((x == 0 || x == 1) || (z == 0 || z == 1)))
					placeBlock(reader, pos.offset(x, 0, z), leafBlock);
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
						placeBlock(reader, pos.offset(x2, 5 - i, z2), leafBlock);
				}
			}
		}

		placeBlock(reader, pos.offset(0, 5, 0), leafBlock);
		placeBlock(reader, pos.offset(1, 5, 0), leafBlock);
		placeBlock(reader, pos.offset(0, 5, 1), leafBlock);
		placeBlock(reader, pos.offset(1, 5, 1), leafBlock);

		return true;
	}
}
