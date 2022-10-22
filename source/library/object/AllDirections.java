package net.tslat.aoa3.library.object;

import net.minecraft.world.phys.Vec3;

public enum AllDirections {
	DOWN_NORTH_WEST(0, new Vec3(-1, -1, -1)),
	DOWN_NORTH(1, new Vec3(-1, -1, 0)),
	DOWN_NORTH_EAST(2, new Vec3(-1, -1, 1)),
	DOWN_WEST(3, new Vec3(0, -1, -1)),
	DOWN(4, new Vec3(0, -1, 0)),
	DOWN_EAST(5, new Vec3(0, -1, 1)),
	DOWN_SOUTH_WEST(6, new Vec3(1, -1, -1)),
	DOWN_SOUTH(7, new Vec3(1, -1, 0)),
	DOWN_SOUTH_EAST(8, new Vec3(1, -1, 1)),
	NORTH_WEST(9, new Vec3(-1, 0, -1)),
	NORTH(10, new Vec3(-1, 0, 0)),
	NORTH_EAST(11, new Vec3(-1, 0, 1)),
	WEST(12, new Vec3(0, 0, -1)),
	CENTER(13, new Vec3(0, 0, 0)),
	EAST(14, new Vec3(0, 0, 1)),
	SOUTH_WEST(15, new Vec3(1, 0, -1)),
	SOUTH(16, new Vec3(1, 0, 0)),
	SOUTH_EAST(17, new Vec3(1, 0, 1)),
	UP_NORTH_WEST(18, new Vec3(-1, 1, -1)),
	UP_NORTH(19, new Vec3(-1, 1, 0)),
	UP_NORTH_EAST(20, new Vec3(-1, 1, 1)),
	UP_WEST(21, new Vec3(0, 1, -1)),
	UP(22, new Vec3(0, 1, 0)),
	UP_EAST(23, new Vec3(0, 1, 1)),
	UP_SOUTH_WEST(24, new Vec3(1, 1, -1)),
	UP_SOUTH(25, new Vec3(1, 1, 0)),
	UP_SOUTH_EAST(26, new Vec3(1, 1, 1));

	private final int index;
	private final Vec3 normalisedAngle;

	AllDirections(int index, Vec3 angle) {
		this.index = index;
		this.normalisedAngle = angle;
	}

	public int index() {
		return this.index;
	}

	public Vec3 angle() {
		return this.normalisedAngle;
	}

	public static AllDirections byIndex(int index) {
		return switch(index) {
			case 0 -> DOWN_NORTH_WEST;
			case 1 -> DOWN_NORTH;
			case 2 -> DOWN_NORTH_EAST;
			case 3 -> DOWN_WEST;
			case 4 -> DOWN;
			case 5 -> DOWN_EAST;
			case 6 -> DOWN_SOUTH_WEST;
			case 7 -> DOWN_SOUTH;
			case 8 -> DOWN_SOUTH_EAST;
			case 9 -> NORTH_WEST;
			case 10 -> NORTH;
			case 11 -> NORTH_EAST;
			case 12 -> WEST;
			case 13 -> CENTER;
			case 14 -> EAST;
			case 15 -> SOUTH_WEST;
			case 16 -> SOUTH;
			case 17 -> SOUTH_EAST;
			case 18 -> UP_NORTH_WEST;
			case 19 -> UP_NORTH;
			case 20 -> UP_NORTH_EAST;
			case 21 -> UP_WEST;
			case 22 -> UP;
			case 23 -> UP_EAST;
			case 24 -> UP_SOUTH_WEST;
			case 25 -> UP_SOUTH;
			case 26 -> UP_SOUTH_EAST;
			default -> CENTER;
		};
	}

	public static AllDirections byAngle(Vec3 angle) {
		int index = 0;

		if (angle.x > -0.66f)
			index += angle.x > 0.33f ? 2 : 1;

		if (angle.y > -0.66f)
			index += angle.y > 0.33f ? 18 : 9;

		if (angle.z > -0.66f)
			index += angle.z > 0.33f ? 6 : 3;

		return byIndex(index);
	}
}
