package com.invalidname.spawnerblocks;

import com.invalidname.spawnerblocks.biomes.SkyCubesBiome;
import com.invalidname.spawnerblocks.blocks.BlockArtificialSpawner;
import com.invalidname.spawnerblocks.blocks.BlockGuardian;
import com.invalidname.spawnerblocks.blocks.BlockTrollCockroachOre;
import com.invalidname.spawnerblocks.blocks.LattenFurnace;
import com.invalidname.spawnerblocks.blocks.LyInfuser;
import com.invalidname.spawnerblocks.blocks.Waymarks;
import com.invalidname.spawnerblocks.blocks.WaypointBlock;
import com.invalidname.spawnerblocks.entities.ButterFly;
import com.invalidname.spawnerblocks.entities.Dracorat;
import com.invalidname.spawnerblocks.entities.EntityArtificialSpawner;
import com.invalidname.spawnerblocks.entities.EntityLattenFurnace;
import com.invalidname.spawnerblocks.entities.EntityLyInfuser;
import com.invalidname.spawnerblocks.entities.Guardian;
import com.invalidname.spawnerblocks.entities.Impostor;
import com.invalidname.spawnerblocks.entities.ImpostorAnteater;
import com.invalidname.spawnerblocks.entities.ImpostorGoose;
import com.invalidname.spawnerblocks.entities.ModelArtificialSpawner;
import com.invalidname.spawnerblocks.entities.ModelButterFly;
import com.invalidname.spawnerblocks.entities.ModelDracorat;
import com.invalidname.spawnerblocks.entities.ModelGuardian;
import com.invalidname.spawnerblocks.entities.ModelImpostor;
import com.invalidname.spawnerblocks.entities.ModelImpostorAnteater;
import com.invalidname.spawnerblocks.entities.ModelImpostorGoose;
import com.invalidname.spawnerblocks.items.BottleWaterLy;
import com.invalidname.spawnerblocks.items.DarkMageWand;
import com.invalidname.spawnerblocks.items.GiantAxe;
import com.invalidname.spawnerblocks.items.GiantPickaxe;
import com.invalidname.spawnerblocks.items.GiantShovel;
import com.invalidname.spawnerblocks.items.GiantStick;
import com.invalidname.spawnerblocks.items.GiantSword;
import com.invalidname.spawnerblocks.items.ItemGeneric;
import com.invalidname.spawnerblocks.items.LumpLattenMelting;
import com.invalidname.spawnerblocks.items.Wayfinder;

import dangerzone.BaseMod;
import dangerzone.Cooking;
import dangerzone.Crafting;
import dangerzone.CreatureTypes;
import dangerzone.DangerZone;
import dangerzone.DangerZoneBase;
import dangerzone.Dimension;
import dangerzone.Dimensions;
import dangerzone.Ores;
import dangerzone.ServerHooker;
import dangerzone.ServerHooks;
import dangerzone.Spawnlist;
import dangerzone.WorldDecorator;
import dangerzone.WorldDecorators;
import dangerzone.biomes.Biome;
import dangerzone.biomes.BiomeManager;
import dangerzone.blocks.Block;
import dangerzone.blocks.BlockSpawner;
import dangerzone.blocks.Blocks;
import dangerzone.entities.Entities;
import dangerzone.items.Item;
import dangerzone.items.ItemSpawnEgg;
import dangerzone.items.ItemTrophy;
import dangerzone.items.Items;

public class SpawnerBlocksMain extends BaseMod {
	
	//Mod constants
	public static final String MOD_NAME = "SpawnerBlocks";
	public static final String DZ_VERSION = "2.1";
	public static final String MOD_VERSION = "1.0";
	
	public SpawnerBlocksMain () {
		super();
		waymarks.init();
	}
	
	//Set up for hidden treasure
	public static Waymarks waymarks = new Waymarks();
	
	//Recipe classes init
	public Cremating cremating = new Cremating();
	public LyInfusing lyinfusing = new LyInfusing();
	
	//WorldDecorator
	public static WorldDecorator spwd = new SpawnerBlocksWorldDecorator();
	
	//Dimensions
	//public static Dimension skycurrents = new Dimension("SpawnerBlocks:Sky Currents Dimension", 0.4f, 0.9f, 1f);
	
	
	//Biome managers
	//public static BiomeManager skycurrentsbiomemanager = new BiomeManager();
	
	//Biomes
	//public static Biome skycubesbiome = new SkyCubesBiome("SpawnerBlocks: Sky Cubes Biome");
	
	//Models
	public static ModelButterFly modelbutterFly = new ModelButterFly();
	public static ModelGuardian modelguardian = new ModelGuardian();
	public static ModelDracorat modeldracorat = new ModelDracorat();
	public static ModelImpostor modelimpostor = new ModelImpostor();
	public static ModelImpostorGoose modelimpostorgoose = new ModelImpostorGoose();
	public static ModelImpostorAnteater modelimpostoranteater = new ModelImpostorAnteater();
	
	//Blocks
	public static Block waypointblock = new WaypointBlock("SpawnerBlocks:Waymark");
	public static Block blockguardian = new BlockGuardian("SpawnerBlocks:BlockGuardian", "res/blocks/stone.png");
	public static Block trollsilverore = new BlockTrollCockroachOre("SpawnerBlocks:Troll Cockroach Ore", "res/blocks/oresilver.png", 150, 6);
	public static Block lattenfurnace = new LattenFurnace("SpawnerBlocks:Latten Furnace");
	public static Block lyinfuser = new LyInfuser("SpawnerBlocks:Ly Infuser", "spawnerblocksres/blocks/"+"lyinfuser.png");
	
	//Spawner blocks
	public static Block butterFlyspawner = new BlockSpawner("SpawnerBlocks:BUTTERfly Spawner", "SpawnerBlocks:BUTTERfly", 0.5f, 300, 4, -0.25f, 1.0f);
	public static Block dracoratspawner = new BlockSpawner("SpawnerBlocks:Dracorat Spawner", "SpawnerBlocks:Dracorat", 0.5f, 300, 4, -0.25f, 1.0f);
	public static Block impostorspawner = new BlockSpawner("SpawnerBlocks:Impostor Spawner", "SpawnerBlocks:Impostor", 0.25f, 600, 1, 0.55f, 1.55f);
	public static Block impostorgoosespawner = new BlockSpawner("SpawnerBlocks:Impostor Goose Spawner", "SpawnerBlocks:Impostor Goose", 0.40f, 600, 2, 0.55f, 1.55f);
	public static Block impostoranteaterspawner = new BlockSpawner("SpawnerBlocks:Impostor Anteater Spawner", "SpawnerBlocks:Impostor Anteater", 0.25f, 600, 1, 0.55f, 1.55f);
	
