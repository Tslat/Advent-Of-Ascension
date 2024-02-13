package net.tslat.aoa3.player.skill;

import com.google.common.base.Suppliers;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.tslat.aoa3.client.ClientOperations;
import net.tslat.aoa3.common.networking.AoANetworking;
import net.tslat.aoa3.common.networking.packets.XpGainPacket;
import net.tslat.aoa3.common.registration.AoAAdvancementTriggers;
import net.tslat.aoa3.common.registration.AoAConfigs;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.event.custom.AoAEvents;
import net.tslat.aoa3.library.builder.SoundBuilder;
import net.tslat.aoa3.player.AoAPlayerEventListener;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.WorldUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public final class AoASkill {
	private final Supplier<MutableComponent> name;
	private final BiFunction<ServerPlayerDataManager, JsonObject, Instance> jsonFactory;
	private final Function<CompoundTag, Instance> clientFactory;

	public AoASkill(BiFunction<ServerPlayerDataManager, JsonObject, Instance> jsonFactory, Function<CompoundTag, Instance> clientFactory) {
		this.name = Suppliers.memoize(() -> Component.translatable(Util.makeDescriptionId("skill", AoARegistries.AOA_SKILLS.getKey(this))));
		this.jsonFactory = jsonFactory;
		this.clientFactory = clientFactory;
	}

	public MutableComponent getName() {
		return this.name.get();
	}

	public Instance buildDefaultInstance(ServerPlayerDataManager plData, JsonObject resourceData) {
		return jsonFactory.apply(plData, resourceData);
	}

	public Instance buildClientInstance(CompoundTag resourceData) {
		return clientFactory.apply(resourceData);
	}

	public static abstract class Instance implements AoAPlayerEventListener {
		private final AoASkill skill;
		private final HashMap<String, AoAAbility.Instance> abilities = new HashMap<String, AoAAbility.Instance>();

		protected ServerPlayerDataManager playerDataManager;

		protected int cycle = 0;
		protected int level = 1;
		protected float xp = 0f;

		protected float xpModifier = 1;

		public boolean needsSync = true;

		protected Instance(AoASkill skill, ServerPlayerDataManager plData, JsonObject instanceData) {
			this.skill = skill;

			if (instanceData != null) {
				this.playerDataManager = plData;
				this.xpModifier = GsonHelper.getAsFloat(instanceData, "xp_modifier", 1);

				if (instanceData.has("abilities")) {
					for (JsonElement entry : instanceData.getAsJsonArray("abilities")) {
						JsonObject abilityData = entry.getAsJsonObject();
						AoAAbility ability = AoAAbilities.getAbility(new ResourceLocation(GsonHelper.getAsString(abilityData, "id")));
						AoAAbility.Instance instance = ability.create(this, abilityData);

						abilities.put(instance.getUniqueIdentifier(), instance);
					}
				}
			}
		}

		protected Instance(AoASkill skill, CompoundTag instanceData) {
			this.skill = skill;
			this.xpModifier = instanceData.contains("xp_modifier") ? instanceData.getFloat("xp_modifier") : 1;

			if (instanceData.contains("abilities")) {
				CompoundTag abilityData = instanceData.getCompound("abilities");

				for (String key : abilityData.getAllKeys()) {
					AoAAbility ability = AoAAbilities.getAbility(new ResourceLocation(abilityData.getCompound(key).getString("id")));
					AoAAbility.Instance instance = ability.loadFromNbt(this, (CompoundTag)abilityData.get(key));

					abilities.put(instance.getUniqueIdentifier(), instance);
				}
			}
		}

		public void changePlayerInstance(ServerPlayerDataManager plData) {
			this.playerDataManager = plData;
		}

		public AoASkill type() {
			return this.skill;
		}

		public MutableComponent getName() {
			return type().getName().copy();
		}

		public HashMap<String, AoAAbility.Instance> getAbilityMap() {
			return this.abilities;
		}

		public Player getPlayer() {
			return this.playerDataManager == null ? ClientOperations.getPlayer() : this.playerDataManager.player();
		}

		public ServerPlayerDataManager getPlayerDataManager() {
			return this.playerDataManager;
		}

		public float getXp() {
			return this.xp;
		}

		public int getLevel(boolean includeVanityLevels) {
			return includeVanityLevels ? this.level : Math.min(this.level, 100);
		}

		public int getCycles() {
			return this.cycle;
		}

		public boolean hasLevel(int levelReq) {
			return getLevel(true) >= levelReq;
		}

		public void setLevel(int newLevel) {
			int oldLevel = this.level;
			this.level = Mth.clamp(newLevel, 0, 1000);
			this.xp = 0;
			this.needsSync = true;

			this.playerDataManager.applyLegitimacyPenalties();
			AoAEvents.playerLevelChange(this.playerDataManager, this, oldLevel, false);
		}

		public void applyXpModifier(float xpMod) {
			this.xpModifier += xpMod;
			this.needsSync = true;
		}

		public void removeXpModifier(float xpMod) {
			this.xpModifier -= xpMod;
			this.needsSync = true;
		}

		private float applyXpBuffs(float xp) {
			xp *= AoAConfigs.SERVER.globalXpModifier.get();

			if (WorldUtil.isWorld(getPlayer().level(), AoADimensions.NOWHERE))
				xp *= 0.5f;

			xp *= xpModifier;

			if (cycle > 0)
				xp *= 3 * cycle;

			return Math.max(0, xp);
		}

		public void adjustXp(float xp, boolean isUnnaturalSource, boolean ignoreXpBuffs) {
			float xpAfterMods = xp;

			if (!isUnnaturalSource && !ignoreXpBuffs)
				xpAfterMods = applyXpBuffs(xp);

			xp = AoAEvents.playerChangeXp(playerDataManager, this, xp, xpAfterMods, !isUnnaturalSource);

			if (xp > 0) {
				if (this.level >= 1000)
					return;
			}
			else if (xp == 0) {
				return;
			}
			else {
				if (level == 1)
					return;

				subtractXp(xp, isUnnaturalSource);

				return;
			}

			float remaining = Math.min(544132359, xp);
			int newLevels = 0;
			float xpRemaining = PlayerUtil.getXpRemainingUntilLevel(playerDataManager, skill);

			if (remaining > xpRemaining) {
				remaining -= xpRemaining;
				newLevels++;

				float stillRemaining;

				while ((stillRemaining = remaining - PlayerUtil.getXpRequiredForNextLevel(level + newLevels)) >= 0 && level + newLevels < 1000) {
					remaining = stillRemaining;
					newLevels++;
				}
			}

			if (newLevels > 0)
				levelUp(level, level + newLevels, !isUnnaturalSource);

			if (isUnnaturalSource) {
				playerDataManager.applyLegitimacyPenalties();
			}
			else {
				AoAAdvancementTriggers.XP_GAIN.get().trigger(this.playerDataManager.player(), this.skill, xp);
			}

			this.xp += remaining;
			this.needsSync = true;

			playerDataManager.checkAndUpdateLegitimacy();
			AoANetworking.sendToPlayer(playerDataManager.player(), new XpGainPacket(AoARegistries.AOA_SKILLS.getKey(skill), xp, newLevels > 0));
		}

		private void subtractXp(float xp, boolean isUnnaturalSource) {
			float remaining = Math.min(544132359, Math.abs(xp));
			int newLevels = 0;

			if (this.level >= 1 && remaining >= this.xp) {
				remaining -= this.xp;
				newLevels++;

				float stillRemaining;

				while ((stillRemaining = remaining - PlayerUtil.getXpRequiredForNextLevel(this.level - newLevels - 1)) >= 0 && this.level - newLevels > 1) {
					remaining = stillRemaining;
					newLevels++;
				}
			}

			if (newLevels > 0) {
				this.level = Math.max(1, this.level - newLevels);

				AoAEvents.playerLevelChange(this.playerDataManager, this, this.level + newLevels, !isUnnaturalSource);
			}

			this.xp = Math.max(0, PlayerUtil.getXpRequiredForNextLevel(this.level - newLevels) - remaining);
			this.needsSync = true;

			if (!isUnnaturalSource)
				playerDataManager.applyLegitimacyPenalties();

			AoANetworking.sendToPlayer(playerDataManager.player(), new XpGainPacket(AoARegistries.AOA_SKILLS.getKey(skill), xp, newLevels > 0));
		}

		private void levelUp(int oldLevel, int newLevel, boolean isNaturalLevel) {
			ServerPlayer player = playerDataManager.player();

			new SoundBuilder(AoASounds.PLAYER_LEVEL_UP).isPlayer().notInWorld().include(player).execute();

			if ((newLevel == 100 || newLevel == 1000) && oldLevel < newLevel)
				AoAScheduler.scheduleSyncronisedTask(() -> new SoundBuilder(newLevel == 100 ? AoASounds.PLAYER_LEVEL_UP_100 : AoASounds.PLAYER_LEVEL_UP_1000).isPlayer().notInWorld().include(player).execute(), 40);

			this.level = newLevel;
			this.xp = 0f;

			AoAEvents.playerLevelChange(this.playerDataManager, this, oldLevel, isNaturalLevel);
			AoAAdvancementTriggers.LEVEL_UP.get().trigger(player, this.skill, newLevel);
		}

		public boolean addCycle() {
			if (this.level >= 100 && this.cycle < 10) {
				int oldLevel = level;

				this.cycle++;
				this.level = 1;
				this.xp = 0;
				this.needsSync = true;

				AoAEvents.playerLevelChange(this.playerDataManager, this, oldLevel, true);
				AoAAdvancementTriggers.CYCLE_SKILL.get().trigger(this.playerDataManager.player(), this.skill, this.cycle);

				return true;
			}

			return false;
		}

		public void setCycle(int cycle) {
			this.cycle = Mth.clamp(cycle, 0, 10);
			this.needsSync = true;

			this.playerDataManager.applyLegitimacyPenalties();
		}

		public CompoundTag saveToNbt() {
			CompoundTag skillData = new CompoundTag();
			CompoundTag abilityData = new CompoundTag();

			skillData.putInt("cycle", this.cycle);
			skillData.putInt("level", this.level);
			skillData.putFloat("xp", this.xp);

			for (Map.Entry<String, AoAAbility.Instance> ability : getAbilityMap().entrySet()) {
				CompoundTag abilityNbt = ability.getValue().saveToNbt();

				if (abilityNbt != null)
					abilityData.put(ability.getKey(), abilityNbt);
			}

			skillData.put("abilities", abilityData);

			return skillData;
		}

		public void loadFromNbt(CompoundTag skillData) {
			this.level = 1;
			this.xp = 0f;

			if (skillData.contains("cycle"))
				cycle = skillData.getInt("cycle");

			if (skillData.contains("level"))
				level = skillData.getInt("level");

			if (skillData.contains("xp"))
				xp = skillData.getFloat("xp");

			if (skillData.contains("abilities")) {
				CompoundTag abilityData = skillData.getCompound("abilities");

				for (AoAAbility.Instance ability : getAbilityMap().values()) {
					if (abilityData.contains(ability.getUniqueIdentifier())) {
						ability.loadFromNbt(abilityData.getCompound(ability.getUniqueIdentifier()));
					}
					else {
						ability.checkDeactivation(false, false);
					}
				}
			}
		}

		public CompoundTag getSyncData(boolean forClientSetup) {
			CompoundTag data = new CompoundTag();
			CompoundTag abilityData = new CompoundTag();

			data.putInt("cycle", cycle);
			data.putInt("level", level);
			data.putFloat("xp", xp);
			data.putFloat("xpMod", xpModifier);

			for (Map.Entry<String, AoAAbility.Instance> ability : getAbilityMap().entrySet()) {
				AoAAbility.Instance instance = ability.getValue();

				if (instance.needsSync || forClientSetup) {
					abilityData.put(instance.getUniqueIdentifier(), instance.getSyncData(forClientSetup));

					instance.needsSync = false;
				}
			}

			if (!abilityData.isEmpty())
				data.put("abilities", abilityData);

			if (!forClientSetup)
				needsSync = false;

			return data;
		}

		public void receiveSyncData(CompoundTag data) {
			this.cycle = data.getInt("cycle");
			this.level = data.getInt("level");
			this.xp = data.getFloat("xp");
			this.xpModifier = data.getFloat("xpMod");

			if (data.contains("abilities")) {
				CompoundTag abilityData = data.getCompound("abilities");

				for (String key : abilityData.getAllKeys()) {
					AoAAbility.Instance ability = abilities.get(key);

					if (ability != null)
						ability.receiveSyncData(abilityData.getCompound(key));
				}
			}
		}

		public boolean canGainXp(boolean naturalXpSource) {
			Player player = getPlayer();

			if (naturalXpSource && (player.isCreative() || player.isSpectator()))
				return false;

			return getLevel(true) < 1000;
		}
	}
}
