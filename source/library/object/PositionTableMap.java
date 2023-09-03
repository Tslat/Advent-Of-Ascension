package net.tslat.aoa3.library.object;

import net.minecraft.core.Vec3i;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Wrapper class for handling a 3d region of space by their x/y/z coordinates and an associated value utilising a one-dimensional array for efficiency.
 * <br>
 * Note that 0 is considered 'empty'
 */
public final class PositionTableMap implements Iterable<Vec3i> {
	private final int[] table;
	private final int width;
	private final int height;
	private final int depth;

	private PositionTableMap(int xWidth, int yHeight, int zDepth) {
		this.table = new int[xWidth * yHeight * zDepth];
		this.width = xWidth;
		this.height = yHeight;
		this.depth = zDepth;
	}

	public static PositionTableMap of(int diameter) {
		return of(diameter, diameter);
	}

	public static PositionTableMap of(int width, int height) {
		return of(width, height, width);
	}

	public static PositionTableMap of(int xWidth, int yHeight, int zDepth) {
		return new PositionTableMap(xWidth, yHeight, zDepth);
	}

	public static PositionTableMap of(Vec3i from, Vec3i to) {
		final int minX = Math.min(from.getX(), to.getX());
		final int minY = Math.min(from.getY(), to.getY());
		final int minZ = Math.min(from.getZ(), to.getZ());
		final int maxX = Math.max(from.getX(), to.getX());
		final int maxY = Math.max(from.getY(), to.getY());
		final int maxZ = Math.max(from.getZ(), to.getZ());

		return of(maxX - minX, maxY - minY, maxZ - minZ);
	}

	public boolean hasAnyValueAt(int x, int y, int z) {
		return this.table[pack(x, y, z)] >= 0;
	}

	public boolean hasValueAt(int x, int y, int z, int value) {
		return this.table[pack(x, y, z)] == value;
	}

	public boolean hasAnyValueAt(Vec3i position) {
		return hasAnyValueAt(position.getX(), position.getY(), position.getZ());
	}

	public boolean hasValueAt(Vec3i position, int value) {
		return hasValueAt(position.getX(), position.getY(), position.getZ(), value);
	}

	public void set(int x, final int y, int z, int value) {
		this.table[pack(x, y, z)] = value;
	}

	public void set(Vec3i position, int value) {
		set(position.getX(), position.getY(), position.getZ(), value);
	}

	public void remove(int x, int y, int z) {
		this.table[pack(x, y, z)] = -1;
	}

	public void remove(Vec3i position) {
		remove(position.getX(), position.getY(), position.getZ());
	}

	public boolean isAtEdgeOfRegion(int x, int y, int z) {
		return x == 0 || x == this.width - 1 || y == 0 || y == this.height - 1 || z == 0 || z == this.depth - 1;
	}

	public boolean isAdjacentFilled(int x, int y, int z) {
		if (hasAnyValueAt(x - 1, y, z) || hasAnyValueAt(x + 1, y, z))
			return true;

		if (hasAnyValueAt(x, y - 1, z) || hasAnyValueAt(x, y + 1, z))
			return true;

		return hasAnyValueAt(x, y, z - 1) || hasAnyValueAt(x, y, z + 1);
	}

	public boolean isAdjacentFilled(int x, int y, int z, int value) {
		if (hasValueAt(x - 1, y, z, value) || hasValueAt(x + 1, y, z, value))
			return true;

		if (hasValueAt(x, y - 1, z, value) || hasValueAt(x, y + 1, z, value))
			return true;

		return hasValueAt(x, y, z - 1, value) || hasValueAt(x, y, z + 1, value);
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
	@Override
	public Iterator<Vec3i> iterator() {
		return new EntryIterator(0) {
			@Override
			public boolean hasNext() {
				if (this.index >= this.seekIndex || PositionTableMap.this.table[this.seekIndex] == 0)
					this.seekIndex = findNextIndex(this.index + 1);

				return this.seekIndex != -1;
			}

			protected int findNextIndex(final int nextIndex) {
				for (int i = nextIndex; i < PositionTableMap.this.table.length; i++) {
					if (PositionTableMap.this.table[i] != 0)
						return i;
				}

				return -1;
			}
		};
	}

	public Stream<Vec3i> stream() {
		return StreamSupport.stream(spliterator(), false);
	}

	public Iterable<Vec3i> emptyPositions() {
		return positionsForValue(0);
	}

	public Iterable<Vec3i> positionsForValue(final int value) {
		return new Iterable<>() {
			@NotNull
			@Override
			public Iterator<Vec3i> iterator() {
				return new EntryIterator(value);
			}
		};
	}

	private class EntryIterator implements Iterator<Vec3i> {
		final int matchingValue;
		int seekIndex = findNextIndex(0);
		int index = 0;

		EntryIterator(final int forValue) {
			this.matchingValue = forValue;
		}

		@Override
		public boolean hasNext() {
			if (this.index >= this.seekIndex || PositionTableMap.this.table[this.seekIndex] != this.matchingValue)
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

		protected int findNextIndex(final int nextIndex) {
			for (int i = nextIndex; i < PositionTableMap.this.table.length; i++) {
				if (PositionTableMap.this.table[i] == this.matchingValue)
					return i;
			}

			return -1;
		}
	}
}
