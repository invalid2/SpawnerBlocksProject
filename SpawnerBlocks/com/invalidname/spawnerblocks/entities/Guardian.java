package com.invalidname.spawnerblocks.entities;

import org.newdawn.slick.opengl.Texture;

import dangerzone.DangerZone;
import dangerzone.GameModes;
import dangerzone.Player;
import dangerzone.TextureMapper;
import dangerzone.Utils;
import dangerzone.World;
import dangerzone.entities.Entity;
import dangerzone.entities.EntityLiving;
import dangerzone.entities.GenericTargetSorter;
import dangerzone.entities.Moose;
import dangerzone.entities.Temperament;
import dangerzone.items.Items;

public class Guardian extends EntityLiving {
	
	int perimeter = DangerZone.rand.nextInt(16)+8;
	double spawnx,spawny,spawnz;
	
	public Guardian(World w){
		super(w);
		maxrenderdist = 130; //in blocks
		this.height = 1.1f;
		this.width = 1.05f;
		uniquename = "SpawnerBlocks:Guardian";
		moveSpeed = 0.25f;
		canBreateUnderWater = true;
		setMaxHealth(20.0f);
		setHealth(20.0f);
		setDefense(4.0f);
		setAttackDamage(5.0f);
		movefrequency = 45;
		setExperience(100);
		setCanDespawn(false);
		canFly = true;
		setFlying(true);
		canBreateUnderWater = true;
		isImmuneToFire = true;
		takesFallDamage = false;
		canSwim = false;
		temperament = Temperament.PASSIVE_AGGRESSIVE;
		enable_hostile = true;
	}
	
	public void init(){
		super.init();
		eyeheight = 0.55f;
		
		spawnx = this.posx;
		spawny = this.posy;
		spawnz = this.posz;
		
	}
	
	public void do_something_else(){
		//additional behavior goes in this override
	}
	
	//we attack all small things!
	public boolean isSuitableTarget(Entity e){
		if(isIgnorable(e))return false;
		if(!CanProbablySeeEntity(e))return false;
		if(e instanceof Guardian)return false;
		if(e.temperament != Temperament.PASSIVE)return true;
		if(e instanceof Player && CanProbablySeeEntity(e))return true;
		
		return true;
	}
	
	public boolean isFoodItem(int foodid){
		if(foodid == Items.deadbug.itemID)return true;
		return false;
	}
	public String getLivingSound(){
		return null;
	}
	
	public String getHurtSound(){
		return "DangerZone:stonehit";
	}
	
	public String getDeathSound(){
		return "DangerZone:stonebreak";
	}
	
	public void doDeathDrops(){		
		if(world.rand.nextInt(10)==1)Utils.doDropRand(world, 0, Items.trophyanteater.itemID, 1f, dimension, posx, posy, posz);
		super.doDeathDrops();
	}
	
	//Model calls back out to see what texture to use.
	public Texture getTexture(){
		if(texture == null){
			//ENTITIES MUST USE TEXTUREMAPPER.GETTEXTURE()!!!!
			texture = TextureMapper.getTexture("spawnerblocksres/entities/"+ "guardian_texture.png");	//this is not fast, so we keep our own pointer!
		}
		return texture;
	}

}
