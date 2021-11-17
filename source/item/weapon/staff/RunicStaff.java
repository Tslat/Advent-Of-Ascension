package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class RunicStaff extends BaseStaff<EnderChestInventory> {
	public RunicStaff(int durability) {
		super(durability);
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return AoASounds.ITEM_RUNIC_STAFF_CAST.get();
	}

	@Override
	protected void populateRunes(HashMap<Item, Integer> runes) {
		runes.put(AoAItems.DISTORTION_RUNE.get(), 5);
	}

	@Override
	public EnderChestInventory checkPreconditions(LivingEntity caster, ItemStack staff) {
		return caster instanceof PlayerEntity ? ((PlayerEntity)caster).getEnderChestInventory() : null;
	}

	@Override
	public void cast(World world, ItemStack staff, LivingEntity caster, EnderChestInventory args) {
		((PlayerEntity)caster).openMenu(new SimpleNamedContainerProvider((id, player, inventory) -> ChestContainer.threeRows(id, player, args), new TranslationTextComponent("container.enderchest")));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
