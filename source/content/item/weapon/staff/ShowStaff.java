package net.tslat.aoa3.content.item.weapon.staff;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.effectslib.api.util.EffectBuilder;
import net.tslat.smartbrainlib.util.EntityRetrievalUtil;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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
	protected void populateRunes(HashMap<Item, Integer> runes) {
		runes.put(AoAItems.COMPASS_RUNE.get(), 3);
		runes.put(AoAItems.POWER_RUNE.get(), 3);
	}

	@Override
	public Optional<List<LivingEntity>> checkPreconditions(LivingEntity caster, ItemStack staff) {
		List<LivingEntity> targets = EntityRetrievalUtil.getEntities(caster, 30, entity -> entity instanceof LivingEntity livingEntity && EntityUtil.Predicates.HOSTILE_MOB.test(livingEntity));

		return Optional.ofNullable(targets.isEmpty() ? null : targets);
	}

	@Override
	public void cast(Level world, ItemStack staff, LivingEntity caster, List<LivingEntity> args) {
		for (LivingEntity entity : args) {
			entity.setSecondsOnFire(5);
			EntityUtil.applyPotions(entity, new EffectBuilder(MobEffects.GLOWING, 100));
			world.addFreshEntity(new FireworkRocketEntity(world, entity.getX(), entity.getBoundingBox().maxY, entity.getZ(), makeFireworksStack()));
		}
	}

	private ItemStack makeFireworksStack() {
		ItemStack fireworks = new ItemStack(Items.FIREWORK_ROCKET, 1);
		CompoundTag explosionTag = new CompoundTag();
		CompoundTag fireworksTag = new CompoundTag();
		CompoundTag finalTag = new CompoundTag();
		CompoundTag wrapperTag = new CompoundTag();
		ListTag finalTagList = new ListTag();

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
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
