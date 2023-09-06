# AutoSkyblockRejoin

Don't you hate it when Hypixel randomly kicks you from Skyblock? It's a sign. Stop playing Skyblock.

However, if for some reason you decide you like Skyblock and Hypixel keeps kicking you while you are AFK with random error messages, despite having bought a booster cookie, this mod will automatically warp you back to your island when it detects you were unfairly kicked!

## Download

Modrinth - https://modrinth.com/mod/autoskyblockrejoin

## How It Works

The mod listens for certain disconnect messages in the chat and if it detects one, it will automatically try to rejoin the Skyblock server on Hypixel after a certain time delay. This delay is around 105 seconds in total, partly because I don't want to create any sort of spam, leading to a ban on Hypixel, but mostly because sometimes Hypixel takes forever to actually execute the command. Once it finds a disconnect message, it will start the process of sending the Skyblock join commands, and will over the span of 105 seconds, send you to the Lobby, Skyblock, and then your Island. If it detects that you are not on your island, it will restart the process. Throughout the process you will get notifications about where in the process you are.

As always, use at your own risk.

## Is It Allowed?

As I said above, use at your own risk. If you want to look at the disallowed Skyblock mods, specifically the "Disallowed AFK Methods" you can read this:

> Disallowed methods include but are not limited to using glitches (examples: F11, alt+tab), modifications, bugs, third-party software, hardware, or objects such as tape or rocks. Players found collecting experience, items, or coins using disallowed methods will be punished.

This mod is undoubtedly third party software and has to do with AFK, but I do not think this rule applies here. This rule above is specifically talking about methods to prevent the AFK kick. This mod does not prevent the kick, but instead warps you back to your island after around 2 minutes of being kicked. The goal with this mod is not to bypass Hypixel's rules, but rather is meant to be used along with an allowed method of AFK and act as a backup when Hypixel (inevitably) errors out and kicks you anyways, regardless of AFK method. This mod was made at request of a player who was consistently getting kicked shortly after going AFK due to random errors, despite following the allowed AFK methods.

## Notes

This mod uses OneConfig for its config, multithreading capabilities, and notifications, as well as its Hypixel API to find the players Locraw and whether they are on Hypixel in the first place.
