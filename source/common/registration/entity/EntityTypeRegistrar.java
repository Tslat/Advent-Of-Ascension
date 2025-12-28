package net.tslat.aoa3.common.registration.entity;

import net.minecraft.SharedConstants;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityAttachment;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;

public class EntityTypeRegistrar<T extends Entity> extends EntityType.Builder<T> {
    private int spawnEggBackgroundColour = -1;
    private int spawnEggForegroundColour = -1;

    private EntityTypeRegistrar(EntityType.EntityFactory<T> factory, MobCategory category) {
        super(factory, category);
    }

    public static <T extends Entity> EntityTypeRegistrar<T> misc(EntityType.EntityFactory<T> factory) {
        return new EntityTypeRegistrar<>(factory, MobCategory.MISC);
    }

    public static <T extends Entity> EntityTypeRegistrar<T> ambientWaterMob(EntityType.EntityFactory<T> factory) {
        return new EntityTypeRegistrar<>(factory, MobCategory.WATER_AMBIENT);
    }

    public static <T extends Entity> EntityTypeRegistrar<T> waterMob(EntityType.EntityFactory<T> factory) {
        return new EntityTypeRegistrar<>(factory, MobCategory.WATER_CREATURE);
    }

    public static <T extends Entity> EntityTypeRegistrar<T> undergroundWaterMob(EntityType.EntityFactory<T> factory) {
        return new EntityTypeRegistrar<>(factory, MobCategory.UNDERGROUND_WATER_CREATURE);
    }

    public static <T extends Entity> EntityTypeRegistrar<T> axolotl(EntityType.EntityFactory<T> factory) {
        return new EntityTypeRegistrar<>(factory, MobCategory.AXOLOTLS);
    }

    public static <T extends Entity> EntityTypeRegistrar<T> ambient(EntityType.EntityFactory<T> factory) {
        return new EntityTypeRegistrar<>(factory, MobCategory.AMBIENT);
    }

    public static <T extends Entity> EntityTypeRegistrar<T> creature(EntityType.EntityFactory<T> factory) {
        return new EntityTypeRegistrar<>(factory, MobCategory.CREATURE).clientTrackingRange(10);
    }

    public static <T extends Entity> EntityTypeRegistrar<T> monster(EntityType.EntityFactory<T> factory) {
        return new EntityTypeRegistrar<>(factory, MobCategory.MONSTER).clientTrackingRange(8);
    }

    public static <T extends Entity> EntityTypeRegistrar<T> of(EntityType.EntityFactory<T> factory, MobCategory category) {
        return new EntityTypeRegistrar<>(factory, category);
    }

    boolean hasSpawnEgg() {
        return this.spawnEggBackgroundColour != -1;
    }

    int getSpawnEggBackgroundColour() {
        return this.spawnEggBackgroundColour;
    }

    int getSpawnEggDotsColour() {
        return this.spawnEggForegroundColour;
    }

    @Override
    public EntityType<T> build(String id) {
        final boolean dataFixers = SharedConstants.CHECK_DATA_FIXER_SCHEMA;
        SharedConstants.CHECK_DATA_FIXER_SCHEMA = false;

        EntityType<T> entityType = super.build(id);
        SharedConstants.CHECK_DATA_FIXER_SCHEMA = dataFixers;

        return entityType;
    }

    public EntityTypeRegistrar<T> spawnEgg(int background, int dots) {
        this.spawnEggBackgroundColour = background;
        this.spawnEggForegroundColour = dots;

        return this;
    }

    public EntityTypeRegistrar<T> sized(float width, float height, float eyeHeight) {
        sized(width, height);
        eyeHeight(eyeHeight);

        return this;
    }

    @Override
    public EntityTypeRegistrar<T> sized(float width, float height) {
        super.sized(width, height);

        return this;
    }

    @Override
    public EntityTypeRegistrar<T> spawnDimensionsScale(float spawnDimensionsScale) {
        super.spawnDimensionsScale(spawnDimensionsScale);

        return this;
    }

    @Override
    public EntityTypeRegistrar<T> eyeHeight(float eyeHeight) {
        super.eyeHeight(eyeHeight);

        return this;
    }

    @Override
    public EntityTypeRegistrar<T> passengerAttachments(float... verticalOffsets) {
        super.passengerAttachments(verticalOffsets);


        return this;
    }

    @Override
    public EntityTypeRegistrar<T> passengerAttachments(Vec3... offsets) {
        super.passengerAttachments(offsets);

        return this;
    }

    @Override
    public EntityTypeRegistrar<T> vehicleAttachment(Vec3 pAttachPoint) {
        return this.attach(EntityAttachment.VEHICLE, pAttachPoint.multiply(1, -1, 1));
    }

    @Override
    public EntityTypeRegistrar<T> ridingOffset(float pRidingOffset) {
        return this.attach(EntityAttachment.VEHICLE, 0.0F, -pRidingOffset, 0.0F);
    }

    @Override
    public EntityTypeRegistrar<T> nameTagOffset(float pNameTagOffset) {
        return this.attach(EntityAttachment.NAME_TAG, 0.0F, pNameTagOffset, 0.0F);
    }

    @Override
    public EntityTypeRegistrar<T> attach(EntityAttachment attachment, float offsetX, float offsetY, float offsetZ) {
        super.attach(attachment, offsetX, offsetY, offsetZ);

        return this;
    }

    @Override
    public EntityTypeRegistrar<T> attach(EntityAttachment attachment, Vec3 offset) {
        super.attach(attachment, offset);

        return this;
    }

    @Override
    public EntityTypeRegistrar<T> noSummon() {
        super.noSummon();

        return this;
    }

    @Override
    public EntityTypeRegistrar<T> noSave() {
        super.noSave();

        return this;
    }

    @Override
    public EntityTypeRegistrar<T> fireImmune() {
        super.fireImmune();

        return this;
    }

    @Override
    public EntityTypeRegistrar<T> immuneTo(Block... blocks) {
        super.immuneTo(blocks);

        return this;
    }

    @Override
    public EntityTypeRegistrar<T> canSpawnFarFromPlayer() {
        super.canSpawnFarFromPlayer();

        return this;
    }

    @Override
    public EntityTypeRegistrar<T> clientTrackingRange(int clientTrackingRange) {
        super.clientTrackingRange(clientTrackingRange);

        return this;
    }

    @Override
    public EntityTypeRegistrar<T> updateInterval(int updateInterval) {
        super.updateInterval(updateInterval);

        return this;
    }

    @Override
    public EntityTypeRegistrar<T> requiredFeatures(FeatureFlag... requiredFeatures) {
        super.requiredFeatures(requiredFeatures);

        return this;
    }

    @Override
    public EntityTypeRegistrar<T> setUpdateInterval(int interval) {
        super.setUpdateInterval(interval);

        return this;
    }

    @Override
    public EntityTypeRegistrar<T> setTrackingRange(int range) {
        super.setTrackingRange(range);

        return this;
    }

    @Override
    public EntityTypeRegistrar<T> setShouldReceiveVelocityUpdates(boolean value) {
        super.setShouldReceiveVelocityUpdates(value);

        return this;
    }
}
