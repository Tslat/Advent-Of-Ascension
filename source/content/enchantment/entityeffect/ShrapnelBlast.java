package net.tslat.aoa3.content.enchantment.entityeffect;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.library.builder.AoAExplosionBuilder;
import net.tslat.tme.api.explosion.ExplosionInfo;
import net.tslat.tme.api.explosion.ExtendedExplosion;
import net.tslat.tme.api.explosion.ShrapnelExplosion;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.api.util.RandomUtil;

import java.util.List;

public record ShrapnelBlast(LevelBasedValue radius, LevelBasedValue entityDamage, LevelBasedValue blockDamage, LevelBasedValue blockDropChance) implements EnchantmentEntityEffect {
    public static final MapCodec<ShrapnelBlast> CODEC = RecordCodecBuilder.mapCodec(builder -> builder.group(
            LevelBasedValue.CODEC.fieldOf("radius").forGetter(ShrapnelBlast::radius),
            LevelBasedValue.CODEC.fieldOf("entityDamage").forGetter(ShrapnelBlast::entityDamage),
            LevelBasedValue.CODEC.fieldOf("blockDamage").forGetter(ShrapnelBlast::blockDamage),
            LevelBasedValue.CODEC.optionalFieldOf("blockDropChance", LevelBasedValue.constant(0.1f)).forGetter(ShrapnelBlast::blockDropChance)
    ).apply(builder, ShrapnelBlast::new));

    @Override
    public MapCodec<ShrapnelBlast> codec() {
        return CODEC;
    }

    @Override
    public void apply(ServerLevel level, int enchantLevel, EnchantedItemInUse item, Entity entity, Vec3 position) {
        AoAExplosionBuilder.at(level, position, ExplosionInfo.builder()
                        .customFx((explosion, tick) -> doShrapnelFx(explosion, tick, enchantLevel))
                                    .radius(this.radius.calculate(enchantLevel)).blockDropChance(Mth.clamp(this.blockDropChance.calculate(enchantLevel), 0, 1))
                                    .entityDamage(this.entityDamage.calculate(enchantLevel)).blockDamage(this.blockDamage.calculate(enchantLevel)).build(),
                               ShrapnelExplosion::new).explodingEntity(entity).explode();
    }

    private void doShrapnelFx(ExtendedExplosion explosion, int explosionTick, int enchantLevel) {
        if (explosionTick == 1) {
            final Vec3 pos = explosion.position();

            ParticleBuilder.forPositions(explosion.radius() >= 4f ? ParticleTypes.EXPLOSION_EMITTER : ParticleTypes.EXPLOSION, pos)
                    .sendToAllPlayersTrackingBlock((ServerLevel)explosion.level, BlockPos.containing(pos));

            if (explosion instanceof ShrapnelExplosion shrapnelExplosion) {
                final List<Vec3> rays = shrapnelExplosion.getRays();
                final ParticleBuilder particles = ParticleBuilder.forPositions(ParticleTypes.SMOKE);
                final int step = Mth.ceil(rays.size() * (1 / 75f) - Math.min(5, (float)enchantLevel));
                int next = 0;

                while (next < rays.size()) {
                    particles.addPosition(pos)
                            .velocity(rays.get(next).scale(1 + RandomUtil.scaledGaussianValue(0.1f)))
                            .lifespan(RandomUtil.numberBetween(3, 5));

                    next = next + Math.max(1, RandomUtil.numberBetween(step - 10, step + 10));
                }

                particles.defaultParticleCount();
                particles.sendToAllPlayersTrackingBlock((ServerLevel)explosion.getLevel(), BlockPos.containing(pos));
            }
        }
    }
}
