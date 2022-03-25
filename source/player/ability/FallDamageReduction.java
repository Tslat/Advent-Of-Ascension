package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.player.skill.AoASkill;

public class FallDamageReduction extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.PLAYER_FALL};

	private final int minHeight;
	private final int maxHeight;

	public FallDamageReduction(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.FALL_DAMAGE_REDUCTION.get(), skill, data);

		this.minHeight = JSONUtils.getAsInt(data, "min_height", 0);
		this.maxHeight = JSONUtils.getAsInt(data, "max_height", Integer.MAX_VALUE);
	}

	public FallDamageReduction(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.FALL_DAMAGE_REDUCTION.get(), skill, data);

		this.minHeight = data.getInt("min_height");
		this.maxHeight = data.getInt("max_height");
	}

	@Override
	protected void updateDescription(TranslationTextComponent defaultDescription) {
		String key = defaultDescription.getKey();

		if (minHeight > 0) {
			if (maxHeight == Integer.MAX_VALUE)
				key += ".min";
		}
		else if (maxHeight < Integer.MAX_VALUE) {
			key += ".max";
		}

		super.updateDescription(new TranslationTextComponent(key,
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

			if (MathHelper.ceil((ev.getDistance() - 3) * ev.getDamageMultiplier()) < 1)
				ev.setDamageMultiplier(0);
		}
	}

	@Override
	public CompoundNBT getSyncData(boolean forClientSetup) {
		CompoundNBT data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			data.putInt("min_height", this.minHeight);
			data.putInt("max_height", this.maxHeight);
		}

		return data;
	}
}
