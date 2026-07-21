package com.github.jsf.color.ansi;

import com.github.jsf.color.converter.*;

public class ANSI8Bit extends ANSI4Bit {
    // --- System Colors (0-15) ---
    public static final ANSI8Bit BLACK = new ANSI8Bit("BLACK", 0, 0, 0, 0);
    public static final ANSI8Bit RED = new ANSI8Bit("RED", 1, 128, 0, 0);
    public static final ANSI8Bit GREEN = new ANSI8Bit("GREEN", 2, 0, 128, 0);
    public static final ANSI8Bit YELLOW = new ANSI8Bit("YELLOW", 3, 128, 128, 0);
    public static final ANSI8Bit BLUE = new ANSI8Bit("BLUE", 4, 0, 0, 128);
    public static final ANSI8Bit MAGENTA = new ANSI8Bit("MAGENTA", 5, 128, 0, 128);
    public static final ANSI8Bit CYAN = new ANSI8Bit("CYAN", 6, 0, 128, 128);
    public static final ANSI8Bit WHITE = new ANSI8Bit("WHITE", 7, 192, 192, 192);
    public static final ANSI8Bit BRIGHT_BLACK = new ANSI8Bit("BRIGHT_BLACK", 8, 128, 128, 128);
    public static final ANSI8Bit BRIGHT_RED = new ANSI8Bit("BRIGHT_RED", 9, 255, 0, 0);
    public static final ANSI8Bit BRIGHT_GREEN = new ANSI8Bit("BRIGHT_GREEN", 10, 0, 255, 0);
    public static final ANSI8Bit BRIGHT_YELLOW = new ANSI8Bit("BRIGHT_YELLOW", 11, 255, 255, 0);
    public static final ANSI8Bit BRIGHT_BLUE = new ANSI8Bit("BRIGHT_BLUE", 12, 0, 0, 255);
    public static final ANSI8Bit BRIGHT_MAGENTA = new ANSI8Bit("BRIGHT_MAGENTA", 13, 255, 0, 255);
    public static final ANSI8Bit BRIGHT_CYAN = new ANSI8Bit("BRIGHT_CYAN", 14, 0, 255, 255);
    public static final ANSI8Bit BRIGHT_WHITE = new ANSI8Bit("BRIGHT_WHITE", 15, 255, 255, 255);

