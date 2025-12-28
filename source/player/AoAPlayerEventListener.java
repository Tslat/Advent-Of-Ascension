package net.tslat.aoa3.player;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.Event;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.tslat.aoa3.client.player.AoAPlayerKeybindListener;
import net.tslat.aoa3.event.dynamic.DynamicEventSubscriber;
import net.tslat.aoa3.event.dynamic.DynamicEventSubscriptions;
import net.tslat.aoa3.library.constant.ScreenImageEffect;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.tme.api.util.RandomUtil;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.internal.networking.packet.TMEParticlePacket;
import org.jetbrains.annotations.ApiStatus;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Generic interface for an instantiable object that can listen to one or more {@link Event events}.
 * <p>
 * Events may not be automatically subscribed - this interface is for managing objects to handle event subscriptions.
 * <p>
 * Contains specialty-event convenience methods for ease of use and consistency.
 */
public interface AoAPlayerEventListener {
	/**
	 * Get all event subscribers for this listener.
	 * <p>
	 * Returned subscribers <b><u>MUST</u></b> be statically instantiated so that reference parity can match them
	 */
	List<DynamicEventSubscriber<? extends Event>> getEventSubscribers();

	/**
	 * Get the event subscriber for a specified event, if it is one of the event subscribers this listener is listening to.
	 */
	default <T extends Event> Optional<DynamicEventSubscriber<T>> getEventSubscriber(Class<T> eventClass) {
		for (DynamicEventSubscriber<? extends Event> subscriber : getEventSubscribers()) {
			if (subscriber.eventClass() == eventClass)
				return Optional.of((DynamicEventSubscriber<T>)subscriber);
		}

		return Optional.empty();
	}

	/**
	 * Used to determine whether the listener should be receiving event calls.
	 * <p>
	 * Override to handle enabling/disabling as needed.
	 *
	 * @return whether the listener is currently active.
	 */
	default ListenerState getListenerState() {
		return ListenerState.ACTIVE;
	}

	/**
	 * Used to determine whether the listener meets the requirements relevant to stay active.
	 * <p>
	 * This check is only made if the listener is currently active, and returning false will call for the listener to be disabled.
	 *
	 * @return whether the listener currently meets any requirements necessary to stay active.
	 */
	default boolean meetsRequirements() {
		return true;
	}

	/**
	 * Get the player this listener is for
	 */
	Player getPlayer();

	/**
	 * Called when your listener is enabled from a previously disabled state.
	 * <p>
	 * Usually this occurs when the player manually re-enables it, or if AoA determines the listener now meets previously un-met conditions
	 */
	default void reenable(boolean isInit) {}

	/**
	 * Called when the listener is disabled regardless of the source. Usually called when the listener returns false from {@link #meetsRequirements()}.
	 * <p>
	 * If your listener isn't able to be disabled, then leave this method as default.
	 */
	default void disable(ListenerState reason, boolean isInit) {}

	/**
	 * Whether the handler should be considered valid or not.
	 * This should be considered a getter for whether this subscriber should be discarded.
	 * <p>
	 * Returning false will prevent this listener from being added to {@link DynamicEventSubscriptions}, and will remove it if it is already added
	 */
	default boolean isStillValid() {
		return getListenerState() == ListenerState.ACTIVE;
	}

	/**
	 * If this listener is still valid, register all {@link #getEventSubscribers() event subscribers}
	 */
	default void registerEventSubscribers() {
		if (isStillValid())
			DynamicEventSubscriptions.addListeners(getEventSubscribers());
	}

	/**
	 * Create a new immutable {@link DynamicEventSubscriber} implementation
	 *
	 * @param eventClass The class of the event to listen to
	 * @param entityGetter A function to retrieve the player this listener is for from the given event
	 * @param handler The handler method for the event when called
	 */
	default <T extends Event> DynamicEventSubscriber<T> listener(final Class<T> eventClass, Function<T, Entity> entityGetter, final Consumer<T> handler) {
		return DynamicEventSubscriber.of(eventClass, conditionally(ev -> entityGetter.apply(ev) == getPlayer(), handler), this::isStillValid);
	}

	/**
	 * Create a new immutable {@link DynamicEventSubscriber} implementation.
	 * <p>
	 * Acts as a helper overload for {@link PlayerEvent} events, as they are the vast majority of events for this listener.
	 *
	 * @param eventClass The class of the event to listen to
	 * @param handler The handler method for the event when called
	 */
	default <T extends PlayerEvent> DynamicEventSubscriber<T> listener(final Class<T> eventClass, final Consumer<T> handler) {
		return DynamicEventSubscriber.of(eventClass, conditionally(ev -> ev.getEntity() == getPlayer(), handler), this::isStillValid);
	}

	/**
	 * Helper method to wrap a handler in a conditional check so that it can be handled polymorphically.
	 */
	default <T extends Event> Consumer<T> conditionally(Predicate<T> condition, Consumer<T> handler) {
		return ev -> {
			if (condition.test(ev))
				handler.accept(ev);
		};
	}

	/**
	 * Conditional event handler to exclude the logical client side
	 */
	default <T extends Event> Consumer<T> serverOnly(Consumer<T> handler) {
		return conditionally(ev -> !getPlayer().level().isClientSide, handler);
	}

