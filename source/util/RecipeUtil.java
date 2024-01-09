package net.tslat.aoa3.util;

import com.mojang.datafixers.Products;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Recipe;

import java.util.function.Function;

public final class RecipeUtil {
    public record RecipeBookDetails(String group, CraftingBookCategory category, boolean showUnlockNotification) {
        public static <T extends Recipe<?>> Products.P3<RecordCodecBuilder.Mu<T>, String, CraftingBookCategory, Boolean> codec(RecordCodecBuilder.Instance<T> builder, Function<T, RecipeBookDetails> getter) {
            return builder.group(
                    ExtraCodecs.strictOptionalField(Codec.STRING, "group", "").forGetter(instance -> getter.apply(instance).group),
                    ExtraCodecs.strictOptionalField(CraftingBookCategory.CODEC, "category", CraftingBookCategory.MISC).forGetter(instance -> getter.apply(instance).category),
                    ExtraCodecs.strictOptionalField(Codec.BOOL, "show_notification", true).forGetter(instance -> getter.apply(instance).showUnlockNotification));
        }

        public static RecipeBookDetails fromNetwork(FriendlyByteBuf buffer) {
            return new RecipeBookDetails(buffer.readUtf(), buffer.readEnum(CraftingBookCategory.class), buffer.readBoolean());
        }

        public void toNetwork(FriendlyByteBuf buffer) {
            buffer.writeUtf(group());
            buffer.writeEnum(category());
            buffer.writeBoolean(showUnlockNotification());
        }
    }


}
