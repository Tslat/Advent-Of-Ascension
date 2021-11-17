package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
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

		this.energyCost = JSONUtils.getAsFloat(data, "energy_cost");
	}

	public InnervationDodge(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.INNERVATION_DODGE.get(), skill, data);

		this.energyCost = data.getFloat("energy_cost");
	}

	@Override
	public TranslationTextComponent getDescription() {
		return new TranslationTextComponent(super.getDescription().getKey(), NumberUtil.roundToNthDecimalPlace(this.energyCost, 2));
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public KeyBinding getKeybind() {
		return AoAKeybinds.ABILITY_ACTION;
	}

	@Override
	public boolean shouldSendKeyPress() {
		ClientPlayerEntity player = Minecraft.getInstance().player;

		float yRot = player.getViewYRot(Minecraft.getInstance().getDeltaFrameTime());

		if (player.input.leftImpulse == 0 || player.input.hasForwardImpulse() || player.level.getGameTime() <= activationTime + 5 || player.abilities.flying)
			return false;

		if (ClientPlayerDataManager.getResource(AoAResources.ENERGY.get()).hasAmount(this.energyCost)) {
			Vector3d movement = player.getDeltaMovement();
			double limit = player.isOnGround() ? 2.5d : 0.9d;
			double velocityX = MathHelper.clamp(movement.x() + (MathHelper.cos(yRot * ((float)Math.PI / 180F)) * player.input.leftImpulse), -limit, limit);
			double velocityZ = MathHelper.clamp(movement.z() + (MathHelper.sin(yRot * ((float)Math.PI / 180F)) * player.input.leftImpulse), -limit, limit);

			player.setDeltaMovement(new Vector3d(velocityX, movement.y(), velocityZ));

			activationTime = player.level.getGameTime();

			return true;
		}

		return false;
	}

	@Override
	public void handleKeyInput() {
		skill.getPlayerDataManager().getResource(AoAResources.ENERGY.get()).consume(this.energyCost, false);
		activatedActionKey(getPlayer());
		skill.adjustXp(PlayerUtil.getTimeBasedXpForLevel(skill.getLevel(true), 20), false, false);
	}

	@Override
	public void handlePreIncomingAttack(LivingAttackEvent ev) {
		if (ev.getEntityLiving().level.getGameTime() < activationTime + 5 && DamageUtil.isMeleeDamage(ev.getSource()))
			ev.setCanceled(true);
	}

	@Override
	public CompoundNBT getSyncData(boolean forClientSetup) {
		CompoundNBT data = super.getSyncData(forClientSetup);

		if (forClientSetup)
			data.putFloat("energy_cost", this.energyCost);

		return data;
	}
}
