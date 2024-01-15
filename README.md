# JMC Warp
JMC Warp is a plugin for Minecraft servers that adds the ability to craft special Warp blocks that allow players to easily teleport between two connected warp blocks.

## Installation
Simply copy the plugin's `.jar` file into your server's `plugin` folder and restart the server. The plugin will then be enabled.

## Guide for players

### Obtaining a Warpstone
![image](https://github.com/AdamJamesNaylor/jmc-warp/assets/1583720/8849d82f-2bf4-445f-a0bd-872064a2c352)
A Warpstone can be crafted by placing a [Lodestone]([url](https://minecraft.fandom.com/wiki/Lodestone)) and an [Enderpearl]([url](https://minecraft.fandom.com/wiki/Ender_Pearl)) into a crafting square. The recipe is shapeless so you don't need to worry about where the ingredients are placed.

### Naming a Warpstone
A Warpstone can be renamed in an anvil like any other item and this allows the player to differentiate between different Warpstone locations. e.g `Farm` or `Home`.

### Placing and Binding warpstones
When a Warpstone is first placed in the world it will not be bound to any destinations so it will function like a normal Lodestone. In order to bind two warpstones one must be placed ontop of another. This will bind the two so that they will allow teleportation between them. If the bind is successful the player will hear a sound effect, see a particle effect, and receive a message letting you know that the two Warpstones are now bound.

### Using a Warpstone
Once two Warpstones are bound together and each are placed in the world, players are free to use them as warps. Simply standing on either Warpstone will teleport them to the other.

## FAQ

### What happens if a Warpstone is broken by a player or exploded?
The Warpstone will always drop itself so a player can pick it back up and it will retain all of it's bind information so that it can be replaced.

### What happens if more than one Warpstone is bound to another?
If a Warpstone is bound to more than one destination it will store that information but a player using that Warpstone will currently always warp to the first destination that was bound. Effectively ignoring all binds after the first. This will be addressed in a future version where the player will be able to select which of the bound destinations to warp to.

### Can anybody craft Warpstones?
Currently anybody can craft a Warpstone if they have the right ingredients but in a future version this ability will need to be unlocked via a command so that ranks and permissions can be supported.

### Can a bind be removed?
Not at the moment but in a future version a Warpstone will be able to be placed in a [Grindstone]([url](https://minecraft.fandom.com/wiki/Grindstone)) in order for it's bind to be removed. Effectively turning back into a Lodestone.

### Why a Lodestone?
Lodestones seem to fit from a Lore perspective and they also aren't cheap to craft meaning Warpstones won't be overused.

### Why a block and not just a command?
Blocks are diegetic, commands are cumbersome to type on certain devices.
