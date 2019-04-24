package net.nevermine.block.functional;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.nevermine.assist.AscensionEnchants;
import net.nevermine.container.PlayerContainer;
import net.nevermine.item.weapon.energy.BaseEnergy;
import net.nevermine.item.weapon.energy.BaseEnergyRapid;
import net.nevermine.item.weapon.greatblade.BaseGreatblade;
import net.nevermine.item.weapon.gun.BaseGun;
import net.nevermine.item.weapon.scythe.BaseScythe;
import net.nevermine.item.weapon.staff.BaseStaff;
import net.nevermine.izer.Blockizer;
import net.nevermine.izer.Itemizer;
import net.nevermine.skill.infusion.infusionHelper;

import java.util.Random;

import static net.nevermine.container.PlayerContainer.Skills.Infusion;
import static net.nevermine.skill.infusion.infusionHelper.InfusionEnchant.*;

public class InfusionTable extends Block {
	private static Block.SoundType rck = Block.soundTypeStone;
	@SideOnly(Side.CLIENT)
	private IIcon top;
	@SideOnly(Side.CLIENT)
	private IIcon side;
	@SideOnly(Side.CLIENT)
	private IIcon bottom;
	private Random rand;

	public InfusionTable(final Material p_i45394_1_) {
		super(p_i45394_1_);
		rand = new Random();
		setCreativeTab(Itemizer.FurnitureTab);
		setHardness(5.0f);
		setResistance(0.5f);
		setSoundType(InfusionTable.rck);
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister icon) {
		top = icon.registerIcon("nevermine:infusionTable_top");
		side = icon.registerIcon("nevermine:infusionTable_side");
		bottom = icon.registerIcon("nevermine:infusionTable_bottom");
		blockIcon = icon.registerIcon("nevermine:infusionTable_side");
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(final int p_149691_1_, final int p_149691_2_) {
		return (p_149691_1_ == 0) ? bottom : ((p_149691_1_ == 1) ? top : blockIcon);
	}

	private enum InfusionType {
		Damage,
		Durability,
		Weight,
		Speed,
		Resistance,
		Magical
	}

	public InfusionType getInfusionType(final World w, final int x, final int y, final int z) {
		final Block right = w.getBlock(x + 3, y + 1, z);
		Block left;
		Block front;
		Block back;

		switch (right.getUnlocalizedName()) {
			case "tile.enhancerDamage":
				left = w.getBlock(x - 3, y + 1, z);
				front = w.getBlock(x, y + 1, z + 3);
				back = w.getBlock(x, y + 1, z - 3);

				if (left == Blockizer.EnhancerDamage && front == Blockizer.EnhancerDamage && back == Blockizer.EnhancerDamage)
					return InfusionType.Damage;
				break;
			case "tile.enhancerDurability":
				left = w.getBlock(x - 3, y + 1, z);
				front = w.getBlock(x, y + 1, z + 3);
				back = w.getBlock(x, y + 1, z - 3);

				if (left == Blockizer.EnhancerDurability && front == Blockizer.EnhancerDurability && back == Blockizer.EnhancerDurability)
					return InfusionType.Durability;
				break;
			case "tile.enhancerWeight":
				left = w.getBlock(x - 3, y + 1, z);
				front = w.getBlock(x, y + 1, z + 3);
				back = w.getBlock(x, y + 1, z - 3);

				if (left == Blockizer.EnhancerWeight && front == Blockizer.EnhancerWeight && back == Blockizer.EnhancerWeight)
					return InfusionType.Weight;
				break;
			case "tile.enhancerSpeed":
				left = w.getBlock(x - 3, y + 1, z);
				front = w.getBlock(x, y + 1, z + 3);
				back = w.getBlock(x, y + 1, z - 3);

				if (left == Blockizer.EnhancerSpeed && front == Blockizer.EnhancerSpeed && back == Blockizer.EnhancerSpeed)
					return InfusionType.Speed;
				break;
			case "tile.enhancerResistance":
				left = w.getBlock(x - 3, y + 1, z);
				front = w.getBlock(x, y + 1, z + 3);
				back = w.getBlock(x, y + 1, z - 3);

				if (left == Blockizer.EnhancerResistance && front == Blockizer.EnhancerResistance && back == Blockizer.EnhancerResistance)
					return InfusionType.Resistance;
				break;
			case "tile.enhancerMagical":
				left = w.getBlock(x - 3, y + 1, z);
				front = w.getBlock(x, y + 1, z + 3);
				back = w.getBlock(x, y + 1, z - 3);

				if (left == Blockizer.EnhancerMagical && front == Blockizer.EnhancerMagical && back == Blockizer.EnhancerMagical)
					return InfusionType.Magical;
				break;
			default:
				break;
		}

		return null;
	}

	public boolean onBlockActivated(final World w, final int x, final int y, final int z, final EntityPlayer p, final int var6, final float var7, final float var8, final float var9) {
		if (w.isRemote || p.isSneaking() || p.getHeldItem() == null)
			return true;

		PlayerContainer cont = PlayerContainer.getProperties(p);
		boolean complete = false;
		Item item = p.getHeldItem().getItem();

		switch (item.getUnlocalizedName()) {
			case "item.iStoneGlistening":
				if (cont.getLevel(Infusion) >= 5 && p.inventory.consumeInventoryItem(Itemizer.iStoneGlistening)) {
					cont.addExperience(8.0f, Infusion);

					if (rand.nextInt(200) == 0 && !p.inventory.addItemStackToInventory(new ItemStack(Itemizer.pStoneGlistening)))
						p.dropItem(Itemizer.pStoneGlistening, 1);

					complete = true;
				}
				break;
			case "item.iStoneGleaming":
				if (cont.getLevel(Infusion) >= 15 && p.inventory.consumeInventoryItem(Itemizer.iStoneGleaming)) {
					cont.addExperience(16.0f, Infusion);

					if (rand.nextInt(200) == 0 && !p.inventory.addItemStackToInventory(new ItemStack(Itemizer.pStoneGleaming)))
						p.dropItem(Itemizer.pStoneGleaming, 1);

					complete = true;
				}
				break;
			case "item.iStoneAmbient":
				if (cont.getLevel(Infusion) >= 20 && p.inventory.consumeInventoryItem(Itemizer.iStoneAmbient)) {
					cont.addExperience(20.0f, Infusion);

					if (rand.nextInt(35) == 0 && !p.inventory.addItemStackToInventory(new ItemStack(Itemizer.pStoneAmbient)))
						p.dropItem(Itemizer.pStoneAmbient, 1);

					complete = true;
				}
				break;
			case "item.iStoneGlaring":
				if (cont.getLevel(Infusion) >= 30 && p.inventory.consumeInventoryItem(Itemizer.iStoneGlaring)) {
					cont.addExperience(40.0f, Infusion);

					if (rand.nextInt(200) == 0 && !p.inventory.addItemStackToInventory(new ItemStack(Itemizer.pStoneGlaring)))
						p.dropItem(Itemizer.pStoneGlaring, 1);

					complete = true;
				}
				break;
			case "item.iStoneGlowing":
				if (cont.getLevel(Infusion) >= 45 && p.inventory.consumeInventoryItem(Itemizer.iStoneGlowing)) {
					cont.addExperience(85.0f, Infusion);

					if (rand.nextInt(200) == 0 && !p.inventory.addItemStackToInventory(new ItemStack(Itemizer.pStoneGlowing)))
						p.dropItem(Itemizer.pStoneGlowing, 1);

					complete = true;
				}
				break;
			case "item.iStoneShining":
				if (cont.getLevel(Infusion) >= 60 && p.inventory.consumeInventoryItem(Itemizer.iStoneShining)) {
					cont.addExperience(150.0f, Infusion);

					if (rand.nextInt(200) == 0 && !p.inventory.addItemStackToInventory(new ItemStack(Itemizer.pStoneShining)))
						p.dropItem(Itemizer.pStoneShining, 1);

					complete = true;
				}
				break;
			case "item.iStoneRadiant":
				if (cont.getLevel(Infusion) >= 70 && p.inventory.consumeInventoryItem(Itemizer.iStoneRadiant)) {
					cont.addExperience(220.0f, Infusion);

					if (rand.nextInt(200) == 0 && !p.inventory.addItemStackToInventory(new ItemStack(Itemizer.pStoneRadiant)))
						p.dropItem(Itemizer.pStoneRadiant, 1);

					complete = true;
				}
				break;
			case "item.iStoneBlooming":
				if (cont.getLevel(Infusion) >= 80 && p.inventory.consumeInventoryItem(Itemizer.iStoneBlooming)) {
					cont.addExperience(300.0f, Infusion);

					if (rand.nextInt(200) == 0 && !p.inventory.addItemStackToInventory(new ItemStack(Itemizer.pStoneBlooming)))
						p.dropItem(Itemizer.pStoneBlooming, 1);

					complete = true;
				}
				break;
			default:
				break;
		}

		if (complete) {
			if (p instanceof EntityPlayerMP)
				((EntityPlayerMP)p).sendContainerToPlayer(p.inventoryContainer);

			w.playSoundAtEntity(p, "nevermine:Infusion", 1.85f, 1.0f);
			return true;
		}

		InfusionType type = getInfusionType(w, x, y, z);

		if (type == null) {
			w.playSoundAtEntity(p, "nevermine:InfusionNoPower", 1.85f, 1.0f);
			return true;
		}

		if (item instanceof ItemSword && !(item instanceof BaseEnergyRapid)) {
			if (type == InfusionType.Damage && EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, p.getHeldItem()) == 0 && p.inventory.consumeInventoryItem(Itemizer.pStoneGlistening)) {
				p.getHeldItem().addEnchantment(Enchantment.sharpness, infusionHelper.getInfusionEnchantLevel(Sharpness, cont.getLevel(Infusion)));
				complete = true;
			}

			if (type == InfusionType.Weight && EnchantmentHelper.getEnchantmentLevel(Enchantment.knockback.effectId, p.getHeldItem()) == 0 && p.inventory.consumeInventoryItem(Itemizer.pStoneGlaring)) {
				p.getHeldItem().addEnchantment(Enchantment.knockback, infusionHelper.getInfusionEnchantLevel(Knockback, cont.getLevel(Infusion)));
				complete = true;
			}

			if (type == InfusionType.Durability && EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, p.getHeldItem()) == 0 && p.inventory.consumeInventoryItem(Itemizer.pStoneBlooming)) {
				p.getHeldItem().addEnchantment(Enchantment.unbreaking, infusionHelper.getInfusionEnchantLevel(Unbreaking, cont.getLevel(Infusion)));
				complete = true;
			}
		}
		else if (item == Items.fishing_rod) {
			if (type == InfusionType.Durability && EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, p.getHeldItem()) == 0 && p.inventory.consumeInventoryItem(Itemizer.pStoneBlooming)) {
				p.getHeldItem().addEnchantment(Enchantment.unbreaking, infusionHelper.getInfusionEnchantLevel(Unbreaking, cont.getLevel(Infusion)));
				complete = true;
			}

