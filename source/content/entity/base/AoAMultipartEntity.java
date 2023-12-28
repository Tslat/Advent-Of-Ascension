package net.tslat.aoa3.content.entity.base;

import net.minecraft.world.entity.Entity;
import net.minecraftforge.entity.PartEntity;
import net.tslat.aoa3.common.packet.AoANetworking;
import net.tslat.aoa3.common.packet.packets.MultipartTogglePacket;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

public interface AoAMultipartEntity {
    void setParts(AoAEntityPart<?>... parts);
    AoAEntityPart<?>[] getParts();

    default AoAEntityPart<?>[] defineParts(AtomicInteger idCounter, IntConsumer idSetter, AoAEntityPart<?>... parts) {
        if (getParts().length > 0)
            throw new IllegalStateException("Cannot add more parts after having already done so!");

        idSetter.accept(idCounter.getAndAdd(parts.length + 1) + 1);

        return parts;
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
