package net.tslat.aoa3.entity.misc.pixon;

import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.misc.InfusionStone;

public class EntityGlowingPixon extends EntityPixon {
    public EntityGlowingPixon(World world) {
        super(world);
    }

    @Override
    public int getHarvestLevelReq() {
        return 40;
    }

    @Override
    public float getHarvestXp() {
        return 200f;
    }

    @Override
    public InfusionStone getInfusionStoneType() {
        return ItemRegister.infusionStoneGlowing;
    }
}
