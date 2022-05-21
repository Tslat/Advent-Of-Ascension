package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.AoAKeybinds;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.ScreenOverlayPacket;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.PlayerUtil;

public class InnervationMobLure extends AoAAbility.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.KEY_INPUT, ListenerType.PLAYER_TICK, ListenerType.INCOMING_ATTACK_DURING};

	private final float perTickDrain;
	private final float luredDamageModifier;

	private boolean isLuring = false;
	private Mob luringEntity = null;

	public InnervationMobLure(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.INNERVATION_MOB_LURE.get(), skill, data);

		this.perTickDrain = GsonHelper.getAsFloat(data, "per_tick_energy_drain");
		this.luredDamageModifier = GsonHelper.getAsFloat(data, "lured_damage_modifier", 0.5f);
	}

	public InnervationMobLure(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.INNERVATION_MOB_LURE.get(), skill, data);

		this.perTickDrain = data.getFloat("per_tick_energy_drain");
		this.luredDamageModifier = data.getFloat("lured_damage_modifier");
	}

	@Override
	protected void updateDescription(TranslatableComponent defaultDescription) {
		super.updateDescription(new TranslatableComponent(defaultDescription.getKey(), NumberUtil.roundToNthDecimalPlace(this.perTickDrain * 20, 2), NumberUtil.roundToNthDecimalPlace(this.luredDamageModifier * 100, 2)));
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public KeyMapping getKeybind() {
		return AoAKeybinds.ABILITY_ACTION;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean shouldSendKeyPress() {
		return Minecraft.getInstance().player.isCrouching();
	}

	@Override
	public void handleKeyInput() {
		ServerPlayer player = getPlayer();

		if (!isLuring && player.isCrouching() && player.getLastHurtMob() instanceof Mob mob) {
			this.luringEntity = mob;
			this.isLuring = true;

			activatedActionKey(player);
		}
	}

	@Override
	public void handlePlayerTick(TickEvent.PlayerTickEvent ev) {
		if (!isLuring)
			return;

		if (luringEntity == null || luringEntity.isDeadOrDying() || ev.player.isDeadOrDying() || !ev.player.isCrouching() || !skill.getPlayerDataManager().getResource(AoAResources.SPIRIT.get()).consume(this.perTickDrain, true)) {
			resetLureState();

			return;
		}

		if (ev.player.level.getGameTime() % 10 == 0 && ev.player instanceof ServerPlayer)
			AoAPackets.messagePlayer((ServerPlayer)ev.player, new ScreenOverlayPacket(AdventOfAscension.id("textures/gui/overlay/misc/action_key_activation_vignette.png"), 10));

		if (luringEntity.getTarget() != ev.player)
			luringEntity.setTarget(ev.player);
	}

	@Override
	public void handleIncomingAttack(LivingHurtEvent ev) {
		if (isLuring && ev.getSource().getEntity() == luringEntity) {
			ev.setAmount(ev.getAmount() * this.luredDamageModifier);

			resetLureState();

			if (skill.canGainXp(true))
				skill.adjustXp(PlayerUtil.getTimeBasedXpForLevel(skill.getLevel(true), 30), false, false);
		}
	}

	private void resetLureState() {
		this.isLuring = false;
		this.luringEntity = null;
	}

	@Override
	public CompoundTag getSyncData(boolean forClientSetup) {
		CompoundTag data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			data.putFloat("per_tick_energy_drain", this.perTickDrain);
			data.putFloat("lured_damage_modifier", this.luredDamageModifier);
		}

		return data;
	}
}
