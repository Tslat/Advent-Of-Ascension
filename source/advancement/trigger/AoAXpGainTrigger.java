package net.tslat.aoa3.advancement.trigger;

import com.google.gson.JsonObject;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.loot.ConditionArrayParser;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.util.constant.Skills;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class AoAXpGainTrigger implements ICriterionTrigger<AoAXpGainTrigger.Instance> {
	private static final ResourceLocation triggerId = new ResourceLocation("aoa3", "gain_xp");
	private final HashMap<PlayerAdvancements, Listeners> listeners = new HashMap<PlayerAdvancements, Listeners>();

	@Override
	public ResourceLocation getId() {
		return triggerId;
	}

	@Override
	public void addPlayerListener(PlayerAdvancements playerAdvancements, Listener<Instance> listener) {
		Listeners playerListeners = this.listeners.get(playerAdvancements);

		if (playerListeners == null)
			this.listeners.put(playerAdvancements, (playerListeners = new Listeners(playerAdvancements)));

		playerListeners.add(listener);
	}

	@Override
	public void removePlayerListener(PlayerAdvancements playerAdvancements, Listener<Instance> listener) {
		Listeners playerListeners = this.listeners.get(playerAdvancements);

		if (playerListeners != null) {
			playerListeners.remove(listener);

			if (playerListeners.isEmpty())
				this.listeners.remove(playerAdvancements);
		}
	}

	@Override
	public void removePlayerListeners(PlayerAdvancements playerAdvancements) {
		this.listeners.remove(playerAdvancements);
	}

	@Override
	public Instance createInstance(JsonObject json, ConditionArrayParser conditions) {
		Skills skill = json.has("skill") ? Skills.valueOf(JSONUtils.getAsString(json, "skill").toUpperCase()) : null;
		int xp = json.has("xp") ? JSONUtils.getAsInt(json, "xp") : 0;

		return new Instance(skill, xp);
	}

	public void trigger(ServerPlayerEntity player, Skills skill, int level) {
		Listeners playerListeners = this.listeners.get(player.getAdvancements());

		if (playerListeners != null)
			playerListeners.trigger(skill, level);
	}

	public static class Instance extends CriterionInstance {
		@Nullable
		private final Skills skill;
		private final int xp;

		public Instance(@Nullable Skills skill, int xp, EntityPredicate.AndPredicate playerPredicate) {
			super(triggerId, playerPredicate);

			this.skill = skill;
			this.xp = xp;
		}

		public Instance(@Nullable Skills skill, int xp) {
			this(skill, xp, EntityPredicate.AndPredicate.ANY);
		}

		public boolean test(Skills skill, int xp) {
			return (this.skill == null || this.skill == skill) && (this.xp == 0 || this.xp <= xp);
		}
	}

	static class Listeners {
		private final PlayerAdvancements advancements;
		private final HashSet<Listener<Instance>> listeners = new HashSet<Listener<Instance>>();

		public Listeners(PlayerAdvancements playerAdvancements) {
			this.advancements = playerAdvancements;
		}

		public boolean isEmpty() {
			return this.listeners.isEmpty();
		}

		public void add(Listener<Instance> listener) {
			this.listeners.add(listener);
		}

		public void remove(Listener<Instance> listener) {
			this.listeners.remove(listener);
		}

		public void trigger(Skills skill, int xp) {
			ArrayList<Listener<Instance>> list = null;

			for (Listener<Instance> listener : this.listeners) {
				if (listener.getTriggerInstance().test(skill, xp)) {
					if (list == null)
						list = new ArrayList<Listener<Instance>>();

					list.add(listener);
				}
			}

			if (list != null) {
				for (Listener<Instance> listener : list) {
					listener.run(this.advancements);
				}
			}
		}
	}
}
