package net.tslat.aoa3.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.JsonOps;
import net.minecraft.nbt.NbtOps;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.smartbrainlib.util.RandomUtil;

import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
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

		json.add("item", new JsonPrimitive(ForgeRegistries.ITEMS.getKey(stack.getItem()).toString()));

		if (stack.getCount() == 1 && !stack.hasTag())
			return json;

		json.addProperty("count", stack.getCount());

		if (stack.hasTag())
			json.add("nbt", NbtOps.INSTANCE.convertTo(JsonOps.INSTANCE, stack.getTag()).getAsJsonObject());

		return json;
	}

	public static <T> JsonObject codecToJson(Codec<T> codec, T object, Function<String, String> errMsg) {
		return codecToJson(codec, object, JsonOps.INSTANCE, errMsg);
	}

	public static <T> JsonObject codecToJson(Codec<T> codec, T object, DynamicOps<JsonElement> ops, Function<String, String> errMsg) {
		DataResult<JsonElement> result = codec.encodeStart(ops, object);
		Optional<JsonElement> output = result.resultOrPartial(error -> {
			throw new IllegalArgumentException(errMsg.apply(error));
		});

		return output.get().getAsJsonObject();
	}

	public static <T> void fastShuffleList(List<T> list) {
		int length = list.size();
		Random random = RandomUtil.getRandomInstance();

		for (int i = length - 1; i > 0; i--) {
			int index = random.nextInt(i);
			T swapElement = list.get(i);

			list.set(i, list.get(index));
			list.set(index, swapElement);
		}
	}
}
