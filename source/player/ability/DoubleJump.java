package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.UpdateClientMovementPacket;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.PlayerUtil;

public class DoubleJump extends AoAAbility.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.KEY_INPUT, ListenerType.PLAYER_FALL};

	private final float energyConsumption;

	private boolean canJump = true;

	public DoubleJump(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.DOUBLE_JUMP.get(), skill, data);

		this.energyConsumption = Math.max(0, GsonHelper.getAsFloat(data, "energy_consumption"));
	}

	public DoubleJump(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.DOUBLE_JUMP.get(), skill, data);

		this.energyConsumption = data.getFloat("energy_consumption");
	}

	@Override
	protected void updateDescription(TranslatableComponent defaultDescription) {
		super.updateDescription(new TranslatableComponent(defaultDescription.getKey(), NumberUtil.roundToNthDecimalPlace(this.energyConsumption, 2)));
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public KeyMapping getKeybind() {
		return Minecraft.getInstance().options.keyJump;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public boolean shouldSendKeyPress() {
		Player player = Minecraft.getInstance().player;

		return !player.isOnGround() && !player.isCreative();
	}

	@Override
	public void handleKeyInput() {
		if (canJump) {
			ServerPlayer player = getPlayer();

			if (player.isOnGround() || player.isCreative())
				return;

			if (consumeResource(AoAResources.ENERGY.get(), energyConsumption, true)) {
				canJump = false;

				player.jumpFromGround();
				AoAPackets.messagePlayer(player, new UpdateClientMovementPacket(UpdateClientMovementPacket.Operation.SET).y((float)player.getDeltaMovement().y()));

				if (getSkill().canGainXp(true))
					getSkill().adjustXp(PlayerUtil.getTimeBasedXpForLevel(getSkill().getLevel(true), 16), false, false);
			}
		}
	}

	@Override
	public void handlePlayerFall(LivingFallEvent ev) {
		if (!canJump)
			ev.setDistance(ev.getDistance() - ev.getEntityLiving().getJumpPower() * 10f);

		canJump = true;
	}

	@Override
	public CompoundTag getSyncData(boolean forClientSetup) {
		CompoundTag syncData = super.getSyncData(forClientSetup);

		if (forClientSetup)
			syncData.putFloat("energy_consumption", energyConsumption);

		return syncData;
	}
}
