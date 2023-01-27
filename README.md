# AutoSkyblockRejoin

Don't you hate it when Hypixel randomly kicks you from Skyblock? It's a sign. Stop playing Skyblock.

However, if for some reason you decide you like Skyblock and Hypixel keeps kicking you while you are AFK with random error messages, despite having bought a booster cookie, this mod will automatically warp you back to your island when it detects you were unfairly kicked!

## Download

Modrinth - https://modrinth.com/mod/autoskyblockrejoin

## How It Works

The mod listens for certain disconnect messages in the chat and if it detects one, it will automatically try to rejoin the Skyblock server on Hypixel after a certain time delay. This delay is around 105 seconds in total, partly because I don't want to create any sort of spam, leading to a ban on Hypixel, but mostly because sometimes Hypixel takes forever to actually execute the command. Once it finds a disconnect message, it will start the process of sending the Skyblock join commands, and will over the span of 105 seconds, send you to the Lobby, Skyblock, and then your Island. If it detects that you are not on your island, it will restart the process. Throughout the process you will get notifications about where in the process you are.

As always, use at your own risk.

## Notes

This mod uses OneConfig for its config, multithreading capabilities, and notifications, as well as its Hypixel API to find the players Locraw and whether they are on Hypixel in the first place.
