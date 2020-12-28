package net.tslat.aoa3.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.NetworkDirection;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.Keybinds;
import net.tslat.aoa3.client.gui.hud.RecoilRenderer;
import net.tslat.aoa3.client.gui.overlay.HelmetScreenRenderer;
import net.tslat.aoa3.client.gui.overlay.ScopeOverlayRenderer;
import net.tslat.aoa3.client.gui.overlay.ScreenOverlayRenderer;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.HaloChangePacket;
import net.tslat.aoa3.common.packet.packets.LongReachItemHitPacket;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.event.GlobalEvents;
import net.tslat.aoa3.item.LongReachItem;
import net.tslat.aoa3.item.armour.ScreenOverlayArmour;
import net.tslat.aoa3.item.weapon.sniper.BaseSniper;
import net.tslat.aoa3.library.scheduling.AoAScheduler;
import net.tslat.aoa3.util.LocaleUtil;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(modid = AdventOfAscension.MOD_ID, value = Dist.CLIENT)
@OnlyIn(Dist.CLIENT)
public class ClientEventHandler {
	public static int tick;

	@SubscribeEvent
	public static void clientTick(final TickEvent.ClientTickEvent ev) {
		if (ev.phase == TickEvent.Phase.END) {
			tick++;

			if (tick > 24000)
				tick = 0;

			if (ScreenOverlayRenderer.overlayTicks > 0)
				--ScreenOverlayRenderer.overlayTicks;

			if (RecoilRenderer.recoilTicksRemaining > 0)
				--RecoilRenderer.recoilTicksRemaining;

			ClientPlayerEntity player = Minecraft.getInstance().player;

			if (player == null)
				return;

			if (player.isSneaking() && player.onGround) {
				Item sniper = player.getHeldItem(Hand.MAIN_HAND).getItem();

				if (sniper instanceof BaseSniper) {
					ScopeOverlayRenderer.isScoped = true;
					ScopeOverlayRenderer.scope = ((BaseSniper)sniper).getScopeType();
				}
				else {
					ScopeOverlayRenderer.isScoped = false;
				}
			}
			else {
				ScopeOverlayRenderer.isScoped = false;

				if (ScopeOverlayRenderer.scope != null) {
					RecoilRenderer.recoilTicks = 5;
					RecoilRenderer.recoilTicksRemaining = 5;
					RecoilRenderer.recoilAngle = 1.0f;
					ScopeOverlayRenderer.scope = null;
				}
			}

			HelmetScreenRenderer.active = false;
			Item helmetItem = player.inventory.armorInventory.get(3).getItem();

			if (!ScopeOverlayRenderer.isScoped && helmetItem instanceof ScreenOverlayArmour) {
				HelmetScreenRenderer.type = ((ScreenOverlayArmour)helmetItem).getOverlay();
				HelmetScreenRenderer.active = true;
			}

			if (!Minecraft.getInstance().isSingleplayer()) {
				GlobalEvents.tick++;
				AoAScheduler.handleSyncScheduledTasks(GlobalEvents.tick);
			}
		}
	}

	@SubscribeEvent(receiveCanceled = true)
	public static void onLongReachSwing(InputEvent.MouseInputEvent ev) {
		if (ev.getAction() == GLFW.GLFW_PRESS && ev.getButton() == GLFW.GLFW_MOUSE_BUTTON_1 && Minecraft.getInstance().currentScreen == null) {
			Minecraft mc = Minecraft.getInstance();
			PlayerEntity player = mc.player;

			if (player != null && player.world != null) {
				if (player.isSpectator())
					return;

				ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);

				if (stack.getItem() instanceof LongReachItem && !player.isHandActive()) {
					RayTraceResult ray = getExtendedReachRayTrace(((LongReachItem)stack.getItem()).getReach());

					if (ray instanceof EntityRayTraceResult)
						AoAPackets.messageServer(new LongReachItemHitPacket((((EntityRayTraceResult)ray).getEntity().getEntityId())));
				}
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent ev) {
		if (AoAConfig.CLIENT.showWelcomeMessage.get()) {
			if (Keybinds.keyAdventGui.getKey().getKeyCode() == GLFW.GLFW_KEY_UNKNOWN) {
				ev.getPlayer().sendMessage(LocaleUtil.getLocaleMessage("message.login.welcome.alt", TextFormatting.GRAY));
			}
			else {
				ev.getPlayer().sendMessage(LocaleUtil.getLocaleMessage("message.login.welcome", TextFormatting.GRAY, Keybinds.keyAdventGui.getLocalizedName()));
			}
		}

		if (ev.getPlayer().world.isRemote())
			AoAPackets.INSTANCE.sendTo(new HaloChangePacket(AoAConfig.CLIENT.personalHaloPreference.get()), ((ClientPlayerEntity)ev.getPlayer()).connection.getNetworkManager(), NetworkDirection.PLAY_TO_SERVER);
	}

	@SubscribeEvent
	public static void onPlayerDeath(LivingDeathEvent ev) {
		if (ev.getEntity() instanceof PlayerEntity)
			ScreenOverlayRenderer.overlayTicks = 0;
	}

	@Nullable
	private static RayTraceResult getExtendedReachRayTrace(float reach) {
		Minecraft mc = Minecraft.getInstance();
		Entity player = mc.getRenderViewEntity();

		if (player != null && player.world != null) {
			float partialTicks = mc.getRenderPartialTicks();
			mc.pointedEntity = null;
			mc.objectMouseOver = player.pick(reach, partialTicks, false);
			Vec3d eyePos = player.getEyePosition(partialTicks);
			double squareDistance = reach;

			squareDistance = squareDistance * squareDistance;

			if (mc.objectMouseOver != null)
				squareDistance = mc.objectMouseOver.getHitVec().squareDistanceTo(eyePos);

			Vec3d lookVec = player.getLook(1.0F);
			Vec3d reachVec = eyePos.add(lookVec.x * (double)reach, lookVec.y * (double)reach, lookVec.z * (double)reach);
			AxisAlignedBB boundingBox = player.getBoundingBox().expand(lookVec.scale(reach)).grow(1.0D, 1.0D, 1.0D);
			EntityRayTraceResult trace = ProjectileHelper.rayTraceEntities(player, eyePos, reachVec, boundingBox, (entity) -> !entity.isSpectator() && entity.canBeCollidedWith(), squareDistance);

			if (trace != null) {
				Entity entity = trace.getEntity();

				if (eyePos.squareDistanceTo(trace.getHitVec()) < squareDistance || mc.objectMouseOver == null) {
					mc.objectMouseOver = trace;

					if (entity instanceof LivingEntity || entity instanceof ItemFrameEntity)
						mc.pointedEntity = entity;
				}
			}

			if (mc.objectMouseOver.getType() == RayTraceResult.Type.ENTITY)
				return mc.objectMouseOver;
		}

		return null;
	}
}
