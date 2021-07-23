package uz.fayz.service;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.fayz.model.CheckBirthDate;
import uz.fayz.model.User;
import uz.fayz.response.Krill;
import uz.fayz.response.Russian;
import uz.fayz.response.Uzb;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public interface BaseService extends Uzb, Russian, Krill {

    default Long getUserChatId(Update update) {
        if (update.hasMessage())
            return update.getMessage().getChatId();
        return update.getCallbackQuery().getMessage().getChatId();
    }

    default String getUserResponse(Update update) {
        if (update.hasMessage())
            if (update.getMessage().hasText())
                return update.getMessage().getText();
            else
                return "Nothing not found!";
        return update.getCallbackQuery().getData();
    }

    default User getCurrentUser(Long newChatId, HashMap<Long, User> longUserHashMap) {
        return longUserHashMap.get(newChatId);
    }

    default boolean isHasUser(Long newChatId, HashMap<Long, User> longUserHashMap) {
        Set<Long> longs = longUserHashMap.keySet();
        return longs.contains(newChatId);
    }

    default ReplyKeyboardMarkup chooseYourLanguage()
    {
        return createMarkupButtons(
                "\uD83C\uDDFA\uD83C\uDDFF O'zbekcha",
                "\uD83C\uDDF7\uD83C\uDDFA Русский",
                "\uD83C\uDDFA\uD83C\uDDFF Ўзбекча"
        );
    }

    default boolean isNewProposition(String inputText){
        return  inputText.equals("/start") ||
                inputText.equals("Yangi murojaat yuborish!") ||
                inputText.equals("Отправить новый обращаться!") ||
                inputText.equals("Янги мурожаат юбориш!");
    }

    default boolean isRussian(String inputText){
        return inputText.endsWith("Русский");
    }

    default boolean isUzbek(String inputText){
        return inputText.endsWith("O'zbekcha");
    }

    default boolean isKrill(String inputText) {
        return inputText.endsWith("Ўзбекча");
    }

    default boolean isLanguage(String inputText){
        return inputText.startsWith("\uD83C\uDDFA\uD83C\uDDFF")
                ||
                inputText.startsWith("\uD83C\uDDF7\uD83C\uDDFA");
    }

    default ReplyKeyboardMarkup makeReplyMarkup() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);

        return replyKeyboardMarkup;
    }

    default ReplyKeyboardMarkup createMarkupButtons(ArrayList<String> arrayList) {
        int index = 0;
        boolean success = true;

        ReplyKeyboardMarkup replyKeyboardMarkup = makeReplyMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow keyboardRow;
        while (success) {
            keyboardRow = new KeyboardRow();
            for (int i = 0; i < 3 && index <= arrayList.size() - 1; i++) {
                keyboardRow.add(arrayList.get(index++));
            }
            rowList.add(keyboardRow);
            if (index == arrayList.size())
                success = false;
        }
        replyKeyboardMarkup.setKeyboard(rowList);
        return replyKeyboardMarkup;
    }

    default CheckBirthDate isTrueBirthdateUz(String inputText){
        return isTrueBirthdateHelper
        (
            inputText,
            "Iltimos, tug'ilgan sananggizni raqamlar orqali," +
                        " tizimda ko'rsatilgan \uD83D\uDC49 (sana/oy/yil) tartibda qaytadan kiriting!",
            "Iltimos, tug'ilgan sananggizni tekshirib," +
                        " tizimda ko'rsatilgan \uD83D\uDC49 (sana/oy/yil) tartibda qaytadan kiriting!",
            whereIsYourProvince
        );
    }

    default CheckBirthDate isTrueBirthdateRu(String inputText){
        return isTrueBirthdateHelper
                (
                        inputText,
                        "Дата рождения (число/месяц/год)",
                        "Дата рождения (число/месяц/год)",
                        whereIsYourProvinceRu
                );
    }

    default CheckBirthDate isTrueBirthdateKrill(String inputText){
        return isTrueBirthdateHelper
                (
                        inputText,
                        "Туғилган санангиз (сана/ой/йил)",
                        "Туғилган санангиз (сана/ой/йил)",
                        whereIsYourProvinceKrill
                );
    }

    default CheckBirthDate isTrueBirthdateHelper(String inputText, String firstResult, String secondResult, String whereIsYourProvince)
    {
        int maxYear = Calendar.getInstance().get(Calendar.YEAR);        // cheklov qo'yish uchun joriy yilni aniqladim ...
        String resultBirthDate = "";
        String result;
        boolean isSuccess = true;
        int day, month, year;
        String birthDate = getBirthDate(inputText);
        if (birthDate == null || (birthDate.length() < 6 || birthDate.length() > 8))
        {
            result = firstResult;
            isSuccess = false;
        }
        else
        {
            day = Integer.parseInt(birthDate.substring(0, 2));
            month = Integer.parseInt(birthDate.substring(2, 4));
            year = Integer.parseInt(birthDate.substring(4));

            if (day > 31 || month > 12 || (year < maxYear - 120 || year > maxYear))
            {
                result = secondResult;
                isSuccess = false;
            }

            // bu yerda user tug'ilgan kunining formatini (dd/mm/yyyy) to'g'irladim
            else
            {
                result = whereIsYourProvince;
                String dayResult, monthResult;
                dayResult = (day < 10) ?  "0" + day : "" + day;
                monthResult = (month < 10) ? "0" + month : "" + month;
                resultBirthDate = dayResult + "/" + monthResult + "/" + year;
            }
        }
        return new CheckBirthDate(result, isSuccess, resultBirthDate);
    }

    default String getBirthDate(String inputText)
    {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < inputText.length(); i++) {
            if (numbers.contains(String.valueOf(inputText.charAt(i))))
                result.append(inputText.charAt(i));
        }
        return String.valueOf(result);
    }

    default String checkResponseUz(String inputText) {
        String response;
        if (inputText.equalsIgnoreCase("Toshkent shahri")) {
            response = inputText + "ning qaysi tumanida yashaysiz?\n";
        } else if (inputText.equalsIgnoreCase("Qoraqolpog‘iston Res")) {
            response = inputText + "publikasining qaysi tuman yoki shaharida yashaysiz?\n";
        } else {
            response = inputText + " viloyatining qaysi tuman yoki shaharida yashaysiz?\n";
        }
        response += "<b>(\uD83D\uDC47\uD83C\uDFFB Iltimos, quyidagi ro'yxatdan tuman yoki shaharingizni tanlang)</b>";
        return response;
    }

    default String checkResponseRu(String inputText) {
        String response;
        if (inputText.equalsIgnoreCase("Город Ташкент")) {
            response = "Вы с какого района или города город Ташкента\n";
        } 
        else if (inputText.equalsIgnoreCase("Каракалпакстан Рес")) {
            response = "Вы с какого района или города Республика Каракалпакстан\n";
        } else {
            response = "Вы с какого района или города " + inputText + " области?\n";
        }
        response += "<b>(\uD83D\uDC47Пожалуйста, выберите Ваш район или город)</b>\n";
        return response;
    }

    default String checkResponseKrill(String inputText) {
        String response;
        if (inputText.equalsIgnoreCase("Тошкент шаҳри")) {
            response = inputText + "нинг қайси туманида яшайсиз?\n";
        } else if (inputText.equalsIgnoreCase("Қорақолпоғистон Рес")) {
            response = inputText + "публикасининг қайси туман ёки шаҳарида яшайсиз?\n";
        } else {
            response = inputText + " вилоятининг қайси туман ёки шаҳарида яшайсиз?\n";
        }
        response += "<b>(\uD83D\uDC47\uD83C\uDFFB Илтимос, қуйидаги рўйхатдан туман ёки шаҳарингизни танланг)</b>";
        return response;
    }

    default boolean isRegion(
            String inputText,
            User currentUser,
            HashMap<String, String[]> stringUserHashMap
    ) {
        String[] strings = stringUserHashMap.get(currentUser.getProvince());
        return Arrays.toString(strings).contains(inputText);
    }

    default ReplyKeyboardMarkup shareYourContact(String shareYourContact){
        ReplyKeyboardMarkup replyKeyboardMarkup = makeReplyMarkup();
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(new KeyboardButton(shareYourContact)
                .setRequestContact(true));
        replyKeyboardMarkup.setKeyboard(Collections.singletonList(keyboardRow));
        return replyKeyboardMarkup;
    }

    default ReplyKeyboardMarkup createMarkupButtons(String firstButton, String secondButton, String thirdButton)
    {
        ReplyKeyboardMarkup replyKeyboardMarkup = makeReplyMarkup();
        KeyboardRow keyboardRow = new KeyboardRow();
        List<KeyboardRow> rowList = new ArrayList<>();

        keyboardRow.add(firstButton);
        keyboardRow.add(secondButton);
        rowList.add(keyboardRow);
        keyboardRow = new KeyboardRow();
        keyboardRow.add(thirdButton);
        rowList.add(keyboardRow);
        replyKeyboardMarkup.setKeyboard(rowList);
        return replyKeyboardMarkup;
    }

    default ReplyKeyboardMarkup createMarkupButtons(String firstButton)
    {
        ReplyKeyboardMarkup replyKeyboardMarkup = makeReplyMarkup();
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(firstButton);
        replyKeyboardMarkup.setKeyboard(Collections.singletonList(keyboardRow));
        return replyKeyboardMarkup;
    }

    default void sendToGmail(String currentUserInfo){
        // Recipient's email ID needs to be mentioned.
        String recipient = "userargosbot@gmail.com";

        // Sender's email ID needs to be mentioned
        String sender = "murojaatargosbot@gmail.com";
        final String password="r:8;g'S@L>8%.fN'";//change accordingly

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(sender, password);

            }

        });

        // Used to debug SMTP issues
        // mazkur operator ma'lumotlarning gmail ga uzatilish protsessini console ga chiqazib beradi ...
//        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(sender));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

            // Set Subject: header field
            message.setSubject("Murojaatchining ma'lumotlari!");

            // Now set the actual message
            message.setText(currentUserInfo);

//            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }
    
}
