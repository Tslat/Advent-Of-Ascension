package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.tslat.aoa3.common.networking.AoANetworking;
import net.tslat.aoa3.common.networking.packets.UpdateClientMovementPacket;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.event.custom.events.PlayerLevelChangeEvent;
import net.tslat.aoa3.player.skill.AoASkill;

public class JumpBoost extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.PLAYER_JUMP, ListenerType.LEVEL_CHANGE};
	private final boolean sprintJumpBoost;

	private double baseBoostMultiplier;
	private double launchMultiplier;

	public JumpBoost(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.JUMP_BOOST.get(), skill, data);

		this.sprintJumpBoost = GsonHelper.getAsBoolean(data, "amplify_lateral_velocity", false);

		updateMultipliers();
	}

	public JumpBoost(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.JUMP_BOOST.get(), skill, data);

		this.sprintJumpBoost = data.getBoolean("amplify_lateral_velocity");
	}

	@Override
	protected void updateDescription(MutableComponent defaultDescription) {
		super.updateDescription(Component.translatable(((TranslatableContents)defaultDescription.getContents()).getKey() + (sprintJumpBoost ? ".lateral" : ".vertical"), getScalingDescriptionComponent(2)));
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	private void updateMultipliers() {
		this.baseBoostMultiplier = 1 + getScaledValue();
		this.launchMultiplier = -0.0008 * Math.pow(baseBoostMultiplier, 4) + 0.00332 * Math.pow(baseBoostMultiplier, 3) - 0.05499 * Math.pow(baseBoostMultiplier, 2) + 0.62043 * baseBoostMultiplier + 0.27697;
	}

	@Override
	public void handleLevelChange(PlayerLevelChangeEvent ev) {
		updateMultipliers();
	}

	@Override
	public void handlePlayerJump(LivingEvent.LivingJumpEvent ev) {
		LivingEntity entity = ev.getEntity();
		Vec3 oldMotion = entity.getDeltaMovement();
		Vec3 newMotion;

		if (!this.sprintJumpBoost) {
			newMotion = oldMotion.multiply(1, this.launchMultiplier / 1.02040814340536d, 1);

			AoANetworking.sendToPlayer(getPlayer(), new UpdateClientMovementPacket(UpdateClientMovementPacket.Operation.SET, oldMotion.y() * this.launchMultiplier / 1.02040814340536d));
		}
		else {
			newMotion = oldMotion.multiply(this.baseBoostMultiplier, 1, this.baseBoostMultiplier);

			AoANetworking.sendToPlayer(getPlayer(), new UpdateClientMovementPacket(UpdateClientMovementPacket.Operation.MULTIPLY, this.baseBoostMultiplier, this.baseBoostMultiplier));
		}

		entity.setDeltaMovement(newMotion);
	}

	@Override
	public void loadFromNbt(CompoundTag data) {
		super.loadFromNbt(data);

		updateMultipliers();
	}

	@Override
	public CompoundTag getSyncData(boolean forClientSetup) {
		CompoundTag data = super.getSyncData(forClientSetup);

		if (forClientSetup)
			data.putBoolean("amplify_lateral_velocity", this.sprintJumpBoost);

		return data;
	}
}
