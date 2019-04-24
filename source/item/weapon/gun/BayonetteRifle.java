package net.nevermine.item.weapon.gun;

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
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.projectiles.gun.EntityMetalPellet;

import java.util.List;

public class BayonetteRifle extends BaseGun {
	private final float rangedDmg = 13.0f;
	private final float meleeDmg = 11.0f;

	public BayonetteRifle(final int consumeChance, final String effect, final int uses, final int fireRate, final Item item) {
		super(consumeChance, effect, uses, fireRate, item);
		setCreativeTab(Weaponizer.WeaponsTab);
	}

	@Override
	public void fireGun(final World world, final ItemStack stack, final EntityPlayer player, final float multi, boolean consume) {
		player.worldObj.spawnEntityInWorld(new EntityMetalPellet(player.worldObj, player, rangedDmg * multi, 0));
	}

	public Multimap getItemAttributeModifiers() {
		final Multimap multimap = super.getItemAttributeModifiers();
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(BayonetteRifle.field_111210_e, "Weapon modifier", meleeDmg, 0));
		return multimap;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.damage.ranged", EnumChatFormatting.DARK_RED, Float.toString(rangedDmg)));
		list.add(StringUtil.getLocaleString("items.description.speed.slow"));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.ammo", EnumChatFormatting.LIGHT_PURPLE, StringUtil.getLocaleString("item.MetalPellet.name")));
	}
}
