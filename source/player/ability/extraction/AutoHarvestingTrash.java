package net.tslat.aoa3.player.ability.extraction;

import com.google.gson.JsonObject;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.tslat.aoa3.client.ClientOperations;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.event.custom.events.PlayerSkillsLootModificationEvent;
import net.tslat.aoa3.event.dynamic.DynamicEventSubscriber;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.RegistryUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AutoHarvestingTrash extends AoAAbility.Instance {
	private final List<DynamicEventSubscriber<?>> eventSubscribers = List.of(
			listener(PlayerSkillsLootModificationEvent.class, serverOnly(this::handleLootModification)));
	@Nullable
	private Item consumingItem = null;

	public AutoHarvestingTrash(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.AUTO_HARVESTING_TRASH.get(), skill, data);
	}

	public AutoHarvestingTrash(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.AUTO_HARVESTING_TRASH.get(), skill, data);

		String itemId = data.getString("item");
		this.consumingItem = itemId.isEmpty() ? null : AoARegistries.ITEMS.getEntry(ResourceLocation.read(itemId).getOrThrow());
		updateDescription(Component.translatable(Util.makeDescriptionId("ability", AoARegistries.AOA_ABILITIES.getKey(type())) + ".description"));
	}

	@Override
	protected void updateDescription(MutableComponent defaultDescription) {
		MutableComponent description = defaultDescription;

		if (this.consumingItem != null)
			description = Component.translatable(((TranslatableContents)defaultDescription.getContents()).getKey() + ".item", Component.translatable(this.consumingItem.asItem().getDescriptionId()).withStyle(ChatFormatting.GRAY));

		super.updateDescription(description);
	}

	@Override
	public List<DynamicEventSubscriber<?>> getEventSubscribers() {
		return this.eventSubscribers;
	}

	private void handleLootModification(final PlayerSkillsLootModificationEvent ev) {
		if (this.consumingItem != null && ev.getLootContext().getParamOrNull(LootContextParams.BLOCK_STATE) != null)
			ev.getGeneratedLoot().removeIf(stack -> stack.getItem() == this.consumingItem);
	}

	@Override
	public void loadFromNbt(CompoundTag data) {
		super.loadFromNbt(data);

		if (data.contains("item")) {
			this.consumingItem = AoARegistries.ITEMS.getEntry(ResourceLocation.read(data.getString("item")).getOrThrow());

			updateDescription(Component.translatable(Util.makeDescriptionId("ability", AoARegistries.AOA_ABILITIES.getKey(type())) + ".description"));
			markForClientSync();
		}
	}

	@Override
	public CompoundTag saveToNbt() {
		CompoundTag data = super.saveToNbt();

		if (this.consumingItem != null)
			data.putString("item", RegistryUtil.getId(this.consumingItem).toString());

		return data;
	}

	@Override
	public CompoundTag getSyncData(boolean forClientSetup) {
		CompoundTag data = super.getSyncData(forClientSetup);

		data.putString("item", this.consumingItem != null ? RegistryUtil.getId(this.consumingItem).toString() : "");

		return data;
	}

	@Override
	public void receiveSyncData(CompoundTag data) {
		super.receiveSyncData(data);

		if (data.getString("item").equals("")) {
			this.consumingItem = null;
		}
		else {
			this.consumingItem = AoARegistries.ITEMS.getEntry(ResourceLocation.read(data.getString("item")).getOrThrow());
		}

		updateDescription(Component.translatable(Util.makeDescriptionId("ability", AoARegistries.AOA_ABILITIES.getKey(type())) + ".description"));
	}

	@Override
	public void receiveInteractionDataFromClient(String data) {
		if (data.equals(RegistryUtil.getId(Items.AIR).toString())) {
			consumingItem = null;
		}
		else {
			consumingItem = AoARegistries.ITEMS.getEntry(ResourceLocation.read(data).getOrThrow());
		}

		markForClientSync();
	}

	// Client Only
	@Override
	public boolean onGuiClick(final int mouseX, final int mouseY) {
		if (!ClientOperations.doAutoHarvestingTrashSelection(this, this.consumingItem))
			return false;

		return super.onGuiClick(mouseX, mouseY);
	}
}
