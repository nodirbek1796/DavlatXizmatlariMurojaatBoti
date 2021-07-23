package uz.fayz.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RussianBase {

    public static String[] provinceRu = {"Андижан", "Бухара", "Фергана", "Джизак", "Хорезм", "Наманган", "Навои",
            "Кашкадарья", "Самарканд", "Сырдарья", "Сурхандарья", "Ташкентская область",
            "Город Ташкент", "Каракалпакстан Рес"};

    String[] andijonRegionsRu = {"Асака", "Баликчи", "Бостон", "Булокбоши", "Избоскан", "Джалакудук",
            "Ходжаабад", "Кургантепа", "Мархамат", "Олтинкол", "Пахтаабад", "Шахрихан", "Улугнор", "г. Ханабад", "Андижанский район", "г. Андижан"};

    String[] buxoroRegionsRu = {"Вобкент", "Жондор", "Когон", "г. Каган", "Олот", "Пешку", "Ромитан", "Шофиркон",
            "Каракол", "Гиждувон", "Каравулбозор", "Бухарский район", "г. Бухара"};

    String[] farganaRegionsRu = {"Алтиарик", "Багдад", "Бешарик", "Бувайда", "Дангара", "Фуркат", "Коштепа",
            "г. Коканд", "Кува", "г. Қувасой", "Риштан", "г. Маргилан", "Сох", "Тошлок", "Учкоприк", "Узбекистан", "Язявон", "Ферганский район", "г. Фергана"};

    String[] jizzaxRegionsRu = {"Арнасой", "Бахмал", "Дустлик", "Шароф Рашидов", "Зарбдор", "Зафарабад", "Зомин", "Мирзачол",
            "Пахтакор", "Янгиабад", "Фориш", "Галлаорол", "г. Джиззах"};

    String[] xorazmRegionsRu = {"Багат", "Гурлан", "Ургенч", "г. Ургенч", "Хива", "г. Хива", "Хонка", "Шовот", "Янгиарик", "Янгибозор", "Кошкопир",
            "Хазорасп", "Тироккала"};

    String[] namanganRegionsRu = {"Косонсой", "Мингбулок", "Норин", "Поп", "Туракурган", "Уйчи", "Учкурган",
            "Чартак", "Чуст", "Наманганский район", "г. Наманган", "Янгикурган"};

    String[] navoiyRegionsRu = {"Конимекс", "Кармана", "Кызылтепа", "Хатырчи", "Навбахор", "Нурота", "г. Газган", "Томди", "Учкудук",
            "г. Зарафшан", "г. Навои"};

    String[] qashqadaryoRegionsRu = {"Дехканабад", "Касби", "Китаб", "Касан", "Миришкор", "Муборак", "Нишон", "Чиракчи",
            "Шахрисабз", "г. Шахрисабз", "Яккабог", "Камаши", "Гузар", "Каршинский район", "Каршинский г."};

    String[] qoraqalpoqRegionsRu = {"Амударья", "Беруний", "Бузатов", "Чимбой", "Элликкала", "Кегейли", "Канлыкуль", "Мойнак",
            "Кунград", "Караозак", "Шуманай", "Тахиатош", "Тахтакупир", "Турткуль", "Ходжайлы", "Нукусский район", "г. Нукус"};

    String[] samarqandRegionsRu = {"Булунгур", "Джомбой", "Иштихон", "Каттакурган", "г. Каттакурган", "Нарпай", "Нурабад", "Акдарё",
            "Паярик", "Пастдаргом", "Пахтачи", "Тойлок", "Ургут", "Кошработ", "Самаркандский район", "г. Самарканд"};

    String[] sirdaryoRegionsRu = {"Боевут", "Гулистон", "Мирзаабад", "Околтин", "Сайхунобод",
            "Говос", "Сардоба", "Сырдарьинский район", "г. Гулистан", "г. Ширин", "г. Янгиер"};

    String[] surxondaryoRegionsRu = {"Ангор", "Бандихон", "Байсун", "Денов", "Жаркоргон", "Музработ", "Олтинсой",
            "Сариосиё", "Узун", "Шерабад", "Шурчи", "Гызырик", "Кумкурган", "Термезский район", "г. Термез"};

    String[] tashkentRegionsRu = {"Ангрен", "Бекабад", "г. Бекабад", "Бостанлык", "Бока", "Чиноз", "Кибрай", "Ахангарон", "г. Ахангаран", "Аккурган", "г. Алмалык",
            "Паркент", "Пискент", "г. Нурафшон", "Kуйичирчиk", "Уртачирчиk", "Янги йул", "г. Янгийул", "Юkоричирчиk", "г. Чирчик", "Ташкентский район", "Зангиота"};

    String[] tashkentCityRegionsRu = {"Бектемир", "Мирабад", "Сергели", "Алмазор", "Учтепа",
            "Чиланзор", "Яшнабад", "Янгихаёт", "Яккасарай", "Юнусабад", "Мирзо Улугбек", "Шайхантахур"};

    public static HashMap<String, String[]> provinceAndRegionsRu(){
        HashMap<String, String[]> hashMapRu = new HashMap<>();
        RussianBase russianBase = new RussianBase();

        hashMapRu.put("Андижан", russianBase.getAndijonRegionsRu());
        hashMapRu.put("Бухара", russianBase.getBuxoroRegionsRu());
        hashMapRu.put("Фергана", russianBase.getFarganaRegionsRu());
        hashMapRu.put("Джизак", russianBase.getJizzaxRegionsRu());
        hashMapRu.put("Хорезм", russianBase.getXorazmRegionsRu());
        hashMapRu.put("Наманган", russianBase.getNamanganRegionsRu());
        hashMapRu.put("Навои", russianBase.getNavoiyRegionsRu());
        hashMapRu.put("Кашкадарья", russianBase.getQashqadaryoRegionsRu());
        hashMapRu.put("Самарканд", russianBase.getSamarqandRegionsRu());
        hashMapRu.put("Сырдарья", russianBase.getSirdaryoRegionsRu());
        hashMapRu.put("Сурхандарья", russianBase.getSurxondaryoRegionsRu());
        hashMapRu.put("Ташкентская область", russianBase.getTashkentRegionsRu());
        hashMapRu.put("Город Ташкент", russianBase.getTashkentCityRegionsRu());
        hashMapRu.put("Каракалпакстан Рес", russianBase.getQoraqalpoqRegionsRu());
        return hashMapRu;
    }
}
