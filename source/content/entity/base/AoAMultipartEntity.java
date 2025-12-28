package net.tslat.aoa3.content.entity.base;

import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntArrayTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.neoforged.neoforge.common.extensions.IEntityExtension;
import net.neoforged.neoforge.entity.PartEntity;
import net.tslat.aoa3.common.networking.AoANetworking;
import net.tslat.aoa3.common.networking.packets.MultipartTogglePacket;
import net.tslat.aoa3.library.builder.MultipartBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public interface AoAMultipartEntity<T extends LivingEntity & AoAMultipartEntity<T>> extends IEntityExtension {
    @Nullable
    MultipartBuilder<? extends T> definePartEntities();
    @NotNull
    AoAEntityPart<T>[] getParts();

    default void registerParts(AtomicInteger idCounter, IntConsumer idSetter, Consumer<AoAEntityPart<T>[]> partConsumer) {
        AoAEntityPart<T>[] parts = MultipartBuilder.findOrCreate((T)this);

        if (parts == null || parts.length == 0)
            return;

        partConsumer.accept(parts);
        idSetter.accept(idCounter.getAndAdd(parts.length + 1) + 1);
    }

    default void setParts(AoAEntityPart<?>... parts) {

    }

    default void setMultipartIds(int baseId) {
        int newId = baseId + 1;

        for (PartEntity<?> part : getParts()) {
            part.setId(newId++);
        }
    }

    default void updateMultipartPositions() {
        for (AoAEntityPart<?> part : getParts()) {
            part.updatePosition();
        }
    }

    default void refreshMultipartDimensions() {
        if (isMultipartActive()) {
            for (AoAEntityPart<?> part : getParts()) {
                part.refreshDimensions();
            }
        }
    }

    default void toggleMultipart(boolean enabled) {
        for (AoAEntityPart<?> part : getParts()) {
            part.setEnabled(enabled);
        }

        if (this instanceof LivingEntity self && !self.level().isClientSide())
            AoANetworking.sendToAllPlayersTrackingEntity(new MultipartTogglePacket(self.getId(), enabled), self);
    }

    default boolean isMultipartActive() {
        for (AoAEntityPart<?> part : getParts()) {
            if (part.isEnabled())
                return true;
        }

        return false;
    }

    default void saveMultiparts(CompoundTag nbt) {
        AoAEntityPart<?>[] parts = getParts();

        if (parts.length > 0) {
            List<Integer> disabledParts = new ObjectArrayList<>();

            for (int i = 0; i < parts.length; i++) {
                if (!parts[i].isEnabled())
                    disabledParts.add(i);
            }

            if (!disabledParts.isEmpty())
                nbt.put("DisabledMultiparts", new IntArrayTag(disabledParts));
        }
    }

    default void loadMultiparts(CompoundTag nbt) {
        if (nbt.contains("DisabledMultiparts", Tag.TAG_INT_ARRAY)) {
            final AoAEntityPart<?>[] parts = getParts();

            for (int i : nbt.getIntArray("DisabledMultiparts")) {
                parts[i].setEnabled(false);
            }
        }
    }

    default void onMultipartParentHurt(DamageSource source) {
        final AoAEntityPart<?>[] parts = getParts();

        if (parts.length > 0 && source.getDirectEntity() instanceof AbstractArrow arrow && arrow.getPierceLevel() > 0 && this instanceof LivingEntity self) {
            if (arrow.piercingIgnoreEntityIds == null)
                arrow.piercingIgnoreEntityIds = new IntOpenHashSet(5);

            for (AoAEntityPart<?> part : parts) {
                arrow.piercingIgnoreEntityIds.add(part.getId());
            }

            arrow.piercingIgnoreEntityIds.add(self.getId());
        }
    }
}
