package net.nevermine.item.weapon.bow;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.projectiles.auxillary.EntityElementalArrow;

import java.util.List;

public class SunshineBow extends ItemBow {
	public static final int DEFAULT_MAX_USE_DURACTION = 72000;
	@SideOnly(Side.CLIENT)
	private IIcon[] IIconArray;
	String name;
	int damage;
	int maxUseDuraction;
	boolean unbreakable;
	Item arrow;

	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return Itemizer.IngotRosite == par2ItemStack.getItem() ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	public static final String[] texture = {"_0", "_1", "_2", "_3"};

	public SunshineBow(int uses, int damage, Item arrow) {
		this(uses, damage, 72000, arrow);
		setFull3D();
	}

	public SunshineBow(int uses, int damage, int maxUseDuraction, Item arrow) {
		setMaxDamage(uses);
		this.arrow = arrow;
		this.damage = damage;
		this.maxUseDuraction = maxUseDuraction;
		unbreakable = false;
		maxStackSize = 1;
		setCreativeTab(Weaponizer.BowsTab);
	}

	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.bow;
	}

	public void registerIcons(IIconRegister icon) {
		String prefix = "nevermine:animateditem/bowSunshine";
		itemIcon = icon.registerIcon(prefix + "_0");
		IIconArray = new IIcon[texture.length];
		for (int N = 0; N < 4; N++) {
			IIconArray[N] = icon.registerIcon(prefix + texture[N]);
		}
	}

	@SideOnly(Side.CLIENT)
	public IIcon getItemIconForUseDuration(int par1) {
		return IIconArray[par1];
	}

	public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
		if (player.getItemInUse() == null) {
			return itemIcon;
		}
		int Pulling = stack.getMaxItemUseDuration() - useRemaining;

		if (Pulling >= 18) {
			return IIconArray[3];
		}

		if (Pulling > 13) {
			return IIconArray[2];
		}

		if (Pulling > 0) {
			return IIconArray[1];
		}

		return IIconArray[0];
	}

	public void onPlayerStoppedUsing(ItemStack item, World par2World, EntityPlayer par3EntityPlayer, int par4) {
		int j = getMaxItemUseDuration(item) - par4;

		ArrowLooseEvent event = new ArrowLooseEvent(par3EntityPlayer, item, j);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.isCanceled())
			return;
		j = event.charge;

		boolean flag = (par3EntityPlayer.capabilities.isCreativeMode) || (EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, item) > 0);

		if ((flag) || (par3EntityPlayer.inventory.hasItem(arrow))) {
			float f = j / 23.0F;
			f = (f * f + f * 2.0F) / 3.0F;

			if (f < 0.1D) {
				return;
			}
			if (f > 1.0F) {
				f = 1.0F;
			}
			EntityArrow entityarrow = null;

			entityarrow = new EntityElementalArrow(par2World, par3EntityPlayer, f * 2.0F, 13, false);

			if (f == 1.0F) {
				entityarrow.setIsCritical(true);
			}
			int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, item);

			if (k > 0) {
				entityarrow.setDamage(entityarrow.getDamage() + k * 0.5D + 0.5D);
			}
			int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, item);

			if (l > 0) {
				entityarrow.setKnockbackStrength(l);
			}
			if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, item) > 0) {
				entityarrow.setFire(100);
			}
			if (!unbreakable)
				item.damageItem(1, par3EntityPlayer);
			if (sound(item, par2World, par3EntityPlayer) != null) {
				par2World.playSoundAtEntity(par3EntityPlayer, sound(item, par2World, par3EntityPlayer), 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
			}
			if (flag) {
				entityarrow.canBePickedUp = 2;
			}
			else {
				par3EntityPlayer.inventory.consumeInventoryItem(arrow);
			}
			if (!par2World.isRemote)
				par2World.spawnEntityInWorld(entityarrow);
		}
	}

	public EntityArrow spawnArrow(ItemStack stack, World world, EntityPlayer player, float time) {
		return new EntityArrow(world, player, time);
	}

	public String sound(ItemStack stack, World world, EntityPlayer player) {
		return "random.bow";
	}

	public int getMaxItemUseDuration(ItemStack item) {
		return maxUseDuraction;
	}

	public boolean isItemTool(ItemStack item) {
		return true;
	}

	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.damage.rangedMax", EnumChatFormatting.DARK_RED, Integer.toString(damage * 2 + 4)));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.ammo", EnumChatFormatting.LIGHT_PURPLE, StringUtil.getLocaleString("item.ElementalArrow.name")));

		if (unbreakable)
			list.add(StringUtil.getLocaleString("items.description.unbreakable"));
	}

	public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
		ArrowNockEvent var4 = new ArrowNockEvent(var3, var1);
		MinecraftForge.EVENT_BUS.post(var4);

		if (var4.isCanceled()) {
			return var4.result;
		}
		if ((var3.capabilities.isCreativeMode) || (var3.inventory.hasItem(getItem()))) {
			var3.setItemInUse(var1, getMaxItemUseDuration(var1));
		}

		return var1;
	}

	public Item getItem() {
		return Itemizer.ElementalArrow;
	}

	public Item setTextureName(String par1Str) {
		iconString = ("nevermine:" + par1Str);
		return this;
	}

	public Item setName(final String name) {
		setUnlocalizedName(this.name = name);
		register();
		return this;
	}

	public void register() {
		int numChars = 0;
		char firstLetter = name.charAt(0);
		if (Character.isLowerCase(firstLetter)) {
			firstLetter = Character.toUpperCase(firstLetter);
		}
		String inGame = name.substring(1);
		for (int k = 0; k < name.length(); ++k) {
			final int code;
			final char c = (char)(code = name.charAt(k));
			if (k != 0) {
				for (int p = 65; p < 90; ++p) {
					if (code == p) {
						if (++numChars == 1) {
							inGame = new StringBuffer(inGame).insert(k - 1, " ").toString();
						}
						else {
							inGame = new StringBuffer(inGame).insert(k, " ").toString();
						}
					}
				}
			}
		}
		final String finalName = firstLetter + inGame;
	}
}
