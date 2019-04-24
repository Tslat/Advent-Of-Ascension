package net.tslat.aoa3.entity.misc.pixon;

import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.misc.InfusionStone;

public class EntityRadiantPixon extends EntityPixon {
    public EntityRadiantPixon(World world) {
        super(world);
    }

    @Override
    public int getHarvestLevelReq() {
        return 72;
    }

    @Override
    public float getHarvestXp() {
        return 700f;
    }

    @Override
    public InfusionStone getInfusionStoneType() {
        return ItemRegister.infusionStoneRadiant;
    }
}
