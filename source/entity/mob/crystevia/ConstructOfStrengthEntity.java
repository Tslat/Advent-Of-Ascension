package net.tslat.aoa3.entity.mob.crystevia;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.PotionUtil;

import javax.annotation.Nullable;

public class ConstructOfStrengthEntity extends AoAMeleeMob {
    public ConstructOfStrengthEntity(EntityType<? extends MonsterEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 2.125f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_CRYSTAL_CONSTRUCT_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_CRYSTAL_CONSTRUCT_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_CRYSTAL_CONSTRUCT_HURT.get();
    }

    @Override
    public void tick() {
        super.tick();

        if (isAlive() && getHealth() < getMaxHealth())
            heal(0.1f);
    }

    @Override
    public boolean addEffect(EffectInstance effect) {
        if (effect.getEffect() == Effects.DAMAGE_BOOST)
            PotionUtil.amplifyEffect(effect, (effect.getAmplifier() + 1) * 4 - effect.getAmplifier());

        return super.addEffect(effect);
    }

    @Override
    protected void onAttack(Entity target) {
        if (!level.isClientSide && target instanceof PlayerEntity && ((PlayerEntity)target).getHealth() > 0 && hasEffect(Effects.DAMAGE_BOOST) && ItemUtil.findInventoryItem((PlayerEntity)target, new ItemStack(AoAItems.BLANK_REALMSTONE.get()), true, 1))
            ItemUtil.givePlayerItemOrDrop((PlayerEntity)target, new ItemStack(AoAItems.IMMORTALLIS_REALMSTONE.get()));
    }
}
