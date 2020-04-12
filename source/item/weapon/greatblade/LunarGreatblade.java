package net.tslat.aoa3.item.weapon.greatblade;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.LongReachWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.library.misc.AoAAttributes;
import net.tslat.aoa3.utils.ItemUtil;

import java.util.List;

public class LunarGreatblade extends BaseGreatblade {
	private final double baseDmg;
	private final double maxDmg;

	public LunarGreatblade(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		setTranslationKey("LunarGreatblade");
		setRegistryName("aoa3:lunar_greatblade");

		this.baseDmg = dmg - (dmg / 2d);
		this.maxDmg = dmg + (dmg / 2d);
	}

	@Override
	protected double getDamageForAttack(ItemStack stack, Entity target, EntityLivingBase attacker, double baseDmg) {
		return (float)(itemRand.nextFloat() * (maxDmg - baseDmg) + baseDmg);
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot equipmentSlot, ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();

		if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), AoAAttributes.vanillaWeaponSpeedModifier(speed));

		return multimap;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("items.description.damage.random", Enums.ItemDescriptionType.ITEM_DAMAGE, Double.toString(baseDmg), Double.toString(maxDmg)));
	}
}
