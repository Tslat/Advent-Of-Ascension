package net.tslat.aoa3.object.item.weapon.staff;

import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class EmberStaff extends BaseStaff<Object> {
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

	@Override
	public void cast(World world, ItemStack staff, LivingEntity caster, Object args) {
		caster.clearFire();

		for (LivingEntity entity : world.getEntitiesOfClass(LivingEntity.class, caster.getBoundingBox().inflate(5), entity -> entity instanceof PlayerEntity || entity instanceof TameableEntity)) {
			entity.clearFire();
		}

		WorldUtil.operateOnMultipleBlocksInRange(world, caster.blockPosition(), 5, state -> state.getBlock() == Blocks.FIRE, pos -> world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState()));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
