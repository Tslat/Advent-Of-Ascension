package net.tslat.aoa3.content.item.datacomponent;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.StringUtil;
import net.tslat.tme.api.object.extension.Text;

import java.util.Optional;

public record GunStats(float damage, int ticksBetweenShots, float recoilModifier, float unholsterTimeModifier, Optional<HolderSet<Item>> ammo, boolean isFullAuto) {
    public static final Codec<GunStats> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            Codec.FLOAT.optionalFieldOf("damage", 0f).forGetter(GunStats::damage),
            Codec.INT.optionalFieldOf("ticks_between_shots", 7).forGetter(GunStats::ticksBetweenShots),
            Codec.FLOAT.optionalFieldOf("recoil_modifier", 1f).forGetter(GunStats::recoilModifier),
            Codec.FLOAT.optionalFieldOf("unholster_time_modifier", 0.85f).forGetter(GunStats::unholsterTimeModifier),
            RegistryCodecs.homogeneousList(Registries.ITEM, BuiltInRegistries.ITEM.byNameCodec()).optionalFieldOf("ammo").forGetter(GunStats::ammo),
            Codec.BOOL.optionalFieldOf("full_auto", true).forGetter(GunStats::isFullAuto)
    ).apply(builder, GunStats::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, GunStats> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.FLOAT, GunStats::damage,
            ByteBufCodecs.VAR_INT, GunStats::ticksBetweenShots,
            ByteBufCodecs.FLOAT, GunStats::recoilModifier,
            ByteBufCodecs.FLOAT, GunStats::unholsterTimeModifier,
            ByteBufCodecs.holderSet(Registries.ITEM).apply(ByteBufCodecs::optional), GunStats::ammo,
            ByteBufCodecs.BOOL, GunStats::isFullAuto,
            GunStats::new);

    public static float calculateDefaultUnholsterMod(float baseDamage, int ticksBetweenShots) {
        if (baseDamage <= 0)
            return 0.8f;

        return 0.8f + 0.17f * Math.min(((20 / (float)ticksBetweenShots) * baseDamage) / 55f, 0.85f);
    }

    public Component getAmmoNameForTooltip() {
        return this.ammo.map(holderSet -> {
            if (holderSet.size() == 0)
                return LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.AMMO_INVALID, LocaleUtil.ItemDescriptionType.ITEM_AMMO_COST);

            if (holderSet instanceof HolderSet.Named<?>)
                return LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.AMMO_ITEM, LocaleUtil.ItemDescriptionType.ITEM_AMMO_COST, Text.ofLiteral(holderSet.unwrapKey().map(tag -> StringUtil.lazyPluralise(StringUtil.toTitleCase(tag.location().getPath()))).orElse(holderSet.toString()), ChatFormatting.ITALIC));

            if (holderSet instanceof HolderSet.Direct<?>) {
                if (holderSet.size() == 1) {
                    Item item = holderSet.get(0).value();

                    return LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.AMMO_ITEM, LocaleUtil.ItemDescriptionType.ITEM_AMMO_COST, item.getName(item.getDefaultInstance()));
                }

                Item item = holderSet.get((int)((System.currentTimeMillis() / 3000L) % holderSet.size())).value();

                return LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.AMMO_ITEM, LocaleUtil.ItemDescriptionType.ITEM_AMMO_COST, item.getName(item.getDefaultInstance()));
            }

            return LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.AMMO_ITEM, LocaleUtil.ItemDescriptionType.ITEM_AMMO_COST, Text.ofLiteral("???", ChatFormatting.ITALIC));
        }).orElse(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.AMMO_NONE, LocaleUtil.ItemDescriptionType.ITEM_AMMO_COST));
    }
}
