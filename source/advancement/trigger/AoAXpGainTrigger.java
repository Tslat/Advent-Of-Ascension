package net.tslat.aoa3.advancement.trigger;

import com.google.gson.JsonObject;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.loot.ConditionArrayParser;
import net.minecraft.loot.ConditionArraySerializer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.RegistryManager;
import net.tslat.aoa3.player.skill.AoASkill;

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
		AoASkill skill = null;

		if (json.has("skill")) {
			ResourceLocation skillId = new ResourceLocation(JSONUtils.getAsString(json, "skill"));
			skill = RegistryManager.ACTIVE.getRegistry(AoASkill.class).getValue(skillId);

			if (skill == null)
				throw new IllegalArgumentException("Invalid AoASkill ID: '" + skillId + "'");
		}

		float xp = json.has("xp") ? JSONUtils.getAsFloat(json, "xp") : 0f;

		return new Instance(skill, xp);
	}

	public void trigger(ServerPlayerEntity player, AoASkill skill, float xp) {
		Listeners playerListeners = this.listeners.get(player.getAdvancements());

		if (playerListeners != null)
			playerListeners.trigger(skill, xp);
	}

	public static class Instance extends CriterionInstance {
		@Nullable
		private final AoASkill skill;
		private final float xp;

		public Instance(@Nullable AoASkill skill, float xp, EntityPredicate.AndPredicate playerPredicate) {
			super(triggerId, playerPredicate);

			this.skill = skill;
			this.xp = xp;
		}

		public Instance(@Nullable AoASkill skill, float xp) {
			this(skill, xp, EntityPredicate.AndPredicate.ANY);
		}

		public boolean test(AoASkill skill, float xp) {
			return (this.skill == null || this.skill == skill) && (this.xp == 0 || this.xp <= xp);
		}

		@Override
		public JsonObject serializeToJson(ConditionArraySerializer conditions) {
			JsonObject obj = super.serializeToJson(conditions);

			if (skill != null)
				obj.addProperty("skill", skill.getRegistryName().toString());

			if (xp > 0)
				obj.addProperty("xp", xp);

			return obj;
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

		public void trigger(AoASkill skill, float xp) {
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
