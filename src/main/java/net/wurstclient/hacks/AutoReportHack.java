/*
 * Copyright (C) 2014 - 2020 | Alexander01998 | All rights reserved.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package net.wurstclient.hacks;

import net.minecraft.item.EmptyMapItem;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.ItemStack;
import net.wurstclient.Category;
import net.wurstclient.SearchTags;
import net.wurstclient.events.UpdateListener;
import net.wurstclient.hack.Hack;

@SearchTags({"auto report", "report"})
public final class AutoReportHack extends Hack implements UpdateListener
{
	
	public AutoReportHack()
	{
		super("Auto Report Hack", "Reports when player is holding a map");
		setCategory(Category.ITEMS);
	}
	
	@Override
	public void onEnable()
	{
		EVENTS.add(UpdateListener.class, this);
	}
	
	@Override
	public void onDisable()
	{
		EVENTS.remove(UpdateListener.class, this);
	}
	
	@Override
	public void onUpdate()
	{
		ItemStack holdingItem = MC.player.getMainHandStack();
		if (holdingItem.getItem() instanceof FilledMapItem) {
			System.out.println("Map detected filled");
		} else if (holdingItem.getItem() instanceof EmptyMapItem) {
			System.out.println("Map detected empty");
		}
		
	}
}
