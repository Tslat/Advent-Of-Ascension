package net.tslat.aoa3.content.item.tool.artifice;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.api.util.RandomUtil;
import net.tslat.tme.internal.particle.transition.OrbitPositionParticleTransition;

import java.util.Set;

public class AnchoringCrystal extends ArtificeItem {
    public AnchoringCrystal() {
        super(new Properties().stacksTo(1));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.NONE;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 100;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (player.isDeadOrDying() || WorldUtil.isWorld(level, AoADimensions.NOWHERE) || (player instanceof ServerPlayer pl && (pl.getRespawnPosition() == null || !WorldUtil.isWorld(level, pl.getRespawnDimension()))))
            return InteractionResultHolder.fail(player.getItemInHand(usedHand));

        player.startUsingItem(usedHand);

        return InteractionResultHolder.consume(player.getItemInHand(usedHand));
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
        if (!level.isClientSide) {
            ParticleBuilder.forRandomPosOnToroid(ParticleTypes.TRIAL_SPAWNER_DETECTED_PLAYER, livingEntity.position().add(0, livingEntity.getBbHeight() * 0.5f, 0), livingEntity.getBbWidth() * 0.45f, livingEntity.getBbWidth())
                    .addTransition(OrbitPositionParticleTransition.create(livingEntity, RandomUtil.numberBetween(5, 10)))
                    .spawnNTimes((getUseDuration(stack, livingEntity) - remainingUseDuration) / 10)
                    .addBulkProperty(builder -> builder.lifespan(Math.min(RandomUtil.numberBetween(20, 60), remainingUseDuration)))
                    .sendToAllPlayersTrackingEntity(livingEntity);
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (livingEntity instanceof ServerPlayer player && player.getRespawnDimension() == player.level().dimension()) {
            DimensionTransition transition = player.findRespawnPositionAndUseSpawnBlock(true, DimensionTransition.DO_NOTHING); // ??
            transition = EventHooks.firePlayerRespawnPositionEvent(player, transition, false).getDimensionTransition();

            if (transition.newLevel() == level) {
                Vec3 pos = transition.pos();

                ParticleBuilder.forRandomPosInEntity(ParticleTypes.CHERRY_LEAVES, player)
                        .spawnNTimes(150)
                        .colourTint(0xE57200)
                        .addBulkProperty(builder -> builder
                                .scaleMod(RandomUtil.valueBetween(0.5f, 1f))
                                .lifespan(RandomUtil.numberBetween(40, 80))
                                .velocity(RandomUtil.gaussianOffset(Vec3.ZERO, 0.1f, 0, 0.1f)))
                        .sendToAllPlayersTrackingBlock(player.serverLevel(), player.blockPosition());

                player.teleportTo(transition.newLevel(), pos.x, pos.y, pos.z, Set.of(), transition.yRot(), transition.xRot());

                ParticleBuilder.forRandomPosOnToroid(ParticleTypes.TRIAL_SPAWNER_DETECTED_PLAYER, livingEntity.position().add(0, livingEntity.getBbHeight() * 0.5f, 0), livingEntity.getBbWidth() * 0.45f, livingEntity.getBbWidth())
                        .addTransition(OrbitPositionParticleTransition.create(livingEntity, RandomUtil.numberBetween(5, 10)))
                        .spawnNTimes(100)
                        .addBulkProperty(builder -> builder.lifespan(RandomUtil.numberBetween(20, 60)))
                        .sendToAllPlayersTrackingEntity(livingEntity);
            }
        }

        return stack;
    }
}