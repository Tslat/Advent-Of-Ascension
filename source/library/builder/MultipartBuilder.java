package net.tslat.aoa3.library.builder;

import it.unimi.dsi.fastutil.Function;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.content.entity.base.AoAEntityPart;
import net.tslat.aoa3.content.entity.base.AoAMultipartEntity;
import net.tslat.aoa3.library.object.interfaces.TriFunction;
import org.jetbrains.annotations.Nullable;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public record MultipartBuilder<T extends LivingEntity>(T parent, boolean dynamic, Part<T>[] parts) {
    public static Map<Class<?>, Function<AoAMultipartEntity<?>, AoAEntityPart<? extends LivingEntity>[]>> CACHE = new IdentityHashMap<>();

    public static <T extends LivingEntity> MultipartBuilder<? extends T> of(T parent, Part... parts) {
        return new MultipartBuilder<T>(parent, false, parts);
    }

    public static <T extends LivingEntity> MultipartBuilder<? extends T> ofDynamic(T parent, Part... parts) {
        return new MultipartBuilder<T>(parent, true, parts);
    }

    @Nullable
    public static <T extends LivingEntity & AoAMultipartEntity<T>> AoAEntityPart<T>[] findOrCreate(T parent) {
        AoAEntityPart<T>[] parts = (AoAEntityPart<T>[])CACHE.getOrDefault(parent.getClass(), key -> null).apply(parent);

        if (parts != null)
            return parts;

        MultipartBuilder<T> builder = (MultipartBuilder)parent.definePartEntities();

        if (builder == null || builder.parts.length == 0)
            return null;

        return builder.build();
    }

    public AoAEntityPart<T>[] build() {
        ObjectArrayList<AoAEntityPart<T>> parts = new ObjectArrayList<>();
        List<Part.Baked<T>> bakedParts = this.dynamic ? null : new ObjectArrayList<>();

        for (Part<T> part : this.parts) {
            buildPart(part, null, parts, bakedParts);
        }

        AoAEntityPart<T>[] result = parts.toArray(new AoAEntityPart[0]);

        if (!this.dynamic)
           CACHE.put(this.parent.getClass(), parent -> {
               AoAEntityPart<T>[] newParts = new AoAEntityPart[result.length];

               for (int i = 0; i < result.length; i++) {
                   newParts[i] = bakedParts.get(i).toAoAEntityPart((T)parent);
               }

               return newParts;
           });

        return result;
    }

    private void buildPart(Part<T> part, @Nullable AoAEntityPart<T> parent, List<AoAEntityPart<T>> parts, @Nullable List<Part.Baked<T>> bakedParts) {
        AoAEntityPart<T> lastPart = part.toAoAEntityPart(this.parent, parent);

        parts.add(lastPart);

        if (bakedParts != null)
            bakedParts.add(part.bake(lastPart.getPosOffset()));

        for (Part<T> child : part.children) {
            buildPart(child, lastPart, parts, bakedParts);
        }
    }

    public static class Part<T extends LivingEntity> {
        private final float width;
        private final float height;
        private final List<Part<T>> children = new ObjectArrayList<>();

        private Float forward;
        private Float up;
        private Float left;

        @Nullable
        private Direction verticalRelation = null;
        private float damageModifier = 1;

        private TriFunction<T, EntityDimensions, Vec3, AoAEntityPart<T>> constructor = AoAEntityPart::new;

        private Part(float width, float height) {
            this.width = width;
            this.height = height;
        }

        public static <T extends LivingEntity> Part<T> sized(float width, float height) {
            return new Part<>(width, height);
        }

        public Part<T> adjacentForward() {
            return adjacentForward(0);
        }

        public Part<T> adjacentForward(float offset) {
            return forward(this.width / 2f + offset);
        }

        public Part<T> forward(float forward) {
            if (forward == 0)
                forward = Float.MIN_NORMAL;

            this.forward = forward;

            return this;
        }

        public Part<T> adjacentBehind() {
            return adjacentBehind(0);
        }

        public Part<T> adjacentBehind(float offset) {
            return back(this.width / 2f + offset);
        }

        public Part<T> back(float back) {
            if (back == 0)
                back = Float.MIN_NORMAL;

            return forward(-back);
        }

        public Part<T> adjacentAbove() {
            this.verticalRelation = Direction.UP;

            return this;
        }

        public Part<T> adjacentAbove(float offset) {
            adjacentAbove();

            return up(offset);
        }

        public Part<T> up(float up) {
            if (up == 0)
                up = Float.MIN_NORMAL;

            this.up = up;

            return this;
        }

        public Part<T> adjacentBelow() {
            this.verticalRelation = Direction.DOWN;

            return this;
        }

        public Part<T> adjacentBelow(float offset) {
            adjacentBelow();

            return down(offset);
        }

        public Part<T> down(float down) {
            if (down == 0)
                down = Float.MIN_NORMAL;

            return up(-down);
        }

        public Part<T> adjacentLeft() {
            return adjacentLeft(0);
        }

        public Part<T> adjacentLeft(float offset) {
            return left(this.width / 2f + offset);
        }

        public Part<T> left(float left) {
            if (left == 0)
                left = Float.MIN_NORMAL;

            this.left = left;

            return this;
        }

        public Part<T> adjacentRight() {
            return adjacentRight(0);
        }

        public Part<T> adjacentRight(float offset) {
            return right(this.width / 2f + offset);
        }

        public Part<T> right(float right) {
            if (right == 0)
                right = Float.MIN_NORMAL;

            return left(-right);
        }

        public Part<T> damageMod(float damageModifier) {
            this.damageModifier = damageModifier;

            return this;
        }

        public Part<T> constructor(TriFunction<T, EntityDimensions, Vec3, AoAEntityPart<T>> constructor) {
            this.constructor = constructor;

            return this;
        }

        public Part<T> then(Part child) {
            this.children.add(child);

            return this;
        }

        public AoAEntityPart<T> toAoAEntityPart(T parent, @Nullable AoAEntityPart<T> neighbour) {
            Entity relative = neighbour == null ? parent : neighbour;
            double parentOffset = relative.getBbWidth() / 2f;
            Vec3 offset = new Vec3(
                    this.left == null ? 0 : this.left + parentOffset * Mth.sign(this.left),
                    this.up == null ? 0 : this.up,
                    this.forward == null ? 0 : this.forward + parentOffset * Mth.sign(this.forward));

            if (this.verticalRelation != null)
                offset = offset.add(0, this.verticalRelation == Direction.UP ? relative.getBbHeight() : -this.height, 0);

            return this.constructor.apply(parent, EntityDimensions.scalable(this.width, this.height), neighbour == null ? offset : offset.add(neighbour.getPosOffset())).setDamageMultiplier(this.damageModifier);
        }

        Baked<T> bake(Vec3 offsetPos) {
            return new Baked<>(this.width, this.height, offsetPos, this.damageModifier, this.constructor);
        }

        record Baked<T extends LivingEntity>(float width, float height, Vec3 offset, float damageMod, TriFunction<T, EntityDimensions, Vec3, AoAEntityPart<T>> constructor) {
            AoAEntityPart<T> toAoAEntityPart(T parent) {
                return this.constructor.apply(parent, EntityDimensions.scalable(this.width, this.height), this.offset).setDamageMultiplier(this.damageMod);
            }
        }
    }
}
