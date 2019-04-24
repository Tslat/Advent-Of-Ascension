package net.tslat.aoa3.entity.misc.pixon;

import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.misc.InfusionStone;

public class EntityGlaringPixon extends EntityPixon {
    public EntityGlaringPixon(World world) {
        super(world);
    }

    @Override
    public int getHarvestLevelReq() {
        return 25;
    }

    @Override
    public float getHarvestXp() {
        return 70f;
    }

    @Override
    public InfusionStone getInfusionStoneType() {
        return ItemRegister.infusionStoneGlaring;
    }
}
