package net.tslat.aoa3.library.builder.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.item.AoADataComponents;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.item.datacomponent.SniperScope;
import net.tslat.aoa3.content.item.weapon.sniper.AoASniper;

import java.util.function.Function;

public interface SniperProperties extends GunProperties {
    static Builder of(Function<GunProperties.Builder, GunProperties> gunProperties) {
        return new Builder(gunProperties.apply(((GunProperties.Builder.Stage5)GunProperties.of()).ammo(AoAItems.METAL_SLUG).firingSound(AoASounds.ITEM_GUN_SNIPER_MEDIUM_FIRE_LONG).semiAuto()));
    }

    class Builder implements SniperProperties {
        protected final GunProperties gunProperties;

        protected ResourceLocation scope = AoASniper.BASIC;
        protected float zoomStrength = 1f;

        private Builder(GunProperties gunStats) {
            this.gunProperties = gunStats;
        }

        @Override
        public Item.Properties toProperties() {
            return this.gunProperties.toProperties().component(AoADataComponents.SNIPER_SCOPE, new SniperScope(this.scope, this.zoomStrength));
        }

        public SniperProperties.Builder scope(ResourceLocation scope) {
            this.scope = scope;

            return this;
        }

        public SniperProperties.Builder zoomStrength(float zoomStrength) {
            this.zoomStrength = zoomStrength;

            return this;
        }
    }
}
