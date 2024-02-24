package net.tslat.aoa3.content.entity.misc;

import com.google.common.base.Suppliers;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAEntityDataSerializers;
import net.tslat.aoa3.common.registration.entity.variant.PixonVariant;
import net.tslat.aoa3.common.registration.item.AoATools;
import net.tslat.aoa3.common.toast.ItemRequirementToastData;
import net.tslat.aoa3.library.builder.SoundBuilder;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LootUtil;
import net.tslat.aoa3.util.MathUtil;
import net.tslat.effectslib.api.particle.ParticleBuilder;
import net.tslat.effectslib.networking.packet.TELParticlePacket;
import net.tslat.smartbrainlib.util.EntityRetrievalUtil;

import java.util.List;
import java.util.UUID;

public class PixonEntity extends Entity {
    private static final EntityDataAccessor<PixonVariant> VARIANT = SynchedEntityData.defineId(PixonEntity.class, AoAEntityDataSerializers.PIXON_VARIANT.get());
    private static final EntityDataAccessor<Float> MAGNITUDE = SynchedEntityData.defineId(PixonEntity.class, EntityDataSerializers.FLOAT);

    protected PixonVariant variant = PixonVariant.AMBIENT.get();
    protected float magnitude = 1;
    protected Object2IntArrayMap<UUID> itemDeficitAlertedPlayers;

    public PixonEntity(EntityType<? extends PixonEntity> pEntityType, Level level) {
        super(pEntityType, level);
    }

    public void setMagnitude(float magnitude) {
        this.magnitude = magnitude;
    }

    public float getMagnitude() {
        return this.magnitude;
    }

    public void setVariant(PixonVariant variant) {
        this.variant = variant;
    }

    public PixonVariant getVariant() {
        return this.variant;
    }

    @Override
    protected void defineSynchedData() {
        getEntityData().define(MAGNITUDE, 1f);
        getEntityData().define(VARIANT, PixonVariant.AMBIENT.get());
    }

    @Override
    public CompoundTag saveWithoutId(CompoundTag data) {
        data = super.saveWithoutId(data);

        data.putInt("Age", this.tickCount);

        return data;
    }

    @Override
    public void load(CompoundTag data) {
        super.load(data);

        if (data.contains("Age", Tag.TAG_INT))
            this.tickCount = data.getInt("Age");
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        super.onSyncedDataUpdated(key);

        if (key.equals(MAGNITUDE))
            this.magnitude = getEntityData().get(MAGNITUDE);

        if (key.equals(VARIANT))
            this.variant = getEntityData().get(VARIANT);
    }

