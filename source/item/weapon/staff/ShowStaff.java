package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PotionUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class ShowStaff extends BaseStaff<List<LivingEntity>> {
	public ShowStaff(int durability) {
		super(durability);
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return AoASounds.ITEM_SHOW_STAFF_CAST.get();
	}

	@Override
	protected void populateRunes(HashMap<RuneItem, Integer> runes) {
		runes.put(AoAItems.COMPASS_RUNE.get(), 3);
		runes.put(AoAItems.POWER_RUNE.get(), 3);
	}

	@Override
	public List<LivingEntity> checkPreconditions(LivingEntity caster, ItemStack staff) {
		List<LivingEntity> list = caster.level.getEntitiesOfClass(LivingEntity.class, caster.getBoundingBox().inflate(30), EntityUtil.Predicates.HOSTILE_MOB);

		if (!list.isEmpty())
			return list;

		return null;
	}

	@Override
	public void cast(World world, ItemStack staff, LivingEntity caster, List<LivingEntity> args) {
		for (LivingEntity entity : args) {
			entity.setSecondsOnFire(5);
			EntityUtil.applyPotions(entity, new PotionUtil.EffectBuilder(Effects.GLOWING, 100));
			world.addFreshEntity(new FireworkRocketEntity(world, entity.getX(), entity.getBoundingBox().maxY, entity.getZ(), makeFireworksStack()));
		}
	}

	private ItemStack makeFireworksStack() {
		ItemStack fireworks = new ItemStack(Items.FIREWORK_ROCKET, 1);
		CompoundNBT explosionTag = new CompoundNBT();
		CompoundNBT fireworksTag = new CompoundNBT();
		CompoundNBT finalTag = new CompoundNBT();
		CompoundNBT wrapperTag = new CompoundNBT();
		ListNBT finalTagList = new ListNBT();

		explosionTag.putBoolean("Trail", true);
		explosionTag.putIntArray("Colors", new int[] {0});
		explosionTag.putByte("Type", (byte)4);
		fireworksTag.put("Explosion", explosionTag);
		finalTagList.add(fireworksTag);
		finalTag.put("Explosions", finalTagList);
		finalTag.putByte("Flight", (byte)3);
		wrapperTag.put("Fireworks", finalTag);
		fireworks.setTag(wrapperTag);

		return fireworks;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
