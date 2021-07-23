package uz.fayz.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class KrillBase {

    String[] andijonRegionsKr = {"Асака", "Балиқчи", "Бўстон", "Булоқбоши", "Избоскан", "Жалақудуқ",
            "Хўжаобод", "Қўрғонтепа", "Марҳамат", "Олтинкўл", "Пахтаобод", "Шаҳрихон", "Улуғнор", "Хонобод шаҳри", "Андижон тумани", "Андижон шаҳри"};

    String[] buxoroRegionsKr = {"Вобкент", "Жондор", "Когон тумани", "Когон шаҳри", "Олот", "Пешку", "Ромитан", "Шофиркон",
            "Қоракўл", "Ғиждувон", "Қоровулбозор", "Бухоро тумани", "Бухоро шаҳри"};

    String[] farganaRegionsKr = {"Олтиариқ", "Боғдод", "Бешариқ", "Бувайда", "Данғара", "Фурқат", "Қўштепа",
            "Қўқон шаҳри", "Қува", "Қувасой шаҳри", "Риштон", "Марғилон шаҳри", "Сўх", "Тошлоқ", "Учкўприк", "Ўзбекистон", "Ёзёвон", "Фарғона тумани", "Фарғона шаҳри"};

    String[] sirdaryoRegionsKr = {"Боёвут", "Гулистон", "Мирзаобод", "Оқолтин", "Сайхунобод",
            "Ховос", "Сардоба", "Сирдарё тумани", "Гулистон шаҳри", "Ширин шаҳри", "Янгиер шаҳри"};

    String[] qoraqalpoqRegionsKr = {"Амударё", "Беруний", "Бўзатов", "Чимбой", "Элликқалъа", "Кегейли", "Мўйноқ", "Қонликўл",
            "Қўнғирот", "Қораўзак", "Шуманай", "Тахиатош", "Тахтакўпир", "Тўрткўл", "Хўжайли", "Нукус тумани", "Нукус шаҳри"};

    String[] navoiyRegionsKr = {"Конимех", "Кармана", "Қизилтепа", "Хатирчи", "Навбаҳор", "Нурота", "Ғозғон шаҳри", "Томди", "Учқудуқ",
            "Зарафшон шаҳри",  "Навоий шаҳри"};

    String[] jizzaxRegionsKr = {"Арнасой", "Бахмал", "Дўстлик", "Шароф Рашидов", "Зарбдор", "Зафаробод", "Зомин", "Мирзачўл",
            "Пахтакор", "Янгиобод", "Фориш", "Ғаллаорол", "Жиззах шаҳри"};

    String[] xorazmRegionsKr = {"Боғот", "Гурлан", "Урганч", "Урганч шаҳри", "Хива", "Хива шаҳри", "Хонқа", "Шовот", "Янгиариқ", "Янгибозор","Қўшкўпир",
            "Ҳазорасп", "Тупроққалъа"};

    String[] namanganRegionsKr = {"Косонсой", "Мингбулоқ", "Норин", "Поп", "Тўрақўрғон", "Уйчи", "Учқўрғон",
            "Чортоқ", "Чуст", "Наманган тумани", "Наманган шаҳри", "Янгиқўрғон"};

    String[] qashqadaryoRegionsKr = {"Деҳқонобод", "Касби", "Китоб", "Косон", "Миришкор", "Муборак", "Нишон", "Чироқчи",
            "Шаҳрисабз", "Шаҳрисабз шаҳри", "Яккабоғ", "Қамаши", "Ғузор", "Қарши тумани", "Қарши шаҳри"};

    String[] samarqandRegionsKr = {"Булунғур", "Жомбой", "Иштихон", "Каттақўрғон", "Каттақўрғон шаҳри", "Нарпай", "Нуробод", "Оқдарё",
            "Паяриқ", "Пастдарғом", "Пахтачи", "Тойлоқ", "Ургут", "Қўшработ", "Самарқанд тумани", "Самарқанд шаҳри"};

    String[] surxondaryoRegionsKr = {"Ангор", "Бандихон", "Бойсун", "Денов", "Жарқўрғон", "Музработ", "Олтинсой",
            "Сариосиё", "Узун", "Шеробод", "Шўрчи", "Қизириқ", "Қумқўрғон", "Термиз тумани", "Термиз шаҳри"};

    String[] tashkentRegionsKr = {"Ангрен", "Бекобод", "Бекобод шаҳри", "Бўстонлиқ", "Бўка", "Чиноз", "Қибрай", "Оҳангарон", "Оҳангарон шаҳри", "Оққўрғон", "Олмалиқ шаҳри",
            "Паркент", "Пискент", "Нурафшон шаҳри", "Қуйичирчиқ", "Ўртачирчиқ", "Чирчиқ шаҳри", "Тошкент тумани", "Янги йўл", "Янгийўл шаҳри", "Юқоричирчиқ", "Зангиота"};

    String[] tashkentCityRegionsKr = {"Бектемир", "Миробод", "Сергели", "Олмазор", "Учтепа",
             "Чилонзор", "Яшнобод", "Яккасарой", "Янгиҳаёт", "Юнусобод", "Мирзо Улуғбек", "Шайхонтоҳур"};

    public static HashMap<String, String[]> provinceAndRegionsKrill(){
        HashMap<String, String[]> hashMapKrill = new HashMap<>();
        KrillBase krillBase = new KrillBase();
        hashMapKrill.put("Андижон", krillBase.getAndijonRegionsKr());
        hashMapKrill.put("Бухоро", krillBase.getBuxoroRegionsKr());
        hashMapKrill.put("Фарғона", krillBase.getFarganaRegionsKr());
        hashMapKrill.put("Жиззах", krillBase.getJizzaxRegionsKr());
        hashMapKrill.put("Хоразм", krillBase.getXorazmRegionsKr());
        hashMapKrill.put("Наманган", krillBase.getNamanganRegionsKr());
        hashMapKrill.put("Навоий", krillBase.getNavoiyRegionsKr());
        hashMapKrill.put("Қашқадарё", krillBase.getQashqadaryoRegionsKr());
        hashMapKrill.put("Самарқанд", krillBase.getSamarqandRegionsKr());
        hashMapKrill.put("Сирдарё", krillBase.getSirdaryoRegionsKr());
        hashMapKrill.put("Сурхондарё", krillBase.getSurxondaryoRegionsKr());
        hashMapKrill.put("Тошкент вил.", krillBase.getTashkentRegionsKr());
        hashMapKrill.put("Тошкент шаҳри", krillBase.getTashkentCityRegionsKr());
        hashMapKrill.put("Қорақолпоғистон Рес", krillBase.getQoraqalpoqRegionsKr());
        return hashMapKrill;
    }

    public static String[] provinceKr = {"Андижон", "Бухоро", "Фарғона", "Жиззах", "Хоразм", "Наманган", "Навоий",
            "Қашқадарё", "Самарқанд", "Сирдарё", "Сурхондарё", "Тошкент вил.",
            "Тошкент шаҳри", "Қорақолпоғистон Рес"};
}
