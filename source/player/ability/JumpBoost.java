package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.UpdateClientMovementPacket;
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

		this.sprintJumpBoost = JSONUtils.getAsBoolean(data, "amplify_lateral_velocity", false);

		updateMultipliers();
	}

	public JumpBoost(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.JUMP_BOOST.get(), skill, data);

		this.sprintJumpBoost = data.getBoolean("amplify_lateral_velocity");
	}

	@Override
	protected void updateDescription(TranslationTextComponent defaultDescription) {
		super.updateDescription(new TranslationTextComponent(defaultDescription.getKey() + (sprintJumpBoost ? ".lateral" : ".vertical"), getScalingDescriptionComponent(2)));
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
		LivingEntity entity = ev.getEntityLiving();
		Vector3d oldMotion = entity.getDeltaMovement();
		Vector3d newMotion;

		if (!sprintJumpBoost) {
			newMotion = oldMotion.multiply(1, launchMultiplier / 1.02040814340536d, 1);

			AoAPackets.messagePlayer(getPlayer(), new UpdateClientMovementPacket(UpdateClientMovementPacket.Operation.SET).y((float)(oldMotion.y() * launchMultiplier / 1.02040814340536d)));
		}
		else {
			newMotion = oldMotion.multiply(baseBoostMultiplier, 1, baseBoostMultiplier);

			AoAPackets.messagePlayer(getPlayer(), new UpdateClientMovementPacket(UpdateClientMovementPacket.Operation.MULTIPLY).x((float)baseBoostMultiplier).z((float)baseBoostMultiplier));
		}

		entity.setDeltaMovement(newMotion);
	}

	@Override
	public void loadFromNbt(CompoundNBT data) {
		super.loadFromNbt(data);

		updateMultipliers();
	}

	@Override
	public CompoundNBT getSyncData(boolean forClientSetup) {
		CompoundNBT data = super.getSyncData(forClientSetup);

		if (forClientSetup)
			data.putBoolean("amplify_lateral_velocity", this.sprintJumpBoost);

		return data;
	}
}
