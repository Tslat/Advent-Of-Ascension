package net.tslat.aoa3.entity.misc.pixon;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;

import javax.annotation.Nonnull;

public class EntityGlisteningPixon extends EntityPixon {
    public EntityGlisteningPixon(World world) {
        super(world);
    }

    @Override
    public int getHarvestLevelReq() {
        return 1;
    }

    @Nonnull
    @Override
    public ResourceLocation getHarvestLootTable() {
        return LootSystemRegister.pixonHarvestGlistening;
    }

    @Override
    public boolean getCanSpawnHere() {
        return world.provider.getDimension() == 0 && super.getCanSpawnHere();
    }
}
