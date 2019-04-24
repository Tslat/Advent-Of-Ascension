package net.tslat.aoa3.entity.mobs.deeplands;

import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nonnull;
import java.util.TreeSet;

public class EntityDweller extends AoAMeleeMob implements SpecialPropertyEntity {
    public static final float entityWidth = 0.625f;
    private int cloneCooldown = 160;

    public EntityDweller(World world) {
        super(world, entityWidth, 2.4f);

        mobProperties.add(Enums.MobProperties.EXPLOSION_IMMUNE);
    }

    @Override
    public float getEyeHeight() {
        return 2.125f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.8;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 50;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 5;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Override
    public boolean getCanSpawnHere() {
        return posY < 120 && super.getCanSpawnHere();
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        if (rand.nextInt(100 - lootingMod) == 0)
            dropItem(ItemRegister.upgradeKitPredator, 1);

        if (rand.nextInt(40 - lootingMod) == 0)
            dropItem(ItemRegister.unchargedStone, 1);

        if (rand.nextInt(7) == 0)
            dropItem(Item.getItemFromBlock(BlockRegister.bannerDeep), 1);

        if (rand.nextInt(40 - lootingMod) == 0)
            dropItem(ItemRegister.realmstoneIromine, 1);
    }

    @Override
    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
    }

    @Override
    protected boolean isSpecialImmuneTo(DamageSource source) {
        return source.isExplosion();
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        cloneCooldown--;

        if (cloneCooldown == 0) {
            cloneCooldown = 160;

            if (!world.isRemote) {
                EntityDwellerClone clone = new EntityDwellerClone(this);

                world.spawnEntity(clone);
            }

            addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 60, 2, true, true));
        }
    }

    @Nonnull
    @Override
    public TreeSet<Enums.MobProperties> getMobProperties() {
        return mobProperties;
    }
}
