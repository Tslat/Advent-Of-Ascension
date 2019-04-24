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

public class AoALevelUpTrigger implements ICriterionTrigger<AoALevelUpTrigger.Instance> {
	private static final ResourceLocation triggerId = new ResourceLocation("aoa3", "level_up");
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
		int lvl = json.has("level") ? JsonUtils.getInt(json, "level") : 0;

		return new AoALevelUpTrigger.Instance(skill, lvl);
	}

	public void trigger(EntityPlayerMP player, Enums.Skills skill, int level) {
		Listeners playerListeners = this.listeners.get(player.getAdvancements());

		if (playerListeners != null)
			playerListeners.trigger(skill, level);
	}

	public static class Instance extends AbstractCriterionInstance {
		@Nullable
		private final Enums.Skills skill;
		private final int level;

		public Instance(@Nullable Enums.Skills skill, int lvl) {
			super(triggerId);

			this.skill = skill;
			this.level = lvl;
		}

		public boolean test(Enums.Skills skill, int level) {
			return (this.skill == null || this.skill == skill) && (this.level == 0 || this.level == level);
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

		public void add(ICriterionTrigger.Listener<AoALevelUpTrigger.Instance> listener) {
			this.listeners.add(listener);
		}

		public void remove(ICriterionTrigger.Listener<AoALevelUpTrigger.Instance> listener) {
			this.listeners.remove(listener);
		}

		public void trigger(Enums.Skills skill, int lvl) {
			ArrayList<Listener<Instance>> list = null;

			for (Listener<Instance> listener : this.listeners) {
				if (listener.getCriterionInstance().test(skill, lvl)) {
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
