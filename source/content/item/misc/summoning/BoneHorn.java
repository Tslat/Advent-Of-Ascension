package net.tslat.aoa3.content.item.misc.summoning;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAMobs;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.entity.boss.tyrosaur.TyrosaurEntity;
import net.tslat.aoa3.library.builder.SoundBuilder;
import net.tslat.aoa3.util.EntitySpawningUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.smartbrainlib.util.BrainUtils;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BoneHorn extends BossSpawningItem<TyrosaurEntity> {
	public BoneHorn() {
		super(0, new Properties().rarity(Rarity.RARE).durability(2).setNoRepair());
	}

	@Override
	public TyrosaurEntity spawnBoss(ServerLevel level, Vec3 position, ItemStack stack) {
		return EntitySpawningUtil.spawnEntity(level, AoAMobs.TYROSAUR.get(), position, MobSpawnType.TRIGGERED);
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 63;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		if (player.getItemInHand(hand).isDamaged()) {
			player.startUsingItem(hand);
			new SoundBuilder(AoASounds.ITEM_BONE_HORN_CALL).followEntity(player).execute();

			return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide);
		}

		return super.use(level, player, hand);
	}

	@Override
	public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
		if (!stack.isDamaged())
			super.onUseTick(level, livingEntity, stack, remainingUseDuration);
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return stack.isDamaged() ? UseAnim.TOOT_HORN : super.getUseAnimation(stack);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
		if (stack.isDamaged()) {
			if (level.dimension() == AoADimensions.PRECASIA.key && level instanceof ServerLevel serverLevel) {
				BlockPos spawnPos = RandomUtil.getRandomPositionWithinRange(entity.blockPosition(), 30, 10, 30, 10, 0, 10, true, level, 10, (state, pos) ->
						level.getBlockState(pos.below()).isValidSpawn(level, pos.below(), AoAMobs.TYROSAUR.get()) && level.noCollision(AoAMobs.TYROSAUR.get().getAABB(pos.getX() + 0.5d, pos.getY(), pos.getZ() + 0.5d)));

				if (spawnPos != entity.blockPosition()) {
					TyrosaurEntity tyrosaur = EntitySpawningUtil.spawnEntity(serverLevel, AoAMobs.TYROSAUR.get(), spawnPos, MobSpawnType.TRIGGERED, summon -> summon.setHealth(50));

					if (tyrosaur != null) {
						BrainUtils.setTargetOfEntity(tyrosaur, entity);
						new SoundBuilder(AoASounds.ENTITY_TYROSAUR_AMBIENT).followEntity(tyrosaur).execute();
					}
				}
			}

			if (entity instanceof Player pl)
				pl.getCooldowns().addCooldown(this, 20);

			return stack;
		}

		return super.finishUsingItem(stack, level, entity);
	}

	@Override
	public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeUsed) {
		new SoundBuilder(AoASounds.ITEM_BONE_HORN_CALL).stopSound().execute();
	}

	@Override
	public EntityType<TyrosaurEntity> getEntityType(ItemStack stack) {
		return stack.isDamaged() ? null : AoAMobs.TYROSAUR.get();
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag tooltipFlag) {
		if (!stack.isDamaged()) {
			tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 1));
			tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 2));
		}
		else {
			tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 3));
		}
	}
}
