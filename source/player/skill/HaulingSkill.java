package net.tslat.aoa3.player.skill;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.player.ItemFishedEvent;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.smartbrainlib.util.RandomUtil;

public class HaulingSkill extends AoASkill.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.FISHED_ITEM, ListenerType.CUSTOM};

	public HaulingSkill(ServerPlayerDataManager plData, JsonObject jsonData) {
		super(AoASkills.HAULING.get(), plData, jsonData);
	}

	public HaulingSkill(CompoundTag nbtData) {
		super(AoASkills.HAULING.get(), nbtData);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleItemFished(ItemFishedEvent ev, boolean isHauling) {
		if (!canGainXp(true))
			return;

		float xp = PlayerUtil.getTimeBasedXpForLevel(getLevel(true), 200) * (float)(1 + RandomUtil.randomScaledGaussianValue(0.25f));

		if (isHauling) {
			xp *= 2f;

			for (ItemStack stack : ev.getDrops()) {
				if (stack.is(AoATags.Items.HAULING_FISH))
					xp *= 1.25f;
			}
		}

		adjustXp(xp, false, false);
	}

	@Override
	public void handleCustomInteraction(String interactionType, Object data) {
		if (interactionType.equals("fishing_cage_harvest")) {
			if (!canGainXp(true))
				return;

			ItemStack[] loot = (ItemStack[])data;
			float xp = PlayerUtil.getTimeBasedXpForLevel(getLevel(true), 1000) * Math.min(4, loot.length);

			adjustXp(xp, false, false);
		}
	}
}
