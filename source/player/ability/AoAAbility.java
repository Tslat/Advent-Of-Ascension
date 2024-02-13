package net.tslat.aoa3.player.ability;

import com.google.common.base.Suppliers;
import com.google.gson.JsonObject;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.player.AoAPlayerEventListener;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.player.skill.AoASkill;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class AoAAbility {
	private final Supplier<MutableComponent> name;
	private final BiFunction<AoASkill.Instance, JsonObject, Instance> jsonFactory;
	private final BiFunction<AoASkill.Instance, CompoundTag, Instance> nbtFactory;

	public AoAAbility(BiFunction<AoASkill.Instance, JsonObject, Instance> jsonFactory, BiFunction<AoASkill.Instance, CompoundTag, Instance> nbtFactory) {
		this.name = Suppliers.memoize(() -> Component.translatable(Util.makeDescriptionId("ability", AoARegistries.AOA_ABILITIES.getKey(this))));
		this.jsonFactory = jsonFactory;
		this.nbtFactory = nbtFactory;
	}

	public MutableComponent getName() {
		return this.name.get();
	}

	public Instance create(AoASkill.Instance skillInstance, JsonObject abilityData) {
		return jsonFactory.apply(skillInstance, abilityData);
	}

	public Instance loadFromNbt(AoASkill.Instance skillInstance, CompoundTag abilityData) {
		return nbtFactory.apply(skillInstance, abilityData);
	}

	public static abstract class Instance implements AoAPlayerEventListener {
		private final AoAAbility ability;
		protected AoASkill.Instance skill;
		private final int levelReq;
		private final String uniqueIdentifier;
		private MutableComponent description;

		public boolean needsSync = true;
		private ListenerState state;

		public Instance(AoAAbility ability, AoASkill.Instance skill, JsonObject data) {
			this.skill = skill;
			this.ability = ability;
			this.uniqueIdentifier = GsonHelper.getAsString(data, "unique_id");
			this.levelReq = GsonHelper.getAsInt(data, "level_req");
			this.state = ListenerState.fromId(GsonHelper.getAsString(data, "state", ListenerState.ACTIVE.getId()));
			this.description = data.has("description") ? Component.translatable(GsonHelper.getAsString(data, "description")) : null;

			checkDeactivation(true, false);
		}

		public Instance(AoAAbility ability, AoASkill.Instance skill, CompoundTag data) {
			this.skill = skill;
			this.ability = ability;
			this.uniqueIdentifier = data.getString("unique_identifier");
			this.levelReq = data.getInt("level_req");
			this.state = ListenerState.fromId(data.getString("state"));
			this.description = data.contains("description") ? Component.translatable(data.getString("description")) : null;
		}

		protected void updateDescription(MutableComponent defaultDescription) {
			this.description = defaultDescription;
		}

		public AoAAbility type() {
			return this.ability;
		}

		public MutableComponent getName() {
			return type().getName();
		}

		public MutableComponent getDescription() {
			if (this.description == null)
				updateDescription(Component.translatable(Util.makeDescriptionId("ability", AoARegistries.AOA_ABILITIES.getKey(type())) + ".description"));

			return this.description;
		}

		public String getUniqueIdentifier() {
			return this.uniqueIdentifier;
		}

		public int getLevelReq() {
			return this.levelReq;
		}

		public AoASkill.Instance getSkill() {
			return this.skill;
		}

		@Override
		public final void reenable(boolean isInit) {
			if (skill.hasLevel(getLevelReq())) {
				this.state = ListenerState.ACTIVE;

				markForClientSync();
				skill.getPlayerDataManager().markListenerDirty(this);

				if (!isInit) {
					applyAttributeModifiers(skill.getPlayerDataManager());
					onReenable();
				}
			}
		}

		@Override
		public final void disable(ListenerState reason, boolean isInit) {
			this.state = reason;

			markForClientSync();
			skill.getPlayerDataManager().markListenerDirty(this);

			if (!isInit) {
				removeAttributeModifiers(skill.getPlayerDataManager());
				onDisable();
			}
		}

		protected void onReenable() {}

		protected void onDisable() {}

		protected void markForClientSync() {
			this.needsSync = true;
			this.skill.needsSync = true;
		}

		@Override
		public ListenerState getListenerState() {
			return state;
		}

		protected Player getPlayer() {
			return this.skill.getPlayer();
		}

		protected boolean consumeResource(AoAResource resource, float amount, boolean consumeIfInsufficient) {
			return this.skill.getPlayerDataManager().getResource(resource).consume(amount, consumeIfInsufficient);
		}

		@Override
		public boolean meetsRequirements() {
			return skill.hasLevel(getLevelReq());
		}

		public void checkDeactivation(boolean isInit, boolean stateChanged) {
			if (state == ListenerState.ACTIVE) {
				if (!skill.hasLevel(levelReq)) {
					disable(ListenerState.DEACTIVATED, isInit);
				}
				else if (stateChanged) {
					reenable(isInit);
				}
			}
			else if (state == ListenerState.DEACTIVATED) {
				if (skill.hasLevel(levelReq)) {
					reenable(isInit);
				}
				else {
					disable(state, isInit);
				}
			}
		}

		public CompoundTag saveToNbt() {
			CompoundTag data = new CompoundTag();

			data.putString("state", this.state.getId());

			return data;
		}

		public void loadFromNbt(CompoundTag data) {
			ListenerState prevState = this.state;
			this.state = ListenerState.fromId(data.getString("state"));

			checkDeactivation(false, prevState != state);
		}

		public CompoundTag getSyncData(boolean forClientSetup) {
			CompoundTag data = new CompoundTag();

			 data.putString("state", this.state.getId());

			 if (forClientSetup) {
				 data.putString("id", AoARegistries.AOA_ABILITIES.getKey(this.type()).toString());
				 data.putString("unique_identifier", this.uniqueIdentifier);
				 data.putInt("level_req", this.levelReq);

				 if (this.description != null)
					 data.putString("description", ((TranslatableContents)this.description.getContents()).getKey());
			 }

			 return data;
		}

		public void receiveSyncData(CompoundTag data) {
			this.state = ListenerState.fromId(data.getString("state"));
		}

		public void receiveInteractionDataFromClient(String data) {}

		@OnlyIn(Dist.CLIENT)
		public boolean onGuiClick(final int mouseX, final int mouseY) {
			return getListenerState() == ListenerState.ACTIVE || getListenerState() == ListenerState.MANUALLY_DISABLED;
		}

		@OnlyIn(Dist.CLIENT)
		public boolean onGuiHover(final int mouseX, final int mouseY) {
			return getListenerState() == ListenerState.ACTIVE || getListenerState() == ListenerState.MANUALLY_DISABLED;
		}
	}
}
