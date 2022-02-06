package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.player.skill.AoASkill;

import javax.annotation.Nullable;

public class BonusCraftingOutput extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.ITEM_CRAFT};

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
			component = new TranslationTextComponent(defaultDescription.getKey() + ".item", getChanceDescriptionComponent(2), this.outputTarget.getDefaultInstance().getHoverName());
		}
		else {
			component = new TranslationTextComponent(defaultDescription.getKey() + ".tag", getChanceDescriptionComponent(2), this.outputTargetTag.getName().toString());
		}

		super.updateDescription(component);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleItemCraft(PlayerEvent.ItemCraftedEvent ev) {
		if (outputTarget != null) {
			if (ev.getCrafting().getItem() == outputTarget)
				ev.getCrafting().setCount((int)Math.ceil(ev.getCrafting().getCount() * (1 + getScaledValue())));
		}
		else {
			if (ev.getCrafting().getItem().is(outputTargetTag))
				ev.getCrafting().setCount((int)Math.ceil(ev.getCrafting().getCount() * (1 + getScaledValue())));
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
