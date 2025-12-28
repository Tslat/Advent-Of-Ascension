package net.tslat.aoa3.library.builder.item;

import net.minecraft.world.item.Item;
import net.tslat.aoa3.common.registration.item.AoADataComponents;
import net.tslat.aoa3.content.item.datacomponent.VulcaneStats;

public interface VulcaneProperties {
    Item.Properties toProperties();
    default Item.Properties durability(int durability) {
        return toProperties().durability(durability);
    }

    static Builder of() {
        return new Builder.Stage2();
    }

    class Builder {
        protected float damage = 10;
        protected float rageCost = 50;

        private Builder() {}

        public Stage2 damage(float damage) {
            this.damage = damage;

            return (Stage2)this;
        }

        public static class Stage2 extends Builder implements VulcaneProperties {
            private Stage2() {}

            public Stage2 rageCost(float rageCost) {
                this.rageCost = rageCost;

                return this;
            }

            @Override
            public Item.Properties toProperties() {
                return new Item.Properties().component(AoADataComponents.VULCANE_STATS, new VulcaneStats(this.damage, this.rageCost));
            }
        }
    }
}
