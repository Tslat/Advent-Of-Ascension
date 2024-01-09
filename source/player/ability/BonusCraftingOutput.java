package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.event.custom.events.ItemCraftingEvent;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.RegistryUtil;
import org.jetbrains.annotations.Nullable;


public class BonusCraftingOutput extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.ITEM_CRAFTING};

	@Nullable
	private final Item outputTarget;
	@Nullable
	private final TagKey<Item> outputTargetTag;

	public BonusCraftingOutput(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.BONUS_CRAFTING_OUTPUT.get(), skill, data);

		if (data.has("item")) {
			outputTargetTag = null;
			outputTarget = AoARegistries.ITEMS.getEntry(new ResourceLocation(GsonHelper.getAsString(data, "item")));
		}
		else {
			outputTarget = null;
			outputTargetTag = ItemTags.create(new ResourceLocation(GsonHelper.getAsString(data, "tag")));
		}
	}

	public BonusCraftingOutput(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.BONUS_CRAFTING_OUTPUT.get(), skill, data);

		if (data.contains("item")) {
			outputTargetTag = null;
			outputTarget = AoARegistries.ITEMS.getEntry(new ResourceLocation(data.getString("item")));
		}
		else {
			outputTarget = null;
			outputTargetTag = ItemTags.create(new ResourceLocation(data.getString("tag")));
		}
	}

	@Override
	protected void updateDescription(MutableComponent defaultDescription) {
		MutableComponent component;

		if (outputTarget != null) {
			component = Component.translatable(((TranslatableContents)defaultDescription.getContents()).getKey() + ".item", getScalingDescriptionComponent(2), this.outputTarget.getDefaultInstance().getHoverName());
		}
		else {
			component = Component.translatable(((TranslatableContents)defaultDescription.getContents()).getKey() + ".tag", getScalingDescriptionComponent(2), this.outputTargetTag.location().toString());
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
			if (ev.getOutputStack().is(outputTargetTag))
				ev.getOutputStack().setCount((int)Math.ceil(ev.getOutputStack().getCount() * (1 + getScaledValue())));
		}
	}

	@Override
	public CompoundTag getSyncData(boolean forClientSetup) {
		CompoundTag data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			if (outputTarget != null) {
				data.putString("item", RegistryUtil.getId(outputTarget).toString());
			}
			else {
				data.putString("tag", outputTargetTag.location().toString());
			}
		}

		return data;
	}
}
