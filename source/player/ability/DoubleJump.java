package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.text.TranslationTextComponent;
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

		this.energyConsumption = Math.max(0, JSONUtils.getAsFloat(data, "energy_consumption"));
	}

	public DoubleJump(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.DOUBLE_JUMP.get(), skill, data);

		this.energyConsumption = data.getFloat("energy_consumption");
	}

	@Override
	protected void updateDescription(TranslationTextComponent defaultDescription) {
		super.updateDescription(new TranslationTextComponent(defaultDescription.getKey(), NumberUtil.roundToNthDecimalPlace(this.energyConsumption, 2)));
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public KeyBinding getKeybind() {
		return Minecraft.getInstance().options.keyJump;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public boolean shouldSendKeyPress() {
		PlayerEntity player = Minecraft.getInstance().player;

		if (player.isOnGround() || player.jumpTriggerTime > 0)
			return false;

		if (player.getItemBySlot(EquipmentSlotType.CHEST).canElytraFly(player))
			return false;

		if (!player.isCreative())
			player.jumpTriggerTime = 7;

		return true;
	}

	@Override
	public void handleKeyInput() {
		ServerPlayerEntity player = getPlayer();

		if (canJump || player.isCreative()) {
			if (player.isOnGround())
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
	public CompoundNBT getSyncData(boolean forClientSetup) {
		CompoundNBT syncData = super.getSyncData(forClientSetup);

		if (forClientSetup)
			syncData.putFloat("energy_consumption", energyConsumption);

		return syncData;
	}
}
