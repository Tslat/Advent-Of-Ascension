package net.tslat.aoa3.content.entity.npc.trader;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.tslat.aoa3.common.container.CorruptedTravellerContainer;
import net.tslat.aoa3.common.registration.AoAConfigs;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.WorldUtil;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;


public class CorruptedTravellerEntity extends PathfinderMob implements GeoEntity {
	private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

	public CorruptedTravellerEntity(EntityType<? extends PathfinderMob> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new FloatGoal(this));
		goalSelector.addGoal(1, new AvoidEntityGoal<AoAMeleeMob>(this, AoAMeleeMob.class, 8f, 0.8d, 1d));
		goalSelector.addGoal(2, new OpenDoorGoal(this, true));
		goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 3f, 1f));
		goalSelector.addGoal(4, new RandomStrollGoal(this, 0.6d));
		goalSelector.addGoal(5, new RandomLookAroundGoal(this));
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level(), AoADimensions.OVERWORLD.key);
	}

	@Override
	public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
		return super.checkSpawnRules(level, spawnReason) && level.getEntitiesOfClass(CorruptedTravellerEntity.class, getBoundingBox().inflate(16, 16, 16)).isEmpty();
	}

	@Override
	public void readAdditionalSaveData(CompoundTag pCompound) {
		super.readAdditionalSaveData(pCompound);

		if (tickCount == 0)
			setGlowingTag(AoAConfigs.SERVER.easyCorruptedTravellers.get());
	}

	@Override
	protected InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack heldStack = player.getItemInHand(hand);

		if (heldStack.getItem() == Items.NAME_TAG) {
			InteractionResult result = heldStack.interactLivingEntity(player, this, hand);

			if (result.consumesAction())
				return result;
		}

		if (isAlive() && !player.isShiftKeyDown()) {
			if (player instanceof ServerPlayer pl)
				openScreen(pl);

			return InteractionResult.sidedSuccess(level().isClientSide);
		}

		return super.mobInteract(player, hand);
	}

	protected void openScreen(ServerPlayer player) {
		player.openMenu(new MenuProvider() {
			@Override
			public Component getDisplayName() {
				return CorruptedTravellerEntity.this.getDisplayName();
			}

			@Nullable
			@Override
			public AbstractContainerMenu createMenu(int screenId, Inventory inv, Player player) {
				return new CorruptedTravellerContainer(screenId, player.getInventory(), CorruptedTravellerEntity.this);
			}
		}, buffer -> buffer.writeInt(getId()));
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(DefaultAnimations.genericWalkIdleController(this));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.geoCache;
	}
}
