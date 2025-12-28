package net.tslat.aoa3.content.entity.projectile.base;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.advent.Logging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.ref.WeakReference;

public interface WeaponFiringContext {
    float damage();
    float velocity();
    float inaccuracy();
    int projectileLifespan();
    int piercingValue();
    @NotNull
    ItemStack weaponStack();
    @NotNull
    InteractionHand weaponHand();
    WeakReference<@Nullable Entity> shooter();

    CompoundTag toNbt(HolderLookup.Provider registryAccess);

    @Nullable
    default Entity getShooter() {
        return shooter().get();
    }

    class Builder {
        private final ItemStack weaponStack;
        private final InteractionHand weaponHand;
        private final WeakReference<Entity> shooter;
        private float damage = 0f;
        private float velocity = 3;
        private float inaccuracy = 0;
        private int lifespan = 120;
        private int piercing = 0;

        private Builder(ItemStack weaponStack, WeakReference<@Nullable Entity> shooter, InteractionHand weaponHand) {
            this.weaponStack = weaponStack;
            this.weaponHand = weaponHand;
            this.shooter = shooter;
        }

        public static Builder copyOf(WeaponFiringContext context) {
            return of(context.weaponStack(), context.getShooter(), context.weaponHand())
                    .damage(context.damage())
                    .velocity(context.velocity())
                    .degreesInaccuracy(context.inaccuracy())
                    .lifespan(context.projectileLifespan())
                    .pierceThrough(context.piercingValue());
        }

        public static Builder of(ItemStack weaponStack, @Nullable Entity shooter) {
            return of(weaponStack, shooter, InteractionHand.MAIN_HAND);
        }

        public static Builder of(ItemStack weaponStack, @Nullable Entity shooter, InteractionHand weaponHand) {
            return new Builder(weaponStack, new WeakReference<>(shooter), weaponHand);
        }

        public Builder damage(float damage) {
            this.damage = damage;

            return this;
        }

        public Builder addDamage(float damage) {
            this.damage += damage;

            return this;
        }

        public Builder subtractDamage(float damage) {
            this.damage -= damage;

            return this;
        }

        public Builder multiplyDamage(float damageMod) {
            this.damage *= damageMod;

            return this;
        }

        public Builder velocity(float velocity) {
            this.velocity = velocity;

            return this;
        }

        public Builder degreesInaccuracy(float inaccuracy) {
            this.inaccuracy = inaccuracy;

            return this;
        }

        public Builder lifespan(int lifespan) {
            this.lifespan = lifespan;

            return this;
        }

        public Builder pierceThrough(int count) {
            this.piercing = count;

            return this;
        }

        public Instance build() {
            return new Instance(this.damage, this.velocity, this.inaccuracy, this.lifespan, this.piercing, this.weaponStack, this.weaponHand, this.shooter);
        }
    }

    record Instance(float damage, float velocity, float inaccuracy, int projectileLifespan, int piercingValue, @NotNull ItemStack weaponStack, @NotNull InteractionHand weaponHand, WeakReference<@Nullable Entity> shooter) implements WeaponFiringContext {
        public static Instance fromNbt(CompoundTag nbt, Level level) {
            float damage = nbt.contains("damage", Tag.TAG_FLOAT) ? nbt.getFloat("damage") : 0f;
            float velocity = nbt.contains("velocity", Tag.TAG_FLOAT) ? nbt.getFloat("velocity") : 3;
            float inaccuracy = nbt.contains("inaccuracy", Tag.TAG_FLOAT) ? nbt.getFloat("inaccuracy") : 0;
            int projectileLifespan = nbt.contains("projectileLifespan", Tag.TAG_INT) ? nbt.getInt("projectileLifespan") : 120;
            int piercingValue = nbt.contains("piercingValue", Tag.TAG_INT) ? nbt.getInt("piercingValue") : 0;
            ItemStack weaponStack = ItemStack.parseOptional(level.registryAccess(), nbt.getCompound("weaponStack"));
            InteractionHand weaponHand;

            try {
                weaponHand = Enum.valueOf(InteractionHand.class, nbt.getString("weaponHand"));
            }
            catch (IllegalArgumentException ex) {
                Logging.logMessage(org.apache.logging.log4j.Level.ERROR, "Invalid hand in saved WeaponFiringContext: " + nbt.getString("weaponHand"));
                weaponHand = InteractionHand.MAIN_HAND;
            }

            WeakReference<@Nullable Entity> shooter;

            if (nbt.contains("shooter", Tag.TAG_INT_ARRAY)) {
                shooter = new WeakReference<>(level.getEntities().get(nbt.getUUID("shooter")));
            }
            else {
                shooter = new WeakReference<>(null);
            }

            return new Instance(damage, velocity, inaccuracy, projectileLifespan, piercingValue, weaponStack, weaponHand, shooter);
        }

        public CompoundTag toNbt(HolderLookup.Provider registryAccess) {
            CompoundTag nbt = new CompoundTag();

            nbt.putFloat("damage", this.damage);
            nbt.putFloat("velocity", this.velocity);
            nbt.putFloat("inaccuracy", this.inaccuracy);
            nbt.putInt("projectileLifespan", this.projectileLifespan);
            nbt.putInt("piercingValue", this.piercingValue);

            if (!this.weaponStack.isEmpty())
                nbt.put("weaponStack", this.weaponStack.save(registryAccess));

            nbt.putString("weaponHand", this.weaponHand.name());

            if (this.shooter.get() != null)
                nbt.putUUID("shooter", this.shooter.get().getUUID());

            return nbt;
        }
    }
}
