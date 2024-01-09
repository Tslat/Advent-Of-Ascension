package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.Mth;
import net.neoforged.neoforge.event.entity.living.LivingFallEvent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.player.skill.AoASkill;

public class FallDamageReduction extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.PLAYER_FALL};

	private final int minHeight;
	private final int maxHeight;

	public FallDamageReduction(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.FALL_DAMAGE_REDUCTION.get(), skill, data);

		this.minHeight = GsonHelper.getAsInt(data, "min_height", 0);
		this.maxHeight = GsonHelper.getAsInt(data, "max_height", Integer.MAX_VALUE);
	}

	public FallDamageReduction(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.FALL_DAMAGE_REDUCTION.get(), skill, data);

		this.minHeight = data.getInt("min_height");
		this.maxHeight = data.getInt("max_height");
	}

	@Override
	protected void updateDescription(MutableComponent defaultDescription) {
		String key = ((TranslatableContents)defaultDescription.getContents()).getKey();

		if (minHeight > 0) {
			if (maxHeight == Integer.MAX_VALUE)
				key += ".min";
		}
		else if (maxHeight < Integer.MAX_VALUE) {
			key += ".max";
		}

		super.updateDescription(Component.translatable(key,
				minHeight - 1,
				maxHeight,
				getScalingDescriptionComponent(2)
		));
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handlePlayerFall(LivingFallEvent ev) {
		if (ev.getDistance() >= minHeight && ev.getDistance() <= maxHeight) {
			ev.setDamageMultiplier(Math.max(0, ev.getDamageMultiplier() * (1 - Math.min(1, getScaledValue()))));

			if (Mth.ceil((ev.getDistance() - 3) * ev.getDamageMultiplier()) < 1)
				ev.setDamageMultiplier(0);
		}
	}

	@Override
	public CompoundTag getSyncData(boolean forClientSetup) {
		CompoundTag data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			data.putInt("min_height", this.minHeight);
			data.putInt("max_height", this.maxHeight);
		}

		return data;
	}
}