			if (type == InfusionType.Magical && EnchantmentHelper.getEnchantmentLevel(Enchantment.field_151370_z.effectId, p.getHeldItem()) == 0 && p.inventory.consumeInventoryItem(Itemizer.pStoneRadiant)) {
				p.getHeldItem().addEnchantment(Enchantment.field_151370_z, infusionHelper.getInfusionEnchantLevel(SeaLuck, cont.getLevel(Infusion)));
				complete = true;
			}

			if (type == InfusionType.Magical && EnchantmentHelper.getEnchantmentLevel(Enchantment.field_151369_A.effectId, p.getHeldItem()) == 0 && p.inventory.consumeInventoryItem(Itemizer.pStoneGlowing)) {
				p.getHeldItem().addEnchantment(Enchantment.field_151369_A, infusionHelper.getInfusionEnchantLevel(Lure, cont.getLevel(Infusion)));
				complete = true;
			}
		}
		else if (item instanceof ItemBow) {
			if (type == InfusionType.Durability && EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, p.getHeldItem()) == 0 && p.inventory.consumeInventoryItem(Itemizer.pStoneBlooming)) {
				p.getHeldItem().addEnchantment(Enchantment.unbreaking, infusionHelper.getInfusionEnchantLevel(Unbreaking, cont.getLevel(Infusion)));
				complete = true;
			}

			if (type == InfusionType.Weight && EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, p.getHeldItem()) == 0 && p.inventory.consumeInventoryItem(Itemizer.pStoneGlaring)) {
				p.getHeldItem().addEnchantment(Enchantment.punch, infusionHelper.getInfusionEnchantLevel(Punch, cont.getLevel(Infusion)));
				complete = true;
			}

			if (type == InfusionType.Damage && EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, p.getHeldItem()) == 0 && p.inventory.consumeInventoryItem(Itemizer.pStoneGlistening)) {
				p.getHeldItem().addEnchantment(Enchantment.power, infusionHelper.getInfusionEnchantLevel(Power, cont.getLevel(Infusion)));
				complete = true;
			}
		}
		else if (item instanceof ItemTool) {
			if (type == InfusionType.Durability && EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, p.getHeldItem()) == 0 && p.inventory.consumeInventoryItem(Itemizer.pStoneBlooming)) {
				p.getHeldItem().addEnchantment(Enchantment.unbreaking, infusionHelper.getInfusionEnchantLevel(Unbreaking, cont.getLevel(Infusion)));
				complete = true;
			}

			if (type == InfusionType.Speed && EnchantmentHelper.getEnchantmentLevel(Enchantment.efficiency.effectId, p.getHeldItem()) == 0 && p.inventory.consumeInventoryItem(Itemizer.pStoneGlowing)) {
				p.getHeldItem().addEnchantment(Enchantment.efficiency, infusionHelper.getInfusionEnchantLevel(Efficiency, cont.getLevel(Infusion)));
				complete = true;
			}
		}
		else if (item instanceof ItemArmor) {
			if (type == InfusionType.Durability && EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, p.getHeldItem()) == 0 && p.inventory.consumeInventoryItem(Itemizer.pStoneBlooming)) {
				p.getHeldItem().addEnchantment(Enchantment.unbreaking, infusionHelper.getInfusionEnchantLevel(Unbreaking, cont.getLevel(Infusion)));
				complete = true;
			}

			if (type == InfusionType.Resistance && EnchantmentHelper.getEnchantmentLevel(Enchantment.protection.effectId, p.getHeldItem()) == 0 && p.inventory.consumeInventoryItem(Itemizer.pStoneShining)) {
				p.getHeldItem().addEnchantment(Enchantment.protection, infusionHelper.getInfusionEnchantLevel(Protection, cont.getLevel(Infusion)));
				complete = true;
			}
		}
		else if (item instanceof BaseGun) {
			if (type == InfusionType.Durability && EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, p.getHeldItem()) == 0 && p.inventory.consumeInventoryItem(Itemizer.pStoneBlooming)) {
				p.getHeldItem().addEnchantment(Enchantment.unbreaking, infusionHelper.getInfusionEnchantLevel(Unbreaking, cont.getLevel(Infusion)));
				complete = true;
			}

			if (type == InfusionType.Damage && EnchantmentHelper.getEnchantmentLevel(AscensionEnchants.Shell.effectId, p.getHeldItem()) == 0 && p.inventory.consumeInventoryItem(Itemizer.pStoneGlaring)) {
				p.getHeldItem().addEnchantment(AscensionEnchants.Shell, infusionHelper.getInfusionEnchantLevel(Shell, cont.getLevel(Infusion)));
				complete = true;
			}

			if (type == InfusionType.Weight && EnchantmentHelper.getEnchantmentLevel(AscensionEnchants.Control.effectId, p.getHeldItem()) == 0 && p.inventory.consumeInventoryItem(Itemizer.pStoneRadiant)) {
				p.getHeldItem().addEnchantment(AscensionEnchants.Control, infusionHelper.getInfusionEnchantLevel(Control, cont.getLevel(Infusion)));
				complete = true;
			}
		}
		else if (item instanceof BaseEnergy || item instanceof BaseEnergyRapid) {
			if (type == InfusionType.Durability && EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, p.getHeldItem()) == 0 && p.inventory.consumeInventoryItem(Itemizer.pStoneBlooming)) {
				p.getHeldItem().addEnchantment(Enchantment.unbreaking, infusionHelper.getInfusionEnchantLevel(Unbreaking, cont.getLevel(Infusion)));
				complete = true;
			}

			if (type == InfusionType.Speed && EnchantmentHelper.getEnchantmentLevel(AscensionEnchants.Recharge.effectId, p.getHeldItem()) == 0 && p.inventory.consumeInventoryItem(Itemizer.pStoneRadiant)) {
				p.getHeldItem().addEnchantment(AscensionEnchants.Recharge, infusionHelper.getInfusionEnchantLevel(Recharge, cont.getLevel(Infusion)));
				complete = true;
			}

			if (type == InfusionType.Damage && EnchantmentHelper.getEnchantmentLevel(AscensionEnchants.Overpower.effectId, p.getHeldItem()) == 0 && p.inventory.consumeInventoryItem(Itemizer.pStoneGlistening)) {
				p.getHeldItem().addEnchantment(AscensionEnchants.Overpower, infusionHelper.getInfusionEnchantLevel(Overpower, cont.getLevel(Infusion)));
				complete = true;
			}
		}
		else if (item instanceof BaseGreatblade) {
			if (type == InfusionType.Durability && EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, p.getHeldItem()) == 0 && p.inventory.consumeInventoryItem(Itemizer.pStoneBlooming)) {
				p.getHeldItem().addEnchantment(Enchantment.unbreaking, infusionHelper.getInfusionEnchantLevel(Unbreaking, cont.getLevel(Infusion)));
				complete = true;
			}

			if (type == InfusionType.Damage && EnchantmentHelper.getEnchantmentLevel(AscensionEnchants.Sever.effectId, p.getHeldItem()) == 0 && p.inventory.consumeInventoryItem(Itemizer.pStoneGlaring)) {
				p.getHeldItem().addEnchantment(AscensionEnchants.Sever, infusionHelper.getInfusionEnchantLevel(Sever, cont.getLevel(Infusion)));
				complete = true;
			}

			if (type == InfusionType.Weight && EnchantmentHelper.getEnchantmentLevel(AscensionEnchants.Crush.effectId, p.getHeldItem()) == 0 && p.inventory.consumeInventoryItem(Itemizer.pStoneRadiant)) {
				p.getHeldItem().addEnchantment(AscensionEnchants.Crush, infusionHelper.getInfusionEnchantLevel(Crush, cont.getLevel(Infusion)));
				complete = true;
			}
		}
		else if (item instanceof BaseScythe) {
			if (type == InfusionType.Durability && EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, p.getHeldItem()) == 0 && p.inventory.consumeInventoryItem(Itemizer.pStoneBlooming)) {
				p.getHeldItem().addEnchantment(Enchantment.unbreaking, infusionHelper.getInfusionEnchantLevel(Unbreaking, cont.getLevel(Infusion)));
				complete = true;
			}

			if (type == InfusionType.Damage && EnchantmentHelper.getEnchantmentLevel(AscensionEnchants.Slice.effectId, p.getHeldItem()) == 0 && p.inventory.consumeInventoryItem(Itemizer.pStoneGlaring)) {
				p.getHeldItem().addEnchantment(AscensionEnchants.Slice, infusionHelper.getInfusionEnchantLevel(Slice, cont.getLevel(Infusion)));
				complete = true;
			}

			if (type == InfusionType.Speed && EnchantmentHelper.getEnchantmentLevel(AscensionEnchants.Windfury.effectId, p.getHeldItem()) == 0 && p.inventory.consumeInventoryItem(Itemizer.pStoneRadiant)) {
				p.getHeldItem().addEnchantment(AscensionEnchants.Windfury, infusionHelper.getInfusionEnchantLevel(Windfury, cont.getLevel(Infusion)));
				complete = true;
			}
		}
		else if (item instanceof BaseStaff) {
			if (type == InfusionType.Durability && EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, p.getHeldItem()) == 0 && p.inventory.consumeInventoryItem(Itemizer.pStoneBlooming)) {
				p.getHeldItem().addEnchantment(Enchantment.unbreaking, infusionHelper.getInfusionEnchantLevel(Unbreaking, cont.getLevel(Infusion)));
				complete = true;
			}

			if (type == InfusionType.Magical && EnchantmentHelper.getEnchantmentLevel(AscensionEnchants.Archmage.effectId, p.getHeldItem()) == 0 && p.inventory.consumeInventoryItem(Itemizer.pStoneRadiant)) {
				int tier = infusionHelper.getInfusionEnchantLevel(Archmage, cont.getLevel(Infusion));

				if (tier > 0) {
					p.getHeldItem().addEnchantment(AscensionEnchants.Archmage, tier);
					complete = true;
				}
			}
		}

		if (complete)
			w.playSoundAtEntity(p, "nevermine:Infusion", 1.85f, 1.0f);

		if (p instanceof EntityPlayerMP)
			((EntityPlayerMP)p).sendContainerToPlayer(p.inventoryContainer);

		return true;
	}

	public Block setSoundType(final Block.SoundType name) {
		return setStepSound(name);
	}
}
