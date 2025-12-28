package net.tslat.aoa3.library.builder.item;

import net.minecraft.world.item.Item;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.item.AoADataComponents;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.item.datacomponent.ShotgunStats;

import java.util.function.Function;

public interface ShotgunProperties extends GunProperties {
    static Builder of(Function<GunProperties.Builder, GunProperties> gunProperties) {
        return new Builder.Stage4(gunProperties.apply(((GunProperties.Builder.Stage5)GunProperties.of()).ammo(AoAItems.SPREADSHOT).firingSound(AoASounds.ITEM_GUN_SHOTGUN_MEDIUM_FIRE_LONG).semiAuto()));
    }

    class Builder {
        protected final GunProperties gunProperties;

        protected int pellets = 3;
        protected float pelletSpread = 0.3f;
        protected float knockbackModifier = 1f;

        private Builder(GunProperties gunProperties) {
            this.gunProperties = gunProperties;
        }

        public Stage2 pellets(int pellets) {
            this.pellets = pellets;

            return (Stage2)this;
        }

        public static class Stage2 extends Builder {
            private Stage2(GunProperties gunProperties) {
                super(gunProperties);
            }

            public Stage3 pelletSpread(float pelletSpread) {
                this.pelletSpread = pelletSpread;

                return (Stage3)this;
            }
        }

        public static class Stage3 extends Stage2 {
            private Stage3(GunProperties gunProperties) {
                super(gunProperties);
            }

            public Stage4 knockbackModifier(float knockbackModifier) {
                this.knockbackModifier = knockbackModifier;

                return (Stage4)this;
            }
        }

        public static class Stage4 extends Stage3 implements ShotgunProperties {
            private Stage4(GunProperties gunProperties) {
                super(gunProperties);
            }

            @Override
            public Item.Properties toProperties() {
                return this.gunProperties.toProperties().component(AoADataComponents.SHOTGUN_STATS,
                                                                   new ShotgunStats(this.pellets, this.pelletSpread, this.knockbackModifier));
            }
        }
    }
}
