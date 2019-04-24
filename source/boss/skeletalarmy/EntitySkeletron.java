package net.nevermine.boss.skeletalarmy;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.boss.EntityBoss;
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.izer.equipment.Armorizer;
import net.nevermine.izer.equipment.Weaponizer;

import java.util.List;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntitySkeletron extends EntityMob implements EntityBoss {
	public EntitySkeletron(final World par1World) {
		super(par1World);
		setSize(1.2f, 1.5f);
	}

	protected String getLivingSound() {
		return "nevermine:SkeletronLiving";
	}

	protected String getDeathSound() {
		return "nevermine:SkeletronDeath";
	}

	protected String getHurtSound() {
		return "nevermine:SkeletronHit";
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(dropStatue(), 1);

		final int wep = rand.nextInt(4);

		if (wep == 1) {
			dropItem(Weaponizer.SkeletalSword, 1);
		}
		else if (wep == 2) {
			dropItem(Weaponizer.SkeletalBow, 1);
		}
		else if (wep == 3) {
			dropItem(Weaponizer.LightningStaff, 1);
		}
		else {
			dropItem(Weaponizer.BoneBlaster, 1);
		}

		final int armorpiece = rand.nextInt(4);

		if (armorpiece == 1) {
			dropItem(Armorizer.CommanderBoots, 1);
		}
		else if (armorpiece == 2) {
			dropItem(Armorizer.CommanderLeggings, 1);
		}
		else if (armorpiece == 3) {
			dropItem(Armorizer.CommanderChestplate, 1);
		}
		else {
			dropItem(Armorizer.CommanderHelmet, 1);
		}

	}

	private Item dropStatue() {
		if (rand.nextInt(15) == 3) {
			return Item.getItemFromBlock(SpecialBlockizer.SkeletorStatue);
		}

		Item drop = null;
		final int statuedrop = rand.nextInt(4);

		if (statuedrop == 0) {
			drop = Item.getItemFromBlock(SpecialBlockizer.SkeleHopperStatue);
		}
		if (statuedrop == 1) {
			drop = Item.getItemFromBlock(SpecialBlockizer.SkeleelderStatue);
		}
		if (statuedrop == 2) {
			drop = Item.getItemFromBlock(SpecialBlockizer.SkelepigStatue);
		}
		if (statuedrop == 3) {
			drop = Item.getItemFromBlock(SpecialBlockizer.SkelemanStatue);
		}

		return drop;
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("nevermine:HeavyStep", 0.85f, 1.0f);
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(70.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1100.0);
	}

	public void onDeath(final DamageSource d) {
		super.onDeath(d);

		if (!worldObj.isRemote && d.getEntity() != null && d.getEntity() instanceof EntityPlayer) {
			final EntityPlayer p = (EntityPlayer)d.getEntity();

			IChatComponent msg = StringUtil.getLocaleWithArguments("message.mob.skeletalArmy.kill", p.getDisplayName());

			for (final EntityPlayer e : (List<EntityPlayer>)p.worldObj.getEntitiesWithinAABB(EntityPlayer.class, p.boundingBox.expand(50.0, 50.0, 50.0))) {
				e.addChatMessage(msg);
			}

			PlayerContainer cont = PlayerContainer.getProperties(p);

			if (cont.getLevel(Hunter) >= 30)
				cont.addExperience(40000, Hunter);
		}
	}
}
