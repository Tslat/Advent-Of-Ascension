package net.tslat.aoa3.library.builder.item;

import net.minecraft.world.item.Item;
import net.tslat.aoa3.common.registration.item.AoADataComponents;
import net.tslat.aoa3.content.item.datacomponent.ThrownWeaponStats;

public interface ThrownItemProperties {
    Item.Properties toProperties();
    default Item.Properties stacksTo(int stackSize) {
        return toProperties().stacksTo(stackSize);
    }

    static Builder of() {
        return new Builder.Stage2();
    }

    class Builder {
        protected int throwTickInterval = 6;
        protected float impactDamage = 0;

        private Builder() {}

        public Stage2 throwTickInterval(int ticks) {
            this.throwTickInterval = ticks;

            return (Stage2)this;
        }

        public static class Stage2 extends Builder implements ThrownItemProperties {
            private Stage2() {}

            public Stage2 impactDamage(float impactDamage) {
                this.impactDamage = impactDamage;

                return this;
            }

            @Override
            public Item.Properties toProperties() {
                return new Item.Properties().component(AoADataComponents.THROWN_WEAPON_STATS, new ThrownWeaponStats(this.impactDamage, this.throwTickInterval));
            }
        }
    }
}
