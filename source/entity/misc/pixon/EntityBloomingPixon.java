package net.tslat.aoa3.entity.misc.pixon;

import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.misc.InfusionStone;

public class EntityBloomingPixon extends EntityPixon {
    public EntityBloomingPixon(World world) {
        super(world);
    }

    @Override
    public int getHarvestLevelReq() {
        return 81;
    }

    @Override
    public float getHarvestXp() {
        return 900f;
    }

    @Override
    public InfusionStone getInfusionStoneType() {
        return ItemRegister.infusionStoneBlooming;
    }
}
