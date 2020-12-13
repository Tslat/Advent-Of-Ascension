package net.tslat.aoa3.library.loot.modifiers;

import com.google.gson.JsonObject;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.IGlobalLootModifier;

public abstract class AoALootModifierSerializer<T extends IGlobalLootModifier> extends GlobalLootModifierSerializer<T> {
	@Override
	public abstract T read(ResourceLocation id, JsonObject object, ILootCondition[] lootConditions);

	public abstract void write(JsonObject json);
}
