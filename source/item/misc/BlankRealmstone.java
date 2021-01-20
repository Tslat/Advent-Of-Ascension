package net.tslat.aoa3.item.misc;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.HuskEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.DistExecutor;
import net.tslat.aoa3.capabilities.volatilestack.VolatileStackCapabilityHandles;
import net.tslat.aoa3.capabilities.volatilestack.VolatileStackCapabilityProvider;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.mob.precasia.PrimitiveCarrotopEntity;
import net.tslat.aoa3.util.*;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class BlankRealmstone extends Item {
	public BlankRealmstone() {
		super(new Item.Properties().group(AoAItemGroups.MISC_ITEMS).maxStackSize(1));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		if (world.isRemote)
			DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientOperations::displayBlankRealmstoneGui);

		return ActionResult.resultSuccess(player.getHeldItem(hand));
	}

	@Override
	public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
		if (!entity.world.isRemote) {
			if (entity.world.getDimension().getType() == AoADimensions.PRECASIA.type()) {
				BlockPos pos = entity.getPosition();
				BlockState state = entity.world.getBlockState(pos);

				if (state.getBlock() == Blocks.CARROTS && ((CropsBlock)state.getBlock()).isMaxAge(state)) {
					PrimitiveCarrotopEntity carrotop = new PrimitiveCarrotopEntity(AoAEntities.Mobs.PRIMITIVE_CARROTOP.get(), entity.world);

					carrotop.setPosition(pos.getX() + 0.5, pos.getY() + 0.2, pos.getZ() + 0.5);
					entity.world.addEntity(carrotop);
					entity.remove();
					entity.world.setBlockState(entity.getPosition(), Blocks.AIR.getDefaultState());
				}
			}
		}

		return false;
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity player, LivingEntity target, Hand hand) {
		if (player.world.getDimension().getType() == AoADimensions.CREEPONIA.type() && target instanceof AoATrader) {
			if (player instanceof ServerPlayerEntity && DamageUtil.isPlayerEnvironmentallyProtected((ServerPlayerEntity)player) && player.getHeldItem(hand).getItem() == AoAItems.BLANK_REALMSTONE.get()) {
				player.setHeldItem(hand, ItemStack.EMPTY);
				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAItems.VOX_PONDS_REALMSTONE.get()));
				PlayerUtil.notifyPlayer((ServerPlayerEntity)player, LocaleUtil.getLocaleString("message.dialogue.creeponiaBlankRealmstone." + RandomUtil.randomNumberUpTo(3)));
			}

			return true;
		}

		return false;
	}

	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if (!attacker.world.isRemote && target.getHealth() <= 0 && target instanceof HuskEntity && attacker instanceof PlayerEntity)
			attacker.setHeldItem(Hand.MAIN_HAND, new ItemStack(AoAItems.BARATHOS_REALMSTONE.get()));

		return super.hitEntity(stack, target, attacker);
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
		return new VolatileStackCapabilityProvider();
	}

	public static void handleAncientCavernTask(ItemStack stack, LivingEntity construct, PlayerEntity player) {
		VolatileStackCapabilityHandles cap = VolatileStackCapabilityProvider.getOrDefault(stack, null);
		HashMap<Class<? extends LivingEntity>, Long> constructKillMap;
		long currentWorldTime = construct.world.getGameTime();

		if (cap.getObject() == null) {
			constructKillMap = new HashMap<Class<? extends LivingEntity>, Long>(5);
		}
		else {
			constructKillMap = (HashMap<Class<? extends LivingEntity>, Long>)cap.getObject();
		}

		constructKillMap.entrySet().removeIf(entry -> entry.getValue() < currentWorldTime - 600);
		constructKillMap.put(construct.getClass(), currentWorldTime);

		if (constructKillMap.size() >= 5) {
			stack.shrink(1);
			ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAItems.ANCIENT_CAVERN_REALMSTONE.get()));
		}
		else {
			cap.setObject(constructKillMap);
		}
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 2));
	}
}
