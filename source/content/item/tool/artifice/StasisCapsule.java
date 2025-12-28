package net.tslat.aoa3.content.item.tool.artifice;

import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.item.AoADataComponents;
import net.tslat.aoa3.library.object.container.CachedEntity;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.api.util.RandomUtil;
import net.tslat.tme.internal.networking.packet.TMEParticlePacket;
import net.tslat.tme.internal.particle.transition.ToPositionParticleTransition;

import java.util.List;

public class StasisCapsule extends ArtificeItem {
    public StasisCapsule() {
        super(new Properties().stacksTo(1).component(AoADataComponents.STORED_ENTITY, CachedEntity.EMPTY));
    }

    public InteractionResult tryCapture(Player player, LivingEntity target, ItemStack stack, InteractionHand hand) {
        if (!stack.get(AoADataComponents.STORED_ENTITY).isEmpty() || !canCapture(player, target))
            return InteractionResult.FAIL;

        if (!player.level().isClientSide) {
            TMEParticlePacket packet = new TMEParticlePacket();

            packet.particle(ParticleBuilder.forRandomPosAtBoundsEdge(ParticleTypes.END_ROD, target.getBoundingBox().inflate(Math.max(target.getBbHeight(), target.getBbWidth()) / 2f))
                    .spawnNTimes(200)
                    .addTransition(ToPositionParticleTransition.create(target.position().add(0, target.getBbHeight() * 0.5f, 0), 5))
                    .colourTint(0.65f, 0, 0.15f, 1f)
                    .lifespan(10));
            packet.particle(ParticleBuilder.forRandomPosAtBoundsEdge(ParticleTypes.ENCHANTED_HIT, target.getBoundingBox().inflate(Math.max(target.getBbHeight(), target.getBbWidth()) / 2f))
                    .spawnNTimes(200)
                    .lifespan(10)
                    .colourTint(1f, 1f, 1f, 1f)
                    .addTransition(ToPositionParticleTransition.create(target.position().add(0, target.getBbHeight() * 0.5f, 0), 5)));

            for (int i = 0; i < 10; i++) {
                packet.particle(ParticleBuilder.forPositions(ParticleTypes.ELECTRIC_SPARK, target.position().add(0, target.getBbHeight() * 0.5f, 0))
                        .spawnNTimes(10)
                        .velocity(RandomUtil.scaledGaussianValue(0.05f), RandomUtil.scaledGaussianValue(0.05f), RandomUtil.scaledGaussianValue(0.05f))
                        .scaleMod(0.25f)
                        .lifespan(RandomUtil.numberBetween(20, 35)));
            }

            packet.sendToAllPlayersTrackingEntity(target);

            player.level().playSound(null, target.getX(), target.getY(), target.getZ(), SoundEvents.BREEZE_WIND_CHARGE_BURST, SoundSource.PLAYERS, 0.5f, (float)RandomUtil.valueBetween(0.9f, 1.1f));
            player.level().playSound(null, target.getX(), target.getY(), target.getZ(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 0.5f, (float)RandomUtil.valueBetween(0.9f, 1.1f));
            stack.set(AoADataComponents.STORED_ENTITY, CachedEntity.store(target));
            player.setItemInHand(hand, stack);
            target.discard();
        }

        return InteractionResult.sidedSuccess(player.level().isClientSide);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        CachedEntity<?> cachedEntity = context.getItemInHand().get(AoADataComponents.STORED_ENTITY);

        if (cachedEntity.isEmpty())
            return InteractionResult.FAIL;

        Player player = context.getPlayer();
        Level level = context.getLevel();
        Vec3 pos = context.getClickLocation();

        if (!level.noCollision(cachedEntity.entityType().getSpawnAABB(pos.x, pos.y, pos.z))) {
            if (!level.isClientSide())
                PlayerUtil.notifyPlayer(player, LocaleUtil.getLocaleMessage(LocaleUtil.createFeedbackLocaleKey("spawnEntity.noSpace"), ChatFormatting.RED));

            return InteractionResult.FAIL;
        }

        if (!level.isClientSide) {
            Entity entity = cachedEntity.createEntity(level);

            if (entity == null) {
                PlayerUtil.notifyPlayer(player, LocaleUtil.getLocaleMessage(LocaleUtil.createFeedbackLocaleKey("spawnEntity.fail"), ChatFormatting.RED));

                return InteractionResult.FAIL;
            }

            entity.setPos(pos);
            level.addFreshEntity(entity);
            context.getItemInHand().set(AoADataComponents.STORED_ENTITY, CachedEntity.EMPTY);

            TMEParticlePacket packet = new TMEParticlePacket();

            for (int i = 0; i < 50; i++) {
                packet.particle(ParticleBuilder.forRandomPosInSphere(ParticleTypes.FALLING_SPORE_BLOSSOM, entity.position().add(0, entity.getBbHeight() * 0.5f, 0), 0.25f)
                        .colourTint(0.8f, 0.3f, 0.3f, 1f)
                        .velocity(RandomUtil.scaledGaussianValue(0.05f), RandomUtil.scaledGaussianValue(0.05f), RandomUtil.scaledGaussianValue(0.05f))
                        .lifespan(RandomUtil.numberBetween(20, 35)));
            }

            packet.sendToAllPlayersTrackingEntity(player);
            level.playSound(null, pos.x, pos.y, pos.z, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.PLAYERS, 0.8f, 1.8f);
            level.playSound(null, pos.x, pos.y, pos.z, SoundEvents.SPLASH_POTION_BREAK, SoundSource.PLAYERS, 1, 1.8f);
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    public static boolean canCapture(Player player, LivingEntity entity) {
        if (entity instanceof Enemy || (entity instanceof NeutralMob neutralMob && neutralMob.isAngryAt(player)))
            return false;

        if (entity instanceof AgeableMob)
            return true;

        if (entity instanceof OwnableEntity ownable && (player.getUUID().equals(ownable.getOwnerUUID()) || ownable.getOwnerUUID() == null))
            return true;

        return false;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        CachedEntity<?> cachedEntity = stack.get(AoADataComponents.STORED_ENTITY);

        if (cachedEntity.isEmpty()) {
            tooltipComponents.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO, 1));
        }
        else {
            tooltipComponents.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.SPECIAL, 2, cachedEntity.getName(context.registries())));
        }
    }
}