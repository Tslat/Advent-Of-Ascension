package net.tslat.aoa3.client.gui.misc;

import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

public class XpGainParticle {
	private int x;
	private int y;
	private String msg;
	private int age;


	public XpGainParticle(Enums.Skills skill, float xpAmount) {
		if (skill == null)
			return;

		msg = StringUtil.getLocaleString("skills." + skill.toString().toLowerCase() + ".name") + ": +" + ((int)(xpAmount * 10)) / 10 + "xp";
		y = 150;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getString() {
		return msg;
	}

	public int getAge() {
		return age;
	}

	public void tickParticle() {
		y -= 1 & age;
		age++;
	}
}
