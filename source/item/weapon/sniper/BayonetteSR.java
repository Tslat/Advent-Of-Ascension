package net.nevermine.item.weapon.sniper;

import com.google.common.collect.Multimap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.ArmorUtil;
import net.nevermine.assist.StringUtil;
import net.nevermine.item.weapon.gun.BaseGun;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.projectiles.gun.EntityMetalPellet;

import java.util.List;

public class BayonetteSR extends BaseGun implements SniperInstance {
	private final float rangedDmg = 55.0f;
	private final float meleeDmg = 11.0f;
	private float multiplier;

	public BayonetteSR(final int consumeChance, final String effect, final int uses, final int fireRate, final Item item) {
		super(consumeChance, effect, uses, fireRate, item);
		multiplier = 1.0f;
		setCreativeTab(Weaponizer.SnipersTab);
	}

	@Override
	public void fireGun(final World world, final ItemStack stack, final EntityPlayer player, final float multi, boolean consume) {
		if (ArmorUtil.isSharpshotArmor(player)) {
			multiplier = 1.25f;
		}
		else {
			multiplier = 1.0f;
		}
		player.worldObj.spawnEntityInWorld(new EntityMetalPellet(player.worldObj, player, rangedDmg * multi * multiplier, 0));
	}

	public Multimap getItemAttributeModifiers() {
		final Multimap multimap = super.getItemAttributeModifiers();
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(BayonetteSR.field_111210_e, "Weapon modifier", 11.0, 0));
		return multimap;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.damage.ranged", EnumChatFormatting.DARK_RED, Integer.toString((int)rangedDmg)));
		list.add(StringUtil.getLocaleString("items.description.speed.slow"));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.ammo", EnumChatFormatting.LIGHT_PURPLE, StringUtil.getLocaleString("item.MetalPellet.name")));
	}
}
