package net.tslat.aoa3.mixin.common.patch;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Set;

@Mixin(BlockEntityType.class)
public interface BlockEntityTypeAccessor {
    @Accessor
    void setValidBlocks(Set<Block> blocks);

    @Accessor
    Set<Block> getValidBlocks();
}