	//Artificial Spawners
	public static Block artificialwoodenspawner = new BlockArtificialSpawner("SpawnerBlocks:Artificial Wooden Spawner", "spawnerblocksres/blocks/"+"woodenartificialspawner.png","res/blocks/wood.png", 50, 3200, 1, 30);
	public static Block artificialstonespawner = new BlockArtificialSpawner("SpawnerBlocks:Artificial Stone Spawner", "spawnerblocksres/blocks/"+"stoneartificialspawner.png","res/blocks/stone.png", 60, 3000, 1, 35);
	public static Block artificialcopperspawner = new BlockArtificialSpawner("SpawnerBlocks:Artificial Copper Spawner", "spawnerblocksres/blocks/"+"copperartificialspawner.png","res/blocks/blockcopper.png", 100, 2500, 2, 50);
	public static Block artificialtinspawner = new BlockArtificialSpawner("SpawnerBlocks:Artificial Tin Spawner", "spawnerblocksres/blocks/"+"tinartificialspawner.png","res/blocks/blocktin.png", 120, 2000, 2, 75);
	public static Block artificialsilverspawner = new BlockArtificialSpawner("SpawnerBlocks:Artificial Silver Spawner", "spawnerblocksres/blocks/"+"silverartificialspawner.png","res/blocks/blocksilver.png", 160, 1500, 3, 80);
	public static Block artificialplatinumspawner = new BlockArtificialSpawner("SpawnerBlocks:Artificial Platinum Spawner", "spawnerblocksres/blocks/"+"platinumartificialspawner.png","res/blocks/blockplatinum.png", 200, 1000, 4, 90);
	
	//Items
	public static Item wayfinder = new Wayfinder("SpawnerBlocks:Wayfinder");
	public static Item darkmagewand = new DarkMageWand("SpawnerBlocks:Dark Mage Wand", "spawnerblocksres/items/"+"darkmagewand.png", "spawnerblocksres/items/"+"darkmagewand_texture.png", 36);
	public static Item wizardwand = new DarkMageWand("SpawnerBlocks:Wizard Wand", "spawnerblocksres/items/"+"wizardwand.png", "spawnerblocksres/items/"+"wizardwand_texture.png", 18);
	public static Item ammo = new Item("SpawnerBlocks:Ammo", "spawnerblocksres/items/"+"ammo.png");
	public static Item lumplatten = new Item("SpawnerBlocks:Latten Lump","spawnerblocksres/items/"+"lumplatten.png");
	public static Item lumplattenmelting = new LumpLattenMelting("SpawnerBlocks:Melting Latten Lump","spawnerblocksres/items/"+"lumplattenmelting.png");
	public static Item ash = new Item("SpawnerBlocks:Ash","spawnerblocksres/items/"+"ash.png");
	public static Item ashinert = new Item("SpawnerBlocks:Inert Ash","spawnerblocksres/items/"+"ashinert.png");
	public static Item bottlewater = new Item("SpawnerBlocks:Water Bottle","spawnerblocksres/items/"+"bottlewater.png");
	public static Item bottlewaterly = new BottleWaterLy("SpawnerBlocks:Ly Infused Water Bottle","spawnerblocksres/items/"+"bottlewaterly.png");
	public static Item dracscale = new ItemGeneric("SpawnerBlocks:Drac Scale","spawnerblocksres/items/"+"dracscale.png", 16);
	public static Item dracmembrane = new ItemGeneric("SpawnerBlocks:Drac Membrane","spawnerblocksres/items/"+"dracmembrane.png", 16);
	public static Item dracwing = new ItemGeneric("SpawnerBlocks:Drac Wing","spawnerblocksres/items/"+"dracwing.png", 1);
	
	//Spawneggs
	public static Item eggbutterFly = new ItemSpawnEgg("SpawnerBlocks:Spawn BUTTERfly", "spawnerblocksres/items/"+"eggbutterFly.png", "SpawnerBlocks:BUTTERfly");
	public static Item eggguardian = new ItemSpawnEgg("SpawnerBlocks:Spawn Guardian", "spawnerblocksres/items/"+"eggguardian.png", "SpawnerBlocks:Guardian");
	public static Item eggdracorat = new ItemSpawnEgg("SpawnerBlocks:Spawn Dracorat", "spawnerblocksres/items/"+"eggdracorat.png", "SpawnerBlocks:Dracorat");
	public static Item eggimpostor = new ItemSpawnEgg("SpawnerBlocks:Spawn Impostor", "spawnerblocksres/items/"+"eggimpostor.png", "SpawnerBlocks:Impostor");
	public static Item eggimpostorgoose = new ItemSpawnEgg("SpawnerBlocks:Spawn Impostor Goose", "spawnerblocksres/items/"+"eggimpostorgoose.png", "SpawnerBlocks:Impostor Goose");
	public static Item eggimpostoranteater = new ItemSpawnEgg("SpawnerBlocks:Spawn Impostor Anteater", "spawnerblocksres/items/"+"eggimpostoranteater.png", "SpawnerBlocks:Impostor Anteater");
	
	//Trophies
	public static Item trophybutterFly = new ItemTrophy("SpawnerBlocks:BUTTERfly Trophy", "SpawnerBlocks:BUTTERfly", 0.45f, 256);
	public static Item trophydracorat = new ItemTrophy("SpawnerBlocks:Dracorat Trophy", "SpawnerBlocks:Dracorat", 0.45f, 128);
	public static Item trophyimpostor = new ItemTrophy("SpawnerBlocks:Impostor Trophy", "SpawnerBlocks:Impostor", 0.35f, 16);
	public static Item trophyimpostorgoose = new ItemTrophy("SpawnerBlocks:Impostor Goose Trophy", "SpawnerBlocks:Impostor Goose", 0.5f, 16);
	public static Item trophyimpostoranteater = new ItemTrophy("SpawnerBlocks:Impostor Anteater Trophy", "SpawnerBlocks:Impostor Anteater", 0.45f, 16);
	
	//Giant Tools
	public static Item giantstick = new GiantStick("SpawnerBlocks:Giant Stick", "spawnerblocksres/items/"+"giantstick.png");
	
	public static Item giantwoodensword = new GiantSword("SpawnerBlocks:Giant Wooden Sword", "spawnerblocksres/items/"+"giantwoodensword.png", "spawnerblocksres/items/"+"giantwoodensword_texture.png", 200, 6);
	public static Item giantwoodenpickaxe = new GiantPickaxe("SpawnerBlocks:Giant Wooden Pickaxe", "spawnerblocksres/items/"+"giantwoodenpickaxe.png", "spawnerblocksres/items/"+"giantwoodenpickaxe_texture.png", 400, 5, 8);
	public static Item giantwoodenaxe = new GiantAxe("SpawnerBlocks:Giant Wooden Axe", "spawnerblocksres/items/"+"giantwoodenaxe.png", "spawnerblocksres/items/"+"giantwoodenaxe_texture.png", 200, 5, 4);
	public static Item giantwoodenshovel = new GiantShovel("SpawnerBlocks:Giant Wooden Shovel", "spawnerblocksres/items/"+"giantwoodenshovel.png", "spawnerblocksres/items/"+"giantwoodenshovel_texture.png", 300, 3, 2);
	