    public void finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty) {
        getEntityData().set(VARIANT, PixonVariant.getVariantForSpawn(world.getLevel(), difficulty, this, Suppliers.memoize(() -> level().getBiome(blockPosition()))));
        getEntityData().set(MAGNITUDE, random.nextFloat() * random.nextFloat() * 25);
    }

    public static boolean canSurviveAt(Level level, Vec3 position) {
        final BlockPos pos = BlockPos.containing(position);

        return level.getBlockState(pos.below()).isSolid() && !level.getBlockState(pos).isSuffocating(level, pos);
    }

    @Override
    public void tick() {
        super.tick();

        if (level() instanceof ServerLevel level) {
            if (this.tickCount >= 12000 + (this.magnitude - 1) * 6000) {
                discard();
                return;
            }

            if (((int)getY()) != getY()) {
                setPos(getX(), (int) getY(), getZ());
            }
            else if (!canSurviveAt(level, position())) {
                discard();
            }

            List<Player> siphoningPlayers = EntityRetrievalUtil.getPlayers(this, 2f + this.magnitude * 0.05f, LivingEntity::isAlive);

            for (Player pl : siphoningPlayers) {
                if (pl.getUseItem().getItem() != AoATools.ATTUNING_BOWL.get()) {
                    checkForPlayerAttuningBowl((ServerPlayer)pl);

                    continue;
                }

                float scaling = Math.min(25, this.magnitude);

                if (this.random.nextFloat() < scaling / 250f) {
                    ItemUtil.givePlayerMultipleItems(pl, LootUtil.generateLoot(this.variant.lootTable(), new LootParams.Builder(level)
                            .withParameter(LootContextParams.THIS_ENTITY, this)
                            .withParameter(LootContextParams.ORIGIN, position())
                            .withParameter(LootContextParams.DAMAGE_SOURCE, level.damageSources().playerAttack(pl))
                            .withParameter(LootContextParams.KILLER_ENTITY, pl)
                            .withParameter(LootContextParams.DIRECT_KILLER_ENTITY, pl)
                            .withParameter(LootContextParams.LAST_DAMAGE_PLAYER, pl)
                            .create(LootContextParamSets.ENTITY)));

                    if (this.magnitude > 1)
                        getEntityData().set(MAGNITUDE, Math.max(1, this.magnitude -= 0.5f));

                    this.tickCount += 500;

                    TELParticlePacket packet = new TELParticlePacket();

                    for (int i = 0; i < 50; i++) {
                        packet.particle(ParticleBuilder.forRandomPosInSphere(ParticleTypes.FIREWORK, position().add(0, 0.35f, 0), 0.65f)
                                .colourOverride(this.random.nextFloat() < 0.25f ? this.variant.secondaryColour() : this.variant.primaryColour())
                                .ignoreDistanceAndLimits());
                    }

                    packet.sendToAllPlayersTrackingEntity(level, this);
                }
            }
        }
        else if (this.variant != null) {
            float size = Math.min(Math.min(2, this.magnitude), (random.nextFloat() * random.nextFloat()) * this.magnitude / 2f);

            ParticleBuilder.forRandomPosInEntity(new CustomisableParticleType.Data(AoAParticleTypes.ORB.get(), size, 1, this.random.nextFloat() < 0.25f ? this.variant.secondaryColour() : this.variant.primaryColour()), this)
                    .velocity(random.nextGaussian() * 0.05f * (1 / size), Math.min(1.5f, this.magnitude * 0.1f), random.nextGaussian() * 0.05f * (1 / size))
                    .cutoffDistance(256)
                    .lifespan(100)
                    .spawnParticles(level());
            ParticleBuilder.forRandomPosInSphere(ParticleTypes.ASH, position(), Math.min(20, this.magnitude))
                    .colourOverride((this.random.nextFloat() < 0.25f ? this.variant.secondaryColour() : this.variant.primaryColour()) | 255 << 24)
                    .velocity(random.nextGaussian() * 0.1f * (1 / size), random.nextFloat() * 0.5f + 0.25f, random.nextGaussian() * 0.1f * (1 / size))
                    .spawnParticles(level());

            for (Vec3 ringPos : MathUtil.inLateralCircle(position(), 0.5f, random.nextIntBetweenInclusive(1, 12))) {
                ParticleBuilder.forPositions(new CustomisableParticleType.Data(AoAParticleTypes.ORB.get(), size, 1, this.random.nextFloat() < 0.25f ? this.variant.secondaryColour() : this.variant.primaryColour()), ringPos)
                        .velocity(position().vectorTo(ringPos).normalize().scale(0.1f).add(random.nextGaussian() * 0.05f * (1 / size), Math.min(1.5f, this.magnitude * 0.05f), random.nextGaussian() * 0.05f * (1 / size)))
                        .lifespan(100)
                        .spawnParticles(level());
            }

            if (this.tickCount % 25 == 0)
                new SoundBuilder(AoASounds.ENTITY_PIXON_AMBIENT).followEntity(this).radius(16).execute();
        }
    }

    private void checkForPlayerAttuningBowl(ServerPlayer pl) {
        final UUID uuid = pl.getUUID();

        if (this.itemDeficitAlertedPlayers == null || !this.itemDeficitAlertedPlayers.containsKey(uuid) || pl.tickCount - this.itemDeficitAlertedPlayers.getInt(uuid) > 100) {
            if (!ItemUtil.findInventoryItem(pl, AoATools.ATTUNING_BOWL.get().getDefaultInstance(), false, 1, true)) {
                if (this.itemDeficitAlertedPlayers == null)
                    this.itemDeficitAlertedPlayers = new Object2IntArrayMap<>(1);

                this.itemDeficitAlertedPlayers.put(uuid, pl.tickCount);
                ItemRequirementToastData.sendToastPopupTo(pl, AoATools.ATTUNING_BOWL.get());

            }
        }
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    public boolean canChangeDimensions() {
        return false;
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag data) {
        if (data.contains("Variant", Tag.TAG_STRING))
            getEntityData().set(VARIANT, AoARegistries.PIXON_VARIANTS.getEntry(ResourceLocation.tryParse(data.getString("Variant"))));

        if (data.contains("Magnitude"))
            getEntityData().set(MAGNITUDE, data.contains("Magnitude") ? data.getFloat("Magnitude") : 1f);
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag data) {
        data.putString("Variant", AoARegistries.PIXON_VARIANTS.getKey(this.variant).toString());
        data.putFloat("Magnitude", this.magnitude);
    }
}
