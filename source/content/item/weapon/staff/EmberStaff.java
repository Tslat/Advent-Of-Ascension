package net.tslat.aoa3.content.item.weapon.staff;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.smartbrainlib.util.EntityRetrievalUtil;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;

public class EmberStaff extends BaseStaff<Pair<List<BlockPos>, List<Entity>>> {
	public EmberStaff(int durability) {
		super(durability);
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return AoASounds.ITEM_EMBER_STAFF_CAST.get();
	}

	@Override
	protected void populateRunes(HashMap<Item, Integer> runes) {
		runes.put(AoAItems.KINETIC_RUNE.get(), 1);
		runes.put(AoAItems.WIND_RUNE.get(), 1);
		runes.put(AoAItems.FIRE_RUNE.get(), 1);
	}

	@Nullable
	@Override
	public Pair<List<BlockPos>, List<Entity>> checkPreconditions(LivingEntity caster, ItemStack staff) {
		List<BlockPos> blocks = new ObjectArrayList<>();
		List<Entity> entities = new ObjectArrayList<>();

		if (caster.isOnFire())
			entities.add(caster);

		entities.addAll(EntityRetrievalUtil.getEntities(caster, 5, entity -> entity.getRemainingFireTicks() > 0 && !(entity instanceof Enemy)));
		blocks.addAll(WorldUtil.getBlocksWithinAABB(caster.level(), new AABB(Vec3.atLowerCornerOf(caster.blockPosition().offset(-5, -5, -5)), Vec3.atBottomCenterOf(caster.blockPosition().offset(5, 5, 5))), (state, pos) -> WorldUtil.canModifyBlock(caster.level(), pos, caster, staff) && state.is(BlockTags.FIRE)));

		return entities.isEmpty() && blocks.isEmpty() ? null : Pair.of(blocks, entities);
	}

	@Override
	public void cast(Level world, ItemStack staff, LivingEntity caster, Pair<List<BlockPos>, List<Entity>> args) {
		for (Entity entity : args.getSecond()) {
			entity.clearFire();
		}

		for (BlockPos pos : args.getFirst()) {
			world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
