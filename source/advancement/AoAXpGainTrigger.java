package net.tslat.aoa3.advancement;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.critereon.AbstractCriterionInstance;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.library.Enums;

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
	public void addListener(PlayerAdvancements playerAdvancements, Listener<Instance> listener) {
		Listeners playerListeners = this.listeners.get(playerAdvancements);

		if (playerListeners == null)
			this.listeners.put(playerAdvancements, (playerListeners = new Listeners(playerAdvancements)));

		playerListeners.add(listener);
	}

	@Override
	public void removeListener(PlayerAdvancements playerAdvancements, Listener<Instance> listener) {
		Listeners playerListeners = this.listeners.get(playerAdvancements);

		if (playerListeners != null) {
			playerListeners.remove(listener);

			if (playerListeners.isEmpty())
				this.listeners.remove(playerAdvancements);
		}
	}

	@Override
	public void removeAllListeners(PlayerAdvancements playerAdvancements) {
		this.listeners.remove(playerAdvancements);
	}

	@Override
	public Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
		Enums.Skills skill = json.has("skill") ? Enums.Skills.valueOf(JsonUtils.getString(json, "skill").toUpperCase()) : null;
		int xp = json.has("xp") ? JsonUtils.getInt(json, "xp") : 0;

		return new Instance(skill, xp);
	}

	public void trigger(EntityPlayerMP player, Enums.Skills skill, int level) {
		Listeners playerListeners = this.listeners.get(player.getAdvancements());

		if (playerListeners != null)
			playerListeners.trigger(skill, level);
	}

	public static class Instance extends AbstractCriterionInstance {
		@Nullable
		private final Enums.Skills skill;
		private final int xp;

		public Instance(@Nullable Enums.Skills skill, int xp) {
			super(triggerId);

			this.skill = skill;
			this.xp = xp;
		}

		public boolean test(Enums.Skills skill, int xp) {
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

		public void trigger(Enums.Skills skill, int xp) {
			ArrayList<Listener<Instance>> list = null;

			for (Listener<Instance> listener : this.listeners) {
				if (listener.getCriterionInstance().test(skill, xp)) {
					if (list == null)
						list = new ArrayList<Listener<Instance>>();

					list.add(listener);
				}
			}

			if (list != null) {
				for (Listener<Instance> listener : list) {
					listener.grantCriterion(this.advancements);
				}
			}
		}
	}
}
