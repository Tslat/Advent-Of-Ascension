package net.tslat.aoa3.library.builder;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoAGameRules;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.tme.api.explosion.ExplosionBuilder;
import net.tslat.tme.api.explosion.ExplosionInfo;
import net.tslat.tme.api.explosion.ExtendedExplosion;
import net.tslat.tme.internal.TMEConstants;

import java.util.function.Predicate;

public class AoAExplosionBuilder<T extends ExtendedExplosion> extends ExplosionBuilder<T> {
    protected AoAExplosionBuilder(ExplosionInfo info, ServerLevel level, Vec3 position, Constructor<T> constructor) {
        super(info, level, position, constructor);
    }

    //region <Factories>

    /**
     * Create a new ExplosionBuilder for a given entity at its position
     */
    public static <T extends ExtendedExplosion> AoAExplosionBuilder<T> at(Entity explodingEntity, ExplosionInfo explosionInfo, Constructor<T> explosionType) {
        if (!(explodingEntity.level() instanceof ServerLevel level))
            throw new IllegalStateException("Attempted to instantiate an ExtendedExplosion on the client side! Explosions must be handled on the server side!");

        return at(level, explodingEntity.getX(), explodingEntity.getY(0.5f), explodingEntity.getZ(), explosionInfo, explosionType).explodingEntity(explodingEntity);
    }

    /**
     * Create a new AoAExplosionBuilder at a given position
     */
    public static <T extends ExtendedExplosion> AoAExplosionBuilder<T> at(ServerLevel level, double x, double y, double z, ExplosionInfo explosionInfo, Constructor<T> explosionType) {
        return at(level, new Vec3(x, y, z), explosionInfo, explosionType);
    }

    /**
     * Create a new AoAExplosionBuilder at the center of a given Block position
     */
    public static <T extends ExtendedExplosion> AoAExplosionBuilder<T> at(ServerLevel level, BlockPos position, ExplosionInfo explosionInfo, Constructor<T> explosionType) {
        return at(level, Vec3.atCenterOf(position), explosionInfo, explosionType);
    }

    /**
     * Create a new AoAExplosionBuilder at a given position
     */
    public static <T extends ExtendedExplosion> AoAExplosionBuilder<T> at(ServerLevel level, Vec3 position, ExplosionInfo explosionInfo, Constructor<T> explosionType) {
        return new AoAExplosionBuilder<>(explosionInfo, level, position, explosionType).customGriefCheck(AoAExplosionBuilder::aoaGriefingCheck);
    }

    //endregion
    //region <Setters>

    /**
     * Set an entity as the origin/direct-source of the explosion<br>
     * E.G. The exploding TNT entity
     */
    public AoAExplosionBuilder<T> explodingEntity(Entity explodingEntity) {
        return (AoAExplosionBuilder<T>)super.explodingEntity(explodingEntity);
    }

    /**
     * Set an entity as the indirect cause of the explosion<br>
     * E.G. The owner of the TNT that is exploding
     */
    public AoAExplosionBuilder<T> indirectSource(Entity indirectExploder) {
        return (AoAExplosionBuilder<T>)super.indirectSource(indirectExploder);
    }

    /**
     * Set a custom DamageSource to use when damaging entities with this explosion
     * <p>
     * Defaults to {@link DamageSources#explosion(Explosion)}
     */
    public AoAExplosionBuilder<T> damageSource(DamageSource damageSource) {
        return (AoAExplosionBuilder<T>)super.damageSource(damageSource);
    }

    /**
     * Set a custom DamageCalculator to use when determining block and entity damage for this explosion
     * <p>
     * Defaults to {@link Explosion#makeDamageCalculator(Entity)}
     */
    public AoAExplosionBuilder<T> damageCalculator(ExplosionDamageCalculator damageCalculator) {
        return (AoAExplosionBuilder<T>)super.damageCalculator(damageCalculator);
    }

    /**
     * Set a custom Predicate for checking block griefing potential for the explosion.
     * <p>
     * Useful if your mod has custom logic for block griefing in the world
     * <p>
     * Defaults to {@link AoAExplosionBuilder#defaultGriefingCheck(ExtendedExplosion)}
     */
    public AoAExplosionBuilder<T> customGriefCheck(Predicate<ExtendedExplosion> predicate) {
        return (AoAExplosionBuilder<T>)super.customGriefCheck(predicate);
    }

    //endregion

    protected static <T extends ExtendedExplosion> boolean aoaGriefingCheck(T explosion) {
        if (!explosion.getProperties().damagesBlocks())
            return false;

        Entity source = explosion.getOwnerOrSource();

        if (PlayerUtil.getPlayerOrOwnerIfApplicable(source) instanceof Player)
            return AoAGameRules.checkDestructiveWeaponPhysics(explosion.level);

        return TMEConstants.PLATFORM.canMobGrief(explosion.level, source);
    }
}
