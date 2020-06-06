package net.tslat.aoa3.library.misc;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.tslat.aoa3.block.functional.misc.CarvedRunicPortalBlock;
import net.tslat.aoa3.block.functional.portal.PortalBlock;
import net.tslat.aoa3.common.registration.BlockRegister;

import java.util.ArrayList;

import static net.tslat.aoa3.library.misc.AoAPortalFrame.PortalDirection.*;

public class AoAPortalFrame {
    private static ArrayList<Vec3i> northSouthPattern = new ArrayList<Vec3i>();
    private static ArrayList<Vec3i> eastWestPattern = new ArrayList<Vec3i>();

    public static PortalDirection testFrameForActivation(World world, BlockPos activationPos, EnumFacing sideClicked, PortalBlock portalBlock) {
        switch (sideClicked) {
            case NORTH:
            case SOUTH:
            	return testNorthSouthPortalFrame(world, activationPos, portalBlock);
            case EAST:
            case WEST:
            	return testEastWestPortalFrame(world, activationPos, portalBlock);
            case UP:
            case DOWN:
            default:
            	PortalDirection dir = testNorthSouthPortalFrame(world, activationPos, portalBlock);

            	if (dir == INVALID)
            		dir = testEastWestPortalFrame(world, activationPos, portalBlock);

            	return dir;
        }
    }

    public static PortalDirection testNorthSouthPortalFrame(World world, BlockPos basePos, PortalBlock portalBlock) {
        Block carvedRuneTravel = null;
        Block carvedRuneReality = null;
        Block carvedRuneSpace = null;
        Block carvedRuneDirection = null;
        int ancientRockCount = 0;

        for (Vec3i pos : northSouthPattern) {
            Block testBlock = world.getBlockState(basePos.add(pos)).getBlock();

            if (testBlock == BlockRegister.ANCIENT_ROCK) {
                ancientRockCount++;
            }
            else if (testBlock instanceof CarvedRunicPortalBlock) {
                if (testBlock == BlockRegister.CARVED_RUNE_TRAVEL) {
                    if (carvedRuneTravel != null)
                        return INVALID;

                    carvedRuneTravel = testBlock;
                }
                else if (testBlock == BlockRegister.CARVED_RUNE_REALITY) {
                    if (carvedRuneReality != null)
                        return INVALID;

                    carvedRuneReality = testBlock;
                }
                else if (testBlock == BlockRegister.CARVED_RUNE_SPACE) {
                    if (carvedRuneSpace != null)
                        return INVALID;

                    carvedRuneSpace = testBlock;
                }
                else if (testBlock == BlockRegister.CARVED_RUNE_DIRECTION) {
                    if (carvedRuneDirection != null)
                        return INVALID;

                    carvedRuneDirection = testBlock;
                }
            }
        }

        if (ancientRockCount == 13 && carvedRuneDirection != null && carvedRuneReality != null && carvedRuneSpace != null && carvedRuneTravel != null) {
            for (int x = -1; x < 2; x++) {
                for (int y = 1; y < 5; y++) {
					IBlockState state = world.getBlockState(basePos.add(x, y, 0));

					if (state.getBlock() instanceof PortalBlock) {
						if (state.getBlock() == portalBlock)
							return EXISTING;
					}
					else if (!state.getMaterial().isReplaceable()) {
						return INVALID;
					}
                }
            }

            return NORTH_SOUTH;
        }

        return INVALID;
    }

