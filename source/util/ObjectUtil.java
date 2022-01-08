package net.tslat.aoa3.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.mojang.serialization.JsonOps;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTDynamicOps;

import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.util.Collection;
import java.util.function.Predicate;

public final class ObjectUtil {
	public static String bufferedReaderToString(BufferedReader reader) {
		final StringBuilder content = new StringBuilder();

		reader.lines().forEach(line -> {
			content.append(line);
			content.append("\n");
		});

		return content.toString();
	}

	@Nullable
	public static <T> T getFromCollection(Collection<T> collection, Predicate<T> predicate) {
		for (T t : collection) {
			if (predicate.test(t))
				return t;
		}

		return null;
	}

	public static JsonObject stackToJson(ItemStack stack) {
		JsonObject json = new JsonObject();

		json.add("item", new JsonPrimitive(stack.getItem().getRegistryName().toString()));

		if (stack.getCount() == 1 && !stack.hasTag())
			return json;

		json.addProperty("count", stack.getCount());

		if (stack.hasTag())
			json.add("nbt", NBTDynamicOps.INSTANCE.convertTo(JsonOps.INSTANCE, stack.getTag()).getAsJsonObject());

		return json;
	}
}
