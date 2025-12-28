package net.tslat.aoa3.content.skill.artifice;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.tslat.tme.api.object.EasyRandom;
import net.tslat.tme.api.util.RandomUtil;
import net.tslat.tme.api.util.StreamCodecUtil;

import java.util.List;
import java.util.UUID;

public record ArtificeCreation(UUID id, int levelReq, FloatProvider xp, IntProvider difficulty, ItemStack creation, List<Ingredient> ingredients) {
    public static final Codec<ArtificeCreation> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            UUIDUtil.CODEC.fieldOf("id").forGetter(ArtificeCreation::id),
            Codec.INT.fieldOf("level_req").forGetter(ArtificeCreation::levelReq),
            FloatProvider.codec(0, Float.MAX_VALUE).fieldOf("xp").forGetter(ArtificeCreation::xp),
            IntProvider.codec(1, 10).fieldOf("difficulty").forGetter(ArtificeCreation::difficulty),
            ItemStack.CODEC.fieldOf("creation").forGetter(ArtificeCreation::creation),
            Ingredient.CODEC_NONEMPTY.listOf().fieldOf("ingredients").forGetter(ArtificeCreation::ingredients)
    ).apply(instance, ArtificeCreation::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, ArtificeCreation> STREAM_CODEC = StreamCodec.composite(
            UUIDUtil.STREAM_CODEC, ArtificeCreation::id,
            ByteBufCodecs.VAR_INT, ArtificeCreation::levelReq,
            StreamCodecUtil.FLOAT_PROVIDER, ArtificeCreation::xp,
            StreamCodecUtil.INT_PROVIDER, ArtificeCreation::difficulty,
            ItemStack.STREAM_CODEC, ArtificeCreation::creation,
            Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()), ArtificeCreation::ingredients,
            ArtificeCreation::new);

    public int getChainLength() {
        EasyRandom random = EasyRandom.createSingleThread();

        random.setSeed(id.hashCode());

        return this.difficulty.sample(RandomUtil.RANDOM);
    }
}
