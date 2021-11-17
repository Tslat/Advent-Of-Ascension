package net.tslat.aoa3.player.skill;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.XpGainPacket;
import net.tslat.aoa3.common.registration.AoAAdvancementTriggers;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.event.custom.AoAEvents;
import net.tslat.aoa3.player.AoAPlayerEventListener;
import net.tslat.aoa3.player.PlayerDataManager;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.util.PlayerUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public final class AoASkill extends ForgeRegistryEntry<AoASkill> {
	private final Lazy<TranslationTextComponent> name;
	private final BiFunction<PlayerDataManager, JsonObject, Instance> jsonFactory;
	private final Function<CompoundNBT, Instance> clientFactory;

	public AoASkill(BiFunction<PlayerDataManager, JsonObject, Instance> jsonFactory, Function<CompoundNBT, Instance> clientFactory) {
		this.name = () -> new TranslationTextComponent(Util.makeDescriptionId("skill", getRegistryName()));
		this.jsonFactory = jsonFactory;
		this.clientFactory = clientFactory;
	}

	public TranslationTextComponent getName() {
		return this.name.get();
	}

	public Instance buildDefaultInstance(PlayerDataManager plData, JsonObject resourceData) {
		return jsonFactory.apply(plData, resourceData);
	}

	public Instance buildClientInstance(CompoundNBT resourceData) {
		return clientFactory.apply(resourceData);
	}

	public static abstract class Instance implements AoAPlayerEventListener {
		private final AoASkill skill;
		private final HashMap<String, AoAAbility.Instance> abilities = new HashMap<String, AoAAbility.Instance>();

		protected PlayerDataManager playerDataManager;

		protected int cycle = 0;
		protected int level = 1;
		protected float xp = 0f;

		protected float xpModifier = 1;

		public boolean needsSync = true;

		protected Instance(AoASkill skill, PlayerDataManager plData, JsonObject instanceData) {
			this.skill = skill;

			if (instanceData != null) {
				this.playerDataManager = plData;
				this.xpModifier = JSONUtils.getAsFloat(instanceData, "xp_modifier", 1);

				if (instanceData.has("abilities")) {
					for (JsonElement entry : instanceData.getAsJsonArray("abilities")) {
						JsonObject abilityData = entry.getAsJsonObject();
						AoAAbility ability = AoAAbilities.getAbility(new ResourceLocation(JSONUtils.getAsString(abilityData, "id")));
						AoAAbility.Instance instance = ability.create(this, abilityData);

						abilities.put(instance.getUniqueIdentifier(), instance);
					}
				}
			}
		}

		protected Instance(AoASkill skill, CompoundNBT instanceData) {
			this.skill = skill;
			this.xpModifier = instanceData.getFloat("xp_modifier");

			if (instanceData.contains("abilities")) {
				CompoundNBT abilityData = instanceData.getCompound("abilities");

				for (String key : abilityData.getAllKeys()) {
					AoAAbility ability = AoAAbilities.getAbility(new ResourceLocation(abilityData.getCompound(key).getString("id")));
					AoAAbility.Instance instance = ability.loadFromNbt(this, (CompoundNBT)abilityData.get(key));

					abilities.put(instance.getUniqueIdentifier(), instance);
				}
			}
		}

		public void changePlayerInstance(PlayerDataManager plData) {
			this.playerDataManager = plData;
		}

		public AoASkill type() {
			return this.skill;
		}

		public TranslationTextComponent getName() {
			return type().getName();
		}

		public HashMap<String, AoAAbility.Instance> getAbilityMap() {
			return this.abilities;
		}

		public PlayerDataManager getPlayerDataManager() {
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
			this.level = MathHelper.clamp(newLevel, 0, 1000);
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
			xp *= AoAConfig.SERVER.globalXpModifier.get();
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
				AoAAdvancementTriggers.xpGainTrigger.trigger(playerDataManager.player(), skill, xp);
			}

			this.xp += remaining;
			this.needsSync = true;

			playerDataManager.checkAndUpdateLegitimacy();
			AoAPackets.messagePlayer(playerDataManager.player(), new XpGainPacket(skill.getRegistryName(), xp, newLevels > 0));
		}

		private void subtractXp(float xp, boolean isUnnaturalSource) {
			float remaining = Math.min(544132359, xp);
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

			AoAPackets.messagePlayer(playerDataManager.player(), new XpGainPacket(skill.getRegistryName(), xp, newLevels > 0));
		}

		private void levelUp(int oldLevel, int newLevel, boolean isNaturalLevel) {
			ServerPlayerEntity player = playerDataManager.player();

			if (newLevel < 100) {
				player.level.playSound(null, player.getX(), player.getY(), player.getZ(), AoASounds.PLAYER_LEVEL_UP.get(), SoundCategory.PLAYERS, 1.0f, 1.0f);
			}
			else if (newLevel == 100 || newLevel == 1000) {
				player.level.playSound(null, player.getX(), player.getY(), player.getZ(), AoASounds.PLAYER_LEVEL_100.get(), SoundCategory.PLAYERS, 1.0f, 1.0f);
			}

			this.level = newLevel;
			this.xp = 0f;

			AoAEvents.playerLevelChange(this.playerDataManager, this, oldLevel, isNaturalLevel);
			AoAAdvancementTriggers.levelUpTrigger.trigger(player, skill, newLevel);
		}

		public boolean addCycle() {
			if (this.level >= 100 && this.cycle < 10) {
				int oldLevel = level;

				this.cycle++;
				this.level = 1;
				this.xp = 0;
				this.needsSync = true;

				AoAEvents.playerLevelChange(this.playerDataManager, this, oldLevel, true);
				AoAAdvancementTriggers.cycleTrigger.trigger(playerDataManager.player(), skill, cycle);

				return true;
			}

			return false;
		}

		public void setCycle(int cycle) {
			this.cycle = MathHelper.clamp(cycle, 0, 10);
			this.needsSync = true;

			this.playerDataManager.applyLegitimacyPenalties();
		}

		public CompoundNBT saveToNbt() {
			CompoundNBT skillData = new CompoundNBT();
			CompoundNBT abilityData = new CompoundNBT();

			skillData.putInt("cycle", this.cycle);
			skillData.putInt("level", this.level);
			skillData.putFloat("xp", this.xp);

			for (Map.Entry<String, AoAAbility.Instance> ability : getAbilityMap().entrySet()) {
				CompoundNBT abilityNbt = ability.getValue().saveToNbt();

				if (abilityNbt != null)
					abilityData.put(ability.getKey(), abilityNbt);
			}

			skillData.put("abilities", abilityData);

			return skillData;
		}

		public void loadFromNbt(CompoundNBT skillData) {
			this.level = 1;
			this.xp = 0f;

			if (skillData.contains("cycle"))
				cycle = skillData.getInt("cycle");

			if (skillData.contains("level"))
				level = skillData.getInt("level");

			if (skillData.contains("xp"))
				xp = skillData.getFloat("xp");

			if (skillData.contains("abilities")) {
				CompoundNBT abilityData = skillData.getCompound("abilities");

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

		public CompoundNBT getSyncData(boolean forClientSetup) {
			CompoundNBT data = new CompoundNBT();
			CompoundNBT abilityData = new CompoundNBT();

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

		public void receiveSyncData(CompoundNBT data) {
			this.cycle = data.getInt("cycle");
			this.level = data.getInt("level");
			this.xp = data.getFloat("xp");
			this.xpModifier = data.getFloat("xpMod");

			if (data.contains("abilities")) {
				CompoundNBT abilityData = data.getCompound("abilities");

				for (String key : abilityData.getAllKeys()) {
					AoAAbility.Instance ability = abilities.get(key);

					if (ability != null)
						ability.receiveSyncData(abilityData.getCompound(key));
				}
			}
		}
	}
}