    // --- Color Cube (16-231) ---
    public static final ANSI8Bit GREY_0 = new ANSI8Bit("GREY_0", 16, 0, 0, 0);
    public static final ANSI8Bit NAVY_BLUE = new ANSI8Bit("NAVY_BLUE", 17, 0, 0, 95);
    public static final ANSI8Bit DARK_BLUE = new ANSI8Bit("DARK_BLUE", 18, 0, 0, 135);
    public static final ANSI8Bit BLUE_3A = new ANSI8Bit("BLUE_3A", 19, 0, 0, 175);
    public static final ANSI8Bit BLUE_3B = new ANSI8Bit("BLUE_3B", 20, 0, 0, 215);
    public static final ANSI8Bit BLUE_1 = new ANSI8Bit("BLUE_1", 21, 0, 0, 255);
    public static final ANSI8Bit DARK_GREEN = new ANSI8Bit("DARK_GREEN", 22, 0, 95, 0);
    public static final ANSI8Bit DEEP_SKY_BLUE_4A = new ANSI8Bit("DEEP_SKY_BLUE_4A", 23, 0, 95, 95);
    public static final ANSI8Bit DEEP_SKY_BLUE_4B = new ANSI8Bit("DEEP_SKY_BLUE_4B", 24, 0, 95, 135);
    public static final ANSI8Bit DEEP_SKY_BLUE_4C = new ANSI8Bit("DEEP_SKY_BLUE_4C", 25, 0, 95, 175);
    public static final ANSI8Bit DODGER_BLUE_3 = new ANSI8Bit("DODGER_BLUE_3", 26, 0, 95, 215);
    public static final ANSI8Bit DODGER_BLUE_2 = new ANSI8Bit("DODGER_BLUE_2", 27, 0, 95, 255);
    public static final ANSI8Bit GREEN_4 = new ANSI8Bit("GREEN_4", 28, 0, 135, 0);
    public static final ANSI8Bit SPRING_GREEN_4 = new ANSI8Bit("SPRING_GREEN_4", 29, 0, 135, 95);
    public static final ANSI8Bit TURQUOISE_4 = new ANSI8Bit("TURQUOISE_4", 30, 0, 135, 135);
    public static final ANSI8Bit DEEP_SKY_BLUE_3A = new ANSI8Bit("DEEP_SKY_BLUE_3A", 31, 0, 135, 175);
    public static final ANSI8Bit DEEP_SKY_BLUE_3B = new ANSI8Bit("DEEP_SKY_BLUE_3B", 32, 0, 135, 215);
    public static final ANSI8Bit DODGER_BLUE_1 = new ANSI8Bit("DODGER_BLUE_1", 33, 0, 135, 255);
    public static final ANSI8Bit GREEN_3A = new ANSI8Bit("GREEN_3A", 34, 0, 175, 0);
    public static final ANSI8Bit SPRING_GREEN_3A = new ANSI8Bit("SPRING_GREEN_3A", 35, 0, 175, 95);
    public static final ANSI8Bit DARK_CYAN = new ANSI8Bit("DARK_CYAN", 36, 0, 175, 135);
    public static final ANSI8Bit LIGHT_SEA_GREEN = new ANSI8Bit("LIGHT_SEA_GREEN", 37, 0, 175, 175);
    public static final ANSI8Bit DEEP_SKY_BLUE_2 = new ANSI8Bit("DEEP_SKY_BLUE_2", 38, 0, 175, 215);
    public static final ANSI8Bit DEEP_SKY_BLUE_1 = new ANSI8Bit("DEEP_SKY_BLUE_1", 39, 0, 175, 255);
    public static final ANSI8Bit GREEN_3B = new ANSI8Bit("GREEN_3B", 40, 0, 215, 0);
    public static final ANSI8Bit SPRING_GREEN_3B = new ANSI8Bit("SPRING_GREEN_3B", 41, 0, 215, 95);
    public static final ANSI8Bit SPRING_GREEN_2A = new ANSI8Bit("SPRING_GREEN_2A", 42, 0, 215, 135);
    public static final ANSI8Bit CYAN_3 = new ANSI8Bit("CYAN_3", 43, 0, 215, 175);
    public static final ANSI8Bit DARK_TURQUOISE = new ANSI8Bit("DARK_TURQUOISE", 44, 0, 215, 215);
    public static final ANSI8Bit TURQUOISE_2 = new ANSI8Bit("TURQUOISE_2", 45, 0, 215, 255);
    public static final ANSI8Bit GREEN_1 = new ANSI8Bit("GREEN_1", 46, 0, 255, 0);
    public static final ANSI8Bit SPRING_GREEN_2B = new ANSI8Bit("SPRING_GREEN_2B", 47, 0, 255, 95);
    public static final ANSI8Bit SPRING_GREEN_1 = new ANSI8Bit("SPRING_GREEN_1", 48, 0, 255, 135);
    public static final ANSI8Bit MEDIUM_SPRING_GREEN = new ANSI8Bit("MEDIUM_SPRING_GREEN", 49, 0, 255, 175);
    public static final ANSI8Bit CYAN_2 = new ANSI8Bit("CYAN_2", 50, 0, 255, 215);
    public static final ANSI8Bit CYAN_1 = new ANSI8Bit("CYAN_1", 51, 0, 255, 255);
    public static final ANSI8Bit DARK_RED_2 = new ANSI8Bit("DARK_RED_2", 52, 95, 0, 0);
    public static final ANSI8Bit DEEP_PINK_4A = new ANSI8Bit("DEEP_PINK_4A", 53, 95, 0, 95);
    public static final ANSI8Bit PURPLE_4A = new ANSI8Bit("PURPLE_4A", 54, 95, 0, 135);
    public static final ANSI8Bit PURPLE_4B = new ANSI8Bit("PURPLE_4B", 55, 95, 0, 175);
    public static final ANSI8Bit PURPLE_3 = new ANSI8Bit("PURPLE_3", 56, 95, 0, 215);
    public static final ANSI8Bit BLUE_VIOLET = new ANSI8Bit("BLUE_VIOLET", 57, 95, 0, 255);
    public static final ANSI8Bit ORANGE_4A = new ANSI8Bit("ORANGE_4A", 58, 95, 95, 0);
    public static final ANSI8Bit GREY_37 = new ANSI8Bit("GREY_37", 59, 95, 95, 95);
    public static final ANSI8Bit MEDIUM_PURPLE_4 = new ANSI8Bit("MEDIUM_PURPLE_4", 60, 95, 95, 135);
    public static final ANSI8Bit SLATE_BLUE_3A = new ANSI8Bit("SLATE_BLUE_3A", 61, 95, 95, 175);
    public static final ANSI8Bit SLATE_BLUE_3B = new ANSI8Bit("SLATE_BLUE_3B", 62, 95, 95, 215);
    public static final ANSI8Bit ROYAL_BLUE_1 = new ANSI8Bit("ROYAL_BLUE_1", 63, 95, 95, 255);
    public static final ANSI8Bit CHARTREUSE_4 = new ANSI8Bit("CHARTREUSE_4", 64, 95, 135, 0);
    public static final ANSI8Bit DARK_SEA_GREEN_4A = new ANSI8Bit("DARK_SEA_GREEN_4A", 65, 95, 135, 95);
    public static final ANSI8Bit PALE_TURQUOISE_4 = new ANSI8Bit("PALE_TURQUOISE_4", 66, 95, 135, 135);
    public static final ANSI8Bit STEEL_BLUE_3A = new ANSI8Bit("STEEL_BLUE_3A", 67, 95, 135, 175);
    public static final ANSI8Bit STEEL_BLUE_3B = new ANSI8Bit("STEEL_BLUE_3B", 68, 95, 135, 215);
    public static final ANSI8Bit CORNFLOWER_BLUE = new ANSI8Bit("CORNFLOWER_BLUE", 69, 95, 135, 255);
    public static final ANSI8Bit CHARTREUSE_3A = new ANSI8Bit("CHARTREUSE_3A", 70, 95, 175, 0);
    public static final ANSI8Bit DARK_SEA_GREEN_4B = new ANSI8Bit("DARK_SEA_GREEN_4B", 71, 95, 175, 95);
    public static final ANSI8Bit CADET_BLUE_2 = new ANSI8Bit("CADET_BLUE_2", 72, 95, 175, 135);
    public static final ANSI8Bit CADET_BLUE_1 = new ANSI8Bit("CADET_BLUE_1", 73, 95, 175, 175);
    public static final ANSI8Bit SKY_BLUE_3 = new ANSI8Bit("SKY_BLUE_3", 74, 95, 175, 215);
    public static final ANSI8Bit STEEL_BLUE_1A = new ANSI8Bit("STEEL_BLUE_1A", 75, 95, 175, 255);
    public static final ANSI8Bit CHARTREUSE_3B = new ANSI8Bit("CHARTREUSE_3B", 76, 95, 215, 0);
    public static final ANSI8Bit PALE_GREEN_3A = new ANSI8Bit("PALE_GREEN_3A", 77, 95, 215, 95);
    public static final ANSI8Bit SEA_GREEN_3 = new ANSI8Bit("SEA_GREEN_3", 78, 95, 215, 135);
    public static final ANSI8Bit AQUAMARINE_3 = new ANSI8Bit("AQUAMARINE_3", 79, 95, 215, 175);
    public static final ANSI8Bit MEDIUM_TURQUOISE = new ANSI8Bit("MEDIUM_TURQUOISE", 80, 95, 215, 215);
    public static final ANSI8Bit STEEL_BLUE_1B = new ANSI8Bit("STEEL_BLUE_1B", 81, 95, 215, 255);
    public static final ANSI8Bit CHARTREUSE_2A = new ANSI8Bit("CHARTREUSE_2A", 82, 95, 255, 0);
    public static final ANSI8Bit SEA_GREEN_2 = new ANSI8Bit("SEA_GREEN_2", 83, 95, 255, 95);
    public static final ANSI8Bit SEA_GREEN_1A = new ANSI8Bit("SEA_GREEN_1A", 84, 95, 255, 135);
    public static final ANSI8Bit SEA_GREEN_1B = new ANSI8Bit("SEA_GREEN_1B", 85, 95, 255, 175);
    public static final ANSI8Bit AQUAMARINE_1A = new ANSI8Bit("AQUAMARINE_1A", 86, 95, 255, 215);
    public static final ANSI8Bit DARK_SLATE_GRAY_2 = new ANSI8Bit("DARK_SLATE_GRAY_2", 87, 95, 255, 255);
    public static final ANSI8Bit DARK_RED_1 = new ANSI8Bit("DARK_RED_1", 88, 135, 0, 0);
    public static final ANSI8Bit DEEP_PINK_4B = new ANSI8Bit("DEEP_PINK_4B", 89, 135, 0, 95);
    public static final ANSI8Bit MEDIUM_PURPLE_4B = new ANSI8Bit("MEDIUM_PURPLE_4B", 90, 135, 0, 135);
    public static final ANSI8Bit DEEP_PINK_4C = new ANSI8Bit("DEEP_PINK_4C", 91, 135, 0, 175);
    public static final ANSI8Bit MAGENTA_3A = new ANSI8Bit("MAGENTA_3A", 92, 135, 0, 215);
    public static final ANSI8Bit DARK_VIOLET_1A = new ANSI8Bit("DARK_VIOLET_1A", 93, 135, 0, 255);
    public static final ANSI8Bit ORANGE_4B = new ANSI8Bit("ORANGE_4B", 94, 135, 95, 0);
    public static final ANSI8Bit LIGHT_SLATE_GREY = new ANSI8Bit("LIGHT_SLATE_GREY", 95, 135, 95, 95);
    public static final ANSI8Bit MEDIUM_PURPLE_3A = new ANSI8Bit("MEDIUM_PURPLE_3A", 96, 135, 95, 135);
    public static final ANSI8Bit MEDIUM_PURPLE_3B = new ANSI8Bit("MEDIUM_PURPLE_3B", 97, 135, 95, 175);
    public static final ANSI8Bit SLATE_BLUE_1 = new ANSI8Bit("SLATE_BLUE_1", 98, 135, 95, 215);
    public static final ANSI8Bit DARK_VIOLET_1B = new ANSI8Bit("DARK_VIOLET_1B", 99, 135, 95, 255);
    public static final ANSI8Bit DARK_GOLDENROD = new ANSI8Bit("DARK_GOLDENROD", 100, 135, 135, 0);
    public static final ANSI8Bit TAN = new ANSI8Bit("TAN", 101, 135, 135, 95);
    public static final ANSI8Bit MISTY_ROSE_3 = new ANSI8Bit("MISTY_ROSE_3", 102, 135, 135, 135);
    public static final ANSI8Bit THISTLE_3 = new ANSI8Bit("THISTLE_3", 103, 135, 135, 175);
    public static final ANSI8Bit SLATE_BLUE_1B = new ANSI8Bit("SLATE_BLUE_1B", 104, 135, 135, 215);
    public static final ANSI8Bit MEDIUM_PURPLE_1 = new ANSI8Bit("MEDIUM_PURPLE_1", 105, 135, 135, 255);
    public static final ANSI8Bit CHARTREUSE_2B = new ANSI8Bit("CHARTREUSE_2B", 106, 135, 175, 0);
    public static final ANSI8Bit DARK_SEA_GREEN_3A = new ANSI8Bit("DARK_SEA_GREEN_3A", 107, 135, 175, 95);
    public static final ANSI8Bit DARK_SEA_GREEN_3B = new ANSI8Bit("DARK_SEA_GREEN_3B", 108, 135, 175, 135);
    public static final ANSI8Bit DARK_SEA_GREEN_2 = new ANSI8Bit("DARK_SEA_GREEN_2", 109, 135, 175, 175);
    public static final ANSI8Bit GREY_63 = new ANSI8Bit("GREY_63", 110, 135, 175, 215);
    public static final ANSI8Bit LIGHT_SKY_BLUE_3A = new ANSI8Bit("LIGHT_SKY_BLUE_3A", 111, 135, 175, 255);
    public static final ANSI8Bit CHARTREUSE_2C = new ANSI8Bit("CHARTREUSE_2C", 112, 135, 215, 0);
    public static final ANSI8Bit YELLOW_GREEEN = new ANSI8Bit("YELLOW_GREEEN", 113, 135, 215, 95);
    public static final ANSI8Bit DARK_SEA_GREEN_2A = new ANSI8Bit("DARK_SEA_GREEN_2A", 114, 135, 215, 135);
    public static final ANSI8Bit DARK_SEA_GREEN_2B = new ANSI8Bit("DARK_SEA_GREEN_2B", 115, 135, 215, 175);
    public static final ANSI8Bit LIGHT_CYAN_3 = new ANSI8Bit("LIGHT_CYAN_3", 116, 135, 215, 215);
    public static final ANSI8Bit LIGHT_SKY_BLUE_3B = new ANSI8Bit("LIGHT_SKY_BLUE_3B", 117, 135, 215, 255);
    public static final ANSI8Bit GREEN_YELLOW = new ANSI8Bit("GREEN_YELLOW", 118, 135, 255, 0);
    public static final ANSI8Bit DARK_SEA_GREEN_1A = new ANSI8Bit("DARK_SEA_GREEN_1A", 119, 135, 255, 95);
    public static final ANSI8Bit DARK_SEA_GREEN_1B = new ANSI8Bit("DARK_SEA_GREEN_1B", 120, 135, 255, 135);
    public static final ANSI8Bit PALE_GREEN_1A = new ANSI8Bit("PALE_GREEN_1A", 121, 135, 255, 175);
    public static final ANSI8Bit DARK_SLATE_GRAY_1 = new ANSI8Bit("DARK_SLATE_GRAY_1", 122, 135, 255, 215);
    public static final ANSI8Bit SKY_BLUE_1 = new ANSI8Bit("SKY_BLUE_1", 123, 135, 255, 255);
    public static final ANSI8Bit RED_3A = new ANSI8Bit("RED_3A", 124, 175, 0, 0);
    public static final ANSI8Bit DEEP_PINK_4D = new ANSI8Bit("DEEP_PINK_4D", 125, 175, 0, 95);
    public static final ANSI8Bit MEDIUM_VIOLET_RED = new ANSI8Bit("MEDIUM_VIOLET_RED", 126, 175, 0, 135);
    public static final ANSI8Bit MAGENTA_3B = new ANSI8Bit("MAGENTA_3B", 127, 175, 0, 175);
    public static final ANSI8Bit DARK_VIOLET_1C = new ANSI8Bit("DARK_VIOLET_1C", 128, 175, 0, 215);
    public static final ANSI8Bit PURPLE_1A = new ANSI8Bit("PURPLE_1A", 129, 175, 0, 255);
    public static final ANSI8Bit DARK_ORANGE_3A = new ANSI8Bit("DARK_ORANGE_3A", 130, 175, 95, 0);
    public static final ANSI8Bit INDIAN_RED_1A = new ANSI8Bit("INDIAN_RED_1A", 131, 175, 95, 95);
    public static final ANSI8Bit HOT_PINK_3A = new ANSI8Bit("HOT_PINK_3A", 132, 175, 95, 135);
    public static final ANSI8Bit MEDIUM_ORCHID_3 = new ANSI8Bit("MEDIUM_ORCHID_3", 133, 175, 95, 175);
    public static final ANSI8Bit MEDIUM_ORCHID = new ANSI8Bit("MEDIUM_ORCHID", 134, 175, 95, 215);
    public static final ANSI8Bit MEDIUM_PURPLE_2 = new ANSI8Bit("MEDIUM_PURPLE_2", 135, 175, 95, 255);
    public static final ANSI8Bit DARK_GOLDENROD_3A = new ANSI8Bit("DARK_GOLDENROD_3A", 136, 175, 135, 0);
    public static final ANSI8Bit LIGHT_GOLDENROD_3 = new ANSI8Bit("LIGHT_GOLDENROD_3", 137, 175, 135, 95);
    public static final ANSI8Bit ROSY_BROWN = new ANSI8Bit("ROSY_BROWN", 138, 175, 135, 135);
    public static final ANSI8Bit GREY_66 = new ANSI8Bit("GREY_66", 139, 175, 135, 175);
    public static final ANSI8Bit MEDIUM_PURPLE_3C = new ANSI8Bit("MEDIUM_PURPLE_3C", 140, 175, 135, 215);
    public static final ANSI8Bit MEDIUM_PURPLE_1B = new ANSI8Bit("MEDIUM_PURPLE_1B", 141, 175, 135, 255);
    public static final ANSI8Bit GOLD_3A = new ANSI8Bit("GOLD_3A", 142, 175, 175, 0);
    public static final ANSI8Bit DARK_KHAKI = new ANSI8Bit("DARK_KHAKI", 143, 175, 175, 95);
    public static final ANSI8Bit NAVAJO_WHITE_3 = new ANSI8Bit("NAVAJO_WHITE_3", 144, 175, 175, 135);
    public static final ANSI8Bit GREY_69 = new ANSI8Bit("GREY_69", 145, 175, 175, 175);
    public static final ANSI8Bit LIGHT_STEEL_BLUE_3 = new ANSI8Bit("LIGHT_STEEL_BLUE_3", 146, 175, 175, 215);
    public static final ANSI8Bit LIGHT_STEEL_BLUE = new ANSI8Bit("LIGHT_STEEL_BLUE", 147, 175, 175, 255);
    public static final ANSI8Bit YELLOW_3A = new ANSI8Bit("YELLOW_3A", 148, 175, 215, 0);
    public static final ANSI8Bit DARK_OLIVE_GREEN_3 = new ANSI8Bit("DARK_OLIVE_GREEN_3", 149, 175, 215, 95);
    public static final ANSI8Bit DARK_SEA_GREEN_3C = new ANSI8Bit("DARK_SEA_GREEN_3C", 150, 175, 215, 135);
    public static final ANSI8Bit DARK_SEA_GREEN_2C = new ANSI8Bit("DARK_SEA_GREEN_2C", 151, 175, 215, 175);
    public static final ANSI8Bit LIGHT_CYAN_2 = new ANSI8Bit("LIGHT_CYAN_2", 152, 175, 215, 215);
    public static final ANSI8Bit LIGHT_SKY_BLUE_1 = new ANSI8Bit("LIGHT_SKY_BLUE_1", 153, 175, 215, 255);
    public static final ANSI8Bit GREEN_YELLOW_2 = new ANSI8Bit("GREEN_YELLOW_2", 154, 175, 255, 0);
    public static final ANSI8Bit DARK_OLIVE_GREEN_2 = new ANSI8Bit("DARK_OLIVE_GREEN_2", 155, 175, 255, 95);
    public static final ANSI8Bit PALE_GREEN_1B = new ANSI8Bit("PALE_GREEN_1B", 156, 175, 255, 135);
    public static final ANSI8Bit DARK_SEA_GREEN_1C = new ANSI8Bit("DARK_SEA_GREEN_1C", 157, 175, 255, 175);
    public static final ANSI8Bit DARK_SEA_GREEN_1D = new ANSI8Bit("DARK_SEA_GREEN_1D", 158, 175, 255, 215);
    public static final ANSI8Bit PALE_TURQUOISE_1 = new ANSI8Bit("PALE_TURQUOISE_1", 159, 175, 255, 255);
    public static final ANSI8Bit RED_3B = new ANSI8Bit("RED_3B", 160, 215, 0, 0);
    public static final ANSI8Bit DEEP_PINK_3A = new ANSI8Bit("DEEP_PINK_3A", 161, 215, 0, 95);
    public static final ANSI8Bit DEEP_PINK_3B = new ANSI8Bit("DEEP_PINK_3B", 162, 215, 0, 135);
    public static final ANSI8Bit MAGENTA_3C = new ANSI8Bit("MAGENTA_3C", 163, 215, 0, 175);
    public static final ANSI8Bit MAGENTA_3D = new ANSI8Bit("MAGENTA_3D", 164, 215, 0, 215);
    public static final ANSI8Bit MAGENTA_2A = new ANSI8Bit("MAGENTA_2A", 165, 215, 0, 255);
    public static final ANSI8Bit DARK_ORANGE_3B = new ANSI8Bit("DARK_ORANGE_3B", 166, 215, 95, 0);
    public static final ANSI8Bit INDIAN_RED_1B = new ANSI8Bit("INDIAN_RED_1B", 167, 215, 95, 95);
    public static final ANSI8Bit HOT_PINK_3B = new ANSI8Bit("HOT_PINK_3B", 168, 215, 95, 135);
    public static final ANSI8Bit HOT_PINK_2 = new ANSI8Bit("HOT_PINK_2", 169, 215, 95, 175);
    public static final ANSI8Bit ORCHID_2 = new ANSI8Bit("ORCHID_2", 170, 215, 95, 215);
    public static final ANSI8Bit MEDIUM_ORCHID_1A = new ANSI8Bit("MEDIUM_ORCHID_1A", 171, 215, 95, 255);
    public static final ANSI8Bit ORANGE_3 = new ANSI8Bit("ORANGE_3", 172, 215, 135, 0);
    public static final ANSI8Bit LIGHT_SALMON_3A = new ANSI8Bit("LIGHT_SALMON_3A", 173, 215, 135, 95);
    public static final ANSI8Bit LIGHT_PINK_3 = new ANSI8Bit("LIGHT_PINK_3", 174, 215, 135, 135);
    public static final ANSI8Bit PINK_3 = new ANSI8Bit("PINK_3", 175, 215, 135, 175);
    public static final ANSI8Bit PLUM_3 = new ANSI8Bit("PLUM_3", 176, 215, 135, 215);
    public static final ANSI8Bit VIOLET = new ANSI8Bit("VIOLET", 177, 215, 135, 255);
    public static final ANSI8Bit GOLD_3B = new ANSI8Bit("GOLD_3B", 178, 215, 175, 0);
    public static final ANSI8Bit LIGHT_GOLDENROD_3B = new ANSI8Bit("LIGHT_GOLDENROD_3B", 179, 215, 175, 95);
    public static final ANSI8Bit TAN_2 = new ANSI8Bit("TAN_2", 180, 215, 175, 135);
    public static final ANSI8Bit MISTY_ROSE_3B = new ANSI8Bit("MISTY_ROSE_3B", 181, 215, 175, 175);
    public static final ANSI8Bit THISTLE_3B = new ANSI8Bit("THISTLE_3B", 182, 215, 175, 215);
    public static final ANSI8Bit PLUM_2 = new ANSI8Bit("PLUM_2", 183, 215, 175, 255);
    public static final ANSI8Bit YELLOW_3B = new ANSI8Bit("YELLOW_3B", 184, 215, 215, 0);
    public static final ANSI8Bit KHAKI_3 = new ANSI8Bit("KHAKI_3", 185, 215, 215, 95);
    public static final ANSI8Bit LIGHT_GOLDENROD_2A = new ANSI8Bit("LIGHT_GOLDENROD_2A", 186, 215, 215, 135);
    public static final ANSI8Bit LIGHT_YELLOW_3 = new ANSI8Bit("LIGHT_YELLOW_3", 187, 215, 215, 175);
    public static final ANSI8Bit GREY_84 = new ANSI8Bit("GREY_84", 188, 215, 215, 215);
    public static final ANSI8Bit LIGHT_STEEL_BLUE_1 = new ANSI8Bit("LIGHT_STEEL_BLUE_1", 189, 215, 215, 255);
    public static final ANSI8Bit YELLOW_2 = new ANSI8Bit("YELLOW_2", 190, 215, 255, 0);
    public static final ANSI8Bit DARK_OLIVE_GREEN_1A = new ANSI8Bit("DARK_OLIVE_GREEN_1A", 191, 215, 255, 95);
    public static final ANSI8Bit DARK_OLIVE_GREEN_1B = new ANSI8Bit("DARK_OLIVE_GREEN_1B", 192, 215, 255, 135);
    public static final ANSI8Bit DARK_OLIVE_GREEN_1C = new ANSI8Bit("DARK_OLIVE_GREEN_1C", 193, 215, 255, 175);
    public static final ANSI8Bit DARK_OLIVE_GREEN_1D = new ANSI8Bit("DARK_OLIVE_GREEN_1D", 194, 215, 255, 215);
    public static final ANSI8Bit HONEYDEW = new ANSI8Bit("HONEYDEW", 195, 215, 255, 255);
    public static final ANSI8Bit RED_1 = new ANSI8Bit("RED_1", 196, 255, 0, 0);
    public static final ANSI8Bit DEEP_PINK_2 = new ANSI8Bit("DEEP_PINK_2", 197, 255, 0, 95);
    public static final ANSI8Bit DEEP_PINK_1A = new ANSI8Bit("DEEP_PINK_1A", 198, 255, 0, 135);
    public static final ANSI8Bit DEEP_PINK_1B = new ANSI8Bit("DEEP_PINK_1B", 199, 255, 0, 175);
    public static final ANSI8Bit MAGENTA_2B = new ANSI8Bit("MAGENTA_2B", 200, 255, 0, 215);
    public static final ANSI8Bit MAGENTA_1 = new ANSI8Bit("MAGENTA_1", 201, 255, 0, 255);
    public static final ANSI8Bit ORANGE_RED_1 = new ANSI8Bit("ORANGE_RED_1", 202, 255, 95, 0);
    public static final ANSI8Bit INDIAN_RED_1C = new ANSI8Bit("INDIAN_RED_1C", 203, 255, 95, 95);
    public static final ANSI8Bit INDIAN_RED_1D = new ANSI8Bit("INDIAN_RED_1D", 204, 255, 95, 135);
    public static final ANSI8Bit HOT_PINK_1A = new ANSI8Bit("HOT_PINK_1A", 205, 255, 95, 175);
    public static final ANSI8Bit HOT_PINK_1B = new ANSI8Bit("HOT_PINK_1B", 206, 255, 95, 215);
    public static final ANSI8Bit MEDIUM_ORCHID_1B = new ANSI8Bit("MEDIUM_ORCHID_1B", 207, 255, 95, 255);
    public static final ANSI8Bit DARK_ORANGE = new ANSI8Bit("DARK_ORANGE", 208, 255, 135, 0);
    public static final ANSI8Bit SALMON_1 = new ANSI8Bit("SALMON_1", 209, 255, 135, 95);
    public static final ANSI8Bit LIGHT_CORAL = new ANSI8Bit("LIGHT_CORAL", 210, 255, 135, 135);
    public static final ANSI8Bit PALE_VIOLET_RED_1 = new ANSI8Bit("PALE_VIOLET_RED_1", 211, 255, 135, 175);
    public static final ANSI8Bit ORCHID_1 = new ANSI8Bit("ORCHID_1", 212, 255, 135, 215);
    public static final ANSI8Bit PLUM_1 = new ANSI8Bit("PLUM_1", 213, 255, 135, 255);
    public static final ANSI8Bit ORANGE_1 = new ANSI8Bit("ORANGE_1", 214, 255, 175, 0);
    public static final ANSI8Bit SANDY_BROWN = new ANSI8Bit("SANDY_BROWN", 215, 255, 175, 95);
    public static final ANSI8Bit LIGHT_SALMON_1 = new ANSI8Bit("LIGHT_SALMON_1", 216, 255, 175, 135);
    public static final ANSI8Bit LIGHT_PINK_1 = new ANSI8Bit("LIGHT_PINK_1", 217, 255, 175, 175);
    public static final ANSI8Bit PINK_1 = new ANSI8Bit("PINK_1", 218, 255, 175, 175);
    public static final ANSI8Bit THISTLE_1 = new ANSI8Bit("THISTLE_1", 219, 255, 175, 255);
    public static final ANSI8Bit GOLD_1 = new ANSI8Bit("GOLD_1", 220, 255, 215, 0);
    public static final ANSI8Bit LIGHT_GOLDENROD_2B = new ANSI8Bit("LIGHT_GOLDENROD_2B", 221, 255, 215, 95);
    public static final ANSI8Bit LIGHT_GOLDENROD_2C = new ANSI8Bit("LIGHT_GOLDENROD_2C", 222, 255, 215, 135);
    public static final ANSI8Bit NAVAJO_WHITE_1 = new ANSI8Bit("NAVAJO_WHITE_1", 223, 255, 215, 175);
    public static final ANSI8Bit MISTY_ROSE1 = new ANSI8Bit("MISTY_ROSE1", 224, 255, 215, 215);
    public static final ANSI8Bit THISTLE_1B = new ANSI8Bit("THISTLE_1B", 225, 255, 215, 255);
    public static final ANSI8Bit YELLOW_1 = new ANSI8Bit("YELLOW_1", 226, 255, 255, 0);
    public static final ANSI8Bit LIGHT_GOLDENROD_1 = new ANSI8Bit("LIGHT_GOLDENROD_1", 227, 255, 255, 95);
    public static final ANSI8Bit KHAKI_1 = new ANSI8Bit("KHAKI_1", 228, 255, 255, 135);
    public static final ANSI8Bit WHEAT_1 = new ANSI8Bit("WHEAT_1", 229, 255, 255, 175);
    public static final ANSI8Bit CORNSILK_1 = new ANSI8Bit("CORNSILK_1", 230, 255, 255, 215);
    public static final ANSI8Bit WHITE_1 = new ANSI8Bit("WHITE_1", 231, 255, 255, 255);

