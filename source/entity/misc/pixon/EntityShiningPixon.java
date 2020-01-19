package net.tslat.aoa3.entity.misc.pixon;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;

import javax.annotation.Nonnull;

public class EntityShiningPixon extends EntityPixon {
    public EntityShiningPixon(World world) {
        super(world);
    }

    @Override
    public int getHarvestLevelReq() {
        return 60;
    }

    @Nonnull
    @Override
    public ResourceLocation getHarvestLootTable() {
        return LootSystemRegister.pixonHarvestShining;
    }
}
