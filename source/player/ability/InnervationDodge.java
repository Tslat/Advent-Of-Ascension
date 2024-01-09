package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.event.entity.living.LivingAttackEvent;
import net.tslat.aoa3.client.AoAKeybinds;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.player.ClientPlayerDataManager;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.PlayerUtil;

public class InnervationDodge extends AoAAbility.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.KEY_INPUT, ListenerType.INCOMING_ATTACK_BEFORE};

	private final float energyCost;

	private long activationTime;

	public InnervationDodge(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.INNERVATION_DODGE.get(), skill, data);

		this.energyCost = GsonHelper.getAsFloat(data, "energy_cost");
	}

	public InnervationDodge(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.INNERVATION_DODGE.get(), skill, data);

		this.energyCost = data.getFloat("energy_cost");
	}

	@Override
	protected void updateDescription(MutableComponent defaultDescription) {
		super.updateDescription(Component.translatable(((TranslatableContents)defaultDescription.getContents()).getKey(), NumberUtil.roundToNthDecimalPlace(this.energyCost, 2)));
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
		LocalPlayer player = Minecraft.getInstance().player;
		float yRot = player.getViewYRot(Minecraft.getInstance().getDeltaFrameTime());

		if (player.input.leftImpulse == 0 || player.input.hasForwardImpulse() || player.level().getGameTime() <= activationTime + 5 || player.getAbilities().flying)
			return false;

		if (ClientPlayerDataManager.get().getResource(AoAResources.ENERGY.get()).hasAmount(this.energyCost)) {
			Vec3 movement = player.getDeltaMovement();
			double limit = player.onGround() ? 2.5d : 0.9d;
			double velocityX = Mth.clamp(movement.x() + (Mth.cos(yRot * ((float)Math.PI / 180F)) * player.input.leftImpulse), -limit, limit);
			double velocityZ = Mth.clamp(movement.z() + (Mth.sin(yRot * ((float)Math.PI / 180F)) * player.input.leftImpulse), -limit, limit);

			player.setDeltaMovement(new Vec3(velocityX, movement.y(), velocityZ));

			activationTime = player.level().getGameTime();
		}

		return true;
	}

	@Override
	public void handleKeyInput() {
		ServerPlayer player = getPlayer();

		if (skill.getPlayerDataManager().getResource(AoAResources.ENERGY.get()).consume(this.energyCost, true)) {
			activatedActionKey(player);

			if (skill.canGainXp(true))
				skill.adjustXp(PlayerUtil.getTimeBasedXpForLevel(skill.getLevel(true), 20), false, false);
		}
	}

	@Override
	public void handlePreIncomingAttack(LivingAttackEvent ev) {
		if (ev.getEntity().level().getGameTime() < activationTime + 5 && DamageUtil.isMeleeDamage(ev.getSource()))
			ev.setCanceled(true);
	}

	@Override
	public CompoundTag getSyncData(boolean forClientSetup) {
		CompoundTag data = super.getSyncData(forClientSetup);

		if (forClientSetup)
			data.putFloat("energy_cost", this.energyCost);

		return data;
	}
}
