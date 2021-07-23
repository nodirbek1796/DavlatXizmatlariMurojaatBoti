package uz.fayz.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.fayz.model.User;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UzbBase {

    public  static  HashMap<Long, User> longUserHashMap = new HashMap<>();

    public static String[] province = {"Andijon", "Buxoro", "Farg‘ona", "Jizzax", "Xorazm", "Namangan", "Navoiy",
            "Qashqadaryo", "Samarqand", "Sirdaryo", "Surxondaryo", "Toshkent vil.",
            "Toshkent shahri", "Qoraqolpog‘iston Res"};

    String[] andijonRegions = {"Asaka", "Baliqchi", "Bo‘ston", "Buloqboshi", "Izboskan", "Jalaquduq",
            "Xo‘jaobod", "Qo‘rg‘ontepa", "Marhamat", "Oltinko‘l", "Paxtaobod", "Shahrixon", "Ulug‘nor", "Xonobod shahri", "Andijon tumani", "Andijon shahri"};

    String[] buxoroRegions = {"Vobkent", "Jondor", "Kogon tumani", "Kogon shahri", "Olot", "Peshku", "Romitan", "Shofirkon",
            "Qorako‘l", "G‘ijduvon", "Qorovulbozor", "Buxoro tumani", "Buxoro shahri"};

    String[] farganaRegions = {"Oltiariq", "Bog‘dod", "Beshariq", "Buvayda", "Dang‘ara", "Furqat", "Qo‘shtepa",
            "Qo'qon shahri", "Quva", "Quvasoy shahri", "Rishton", "Marg'ilon shahri", "So‘x", "Toshloq", "Uchko‘prik", "O‘zbekiston", "Yozyovon", "Farg‘ona tumani", "Farg'ona shahri"};

    String[] jizzaxRegions = {"Arnasoy", "Baxmal", "Doʻstlik", "Sharof Rashidov", "Zarbdor", "Zafarobod", "Zomin", "Mirzachoʻl",
            "Paxtakor", "Yangiobod", "Forish", "Gʻallaorol", "Jizzax shahri"};

    String[] xorazmRegions = {"Bogʻot", "Gurlan", "Urganch", "Urganch shahri", "Xiva", "Xiva shahri", "Xonqa", "Shovot", "Yangiariq", "Yangibozor","Qoʻshkoʻpir",
            "Hazorasp", "Tuproqqal’a"};

    String[] namanganRegions = {"Kosonsoy", "Mingbuloq", "Norin", "Pop", "Toʻraqoʻrgʻon", "Uychi", "Uchqoʻrgʻon",
            "Chortoq", "Chust", "Namangan tumani", "Namangan shahri", "Yangiqoʻrgʻon"};

    String[] navoiyRegions = {"Konimex", "Karmana", "Qiziltepa", "Xatirchi", "Navbahor", "Nurota", "G'ozg'on shahri", "Tomdi", "Uchquduq",
            "Zarafshon shahri",  "Navoiy shahri"};

    String[] qashqadaryoRegions = {"Dehqonobod", "Kasbi", "Kitob", "Koson", "Mirishkor", "Muborak", "Nishon", "Chiroqchi",
            "Shahrisabz", "Shahrisabz shahri", "Yakkabogʻ", "Qamashi", "Gʻuzor", "Qarshi tumani", "Qarshi shahri"};

    String[] qoraqalpoqRegions = {"Amudaryo", "Beruniy", "Bo'zatov", "Chimboy", "Ellikqal’a", "Kegeyli", "Mo‘ynoq", "Qonliko‘l",
            "Qo‘ng‘irot", "Qorao‘zak", "Shumanay", "Taxiatosh", "Taxtako‘pir", "To‘rtko‘l", "Xo‘jayli", "Nukus tumani", "Nukus shahri"};

    String[] samarqandRegions = {"Bulungʻur", "Jomboy", "Ishtixon", "Kattaqoʻrgʻon", "Kattaqoʻrgʻon shahri", "Narpay", "Nurobod", "Oqdaryo",
            "Payariq", "Pastdargʻom", "Paxtachi", "Toyloq", "Urgut", "Qoʻshrabot", "Samarqand tumani", "Samarqand shahri"};

    String[] sirdaryoRegions = {"Boyovut", "Guliston", "Mirzaobod", "Oqoltin", "Sayxunobod",
            "Xovos", "Sardoba", "Sirdaryo tumani", "Guliston shahri", "Shirin shahri", "Yangiyer shahri"};

    String[] surxondaryoRegions = {"Angor", "Bandixon", "Boysun", "Denov", "Jarqoʻrgʻon", "Muzrabot", "Oltinsoy",
            "Sariosiyo", "Uzun", "Sherobod", "Shoʻrchi", "Qiziriq", "Qumqoʻrgʻon", "Termiz tumani", "Termiz shahri"};

    String[] tashkentRegions = {"Angren", "Bekobod", "Bo‘stonliq", "Bo‘ka", "Chinoz", "Qibray", "Ohangaron", "Oqqo‘rg‘on",
            "Parkent", "Piskent", "Quyi chirchiq", "O‘rta chirchiq", "Yangi yo‘l", "Yuqori chirchiq", "Zangiota"};

    String[] tashkentCityRegions = {"Bektemir", "Mirobod", "Sergeli", "Olmazor", "Uchtepa",
             "Chilonzor", "Yashnobod", "Yakkasaroy", "Yangihayot", "Yunusobod", "Mirzo Ulug‘bek", "Shayxontohur"};

    public static HashMap<String, String[]> provinceAndRegions(){
        HashMap<String, String[]> hashMap = new HashMap<>();
        UzbBase uzbBase = new UzbBase();
        hashMap.put("Andijon", uzbBase.getAndijonRegions());
        hashMap.put("Buxoro", uzbBase.getBuxoroRegions());
        hashMap.put("Farg‘ona", uzbBase.getFarganaRegions());
        hashMap.put("Jizzax", uzbBase.getJizzaxRegions());
        hashMap.put("Xorazm", uzbBase.getXorazmRegions());
        hashMap.put("Namangan", uzbBase.getNamanganRegions());
        hashMap.put("Navoiy", uzbBase.getNavoiyRegions());
        hashMap.put("Qashqadaryo", uzbBase.getQashqadaryoRegions());
        hashMap.put("Qoraqolpog‘iston Res", uzbBase.getQoraqalpoqRegions());
        hashMap.put("Samarqand", uzbBase.getSamarqandRegions());
        hashMap.put("Sirdaryo", uzbBase.getSirdaryoRegions());
        hashMap.put("Surxondaryo", uzbBase.getSurxondaryoRegions());
        hashMap.put("Toshkent vil.", uzbBase.getTashkentRegions());
        hashMap.put("Toshkent shahri", uzbBase.getTashkentCityRegions());
        return hashMap;
    }
}