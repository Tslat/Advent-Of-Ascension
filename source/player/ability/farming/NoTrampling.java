package net.tslat.aoa3.player.ability.farming;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.event.dynamic.DynamicEventSubscriber;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.player.skill.AoASkill;

import java.util.List;

public class NoTrampling extends AoAAbility.Instance {
	private final List<DynamicEventSubscriber<?>> eventSubscribers = List.of(
			listener(BlockEvent.FarmlandTrampleEvent.class, BlockEvent.FarmlandTrampleEvent::getEntity, serverOnly(this::handleTrampling)));

	public NoTrampling(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.NO_TRAMPLING.get(), skill, data);
	}

	public NoTrampling(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.NO_TRAMPLING.get(), skill, data);
	}

	@Override
	public List<DynamicEventSubscriber<?>> getEventSubscribers() {
		return this.eventSubscribers;
	}

	private void handleTrampling(BlockEvent.FarmlandTrampleEvent ev) {
		ev.setCanceled(true);
	}
}
