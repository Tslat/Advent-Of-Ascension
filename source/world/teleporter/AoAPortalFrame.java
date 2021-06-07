package net.tslat.aoa3.world.teleporter;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.World;
import net.tslat.aoa3.block.functional.portal.PortalBlock;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoATags;

import java.util.ArrayList;

import static net.tslat.aoa3.world.teleporter.AoAPortalFrame.PortalDirection.*;

public class AoAPortalFrame {
    private static final ArrayList<Vector3i> northSouthPattern = new ArrayList<Vector3i>();
    private static final ArrayList<Vector3i> eastWestPattern = new ArrayList<Vector3i>();

    public static PortalDirection testFrameForActivation(World world, BlockPos activationPos, Direction sideClicked, PortalBlock portalBlock) {
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

        for (Vector3i pos : northSouthPattern) {
            Block testBlock = world.getBlockState(basePos.offset(pos)).getBlock();

            if (testBlock == AoABlocks.ANCIENT_ROCK.get()) {
                ancientRockCount++;
            }
            else if (testBlock.is(AoATags.Blocks.CARVED_RUNE)) {
                if (testBlock == AoABlocks.CARVED_RUNE_OF_TRAVEL.get()) {
                    if (carvedRuneTravel != null)
                        return INVALID;

                    carvedRuneTravel = testBlock;
                }
                else if (testBlock == AoABlocks.CARVED_RUNE_OF_REALITY.get()) {
                    if (carvedRuneReality != null)
                        return INVALID;

                    carvedRuneReality = testBlock;
                }
                else if (testBlock == AoABlocks.CARVED_RUNE_OF_SPACE.get()) {
                    if (carvedRuneSpace != null)
                        return INVALID;

                    carvedRuneSpace = testBlock;
                }
                else if (testBlock == AoABlocks.CARVED_RUNE_OF_DIRECTION.get()) {
                    if (carvedRuneDirection != null)
                        return INVALID;

                    carvedRuneDirection = testBlock;
                }
            }
        }

        if (ancientRockCount == 13 && carvedRuneDirection != null && carvedRuneReality != null && carvedRuneSpace != null && carvedRuneTravel != null) {
            for (int x = -1; x < 2; x++) {
                for (int y = 1; y < 5; y++) {
					BlockState state = world.getBlockState(basePos.offset(x, y, 0));

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

        for (Vector3i pos : eastWestPattern) {
            Block testBlock = world.getBlockState(basePos.offset(pos)).getBlock();

            if (testBlock == AoABlocks.ANCIENT_ROCK.get()) {
                ancientRockCount++;
            }
            else if (testBlock.is(AoATags.Blocks.CARVED_RUNE)) {
                if (testBlock == AoABlocks.CARVED_RUNE_OF_TRAVEL.get()) {
                    if (carvedRuneTravel != null)
                        return INVALID;

                    carvedRuneTravel = testBlock;
                }
                else if (testBlock == AoABlocks.CARVED_RUNE_OF_REALITY.get()) {
                    if (carvedRuneReality != null)
                        return INVALID;

                    carvedRuneReality = testBlock;
                }
                else if (testBlock == AoABlocks.CARVED_RUNE_OF_SPACE.get()) {
                    if (carvedRuneSpace != null)
                        return INVALID;

                    carvedRuneSpace = testBlock;
                }
                else if (testBlock == AoABlocks.CARVED_RUNE_OF_DIRECTION.get()) {
                    if (carvedRuneDirection != null)
                        return INVALID;

                    carvedRuneDirection = testBlock;
                }
            }
        }

        if (ancientRockCount == 13 && carvedRuneDirection != null && carvedRuneReality != null && carvedRuneSpace != null && carvedRuneTravel != null) {
            for (int z = -1; z < 2; z++) {
                for (int y = 1; y < 5; y++) {
					BlockState state = world.getBlockState(basePos.offset(0, y, z));

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
                        world.setBlock(basePos.offset(x, y, 0), portalBlock.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.Z), 2);
                    }
                }
                break;
            case EAST_WEST:
                for (int z = -1; z < 2; z++) {
                    for (int y = 1; y < 5; y++) {
                        world.setBlock(basePos.offset(0, y, z), portalBlock.defaultBlockState(), 2);
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
        northSouthPattern.add(new Vector3i(-2, 0, 0));
        northSouthPattern.add(new Vector3i(-1, 0, 0));
        northSouthPattern.add(new Vector3i(-0, 0, 0));
        northSouthPattern.add(new Vector3i(1, 0, 0));
        northSouthPattern.add(new Vector3i(2, 0, 0));
        northSouthPattern.add(new Vector3i(2, 1, 0));
        northSouthPattern.add(new Vector3i(2, 2, 0));
        northSouthPattern.add(new Vector3i(2, 3, 0));
        northSouthPattern.add(new Vector3i(2, 4, 0));
        northSouthPattern.add(new Vector3i(2, 5, 0));
        northSouthPattern.add(new Vector3i(1, 5, 0));
        northSouthPattern.add(new Vector3i(0, 5, 0));
        northSouthPattern.add(new Vector3i(-1, 5, 0));
        northSouthPattern.add(new Vector3i(-2, 5, 0));
        northSouthPattern.add(new Vector3i(-2, 4, 0));
        northSouthPattern.add(new Vector3i(-2, 3, 0));
        northSouthPattern.add(new Vector3i(-2, 2, 0));
        northSouthPattern.add(new Vector3i(-2, 1, 0));

        eastWestPattern.add(new Vector3i(0, 0, -2));
        eastWestPattern.add(new Vector3i(0, 0, -1));
        eastWestPattern.add(new Vector3i(0, 0, 0));
        eastWestPattern.add(new Vector3i(0, 0, 1));
        eastWestPattern.add(new Vector3i(0, 0, 2));
        eastWestPattern.add(new Vector3i(0, 1, 2));
        eastWestPattern.add(new Vector3i(0, 2, 2));
        eastWestPattern.add(new Vector3i(0, 3, 2));
        eastWestPattern.add(new Vector3i(0, 4, 2));
        eastWestPattern.add(new Vector3i(0, 5, 2));
        eastWestPattern.add(new Vector3i(0, 5, 1));
        eastWestPattern.add(new Vector3i(0, 5, 0));
        eastWestPattern.add(new Vector3i(0, 5, -1));
        eastWestPattern.add(new Vector3i(0, 5, -2));
        eastWestPattern.add(new Vector3i(0, 4, -2));
        eastWestPattern.add(new Vector3i(0, 3, -2));
        eastWestPattern.add(new Vector3i(0, 2, -2));
        eastWestPattern.add(new Vector3i(0, 1, -2));
    }
}
