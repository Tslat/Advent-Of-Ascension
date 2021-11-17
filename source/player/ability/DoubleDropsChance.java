package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.text.TranslationTextComponent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;

public class DoubleDropsChance extends AoAAbility.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.LOOT_MODIFICATION};

	private final float baseChance;
	private final float perLevelMod;

	public DoubleDropsChance(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.DOUBLE_DROPS_CHANCE.get(), skill, data);

		this.baseChance = JSONUtils.getAsFloat(data, "base_chance", 0);
		this.perLevelMod = JSONUtils.getAsFloat(data, "per_level_mod", 0);
	}

	public DoubleDropsChance(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.DOUBLE_DROPS_CHANCE.get(), skill, data);

		this.baseChance = data.getFloat("base_chance");
		this.perLevelMod = data.getFloat("per_level_mod");
	}

	@Override
	public TranslationTextComponent getDescription() {
		String suffix = baseChance > 0 ? perLevelMod > 0 ? ".combined" : ".fixed" : ".scaled";

		return new TranslationTextComponent(super.getDescription().getKey() + suffix, NumberUtil.roundToNthDecimalPlace(baseChance * 100, 2), NumberUtil.roundToNthDecimalPlace(perLevelMod * 100, 2));
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleLootModification(List<ItemStack> loot, LootContext context) {
		if (!context.hasParam(LootParameters.THIS_ENTITY))
			return;

		Entity killedEntity = context.getParamOrNull(LootParameters.THIS_ENTITY);

		if (killedEntity instanceof PlayerEntity)
			return;

		if (RandomUtil.percentChance(baseChance + skill.getLevel(false) * perLevelMod)) {
			List<ItemStack> extras = null;

			for (ItemStack stack : loot) {
				if (stack.isStackable()) {
					if (stack.getCount() <= stack.getMaxStackSize() / 2) {
						stack.setCount(stack.getCount() * 2);
					}
					else {
						if (extras == null)
							extras = new ArrayList<ItemStack>();

						ItemStack newStack = stack.copy();

						newStack.setCount(stack.getCount() * 2 - stack.getMaxStackSize());
						extras.add(newStack);
					}
				}
				else {
					if (extras == null)
						extras = new ArrayList<ItemStack>();

					extras.add(stack.copy());
				}
			}
		}
	}

	@Override
	public CompoundNBT getSyncData(boolean forClientSetup) {
		CompoundNBT data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			data.putFloat("base_chance", this.baseChance);
			data.putFloat("per_level_mod", this.perLevelMod);
		}

		return data;
	}
}
