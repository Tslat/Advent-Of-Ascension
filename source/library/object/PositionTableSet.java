package net.tslat.aoa3.library.object;

import net.minecraft.core.Vec3i;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Wrapper class for handling a 3d region of space by their x/y/z coordinates utilising a one-dimensional array for efficiency.
 */
public final class PositionTableSet implements Iterable<Vec3i> {
	private final boolean[] table;
	private final int width;
	private final int height;
	private final int depth;

	private PositionTableSet(int xWidth, int yHeight, int zDepth) {
		this.table = new boolean[xWidth * yHeight * zDepth];
		this.width = xWidth;
		this.height = yHeight;
		this.depth = zDepth;
	}

	public static PositionTableSet of(int diameter) {
		return of(diameter, diameter);
	}

	public static PositionTableSet of(int width, int height) {
		return of(width, height, width);
	}

	public static PositionTableSet of(int xWidth, int yHeight, int zDepth) {
		return new PositionTableSet(xWidth, yHeight, zDepth);
	}

	public static PositionTableSet of(Vec3i from, Vec3i to) {
		final int minX = Math.min(from.getX(), to.getX());
		final int minY = Math.min(from.getY(), to.getY());
		final int minZ = Math.min(from.getZ(), to.getZ());
		final int maxX = Math.max(from.getX(), to.getX());
		final int maxY = Math.max(from.getY(), to.getY());
		final int maxZ = Math.max(from.getZ(), to.getZ());

		return of(maxX - minX, maxY - minY, maxZ - minZ);
	}

	public boolean isFilledAt(int x, int y, int z) {
		return this.table[pack(x, y, z)];
	}

	public boolean isFilledAt(Vec3i position) {
		return isFilledAt(position.getX(), position.getY(), position.getZ());
	}

	public void add(int x, int y, int z) {
		this.table[pack(x, y, z)] = true;
	}

	public void add(Vec3i position) {
		add(position.getX(), position.getY(), position.getZ());
	}

	public void remove(int x, int y, int z) {
		this.table[pack(x, y, z)] = false;
	}

	public void remove(Vec3i position) {
		remove(position.getX(), position.getY(), position.getZ());
	}

	public boolean isAtEdgeOfRegion(int x, int y, int z) {
		return x == 0 || x == this.width - 1 || y == 0 || y == this.height - 1 || z == 0 || z == this.depth - 1;
	}

	public boolean isAdjacentFilled(int x, int y, int z) {
		if (isFilledAt(x - 1, y, z) || isFilledAt(x + 1, y, z))
			return true;

		if (isFilledAt(x, y - 1, z) || isFilledAt(x, y + 1, z))
			return true;

		return isFilledAt(x, y, z - 1) || isFilledAt(x, y, z + 1);
	}

	private int pack(int x, int y, int z) {
		return z * this.height * this.width + y * this.width + x;
	}

	private Vec3i unpack(int index) {
		final int x = index % this.width;
		final int y = (index / this.width) % this.height;
		final int z = index / (this.width * this.height);

		return new Vec3i(x, y, z);
	}

	@NotNull
	public Iterable<Vec3i> emptyPositions() {
		return new Iterable<>() {
			@NotNull
			@Override
			public Iterator<Vec3i> iterator() {
				return new Iterator<>() {
					int seekIndex = findNextIndex(0);
					int index = 0;

					@Override
					public boolean hasNext() {
						if (this.index >= this.seekIndex || PositionTableSet.this.table[this.seekIndex])
							this.seekIndex = findNextIndex(this.index + 1);

						return this.seekIndex != -1;
					}

					@Override
					public Vec3i next() {
						if (this.index >= this.seekIndex)
							this.seekIndex = findNextIndex(this.index + 1);

						this.index = this.seekIndex;

						if (this.index < 0)
							throw new IllegalStateException("Iterator did not have another element to proceed to! Check hasNext() before calling next");

						return unpack(this.index);
					}

					private int findNextIndex(int nextIndex) {
						for (int i = nextIndex; i < PositionTableSet.this.table.length; i++) {
							if (!PositionTableSet.this.table[i])
								return i;
						}

						return -1;
					}
				};
			}
		};
	}

	public Stream<Vec3i> stream() {
		return StreamSupport.stream(spliterator(), false);
	}

	@NotNull
	@Override
	public Iterator<Vec3i> iterator() {
		return new Iterator<>() {
			int seekIndex = findNextIndex(0);
			int index = 0;

			@Override
			public boolean hasNext() {
				if (this.index >= this.seekIndex || !PositionTableSet.this.table[this.seekIndex])
					this.seekIndex = findNextIndex(this.index + 1);

				return this.seekIndex != -1;
			}

			@Override
			public Vec3i next() {
				if (this.index >= this.seekIndex)
					this.seekIndex = findNextIndex(this.index + 1);

				this.index = this.seekIndex;

				if (this.index < 0)
					throw new IllegalStateException("Iterator did not have another element to proceed to! Check hasNext() before calling next");

				return unpack(this.index);
			}

			private int findNextIndex(int nextIndex) {
				for (int i = nextIndex; i < PositionTableSet.this.table.length; i++) {
					if (PositionTableSet.this.table[i])
						return i;
				}

				return -1;
			}
		};
	}
}
