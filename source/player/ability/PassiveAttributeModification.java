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
import net.tslat.aoa3.player.PlayerDataManager;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.StringUtil;

import java.util.UUID;

import static net.tslat.aoa3.player.AoAPlayerEventListener.ListenerType.ATTRIBUTE_MODIFIERS;

public class PassiveAttributeModification extends AoAAbility.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ATTRIBUTE_MODIFIERS};

	private final Attribute attribute;
	private final AttributeModifier modifier;

	public PassiveAttributeModification(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.PASSIVE_ATTRIBUTE_MODIFICATION.get(), skill, data);

		this.attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation(JSONUtils.getAsString(data, "attribute")));
		this.modifier = new AttributeModifier(UUID.randomUUID(), getUniqueIdentifier(), JSONUtils.getAsFloat(data, "amount"), AttributeModifier.Operation.fromValue(JSONUtils.getAsInt(data, "operation")));
	}

	public PassiveAttributeModification(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.PASSIVE_ATTRIBUTE_MODIFICATION.get(), skill, data);

		this.attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation(data.getString("attribute")));
		this.modifier = new AttributeModifier(data.getUUID("uuid"), getUniqueIdentifier(), data.getFloat("amount"), AttributeModifier.Operation.fromValue(data.getInt("operation")));
	}

	@Override
	public TranslationTextComponent getDescription() {
		String amount;

		switch (this.modifier.getOperation()) {
			case MULTIPLY_BASE:
			case MULTIPLY_TOTAL:
				amount = "+" + NumberUtil.roundToNthDecimalPlace((float)this.modifier.getAmount() * 100, 3) + "%";
				break;
			case ADDITION:
			default:
				amount = NumberUtil.roundToNthDecimalPlace((float)this.modifier.getAmount(), 3);
				break;
		}

		return new TranslationTextComponent(super.getDescription().getKey(), StringUtil.toTitleCase(attribute.getDescriptionId().substring(attribute.getDescriptionId().lastIndexOf(".") + 1)), amount);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void applyAttributeModifiers(PlayerDataManager plData) {
		EntityUtil.reapplyAttributeModifier(plData.player(), attribute, modifier, false);
	}

	@Override
	public void removeAttributeModifiers(PlayerDataManager plData) {
		EntityUtil.removeAttributeModifier(plData.player(), attribute, modifier.getId());

		if (attribute == Attributes.MAX_HEALTH && plData.player().getHealth() > plData.player().getMaxHealth())
			plData.player().setHealth(plData.player().getMaxHealth());
	}

	@Override
	public CompoundNBT getSyncData(boolean forClientSetup) {
		CompoundNBT data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			data.putString("attribute", attribute.getRegistryName().toString());
			data.putInt("operation", this.modifier.getOperation().toValue());
			data.putFloat("amount", (float)this.modifier.getAmount());
			data.putUUID("uuid", this.modifier.getId());
		}

		return data;
	}

	@Override
	public CompoundNBT saveToNbt() {
		CompoundNBT data = super.saveToNbt();

		if (attribute == Attributes.MAX_HEALTH)
			data.putFloat("current_health", getPlayer().getHealth());

		return data;
	}

	@Override
	public void loadFromNbt(CompoundNBT data) {
		super.loadFromNbt(data);

		if (attribute == Attributes.MAX_HEALTH && getListenerState() == ListenerState.ACTIVE && data.contains("current_health")) {
			float health = data.getFloat("current_health");

			if (health > 0)
				getPlayer().setHealth(health);
		}
	}
}
