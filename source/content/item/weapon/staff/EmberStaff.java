package net.tslat.aoa3.content.item.weapon.staff;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.tme.api.util.EntityRetrievalUtil;

import java.util.List;
import java.util.Optional;

public class EmberStaff extends AoAStaff<Pair<List<BlockPos>, List<Entity>>> {
	public EmberStaff(Item.Properties properties) {
		super(properties);
	}

	@Override
	public Optional<Pair<List<BlockPos>, List<Entity>>> checkPreconditions(LivingEntity caster, ItemStack staff) {
		List<BlockPos> blocks = new ObjectArrayList<>();
		List<Entity> entities = new ObjectArrayList<>();

		if (caster.isOnFire())
			entities.add(caster);

		entities.addAll(EntityRetrievalUtil.getEntities(caster, 5, entity -> entity.getRemainingFireTicks() > 0 && !(entity instanceof Enemy)));
		blocks.addAll(WorldUtil.getBlocksWithinAABB(caster.level(), new AABB(Vec3.atLowerCornerOf(caster.blockPosition().offset(-5, -5, -5)), Vec3.atBottomCenterOf(caster.blockPosition().offset(5, 5, 5))), (state, pos) -> WorldUtil.canModifyBlock(caster.level(), pos, caster, staff) && state.is(BlockTags.FIRE)));

		return Optional.ofNullable(entities.isEmpty() && blocks.isEmpty() ? null : Pair.of(blocks, entities));
	}

	@Override
	public void cast(ServerLevel level, LivingEntity caster, ItemStack staff, InteractionHand hand, Pair<List<BlockPos>, List<Entity>> args) {
		for (Entity entity : args.getSecond()) {
			entity.clearFire();
		}

		for (BlockPos pos : args.getFirst()) {
			level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
