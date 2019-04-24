package net.tslat.aoa3.entity.misc;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.item.weapon.blaster.BaseBlaster;
import net.tslat.aoa3.utils.ModUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;

public class EntityLottoIdol extends EntityLiving {
    public static final float entityWidth = 1.0f;
    private final ItemStack majorPrize;
    private final ItemStack minorPrize;

    public EntityLottoIdol(World world) {
        this(world, ItemStack.EMPTY, ItemStack.EMPTY);
    }

    public EntityLottoIdol(World world, ItemStack majorPrize, ItemStack consolationPrize) {
        super(world);
        setSize(entityWidth, 1.0f);
        this.majorPrize = majorPrize;
        this.minorPrize = consolationPrize;

        if (majorPrize != null) {
            String prefix;
            Item item = majorPrize.getItem();

            if (item instanceof BaseBlaster) {
                if (item == WeaponRegister.blasterPenguinBlaster || item == WeaponRegister.blasterBearBlaster || item == WeaponRegister.blasterCamelCannon ||
                        item == WeaponRegister.blasterDragonDestroyer || item == WeaponRegister.blasterBeeBlaster || item == WeaponRegister.blasterDeerDetonator || item == WeaponRegister.blasterHoundHoncho || item == WeaponRegister.blasterFishFryer) {
                    prefix = "Animal Blaster";
                }
                else if (item == WeaponRegister.blasterElectroSoundCannon || item == WeaponRegister.blasterVibeSoundCannon || item == WeaponRegister.blasterSynthSoundCannon || item == WeaponRegister.blasterBeatSoundCannon || item == WeaponRegister.blasterStepSoundCannon) {
                    prefix = "Sound Cannon";
                }
                else {
                    prefix = StringUtil.getLocale(majorPrize.getItem().getUnlocalizedName() + ".name").getFormattedText();
                }
            }
            else {
                prefix = StringUtil.getLocale(majorPrize.getItem().getUnlocalizedName() + ".name").getFormattedText();
            }

            setCustomNameTag(StringUtil.getLocaleWithArguments("entity.aoa3.lotto_idol.fullName", prefix).getFormattedText());
            setAlwaysRenderNameTag(false);
        }
    }

    @Override
    public float getEyeHeight() {
        return 0.75f;
    }

    @Override
    protected void initEntityAI() {
        tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.entityIdolLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.entityIdolHit;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.entityIdolHit;
    }

    @Override
    protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {
        if (!world.isRemote) {
            if (rand.nextInt(22) == 0) {
                entityDropItem(majorPrize, 0.5f);
                world.playSound(null, getPosition().getX(), getPosition().getY(), getPosition().getZ(), SoundsRegister.entityIdolPrize, SoundCategory.MASTER, 1.0f, 1.0f);

                if (source.getTrueSource() instanceof EntityPlayerMP)
                    ModUtil.completeAdvancement((EntityPlayerMP)source.getTrueSource(), "overworld/winner_winner", "major_prize_win");
            }
            else if (rand.nextInt(5) < 4) {
                entityDropItem(new ItemStack(Items.WHEAT_SEEDS, 4), 0.5f);
            }
            else if (rand.nextInt(5) < 4) {
                entityDropItem(new ItemStack(BlockRegister.bannerLotto), 0.5f);
            }
            else {
                entityDropItem(minorPrize, 0.5f);
            }
        }
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1);
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0);
        getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0f);
    }
}
