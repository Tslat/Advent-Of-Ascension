package net.nevermine.boss.primordialfive;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.boss.EntityBoss;
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.izer.equipment.Armorizer;
import net.nevermine.izer.equipment.Weaponizer;

import java.util.List;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityOkazor extends EntityMob implements EntityBoss {
	private int musicTick;
	private int healCounter;
	private int stacks;

	public EntityOkazor(final World par1World) {
		super(par1World);
		healCounter = 0;
		stacks = 0;
		setSize(1.2f, 2.1f);
	}

	public EntityOkazor(final World par1World, final int music) {
		this(par1World);
		musicTick = music;
	}

	protected String getLivingSound() {
		return "nevermine:PrimordialLiving";
	}

	protected String getDeathSound() {
		return "nevermine:PrimordialDeath";
	}

	protected String getHurtSound() {
		return "nevermine:PrimordialLiving";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.55f, 1.0f);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		final int wep = rand.nextInt(3);

		if (wep == 1) {
			dropItem(Weaponizer.PrimordialBow, 1);
		}
		else if (wep == 2) {
			dropItem(Weaponizer.PrimordialStaff, 1);
		}
		else {
			dropItem(Weaponizer.PrimordialGreatblade, 1);
		}

		final int arm = rand.nextInt(4);

		if (arm == 1) {
			dropItem(Armorizer.PrimordialHelmet, 1);
		}
		else if (arm == 2) {
			dropItem(Armorizer.PrimordialChestplate, 1);
		}
		else if (arm == 3) {
			dropItem(Armorizer.PrimordialLeggings, 1);
		}
		else {
			dropItem(Armorizer.PrimordialBoots, 1);
		}

		final int stat = rand.nextInt(5);

		if (stat == 1) {
			dropItem(Item.getItemFromBlock(SpecialBlockizer.HarkosStatue), 1);
		}
		else if (stat == 2) {
			dropItem(Item.getItemFromBlock(SpecialBlockizer.KajarosStatue), 1);
		}
		else if (stat == 3) {
			dropItem(Item.getItemFromBlock(SpecialBlockizer.MiskelStatue), 1);
		}
		else if (stat == 4) {
			dropItem(Item.getItemFromBlock(SpecialBlockizer.OkazorStatue), 1);
		}
		else {
			dropItem(Item.getItemFromBlock(SpecialBlockizer.RaxxanStatue), 1);
		}
	}

	public void onDeath(final DamageSource d) {
		super.onDeath(d);

		if (!worldObj.isRemote && d.getEntity() instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer)d.getEntity();

			for (final EntityPlayer e : (List<EntityPlayer>)p.worldObj.getEntitiesWithinAABB(EntityPlayer.class, p.boundingBox.expand(50.0, 50.0, 50.0))) {
				e.addChatMessage(StringUtil.getLocaleWithArguments("message.mob.primordialFive.kill", p.getDisplayName()));
			}

			PlayerContainer cont = PlayerContainer.getProperties(p);

			if (cont.getLevel(Hunter) >= 30)
				cont.addExperience(40000, Hunter);
		}
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		--musicTick;
		if (musicTick == 0) {
			musicTick = 290;
			playSound("nevermine:MusicPrimordialFive", 2.8f, 1.0f);
		}

		if (healCounter > 0) {
			--healCounter;
		}

		if (healCounter == 0) {
			stacks = 0;
		}

		if (rand.nextInt(125) == 33 && getLastAttacker() != null) {
			setPosition(getLastAttacker().posX, getLastAttacker().posY, getLastAttacker().posZ);
		}
	}

	public boolean attackEntityAsMob(final Entity par1) {
		if (super.attackEntityAsMob(par1)) {
			if (par1 instanceof EntityLivingBase) {
				++stacks;
				healCounter = 80;
				if (stacks == 3) {
					heal(1200.0f);
				}
			}
			return true;
		}
		return false;
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(50.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1200.0);
	}
}
