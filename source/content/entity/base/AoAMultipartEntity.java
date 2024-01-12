package net.tslat.aoa3.content.entity.base;

import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.entity.PartEntity;
import net.tslat.aoa3.common.networking.AoANetworking;
import net.tslat.aoa3.common.networking.packets.MultipartTogglePacket;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

public interface AoAMultipartEntity {
    void setParts(AoAEntityPart<?>... parts);
    AoAEntityPart<?>[] getParts();

    default void defineParts(AtomicInteger idCounter, IntConsumer idSetter, AoAEntityPart<?>... parts) {
        idSetter.accept(idCounter.getAndAdd(parts.length + 1) + 1);
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

        final Entity self = (Entity)this;

        if (!self.level().isClientSide())
            AoANetworking.sendToAllPlayersTrackingEntity(new MultipartTogglePacket(self.getId(), enabled), self);
    }

    default boolean isMultipartActive() {
        for (AoAEntityPart<?> part : getParts()) {
            if (part.isEnabled())
                return true;
        }

        return false;
    }
}
