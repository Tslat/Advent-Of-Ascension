package net.tslat.aoa3.player.skill;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.player.ItemFishedEvent;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.event.custom.events.HaulingItemFishedEvent;
import net.tslat.aoa3.event.dynamic.DynamicEventSubscriber;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.tme.api.util.RandomUtil;

import java.util.List;

public class HaulingSkill extends AoASkill.Instance {
	private final List<DynamicEventSubscriber<?>> eventSubscribers = List.of(
			listener(ItemFishedEvent.class, ItemFishedEvent::getEntity, serverOnly(this::handleItemFished)));

	public HaulingSkill(ServerPlayerDataManager plData, JsonObject jsonData) {
		super(AoASkills.HAULING.get(), plData, jsonData);
	}

	public HaulingSkill(CompoundTag nbtData) {
		super(AoASkills.HAULING.get(), nbtData);
	}

	@Override
	public List<DynamicEventSubscriber<?>> getEventSubscribers() {
		return this.eventSubscribers;
	}

	private void handleItemFished(final ItemFishedEvent ev) {
		if (!canGainXp(true))
			return;

		float xp = PlayerUtil.getTimeBasedXpForLevel(getLevel(true), 200) * (float)(1 + RandomUtil.scaledGaussianValue(0.25f));

		if (ev instanceof HaulingItemFishedEvent) {
			xp *= 2f;

			for (ItemStack stack : ev.getDrops()) {
				if (stack.is(AoATags.Items.HAULING_FISH))
					xp *= 1.25f;
			}
		}

		adjustXp(xp, false, false);
	}
}
