package net.tslat.aoa3.player.ability;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.common.container.InfusionTableContainer;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.event.custom.events.ItemCraftingEvent;
import net.tslat.aoa3.player.skill.AoASkill;

import java.util.Map;

public class AutoEnchantCrafting extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.ITEM_CRAFTING};

	private final EnchantmentInstance[] enchantments;

	public AutoEnchantCrafting(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.AUTO_ENCHANT_CRAFTING.get(), skill, data);

		JsonObject enchantMap = data.getAsJsonObject("enchantments");
		enchantments = new EnchantmentInstance[enchantMap.size()];
		int i = 0;

		for (Map.Entry<String, JsonElement> entry : enchantMap.entrySet()) {
			enchantments[i] = new EnchantmentInstance(ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation(entry.getKey())), entry.getValue().getAsInt());
			i++;
		}

		if (enchantments.length == 0)
			throw new IllegalArgumentException("No valid enchantments found for AutoEnchantCrafting ability, ID: '" + getUniqueIdentifier() + "'");
	}

	public AutoEnchantCrafting(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.AUTO_ENCHANT_CRAFTING.get(), skill, data);

		CompoundTag enchantMap = data.getCompound("enchantments");
		enchantments = new EnchantmentInstance[enchantMap.size()];
		int i = 0;

		for (String enchantId : enchantMap.getAllKeys()) {
			enchantments[i] = new EnchantmentInstance(ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation(enchantId)), enchantMap.getInt(enchantId));
			i++;
		}
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	protected void updateDescription(MutableComponent defaultDescription) {
		boolean comma = false;

		for (EnchantmentInstance enchants : enchantments) {
			if (comma)
				defaultDescription.append(", ");

			defaultDescription.append(enchants.enchantment.getFullname(enchants.level));

			comma = true;
		}

		super.updateDescription(defaultDescription);
	}

	@Override
	public void handleItemCrafting(ItemCraftingEvent ev) {
		if (ev.getCraftMatrix() instanceof InfusionTableContainer.InfusionInventory)
			return;

		ItemStack output = ev.getOutputStack();

		for (EnchantmentInstance data : enchantments) {
			if (!data.enchantment.canApplyAtEnchantingTable(output))
				return;
		}

		for (EnchantmentInstance data : enchantments) {
			output.enchant(data.enchantment, data.level);
		}
	}

	@Override
	public CompoundTag getSyncData(boolean forClientSetup) {
		CompoundTag data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			CompoundTag enchantMap = new CompoundTag();

			for (EnchantmentInstance enchantData : enchantments) {
				enchantMap.putInt(ForgeRegistries.ENCHANTMENTS.getKey(enchantData.enchantment).toString(), enchantData.level);
			}

			data.put("enchantments", enchantMap);
		}

		return data;
	}
}
