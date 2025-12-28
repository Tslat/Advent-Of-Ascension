package net.tslat.aoa3.library.builder.item;

import net.minecraft.world.item.Item;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.item.AoAItems;

import java.util.function.Function;

public interface CannonProperties extends GunProperties {
    static Builder of(Function<GunProperties.Builder, GunProperties> gunProperties) {
        return new Builder(gunProperties.apply(((GunProperties.Builder.Stage5)GunProperties.of()).ammo(AoAItems.CANNONBALL).firingSound(AoASounds.ITEM_GUN_GENERIC_FIRE_4).semiAuto()));
    }

    class Builder implements CannonProperties {
        protected final GunProperties gunProperties;

        private Builder(GunProperties gunStats) {
            this.gunProperties = gunStats;
        }

        @Override
        public Item.Properties toProperties() {
            return this.gunProperties.toProperties();
        }
    }
}
