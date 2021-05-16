package by.adukar.telegrambot.consts;

import java.util.HashMap;
import java.util.Map;

public class Dictionary {

    public static Map<String, String> dictionaryEnglishEat = new HashMap<>();
    public static Map<String, String> dictionaryEnglishFurniture = new HashMap<>();
    public static Map<String, String> dictionaryEnglishСlothing = new HashMap<>();
    public static Map<String, String> dictionaryEnglishColors = new HashMap<>();

    public static Map<String, String> dictionaryGermanEat = new HashMap<>();
    public static Map<String, String> dictionaryGermanFurniture = new HashMap<>();
    public static Map<String, String> dictionaryGermanСlothing = new HashMap<>();
    public static Map<String, String> dictionaryGermanColors = new HashMap<>();

    public static Map<String, String> dictionaryFrenchEat = new HashMap<>();
    public static Map<String, String> dictionaryFrenchFurniture = new HashMap<>();
    public static Map<String, String> dictionaryFrenchСlothing = new HashMap<>();
    public static Map<String, String> dictionaryFrenchColors = new HashMap<>();




    public void init(){
        dictionaryEnglishEat.put("Яблоко","аpple");
        dictionaryEnglishEat.put("Хлеб","bread");
        dictionaryEnglishEat.put("Конфета","candy");

        dictionaryGermanEat.put("Яблоко","Apfel");
        dictionaryGermanEat.put("Хлеб","Brot");
        dictionaryGermanEat.put("Конфета","Süßigkeiten");

        dictionaryFrenchEat.put("Яблоко","Pomme");
        dictionaryFrenchEat.put("Хлеб","pain");
        dictionaryFrenchEat.put("Конфета","Des bonbons");



        dictionaryEnglishFurniture.put("Шкаф","cupboard");
        dictionaryEnglishFurniture.put("Диван","sofa");
        dictionaryEnglishFurniture.put("Стол","table");

        dictionaryGermanFurniture.put("Шкаф","Schrank");
        dictionaryGermanFurniture.put("Диван","Sofa");
        dictionaryGermanFurniture.put("Стол","Tisch");

        dictionaryFrenchFurniture.put("Шкаф","armoire");
        dictionaryFrenchFurniture.put("Диван","sofa");
        dictionaryFrenchFurniture.put("Стол","tableau");



        dictionaryEnglishСlothing.put("Куртка","jacket");
        dictionaryEnglishСlothing.put("Носки","socks");
        dictionaryEnglishСlothing.put("Джинсы","jeans");

        dictionaryGermanСlothing.put("Куртка","Jacke");
        dictionaryGermanСlothing.put("Носки","Socken");
        dictionaryGermanСlothing.put("Джинсы","Jeans");

        dictionaryFrenchСlothing.put("Куртка","veste");
        dictionaryFrenchСlothing.put("Носки","chaussettes");
        dictionaryFrenchСlothing.put("Джинсы","jeans");



        dictionaryEnglishColors.put("Синий","blue");
        dictionaryEnglishColors.put("Красный","red");
        dictionaryEnglishColors.put("Желтый","yellow");

        dictionaryGermanColors.put("Синий","Blau");
        dictionaryGermanColors.put("Красный","rot");
        dictionaryGermanColors.put("Желтый","Gelb");

        dictionaryFrenchColors.put("Синий","bleu");
        dictionaryFrenchColors.put("Красный","rouge");
        dictionaryFrenchColors.put("Желтый","Jaune");
    }
}
