package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.EquipmentSlot;
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
	protected void updateDescription(MutableComponent defaultDescription) {
		super.updateDescription(Component.translatable(((TranslatableContents)defaultDescription.getContents()).getKey(), NumberUtil.roundToNthDecimalPlace(this.energyConsumption, 2)));
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

		if (player.onGround() || player.jumpTriggerTime > 0)
			return false;

		if (player.getItemBySlot(EquipmentSlot.CHEST).canElytraFly(player))
			return false;

		if (!player.isCreative())
			player.jumpTriggerTime = 7;

		return true;
	}

	@Override
	public void handleKeyInput() {
		ServerPlayer player = getPlayer();

		if (canJump || player.isCreative()) {
			if (player.onGround())
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
			ev.setDistance(ev.getDistance() - ev.getEntity().getJumpPower() * 10f);

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
