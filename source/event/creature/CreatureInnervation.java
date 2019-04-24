package net.nevermine.event.creature;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.nevermine.assist.ConfigurationHelper;
import net.nevermine.container.PlayerContainer;
import net.nevermine.item.weapon.sword.SwordInterface;
import net.nevermine.izer.Itemizer;
import net.nevermine.npc.auxil.EntityBloodlust;
import net.nevermine.projectiles.HardProjectile;
import net.nevermine.skill.innervation.innervationHelper;

import java.util.Random;

import static net.nevermine.container.PlayerContainer.Skills.Innervation;

public class CreatureInnervation {
	private Random rand;
	private int pick;

	public CreatureInnervation() {
		rand = new Random();
	}

	@SubscribeEvent
	public void goldPickup(final PlayerEvent.ItemPickupEvent e) {
		if (!e.player.worldObj.isRemote && (e.pickedUp.getEntityItem().getItem() == Itemizer.PureGold || e.pickedUp.getEntityItem().getItem() == Itemizer.ImpureGold)) {
			e.player.inventory.consumeInventoryItem(e.pickedUp.getEntityItem().getItem());
		}
	}

	@SubscribeEvent
	public void heartToss(final ItemTossEvent e) {
		if (!e.player.worldObj.isRemote && e.entityItem.getEntityItem().getItem() == Itemizer.HeartStone) {
			PlayerContainer cont = PlayerContainer.getProperties(e.player);

			for (int i = 0; i < e.entityItem.getEntityItem().stackSize; i++) {
				cont.addExperience(cont.getExpRequired(Innervation) / innervationHelper.getExpDenominator(cont.getLevel(Innervation)), Innervation);
			}

			e.entityItem.setDead();
			e.player.worldObj.playSoundAtEntity(e.player, "nevermine:HeartPickup", 2.85f, 1.0f);
		}
	}

	@SubscribeEvent
	public void heartPickup(final PlayerEvent.ItemPickupEvent e) {
		if (!e.player.worldObj.isRemote && e.pickedUp.getEntityItem().getItem() == Itemizer.HeartStone) {
			PlayerContainer cont = PlayerContainer.getProperties(e.player);

			while (e.player.inventory.consumeInventoryItem(Itemizer.HeartStone)) {
				cont.addExperience(cont.getExpRequired(Innervation) / innervationHelper.getExpDenominator(cont.getLevel(Innervation)), Innervation);
			}

			e.player.heal(innervationHelper.getHeartstoneHealAmount(cont.getLevel(Innervation)));
			e.pickedUp.setDead();
			e.player.worldObj.playSoundAtEntity(e.player, "nevermine:HeartPickup", 2.85f, 1.0f);
		}
	}

	@SubscribeEvent
	public void damageLimitEvent(final LivingHurtEvent e) {
		if (e.ammount != 0.0f && ConfigurationHelper.limitation == 1 && e.source.getEntity() != null) {
			if (e.source.getEntity() instanceof EntityPlayer && !e.source.isProjectile()) {
				if (((EntityPlayer)e.source.getEntity()).getHeldItem() != null && !e.source.isMagicDamage() && !(((EntityPlayer)e.source.getEntity()).getHeldItem().getItem() instanceof SwordInterface)) {
					if (((EntityPlayer)e.source.getEntity()).isPotionActive(Potion.damageBoost)) {
						if (e.ammount > 50.0f) {
							e.ammount = 50.0f;
						}
					}
					else if (e.ammount > 25.0f) {
						e.ammount = 25.0f;
					}
				}
			}
			else if (!(e.source.getSourceOfDamage() instanceof HardProjectile) && e.ammount > 25.0f) {
				e.ammount = 25.0f;
			}
		}
	}

	@SubscribeEvent
	public void onPlayerInnervationDodge(final LivingHurtEvent e) {
		if (!e.entity.worldObj.isRemote && e.entity instanceof EntityPlayer && e.source != DamageSource.outOfWorld && e.source != DamageSource.starve) {
			final int lvl = PlayerContainer.getProperties((EntityPlayer)e.entity).getLevel(Innervation);

			if (innervationHelper.tryDodge(lvl)) {
				if (rand.nextBoolean()) {
					e.setCanceled(true);
				}
				else {
					((EntityLivingBase)e.entity).heal(innervationHelper.getDodgeHealAmount(lvl));
				}

				e.entity.worldObj.playSoundAtEntity(e.entity, "nevermine:MagickeFire", 1.0f, 1.0f);
			}
		}
	}

	@SubscribeEvent
	public void onPlayerDoingDamageEvent(final LivingHurtEvent e) {
		if (e.source.getEntity() instanceof EntityPlayer && !e.source.isProjectile() && !e.source.isMagicDamage() && !e.source.getEntity().worldObj.isRemote && rand.nextInt(30) == 15) {
			final EntityBloodlust var2 = new EntityBloodlust(e.entity.worldObj);

			var2.setLocationAndAngles(e.entity.posX, e.entity.posY, e.entity.posZ, rand.nextFloat() * 360.0f, 0.0f);
			e.entity.worldObj.spawnEntityInWorld(var2);
		}
	}

	@SubscribeEvent
	public void onKill(final LivingDeathEvent e) {
		if (!e.entity.worldObj.isRemote && e.entity instanceof EntityLiving) {
			if (rand.nextInt(8) == 5) {
				e.entity.worldObj.playSoundAtEntity(e.entity, "nevermine:HeartstoneDrop", 2.85f, 1.0f);
				e.entity.dropItem(Itemizer.HeartStone, 1);
			}

			if (rand.nextInt(100) != 0)
				return;

			switch (rand.nextInt(7)) {
				case 0:
					e.entity.dropItem(Itemizer.pStoneBlooming, 1);
					break;
				case 1:
					e.entity.dropItem(Itemizer.pStoneGleaming, 1);
					break;
				case 2:
					e.entity.dropItem(Itemizer.pStoneGlaring, 1);
					break;
				case 3:
					e.entity.dropItem(Itemizer.pStoneGlowing, 1);
					break;
				case 4:
					e.entity.dropItem(Itemizer.pStoneGlistening, 1);
					break;
				case 5:
					e.entity.dropItem(Itemizer.pStoneRadiant, 1);
					break;
				case 6:
					e.entity.dropItem(Itemizer.pStoneShining, 1);
					break;
				default:
					break;
			}
		}
	}
}
