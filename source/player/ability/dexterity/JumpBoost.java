package net.tslat.aoa3.player.ability.dexterity;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.neoforge.event.entity.living.LivingFallEvent;
import net.tslat.aoa3.common.networking.AoANetworking;
import net.tslat.aoa3.common.networking.packets.UpdateClientMovementPacket;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.event.custom.events.PlayerLevelChangeEvent;
import net.tslat.aoa3.event.dynamic.DynamicEventSubscriber;
import net.tslat.aoa3.player.ability.generic.ScalableModAbility;
import net.tslat.aoa3.player.skill.AoASkill;

import java.util.List;

public class JumpBoost extends ScalableModAbility {
	private final List<DynamicEventSubscriber<?>> eventSubscribers = List.of(
			listener(LivingFallEvent.class, LivingFallEvent::getEntity, serverOnly(this::handlePlayerFall)),
			listener(LivingEvent.LivingJumpEvent.class, LivingEvent.LivingJumpEvent::getEntity, serverOnly(this::handlePlayerJump)),
			listener(PlayerLevelChangeEvent.class, serverOnly(this::handleLevelChange)));
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
	public List<DynamicEventSubscriber<?>> getEventSubscribers() {
		return this.eventSubscribers;
	}

	private void updateMultipliers() {
		this.baseBoostMultiplier = 1 + getScaledValue();
        this.launchMultiplier = -0.0008 * Math.pow(this.baseBoostMultiplier, 4) + 0.00332 * Math.pow(this.baseBoostMultiplier, 3) - 0.05499 * Mth.square(baseBoostMultiplier) + 0.62043 * baseBoostMultiplier + 0.27697;
	}

	private void handleLevelChange(PlayerLevelChangeEvent ev) {
		updateMultipliers();
	}

	private void handlePlayerJump(LivingEvent.LivingJumpEvent ev) {
		LivingEntity entity = ev.getEntity();
		Vec3 oldMotion = entity.getDeltaMovement();
		Vec3 newMotion;

		if (!this.sprintJumpBoost) {
			newMotion = oldMotion.multiply(1, this.launchMultiplier / 1.02040814340536d, 1);

			AoANetworking.sendToPlayer((ServerPlayer)getPlayer(), new UpdateClientMovementPacket(UpdateClientMovementPacket.Operation.SET, oldMotion.y() * this.launchMultiplier / 1.02040814340536d));
		}
		else {
			newMotion = oldMotion.multiply(this.baseBoostMultiplier, 1, this.baseBoostMultiplier);

			AoANetworking.sendToPlayer((ServerPlayer)getPlayer(), new UpdateClientMovementPacket(UpdateClientMovementPacket.Operation.MULTIPLY, this.baseBoostMultiplier, this.baseBoostMultiplier));
		}

		entity.setDeltaMovement(newMotion);
	}

	private void handlePlayerFall(LivingFallEvent ev) {
		if (ev.getDistance() - (this.launchMultiplier * 0.75f) < 3)
			ev.setCanceled(true);
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
