package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.client.ClientOperations;
import net.tslat.aoa3.client.gui.container.SelectInventoryItemScreen;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.SyncAoAAbilityDataPacket;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.player.skill.AoASkill;

import javax.annotation.Nullable;
import java.util.List;

public class AutoHarvestingTrash extends AoAAbility.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.LOOT_MODIFICATION};

	@Nullable
	private Item consumingItem = null;

	public AutoHarvestingTrash(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.AUTO_HARVESTING_TRASH.get(), skill, data);
	}

	public AutoHarvestingTrash(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.AUTO_HARVESTING_TRASH.get(), skill, data);

		String itemId = data.getString("item");
		this.consumingItem = itemId.isEmpty() ? null : ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemId));
	}

	@Override
	protected void updateDescription(TranslationTextComponent defaultDescription) {
		TranslationTextComponent description = defaultDescription;

		if (this.consumingItem != null)
			description = new TranslationTextComponent(defaultDescription.getKey() + ".item", new TranslationTextComponent(this.consumingItem.asItem().getDescriptionId()).withStyle(TextFormatting.GRAY));

		super.updateDescription(description);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleLootModification(List<ItemStack> loot, LootContext context) {
		if (consumingItem != null && context.getParamOrNull(LootParameters.BLOCK_STATE) != null)
			loot.removeIf(stack -> stack.getItem() == consumingItem);
	}

	@Override
	public void loadFromNbt(CompoundNBT data) {
		super.loadFromNbt(data);

		if (data.contains("item")) {
			this.consumingItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(data.getString("item")));

			updateDescription(new TranslationTextComponent(Util.makeDescriptionId("ability", type().getRegistryName()) + ".description"));
			markForClientSync();
		}
	}

	@Override
	public CompoundNBT saveToNbt() {
		CompoundNBT data = super.saveToNbt();

		if (this.consumingItem != null)
			data.putString("item", this.consumingItem.getRegistryName().toString());

		return data;
	}

	@Override
	public CompoundNBT getSyncData(boolean forClientSetup) {
		CompoundNBT data = super.getSyncData(forClientSetup);

		data.putString("item", consumingItem != null ? consumingItem.getRegistryName().toString() : "");

		return data;
	}

	@Override
	public void receiveSyncData(CompoundNBT data) {
		super.receiveSyncData(data);

		if (data.getString("item").equals("")) {
			this.consumingItem = null;
		}
		else {
			this.consumingItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(data.getString("item")));
		}

		updateDescription(new TranslationTextComponent(Util.makeDescriptionId("ability", type().getRegistryName()) + ".description"));
	}

	@Override
	public void receiveInteractionDataFromClient(String data) {
		if (data.equals(Items.AIR.getRegistryName().toString())) {
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
			ForgeHooksClient.pushGuiLayer(mc, new SelectInventoryItemScreen(mc, consumingItem, item -> AoAPackets.messageServer(new SyncAoAAbilityDataPacket(this, item.getRegistryName().toString()))));

			return false;
		}

		return super.onGuiClick(mouseX, mouseY);
	}
}
