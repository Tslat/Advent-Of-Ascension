package net.tslat.aoa3.player.ability.hauling;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.player.ItemFishedEvent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.event.custom.events.HaulingItemFishedEvent;
import net.tslat.aoa3.event.dynamic.DynamicEventSubscriber;
import net.tslat.aoa3.player.ability.generic.ScalableModAbility;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.tme.api.util.RandomUtil;

import java.util.List;

public class FishingXpBoost extends ScalableModAbility {
	private final List<DynamicEventSubscriber<?>> eventSubscribers = List.of(
			listener(ItemFishedEvent.class, serverOnly(this::handleItemFished)));

	private final boolean useAddition;

	public FishingXpBoost(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.FISHING_XP_BOOST.get(), skill, data);

		this.useAddition = GsonHelper.getAsBoolean(data, "use_addition", false);
	}

	public FishingXpBoost(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.FISHING_XP_BOOST.get(), skill, data);

		this.useAddition = data.getBoolean("use_addition");
	}

	@Override
	protected boolean isPercent() {
		return !useAddition;
	}

	@Override
	public List<DynamicEventSubscriber<?>> getEventSubscribers() {
		return this.eventSubscribers;
	}

	private void handleItemFished(final ItemFishedEvent ev) {
		if (ev instanceof HaulingItemFishedEvent haulingEv) {
			float xp = haulingEv.getXp();

			if (useAddition) {
				xp += getScaledValue();
			}
			else {
				xp *= 1 + getScaledValue();
			}

			haulingEv.setXp((int)xp);
		}
		else {
			Player player = ev.getEntity();

			player.level().addFreshEntity(new ExperienceOrb(player.level(), player.getX() + 0.5d, player.getY() + 0.5d, player.getZ() + 0.5d, RandomUtil.numberBetween(1, 6)));
		}
	}

	@Override
	public CompoundTag getSyncData(boolean forClientSetup) {
		CompoundTag data = super.getSyncData(forClientSetup);

		if (forClientSetup)
			data.putBoolean("use_addition", this.useAddition);

		return data;
	}
}
