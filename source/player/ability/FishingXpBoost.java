package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.JSONUtils;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.event.custom.events.HaulingItemFishedEvent;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.RandomUtil;

public class FishingXpBoost extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.FISHED_ITEM};

	private final boolean useAddition;

	public FishingXpBoost(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.FISHING_XP_BOOST.get(), skill, data);

		this.useAddition = JSONUtils.getAsBoolean(data, "use_addition", false);
	}

	public FishingXpBoost(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.FISHING_XP_BOOST.get(), skill, data);

		this.useAddition = data.getBoolean("use_addition");
	}

	@Override
	protected boolean isPercent() {
		return !useAddition;
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleItemFished(ItemFishedEvent ev, boolean isHauling) {
		if (ev instanceof HaulingItemFishedEvent) {
			HaulingItemFishedEvent haulingEv = (HaulingItemFishedEvent)ev;

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
			PlayerEntity player = ev.getPlayer();

			player.level.addFreshEntity(new ExperienceOrbEntity(player.level, player.getX() + 0.5d, player.getY() + 0.5d, player.getZ() + 0.5d, RandomUtil.randomNumberBetween(1, 6)));
		}
	}

	@Override
	public CompoundNBT getSyncData(boolean forClientSetup) {
		CompoundNBT data = super.getSyncData(forClientSetup);

		if (forClientSetup)
			data.putBoolean("use_addition", this.useAddition);

		return data;
	}
}
