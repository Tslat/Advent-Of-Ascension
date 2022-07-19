package net.tslat.aoa3.util;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

public final class RandomUtil {
	private static final char[] ALPHANUMERIC_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
	public static final EasyRandom RANDOM = new EasyRandom(RandomSource.createThreadSafe());

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

	public static BlockPos getRandomPositionWithinRange(BlockPos centerPos, int xRadius, int yRadius, int zRadius, int minSpreadX, int minSpreadY, int minSpreadZ, boolean safeSurfacePlacement, Level world, int tries, @Nullable Predicate<BlockState> statePredicate) {
		return RANDOM.getRandomPositionWithinRange(centerPos, xRadius, yRadius, zRadius, minSpreadX, minSpreadY, minSpreadZ, safeSurfacePlacement, world, tries, statePredicate);
	}

	public static String getRandomAlphaNumeric(int length) {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < length; i++) {
			builder.append(ALPHANUMERIC_CHARACTERS[RANDOM.randomNumberUpTo(ALPHANUMERIC_CHARACTERS.length)]);
		}

		return builder.toString();
	}

	public static final class EasyRandom implements RandomSource  {
		private final RandomSource random;

		public EasyRandom() {
			this(RandomSource.create());
		}

		public EasyRandom(@Nonnull RandomSource rand) {
			this.random = rand;
		}

		public RandomSource getSource() {
			return RandomSource.create();
		}

		public boolean fiftyFifty() {
			return random.nextBoolean();
		}

		public boolean oneInNChance(int n) {
			if (n <= 0)
				return false;

			return random.nextFloat() < 1 / (float)n;
		}

		public boolean percentChance(double percentChance) {
			if (percentChance <= 0)
				return false;

			if (percentChance >= 1)
				return true;

			return random.nextDouble() < percentChance;
		}

		public boolean percentChance(float percentChance) {
			if (percentChance <= 0)
				return false;

			if (percentChance >= 1)
				return true;

			return random.nextDouble() < percentChance;
		}

		public int randomNumberUpTo(int upperBound) {
			return random.nextInt(upperBound);
		}

		public float randomValueUpTo(float upperBound) {
			return random.nextFloat() * upperBound;
		}

		public double randomValueUpTo(double upperBound) {
			return random.nextDouble() * upperBound;
		}

		public double randomGaussianValue() {
			return random.nextGaussian();
		}

		public double randomScaledGaussianValue(double scale) {
			return random.nextGaussian() * scale;
		}

		public int randomNumberBetween(int min, int max) {
			return min + (int)Math.floor(random.nextDouble() * (1 + max - min));
		}

		public double randomValueBetween(double min, double max) {
			return min + random.nextDouble() * (max - min);
		}

		public <T> T getRandomSelection(@Nonnull T... options) {
			return options[random.nextInt(options.length)];
		}

		public <T> T getRandomSelection(@Nonnull List<T> options) {
			return options.get(random.nextInt(options.size()));
		}

		public BlockPos getRandomPositionWithinRange(BlockPos centerPos, int xRadius, int yRadius, int zRadius) {
			return getRandomPositionWithinRange(centerPos, xRadius, yRadius, zRadius, false, null);
		}

		public BlockPos getRandomPositionWithinRange(BlockPos centerPos, int xRadius, int yRadius, int zRadius, boolean safeSurfacePlacement, Level world) {
			return getRandomPositionWithinRange(centerPos, xRadius, yRadius, zRadius, 0, 0, 0, safeSurfacePlacement, world, 1, null);
		}

		public BlockPos getRandomPositionWithinRange(BlockPos centerPos, int xRadius, int yRadius, int zRadius, int minSpreadX, int minSpreadY, int minSpreadZ, boolean safeSurfacePlacement, Level world, int tries, @Nullable Predicate<BlockState> statePredicate) {
			BlockPos.MutableBlockPos mutablePos;
			xRadius = Math.max(xRadius - minSpreadX, 0);
			yRadius = Math.max(yRadius - minSpreadY, 0);
			zRadius = Math.max(zRadius - minSpreadZ, 0);

			for (int i = 0; i < tries; i++) {
				mutablePos = centerPos.mutable();
				double xAdjust = random.nextFloat() * xRadius * 2 - xRadius;
				double yAdjust = random.nextFloat() * yRadius * 2 - yRadius;
				double zAdjust = random.nextFloat() * zRadius * 2 - zRadius;
				int newX = (int)Math.floor(mutablePos.getX() + xAdjust + minSpreadX * Math.signum(xAdjust));
				int newY = (int)Math.floor(mutablePos.getY() + yAdjust + minSpreadY * Math.signum(yAdjust));
				int newZ = (int)Math.floor(mutablePos.getZ() + zAdjust + minSpreadZ * Math.signum(zAdjust));

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

		@Override
		public EasyRandom fork() {
			return new EasyRandom(this.random.fork());
		}

		@Override
		public PositionalRandomFactory forkPositional() {
			return this.random.forkPositional();
		}

		@Override
		public void setSeed(long seed) {
			this.random.setSeed(seed);
		}

		@Override
		public int nextInt() {
			return this.random.nextInt();
		}

		@Override
		public int nextInt(int upperLimit) {
			return this.random.nextInt(upperLimit);
		}

		@Override
		public long nextLong() {
			return this.random.nextLong();
		}

		@Override
		public boolean nextBoolean() {
			return this.random.nextBoolean();
		}

		@Override
		public float nextFloat() {
			return this.random.nextFloat();
		}

		@Override
		public double nextDouble() {
			return this.random.nextDouble();
		}

		@Override
		public double nextGaussian() {
			return this.random.nextGaussian();
		}
	}
}
