package net.tslat.aoa3.event.dynamic;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import net.neoforged.bus.api.Event;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Special-handler class that allows for efficient event subscription/unsubscription at the cost of loss of event-prioritisation and cancellation-handling policy.
 * <p>
 * This gives a performant entrypoint for event listeners that may or may not be active
 */
public final class DynamicEventSubscriptions {
    private static final Map<Class<? extends Event>, Set<DynamicEventSubscriber<? extends Event>>> LISTENERS = new ConcurrentHashMap<>();
    private static final Map<Class<? extends Event>, List<DynamicEventSubscriber<? extends Event>>> NEW_LISTENERS = new Reference2ObjectOpenHashMap<>();
    private static boolean isDirty = false;

    public static void init() {
        NeoForge.EVENT_BUS.addListener(DynamicEventSubscriptions::addNewSubscribers);
    }

    /**
     * Add a group of listeners to the dynamic event handler, to be checked and organised at the end of the current tick.
     * <p>
     * This also acts as an add/remove entrypoint, since it's functionally the same thing
     */
    public static void addListeners(DynamicEventSubscriber<? extends Event>... subscribers) {
        addListeners(List.of(subscribers));
    }

    /**
     * Mark a group of subscribers as dirty, to be checked and organised at the end of the current tick.
     * <p>
     * This also acts as an add/remove entrypoint, since it's functionally the same thing
     */
    public static void addListeners(Collection<? extends DynamicEventSubscriber<? extends Event>> subscribers) {
        synchronized (NEW_LISTENERS) {
            for (DynamicEventSubscriber<? extends Event> subscriber : subscribers) {
                assert subscriber.eventClass() != PlayerEvent.Clone.class && subscriber.eventClass() != PlayerEvent.PlayerRespawnEvent.class;

                NEW_LISTENERS.computeIfAbsent(subscriber.eventClass(), key -> new ObjectArrayList<>()).add(subscriber);
            }

            isDirty = true;
        }
    }

    private static void addNewSubscribers(final ServerTickEvent.Post ev) {
        if (!isDirty)
            return;

        synchronized (NEW_LISTENERS) {
            for (Map.Entry<Class<? extends Event>, List<DynamicEventSubscriber<? extends Event>>> entry : NEW_LISTENERS.entrySet()) {
                Class<? extends Event> eventClass = entry.getKey();
                Set<DynamicEventSubscriber<? extends Event>> listeners;

                if (!LISTENERS.containsKey(eventClass)) {
                    listeners = LISTENERS.computeIfAbsent(eventClass, k -> new CopyOnWriteArraySet<>());

                    NeoForge.EVENT_BUS.addListener(eventClass, event -> {
                        List<DynamicEventSubscriber<? extends Event>> toRemove = new ObjectArrayList<>();

                        for (DynamicEventSubscriber subscriber : listeners) {
                            if (!subscriber.isStillValid()) {
                                toRemove.add(subscriber);

                                continue;
                            }

                            subscriber.accept(event);
                        }

                        if (!toRemove.isEmpty())
                            listeners.removeAll(toRemove);
                    });
                }
                else {
                    listeners = LISTENERS.get(eventClass);
                }

                listeners.addAll(entry.getValue());
                entry.getValue().clear();
            }
        }

        isDirty = false;
    }
}
