package net.tslat.aoa3.library.builder.item;

import net.minecraft.world.item.Item;
import net.tslat.aoa3.common.registration.item.AoADataComponents;
import net.tslat.aoa3.content.item.datacomponent.CrossbowStats;

public interface CrossbowProperties {
    Item.Properties toProperties();
    default Item.Properties durability(int durability) {
        return toProperties().durability(durability);
    }

    static Builder of() {
        return new Builder.Stage2();
    }

    class Builder {
        protected float damage = 12f;
        protected float chargeSpeedModifier = 1f;

        private Builder() {}

        public Stage2 damage(float damage) {
            this.damage = damage;

            return (Stage2)this;
        }

        public static class Stage2 extends Builder implements CrossbowProperties {
            private Stage2() {}

            public Stage2 chargeSpeedMod(float modifier) {
                this.chargeSpeedModifier = modifier;

                return this;
            }

            @Override
            public Item.Properties toProperties() {
                return new Item.Properties().component(AoADataComponents.CROSSBOW_STATS, new CrossbowStats(this.damage, this.chargeSpeedModifier));
            }
        }
    }
}