	public static Item giantstonesword = new GiantSword("SpawnerBlocks:Giant Stone Sword", "spawnerblocksres/items/"+"giantstonesword.png", "spawnerblocksres/items/"+"giantstonesword_texture.png", 400, 8);
	public static Item giantstonepickaxe = new GiantPickaxe("SpawnerBlocks:Giant Stone Pickaxe", "spawnerblocksres/items/"+"giantstonepickaxe.png", "spawnerblocksres/items/"+"giantstonepickaxe_texture.png", 800, 8, 10);
	public static Item giantstoneaxe = new GiantAxe("SpawnerBlocks:Giant Stone Axe", "spawnerblocksres/items/"+"giantstoneaxe.png", "spawnerblocksres/items/"+"giantstoneaxe_texture.png", 400, 8, 6);
	public static Item giantstoneshovel = new GiantShovel("SpawnerBlocks:Giant Stone Shovel", "spawnerblocksres/items/"+"giantstoneshovel.png", "spawnerblocksres/items/"+"giantstoneshovel_texture.png", 600, 4, 3);
	
	public static Item giantcoppersword = new GiantSword("SpawnerBlocks:Giant Copper Sword", "spawnerblocksres/items/"+"giantcoppersword.png", "spawnerblocksres/items/"+"giantcoppersword_texture.png", 600, 13);
	public static Item giantcopperpickaxe = new GiantPickaxe("SpawnerBlocks:Giant Copper Pickaxe", "spawnerblocksres/items/"+"giantcopperpickaxe.png", "spawnerblocksres/items/"+"giantcopperpickaxe_texture.png", 1300, 10, 13);
	public static Item giantcopperaxe = new GiantAxe("SpawnerBlocks:Giant Copper Axe", "spawnerblocksres/items/"+"giantcopperaxe.png", "spawnerblocksres/items/"+"giantcopperaxe_texture.png", 600, 11, 9);
	public static Item giantcoppershovel = new GiantShovel("SpawnerBlocks:Giant Copper Shovel", "spawnerblocksres/items/"+"giantcoppershovel.png", "spawnerblocksres/items/"+"giantcoppershovel_texture.png", 1100, 8, 9);
	
	public static Item gianttinsword = new GiantSword("SpawnerBlocks:Giant Tin Sword", "spawnerblocksres/items/"+"gianttinsword.png", "spawnerblocksres/items/"+"gianttinsword_texture.png", 800, 24);
	public static Item gianttinpickaxe = new GiantPickaxe("SpawnerBlocks:Giant Tin Pickaxe", "spawnerblocksres/items/"+"gianttinpickaxe.png", "spawnerblocksres/items/"+"gianttinpickaxe_texture.png", 1600, 16, 15);
	public static Item gianttinaxe = new GiantAxe("SpawnerBlocks:Giant Tin Axe", "spawnerblocksres/items/"+"gianttinaxe.png", "spawnerblocksres/items/"+"gianttinaxe_texture.png", 900, 20, 13);
	public static Item gianttinshovel = new GiantShovel("SpawnerBlocks:Giant Tin Shovel", "spawnerblocksres/items/"+"gianttinshovel.png", "spawnerblocksres/items/"+"gianttinshovel_texture.png", 1400, 12, 11);
	
	public static Item giantsilversword = new GiantSword("SpawnerBlocks:Giant Silver Sword", "spawnerblocksres/items/"+"giantsilversword.png", "spawnerblocksres/items/"+"giantsilversword_texture.png", 1200, 30);
	public static Item giantsilverpickaxe = new GiantPickaxe("SpawnerBlocks:Giant Silver Pickaxe", "spawnerblocksres/items/"+"giantsilverpickaxe.png", "spawnerblocksres/items/"+"giantsilverpickaxe_texture.png", 1900, 24, 18);
	public static Item giantsilveraxe = new GiantAxe("SpawnerBlocks:Giant Silver Axe", "spawnerblocksres/items/"+"giantsilveraxe.png", "spawnerblocksres/items/"+"giantsilveraxe_texture.png", 1200, 28, 18);
	public static Item giantsilvershovel = new GiantShovel("SpawnerBlocks:Giant Silver Shovel", "spawnerblocksres/items/"+"giantsilvershovel.png", "spawnerblocksres/items/"+"giantsilvershovel_texture.png", 1700, 20, 15);
	
	public static Item giantplatinumsword = new GiantSword("SpawnerBlocks:Giant Platinum Sword", "spawnerblocksres/items/"+"giantplatinumsword.png", "spawnerblocksres/items/"+"giantplatinumsword_texture.png", 2200, 42);
	public static Item giantplatinumpickaxe = new GiantPickaxe("SpawnerBlocks:Giant Platinum Pickaxe", "spawnerblocksres/items/"+"giantplatinumpickaxe.png", "spawnerblocksres/items/"+"giantplatinumpickaxe_texture.png", 2600, 32, 24);
	public static Item giantplatinumaxe = new GiantAxe("SpawnerBlocks:Giant Platinum Axe", "spawnerblocksres/items/"+"giantplatinumaxe.png", "spawnerblocksres/items/"+"giantplatinumaxe_texture.png", 2000, 36, 20);
	public static Item giantplatinumshovel = new GiantShovel("SpawnerBlocks:Giant Platinum Shovel", "spawnerblocksres/items/"+"giantplatinumshovel.png", "spawnerblocksres/items/"+"giantplatinumshovel_texture.png", 2400, 28, 20);
	
	public static ServerHooks spawnerblocksserverhooks = new SpawnerBlocksServerHooks();
	
	//public static Block test = new BlockTest();
	public String getModName()
	{
		return MOD_NAME+" ver"+MOD_VERSION;
	}
	
	public String versionBuiltWith()
	{
		return DZ_VERSION;
	}
	
