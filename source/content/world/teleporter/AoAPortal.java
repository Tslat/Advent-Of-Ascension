package net.tslat.aoa3.content.world.teleporter;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.GlobalPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.chunk.BulkSectionAccess;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoAConfigs;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.block.functional.portal.NowherePortalBlock;
import net.tslat.aoa3.content.block.functional.portal.PortalBlock;
import net.tslat.aoa3.library.object.container.BoundingRegion;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.tme.api.sound.SoundBuilder;
import org.jetbrains.annotations.Nullable;

import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public interface AoAPortal extends Portal {
    IdentityHashMap<ResourceKey<Level>, List<BlockPos>> TRANSIENT_PORTAL_CACHE = new IdentityHashMap<>();

    PortalBlock getPortalBlock();
    Block getPortalFrame();

    default int portalFrameWidth() {
        return 5;
    }

    default int portalSpaceHeight() {
        return 6;
    }

    default DimensionTransition.PostDimensionTransition playTransitSound(Entity entity) {
        if (entity instanceof ServerPlayer pl) {
            return entity2 ->
                    SoundBuilder.localAmbience(AoASounds.PORTAL_EXIT, pl.level())
                            .varyPitch(0.2f)
                            .onlyFor(pl)
                            .play();
        }

        return DimensionTransition.PLAY_PORTAL_SOUND;
    }

    static BlockPos makeSafeCoords(Level fromLevel, Level toLevel, Vec3 pos) {
        final WorldBorder border = toLevel.getWorldBorder();
        final double relativeScale = DimensionType.getTeleportationScale(fromLevel.dimensionType(), toLevel.dimensionType());

        return border.clampToBounds(pos.x * relativeScale, pos.y, pos.z * relativeScale);
    }

    static DimensionTransition getTransitionForLevel(ServerLevel destination, Entity entity, Block aoaPortalBlock) {
        return getTransitionForLevel(destination, entity, Optional.empty(), entity.blockPosition(), (PortalBlock)aoaPortalBlock, Optional.ofNullable(entity instanceof ServerPlayer pl ? PlayerUtil.getAdventPlayer(pl).storage.getPortalReturnFor(entity.level().dimension()) : null));
    }

    static DimensionTransition getTransitionForLevel(ServerLevel destination, Entity entity, Optional<BlockPos> fromPortal, BlockPos safeCoords, AoAPortal portal, Optional<GlobalPos> existingLink) {
        final ServerLevel fromLevel = (ServerLevel)entity.level();
        final BlockPos portalPos = getOrCreatePortalLocation(destination, fromLevel, entity, safeCoords, portal, existingLink);

        fromPortal.ifPresent(portalBlock -> {
            if (entity instanceof ServerPlayer pl)
                updatePlayerLink(pl, portalBlock, fromLevel.dimension(), destination.dimension());
        });

        return new DimensionTransition(destination, Vec3.atCenterOf(portalPos), entity.getDeltaMovement(), entity.getYRot(), entity.getXRot(), portal.playTransitSound(entity).then(DimensionTransition.PLACE_PORTAL_TICKET));
    }

    static BlockPos getOrCreatePortalLocation(ServerLevel destination, ServerLevel fromLevel, Entity entity, BlockPos safeCoords, AoAPortal portal, Optional<GlobalPos> existingLink) {
        BlockPos portalPos = entity instanceof ServerPlayer pl ? existingLink.filter(link -> link.dimension() == destination.dimension()).map(link -> portal.retrieveExistingLinkExit(pl, pl.serverLevel(), destination, link)).orElse(null) : null;

        if (fromLevel.getBlockState(entity.blockPosition()).getBlock() instanceof PortalBlock)
            updateLocalCache(fromLevel, entity.blockPosition());

        if (portalPos == null && entity instanceof ServerPlayer && WorldUtil.isWorld((Level)destination, AoADimensions.NOWHERE) && !(portal.getClass().isAssignableFrom(NowherePortalBlock.class)))
            portalPos = new BlockPos(16, 1003, 16);

        if (portalPos == null)
            portalPos = portal.findExistingPortal(destination, entity, safeCoords);

        if (portalPos == null) {
            portalPos = portal.findSuitablePortalLocation(destination, entity, safeCoords);
            portalPos = portal.createPortalFrameAndSpace(destination, entity, portalPos);
        }

        updateLocalCache(destination, portalPos);

        return portalPos;
    }

    static void updatePlayerLink(ServerPlayer pl, BlockPos fromPortalBlockPos, ResourceKey<Level> fromDim, ResourceKey<Level> destinationDim) {
        final ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer(pl);
        final GlobalPos portalLoc = plData.storage.getPortalReturnFor(destinationDim);

        if (portalLoc != null) {
            GlobalPos returnPortalLoc = plData.storage.getPortalReturnFor(fromDim);

            if (returnPortalLoc != null && returnPortalLoc.dimension() == destinationDim)
                return;
        }

        if (portalLoc == null || fromDim == portalLoc.dimension() || pl.distanceToSqr(Vec3.atLowerCornerOf(portalLoc.pos())) > AoAConfigs.SERVER.portalSearchRadius.get() * AoAConfigs.SERVER.portalSearchRadius.get())
            plData.storage.setPortalReturnLocation(destinationDim, fromDim, fromPortalBlockPos);
    }

    @Nullable
    static BlockPos getLocalCachedPos(Level level, BlockPos pos, Block portalBlock) {
        final List<BlockPos> localPortalCache = TRANSIENT_PORTAL_CACHE.get(level.dimension());

        if (localPortalCache == null || localPortalCache.isEmpty())
            return null;

        final int range = AoAConfigs.SERVER.portalSearchRadius.getAsInt();
        BlockPos closest = null;
        double closestDistSqr = Double.MAX_VALUE;

        for (Iterator<BlockPos> positions = localPortalCache.iterator(); positions.hasNext();) {
            BlockPos nextPos = positions.next();
            double distSqr = nextPos.distSqr(pos);

            if (distSqr <= range * range && distSqr < closestDistSqr) {
                if (!level.getBlockState(nextPos).is(portalBlock)) {
                    positions.remove();
                    continue;
                }

                closest = nextPos;
                closestDistSqr = distSqr;
            }
        }

        return closest;
    }

    static BlockPos updateLocalCache(Level level, BlockPos pos) {
        TRANSIENT_PORTAL_CACHE.computeIfAbsent(level.dimension(), key -> new ObjectArrayList<>()).add(pos.immutable());

        return pos;
    }

    @Nullable
    default BlockPos retrieveExistingLinkExit(ServerPlayer player, ServerLevel currentWorld, ServerLevel destWorld, GlobalPos existingLink) {
        final ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer(player);
        BlockPos locPos = existingLink.pos();
        BlockState state = destWorld.getBlockState(locPos);

        if (state.is(getPortalBlock()))
            return locPos;

        if (!(state.getBlock() instanceof PortalBlock))
            plData.storage.removePortalReturnLocation(currentWorld.dimension());

        return currentWorld.dimension() == AoADimensions.NOWHERE && destWorld.dimension() == AoADimensions.OVERWORLD ? locPos : null;
    }

    default BlockPos findExistingPortal(Level targetLevel, Entity entity, BlockPos originPos) {
        final BlockPos cachedPos = getLocalCachedPos(targetLevel, originPos, getPortalBlock());

        if (cachedPos != null)
            return cachedPos;

        final Block portalBlock = getPortalBlock();
        final int searchRadius = AoAConfigs.SERVER.portalSearchRadius.get();
        final int worldHeight = targetLevel.getMaxBuildHeight();
        final int worldFloor = targetLevel.getMinBuildHeight();
        final int posX = Mth.floor(originPos.getX());
        final int posY = originPos.getY() >= worldHeight ? 65 : Mth.floor(originPos.getY());
        final int posZ = Mth.floor(originPos.getZ());
        final BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos(posX, posY, posZ);

        try (BulkSectionAccess sectionAccess = new BulkSectionAccess(targetLevel)) {
            if (sectionAccess.getBlockState(checkPos).is(portalBlock)) {
                while (sectionAccess.getBlockState(checkPos.move(Direction.DOWN)).is(portalBlock));

                return updateLocalCache(targetLevel, checkPos);
            }

            for (int radius = 1; radius <= searchRadius; radius++) {
                for (int y = posY - radius; y <= posY + radius; y += radius * 2) {
                    if (y < worldFloor || y >= worldHeight)
                        continue;

                    int xNeg = -1;

                    for (int x = 0; x <= radius; x++) {
                        int x2 = posX + x * xNeg;

                        if (xNeg == 1 && x != 0)
                            x--;

                        xNeg *= -1;
                        int zNeg = -1;

                        for (int z = 0; z <= radius; z++) {
                            int z2 = posZ + z * zNeg;

                            if (zNeg == 1 && z != 0)
                                z--;

                            zNeg *= -1;

                            if (sectionAccess.getBlockState(checkPos.set(x2, y, z2)).is(portalBlock)) {
                                while (sectionAccess.getBlockState(checkPos.move(Direction.DOWN)).is(portalBlock));

                                return updateLocalCache(targetLevel, checkPos.above());
                            }
                        }
                    }
                }

                int yNeg = -1;

                for (int y = 0; y <= radius - 1; y++) {
                    int y2 = posY + y * yNeg;

                    if (y2 < worldFloor || y2 >= worldHeight)
                        continue;

                    if (yNeg == 1 && y != 0)
                        y--;

                    yNeg *= -1;
                    int zNeg = -1;

                    for (int z = 0; z <= radius; z++) {
                        int z2 = posZ + z * zNeg;

                        if (zNeg == 1 && z != 0)
                            z--;

                        zNeg *= -1;

                        for (int x = -radius; x <= radius; x += radius * 2) {
                            int x2 = posX + x;

                            if (sectionAccess.getBlockState(checkPos.set(x2, y2, z2)).is(portalBlock)) {
                                while (sectionAccess.getBlockState(checkPos.move(Direction.DOWN)).is(portalBlock));

                                return updateLocalCache(targetLevel, checkPos.above());
                            }
                        }
                    }

                    int xNeg = 1;

                    for (int x = 1; x <= radius - 1; x++) {
                        int x2 = posX + x * xNeg;

                        if (xNeg == 1 && x != 0)
                            x--;

                        xNeg *= -1;

                        for (int z = -radius; z <= radius; z += radius * 2) {
                            int z2 = posZ + z;

                            if (sectionAccess.getBlockState(checkPos.set(x2, y2, z2)).is(portalBlock)) {
                                while (sectionAccess.getBlockState(checkPos.move(Direction.DOWN)).is(portalBlock));

                                return updateLocalCache(targetLevel, checkPos.above());
                            }
                        }
                    }
                }
            }

            for (int x = posX - searchRadius; x <= posX + searchRadius; x++) {
                for (int z = posZ - searchRadius; z <= posZ + searchRadius; z++) {
                    checkPos.set(x, posY - searchRadius, z);

                    while (!sectionAccess.getBlockState(checkPos.move(Direction.DOWN)).is(portalBlock) && checkPos.getY() >= worldFloor);

                    if (sectionAccess.getBlockState(checkPos).is(portalBlock)) {
                        while (sectionAccess.getBlockState(checkPos.move(Direction.DOWN)).is(portalBlock));

                        return checkPos.above(2).immutable();
                    }
                }
            }

            for (int x = posX - searchRadius; x <= posX + searchRadius; x++) {
                for (int z = posZ - searchRadius; z <= posZ + searchRadius; z++) {
                    checkPos.set(x, posY + searchRadius, z);

                    while (!sectionAccess.getBlockState(checkPos.move(Direction.UP)).is(portalBlock) && checkPos.getY() < worldHeight);

                    if (sectionAccess.getBlockState(checkPos).is(portalBlock))
                        return checkPos.above().immutable();
                }
            }
        }

        return null;
    }

    default BlockPos findSuitablePortalLocation(Level level, Entity entity, BlockPos originPos) {
        final int searchRadius = AoAConfigs.SERVER.portalSearchRadius.get();
        final BoundingRegion boundingRegion = BoundingRegion.createForTeleportation(level);
        final BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
        final int posX = originPos.getX();
        final int posY = originPos.getY() >= boundingRegion.maxY() ?
                boundingRegion.isWithinY(level.getSeaLevel()) ?
                        level.getSeaLevel() + 2 :
                        (int)(boundingRegion.minY() + (boundingRegion.maxY() - boundingRegion.minY() / 2f)) :
                originPos.getY();
        final int posZ = originPos.getZ();
        BlockPos fallbackPos = null;

        try (BulkSectionAccess sectionAccess = new BulkSectionAccess(level)) {
            if (hasExistingSpaceForPortal(posX, posY, posZ, sectionAccess, boundingRegion)) {
                if (!sectionAccess.getBlockState(checkPos.set(posX, posY, posZ)).isAir())
                    return checkPos.set(posX, posY + 2, posZ).immutable();

                fallbackPos = checkPos.set(posX, posY + 2, posZ).immutable();
            }

            for (int radius = 1; radius <= searchRadius; radius++) {
                for (int y = (int)Math.max(posY - radius, boundingRegion.minY()); y <= (int)Math.min(posY + radius, boundingRegion.maxY()); y += radius * 2) {
                    int xNeg = -1;

                    for (int x = 0; x <= radius; x++) {
                        int x2 = posX + x * xNeg;

                        if (xNeg == 1 && x != 0)
                            x--;

                        xNeg *= -1;
                        int zNeg = -1;

                        for (int z = 0; z <= radius; z++) {
                            int z2 = posZ + z * zNeg;

                            if (zNeg == 1 && z != 0)
                                z--;

                            zNeg *= -1;

                            if (!sectionAccess.getBlockState(checkPos.set(x2, y, z2)).isAir()) {
                                if (hasExistingSpaceForPortal(x2, y, z2, sectionAccess, boundingRegion))
                                    return checkPos.set(x2, y + 2, z2).immutable();
                            }
                            else if (fallbackPos == null) {
                                if (hasExistingSpaceForPortal(x2, y, z2, sectionAccess, boundingRegion))
                                    fallbackPos = checkPos.set(x2, y + 2, z2).immutable();
                            }
                        }
                    }
                }

                int yNeg = -1;

                for (int y = 0; y <= radius - 1; y++) {
                    int y2 = posY + y * yNeg;

                    if (!boundingRegion.isAboveMinY(y2) || !boundingRegion.isBelowMaxY(y2 + 6))
                        continue;

                    if (yNeg == 1 && y != 0)
                        y--;

                    yNeg *= -1;
                    int zNeg = -1;

                    for (int z = 0; z <= radius; z++) {
                        int z2 = posZ + z * zNeg;

                        if (zNeg == 1 && z != 0)
                            z--;

                        zNeg *= -1;

                        for (int x = -radius; x <= radius; x += radius * 2) {
                            int x2 = posX + x;

                            if (!sectionAccess.getBlockState(checkPos.set(x2, y2, z2)).isAir()) {
                                if (hasExistingSpaceForPortal(x2, y2, z2, sectionAccess, boundingRegion))
                                    return checkPos.set(x2, y2 + 2, z2).immutable();
                            }
                            else if (fallbackPos == null) {
                                if (hasExistingSpaceForPortal(x2, y2, z2, sectionAccess, boundingRegion))
                                    fallbackPos = checkPos.set(x2, y2 + 2, z2).immutable();
                            }
                        }
                    }

                    int xNeg = 1;

                    for (int x = 1; x <= radius - 1; x++) {
                        int x2 = posX + x * xNeg;

                        if (xNeg == 1 && x != 0)
                            x--;

                        xNeg *= -1;

                        for (int z = -radius; z <= radius; z += radius * 2) {
                            int z2 = posZ + z;

                            if (!sectionAccess.getBlockState(checkPos.set(x2, y2, z2)).isAir()) {
                                if (hasExistingSpaceForPortal(x2, y2, z2, sectionAccess, boundingRegion))
                                    return checkPos.set(x2, y2 + 2, z2).immutable();
                            }
                            else if (fallbackPos == null) {
                                if (hasExistingSpaceForPortal(x2, y2, z2, sectionAccess, boundingRegion))
                                    fallbackPos = checkPos.set(x2, y2 + 2, z2).immutable();
                            }
                        }
                    }
                }
            }

            for (int x = posX - searchRadius; x <= posX + searchRadius; x++) {
                for (int z = posZ - searchRadius; z <= posZ + searchRadius; z++) {
                    checkPos.set(x, posY - searchRadius, z);

                    while (sectionAccess.getBlockState(checkPos.move(Direction.DOWN)).isAir() && checkPos.getY() >= 0);

                    int y = checkPos.getY();

                    if (hasExistingSpaceForPortal(x, y, z, sectionAccess, boundingRegion))
                        return checkPos.set(x, y + 2, z).immutable();
                }
            }

            for (int x = posX - searchRadius; x <= posX + searchRadius; x++) {
                for (int z = posZ - searchRadius; z <= posZ + searchRadius; z++) {
                    checkPos.set(x, boundingRegion.maxY() - 1 - portalSpaceHeight(), z);

                    while (sectionAccess.getBlockState(checkPos.move(Direction.DOWN)).isAir() && checkPos.getY() >= posY + searchRadius);

                    int y = checkPos.getY();

                    if (hasExistingSpaceForPortal(x, y, z, sectionAccess, boundingRegion))
                        return checkPos.set(x, y + 2, z).immutable();
                }
            }
        }

        if (fallbackPos != null)
            return fallbackPos;

        return BlockPos.containing(originPos.getX(), Math.clamp(originPos.getY() + 2, boundingRegion.minY() + 2, boundingRegion.maxY() - 10), originPos.getZ());
    }

    default boolean hasExistingSpaceForPortal(int x, int y, int z, BulkSectionAccess sectionAccess, BoundingRegion boundingRegion) {
        BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
        int widthRadius = Mth.ceil(portalFrameWidth() / 2f);
        int minX = x - widthRadius;
        int minY = y + 1;
        int minZ = z - widthRadius;
        int maxX = x + widthRadius;
        int maxY = y + portalSpaceHeight();
        int maxZ = z + widthRadius;

        if (!boundingRegion.isBelowMaxX(maxX) || !boundingRegion.isBelowMaxY(maxY) || !boundingRegion.isBelowMaxZ(maxZ) ||
                !boundingRegion.isAboveMinX(minX) || !boundingRegion.isAboveMinY(minY) || !boundingRegion.isAboveMinZ(minZ))
            return false;

        for (int x2 = minX; x2 <= maxX; x2++) {
            for (int z2 = minZ; z2 <= maxZ; z2++) {
                for (int y2 = minY; y2 <= maxY; y2++) {
                    if (!sectionAccess.getBlockState(checkPos.set(x2, y2, z2)).isAir())
                        return false;
                }
            }
        }

        return true;
    }

    default BlockPos createPortalFrameAndSpace(Level level, Entity entity, BlockPos pos) {
        if (WorldUtil.isWorld(level, AoADimensions.OVERWORLD))
            return pos;

        final BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
        final BlockState border = getPortalFrame().defaultBlockState();
        final Direction.Axis direction = EntityUtil.getDirectionFacing(entity, true).getAxis();
        final BlockState portal = getPortalBlock().defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_AXIS, direction == Direction.Axis.X ? Direction.Axis.X : Direction.Axis.Z);
        int posX = pos.getX();
        int posY = pos.getY() - 1;
        int posZ = pos.getZ();

        if (!level.isEmptyBlock(pos)) {
            final BlockState air = Blocks.AIR.defaultBlockState();

            for (int x = posX - 3; x <= posX + 3; x++) {
                for (int z = posZ - 3; z <= posZ + 3; z++) {
                    for (int y = posY + 1; y <= posY + 4; y++) {
                        level.setBlock(mutablePos.set(x, y, z), air, Block.UPDATE_CLIENTS);
                    }
                }
            }
        }

        if (direction == Direction.Axis.X) {
            for (int z = posZ - 2; z <= posZ + 2; z++) {
                level.setBlock(mutablePos.set(posX, posY, z), border, Block.UPDATE_CLIENTS);
                level.setBlock(mutablePos.set(posX, posY + 5, z), border, Block.UPDATE_CLIENTS);
            }

            for (int y = posY + 1; y <= posY + 4; y++) {
                level.setBlock(mutablePos.set(posX, y, posZ - 2), border, Block.UPDATE_CLIENTS);
                level.setBlock(mutablePos.set(posX, y, posZ - 1), portal, Block.UPDATE_CLIENTS);
                level.setBlock(mutablePos.set(posX, y, posZ), portal, Block.UPDATE_CLIENTS);
                level.setBlock(mutablePos.set(posX, y, posZ + 1), portal, Block.UPDATE_CLIENTS);
                level.setBlock(mutablePos.set(posX, y, posZ + 2), border, Block.UPDATE_CLIENTS);
            }
        }
        else {
            for (int x = posX - 2; x <= posX + 2; x++) {
                level.setBlock(mutablePos.set(x, posY, posZ), border, Block.UPDATE_CLIENTS);
                level.setBlock(mutablePos.set(x, posY + 5, posZ), border, Block.UPDATE_CLIENTS);
            }

            for (int y = posY + 1; y <= posY + 4; y++) {
                level.setBlock(mutablePos.set(posX - 2, y, posZ), border, Block.UPDATE_CLIENTS);
                level.setBlock(mutablePos.set(posX - 1, y, posZ), portal, Block.UPDATE_CLIENTS);
                level.setBlock(mutablePos.set(posX, y, posZ), portal, Block.UPDATE_CLIENTS);
                level.setBlock(mutablePos.set(posX + 1, y, posZ), portal, Block.UPDATE_CLIENTS);
                level.setBlock(mutablePos.set(posX + 2, y, posZ), border, Block.UPDATE_CLIENTS);
            }
        }

        posY--;

        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                mutablePos.set(posX + x, posY, posZ);

                if (!level.getBlockState(mutablePos).isFaceSturdy(level, mutablePos, Direction.UP)) {
                    createPortalBaseAndDecorations(level, new BlockPos(posX, posY, posZ), direction);

                    return pos;
                }
            }
        }

        return pos;
    }

    default void createPortalBaseAndDecorations(Level level, BlockPos pos, Direction.Axis direction) {
        final BlockState border = getPortalFrame().defaultBlockState();

        for (int x = pos.getX() - 2; x <= pos.getX() + 2; x++) {
            for (int z = pos.getZ() - 2; z <= pos.getZ() + 2; z++) {
                level.setBlock(new BlockPos(x, pos.getY(), z), border, Block.UPDATE_CLIENTS);
            }
        }
    }
}
