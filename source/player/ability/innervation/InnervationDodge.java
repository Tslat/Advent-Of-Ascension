package net.tslat.aoa3.player.ability.innervation;

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
import net.minecraft.world.TickRateManager;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.tslat.aoa3.client.AoAKeybinds;
import net.tslat.aoa3.client.player.AoAPlayerKeybindListener;
import net.tslat.aoa3.client.player.ClientPlayerDataManager;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.event.dynamic.DynamicEventSubscriber;
import net.tslat.aoa3.player.AoAPlayerEventListener;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.PlayerUtil;

import java.util.List;
import java.util.function.Consumer;

public class InnervationDodge extends AoAAbility.Instance {
	private final List<DynamicEventSubscriber<?>> eventSubscribers = List.of(
			whenTakingDamage(serverOnly(this::handleIncomingDamage)));

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
	public List<DynamicEventSubscriber<?>> getEventSubscribers() {
		return this.eventSubscribers;
	}

	@Override
	public void createKeybindListener(Consumer<AoAPlayerKeybindListener> consumer) {
		consumer.accept(new AoAPlayerKeybindListener() {
			@Override
			public AoAPlayerEventListener getEventListener() {
				return InnervationDodge.this;
			}

			@Override
			public KeyMapping getKeybind() {
				return AoAKeybinds.ABILITY_ACTION;
			}

			@Override
			public boolean shouldSendKeyPress() {
				Minecraft mc = Minecraft.getInstance();
				LocalPlayer player = mc.player;
				TickRateManager tickRateManager = mc.level.tickRateManager();
				float yRot = player.getViewYRot(mc.getTimer().getGameTimeDeltaPartialTick(!tickRateManager.isEntityFrozen(player)));

				if (player.input.leftImpulse == 0 || player.input.hasForwardImpulse() || player.level().getGameTime() <= InnervationDodge.this.activationTime + 5 || player.getAbilities().flying)
					return false;

				if (ClientPlayerDataManager.get().getResource(AoAResources.ENERGY.get()).hasAmount(InnervationDodge.this.energyCost)) {
					Vec3 movement = player.getDeltaMovement();
					double limit = player.onGround() ? 2.5d : 0.9d;
					double velocityX = Mth.clamp(movement.x() + (Mth.cos(yRot * ((float)Math.PI / 180F)) * player.input.leftImpulse), -limit, limit);
					double velocityZ = Mth.clamp(movement.z() + (Mth.sin(yRot * ((float)Math.PI / 180F)) * player.input.leftImpulse), -limit, limit);

					player.setDeltaMovement(new Vec3(velocityX, movement.y(), velocityZ));

					InnervationDodge.this.activationTime = player.level().getGameTime();
				}

				return true;
			}
		});
	}

	@Override
	public void handleKeyInput() {
		if (getPlayer() instanceof ServerPlayer player) {
			if (this.skill.getPlayerDataManager().getResource(AoAResources.ENERGY.get()).consume(this.energyCost, true)) {
				activatedActionKey(player);

				if (this.skill.canGainXp(true))
					PlayerUtil.giveTimeBasedXpToPlayer(player, this.skill.type(), 20,  false);
			}
		}
	}

	private void handleIncomingDamage(LivingIncomingDamageEvent ev) {
		if (ev.getEntity().level().getGameTime() < this.activationTime + 5 && DamageUtil.isMeleeDamage(ev.getSource()))
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
