package net.tslat.aoa3.player.ability;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.event.custom.events.ItemCraftingEvent;
import net.tslat.aoa3.player.skill.AoASkill;

import java.util.Map;

public class AutoEnchantCrafting extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.ITEM_CRAFTING};

	private final EnchantmentData[] enchantments;

	public AutoEnchantCrafting(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.AUTO_ENCHANT_CRAFTING.get(), skill, data);

		JsonObject enchantMap = data.getAsJsonObject("enchantments");
		enchantments = new EnchantmentData[enchantMap.size()];
		int i = 0;

		for (Map.Entry<String, JsonElement> entry : enchantMap.entrySet()) {
			enchantments[i] = new EnchantmentData(ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation(entry.getKey())), entry.getValue().getAsInt());
			i++;
		}

		if (enchantments.length == 0)
			throw new IllegalArgumentException("No valid enchantments found for AutoEnchantCrafting ability, ID: '" + getUniqueIdentifier() + "'");
	}

	public AutoEnchantCrafting(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.AUTO_ENCHANT_CRAFTING.get(), skill, data);

		CompoundNBT enchantMap = data.getCompound("enchantments");
		enchantments = new EnchantmentData[enchantMap.size()];
		int i = 0;

		for (String enchantId : enchantMap.getAllKeys()) {
			enchantments[i] = new EnchantmentData(ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation(enchantId)), enchantMap.getInt(enchantId));
			i++;
		}
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	protected void updateDescription(TranslationTextComponent defaultDescription) {
		boolean comma = false;

		for (EnchantmentData enchants : enchantments) {
			if (comma)
				defaultDescription.append(", ");

			defaultDescription.append(enchants.enchantment.getFullname(enchants.level));

			comma = true;
		}

		super.updateDescription(defaultDescription);
	}

	@Override
	public void handleItemCrafting(ItemCraftingEvent ev) {
		ItemStack output = ev.getOutputStack();

		for (EnchantmentData data : enchantments) {
			if (!data.enchantment.canApplyAtEnchantingTable(output))
				return;
		}

		for (EnchantmentData data : enchantments) {
			output.enchant(data.enchantment, data.level);
		}
	}

	@Override
	public CompoundNBT getSyncData(boolean forClientSetup) {
		CompoundNBT data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			CompoundNBT enchantMap = new CompoundNBT();

			for (EnchantmentData enchantData : enchantments) {
				enchantMap.putInt(enchantData.enchantment.getRegistryName().toString(), enchantData.level);
			}

			data.put("enchantments", enchantMap);
		}

		return data;
	}
}
