package net.tslat.aoa3.content.world.event;

import com.google.common.collect.Streams;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.common.registration.custom.AoAWorldEvents;
import net.tslat.aoa3.common.registration.entity.AoADamageTypes;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.NumberUtil;

public class BarathosSandstormEvent implements AoAWorldEvent {
    public static final MapCodec<BarathosSandstormEvent> CODEC = RecordCodecBuilder.mapCodec(builder -> builder.group(
            GenericSettings.CODEC.fieldOf("base_settings").forGetter(event -> event.baseSettings),
            IntProvider.POSITIVE_CODEC.fieldOf("interval").forGetter(event -> event.interval),
            IntProvider.POSITIVE_CODEC.fieldOf("duration").forGetter(event -> event.duration),
            FloatProvider.CODEC.fieldOf("damage").forGetter(event -> event.damage)
    ).apply(builder, BarathosSandstormEvent::new));

    private final GenericSettings baseSettings;
    private final IntProvider interval;
    private final IntProvider duration;
    private final FloatProvider damage;

    private boolean active = false;
    private long nextSandstorm = -1;
    private long sandstormEndTime = -1;
    private long sandstormStartTime = -1;

    public BarathosSandstormEvent() {
        this(null, null, null, null);
    }

    public BarathosSandstormEvent(GenericSettings baseSettings, IntProvider interval, IntProvider duration, FloatProvider damage) {
        this.baseSettings = baseSettings;
        this.interval = interval;
        this.duration = duration;
        this.damage = damage;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean isActive() {
        return this.active;
    }

    @Override
    public boolean shouldAddToDimension(ServerLevel level) {
        return this.baseSettings.dimensions().contains(level.dimension());
    }

    @Override
    public AoAWorldEvent copy() {
        return new BarathosSandstormEvent(this.baseSettings, this.interval, this.duration, this.damage);
    }

    @Override
    public ResourceLocation getId() {
        return this.baseSettings.id();
    }

    @Override
    public Type<BarathosSandstormEvent> getType() {
        return AoAWorldEvents.BARATHOS_SANDSTORM.get();
    }

    @Override
    public CompoundTag save() {
        CompoundTag tag = new CompoundTag();

        tag.putBoolean("Active", this.active);
        tag.putLong("NextSandstorm", this.nextSandstorm);
        tag.putLong("SandstormEndTime", this.sandstormEndTime);
        tag.putLong("SandstormStartTime", this.sandstormStartTime);

        return tag;
    }

    @Override
    public void load(CompoundTag tag) {
        this.active = tag.getBoolean("Active");
        this.nextSandstorm = tag.getLong("NextSandstorm");
        this.sandstormEndTime = tag.getLong("SandstormEndTime");
        this.sandstormStartTime = tag.getLong("SandstormStartTime");
    }

    @Override
    public void tick(Level level) {
        if (!(level instanceof ServerLevel serverLevel))
            return;

        if (!isActive()) {
            if (this.nextSandstorm == -1)
                this.nextSandstorm = serverLevel.getGameTime() + this.interval.sample(serverLevel.getRandom());

            if (this.nextSandstorm <= serverLevel.getGameTime())
                start(serverLevel);

            return;
        }

        if (this.sandstormEndTime >= 0 && this.sandstormEndTime <= serverLevel.getGameTime()) {
            stop(serverLevel);

            return;
        }

        if (getIntensity(serverLevel.getGameTime()) >= 1 && serverLevel.getGameTime() % 10 == 0) {
            DamageSource source = DamageUtil.miscDamage(AoADamageTypes.SANDSTORM, serverLevel);

            Streams.stream(serverLevel.getEntities().getAll())
                    .filter(LivingEntity.class::isInstance)
                    .map(LivingEntity.class::cast)
                    .filter(this::isEntityAffected)
                    .forEach(entity -> entity.hurt(source, this.damage.sample(serverLevel.getRandom())));
        }
    }

    public float getIntensity(long gameTime) {
        return Math.min(1f, Math.min((gameTime - this.sandstormStartTime) / 600f, (this.sandstormEndTime - gameTime) / 100f));
    }

    public boolean isEntityAffected(LivingEntity entity) {
        return NumberUtil.numberIsBetween(entity.getY(), 90, 125) && entity.level().getBrightness(LightLayer.SKY, entity.blockPosition()) == 15 && !entity.getType().is(AoATags.Entities.IMMUNE_TO_SANDSTORM) && (!(entity instanceof Player) || entity.tickCount > 60);
    }

    @Override
    public void start(ServerLevel level) {
        this.active = true;
        this.sandstormStartTime = level.getGameTime();
        this.sandstormEndTime = this.sandstormStartTime + this.duration.sample(level.getRandom());

        markDirty(level);
    }

    @Override
    public void stop(ServerLevel level) {
        this.active = false;
        this.nextSandstorm = level.getGameTime() + this.interval.sample(level.getRandom());
        this.sandstormEndTime = -1;
        this.sandstormStartTime = -1;

        markDirty(level);
    }
}