    // --- Grayscale (232-255) ---
    public static final ANSI8Bit GREY_3 = new ANSI8Bit("GREY_3", 232, 8, 8, 8);
    public static final ANSI8Bit GREY_7 = new ANSI8Bit("GREY_7", 233, 18, 18, 18);
    public static final ANSI8Bit GREY_11 = new ANSI8Bit("GREY_11", 234, 28, 28, 28);
    public static final ANSI8Bit GREY_15 = new ANSI8Bit("GREY_15", 235, 38, 38, 38);
    public static final ANSI8Bit GREY_19 = new ANSI8Bit("GREY_19", 236, 48, 48, 48);
    public static final ANSI8Bit GREY_23 = new ANSI8Bit("GREY_23", 237, 58, 58, 58);
    public static final ANSI8Bit GREY_27 = new ANSI8Bit("GREY_27", 238, 68, 68, 68);
    public static final ANSI8Bit GREY_30 = new ANSI8Bit("GREY_30", 239, 78, 78, 78);
    public static final ANSI8Bit GREY_35 = new ANSI8Bit("GREY_35", 240, 88, 88, 88);
    public static final ANSI8Bit GREY_39 = new ANSI8Bit("GREY_39", 241, 98, 98, 98);
    public static final ANSI8Bit GREY_42 = new ANSI8Bit("GREY_42", 242, 108, 108, 108);
    public static final ANSI8Bit GREY_46 = new ANSI8Bit("GREY_46", 243, 118, 118, 118);
    public static final ANSI8Bit GREY_50 = new ANSI8Bit("GREY_50", 244, 128, 128, 128);
    public static final ANSI8Bit GREY_54 = new ANSI8Bit("GREY_54", 245, 138, 138, 138);
    public static final ANSI8Bit GREY_58 = new ANSI8Bit("GREY_58", 246, 148, 148, 148);
    public static final ANSI8Bit GREY_62 = new ANSI8Bit("GREY_62", 247, 158, 158, 158);
    public static final ANSI8Bit GREY_66_B = new ANSI8Bit("GREY_66_B", 248, 168, 168, 168);
    public static final ANSI8Bit GREY_70 = new ANSI8Bit("GREY_70", 249, 178, 178, 178);
    public static final ANSI8Bit GREY_74 = new ANSI8Bit("GREY_74", 250, 188, 188, 188);
    public static final ANSI8Bit GREY_78 = new ANSI8Bit("GREY_78", 251, 198, 198, 198);
    public static final ANSI8Bit GREY_82 = new ANSI8Bit("GREY_82", 252, 208, 208, 208);
    public static final ANSI8Bit GREY_85 = new ANSI8Bit("GREY_85", 253, 218, 218, 218);
    public static final ANSI8Bit GREY_89 = new ANSI8Bit("GREY_89", 254, 228, 228, 228);
    public static final ANSI8Bit GREY_93 = new ANSI8Bit("GREY_93", 255, 238, 238, 238);
    public static final ANSI8Bit[] VALUES = new ANSI8Bit[]{
            BLACK, RED, GREEN, YELLOW, BLUE, MAGENTA, CYAN, WHITE, BRIGHT_BLACK,
            GREY_0, NAVY_BLUE, DARK_BLUE, BLUE_3A, BLUE_3B, BLUE_1, DARK_GREEN,
            DEEP_SKY_BLUE_3B, DODGER_BLUE_1, GREEN_3A, SPRING_GREEN_3A, DARK_CYAN,
            SPRING_GREEN_1, MEDIUM_SPRING_GREEN, CYAN_2, CYAN_1, DARK_RED_2, DEEP_PINK_4A,
            PALE_TURQUOISE_4, STEEL_BLUE_3A, STEEL_BLUE_3B, CORNFLOWER_BLUE, CHARTREUSE_3A,
            CHARTREUSE_2A, SEA_GREEN_2, SEA_GREEN_1A, SEA_GREEN_1B, AQUAMARINE_1A,
            SLATE_BLUE_1, DARK_VIOLET_1B, DARK_GOLDENROD, TAN, MISTY_ROSE_3, THISTLE_3,
            DARK_SEA_GREEN_2A, DARK_SEA_GREEN_2B, LIGHT_CYAN_3, LIGHT_SKY_BLUE_3B,
            DARK_ORANGE_3A, INDIAN_RED_1A, HOT_PINK_3A, MEDIUM_ORCHID_3, MEDIUM_ORCHID,
            LIGHT_STEEL_BLUE_3, LIGHT_STEEL_BLUE, YELLOW_3A, DARK_OLIVE_GREEN_3,
            DEEP_PINK_3B, MAGENTA_3C, MAGENTA_3D, MAGENTA_2A, DARK_ORANGE_3B,
            GOLD_3B, LIGHT_GOLDENROD_3B, TAN_2, MISTY_ROSE_3B, THISTLE_3B, PLUM_2,
            DARK_OLIVE_GREEN_1D, HONEYDEW, RED_1, DEEP_PINK_2, DEEP_PINK_1A, DEEP_PINK_1B,
            LIGHT_CORAL, PALE_VIOLET_RED_1, ORCHID_1, PLUM_1, ORANGE_1, SANDY_BROWN,
            YELLOW_1, LIGHT_GOLDENROD_1, KHAKI_1, WHEAT_1, CORNSILK_1, WHITE_1, GREY_3,
            GREY_42, GREY_46, GREY_50, GREY_54, GREY_58, GREY_62, GREY_66_B, GREY_70,
            GREY_7, GREY_11, GREY_15, GREY_19, GREY_23, GREY_27, GREY_30, GREY_35,
            GREY_39, GREY_74, GREY_78, GREY_82, GREY_85, GREY_89, GREY_93, BRIGHT_RED,
            BRIGHT_GREEN, BRIGHT_YELLOW, BRIGHT_BLUE, BRIGHT_MAGENTA, BRIGHT_CYAN, BRIGHT_WHITE,
            DEEP_SKY_BLUE_4A, DEEP_SKY_BLUE_4B, DEEP_SKY_BLUE_4C, DODGER_BLUE_3, DODGER_BLUE_2,
            GREEN_4, SPRING_GREEN_4, TURQUOISE_4, DEEP_SKY_BLUE_3A, LIGHT_SEA_GREEN, DEEP_SKY_BLUE_2,
            DEEP_SKY_BLUE_1, GREEN_3B, SPRING_GREEN_3B, SPRING_GREEN_2A, CYAN_3, DARK_TURQUOISE,
            TURQUOISE_2, GREEN_1, SPRING_GREEN_2B, PURPLE_4A, PURPLE_4B, PURPLE_3, BLUE_VIOLET,
            ORANGE_4A, GREY_37, MEDIUM_PURPLE_4, SLATE_BLUE_3A, SLATE_BLUE_3B, ROYAL_BLUE_1,
            CHARTREUSE_4, DARK_SEA_GREEN_4A, DARK_SEA_GREEN_4B, CADET_BLUE_2, CADET_BLUE_1, SKY_BLUE_3,
            STEEL_BLUE_1A, CHARTREUSE_3B, PALE_GREEN_3A, SEA_GREEN_3, AQUAMARINE_3, MEDIUM_TURQUOISE,
            STEEL_BLUE_1B, DARK_SLATE_GRAY_2, DARK_RED_1, DEEP_PINK_4B, MEDIUM_PURPLE_4B, DEEP_PINK_4C,
            MAGENTA_3A, DARK_VIOLET_1A, ORANGE_4B, LIGHT_SLATE_GREY, MEDIUM_PURPLE_3A, MEDIUM_PURPLE_3B,
            SLATE_BLUE_1B, MEDIUM_PURPLE_1, CHARTREUSE_2B, DARK_SEA_GREEN_3A, DARK_SEA_GREEN_3B,
            DARK_SEA_GREEN_2, GREY_63, LIGHT_SKY_BLUE_3A, CHARTREUSE_2C, YELLOW_GREEEN, GREEN_YELLOW,
            DARK_SEA_GREEN_1A, DARK_SEA_GREEN_1B, PALE_GREEN_1A, DARK_SLATE_GRAY_1, SKY_BLUE_1, RED_3A,
            DEEP_PINK_4D, MEDIUM_VIOLET_RED, MAGENTA_3B, DARK_VIOLET_1C, PURPLE_1A, MEDIUM_PURPLE_2,
            DARK_GOLDENROD_3A, LIGHT_GOLDENROD_3, ROSY_BROWN, GREY_66, MEDIUM_PURPLE_3C, MEDIUM_PURPLE_1B,
            GOLD_3A, DARK_KHAKI, NAVAJO_WHITE_3, GREY_69, DARK_SEA_GREEN_3C, DARK_SEA_GREEN_2C, LIGHT_CYAN_2,
            LIGHT_SKY_BLUE_1, GREEN_YELLOW_2, DARK_OLIVE_GREEN_2, PALE_GREEN_1B, DARK_SEA_GREEN_1C,
            DARK_SEA_GREEN_1D, PALE_TURQUOISE_1, RED_3B, DEEP_PINK_3A, INDIAN_RED_1B, HOT_PINK_3B,
            HOT_PINK_2, ORCHID_2, MEDIUM_ORCHID_1A, ORANGE_3, LIGHT_SALMON_3A, LIGHT_PINK_3, PINK_3,
            PLUM_3, VIOLET, YELLOW_3B, KHAKI_3, LIGHT_GOLDENROD_2A, LIGHT_YELLOW_3, GREY_84,
            LIGHT_STEEL_BLUE_1, YELLOW_2, DARK_OLIVE_GREEN_1A, DARK_OLIVE_GREEN_1B, DARK_OLIVE_GREEN_1C,
            MAGENTA_2B, MAGENTA_1, ORANGE_RED_1, INDIAN_RED_1C, INDIAN_RED_1D, HOT_PINK_1A, HOT_PINK_1B,
            MEDIUM_ORCHID_1B, DARK_ORANGE, SALMON_1, LIGHT_SALMON_1, LIGHT_PINK_1, PINK_1, THISTLE_1,
            GOLD_1, LIGHT_GOLDENROD_2B, LIGHT_GOLDENROD_2C, NAVAJO_WHITE_1, MISTY_ROSE1, THISTLE_1B,
    };

