package net.tslat.aoa3.entity.mobs.creeponia;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityCaveCreepoid extends EntityCreeper implements SpecialPropertyEntity {
    protected final TreeSet<Enums.MobProperties> mobProperties = new TreeSet<Enums.MobProperties>();
    public static final float entityWidth = 0.875f;

    public EntityCaveCreepoid(World world) {
        super(world);

        setSize(entityWidth, 1.6875f);

        this.mobProperties.add(Enums.MobProperties.EXPLOSION_IMMUNE);
    }

    @Override
    public float getEyeHeight() {
        return 1.40625f;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50);
        getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.95);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobCreepoidLiving;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobCreepoidDeath;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundsRegister.mobCreepoidHit;
    }

    @Override
    public boolean getCanSpawnHere() {
        return posY < 19 && super.getCanSpawnHere();
    }

    @Override
    public boolean isEntityInvulnerable(DamageSource source) {
        return source.isExplosion() || super.isEntityInvulnerable(source);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (super.attackEntityFrom(source, amount)) {
            if (!source.isFireDamage() && rand.nextInt(3) == 0)
                world.createExplosion(this, posX, posY, posZ, 2, false);

            return true;
        }

        return false;
    }

    @Override
    protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {
        dropItem(ItemRegister.coinCopper, 2 + rand.nextInt(5 + lootingModifier));

        if (wasRecentlyHit) {
            if (rand.nextBoolean())
                dropItem(ItemRegister.tokensCreeponia, 1 + rand.nextInt(3 + lootingModifier));

            if (rand.nextInt(4) == 0)
                dropItem(Item.getItemFromBlock(BlockRegister.bannerCreepoid), 1);
        }
    }

    @Nonnull
    @Override
    public TreeSet<Enums.MobProperties> getMobProperties() {
        return mobProperties;
    }
}
