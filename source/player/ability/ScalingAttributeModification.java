package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.event.custom.events.PlayerLevelChangeEvent;
import net.tslat.aoa3.player.PlayerDataManager;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.StringUtil;

import java.util.UUID;

import static net.tslat.aoa3.player.AoAPlayerEventListener.ListenerType.ATTRIBUTE_MODIFIERS;
import static net.tslat.aoa3.player.AoAPlayerEventListener.ListenerType.LEVEL_CHANGE;

public class ScalingAttributeModification extends AoAAbility.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ATTRIBUTE_MODIFIERS, LEVEL_CHANGE};

	private final Attribute attribute;
	private final float baseValue;
	private final float perLevelValue;
	private final AttributeModifier modifier;

	private float loginHealth = -1;

	public ScalingAttributeModification(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.SCALING_ATTRIBUTE_MODIFICATION.get(), skill, data);

		this.attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation(JSONUtils.getAsString(data, "attribute")));
		this.baseValue = JSONUtils.getAsFloat(data, "base_value");
		this.perLevelValue = JSONUtils.getAsFloat(data, "per_level_value");
		this.modifier = new AttributeModifier(UUID.randomUUID(), getUniqueIdentifier(), 0, AttributeModifier.Operation.fromValue(JSONUtils.getAsInt(data, "operation"))) {
			@Override
			public double getAmount() {
				return baseValue + skill.getLevel(false) * perLevelValue;
			}
		};
	}

	public ScalingAttributeModification(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.SCALING_ATTRIBUTE_MODIFICATION.get(), skill, data);

		this.attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation(data.getString("attribute")));
		this.baseValue = data.getFloat("base_value");
		this.perLevelValue = data.getFloat("per_level_value");

		this.modifier = new AttributeModifier(data.getUUID("uuid"), getUniqueIdentifier(), 0, AttributeModifier.Operation.fromValue(data.getInt("operation"))) {
			@Override
			public double getAmount() {
				return baseValue + skill.getLevel(false) * perLevelValue;
			}
		};
	}

	@Override
	public TranslationTextComponent getDescription() {
		String key = super.getDescription().getKey();
		String amount;
		String perLevel;

		switch (this.modifier.getOperation()) {
			case MULTIPLY_BASE:
			case MULTIPLY_TOTAL:
				amount = "+" + NumberUtil.roundToNthDecimalPlace(baseValue * 100, 3) + "%";
				perLevel = NumberUtil.roundToNthDecimalPlace(this.perLevelValue * 100, 3) + "%";
				break;
			case ADDITION:
			default:
				amount = NumberUtil.roundToNthDecimalPlace(baseValue, 3);
				perLevel = NumberUtil.roundToNthDecimalPlace(this.perLevelValue, 3);
				break;
		}

		if (baseValue == 0)
			key += ".scaleOnly";

		return new TranslationTextComponent(key, StringUtil.toTitleCase(attribute.getDescriptionId().substring(attribute.getDescriptionId().lastIndexOf(".") + 1)), amount, perLevel);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void applyAttributeModifiers(PlayerDataManager plData) {
		EntityUtil.reapplyAttributeModifier(plData.player(), attribute, modifier, false);

		if (loginHealth > 0) {
			plData.player().setHealth(loginHealth);
			loginHealth = -1;
		}
	}

	@Override
	public void removeAttributeModifiers(PlayerDataManager plData) {
		EntityUtil.removeAttributeModifier(plData.player(), attribute, modifier.getId());

		if (attribute == Attributes.MAX_HEALTH && plData.player().getHealth() > plData.player().getMaxHealth())
			plData.player().setHealth(plData.player().getMaxHealth());
	}

	@Override
	public void handleLevelChange(PlayerLevelChangeEvent ev) {
		EntityUtil.reapplyAttributeModifier(ev.getPlayer(), attribute, modifier, false);
	}

	@Override
	public CompoundNBT getSyncData(boolean forClientSetup) {
		CompoundNBT data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			data.putString("attribute", attribute.getRegistryName().toString());
			data.putInt("operation", this.modifier.getOperation().toValue());
			data.putFloat("base_value", this.baseValue);
			data.putFloat("per_level_value", this.perLevelValue);
			data.putUUID("uuid", this.modifier.getId());
		}

		return data;
	}

	@Override
	public CompoundNBT saveToNbt() {
		CompoundNBT data = super.saveToNbt();

		if (attribute == Attributes.MAX_HEALTH)
			data.putDouble("current_health", getPlayer().getHealth());

		return data;
	}

	@Override
	public void loadFromNbt(CompoundNBT data) {
		super.loadFromNbt(data);

		if (attribute == Attributes.MAX_HEALTH && getListenerState() == ListenerState.ACTIVE && data.contains("current_health")) {
			if (getLevelReq() == 1) {
				loginHealth = (float)data.getDouble("current_health");
			}
			else {
				getPlayer().setHealth((float)data.getDouble("current_health"));
			}
		}
	}
}