    public static ANSI8Bit[] values() {
        return VALUES;
    }

    /**
     * Get the nearest value of color {@link ANSI8Bit} from the {@code hsl}
     *
     * @param hsl the {@code hsl} color
     * @return the nearest value of color {@link ANSI8Bit}
     */
    public static ANSI8Bit getNearestFromHSL(double[] hsl) {
        return getNearestFromARGB(HSLConverter.getInstance().toARGB(hsl));
    }

    /**
     * Get the nearest value of color {@link ANSI8Bit} from the {@code hsb}
     *
     * @param hsb the {@code hsb} color
     * @return the nearest value of color {@link ANSI8Bit}
     */
    public static ANSI8Bit getNearestFromHSB(double[] hsb) {
        return getNearestFromARGB(HSBConverter.getInstance().toARGB(hsb));
    }

    /**
     * Get the nearest value of color {@link ANSI8Bit} from the {@code hex}
     *
     * @param hex the {@code hex} color
     * @return the nearest value of color {@link ANSI8Bit}
     */
    public static ANSI8Bit getNearestFromHex(int hex) {
        return getNearestFromARGB(IntegerHexConverter.getInstance().toARGB(hex));
    }

    /**
     * Get the nearest value of color {@link ANSI8Bit} from the {@code hex}
     *
     * @param hex the {@code hex} color
     * @return the nearest value of color {@link ANSI8Bit}
     */
    public static ANSI8Bit getNearestFromHex(String hex) {
        return getNearestFromARGB(StringHexConverter.getInstance().toARGB(hex));
    }

    /**
     * Get the nearest value of color {@link ANSI8Bit} from the {@code rgb}
     *
     * @param rgb the {@code rgb} color
     * @return the nearest value of color {@link ANSI8Bit}
     */
    public static ANSI8Bit getNearestFromRGB(int rgb) {
        return getNearestFromARGB(DecimalConverter.getInstance().toARGB(rgb));
    }

    /**
     * Get the nearest value of color {@link ANSI8Bit} from the {@code rgb}
     *
     * @param rgb the {@code rgb} color
     * @return the nearest value of color {@link ANSI8Bit}
     */
    public static ANSI8Bit getNearestFromRGB(int[] rgb) {
        return getNearestFromARGB(rgb);
    }

    /**
     * Get the nearest value of color {@link ANSI8Bit} from the {@code argb}
     *
     * @param argb the {@code argb} color
     * @return the nearest value of color {@link ANSI8Bit}
     */
    public static ANSI8Bit getNearestFromARGB(int[] argb) {
        return ANSI.getNearest(argb, values());
    }

    protected ANSI8Bit(String name, int code, int r, int g, int b) {
        super(name, code, r, g, b);
    }
}