	public void registerThings(){		
		//World Decorator
		WorldDecorators.registerWorldDecorator(spwd);
		
		//Dimensions
		//Dimensions.registerDimension(skycurrents);
		
		//Biome Managers
		//skycurrents.registerBiomeManager(skycurrentsbiomenanager);
		
		//Biomes
		//skycurrentsbiomenanager.registerBiome(skycubesbiome);
		
		//Entities
		Entities.registerEntity(ButterFly.class, "SpawnerBlocks:BUTTERfly", modelbutterFly);
		Entities.registerEntity(Guardian.class, "SpawnerBlocks:Guardian", modelguardian);
		Entities.registerEntity(Impostor.class, "SpawnerBlocks:Impostor", modelimpostor);
		Entities.registerEntity(ImpostorGoose.class, "SpawnerBlocks:Impostor Goose", modelimpostorgoose);
		Entities.registerEntity(ImpostorAnteater.class, "SpawnerBlocks:Impostor Anteater", modelimpostoranteater);
		Entities.registerEntity(Dracorat.class, "SpawnerBlocks:Dracorat", modeldracorat);
		Entities.registerEntity(EntityArtificialSpawner.class, "SpawnerBlocks:EntityArtificialSpawner", new ModelArtificialSpawner());
		Entities.registerEntity(EntityLattenFurnace.class, "SpawnerBlocks:EntityLattenFurnace", null);
		Entities.registerEntity(EntityLyInfuser.class, "SpawnerBlocks:EntityLyInfuser", null);
		
		//Sounds
		DangerZone.soundmangler.registerSound("SpawnerBlocks:darkmagewand", "spawnerblocksres/sounds/"+"darkmagewand.wav");
		DangerZone.soundmangler.registerSound("SpawnerBlocks:guardian_activate", "spawnerblocksres/sounds/"+"guardian_activate.wav");
		DangerZone.soundmangler.registerSound("SpawnerBlocks:lattenfurnace", "spawnerblocksres/sounds/"+"lattenfurnace.wav");
		DangerZone.soundmangler.registerSound("SpawnerBlocks:lyinfuser_input", "spawnerblocksres/sounds/"+"lyinfuser_input.wav");
		DangerZone.soundmangler.registerSound("SpawnerBlocks:lyinfuser_output", "spawnerblocksres/sounds/"+"lyinfuser_output.wav");
		DangerZone.soundmangler.registerSound("SpawnerBlocks:wayfinder", "spawnerblocksres/sounds/"+"wayfinder.wav");
		DangerZone.soundmangler.registerSound("SpawnerBlocks:impostor1", "spawnerblocksres/sounds/"+"impostor1.wav");
		DangerZone.soundmangler.registerSound("SpawnerBlocks:impostor2", "spawnerblocksres/sounds/"+"impostor2.wav");
		
		//Blocks
		Blocks.registerBlock(waypointblock);
		Blocks.registerBlock(blockguardian);
		Blocks.registerBlock(trollsilverore);
		//Blocks.registerBlock(test);
		Blocks.registerBlock(lattenfurnace);
		Blocks.registerBlock(lyinfuser);
		
		//Spawner blocks
		Blocks.registerBlock(butterFlyspawner);
		Blocks.registerBlock(dracoratspawner);
		Blocks.registerBlock(impostorspawner);
		Blocks.registerBlock(impostorgoosespawner);
		Blocks.registerBlock(impostoranteaterspawner);
		
		//Artificial Spawners
		Blocks.registerBlock(artificialwoodenspawner);
		Blocks.registerBlock(artificialstonespawner);
		Blocks.registerBlock(artificialcopperspawner);
		Blocks.registerBlock(artificialtinspawner);
		Blocks.registerBlock(artificialsilverspawner);
		Blocks.registerBlock(artificialplatinumspawner);
		
		//Items
		Items.registerItem(ammo);
		Items.registerItem(wayfinder);
		Items.registerItem(wizardwand);
		Items.registerItem(darkmagewand);
		Items.registerItem(lumplatten);
		Items.registerItem(lumplattenmelting);
		Items.registerItem(ash);
		Items.registerItem(ashinert);
		Items.registerItem(bottlewater);
		Items.registerItem(bottlewaterly);
		Items.registerItem(dracscale);
		Items.registerItem(dracmembrane);
		Items.registerItem(dracwing);
		
		//Spawneggs
		Items.registerItem(eggbutterFly);
		Items.registerItem(eggguardian);
		Items.registerItem(eggdracorat);
		Items.registerItem(eggimpostor);
		Items.registerItem(eggimpostorgoose);
		Items.registerItem(eggimpostoranteater);
		
		//Trophies
		Items.registerItem(trophybutterFly);
		Items.registerItem(trophydracorat);
		Items.registerItem(trophyimpostor);
		Items.registerItem(trophyimpostorgoose);
		Items.registerItem(trophyimpostoranteater);
		
		//Hardware
		Items.registerItem(giantstick);
		
		Items.registerItem(giantwoodensword);
		Items.registerItem(giantwoodenaxe);
		Items.registerItem(giantwoodenpickaxe);
		Items.registerItem(giantwoodenshovel);
		
		Items.registerItem(giantstonesword);
		Items.registerItem(giantstoneaxe);
		Items.registerItem(giantstonepickaxe);
		Items.registerItem(giantstoneshovel);
		
		Items.registerItem(giantcoppersword);
		Items.registerItem(giantcopperaxe);
		Items.registerItem(giantcopperpickaxe);
		Items.registerItem(giantcoppershovel);
		
		Items.registerItem(gianttinsword);
		Items.registerItem(gianttinaxe);
		Items.registerItem(gianttinpickaxe);
		Items.registerItem(gianttinshovel);
		
		Items.registerItem(giantsilversword);
		Items.registerItem(giantsilveraxe);
		Items.registerItem(giantsilverpickaxe);
		Items.registerItem(giantsilvershovel);
		
		Items.registerItem(giantplatinumsword);
		Items.registerItem(giantplatinumaxe);
		Items.registerItem(giantplatinumpickaxe);
		Items.registerItem(giantplatinumshovel);
		
		//Ores registry
		Ores.registerOre(trollsilverore, Blocks.stone, null, null, 0, 50, 30, 8, 0);
		Ores.registerOre(trollsilverore, Blocks.stone, Dimensions.skyislandsdimension, null, 120, 200, 15, 8, 0);
		
		//Cremating recipes ie Latten Furnace recipes
		Cremating.registerCrematingRecipe(ash, ashinert, 15, 5);
		Cremating.registerCrematingRecipe(Blocks.dirt, ash, 15, 10);
		Cremating.registerCrematingRecipe(Blocks.grassblock, ash, 20, 25);
		Cremating.registerCrematingRecipe(Blocks.roachnest, ash, 20, 30);
		Cremating.registerCrematingRecipe(Blocks.redwoodlog, ash, 20, 30);
		Cremating.registerCrematingRecipe(Blocks.darkplywood, ash, 20, 25);
		Cremating.registerCrematingRecipe(Blocks.redwoodleaves, ash, 20, 20);
		Cremating.registerCrematingRecipe(Blocks.redleaves, ash, 20, 20);
		Cremating.registerCrematingRecipe(Blocks.orangeleaves, ash, 20, 20);
		Cremating.registerCrematingRecipe(Blocks.yellowleaves, ash, 20, 20);
		Cremating.registerCrematingRecipe(Blocks.greenleaves, ash, 20, 20);
		Cremating.registerCrematingRecipe(Blocks.log, ash, 20, 30);
		Cremating.registerCrematingRecipe(Blocks.plywood, ash, 20, 25);
		Cremating.registerCrematingRecipe(Blocks.leaves, ash, 20, 20);
		Cremating.registerCrematingRecipe(Blocks.willowlog, ash, 20, 30);
		Cremating.registerCrematingRecipe(Blocks.lightplywood, ash, 20, 25);
		Cremating.registerCrematingRecipe(Blocks.willowleaves, ash, 20, 20);
		Cremating.registerCrematingRecipe(Blocks.pineleaves, ash, 20, 20);
		Cremating.registerCrematingRecipe(Blocks.grass, ash, 20, 10);
		Cremating.registerCrematingRecipe(Blocks.peachleaves, ash, 20, 20);
		Cremating.registerCrematingRecipe(Blocks.cherryleaves, ash, 20, 20);
		Cremating.registerCrematingRecipe(Blocks.appleleaves, ash, 20, 20);
		Cremating.registerCrematingRecipe(Blocks.workbench, ash, 20, 25);
		Cremating.registerCrematingRecipe(Blocks.chest, ash, 20, 22);
		Cremating.registerCrematingRecipe(Blocks.stand, ash, 20, 25);
		Cremating.registerCrematingRecipe(Blocks.lightstick, ash, 20, 15);
		Cremating.registerCrematingRecipe(Blocks.darkstick, ash, 20, 15);
		Cremating.registerCrematingRecipe(Blocks.autofence, ash, 20, 28);
		Cremating.registerCrematingRecipe(Blocks.post, ash, 20, 25);
		Cremating.registerCrematingRecipe(Blocks.ladder, ash, 20, 25);
		Cremating.registerCrematingRecipe(Blocks.flower_red, ash, 20, 18);
		Cremating.registerCrematingRecipe(Blocks.flower_pink, ash, 20, 18);
		Cremating.registerCrematingRecipe(Blocks.flower_purple, ash, 20, 18);
		Cremating.registerCrematingRecipe(Blocks.flower_yellow, ash, 20, 18);
		Cremating.registerCrematingRecipe(Blocks.butterfly_plant, ash, 20, 12);
		Cremating.registerCrematingRecipe(Blocks.sapling_tallwood, ash, 20, 16);
		Cremating.registerCrematingRecipe(Blocks.sapling_cherry, ash, 20, 16);
		Cremating.registerCrematingRecipe(Blocks.sapling_peach, ash, 20, 16);
		Cremating.registerCrematingRecipe(Blocks.sapling_apple, ash, 20, 16);
		Cremating.registerCrematingRecipe(Blocks.sapling_scragglyredwood, ash, 20, 16);
		Cremating.registerCrematingRecipe(Blocks.sapling_scraggly, ash, 20, 16);
		Cremating.registerCrematingRecipe(Blocks.sapling_bigroundredwood, ash, 20, 16);
		Cremating.registerCrematingRecipe(Blocks.sapling_bigroundwillow, ash, 20, 16);
		Cremating.registerCrematingRecipe(Blocks.sapling_flower, ash, 20, 16);
		Cremating.registerCrematingRecipe(Blocks.sapling_flowertwo, ash, 20, 16);
		Cremating.registerCrematingRecipe(Blocks.sapling_scrub, ash, 20, 16);
		Cremating.registerCrematingRecipe(Blocks.sapling_flowernormal, ash, 20, 16);
		Cremating.registerCrematingRecipe(Blocks.sapling_umbrella, ash, 20, 16);
		Cremating.registerCrematingRecipe(Blocks.sapling_bulb, ash, 20, 16);
		Cremating.registerCrematingRecipe(Blocks.sapling_looplowspiral, ash, 20, 16);
		Cremating.registerCrematingRecipe(Blocks.sapling_loop, ash, 20, 16);
		Cremating.registerCrematingRecipe(Blocks.sapling_generic, ash, 20, 16);
		Cremating.registerCrematingRecipe(Blocks.sapling_tallwillow, ash, 20, 16);
		Cremating.registerCrematingRecipe(Blocks.sapling_scrub, ash, 20, 16);
		Cremating.registerCrematingRecipe(Blocks.sapling_flowernormal, ash, 20, 16);
		Cremating.registerCrematingRecipe(Blocks.sapling_umbrella, ash, 20, 16);
		Cremating.registerCrematingRecipe(Blocks.sapling_bulb, ash, 20, 16);
		Cremating.registerCrematingRecipe(Blocks.sapling_looplowspiral, ash, 20, 16);
		Cremating.registerCrematingRecipe(Blocks.sapling_loop, ash, 20, 16);
		Cremating.registerCrematingRecipe(Blocks.sapling_generic, ash, 20, 16);
		Cremating.registerCrematingRecipe(Blocks.sapling_tallwillow, ash, 20, 16);
		Cremating.registerCrematingRecipe(Blocks.sapling_vase, ash, 20, 16);
		Cremating.registerCrematingRecipe(Blocks.sapling_spiral, ash, 20, 16);
		Cremating.registerCrematingRecipe(Blocks.sapling_loopspiral, ash, 20, 16);
		Cremating.registerCrematingRecipe(Blocks.sapling_bowl, ash, 20, 16);
		Cremating.registerCrematingRecipe(Blocks.sapling_doublebowl, ash, 20, 16);
		Cremating.registerCrematingRecipe(Blocks.reefgrass, ash, 20, 14);
		Cremating.registerCrematingRecipe(Blocks.redreefgrass, ash, 20, 14);
		Cremating.registerCrematingRecipe(Blocks.redcoral, ash, 20, 14);
		Cremating.registerCrematingRecipe(Blocks.yellowcoral, ash, 20, 14);
		Cremating.registerCrematingRecipe(Blocks.bluecoral, ash, 20, 14);
		Cremating.registerCrematingRecipe(Blocks.firecoral, ash, 20, 14);
		Cremating.registerCrematingRecipe(Blocks.redfancoral, ash, 20, 14);
		Cremating.registerCrematingRecipe(Blocks.blackfancoral, ash, 20, 14);
		Cremating.registerCrematingRecipe(Blocks.seaweed, ash, 20, 14);
		Cremating.registerCrematingRecipe(Blocks.tallseaweed, ash, 20, 14);
		Cremating.registerCrematingRecipe(Blocks.brownseaweed, ash, 20, 14);
		Cremating.registerCrematingRecipe(Blocks.browntallseaweed, ash, 20, 14);
		Cremating.registerCrematingRecipe(Blocks.yellowseaweed, ash, 20, 14);
		Cremating.registerCrematingRecipe(Blocks.yellowtallseaweed, ash, 20, 14);
		Cremating.registerCrematingRecipe(Blocks.kelp, ash, 20, 14);
		Cremating.registerCrematingRecipe(Blocks.tallkelp, ash, 20, 14);
		Cremating.registerCrematingRecipe(Blocks.fishnursery, ash, 20, 14);
		Cremating.registerCrematingRecipe(Blocks.stickyblock, ash, 20, 14);
		Cremating.registerCrematingRecipe(Blocks.desk, ash, 20, 14);
		Cremating.registerCrematingRecipe(Items.stick, ash, 20, 20);
		Cremating.registerCrematingRecipe(Items.raft, ash, 20, 28);
		Cremating.registerCrematingRecipe(Items.squeaktoy, ash, 20, 18);
		Cremating.registerCrematingRecipe(Items.sharktooth, ash, 20, 24);
		Cremating.registerCrematingRecipe(Items.sharkfin, ash, 20, 26);
		Cremating.registerCrematingRecipe(Items.corn, ash, 20, 30);
		Cremating.registerCrematingRecipe(Items.moosemeat, ash, 20, 32);
		Cremating.registerCrematingRecipe(Items.moosemeat_cooked, ash, 20, 22);
		Cremating.registerCrematingRecipe(Items.goosemeat, ash, 20, 20);
		Cremating.registerCrematingRecipe(Items.goosemeat_cooked, ash, 20, 30);
		Cremating.registerCrematingRecipe(Items.ostrichmeat, ash, 20, 30);
		Cremating.registerCrematingRecipe(Items.ostrichmeat_cooked, ash, 20, 20);
		Cremating.registerCrematingRecipe(Items.fishmeat, ash, 20, 32);
		Cremating.registerCrematingRecipe(Items.fishmeat_cooked, ash, 20, 20);
		Cremating.registerCrematingRecipe(Items.radish, ash, 20, 24);
		Cremating.registerCrematingRecipe(Items.rice, ash, 20, 24);
		Cremating.registerCrematingRecipe(Items.peach, ash, 20, 24);
		Cremating.registerCrematingRecipe(Items.apple, ash, 20, 24);
		Cremating.registerCrematingRecipe(Items.cherries, ash, 20, 22);
		Cremating.registerCrematingRecipe(Items.deadbug, ash, 20, 24);
		Cremating.registerCrematingRecipe(Items.bread, ash, 20, 26);
		Cremating.registerCrematingRecipe(Items.cheese, ash, 20, 24);
		Cremating.registerCrematingRecipe(Items.furball, ash, 20, 25);
		Cremating.registerCrematingRecipe(Items.vampireteeth, ash, 20, 32);
		Cremating.registerCrematingRecipe(Items.experiencebottle, ash, 20, 27);
		Cremating.registerCrematingRecipe(Items.moosebone, ash, 20, 26);
		Cremating.registerCrematingRecipe(Items.feather, ash, 20, 24);
		Cremating.registerCrematingRecipe(Items.peachseed, ash, 20, 18);
		Cremating.registerCrematingRecipe(Items.appleseed, ash, 20, 18);
		Cremating.registerCrematingRecipe(Items.cherryseed, ash, 20, 18);
		Cremating.registerCrematingRecipe(Items.string, ash, 20, 22);
		Cremating.registerCrematingRecipe(Items.door, ash, 20, 24);
		Cremating.registerCrematingRecipe(Items.sign, ash, 20, 24);
		Cremating.registerCrematingRecipe(Items.charcoalstick, ash, 20, 16);
		Cremating.registerCrematingRecipe(Items.woodchips, ash, 20, 18);
		Cremating.registerCrematingRecipe(Items.woodpulp, ash, 20, 16);
		Cremating.registerCrematingRecipe(Items.paper, ash, 20, 14);
		Cremating.registerCrematingRecipe(giantstick, ash, 20, 26);
		Cremating.registerCrematingRecipe(Items.woodensword, ash, 20, 22);
		Cremating.registerCrematingRecipe(Items.woodenpickaxe, ash, 20, 22);
		Cremating.registerCrematingRecipe(Items.woodenaxe, ash, 20, 22);
		Cremating.registerCrematingRecipe(Items.woodenshovel, ash, 20, 22);
		Cremating.registerCrematingRecipe(Items.woodenhoe, ash, 20, 22);
		Cremating.registerCrematingRecipe(Items.arrow, ash, 20, 20);
		Cremating.registerCrematingRecipe(Items.bow, ash, 20, 26);
		Cremating.registerCrematingRecipe(Items.deadstickfish, ash, 20, 20);
		Cremating.registerCrematingRecipe(Items.pufferfishspikes, ash, 20, 20);
		Cremating.registerCrematingRecipe(Items.moosehead, ash, 20, 40);
		Cremating.registerCrematingRecipe(giantwoodensword, ash, 20, 28);
		Cremating.registerCrematingRecipe(giantwoodenpickaxe, ash, 20, 28);
		Cremating.registerCrematingRecipe(giantwoodenaxe, ash, 20, 28);
		Cremating.registerCrematingRecipe(giantwoodenshovel, ash, 20, 28);
		Cremating.registerCrematingRecipe(artificialwoodenspawner, ash, 20, 36);
		Cremating.registerCrematingRecipe(Items.egganotherfish, ash, 20, 30);
		Cremating.registerCrematingRecipe(Items.egganteater, ash, 20, 50);
		Cremating.registerCrematingRecipe(Items.eggbulletbat, ash, 20, 30);
		Cremating.registerCrematingRecipe(Items.eggbutterfly, ash, 20, 30);
		Cremating.registerCrematingRecipe(Items.eggbutterflyfish, ash, 20, 30);
		Cremating.registerCrematingRecipe(Items.eggcloud, ash, 20, 30);
		Cremating.registerCrematingRecipe(Items.eggcockroach, ash, 20, 20);
		Cremating.registerCrematingRecipe(Items.eggdesertrainfrog, ash, 20, 20);
		Cremating.registerCrematingRecipe(Items.eggeel, ash, 20, 30);
		Cremating.registerCrematingRecipe(Items.eggfish, ash, 20, 20);
		Cremating.registerCrematingRecipe(Items.eggfrog, ash, 20, 20);
		Cremating.registerCrematingRecipe(Items.eggghost, ash, 20, 15);
		Cremating.registerCrematingRecipe(Items.eggghostskelly, ash, 20, 15);
		Cremating.registerCrematingRecipe(Items.egggoose, ash, 20, 30);
		Cremating.registerCrematingRecipe(Items.egggosling, ash, 20, 25);
		Cremating.registerCrematingRecipe(Items.egghammerhead, ash, 20, 50);
		Cremating.registerCrematingRecipe(Items.eggmartian, ash, 20, 50);
		Cremating.registerCrematingRecipe(Items.eggmermaid, ash, 20, 50);
		Cremating.registerCrematingRecipe(Items.eggminnow, ash, 20, 40);
		Cremating.registerCrematingRecipe(Items.eggmoose, ash, 20, 50);
		Cremating.registerCrematingRecipe(Items.eggostrich, ash, 20, 30);
		Cremating.registerCrematingRecipe(Items.eggpiranah, ash, 20, 35);
		Cremating.registerCrematingRecipe(Items.eggpufferfish, ash, 20, 30);
		Cremating.registerCrematingRecipe(Items.eggrat, ash, 20, 35);
		Cremating.registerCrematingRecipe(Items.eggskeletorus, ash, 20, 50);
		Cremating.registerCrematingRecipe(Items.eggsnarler, ash, 20, 40);
		Cremating.registerCrematingRecipe(Items.eggsparklemuffin, ash, 20, 50);
		Cremating.registerCrematingRecipe(Items.eggstickfish, ash, 20, 20);
		Cremating.registerCrematingRecipe(Items.eggthecount, ash, 20, 55);
		Cremating.registerCrematingRecipe(Items.eggvampire, ash, 20, 50);
		Cremating.registerCrematingRecipe(Items.eggvampiremoose, ash, 20, 50);
		Cremating.registerCrematingRecipe(Items.eggvixen, ash, 20, 50);
		Cremating.registerCrematingRecipe(Items.eggvolcano, ash, 20, 20);
		Cremating.registerCrematingRecipe(Items.eggwerewolf, ash, 20, 50);
		Cremating.registerCrematingRecipe(eggbutterFly, ash, 20, 30);
		Cremating.registerCrematingRecipe(eggguardian, ash, 20, 30);
		Cremating.registerCrematingRecipe(eggdracorat, ash, 20, 35);
		Cremating.registerCrematingRecipe(eggimpostor, ash, 20, 50);
		Cremating.registerCrematingRecipe(eggimpostoranteater, ash, 20, 50);
		Cremating.registerCrematingRecipe(eggimpostorgoose, ash, 20, 35);
		
		//Infusing recipes
		LyInfusing.registerInfusingRecipe(bottlewater, bottlewaterly, 150);
		LyInfusing.registerInfusingRecipe(Items.frog_confusion1, Items.frog_confusion2, 120);
		LyInfusing.registerInfusingRecipe(Items.frog_confusion2, Items.frog_confusion3, 120);
		LyInfusing.registerInfusingRecipe(Items.frog_morph1, Items.frog_morph2, 120);
		LyInfusing.registerInfusingRecipe(Items.frog_morph2, Items.frog_morph3, 120);
		LyInfusing.registerInfusingRecipe(Items.frog_poison1, Items.frog_poison2, 120);
		LyInfusing.registerInfusingRecipe(Items.frog_poison2, Items.frog_poison3, 120);
		LyInfusing.registerInfusingRecipe(Items.frog_regen1, Items.frog_regen2, 120);
		LyInfusing.registerInfusingRecipe(Items.frog_regen2, Items.frog_regen3, 120);
		LyInfusing.registerInfusingRecipe(Items.frog_slowness1, Items.frog_slowness2, 120);
		LyInfusing.registerInfusingRecipe(Items.frog_slowness2, Items.frog_slowness3, 120);
		LyInfusing.registerInfusingRecipe(Items.frog_speed1, Items.frog_speed2, 120);
		LyInfusing.registerInfusingRecipe(Items.frog_speed2, Items.frog_speed3, 120);
		LyInfusing.registerInfusingRecipe(Items.frog_strength1, Items.frog_strength2, 120);
		LyInfusing.registerInfusingRecipe(Items.frog_strength2, Items.frog_strength3, 120);
		LyInfusing.registerInfusingRecipe(Items.frog_weakness1, Items.frog_weakness2, 120);
		LyInfusing.registerInfusingRecipe(Items.frog_weakness2, Items.frog_weakness3, 120);
		
		//Cooking recipes
		Cooking.registerCookingRecipe(lumplatten, lumplattenmelting, 20);
		
		//Crafting recipes - Blocks
		Crafting.registerCraftingRecipe(Items.light, Items.light, Items.light, Items.dark, Items.emerald, Items.dark, Items.dark, Items.dark, Items.dark, waypointblock, 1, true);
		
		//Crafting recipes - Artificial Spawners
		Crafting.registerCraftingRecipe(Blocks.log, Blocks.log, Blocks.log, Items.light, null, Items.dark, Blocks.log, Blocks.log, Blocks.log, artificialwoodenspawner, 1, true);
		Crafting.registerCraftingRecipe(Blocks.redwoodlog, Blocks.redwoodlog, Blocks.redwoodlog, Items.light, null, Items.dark, Blocks.redwoodlog, Blocks.redwoodlog, Blocks.redwoodlog, artificialwoodenspawner, 1, true);
		Crafting.registerCraftingRecipe(Blocks.willowlog, Blocks.willowlog, Blocks.willowlog, Items.light, null, Items.dark, Blocks.willowlog, Blocks.willowlog, Blocks.willowlog, artificialwoodenspawner, 1, true);
		Crafting.registerCraftingRecipe(Blocks.stone, Blocks.stone, Blocks.stone, Items.light, null, Items.dark, Blocks.stone, Blocks.stone, Blocks.stone, artificialstonespawner, 1, true);
		Crafting.registerCraftingRecipe(Blocks.blockcopper, Blocks.blockcopper, Blocks.blockcopper, Items.light, null, Items.dark, Blocks.blockcopper, Blocks.blockcopper, Blocks.blockcopper, artificialcopperspawner, 1, true);
		Crafting.registerCraftingRecipe(Blocks.blocktin, Blocks.blocktin, Blocks.blocktin, Items.light, null, Items.dark, Blocks.blocktin, Blocks.blocktin, Blocks.blocktin, artificialtinspawner, 1, true);
		Crafting.registerCraftingRecipe(Blocks.blocksilver, Blocks.blocksilver, Blocks.blocksilver, Items.light, null, Items.dark, Blocks.blocksilver, Blocks.blocksilver, Blocks.blocksilver, artificialsilverspawner, 1, true);
		Crafting.registerCraftingRecipe(Blocks.blockplatinum, Blocks.blockplatinum, Blocks.blockplatinum, Items.light, null, Items.dark, Blocks.blockplatinum, Blocks.blockplatinum, Blocks.blockplatinum, artificialplatinumspawner, 1, true);
		
		//Crafting recipes - Items
		Crafting.registerCraftingRecipe(null, Items.light, null, Items.light, Items.emerald, Items.dark, null, Items.dark, null, wayfinder, 1, true);
		Crafting.registerCraftingRecipe(Items.lumpcopper, Items.lumpcopper, Items.lumpcopper, Items.lumptin, Items.lumptin, Items.lumptin, Items.lumptin, Items.lumptin, Items.lumptin, ammo, 1, true);
		Crafting.registerCraftingRecipe(wizardwand, ammo, null, null, null, null, null, null, null, wizardwand, 1, false);
		Crafting.registerCraftingRecipe(darkmagewand, ammo, null, null, null, null, null, null, null, darkmagewand, 1, false);
		Crafting.registerCraftingRecipe(Items.lumpcopper, Items.lumptin, null, null, null, null, null, null, null, lumplatten, 1, false);
		Crafting.registerCraftingRecipe(null, lumplatten, null, lumplatten, null, lumplatten, lumplatten, lumplatten, lumplatten, lyinfuser, 1, true);
		
		//Crafting recipes - Giant Tools
		craftGenericSword(Blocks.plywood, Blocks.plywood, giantstick);
		craftGenericSword(Blocks.darkplywood, Blocks.darkplywood, giantstick);
		craftGenericSword(Blocks.lightplywood, Blocks.lightplywood, giantstick);
		craftGenericSword(Blocks.plywood, giantstick, giantwoodensword);
		craftGenericSword(Blocks.darkplywood, giantstick, giantwoodensword);
		craftGenericSword(Blocks.lightplywood, giantstick, giantwoodensword);
		craftGenericSword(Blocks.stone, giantstick, giantstonesword);
		craftGenericSword(Blocks.greystone, giantstick, giantstonesword);
		craftGenericSword(Blocks.blockcopper, giantstick, giantcoppersword);
		craftGenericSword(Blocks.blocktin, giantstick, gianttinsword);
		craftGenericSword(Blocks.blocksilver, giantstick, giantsilversword);
		craftGenericSword(Blocks.blockplatinum, giantstick, giantplatinumsword);
		
		craftGenericPickaxe(Blocks.plywood, giantstick, giantwoodenpickaxe);
		craftGenericPickaxe(Blocks.darkplywood, giantstick, giantwoodenpickaxe);
		craftGenericPickaxe(Blocks.lightplywood, giantstick, giantwoodenpickaxe);
		craftGenericPickaxe(Blocks.stone, giantstick, giantstonepickaxe);
		craftGenericPickaxe(Blocks.greystone, giantstick, giantstonepickaxe);
		craftGenericPickaxe(Blocks.blockcopper, giantstick, giantcopperpickaxe);
		craftGenericPickaxe(Blocks.blocktin, giantstick, gianttinpickaxe);
		craftGenericPickaxe(Blocks.blocksilver, giantstick, giantsilverpickaxe);
		craftGenericPickaxe(Blocks.blockplatinum, giantstick, giantplatinumpickaxe);
		
		craftGenericShovel(Blocks.plywood, giantstick, giantwoodenshovel);
		craftGenericShovel(Blocks.darkplywood, giantstick, giantwoodenshovel);
		craftGenericShovel(Blocks.lightplywood, giantstick, giantwoodenshovel);
		craftGenericShovel(Blocks.stone, giantstick, giantstoneshovel);
		craftGenericShovel(Blocks.greystone, giantstick, giantstoneshovel);
		craftGenericShovel(Blocks.blockcopper, giantstick, giantcoppershovel);
		craftGenericShovel(Blocks.blocktin, giantstick, gianttinshovel);
		craftGenericShovel(Blocks.blocksilver, giantstick, giantsilvershovel);
		craftGenericShovel(Blocks.blockplatinum, giantstick, giantplatinumshovel);
		
		craftGenericAxe(Blocks.plywood, giantstick, giantwoodenaxe);
		craftGenericAxe(Blocks.darkplywood, giantstick, giantwoodenaxe);
		craftGenericAxe(Blocks.lightplywood, giantstick, giantwoodenaxe);
		craftGenericAxe(Blocks.stone, giantstick, giantstoneaxe);
		craftGenericAxe(Blocks.greystone, giantstick, giantstoneaxe);
		craftGenericAxe(Blocks.blockcopper, giantstick, giantcopperaxe);
		craftGenericAxe(Blocks.blocktin, giantstick, gianttinaxe);
		craftGenericAxe(Blocks.blocksilver, giantstick, giantsilveraxe);
		craftGenericAxe(Blocks.blockplatinum, giantstick, giantplatinumaxe);
		
		
		//SpawnLists
		Spawnlist.registerSpawn( new ButterFly(null), Dimensions.overworlddimension, null, 50, 110, 20, 3, CreatureTypes.AIR, CreatureTypes.TRANSIENT);
		Spawnlist.registerSpawn( new Impostor(null), Dimensions.overworlddimension, DangerZoneBase.overworldforest, 60, 90, 5, 1, CreatureTypes.LAND, CreatureTypes.PERMANENT);
		
		Spawnlist.registerSpawn( new ButterFly(null), Dimensions.bigroundtreedimension, null, 50, 110, 20, 3, CreatureTypes.AIR, CreatureTypes.TRANSIENT);
		Spawnlist.registerSpawn( new ImpostorGoose(null), Dimensions.bigroundtreedimension, null, 40, 70, 80, 1, CreatureTypes.WATER, CreatureTypes.PERMANENT);
		Spawnlist.registerSpawn( new ImpostorAnteater(null), Dimensions.bigroundtreedimension, null, 60, 90, 5, 1, CreatureTypes.LAND, CreatureTypes.PERMANENT);
		
		Spawnlist.registerSpawn( new ButterFly(null), Dimensions.pathwaydimension, null, 50, 110, 20, 3, CreatureTypes.AIR, CreatureTypes.TRANSIENT);
		Spawnlist.registerSpawn( new ImpostorGoose(null), Dimensions.pathwaydimension, null, 40, 70, 110, 1, CreatureTypes.WATER, CreatureTypes.PERMANENT);
		Spawnlist.registerSpawn( new ImpostorAnteater(null), Dimensions.pathwaydimension, null, 60, 90, 5, 1, CreatureTypes.LAND, CreatureTypes.PERMANENT);
		
		Spawnlist.registerSpawn( new ButterFly(null), Dimensions.ruggedhillsdimension, null, 50, 110, 20, 3, CreatureTypes.AIR, CreatureTypes.TRANSIENT);
		
		Spawnlist.registerSpawn( new ButterFly(null), Dimensions.windsweptdimension, null, 50, 110, 20, 3, CreatureTypes.AIR, CreatureTypes.TRANSIENT);
		
		Spawnlist.registerSpawn( new ButterFly(null), Dimensions.skyislandsdimension, null, 50, 110, 20, 3, CreatureTypes.AIR, CreatureTypes.TRANSIENT);
		
		Spawnlist.registerSpawn( new ButterFly(null), Dimensions.pleasantvilledimension, null, 50, 110, 20, 3, CreatureTypes.AIR, CreatureTypes.TRANSIENT);
		
		//Server Hooks
		ServerHooker.registerServerHooks(spawnerblocksserverhooks);
	}
	
