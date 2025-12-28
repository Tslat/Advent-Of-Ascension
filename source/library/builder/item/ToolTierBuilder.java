package net.tslat.aoa3.library.builder.item;

import com.google.common.base.Suppliers;
import net.minecraft.core.Holder;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.SimpleTier;

import java.util.function.Supplier;

public sealed class ToolTierBuilder permits ToolTierBuilder.HarvestSpeed {
    int durability;
    float baseSpeed;
    float damageBonus;
    int enchantmentValue;
    Supplier<Ingredient> repairIngredient;
    TagKey<Block> doesntHarvestBlocks;

    private ToolTierBuilder() {}

    public static HarvestSpeed tool() {
        return new BlockHarvestBlacklist();
    }

    public static Durability weapon(float speed) {
        return tool().baseSpeed(speed);
    }

    public static sealed class HarvestSpeed extends ToolTierBuilder permits Durability {
        public Durability baseSpeed(float speed) {
            this.baseSpeed = speed;

            return (Durability)this;
        }
    }

    public static sealed class Durability extends HarvestSpeed permits DamageBonus {
        public DamageBonus durability(int durability) {
            this.durability = durability;

            return (DamageBonus)this;
        }
    }

    public static sealed class DamageBonus extends Durability permits EnchantmentValue {
        public EnchantmentValue damage(float damage) {
            this.damageBonus = damage - 1;

            return (EnchantmentValue)this;
        }
    }

    public static sealed class EnchantmentValue extends DamageBonus permits RepairIngredient {
        public RepairIngredient enchantValue(int enchantValue) {
            this.enchantmentValue = enchantValue;

            return (RepairIngredient)this;
        }
    }

    public static sealed class RepairIngredient extends EnchantmentValue permits BlockHarvestBlacklist {
        public BlockHarvestBlacklist repairsWith(Holder<Item> item) {
            return repairsWith(() -> Ingredient.of(item.value()));
        }

        public BlockHarvestBlacklist repairsWith(Item item) {
            return repairsWith(() -> Ingredient.of(item));
        }

        public BlockHarvestBlacklist repairsWith(TagKey<Item> tag) {
            return repairsWith(() -> Ingredient.of(tag));
        }

        public BlockHarvestBlacklist noRepair() {
            return repairsWith(() -> Ingredient.EMPTY);
        }

        public BlockHarvestBlacklist repairsWith(Supplier<Ingredient> ingredient) {
            this.repairIngredient = Suppliers.memoize(ingredient::get);

            return (BlockHarvestBlacklist)this;
        }
    }

    public static final class BlockHarvestBlacklist extends RepairIngredient {
        public Tier isNonHarvesting() {
            return doesntHarvestBlocks(BlockTags.INCORRECT_FOR_NETHERITE_TOOL);
        }

        public Tier doesntHarvestBlocks(TagKey<Block> tag) {
            this.doesntHarvestBlocks = tag;

            return build();
        }
    }

    Tier build() {
        return new SimpleTier(this.doesntHarvestBlocks, this.durability, this.baseSpeed, this.damageBonus, this.enchantmentValue, this.repairIngredient);
    }

    public static BlockHarvestBlacklist basedOn(Tier tier) {
        final BlockHarvestBlacklist newTier = new BlockHarvestBlacklist();

        newTier.doesntHarvestBlocks = tier.getIncorrectBlocksForDrops();
        newTier.durability = tier.getUses();
        newTier.baseSpeed = tier.getSpeed();
        newTier.damageBonus = tier.getAttackDamageBonus();
        newTier.enchantmentValue = tier.getEnchantmentValue();
        newTier.repairIngredient = Suppliers.memoize(tier::getRepairIngredient);

        return newTier;
    }
}