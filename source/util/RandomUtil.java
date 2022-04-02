package net.tslat.aoa3.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public final class RandomUtil {
	private static final char[] ALPHANUMERIC_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
	public static final EasyRandom RANDOM = new EasyRandom(new Random());

	public static boolean fiftyFifty() {
		return RANDOM.fiftyFifty();
	}

	public static boolean oneInNChance(int n) {
		return RANDOM.oneInNChance(n);
	}

	public static boolean percentChance(double percentChance) {
		return RANDOM.percentChance(percentChance);
	}

	public static boolean percentChance(float percentChance) {
		return RANDOM.percentChance(percentChance);
	}

	public static int randomNumberUpTo(int upperBound) {
		return RANDOM.randomNumberUpTo(upperBound);
	}

	public static float randomValueUpTo(float upperBound) {
		return RANDOM.randomValueUpTo(upperBound);
	}

	public static double randomValueUpTo(double upperBound) {
		return RANDOM.randomValueUpTo(upperBound);
	}

	public static double randomGaussianValue() {
		return RANDOM.randomGaussianValue();
	}

	public static double randomScaledGaussianValue(double scale) {
		return RANDOM.randomScaledGaussianValue(scale);
	}

	public static int randomNumberBetween(int min, int max) {
		return RANDOM.randomNumberBetween(min, max);
	}

	public static double randomValueBetween(double min, double max) {
		return RANDOM.randomValueBetween(min, max);
	}

	public static <T> T getRandomSelection(@Nonnull T... options) {
		return RANDOM.getRandomSelection(options);
	}

	public static <T> T getRandomSelection(@Nonnull List<T> options) {
		return RANDOM.getRandomSelection(options);
	}

	public static BlockPos getRandomPositionWithinRange(BlockPos centerPos, int xRadius, int yRadius, int zRadius) {
		return RANDOM.getRandomPositionWithinRange(centerPos, xRadius, yRadius, zRadius);
	}

	public static BlockPos getRandomPositionWithinRange(BlockPos centerPos, int xRadius, int yRadius, int zRadius, boolean safeSurfacePlacement, Level world) {
		return RANDOM.getRandomPositionWithinRange(centerPos, xRadius, yRadius, zRadius, safeSurfacePlacement, world);
	}

	public static BlockPos getRandomPositionWithinRange(BlockPos centerPos, int xRadius, int yRadius, int zRadius, boolean safeSurfacePlacement, Level world, @Nullable Predicate<BlockState> statePredicate, int tries) {
		return RANDOM.getRandomPositionWithinRange(centerPos, xRadius, yRadius, zRadius, safeSurfacePlacement, world, statePredicate, tries);
	}

	public static String getRandomAlphaNumeric(int length) {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < length; i++) {
			builder.append(ALPHANUMERIC_CHARACTERS[RANDOM.randomNumberUpTo(ALPHANUMERIC_CHARACTERS.length)]);
		}

		return builder.toString();
	}

	public static final class EasyRandom {
		private final Random RANDOM;

		public EasyRandom() {
			this(new Random());
		}

		public EasyRandom(@Nonnull Random rand) {
			this.RANDOM = rand;
		}

		public Random source() {
			return RANDOM;
		}

		public boolean fiftyFifty() {
			return RANDOM.nextBoolean();
		}

		public boolean oneInNChance(int n) {
			if (n <= 0)
				return false;

			return RANDOM.nextFloat() < 1 / (float)n;
		}

		public boolean percentChance(double percentChance) {
			if (percentChance <= 0)
				return false;

			if (percentChance >= 1)
				return true;

			return RANDOM.nextDouble() < percentChance;
		}

		public boolean percentChance(float percentChance) {
			if (percentChance <= 0)
				return false;

			if (percentChance >= 1)
				return true;

			return RANDOM.nextDouble() < percentChance;
		}

		public int randomNumberUpTo(int upperBound) {
			return RANDOM.nextInt(upperBound);
		}

		public float randomValueUpTo(float upperBound) {
			return RANDOM.nextFloat() * upperBound;
		}

		public double randomValueUpTo(double upperBound) {
			return RANDOM.nextDouble() * upperBound;
		}

		public double randomGaussianValue() {
			return RANDOM.nextGaussian();
		}

		public double randomScaledGaussianValue(double scale) {
			return RANDOM.nextGaussian() * scale;
		}

		public int randomNumberBetween(int min, int max) {
			return min + (int)Math.floor(RANDOM.nextDouble() * (1 + max - min));
		}

		public double randomValueBetween(double min, double max) {
			return min + RANDOM.nextDouble() * (max - min);
		}

		public <T> T getRandomSelection(@Nonnull T... options) {
			return options[RANDOM.nextInt(options.length)];
		}

		public <T> T getRandomSelection(@Nonnull List<T> options) {
			return options.get(RANDOM.nextInt(options.size()));
		}

		public BlockPos getRandomPositionWithinRange(BlockPos centerPos, int xRadius, int yRadius, int zRadius) {
			return getRandomPositionWithinRange(centerPos, xRadius, yRadius, zRadius, false, null);
		}

		public BlockPos getRandomPositionWithinRange(BlockPos centerPos, int xRadius, int yRadius, int zRadius, boolean safeSurfacePlacement, Level world) {
			return getRandomPositionWithinRange(centerPos, xRadius, yRadius, zRadius, safeSurfacePlacement, world, null, 1);
		}

		public BlockPos getRandomPositionWithinRange(BlockPos centerPos, int xRadius, int yRadius, int zRadius, boolean safeSurfacePlacement, Level world, @Nullable Predicate<BlockState> statePredicate, int tries) {
			BlockPos.MutableBlockPos mutablePos = centerPos.mutable();

			for (int i = 0; i < tries; i++) {
				int newX = (int)Math.floor(mutablePos.getX() + RANDOM.nextFloat() * xRadius * 2 - xRadius);
				int newY = (int)Math.floor(mutablePos.getY() + RANDOM.nextFloat() * yRadius * 2 - yRadius);
				int newZ = (int)Math.floor(mutablePos.getZ() + RANDOM.nextFloat() * zRadius * 2 - zRadius);

				mutablePos.set(newX, newY, newZ);

				if (safeSurfacePlacement && world != null)
					mutablePos.set(world.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, mutablePos));

				if (statePredicate == null || statePredicate.test(world.getBlockState(mutablePos)))
					return mutablePos.immutable();
			}

			return centerPos;
		}

		public String getRandomAlphaNumeric(int length) {
			StringBuilder builder = new StringBuilder();

			for (int i = 0; i < length; i++) {
				builder.append(getRandomSelection(ALPHANUMERIC_CHARACTERS));
			}

			return builder.toString();
		}
	}
}
