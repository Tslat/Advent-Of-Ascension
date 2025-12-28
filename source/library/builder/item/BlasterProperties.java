package net.tslat.aoa3.library.builder.item;

import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.tslat.aoa3.common.registration.item.AoADataComponents;
import net.tslat.aoa3.content.item.datacomponent.BlasterStats;
import net.tslat.aoa3.content.item.datacomponent.ItemUseSound;
import net.tslat.aoa3.content.item.weapon.blaster.AoABlaster;

import java.util.Optional;

public interface BlasterProperties {
    Item.Properties toProperties();
    default Item.Properties durability(int durability) {
        return toProperties().durability(durability);
    }

    static Builder of() {
        return new Builder.Stage5();
    }

    class Builder {
        protected float damage;
        protected int ticksBetweenShots;
        protected float spiritCost;
        protected int chargeUpTicks;
        protected Optional<Holder<SoundEvent>> firingSound;
        protected float firingSoundPitch = 1;
        protected float beamDistance = 0f;

        private Builder() {}

        public Stage2 damage(float damage) {
            this.damage = damage;

            return (Stage2)this;
        }

        public static class Stage2 extends Builder {
            private Stage2() {}

            public Stage3 ticksBetweenShots(int ticks) {
                this.ticksBetweenShots = ticks;

                return (Stage3)this;
            }
        }

        public static class Stage3 extends Stage2 {
            private Stage3() {}

            public Stage4 spiritCost(float spiritCost) {
                this.spiritCost = spiritCost;

                return (Stage4)this;
            }
        }

        public static class Stage4 extends Stage3 {
            private Stage4() {}

            public Stage5 chargeUpTicks(int ticks) {
                this.chargeUpTicks = ticks;

                return (Stage5)this;
            }
        }

        public static class Stage5 extends Stage4 implements BlasterProperties {
            private Stage5() {}

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

            public Stage5 beamDistance(float distance) {
                this.beamDistance = distance;

                return this;
            }

            @Override
            public Item.Properties toProperties() {
                return new Item.Properties()
                        .component(AoADataComponents.BLASTER_STATS, new BlasterStats(this.damage, this.ticksBetweenShots, this.spiritCost, this.chargeUpTicks, this.beamDistance))
                        .component(AoADataComponents.ITEM_USE_SOUND, new ItemUseSound(this.firingSound, this.firingSoundPitch))
                        .attributes(AoABlaster.createBlasterAttributeModifiers());
            }
        }
    }
}
