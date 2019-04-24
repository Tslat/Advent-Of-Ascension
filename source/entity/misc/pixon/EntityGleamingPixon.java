package net.tslat.aoa3.entity.misc.pixon;

import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.misc.InfusionStone;

public class EntityGleamingPixon extends EntityPixon {
    public EntityGleamingPixon(World world) {
        super(world);
    }

    @Override
    public int getHarvestLevelReq() {
        return 10;
    }

    @Override
    public float getHarvestXp() {
        return 30f;
    }

    @Override
    public InfusionStone getInfusionStoneType() {
        return ItemRegister.infusionStoneGleaming;
    }
}
