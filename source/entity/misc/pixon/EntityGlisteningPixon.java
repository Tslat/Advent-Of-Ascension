package net.tslat.aoa3.entity.misc.pixon;

import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.misc.InfusionStone;

public class EntityGlisteningPixon extends EntityPixon {
    public EntityGlisteningPixon(World world) {
        super(world);
    }

    @Override
    public int getHarvestLevelReq() {
        return 1;
    }

    @Override
    public float getHarvestXp() {
        return 10f;
    }

    @Override
    public boolean getCanSpawnHere() {
        return world.provider.getDimension() == 0 && super.getCanSpawnHere();
    }

    @Override
    public InfusionStone getInfusionStoneType() {
        return ItemRegister.infusionStoneGlistening;
    }
}
