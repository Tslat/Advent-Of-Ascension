package net.tslat.aoa3.util;

import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
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
		CompoundNBT nbt = stack.save(new CompoundNBT());

		return NBTDynamicOps.INSTANCE.convertTo(JsonOps.INSTANCE, nbt).getAsJsonObject();
	}
}
