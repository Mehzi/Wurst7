/*
 * Copyright (C) 2014 - 2020 | Alexander01998 | All rights reserved.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package net.wurstclient.hacks;

import java.util.ArrayList;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.wurstclient.Category;
import net.wurstclient.SearchTags;
import net.wurstclient.events.UpdateListener;
import net.wurstclient.events.LeftClickListener;
import net.wurstclient.hack.Hack;
import net.wurstclient.settings.ItemListSetting;
import net.wurstclient.util.BlockUtils;

@SearchTags({"auto mine", "AutoBreak", "auto break", "selective break"})
public final class SelectiveBreakHack extends Hack implements UpdateListener
{
	private ItemListSetting blocks = new ItemListSetting("Items",
			"Unwanted items that will be dropped.", "minecraft:dirt");
	
	public SelectiveBreakHack()
	{
		super("Selective Break", "Turn off breaking for certain blocks");
		setCategory(Category.BLOCKS);
		addSetting(blocks);
	}
	
	@Override
	public void onEnable()
	{
		WURST.getHax().excavatorHack.setEnabled(false);
		WURST.getHax().nukerHack.setEnabled(false);
		WURST.getHax().nukerLegitHack.setEnabled(false);
		WURST.getHax().speedNukerHack.setEnabled(false);
		WURST.getHax().tunnellerHack.setEnabled(false);
		
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
		if(MC.crosshairTarget == null
				|| MC.crosshairTarget.getType() != HitResult.Type.BLOCK)
				return;
		if(MC.currentScreen instanceof HandledScreen
				&& !(MC.currentScreen instanceof InventoryScreen))
				return;
		
		BlockHitResult blockHitResult = (BlockHitResult)MC.crosshairTarget;
		BlockPos pos = new BlockPos(blockHitResult.getBlockPos());
		String blockName = BlockUtils.getName(pos);
		if (blocks.getItemNames().contains(blockName)) {
			IMC.getInteractionManager().setBreakingBlock(true);
			MC.interactionManager.cancelBlockBreaking();
		}
	}
	
}
