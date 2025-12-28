package net.tslat.aoa3.content.item.weapon.staff;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.levelgen.Heightmap;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.WorldUtil;

import java.util.List;
import java.util.Optional;

public class LightningStaff extends AoAStaff<BlockPos> {
	public LightningStaff(Item.Properties properties) {
		super(properties);
	}

	@Override
	public Optional<BlockPos> checkPreconditions(LivingEntity caster, ItemStack staff) {
		return Optional.ofNullable(PlayerUtil.getBlockAimingAt(caster, 70));
	}

	@Override
	public void cast(ServerLevel level, LivingEntity caster, ItemStack staff, InteractionHand hand, BlockPos args) {
		for (int i = 0; i <= 360; i += 18) {
			double posX = args.getX() + Math.cos(i) * 4;
			double posZ = args.getZ() + Math.sin(i) * 4;

			WorldUtil.spawnLightning(level, caster instanceof ServerPlayer ? (ServerPlayer)caster : null, posX, level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, BlockPos.containing(posX, 0, posZ)).getY(), posZ, true, false);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
