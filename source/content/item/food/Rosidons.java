package net.tslat.aoa3.content.item.food;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityTeleportEvent;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;
import java.util.List;

public class Rosidons extends Item {
	public Rosidons() {
		super(new Item.Properties().food(new FoodProperties.Builder().nutrition(0).saturationMod(0).alwaysEat().build()));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
		if (!world.isClientSide) {
			int minY = Math.max(world.getMinBuildHeight(), (int)Math.floor(entity.getY()));
			int calculatedY = WorldUtil.getHighestSafeLocation(world, (int)Math.floor(entity.getX()), (int)Math.floor(entity.getZ()), false, minY);

			if (calculatedY <= minY) {
				if (entity instanceof ServerPlayer pl)
					PlayerUtil.notifyPlayer(pl, Component.translatable("message.feedback.item.rosidons.noHeightFail"));

				return super.finishUsingItem(stack, world, entity);
			}

			if (MinecraftForge.EVENT_BUS.post(new EntityTeleportEvent(entity, entity.getX(), calculatedY, entity.getZ())))
				return stack;

			if (calculatedY - entity.getY() >= 350 && entity instanceof ServerPlayer pl)
				pl.getAdvancements().award(AdvancementUtil.getAdvancement(AdventOfAscension.id("completionist/super_escape_rope")), "350_blocks");

			world.gameEvent(GameEvent.TELEPORT, entity.position(), GameEvent.Context.of(entity));
			world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.PLAYERS, 1, 1);
			entity.teleportTo(entity.getX(), calculatedY + 1.5d, entity.getZ());
		}

		return super.finishUsingItem(stack, world, entity);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
