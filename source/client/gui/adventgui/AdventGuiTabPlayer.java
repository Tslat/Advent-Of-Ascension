package net.tslat.aoa3.client.gui.adventgui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.gui.lib.ScrollablePane;
import net.tslat.aoa3.client.render.AoAGuiElementRenderers;
import net.tslat.aoa3.client.render.custom.AoASkillRenderer;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.AddSkillCyclePacket;
import net.tslat.aoa3.common.packet.packets.ToggleAoAAbilityPacket;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.player.AoAPlayerEventListener;
import net.tslat.aoa3.player.ClientPlayerDataManager;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AdventGuiTabPlayer extends Screen {
	private static final ResourceLocation TOTAL_LEVEL_ICON = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/aoaskill/total_level.png");

	private LivingEntity entityToRender = null;
	private float skillRenderScale = 1;

	private AbilityPane abilityPane = null;

	private int adjustedMouseX;
	private int adjustedMouseY;

	private boolean hoveringSkillClose = false;
	private boolean hoveringAddCycle = false;
	private long addCycleLastClicked = -1;

	protected AdventGuiTabPlayer() {
		super(new TranslationTextComponent("gui.aoa3.adventGui.stats"));
	}

	@Override
	protected void init() {
		setRenderEntity();

		boolean scaled;

		do {
			buttons.clear();
			children.clear();
			int totalWidth = 40;
			int totalHeight = 0;
			scaled = true;

			for (AoASkill.Instance skill : ClientPlayerDataManager.get().getSkills()) {
				AoASkillRenderer renderer = AoAGuiElementRenderers.getSkillRenderer(skill.type());
				float renderWidth = (renderer.hudRenderWidth(skill) + 15) * skillRenderScale;
				float renderHeight = (renderer.hudRenderHeight(skill) + 20) * skillRenderScale;

				if (totalWidth + renderWidth >= 244 / skillRenderScale || totalHeight + renderHeight >= 100 / skillRenderScale) {
					totalWidth = 40;
					totalHeight += renderHeight;

					if (totalHeight >= 100) {
						skillRenderScale *= 0.95f;
						scaled = false;

						break;
					}
				}

				addButton(new SkillEntry(this, totalWidth + 7, totalHeight + 10, renderer, skill, skillRenderScale));

				totalWidth += renderWidth;
			}
		}
		while (!scaled);

		AoASkill.Instance skill = abilityPane == null ? null : abilityPane.skill;
		abilityPane = new AbilityPane(minecraft, AdventMainGui.scaledTabRootY + 50, AdventMainGui.scaledTabRootX + 250, 280, 500, AdventMainGui.SCALE);

		abilityPane.setSkill(skill);
	}

	@Override
	public void render(MatrixStack matrix, int mouseX, int mouseY, float partialTicks) {
		adjustedMouseX = (int)(mouseX * (1 / AdventMainGui.SCALE));
		adjustedMouseY = (int)(mouseY * (1 / AdventMainGui.SCALE));

		matrix.pushPose();
		matrix.translate(AdventMainGui.scaledTabRootX, AdventMainGui.scaledTabRootY, 0);

		if (abilityPane == null || abilityPane.skill == null) {
			hoveringAddCycle = false;
			hoveringSkillClose = false;

			matrix.scale(1.6f, 1.6f, 1);

			matrix.pushPose();
			matrix.scale(skillRenderScale, skillRenderScale, 1);
			super.render(matrix, adjustedMouseX, adjustedMouseY, partialTicks);
			matrix.popPose();

			drawPlayerBox(matrix, adjustedMouseX, adjustedMouseY, 67, partialTicks);
			drawTotalLevel(matrix, adjustedMouseX, adjustedMouseY, partialTicks);
		}
		else {
			AoASkillRenderer skillRenderer = AoAGuiElementRenderers.getSkillRenderer(abilityPane.skill.type());
			FontRenderer font = Minecraft.getInstance().font;
			float skillRenderWidth = skillRenderer.guiRenderWidth(abilityPane.skill) * 3 / 2f;
			hoveringSkillClose = (adjustedMouseX > AdventMainGui.scaledTabRootX + 736 && adjustedMouseX < AdventMainGui.scaledTabRootX + 755 && adjustedMouseY > AdventMainGui.scaledTabRootY + 7 && adjustedMouseY < AdventMainGui.scaledTabRootY + 27);

			matrix.pushPose();

			RenderUtil.drawScaledString(matrix, font, "x", 740, 5, 3f, hoveringSkillClose ? ColourUtil.RGB(20, 20, 20) : ColourUtil.BLACK, RenderUtil.StringRenderType.OUTLINED);

			matrix.translate(150 - skillRenderWidth / 2f, 20, 0);

			RenderUtil.drawCenteredScaledMessage(matrix, font, abilityPane.skill.getName(), 0, 0, 2f, ColourUtil.WHITE, RenderUtil.StringRenderType.OUTLINED);

			matrix.translate(0, 20, 0);
			matrix.pushPose();
			matrix.translate(-skillRenderWidth, 0, 0);
			matrix.scale(3f, 3f, 1);

			skillRenderer.renderInGui(matrix, abilityPane.skill, partialTicks, adjustedMouseX, adjustedMouseY, AoASkillRenderer.ProgressRenderType.None, false);

			matrix.popPose();

			RenderUtil.drawCenteredScaledMessage(matrix, font, new TranslationTextComponent("gui." + AdventOfAscension.MOD_ID + ".adventGui.player.skillLevel", ""), 0, 80, 1.5f, ColourUtil.WHITE, RenderUtil.StringRenderType.OUTLINED);
			RenderUtil.drawCenteredScaledString(matrix, font, String.valueOf(abilityPane.skill.getLevel(true)), 0, 95, 1.5f, ColourUtil.WHITE, RenderUtil.StringRenderType.OUTLINED);
			RenderUtil.drawCenteredScaledMessage(matrix, font, new TranslationTextComponent("gui." + AdventOfAscension.MOD_ID + ".adventGui.player.skillXp", ""), 0, 120, 1.5f, ColourUtil.WHITE, RenderUtil.StringRenderType.OUTLINED);
			RenderUtil.drawCenteredScaledString(matrix, font, NumberUtil.roundToNthDecimalPlace(abilityPane.skill.getXp(), 2), 0, 135, 1.5f, ColourUtil.WHITE, RenderUtil.StringRenderType.OUTLINED);
			RenderUtil.drawCenteredScaledMessage(matrix, font, new TranslationTextComponent("gui." + AdventOfAscension.MOD_ID + ".adventGui.player.skillXpRemaining", ""), 0, 160, 1.5f, ColourUtil.WHITE, RenderUtil.StringRenderType.OUTLINED);
			RenderUtil.drawCenteredScaledString(matrix, font, NumberUtil.roundToNthDecimalPlace(PlayerUtil.getXpRequiredForNextLevel(abilityPane.skill.getLevel(true)) - abilityPane.skill.getXp(), 2), 0, 175, 1.5f, ColourUtil.WHITE, RenderUtil.StringRenderType.OUTLINED);
			RenderUtil.drawCenteredScaledMessage(matrix, font, new TranslationTextComponent("gui." + AdventOfAscension.MOD_ID + ".adventGui.player.skillProgress", ""), 0, 200, 1.5f, ColourUtil.WHITE, RenderUtil.StringRenderType.OUTLINED);
			RenderUtil.drawCenteredScaledString(matrix, font, PlayerUtil.getLevelProgressPercentage(abilityPane.skill.getLevel(true), abilityPane.skill.getXp()) + "%", 0, 215, 1.5f, ColourUtil.WHITE, RenderUtil.StringRenderType.OUTLINED);
			RenderUtil.drawCenteredScaledMessage(matrix, font, new TranslationTextComponent("gui." + AdventOfAscension.MOD_ID + ".adventGui.player.skillCycles", ""), 0, 240, 1.5f, ColourUtil.WHITE, RenderUtil.StringRenderType.OUTLINED);
			RenderUtil.drawCenteredScaledString(matrix, font, String.valueOf(abilityPane.skill.getCycles()), 0, 255, 1.5f, ColourUtil.WHITE, RenderUtil.StringRenderType.OUTLINED);

			if (abilityPane.skill.getLevel(true) >= 100 && abilityPane.skill.getCycles() < 10) {
				TranslationTextComponent text = addCycleLastClicked > 0 ? new TranslationTextComponent("gui." + AdventOfAscension.MOD_ID + ".adventGui.player.addCycle.confirm") : new TranslationTextComponent("gui." + AdventOfAscension.MOD_ID + ".adventGui.player.addCycle");
				int buttonWidth = font.width(text);
				hoveringAddCycle = (adjustedMouseX > AdventMainGui.scaledTabRootX + (150 - skillRenderWidth / 2f) - (buttonWidth / 2f * 1.5f) && adjustedMouseX < AdventMainGui.scaledTabRootX + (150 - skillRenderWidth / 2f) + (buttonWidth / 2f * 1.5f) && adjustedMouseY > AdventMainGui.scaledTabRootY + 315 && adjustedMouseY < AdventMainGui.scaledTabRootY + 327);

				if (System.currentTimeMillis() - addCycleLastClicked > 3000)
					addCycleLastClicked = -1;

				RenderUtil.drawCenteredScaledMessage(matrix, font, text, 0, 275, 1.5f, hoveringAddCycle ? ColourUtil.RGB(255, 100, 100) : ColourUtil.RGB(255, 50, 50), RenderUtil.StringRenderType.DROP_SHADOW);
			}
			else {
				hoveringAddCycle = false;
			}

			matrix.popPose();

			RenderUtil.drawCenteredScaledMessage(matrix, font, new TranslationTextComponent("gui." + AdventOfAscension.MOD_ID + ".adventGui.player.abilities"), 500, 20, 2f, ColourUtil.WHITE, RenderUtil.StringRenderType.OUTLINED);

			matrix.popPose();

			if (abilityPane != null)
				abilityPane.render(matrix, adjustedMouseX, adjustedMouseY, partialTicks);

			return;
		}

		matrix.popPose();
	}

	private void drawTotalLevel(MatrixStack matrix, int mouseX, int mouseY, float partialTicks) {
		Minecraft mc = Minecraft.getInstance();

		RenderSystem.enableAlphaTest();
		mc.textureManager.bind(TOTAL_LEVEL_ICON);
		RenderUtil.renderScaledCustomSizedTexture(matrix, 10, 14, 0, 0, 48, 48, 60, 60, 48, 48);
		RenderUtil.drawCenteredScaledMessage(matrix, mc.font, new TranslationTextComponent("gui." + AdventOfAscension.MOD_ID + ".adventGui.player.totalLevel"), 40, 10, 1, ColourUtil.WHITE, RenderUtil.StringRenderType.OUTLINED);
		RenderUtil.drawCenteredScaledString(matrix, mc.font, String.valueOf(ClientPlayerDataManager.get().getTotalLevel()), 40, 40, 1, ColourUtil.WHITE, RenderUtil.StringRenderType.OUTLINED);
	}

	private void drawPlayerBox(MatrixStack matrix, int mouseX, int mouseY, int scale, float partialTicks) {
		matrix.pushPose();

		if (entityToRender == null)
			setRenderEntity();

		Minecraft mc = Minecraft.getInstance();
		
		ITextComponent name = mc.player.getDisplayName();

		matrix.translate(40, 206, 1050.0F);
		RenderUtil.drawCenteredScaledMessage(matrix, mc.font, name, 0, 206 - 310, 1.0f, ColourUtil.WHITE, RenderUtil.StringRenderType.OUTLINED);
		matrix.scale(0.625f, 0.625f, 0.625f);

		matrix.scale(1.0F, 1.0F, -1.0F);

		matrix.translate(0.0D, 0.0D, 1000.0D);
		matrix.scale((float)scale, (float)scale, (float)scale);

		Quaternion quaternion = Vector3f.XP.rotationDegrees(180f);

		matrix.mulPose(quaternion);

		float yawOffset = entityToRender.yBodyRot;
		float rotYaw = entityToRender.yRot;
		float rotPitch = entityToRender.xRot;
		float prevYawHead = entityToRender.yHeadRotO;
		float rotYawHead = entityToRender.yHeadRot;
		entityToRender.yBodyRot = 0;
		entityToRender.yRot = (float)Math.atan((((AdventMainGui.scaledRootX + 264) - mouseX) / 40.0F)) * 40.0F;
		entityToRender.xRot = -((float)Math.atan((((AdventMainGui.scaledRootY + 465) - 111 - mouseY) / 40.0F))) * 20.0F;
		entityToRender.yHeadRot = entityToRender.yRot;
		entityToRender.yHeadRotO = entityToRender.yRot;
		EntityRendererManager renderManager = Minecraft.getInstance().getEntityRenderDispatcher();

		renderManager.setRenderShadow(false);

		IRenderTypeBuffer.Impl renderBuffer = Minecraft.getInstance().renderBuffers().bufferSource();

		renderManager.render(entityToRender, 0, 0, 0, 0, 1, matrix, renderBuffer, 15728880);
		renderBuffer.endBatch();
		renderManager.setRenderShadow(true);

		entityToRender.yBodyRot = yawOffset;
		entityToRender.yRot = rotYaw;
		entityToRender.xRot = rotPitch;
		entityToRender.yHeadRotO = prevYawHead;
		entityToRender.yHeadRot = rotYawHead;

		matrix.popPose();
	}

	@Override
	public void removed() {
		super.removed();

		if (entityToRender != null && !(entityToRender instanceof PlayerEntity))
			entityToRender.remove();

		entityToRender = null;
	}

	private void setRenderEntity() {
		if (entityToRender == null) {
			Minecraft mc = Minecraft.getInstance();
			
			if (!ClientPlayerDataManager.get().isLegitimate()) {
				entityToRender = RandomUtil.getRandomSelection(
						AoAEntities.Mobs.ARCWORM,
						AoAEntities.Mobs.CHARGER,
						AoAEntities.Mobs.DESERT_CHARGER,
						AoAEntities.Mobs.HILL_CHARGER,
						AoAEntities.Mobs.OCCULENT,
						AoAEntities.Mobs.SEA_CHARGER,
						AoAEntities.Mobs.SNOW_CHARGER,
						AoAEntities.Mobs.SWAMP_CHARGER,
						AoAEntities.Mobs.VOID_CHARGER,
						AoAEntities.Mobs.CYCLOPS,
						AoAEntities.Mobs.STICKY,
						AoAEntities.Mobs.KRANKY,
						AoAEntities.Mobs.GINGERBREAD_MAN,
						AoAEntities.Mobs.SHADE,
						AoAEntities.Mobs.BOBO,
						AoAEntities.Mobs.CHOCKO,
						AoAEntities.Mobs.STITCHES,
						AoAEntities.Mobs.BUGEYE,
						AoAEntities.Mobs.SEA_TROLL,
						AoAEntities.Mobs.ELITE_SKELE_PIG,
						AoAEntities.Animals.SPEARMINT_SNAIL,
						AoAEntities.Mobs.SHADOW,
						AoAEntities.Mobs.ALARMO,
						AoAEntities.Mobs.ANCIENT_GOLEM
				).get().create(mc.level);
			}
			else {
				entityToRender = mc.player;
			}
		}
	}

	@Override
	public void resize(Minecraft mc, int pWidth, int pHeight) {
		super.resize(mc, pWidth, pHeight);

		if (abilityPane != null)
			abilityPane.onResize(mc, AdventMainGui.scaledTabRootX + 250, AdventMainGui.scaledTabRootY + 50, 500, 280);
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		if (button == 0 && abilityPane != null && abilityPane.skill != null) {
			if (hoveringSkillClose) {
				abilityPane.setSkill(null);

				return true;
			}

			if (hoveringAddCycle) {
				if (addCycleLastClicked < 0) {
					addCycleLastClicked = System.currentTimeMillis();

					return true;
				}

				AoAPackets.messageServer(new AddSkillCyclePacket(abilityPane.skill.type()));

				return true;
			}

			if (abilityPane.handleMouseClick(adjustedMouseX, adjustedMouseY, button))
				return true;
		}

		return super.mouseClicked(mouseX, mouseY, button);
	}

	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int button) {
		if (abilityPane != null && abilityPane.skill != null) {
			if (abilityPane.handleMouseReleased(adjustedMouseX, adjustedMouseY, button))
				return true;
		}

		return super.mouseReleased(mouseX, mouseY, button);
	}

	@Override
	public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
		if (abilityPane != null && abilityPane.skill != null) {
			if (abilityPane.handleMouseScroll(adjustedMouseX, adjustedMouseY, delta))
				return true;
		}

		return super.mouseScrolled(mouseX, mouseY, delta);
	}

	private static class AbilityPane extends ScrollablePane {
		private AoASkill.Instance skill;
		private List<AoAAbility.Instance> sortedAbilities = null;

		private int hoveredAbility = -1;

		private float clickStartScroll = -1;

		public AbilityPane(Minecraft mc, int top, int left, int viewHeight, int viewWidth, float renderingScale) {
			super(mc, top, left, viewHeight, viewWidth, renderingScale);
		}

		private void setSkill(AoASkill.Instance skill) {
			this.skill = skill;
			this.sortedAbilities = skill == null ? null : skill.getAbilityMap().values().stream().sorted(Comparator.comparingInt(AoAAbility.Instance::getLevelReq)).collect(Collectors.toList());
		}

		@Override
		public int getFullPaneHeight() {
			return skill != null ? viewHeight + skill.getAbilityMap().size() * 25 - 140 : 0;
		}

		@Override
		public boolean handleMouseClick(double mouseX, double mouseY, int button) {
			if (!isMouseHovering())
				return false;

			clickStartScroll = distanceScrolled;

			return super.handleMouseClick(mouseX, mouseY, button);
		}

		@Override
		public boolean handleMouseReleased(double mouseX, double mouseY, int button) {
			boolean wasDragging = isDragging;
			isDragging = false;

			if (!isMouseHovering())
				return false;

			if (wasDragging && clickStartScroll == distanceScrolled && hoveredAbility != -1)
				AoAPackets.messageServer(new ToggleAoAAbilityPacket(skill.type(), sortedAbilities.get(hoveredAbility)));

			return super.handleMouseReleased(mouseX, mouseY, button);
		}

		@Override
		public void drawPaneContents(MatrixStack matrix, int top, int left, int right, int bottom, float scrollDistance, float partialTicks) {
			Minecraft mc = Minecraft.getInstance();
			FontRenderer font = mc.font;
			float center = (right - left) / 2f;

			if (isMouseHovering() && mouseX < right - 8) {
				hoveredAbility = Math.min((int)-((top - mouseY - scrollDistance) / 50f), sortedAbilities.size() - 1);

				if (hoveredAbility >= 0 && !sortedAbilities.get(hoveredAbility).type().canBeDisabled())
					hoveredAbility = -1;
			}
			else {
				hoveredAbility = -1;
			}

			matrix.pushPose();
			matrix.translate(left, top, 0);
			matrix.translate(0, -scrollDistance, 0);

			for (int i = 0; i < sortedAbilities.size(); i++) {
				AoAAbility.Instance ability = sortedAbilities.get(i);
				int colour = ColourUtil.RGB(100, 100, 100);

				if (ability.getListenerState() == AoAPlayerEventListener.ListenerState.ACTIVE) {
					colour = hoveredAbility == i ? ColourUtil.RGB(255, 255, 180) : ColourUtil.WHITE;
				}
				else if (ability.getListenerState() == AoAPlayerEventListener.ListenerState.MANUALLY_DISABLED) {
					if (hoveredAbility == i)
						colour = ColourUtil.RGB(150, 150, 100);
				}

				RenderUtil.drawScaledString(matrix, font, String.valueOf(ability.getLevelReq()), 5, 2, 1.5f, colour, RenderUtil.StringRenderType.OUTLINED);
				RenderUtil.drawCenteredScaledMessage(matrix, font, ability.getName(), center, 2, 1.5f, colour, RenderUtil.StringRenderType.OUTLINED);
				matrix.translate(0, 20, 0);
				RenderUtil.drawWrappedMessage(matrix, font, ability.getDescription(), 5, 2, right - left - 10, colour);
				matrix.translate(0, 30, 0);
			}

			matrix.popPose();
		}

		@Override
		public void drawBackground(MatrixStack matrix) {}
	}

	private static class SkillEntry extends Widget {
		private final AdventGuiTabPlayer tab;

		private final AoASkillRenderer renderer;
		private final AoASkill.Instance skill;
		private final float scale;

		private SkillEntry(AdventGuiTabPlayer tab, float posX, float posY, AoASkillRenderer renderer, AoASkill.Instance instance, float scale) {
			super((int)posX, (int)posY, renderer.guiRenderHeight(instance), renderer.guiRenderWidth(instance), instance.getName());

			this.tab = tab;
			this.renderer = renderer;
			this.skill = instance;
			this.scale = 2 * 1.6f * scale;
		}

		@Override
		public void render(MatrixStack matrix, int mouseX, int mouseY, float partialTicks) {
			if (this.visible) {
				this.isHovered = isMouseOver(mouseX - AdventMainGui.scaledTabRootX, mouseY - AdventMainGui.scaledTabRootY);

				matrix.pushPose();
				matrix.scale(2, 2, 1);
				matrix.translate(x, y, 0);
				renderer.renderInGui(matrix, skill, partialTicks, mouseX, mouseY, AoAConfig.CLIENT.hudSkillProgressRenderType.get(), true);
				matrix.translate(0, -5, 0);
				matrix.scale(0.5f, 0.5f, 1);

				RenderUtil.drawCenteredScaledMessage(matrix, Minecraft.getInstance().font, getMessage(), width, 0, 1, isHovered() ? ColourUtil.RGB(255, 255, 100) : ColourUtil.WHITE, RenderUtil.StringRenderType.OUTLINED);
				matrix.popPose();
			}
		}

		@Override
		protected boolean clicked(double pMouseX, double pMouseY) {
			return isHovered;
		}

		@Override
		public void onClick(double pMouseX, double pMouseY) {
			tab.abilityPane.setSkill(skill);
			isHovered = false;
		}

		@Override
		public boolean isMouseOver(double mouseX, double mouseY) {
			if (!active || !visible)
				return false;

			if (mouseX <= x * scale)
				return false;

			if (mouseX >= x * scale + width * scale)
				return false;

			if (mouseY <= y * 2 * 1.6f)
				return false;

			return mouseY < y * scale + height * scale;
		}
	}
}
