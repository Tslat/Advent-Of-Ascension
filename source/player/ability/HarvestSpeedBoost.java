package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.tslat.aoa3.client.AoAKeybinds;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.NumberUtil;

public class HarvestSpeedBoost extends AoAAbility.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.BLOCK_BREAK_SPEED, ListenerType.PLAYER_TICK, ListenerType.KEY_INPUT};

	private final float energyDrainPerTick;
	private final float costReductionPerLevel;
	private final float speedBoostMod;

	private boolean active = false;

	public HarvestSpeedBoost(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.HARVEST_SPEED_BOOST.get(), skill, data);

		this.energyDrainPerTick = JSONUtils.getAsFloat(data, "energy_drain_per_tick");
		this.costReductionPerLevel = JSONUtils.getAsFloat(data, "cost_reduction_per_level", 0);
		this.speedBoostMod = JSONUtils.getAsFloat(data, "speed_boost", 2);
	}

	public HarvestSpeedBoost(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.HARVEST_SPEED_BOOST.get(), skill, data);

		this.energyDrainPerTick = data.getFloat("energy_drain_per_tick");
		this.costReductionPerLevel = data.getFloat("cost_reduction_per_level");
		this.speedBoostMod = data.getFloat("speed_boost");
	}

	@Override
	protected void updateDescription(TranslationTextComponent defaultDescription) {
		super.updateDescription(new TranslationTextComponent(defaultDescription.getKey() + (costReductionPerLevel != 0 ? ".scaling" : ""),
				NumberUtil.roundToNthDecimalPlace(this.energyDrainPerTick * 20, 2),
				NumberUtil.roundToNthDecimalPlace(this.costReductionPerLevel * 20, 2)));
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public CompoundNBT getSyncData(boolean forClientSetup) {
		CompoundNBT data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			data.putFloat("energy_drain_per_tick", this.energyDrainPerTick);
			data.putFloat("cost_reduction_per_level", this.costReductionPerLevel);
			data.putFloat("speed_boost", this.speedBoostMod);
		}

		data.putBoolean("active", this.active);

		return data;
	}

	@Override
	public void receiveSyncData(CompoundNBT data) {
		super.receiveSyncData(data);

		this.active = data.getBoolean("active");
	}

	@Override
	public void handleHarvestSpeedCheck(PlayerEvent.BreakSpeed ev) {
		if (active)
			ev.setNewSpeed(ev.getNewSpeed() * speedBoostMod);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public KeyBinding getKeybind() {
		return AoAKeybinds.ABILITY_ACTION;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public boolean shouldSendKeyPress() {
		PlayerEntity player = Minecraft.getInstance().player;

		if (player.isCreative())
			return false;

		return Minecraft.getInstance().gameMode.isDestroying() || active;
	}

	@Override
	public void handleKeyInput() {
		ServerPlayerEntity player = getPlayer();

		if (active) {
			active = false;
		}
		else {
			if (player.isCreative() || !player.gameMode.isDestroyingBlock)
				return;

			active = true;
		}

		activatedActionKey(player);
		markForClientSync();
	}

	@Override
	public void handlePlayerTick(TickEvent.PlayerTickEvent ev) {
		if (!active)
			return;

		AoAResource.Instance energy = skill.getPlayerDataManager().getResource(AoAResources.ENERGY.get());
		ServerPlayerEntity player = getPlayer();

		if (!player.isCreative() && !player.isSpectator() && energy.consume(energy.getPerTickRegen() + energyDrainPerTick - costReductionPerLevel * skill.getLevel(true), true)) {

			if (player.level.getGameTime() % 10 == 0)
				activatedActionKey(player);
		}
		else {
			active = false;

			markForClientSync();
		}
	}
}
