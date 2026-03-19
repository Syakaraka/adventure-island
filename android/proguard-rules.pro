# LibGDX specific rules
-keep class com.badlogicgames.** { *; }
-keep class com.adventureisland.game.** { *; }

# Keep native methods
-keepclasseswithmembernames class * {
    native <methods>;
}
