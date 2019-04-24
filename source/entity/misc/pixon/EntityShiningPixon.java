package net.tslat.aoa3.entity.misc.pixon;

import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.misc.InfusionStone;

public class EntityShiningPixon extends EntityPixon {
    public EntityShiningPixon(World world) {
        super(world);
    }

    @Override
    public int getHarvestLevelReq() {
        return 60;
    }

    @Override
    public float getHarvestXp() {
        return 350f;
    }

    @Override
    public InfusionStone getInfusionStoneType() {
        return ItemRegister.infusionStoneShining;
    }
}
