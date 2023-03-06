package net.tslat.aoa3.content.item.misc;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.HuskEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.DistExecutor;
import net.tslat.aoa3.client.ClientOperations;
import net.tslat.aoa3.common.registration.*;
import net.tslat.aoa3.content.capability.volatilestack.VolatileStackCapabilityProvider;
import net.tslat.aoa3.content.entity.base.AoATrader;
import net.tslat.aoa3.content.entity.mob.misc.doppelganger.DoppelgangerEntity;
import net.tslat.aoa3.content.entity.mob.precasia.PrimitiveCarrotopEntity;
import net.tslat.aoa3.content.entity.npc.banker.AoABanker;
import net.tslat.aoa3.util.*;

import javax.annotation.Nullable;
import java.util.List;

public class BlankRealmstone extends Item {
	public BlankRealmstone() {
		super(new Item.Properties().tab(AoAItemGroups.MISC_ITEMS).stacksTo(1));
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		if (world.isClientSide)
			DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientOperations::displayBlankRealmstoneGui);

		return ActionResult.success(player.getItemInHand(hand));
	}

	@Override
	public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
		if (!entity.level.isClientSide) {
			if (WorldUtil.isWorld(entity.level, AoADimensions.PRECASIA.key)) {
				BlockPos pos = entity.blockPosition();
				BlockState state = entity.level.getBlockState(pos);

				if (state.getBlock() == Blocks.CARROTS && ((CropsBlock)state.getBlock()).isMaxAge(state)) {
					PrimitiveCarrotopEntity carrotop = new PrimitiveCarrotopEntity(AoAEntities.Mobs.PRIMITIVE_CARROTOP.get(), entity.level);

					carrotop.setPos(pos.getX() + 0.5, pos.getY() + 0.2, pos.getZ() + 0.5);
					entity.level.addFreshEntity(carrotop);
					entity.remove();
					entity.level.setBlockAndUpdate(entity.blockPosition(), Blocks.AIR.defaultBlockState());
				}
			}
		}

		return false;
	}

	@Override
	public ActionResultType useOn(ItemUseContext context) {
		World level = context.getLevel();
		BlockPos pos = context.getClickedPos();

		if (level.getBlockState(context.getClickedPos()).getBlock() == AoABlocks.CRAEXXEUS_ALTAR.get()) {
			if (level instanceof ServerWorld) {
				for (int x = -3; x <= 3; x++) {
					for (int y = 1; y <= 3; y++) {
						for (int z = -3; z <= 3; z++) {
							if (level.getBlockState(pos.offset(x, y, z)).getMaterial().blocksMotion())
								return ActionResultType.SUCCESS;
						}
					}
				}

				DoppelgangerEntity doppelganger = new DoppelgangerEntity(level);

				doppelganger.setPos(pos.getX() + 0.5f, pos.getY() + 1, pos.getZ() + 0.5f);
				doppelganger.finalizeSpawn((ServerWorld)level, level.getCurrentDifficultyAt(pos), SpawnReason.TRIGGERED, null, null);
				level.addFreshEntity(doppelganger);

				for (int i = 0; i < 5; i++) {
					level.addAlwaysVisibleParticle(ParticleTypes.SOUL, doppelganger.getX() + random.nextGaussian() * 0.25f, doppelganger.getY(), doppelganger.getZ() + random.nextGaussian() * 0.25f, 0, 0.5f, 0);
				}

				if (!context.getPlayer().abilities.instabuild)
					context.getItemInHand().shrink(1);
			}

			return ActionResultType.SUCCESS;
		}

		return super.useOn(context);
	}

	@Override
	public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity player, LivingEntity target, Hand hand) {
		if (WorldUtil.isWorld(player.level, AoADimensions.CREEPONIA.key) && (target instanceof AoATrader || target instanceof AoABanker)) {
			if (player instanceof ServerPlayerEntity && DamageUtil.isPlayerEnvironmentallyProtected((ServerPlayerEntity)player) && player.getItemInHand(hand).getItem() == AoAItems.BLANK_REALMSTONE.get()) {
				player.setItemInHand(hand, ItemStack.EMPTY);
				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAItems.VOX_PONDS_REALMSTONE.get()));
				PlayerUtil.notifyPlayer(player, new TranslationTextComponent("message.dialogue.creeponiaBlankRealmstone." + RandomUtil.randomNumberUpTo(3)));
			}

			return ActionResultType.SUCCESS;
		}

		return ActionResultType.PASS;
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if (!attacker.level.isClientSide && target.getHealth() <= 0 && target instanceof HuskEntity && attacker instanceof PlayerEntity)
			attacker.setItemInHand(Hand.MAIN_HAND, new ItemStack(AoAItems.BARATHOS_REALMSTONE.get()));

		return super.hurtEnemy(stack, target, attacker);
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
		return new VolatileStackCapabilityProvider();
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 2));
	}
}
