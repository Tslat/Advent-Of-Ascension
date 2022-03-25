package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.event.custom.events.ItemCraftingEvent;
import net.tslat.aoa3.player.skill.AoASkill;

import javax.annotation.Nullable;

public class BonusCraftingOutput extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.ITEM_CRAFTING};

	@Nullable
	private final Item outputTarget;
	@Nullable
	private final Tags.IOptionalNamedTag<Item> outputTargetTag;

	public BonusCraftingOutput(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.BONUS_CRAFTING_OUTPUT.get(), skill, data);

		if (data.has("item")) {
			outputTargetTag = null;
			outputTarget = ForgeRegistries.ITEMS.getValue(new ResourceLocation(JSONUtils.getAsString(data, "item")));
		}
		else {
			outputTarget = null;
			outputTargetTag = ItemTags.createOptional(new ResourceLocation(JSONUtils.getAsString(data, "tag")));
		}
	}

	public BonusCraftingOutput(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.BONUS_CRAFTING_OUTPUT.get(), skill, data);

		if (data.contains("item")) {
			outputTargetTag = null;
			outputTarget = ForgeRegistries.ITEMS.getValue(new ResourceLocation(data.getString("item")));
		}
		else {
			outputTarget = null;
			outputTargetTag = ItemTags.createOptional(new ResourceLocation(data.getString("tag")));
		}
	}

	@Override
	protected void updateDescription(TranslationTextComponent defaultDescription) {
		TranslationTextComponent component;

		if (outputTarget != null) {
			component = new TranslationTextComponent(defaultDescription.getKey() + ".item", getScalingDescriptionComponent(2), this.outputTarget.getDefaultInstance().getHoverName());
		}
		else {
			component = new TranslationTextComponent(defaultDescription.getKey() + ".tag", getScalingDescriptionComponent(2), this.outputTargetTag.getName().toString());
		}

		super.updateDescription(component);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleItemCrafting(ItemCraftingEvent ev) {
		if (outputTarget != null) {
			if (ev.getOutputStack().getItem() == outputTarget)
				ev.getOutputStack().setCount((int)Math.ceil(ev.getOutputStack().getCount() * (1 + getScaledValue())));
		}
		else {
			if (ev.getOutputStack().getItem().is(outputTargetTag))
				ev.getOutputStack().setCount((int)Math.ceil(ev.getOutputStack().getCount() * (1 + getScaledValue())));
		}
	}

	@Override
	public CompoundNBT getSyncData(boolean forClientSetup) {
		CompoundNBT data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			if (outputTarget != null) {
				data.putString("item", outputTarget.getRegistryName().toString());
			}
			else {
				data.putString("tag", outputTargetTag.getName().toString());
			}
		}

		return data;
	}
}
