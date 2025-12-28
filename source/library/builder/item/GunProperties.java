package net.tslat.aoa3.library.builder.item;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.item.AoADataComponents;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.item.datacomponent.GunStats;
import net.tslat.aoa3.content.item.datacomponent.ItemUseSound;
import net.tslat.aoa3.content.item.weapon.gun.AoAGun;
import net.tslat.aoa3.library.object.extension.BlockToItemHolder;

import java.util.Optional;

public interface GunProperties {
    Item.Properties toProperties();
    default Item.Properties durability(int durability) {
        return toProperties().durability(durability);
    }

    static Builder of() {
        return new Builder.Stage5();
    }

    class Builder {
        protected float damage = 0f;
        protected int ticksBetweenShots = 7;
        protected float recoilModifier = 1f;
        protected float unholsterTimeModifier = 0.85f;
        protected Optional<? extends HolderSet<Item>> ammo = Optional.of(HolderSet.direct(AoAItems.LIMONITE_BULLET));
        protected Optional<Holder<SoundEvent>> firingSound = Optional.of(AoASounds.ITEM_GUN_GENERIC_FIRE_4);
        protected float firingSoundPitch = 1f;
        protected boolean isFullAuto = true;

        private Builder() {}

        public Stage2 damage(float damage) {
            this.damage = damage;

            return (Stage2)this;
        }

        public static class Stage2 extends Builder {
            private Stage2() {}

            public Stage3 shotIntervalTicks(int ticksBetweenShots) {
                this.ticksBetweenShots = ticksBetweenShots;

                return (Stage3)this;
            }
        }

        public static class Stage3 extends Stage2 {
            private Stage3() {}

            public Stage4 recoilMod(float recoilMod) {
                this.recoilModifier = recoilMod;

                return (Stage4)this;
            }
        }

        public static class Stage4 extends Stage3 {
            private Stage4() {}

            public Stage5 unholsterTimeMod(float unholsterTimeMod) {
                this.unholsterTimeModifier = unholsterTimeMod;

                return (Stage5)this;
            }
        }

        public static class Stage5 extends Stage4 implements GunProperties {
            private Stage5() {}

            public Stage5 dynamicAmmo() {
                this.ammo = Optional.empty();

                return this;
            }

            public Stage5 ammo(Item ammo) {
                this.ammo = Optional.of(HolderSet.direct(BuiltInRegistries.ITEM::wrapAsHolder, ammo));

                return this;
            }

            public Stage5 ammo(Block ammo) {
                if (ammo.asItem() == null)
                    throw new IllegalArgumentException("Can't use block with no item as ammo!");

                this.ammo = Optional.of(HolderSet.direct(BuiltInRegistries.ITEM::wrapAsHolder, ammo.asItem()));

                return this;
            }

            public Stage5 ammo(Holder<? extends ItemLike>... ammo) {
                if (ammo.length == 0)
                    throw new IllegalArgumentException("Can't use empty array as ammo!");

                this.ammo = Optional.of(HolderSet.direct(BlockToItemHolder.of(ammo)));

                return this;
            }

            public Stage5 ammo(TagKey<Item> ammo) {
                this.ammo = BuiltInRegistries.ITEM.getTag(ammo);

                return this;
            }

            public Stage5 noFiringSound() {
                this.firingSound = Optional.empty();

                return this;
            }

            public Stage5 firingSound(SoundEvent sound) {
                return firingSound(Holder.direct(sound));
            }

            public Stage5 firingSound(Holder<SoundEvent> sound) {
                this.firingSound = Optional.of(sound);

                return this;
            }

            public Stage5 firingSoundPitch(float pitch) {
                this.firingSoundPitch = pitch;

                return this;
            }

            public Stage5 semiAuto() {
                this.isFullAuto = false;

                return this;
            }

            public Stage5 fullAuto() {
                this.isFullAuto = true;

                return this;
            }

            @Override
            public Item.Properties toProperties() {
                return new Item.Properties()
                        .component(AoADataComponents.GUN_STATS,
                                   new GunStats(this.damage, this.ticksBetweenShots, this.recoilModifier, this.unholsterTimeModifier, (Optional<HolderSet<Item>>)this.ammo, this.isFullAuto))
                        .component(AoADataComponents.ITEM_USE_SOUND,
                                   new ItemUseSound(this.firingSound, this.firingSoundPitch))
                        .attributes(AoAGun.createGunAttributeModifiers(this.unholsterTimeModifier));
            }
        }
    }
}
