package net.tslat.aoa3.player.ability.innervation;

import com.google.gson.JsonObject;
import net.minecraft.client.KeyMapping;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.tslat.aoa3.client.AoAKeybinds;
import net.tslat.aoa3.client.player.AoAPlayerKeybindListener;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.event.dynamic.DynamicEventSubscriber;
import net.tslat.aoa3.library.constant.ScreenImageEffect;
import net.tslat.aoa3.player.AoAPlayerEventListener;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.PlayerUtil;

import java.util.List;
import java.util.function.Consumer;

public class InnervationMobLure extends AoAAbility.Instance {
	private final List<DynamicEventSubscriber<?>> eventSubscribers = List.of(
			listener(PlayerTickEvent.Pre.class, PlayerTickEvent.Pre::getEntity, serverOnly(this::handlePlayerTick)),
			whenTakingDamage(serverOnly(this::handleIncomingDamage)));

	private final float onHitDrain;
	private final float perTickDrain;
	private final float luredDamageModifier;

	private boolean isLuring = false;
	private Mob luringEntity = null;

	public InnervationMobLure(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.INNERVATION_MOB_LURE.get(), skill, data);

		this.onHitDrain = GsonHelper.getAsFloat(data, "on_hit_drain");
		this.perTickDrain = GsonHelper.getAsFloat(data, "per_tick_spirit_drain");
		this.luredDamageModifier = GsonHelper.getAsFloat(data, "lured_damage_modifier", 0.5f);
	}

	public InnervationMobLure(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.INNERVATION_MOB_LURE.get(), skill, data);

		this.onHitDrain = data.getFloat("on_hit_drain");
		this.perTickDrain = data.getFloat("per_tick_spirit_drain");
		this.luredDamageModifier = data.getFloat("lured_damage_modifier");
	}

	@Override
	protected void updateDescription(MutableComponent defaultDescription) {
		super.updateDescription(Component.translatable(((TranslatableContents)defaultDescription.getContents()).getKey(), NumberUtil.roundToNthDecimalPlace(this.perTickDrain * 20, 2), NumberUtil.roundToNthDecimalPlace(this.luredDamageModifier * 100, 2), NumberUtil.roundToNthDecimalPlace(this.onHitDrain, 2)));
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
				return InnervationMobLure.this;
			}

			@Override
			public KeyMapping getKeybind() {
				return AoAKeybinds.ABILITY_ACTION;
			}

			@Override
			public boolean shouldSendKeyPress() {
				return getPlayer().isCrouching();
			}
		});
	}

	@Override
	public void handleKeyInput() {
		if (getPlayer() instanceof ServerPlayer player) {
			if (!isLuring && player.isCrouching() && player.getLastHurtMob() instanceof Mob mob) {
				this.luringEntity = mob;
				this.isLuring = true;

				activatedActionKey(player);
			}
		}
	}

	private void handlePlayerTick(final PlayerTickEvent.Pre ev) {
		if (!this.isLuring)
			return;

		final Player pl = ev.getEntity();

		if (this.luringEntity == null || this.luringEntity.isDeadOrDying() || pl.isDeadOrDying() || !pl.isCrouching() || !this.skill.getPlayerDataManager().getResource(AoAResources.SPIRIT.get()).consume(this.perTickDrain, true)) {
			resetLureState();

			return;
		}

		if (pl.level().getGameTime() % 10 == 0 && pl instanceof ServerPlayer serverPl)
			new ScreenImageEffect(ScreenImageEffect.Type.ACTION_KEY_VIGNETTE).fullscreen(true).duration(10).sendToPlayer(serverPl);

		if (this.luringEntity.getTarget() != pl)
			this.luringEntity.setTarget(pl);
	}

	private void handleIncomingDamage(LivingIncomingDamageEvent ev) {
		if (this.isLuring && ev.getSource().getEntity() == this.luringEntity) {
			this.skill.getPlayerDataManager().getResource(AoAResources.SPIRIT.get()).consume(this.onHitDrain, true);
			ev.setAmount(ev.getAmount() * this.luredDamageModifier);

			resetLureState();

			if (this.skill.canGainXp(true))
				PlayerUtil.giveTimeBasedXpToPlayer((ServerPlayer)getPlayer(), this.skill.type(), 30,  false);
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
			data.putFloat("on_hit_drain", this.onHitDrain);
			data.putFloat("per_tick_spirit_drain", this.perTickDrain);
			data.putFloat("lured_damage_modifier", this.luredDamageModifier);
		}

		return data;
	}
}