    public static PortalDirection testEastWestPortalFrame(World world, BlockPos basePos, PortalBlock portalBlock) {
        Block carvedRuneTravel = null;
        Block carvedRuneReality = null;
        Block carvedRuneSpace = null;
        Block carvedRuneDirection = null;
        int ancientRockCount = 0;

        for (Vec3i pos : eastWestPattern) {
            Block testBlock = world.getBlockState(basePos.add(pos)).getBlock();

            if (testBlock == BlockRegister.ANCIENT_ROCK) {
                ancientRockCount++;
            }
            else if (testBlock instanceof CarvedRunicPortalBlock) {
                if (testBlock == BlockRegister.CARVED_RUNE_TRAVEL) {
                    if (carvedRuneTravel != null)
                        return INVALID;

                    carvedRuneTravel = testBlock;
                }
                else if (testBlock == BlockRegister.CARVED_RUNE_REALITY) {
                    if (carvedRuneReality != null)
                        return INVALID;

                    carvedRuneReality = testBlock;
                }
                else if (testBlock == BlockRegister.CARVED_RUNE_SPACE) {
                    if (carvedRuneSpace != null)
                        return INVALID;

                    carvedRuneSpace = testBlock;
                }
                else if (testBlock == BlockRegister.CARVED_RUNE_DIRECTION) {
                    if (carvedRuneDirection != null)
                        return INVALID;

                    carvedRuneDirection = testBlock;
                }
            }
        }

        if (ancientRockCount == 13 && carvedRuneDirection != null && carvedRuneReality != null && carvedRuneSpace != null && carvedRuneTravel != null) {
            for (int z = -1; z < 2; z++) {
                for (int y = 1; y < 5; y++) {
					IBlockState state = world.getBlockState(basePos.add(0, y, z));

					if (state.getBlock() instanceof PortalBlock) {
						if (state.getBlock() == portalBlock)
							return EXISTING;
					}
					else if (!state.getMaterial().isReplaceable()) {
						return INVALID;
					}
                }
            }

            return EAST_WEST;
        }

        return INVALID;
    }

    public static void lightPortalFrame(World world, BlockPos basePos, PortalDirection direction, PortalBlock portalBlock) {
        switch (direction) {
            case NORTH_SOUTH:
                for (int x = -1; x < 2; x++) {
                    for (int y = 1; y < 5; y++) {
                        world.setBlockState(basePos.add(x, y, 0), portalBlock.getDefaultState());
                    }
                }
                break;
            case EAST_WEST:
                for (int z = -1; z < 2; z++) {
                    for (int y = 1; y < 5; y++) {
                        world.setBlockState(basePos.add(0, y, z), portalBlock.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.EAST));
                    }
                }
                break;
            default:
                break;
        }
    }

    public enum PortalDirection {
        INVALID,
		EXISTING,
        NORTH_SOUTH,
        EAST_WEST
    }

    static {
        northSouthPattern.add(new Vec3i(-2, 0, 0));
        northSouthPattern.add(new Vec3i(-1, 0, 0));
        northSouthPattern.add(new Vec3i(-0, 0, 0));
        northSouthPattern.add(new Vec3i(1, 0, 0));
        northSouthPattern.add(new Vec3i(2, 0, 0));
        northSouthPattern.add(new Vec3i(2, 1, 0));
        northSouthPattern.add(new Vec3i(2, 2, 0));
        northSouthPattern.add(new Vec3i(2, 3, 0));
        northSouthPattern.add(new Vec3i(2, 4, 0));
        northSouthPattern.add(new Vec3i(2, 5, 0));
        northSouthPattern.add(new Vec3i(1, 5, 0));
        northSouthPattern.add(new Vec3i(0, 5, 0));
        northSouthPattern.add(new Vec3i(-1, 5, 0));
        northSouthPattern.add(new Vec3i(-2, 5, 0));
        northSouthPattern.add(new Vec3i(-2, 4, 0));
        northSouthPattern.add(new Vec3i(-2, 3, 0));
        northSouthPattern.add(new Vec3i(-2, 2, 0));
        northSouthPattern.add(new Vec3i(-2, 1, 0));

        eastWestPattern.add(new Vec3i(0, 0, -2));
        eastWestPattern.add(new Vec3i(0, 0, -1));
        eastWestPattern.add(new Vec3i(0, 0, 0));
        eastWestPattern.add(new Vec3i(0, 0, 1));
        eastWestPattern.add(new Vec3i(0, 0, 2));
        eastWestPattern.add(new Vec3i(0, 1, 2));
        eastWestPattern.add(new Vec3i(0, 2, 2));
        eastWestPattern.add(new Vec3i(0, 3, 2));
        eastWestPattern.add(new Vec3i(0, 4, 2));
        eastWestPattern.add(new Vec3i(0, 5, 2));
        eastWestPattern.add(new Vec3i(0, 5, 1));
        eastWestPattern.add(new Vec3i(0, 5, 0));
        eastWestPattern.add(new Vec3i(0, 5, -1));
        eastWestPattern.add(new Vec3i(0, 5, -2));
        eastWestPattern.add(new Vec3i(0, 4, -2));
        eastWestPattern.add(new Vec3i(0, 3, -2));
        eastWestPattern.add(new Vec3i(0, 2, -2));
        eastWestPattern.add(new Vec3i(0, 1, -2));
    }
}