	public void postLoadProcessing() {
		
	}
	
	public void craftGenericSword(Object material, Object stick, Item result) {
		//three possible positions
		Crafting.registerCraftingRecipe(material, null, null, material, null, null, stick, null, null, result, 1, true);
		Crafting.registerCraftingRecipe(null, material, null, null, material, null, null, stick, null, result, 1, true);
		Crafting.registerCraftingRecipe(null, null, material, null, null, material, null, null, stick, result, 1, true);
	}
	
	public void craftGenericAxe(Object material, Object stick, Item result) {
		//two possible positions
		Crafting.registerCraftingRecipe(material, material, null, material, stick, null, null, stick, null, result, 1, true);
		Crafting.registerCraftingRecipe(null, material, material, null, stick, material, null, stick, null, result, 1, true);
	}
	
	public void craftGenericHoe(Object material, Object stick, Item result) {
		Crafting.registerCraftingRecipe(material, material, null, null, stick, null, null, stick, null, result, 1, true);
		Crafting.registerCraftingRecipe(null, material, material, null, stick, null, null, stick, null, result, 1, true);
	}
	
	public void craftGenericPickaxe(Object material, Object stick, Item result){
		Crafting.registerCraftingRecipe(material, material, material, null, stick, null, null, stick, null, result, 1, true);
	}
	
	public void craftGenericShovel(Object material, Object stick, Item result){
		Crafting.registerCraftingRecipe(material, null, null, stick, null, null, stick, null, null, result, 1, true);
		Crafting.registerCraftingRecipe(null, material, null, null, stick, null, null, stick, null, result, 1, true);
		Crafting.registerCraftingRecipe(null, null, material, null, null, stick, null, null, stick, result, 1, true);
	}

}
