/**
 * Copyright 2013 Mark Browning, StellaArtois
 * Licensed under the LGPL 3.0 or later (See LICENSE.md for details)
 */
package com.mtbs3d.minecrift.control;

import java.util.ArrayList;

import net.minecraft.src.GameSettings;

public abstract class ControlBinding {
	public interface ControlBindCallback {
		public void doneBinding();
	}
	
	boolean valid = true;
	
	public void setValid( boolean val ) { valid = val; }
	public boolean isValid() { return valid; }
	
	public static ArrayList<ControlBinding> bindings = new ArrayList<ControlBinding>();
	
	public static void CreateBindingList( GameSettings settings )
	{
		bindings.add( new WalkForwardBinding());
		bindings.add( new WalkBackwardBinding());
		bindings.add( new WalkLeftBinding());
		bindings.add( new WalkRightBinding());
		bindings.add( new JoystickAim.JoyAimYawBinding() );
		bindings.add( new JoystickAim.JoyAimPitchBinding() );
		bindings.add( new KeyControlBinding( settings.keyBindAttack ));
		bindings.add( new KeyControlBinding( settings.keyBindUseItem ));
		bindings.add( new KeyControlBinding( settings.keyBindJump ));
		bindings.add( new ItemLeftControlBinding() );
		bindings.add( new ItemRightControlBinding() );
		bindings.add( new KeyControlBinding( settings.keyBindDrop ));
		bindings.add( new KeyControlBinding( settings.keyBindChat ));
		bindings.add( new KeyControlBinding( settings.keyBindSneak ));
		bindings.add( new KeyControlBinding( settings.keyBindPickBlock ));
		bindings.add( new KeyControlBinding( settings.keyBindInventory ));
		bindings.add( new KeyControlBinding( settings.keyBindPlayerList ));
		//TODO: read from settings.keyBindings, instead, which is populated by Forge.
		bindings.add( new GuiScreenNaviator.GuiUpBinding() ); 
		bindings.add( new GuiScreenNaviator.GuiDownBinding() ); 
		bindings.add( new GuiScreenNaviator.GuiLeftBinding() ); 
		bindings.add( new GuiScreenNaviator.GuiRightBinding() ); 
		bindings.add( new GuiScreenNaviator.GuiSelectBinding() ); 
		bindings.add( new GuiScreenNaviator.GuiAltSelectBinding() ); 
		bindings.add( new GuiScreenNaviator.GuiBackBinding() ); 
		bindings.add( new GuiScreenNaviator.GuiShiftBinding() ); 
		
	}
	
	public boolean isAxis() { return false; }
	public boolean isGUI() { return false; }
	
	ControlBindCallback callback = null;
	
	public void setDoneBindingCallback( ControlBindCallback callback ) {
		this.callback = callback;
	}
	
	public void doneBinding() {
		if( callback != null )
			callback.doneBinding();
	}
	
	public ControlBinding( String desc ) {
		this.description = desc;
	}
	public String key;

	String description;
	public abstract void setValue( float value );
	public abstract void setState( boolean state );
	public String getDescription() {
		return description;
	}
	
	String bound = "None";
	public String boundTo() {
		return bound;
	}
	public void bindTo(String name) {
		System.out.println("[Minecrift]"+getDescription()+" bound to "+name+"!");
		bound = name;
	}
}
