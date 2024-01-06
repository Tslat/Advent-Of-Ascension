package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.client.ClientOperations;
import net.tslat.aoa3.client.gui.container.SelectInventoryItemScreen;
import net.tslat.aoa3.common.packet.AoANetworking;
import net.tslat.aoa3.common.packet.packets.SyncAoAAbilityDataPacket;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.player.skill.AoASkill;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AutoHarvestingTrash extends AoAAbility.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.LOOT_MODIFICATION};

	@Nullable
	private Item consumingItem = null;

	public AutoHarvestingTrash(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.AUTO_HARVESTING_TRASH.get(), skill, data);
	}

	public AutoHarvestingTrash(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.AUTO_HARVESTING_TRASH.get(), skill, data);

		String itemId = data.getString("item");
		this.consumingItem = itemId.isEmpty() ? null : ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemId));
	}

	@Override
	protected void updateDescription(MutableComponent defaultDescription) {
		MutableComponent description = defaultDescription;

		if (this.consumingItem != null)
			description = Component.translatable(((TranslatableContents)defaultDescription.getContents()).getKey() + ".item", Component.translatable(this.consumingItem.asItem().getDescriptionId()).withStyle(ChatFormatting.GRAY));

		super.updateDescription(description);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleLootModification(List<ItemStack> loot, LootContext context) {
		if (consumingItem != null && context.getParamOrNull(LootContextParams.BLOCK_STATE) != null)
			loot.removeIf(stack -> stack.getItem() == consumingItem);
	}

	@Override
	public void loadFromNbt(CompoundTag data) {
		super.loadFromNbt(data);

		if (data.contains("item")) {
			this.consumingItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(data.getString("item")));

			updateDescription(Component.translatable(Util.makeDescriptionId("ability", AoARegistries.AOA_ABILITIES.getId(type())) + ".description"));
			markForClientSync();
		}
	}

	@Override
	public CompoundTag saveToNbt() {
		CompoundTag data = super.saveToNbt();

		if (this.consumingItem != null)
			data.putString("item", ForgeRegistries.ITEMS.getKey(this.consumingItem).toString());

		return data;
	}

	@Override
	public CompoundTag getSyncData(boolean forClientSetup) {
		CompoundTag data = super.getSyncData(forClientSetup);

		data.putString("item", this.consumingItem != null ? ForgeRegistries.ITEMS.getKey(this.consumingItem).toString() : "");

		return data;
	}

	@Override
	public void receiveSyncData(CompoundTag data) {
		super.receiveSyncData(data);

		if (data.getString("item").equals("")) {
			this.consumingItem = null;
		}
		else {
			this.consumingItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(data.getString("item")));
		}

		updateDescription(Component.translatable(Util.makeDescriptionId("ability", AoARegistries.AOA_ABILITIES.getId(type())) + ".description"));
	}

	@Override
	public void receiveInteractionDataFromClient(String data) {
		if (data.equals(ForgeRegistries.ITEMS.getKey(Items.AIR).toString())) {
			consumingItem = null;
		}
		else {
			consumingItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(data));
		}

		markForClientSync();
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean onGuiClick(final int mouseX, final int mouseY) {
		Minecraft mc = Minecraft.getInstance();

		if (ClientOperations.isPressingCrouchKey()) {
			ForgeHooksClient.pushGuiLayer(mc, new SelectInventoryItemScreen(mc, consumingItem, item -> AoANetworking.sendToServer(new SyncAoAAbilityDataPacket(this, ForgeRegistries.ITEMS.getKey(item).toString()))));

			return false;
		}

		return super.onGuiClick(mouseX, mouseY);
	}
}
