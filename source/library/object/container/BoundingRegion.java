package net.tslat.aoa3.library.object.container;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.border.WorldBorder;

public record BoundingRegion(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
    public static BoundingRegion createFromLevel(Level level) {
        WorldBorder border = level.getWorldBorder();

        return new BoundingRegion(border.getMinX(), level.getMinBuildHeight(), border.getMinZ(), border.getMaxX(), level.getMaxBuildHeight(), border.getMaxZ());
    }

    public static BoundingRegion createForTeleportation(Level level) {
        WorldBorder border = level.getWorldBorder();

        return new BoundingRegion(border.getMinX(), level.getMinBuildHeight(), border.getMinZ(), border.getMaxX(), level.getMinBuildHeight() + level.dimensionType().logicalHeight(), border.getMaxZ());
    }

    public BoundingRegion createRestricted(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return new BoundingRegion(
                Math.max(this.minX, minX),
                Math.max(this.minY, minY),
                Math.max(this.minZ, minZ),
                Math.min(this.maxX, maxX),
                Math.min(this.maxY, maxY),
                Math.min(this.maxZ, maxZ));
    }

    public double clampX(double x) {
        return Math.max(this.minX, Math.min(this.maxX, x));
    }

    public boolean isNegativeRegion() {
        return this.maxX < this.minX || this.maxY < this.minY || this.maxZ < this.minZ;
    }

    public boolean isWithinX(double x) {
        return x >= this.minX && x <= this.maxX;
    }

    public boolean isWithinY(double y) {
        return y >= this.minY && y <= this.maxY;
    }

    public boolean isWithinZ(double z) {
        return z >= this.minZ && z <= this.maxZ;
    }

    public boolean isWithin(double x, double y, double z) {
        return isWithinX(x) && isWithinY(y) && isWithinZ(z);
    }

    public boolean isAboveMinX(double x) {
        return x >= this.minX;
    }

    public boolean isAboveMinY(double y) {
        return y >= this.minY;
    }

    public boolean isAboveMinZ(double z) {
        return z >= this.minZ;
    }

    public boolean isBelowMaxX(double x) {
        return x <= this.maxX;
    }

    public boolean isBelowMaxY(double y) {
        return y <= this.maxY;
    }

    public boolean isBelowMaxZ(double z) {
        return z <= this.maxZ;
    }
}
