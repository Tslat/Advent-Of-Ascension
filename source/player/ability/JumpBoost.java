package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
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

		this.sprintJumpBoost = GsonHelper.getAsBoolean(data, "amplify_lateral_velocity", false);

		updateMultipliers();
	}

	public JumpBoost(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.JUMP_BOOST.get(), skill, data);

		this.sprintJumpBoost = data.getBoolean("amplify_lateral_velocity");
	}

	@Override
	protected void updateDescription(TranslatableComponent defaultDescription) {
		super.updateDescription(new TranslatableComponent(defaultDescription.getKey() + (sprintJumpBoost ? ".lateral" : ".vertical"), getScalingDescriptionComponent(2)));
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
		Vec3 oldMotion = entity.getDeltaMovement();
		Vec3 newMotion;

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
