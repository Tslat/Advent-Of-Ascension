package net.tslat.aoa3.client.player;

import net.minecraft.client.KeyMapping;
import net.tslat.aoa3.player.AoAPlayerEventListener;

import java.util.function.Consumer;

/**
 * Clientside listener for keybind actions - used for {@link AoAPlayerEventListener#createKeybindListener(Consumer)}
 * This allows client-side code separation in {@link AoAPlayerEventListener AoAPlayerEventListeners}
 */
public interface AoAPlayerKeybindListener {
    /**
     * Return the instance of {@link AoAPlayerEventListener} this keybind listener belongs to
     */
    AoAPlayerEventListener getEventListener();

    /**
     * Whether the listener this keybind listener belongs to is active
     */
    default boolean isListenerActive() {
        return getEventListener().getListenerState() == AoAPlayerEventListener.ListenerState.ACTIVE;
    }

    /**
     * Return the keymapping for this listener.
     * This must be a standard registered keybind
     */
    KeyMapping getKeybind();

    /**
     * Determine whether the detected keypress action should be sent to the server side
     */
    boolean shouldSendKeyPress();
}
