package net.tslat.aoa3.entity.misc.pixon;

import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.misc.InfusionStone;

public class EntityAmbientPixon extends EntityPixon {
    public EntityAmbientPixon(World world) {
        super(world);
    }

    @Override
    public int getHarvestLevelReq() {
        return 20;
    }

    @Override
    public float getHarvestXp() {
        return 40f;
    }

    @Override
    public InfusionStone getInfusionStoneType() {
        return ItemRegister.infusionStoneAmbient;
    }
}