	/**
	 * Helper method to create a Handler for when the listener entity is about to attack another
	 *
	 * @param handler The handler consumer for the event
	 */
	default DynamicEventSubscriber<LivingIncomingDamageEvent> whenAttacking(Consumer<LivingIncomingDamageEvent> handler) {
		return listener(LivingIncomingDamageEvent.class, ev -> ev.getSource().getEntity(), handler);
	}

	/**
	 * Helper method to create a Handler for when the listener entity is about to take damage
	 *
	 * @param handler The handler consumer for the event
	 */
	default DynamicEventSubscriber<LivingIncomingDamageEvent> whenTakingDamage(Consumer<LivingIncomingDamageEvent> handler) {
		return listener(LivingIncomingDamageEvent.class, LivingIncomingDamageEvent::getEntity, handler);
	}

	/**
	 * Helper method to create a Handler for when the listener entity has attacked another
	 *
	 * @param handler The handler consumer for the event
	 */
	default DynamicEventSubscriber<LivingDamageEvent.Post> afterAttacking(Consumer<LivingDamageEvent.Post> handler) {
		return listener(LivingDamageEvent.Post.class, ev -> ev.getSource().getEntity(), handler);
	}

	/**
	 * Helper method to create a Handler for when the listener entity has taken damage
	 *
	 * @param handler The handler consumer for the event
	 */
	default DynamicEventSubscriber<LivingDamageEvent.Post> afterTakingDamage(Consumer<LivingDamageEvent.Post> handler) {
		return listener(LivingDamageEvent.Post.class, LivingDamageEvent.Post::getEntity, handler);
	}

	/**
	 * Helper method to create a Handler for when the listener entity kills another
	 *
	 * @param handler The handler consumer for the event
	 */
	default DynamicEventSubscriber<LivingDeathEvent> onEntityKill(Consumer<LivingDeathEvent> handler) {
		return DynamicEventSubscriber.of(LivingDeathEvent.class, ev -> {
			final Entity selfEntity = getPlayer();

			for (Entity attacker : EntityUtil.getAttackersForMob(ev.getEntity(), entity -> entity != ev.getEntity() && entity == selfEntity)) {
				handler.accept(ev);

				break;
			}
		}, this::isStillValid);
	}

	/**
	 * Create an {@link AoAPlayerKeybindListener} instance for this listener, allowing for keybind handling in common code
	 *
	 * @param consumer The consumer to accept the newly created instance
	 */
	default void createKeybindListener(Consumer<AoAPlayerKeybindListener> consumer) {}

	/**
	 * This method gets triggered when the keycode assigned in {@link AoAPlayerKeybindListener#getKeybind()} is pressed.
	 * <p>
	 * It will only be called once per press, so ongoing press effects should be handled manually via the keybind itself.
	 */
	default void handleKeyInput() {}

	/**
	 * Send the action key screen effect pulse to the given player
	 */
	default void activatedActionKey(ServerPlayer player) {
		TMEParticlePacket packet = new TMEParticlePacket();

		for (int i = 0; i < 50; i++) {
			packet.particle(ParticleBuilder.forRandomPosInEntity(ParticleTypes.GLOW_SQUID_INK, player)
									.colourTint(255, 204, 0, 50)
									.scaleMod(RandomUtil.valueBetween(0.25f, 0.75f))
									.lifespan(RandomUtil.numberBetween(20, 40))
									.velocity(RandomUtil.scaledGaussianValue(0.05f), RandomUtil.scaledGaussianValue(0.05f), RandomUtil.scaledGaussianValue(0.05f)));
		}

		packet.sendToAllPlayersTrackingEntity(player);
		new ScreenImageEffect(ScreenImageEffect.Type.ACTION_KEY_VIGNETTE).coloured(ColourUtil.makeARGB(ColourUtil.WHITE, 127)).fullscreen(true).duration(10).sendToPlayer(player);
	}

	/**
	 * Called when one or more {@link #createKeybindListener(Consumer) keybind listeners} accepts a keypress
	 */
	@ApiStatus.Internal
	static void onKeyPress(final Player player, final List<String> abilityListeners) {
		PlayerDataManager plData = PlayerUtil.getAdventPlayer(player);

		for (String str : abilityListeners) {
			AoAAbility.Instance ability = plData.getAbility(str);

			if (ability != null)
				ability.handleKeyInput();
		}
	}

	enum ListenerState {
		ACTIVE("active"),
		MANUALLY_DISABLED("disabled"),
		DEACTIVATED("deactivated"),
		REGION_LOCKED("region_locked"),
		REMOVED("region_locked");

		private final String id;

		ListenerState(String id) {
			this.id = id;
		}

		public String getId() {
			return this.id;
		}

		public static ListenerState fromId(String id) {
			return switch (id) {
				case "disabled" -> MANUALLY_DISABLED;
				case "deactivated" -> DEACTIVATED;
				case "active" -> ACTIVE;
				case "region_locked" -> REGION_LOCKED;
				case "removed" -> REMOVED;
				default -> DEACTIVATED;
			};
		}
	}
}
