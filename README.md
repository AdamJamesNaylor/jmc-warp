# JMC Warp
JMC Warp is a plugin for Minecraft servers that adds the ability to craft special Warpstone blocks that allow players to easily teleport between two connected Warpstones.

[Installing](https://github.com/AdamJamesNaylor/jmc-warp/blob/main/README.md#installing)  
[Guide for players](https://github.com/AdamJamesNaylor/jmc-warp/blob/main/README.md#guide-for-players)  
[Guide for administrators](https://github.com/AdamJamesNaylor/jmc-warp/blob/main/README.md#guide-for-administrators)  
[Guide for developers](https://github.com/AdamJamesNaylor/jmc-warp/blob/main/README.md#guide-for-developers)  
[FAQ](https://github.com/AdamJamesNaylor/jmc-warp/blob/main/README.md#faq)  

## Installing
Simply copy the plugin's `.jar` file into your server's `plugin` folder and restart the server. The plugin will then be enabled.

## Guide for players

### Obtaining a Warpstone
![image](https://github.com/AdamJamesNaylor/jmc-warp/assets/1583720/8849d82f-2bf4-445f-a0bd-872064a2c352)

A Warpstone can be crafted by placing a [Lodestone](https://minecraft.fandom.com/wiki/Lodestone) and an [Enderpearl](https://minecraft.fandom.com/wiki/Ender_Pearl) into a crafting square. The recipe is [shapeless](https://minecraft.wiki/w/Crafting#Basic_crafting) so you don't need to worry about where the ingredients are placed.

### Naming a Warpstone
A Warpstone can be renamed in an anvil like any other item and this allows the player to differentiate between different Warpstone locations. e.g `Farm` or `Home`.

### Placing and connecting warpstones
When a Warpstone is first placed in the world it will not be bound to any destinations so it will function like a normal Lodestone. In order to connect two Warpstones one must be placed on top of another. This will connect the two so that they will allow teleportation between them. If the connect is successful the player will hear a sound effect, see a particle effect, and receive a message letting them know that the two Warpstones are now bound.

### Using a Warpstone
Once two Warpstones are bound together and each are placed in the world, players are free to use them as warps. Simply standing on either Warpstone will teleport them to the other.

If the destination Warpstone has been removed from the world (either by a player or due to an explosion) the Warpstone will not teleport the player but let them know that the destination is no longer valid.

## Guide for administrators

### Configuration

The following settings can be changed in the `config.yml` file.

#### block

Default: minecraft:lodestone  
Specifies which block to use as the warp block.

### Commands

#### /jmcwarp give
Gives the player a warpstone.

### Persitence
The Warpstone state is currently persisted in the file `todo.yml`. In future versions this may change to something more scalable.

## Guide for developers

### Events
The plugin raises the following events;
#### WarpstoneCraftEvent
Raised when a Warpstone is crafted.
#### WarpstonePlaceEvent
Raised when a Warpstone is placed in the world by a player.
#### WarpstoneConnectEvent
Raised when a Warpstone is placed on top of another and the two are successfully bound together.
#### WarpstoneUseEvent
Raised when a player walks on to a Warpstone even if it doesn't have a destination.
#### WarpstoneWarpEvent
Raised after a player has walked onto a Warpstone and has been teleported to the destination Warpstone.
#### WarpstoneDestroyEvent
Rasied when Warpstone is destroyed via `EntityExplodeEvent`, `BlockExplodeEvent` or `BlockBreakEvent`.

## FAQ

### What happens if a Warpstone is broken by a player or exploded?
The Warpstone will always drop itself so a player can pick it back up and it will retain all of it's connection information so that it can be replaced.

### What happens if more than one Warpstone is bound to another?
If a Warpstone is bound to more than one destination it will store that information but a player using that Warpstone will currently always warp to the first destination that was bound. Effectively ignoring all connections after the first. This will be addressed in a future version where the player will be able to select which of the bound destinations to warp to.

### Can anybody craft Warpstones?
Currently anybody can craft a Warpstone if they have the right ingredients but in a future version this ability will need to be unlocked via a command so that ranks and permissions can be supported.

### Can a connection be removed?
Not at the moment but in a future version a Warpstone will be able to be placed in a [Grindstone](https://minecraft.fandom.com/wiki/Grindstone) in order for it's connection to be removed. Effectively turning back into a Lodestone.

### Why a Lodestone?
Lodestones seem to fit from a lore perspective and they also aren't cheap to craft meaning Warpstones won't be overused. This can be changed in the configuration.

### Why a block and not just a command?
Blocks are diegetic, commands are cumbersome to type on certain devices.

### What happens if a player uses a Warpstone with a destination that has been removed from the world?
If the destination Warpstone is no longer placed in the world (because a player removed it or it got destroyed somehow) the Warpstone will simply not teleport the player and will inform them with a message.

### Can you warp between different dimensions?
Yep. Multidimensional warping is supported.


