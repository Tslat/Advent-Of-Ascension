package net.tslat.aoa3.entity.misc.pixon;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;

import javax.annotation.Nonnull;

public class EntityGleamingPixon extends EntityPixon {
    public EntityGleamingPixon(World world) {
        super(world);
    }

    @Override
    public int getHarvestLevelReq() {
        return 10;
    }

    @Nonnull
    @Override
    public ResourceLocation getHarvestLootTable() {
        return LootSystemRegister.pixonHarvestGleaming;
    }
}
