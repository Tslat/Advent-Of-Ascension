package net.tslat.aoa3.library.builder.item;

import it.unimi.dsi.fastutil.objects.Reference2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.item.AoADataComponents;
import net.tslat.aoa3.content.item.datacomponent.ItemUseSound;
import net.tslat.aoa3.content.item.datacomponent.StaffStats;
import net.tslat.aoa3.content.item.weapon.staff.AoAStaff;

import java.util.Optional;
import java.util.function.Consumer;

public interface StaffProperties {
    Item.Properties toProperties();
    default Item.Properties durability(int durability) {
        return toProperties().durability(durability);
    }

    static Builder of() {
        return new Builder.Stage2();
    }

    class Builder {
        protected Reference2IntMap<Item> runeCost;
        protected Holder<SoundEvent> castingSound = AoASounds.ITEM_STAFF_CAST;
        protected float castingSoundPitch = 1;
        protected float damage = 0;

        private Builder() {}

        public Stage2 runeCost(ItemLike rune, int cost) {
            this.runeCost = mapForRunes(1, map -> map.put(rune.asItem(), cost));

            return (Stage2)this;
        }

        public Stage2 runeCost(ItemLike rune, int cost, ItemLike rune2, int cost2) {
            this.runeCost = mapForRunes(2, map -> {
                map.put(rune.asItem(), cost);
                map.put(rune2.asItem(), cost2);
            });

            return (Stage2)this;
        }

        public Stage2 runeCost(ItemLike rune, int cost, ItemLike rune2, int cost2, ItemLike rune3, int cost3) {
            this.runeCost = mapForRunes(2, map -> {
                map.put(rune.asItem(), cost);
                map.put(rune2.asItem(), cost2);
                map.put(rune3.asItem(), cost3);
            });

            return (Stage2)this;
        }

        public Stage2 runeCost(ItemLike rune, int cost, ItemLike rune2, int cost2, ItemLike rune3, int cost3, ItemLike rune4, int cost4) {
            this.runeCost = mapForRunes(2, map -> {
                map.put(rune.asItem(), cost);
                map.put(rune2.asItem(), cost2);
                map.put(rune3.asItem(), cost3);
                map.put(rune4.asItem(), cost4);
            });

            return (Stage2)this;
        }

        public Stage2 runeCost(ItemLike rune, int cost, ItemLike rune2, int cost2, ItemLike rune3, int cost3, ItemLike rune4, int cost4, ItemLike rune5, int cost5) {
            this.runeCost = mapForRunes(2, map -> {
                map.put(rune.asItem(), cost);
                map.put(rune2.asItem(), cost2);
                map.put(rune3.asItem(), cost3);
                map.put(rune4.asItem(), cost4);
                map.put(rune5.asItem(), cost5);
            });

            return (Stage2)this;
        }

        public Stage2 runeCost(ItemLike rune, int cost, ItemLike rune2, int cost2, ItemLike rune3, int cost3, ItemLike rune4, int cost4, ItemLike rune5, int cost5, ItemLike rune6, int cost6) {
            this.runeCost = mapForRunes(2, map -> {
                map.put(rune.asItem(), cost);
                map.put(rune2.asItem(), cost2);
                map.put(rune3.asItem(), cost3);
                map.put(rune4.asItem(), cost4);
                map.put(rune5.asItem(), cost5);
                map.put(rune6.asItem(), cost6);
            });

            return (Stage2)this;
        }

        private Reference2IntMap<Item> mapForRunes(int size, Consumer<Reference2IntMap<Item>> consumer) {
            return Util.make(new Reference2IntArrayMap<>(size), consumer);
        }

        public static class Stage2 extends Builder implements StaffProperties {
            private Stage2() {}

            public Stage2 castingSound(SoundEvent sound) {
                return castingSound(Holder.direct(sound));
            }

            public Stage2 castingSound(Holder<SoundEvent> sound) {
                this.castingSound = sound;

                return this;
            }

            public Stage2 castingSoundPitch(float pitch) {
                this.castingSoundPitch = pitch;

                return this;
            }

            public Stage2 damage(float damage) {
                this.damage = damage;

                return this;
            }

            @Override
            public Item.Properties toProperties() {
                return new Item.Properties()
                        .component(AoADataComponents.STAFF_STATS, new StaffStats(this.runeCost, this.damage))
                        .component(AoADataComponents.STORED_SPELL_CASTS, AoAStaff.StoredCasts.DISABLED)
                        .component(AoADataComponents.ITEM_USE_SOUND, new ItemUseSound(Optional.of(this.castingSound), this.castingSoundPitch));
            }
        }
    }
}
