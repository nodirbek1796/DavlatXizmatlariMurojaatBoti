package uz.fayz;

import lombok.SneakyThrows;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import uz.fayz.button.Buttons;
import uz.fayz.database.KrillBase;
import uz.fayz.database.RussianBase;
import uz.fayz.database.UzbBase;
import uz.fayz.model.CheckBirthDate;
import uz.fayz.model.User;
import uz.fayz.response.Krill;
import uz.fayz.response.Russian;
import uz.fayz.response.Uzb;
import uz.fayz.service.BaseService;

import java.util.*;

public class Main extends TelegramLongPollingBot implements Uzb, BaseService, Russian, Krill {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi api = new TelegramBotsApi();
        try {
            api.registerBot(new Main());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    private Long userChatId;
    private String userMessage;

    @Override
    public String getBotToken() {
        return "1636811932:AAGaCQd9hilWVu6TBH3Qif2VZmmOJ0BXUB4";
    }

    @Override
    public String getBotUsername() {
        return "@ulugbek_abdurahimov_bot";
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {

        ArrayList<String> provinceUz = new ArrayList<>();
        Collections.addAll(provinceUz, UzbBase.province);

        ArrayList<String> provinceRu = new ArrayList<>();
        Collections.addAll(provinceRu, RussianBase.provinceRu);

        ArrayList<String> provinceKrill = new ArrayList<>();
        Collections.addAll(provinceKrill, KrillBase.provinceKr);

        userChatId = getUserChatId(update);
        String inputText = getUserResponse(update);

        if (!isHasUser(userChatId, UzbBase.longUserHashMap))
            UzbBase.longUserHashMap.put(userChatId, new User());

        // dasturdagi barcha amallar mazkur user ustida bajariladi ...
        User currentUser = getCurrentUser(userChatId, UzbBase.longUserHashMap);

        if (update.hasCallbackQuery())
        {
            if (inputText.equals("Everything is okay with me!"))
            {
                if (isUzbek(currentUser.getLanguage()))
                    userMessage = whatIsYourName;
                else if (isRussian(currentUser.getLanguage()))
                    userMessage = whatIsYourNameRu;
                else
                    userMessage = whatIsYourNameKrill;
                execute(null, null);
            }
        }
        else
        {
            if (update.getMessage().getContact() == null)
            {
                if (isNewProposition(inputText))
                {
                    userMessage = chooseLanguage;
                    execute(chooseYourLanguage(), null);
                }
                else if (isLanguage(inputText))
                {
                    currentUser.setLanguage(inputText);
                    if (isUzbek(inputText)) {
                        userMessage = hello;
                        execute(null, Buttons.sendInlineKeyBoardMessage());
                    } else if (isRussian(inputText)) {
                        userMessage = helloRu;
                        execute(null, Buttons.sendInlineKeyBoardMessageRu());
                    } else {
                        userMessage = helloKrill;
                        execute(null, Buttons.sendInlineKeyBoardMessageKrill());
                    }
                }
                else if (isUzbek(currentUser.getLanguage()))
                {
                    if (currentUser.getFullName() == null)
                    {
                        currentUser.setFullName(inputText);      // set full name to user
                        userMessage = whenIsYourBirthDate;
                        execute(null, null);
                    }
                    else if (currentUser.getBirthDate() == null)
                    {
                        CheckBirthDate trueBirthdate = isTrueBirthdateUz(inputText);
                        userMessage = trueBirthdate.getResult();
                        if (trueBirthdate.isSuccess())
                        {
                            currentUser.setBirthDate(trueBirthdate.getResultBirthDate());     // set birth date to user
                            execute(createMarkupButtons(provinceUz), null);   // call helper method
                        }
                        else
                            execute(null, null);
                    }
                    else if (provinceUz.toString().contains(inputText))
                    {
                        // set province to user's province field
                        currentUser.setProvince(inputText);
                        // add all regions from hashmap's value to regions array list
                        ArrayList<String> regionsUz = new ArrayList<>();
                        Collections.addAll(regionsUz, UzbBase.provinceAndRegions().get(inputText));

                        // bu yerda userga qanday javob qaytarishni aniqlaganman
                        userMessage = checkResponseUz(inputText);
                        execute(createMarkupButtons(regionsUz), null);    // called helper method
                    }
                    else if (isRegion(inputText, currentUser, UzbBase.provinceAndRegions()))
                    {
                        currentUser.setRegion(inputText);
                        // tumanlar ro'yxati ko'rinmaydigan qilindi
                        userMessage = whereAreYouWork;
                        execute(null, null);
                    }
                    else if (currentUser.getJobName() == null)
                    {
                        currentUser.setJobName(inputText);
                        userMessage = whatIsYourPosition;
                        execute(null, null);
                    }
                    else if (currentUser.getPosition() == null)
                    {
                        currentUser.setPosition(inputText);
                        userMessage = typeOfProposition;

                        ReplyKeyboardMarkup chooseYourTypeOfProposition = createMarkupButtons(
                                "Taklif \uD83D\uDE4B\uD83C\uDFFB\u200D♂️",
                                "Muammo \uD83E\uDD26\uD83C\uDFFB\u200D♂️",
                                "Ariza \uD83D\uDC81\uD83C\uDFFB\u200D♂️"
                        );

                        execute(chooseYourTypeOfProposition, null);
                    }
                    else if (checkTypeOfProposition.contains(inputText))
                    {
                        currentUser.setTypeOfDescription(inputText);
                        userMessage = enterYourProposition;
                        execute(null, null);
                    }
                    else if (currentUser.getDescription() == null)
                    {
                        currentUser.setDescription(inputText);
                        userMessage = sendYourFiles;
                        execute(createMarkupButtons("Davom etish ➡️"), null);
                    }
                    else if (inputText.equals("Davom etish ➡️"))
                    {
                        userMessage = pressShareContactButton;
                        execute
                                (
                                        shareYourContact("Telefon raqamini yuborish \uD83D\uDCF1"),
                                        null
                                );
                    }
                }
                else if (isRussian(currentUser.getLanguage()))
                {
                    if (currentUser.getFullName() == null)
                    {
                        currentUser.setFullName(inputText);      // set full name to user
                        userMessage = whenIsYourBirthDateRu;
                        execute(null, null);
                    }
                    else if (currentUser.getBirthDate() == null)
                    {
                        CheckBirthDate trueBirthdate = isTrueBirthdateRu(inputText);
                        userMessage = trueBirthdate.getResult();
                        if (trueBirthdate.isSuccess())
                        {
                            currentUser.setBirthDate(trueBirthdate.getResultBirthDate());     // set birth date to user
                            execute(createMarkupButtons(provinceRu), null);   // call helper method
                        }
                        else
                            execute(null, null);
                    }
                    else if (provinceRu.toString().contains(inputText))
                    {
                        // set province to user's province field
                        currentUser.setProvince(inputText);
                        // add all regions from hashmap's value to regions array list
                        ArrayList<String> regionsRu = new ArrayList<>();
                        Collections.addAll(regionsRu, RussianBase.provinceAndRegionsRu().get(inputText));

                        // bu yerda userga qanday javob qaytarishni aniqlaganman
                        userMessage = checkResponseRu(inputText);
                        execute(createMarkupButtons(regionsRu), null);    // called helper method
                    }
                    else if (isRegion(inputText, currentUser, RussianBase.provinceAndRegionsRu()))
                    {
                        currentUser.setRegion(inputText);
                        userMessage = whereAreYouWorkRu;
                        execute(null, null);
                    }
                    else if (currentUser.getJobName() == null)
                    {
                        currentUser.setJobName(inputText);
                        userMessage = whatIsYourPositionRu;
                        execute(null, null);
                    }
                    else if (currentUser.getPosition() == null)
                    {
                        currentUser.setPosition(inputText);
                        userMessage = typeOfPropositionRu;

                        ReplyKeyboardMarkup chooseYourTypeOfProposition = createMarkupButtons(
                                "Предложение \uD83D\uDE4B\uD83C\uDFFB\u200D♂️",
                                "Жалоба \uD83E\uDD26\uD83C\uDFFB\u200D♂️",
                                "Заявление \uD83D\uDC81\uD83C\uDFFB\u200D♂️"
                        );

                        execute(chooseYourTypeOfProposition, null);
                    }
                    else if (checkTypeOfPropositionRu.contains(inputText))
                    {
                        currentUser.setTypeOfDescription(inputText);
                        userMessage = enterYourPropositionRu;
                        execute(null, null);
                    }
                    else if (currentUser.getDescription() == null)
                    {
                        currentUser.setDescription(inputText);
                        userMessage = sendYourFilesRu;
                        execute(createMarkupButtons("Продолжать ➡️"), null);
                    }
                    else if(inputText.equals("Продолжать ➡️"))
                    {
                        userMessage = pressShareContactButtonRu;
                        execute
                                (
                                        shareYourContact("Поделиться Номером телефона \uD83D\uDCF1"),
                                        null
                                );
                    }
                }
                else
                {
                    if (currentUser.getFullName() == null)
                    {
                        currentUser.setFullName(inputText);      // set full name to user
                        userMessage = whenIsYourBirthDateKrill;
                        execute(null, null);
                    }
                    else if (currentUser.getBirthDate() == null)
                    {
                        CheckBirthDate trueBirthdate = isTrueBirthdateKrill(inputText);
                        userMessage = trueBirthdate.getResult();
                        if (trueBirthdate.isSuccess())
                        {
                            currentUser.setBirthDate(trueBirthdate.getResultBirthDate());     // set birth date to user
                            execute(createMarkupButtons(provinceKrill), null);   // call helper method
                        }
                        else
                            execute(null, null);
                    }
                    else if (provinceKrill.toString().contains(inputText))
                    {
                        // set province to user's province field
                        UzbBase.longUserHashMap.get(userChatId).setProvince(inputText);
                        // add all regions from hashmap's value to regions array list
                        ArrayList<String> regionsKrill = new ArrayList<>();
                        Collections.addAll(regionsKrill, KrillBase.provinceAndRegionsKrill().get(inputText));

                        // bu yerda userga qanday javob qaytarishni aniqlaganman
                        userMessage = checkResponseKrill(inputText);
                        execute(createMarkupButtons(regionsKrill), null);    // called helper method
                    }
                    else if (isRegion(inputText, currentUser, KrillBase.provinceAndRegionsKrill()))
                    {
                        UzbBase.longUserHashMap.get(userChatId).setRegion(inputText);
                        // tumanlar ro'yxati ko'rinmaydigan qilindi
                        userMessage = whereAreYouWorkKrill;
                        execute(null, null);
                    }
                    else if (currentUser.getJobName() == null)
                    {
                        currentUser.setJobName(inputText);
                        userMessage = whatIsYourPositionKrill;
                        execute(null, null);
                    }
                    else if (currentUser.getPosition() == null)
                    {
                        currentUser.setPosition(inputText);
                        userMessage = typeOfPropositionKrill;

                        ReplyKeyboardMarkup chooseYourTypeOfProposition = createMarkupButtons(
                                "Таклиф \uD83D\uDE4B\uD83C\uDFFB\u200D♂️",
                                "Муаммо \uD83E\uDD26\uD83C\uDFFB\u200D♂️",
                                "Ариза \uD83D\uDC81\uD83C\uDFFB\u200D♂️"
                        );
                        execute(chooseYourTypeOfProposition, null);
                    }
                    else if (checkTypeOfPropositionKrill.contains(inputText))
                    {
                        currentUser.setTypeOfDescription(inputText);
                        userMessage = enterYourPropositionKrill;
                        execute(null, null);
                    }
                    else if (currentUser.getDescription() == null)
                    {
                        currentUser.setDescription(inputText);
                        userMessage = sendYourFilesKrill;
                        execute(createMarkupButtons("Давом этиш ➡️"), null);
                    }
                    else if (inputText.equals("Давом этиш ➡️"))
                    {
                        userMessage = pressShareContactButtonKrill;
                        execute
                                (
                                        shareYourContact("Телефон рақамини юбориш \uD83D\uDCF1"),
                                        null
                                );
                    }
                }
                if (currentUser.getDescription() != null)
                    setFiles(update, currentUser);
            }
            else
            {
                String phoneNumber = update.getMessage().getContact().getPhoneNumber();
                if (!phoneNumber.startsWith("+"))
                    phoneNumber = "+" + phoneNumber;
                currentUser.setPhoneNumber(phoneNumber);
                String backToMainMenu;

                if (isUzbek(currentUser.getLanguage())) {
                    userMessage = last;
                    backToMainMenu = "Yangi murojaat yuborish!";
                }
                else if (isRussian(currentUser.getLanguage())) {
                    userMessage = lastRu;
                    backToMainMenu = "Отправить новый обращаться!";
                }
                else {
                    userMessage = lastKrill;
                    backToMainMenu = "Янги мурожаат юбориш!";
                }
                execute
                        (
                                createMarkupButtons(backToMainMenu),
                                null
                        );


                long deleteUser = userChatId;
//                userChatId = ;
                userMessage = String.valueOf(currentUser);

                // send user's info to admin' telegram account
                execute(null, null);
                sendFiles(currentUser, userChatId);

                // send user's info to admin' gmail account
                sendToGmail(userMessage);

                UzbBase.longUserHashMap.remove(deleteUser);
            }
        }
    }

    private void execute(
            ReplyKeyboardMarkup replyKeyboardMarkup,
            InlineKeyboardMarkup inlineKeyboardMarkup
    )
    {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(this.userChatId);
        sendMessage.setText(this.userMessage).enableHtml(true);

        if (replyKeyboardMarkup != null)
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
        else if (inlineKeyboardMarkup != null)
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void setFiles(Update update, User currentUser){

        String nextAction = "";

        if (isUzbek(currentUser.getLanguage())) {
            userMessage = "Qo'shimcha fayllar bo'lsa ularni yuklang, yoki davom etish tugmasini bosing!";
            nextAction = "Davom etish ➡️";
        }
        else if (isRussian(currentUser.getLanguage())) {
            userMessage = "Если имеете дополнительные файлы, загрузите их или нажмите на кнопку продолжить!";
            nextAction = "Продолжать ➡️";
        }
        else if (isKrill(currentUser.getLanguage())) {
            userMessage = "Қўшимча файллар бўлса уларни юкланг, ёки давом этиш тугмасини босинг!";
            nextAction = "Давом этиш ➡️";
        }

        ReplyKeyboardMarkup markupButtons = createMarkupButtons(nextAction);

        if (update.getMessage().hasDocument()) {
            currentUser.setDocument(update.getMessage().getDocument());
            execute(markupButtons, null);
        }
        else if (update.getMessage().hasPhoto()) {
            List<PhotoSize> photo = update.getMessage().getPhoto();
            currentUser.setPhotoSize(photo);
            execute(markupButtons, null);
        }
        else if (update.getMessage().hasAudio()) {
            currentUser.setAudio(update.getMessage().getAudio());
            execute(markupButtons, null);
        }
        else if (update.getMessage().hasVideo()) {
            currentUser.setVideo(update.getMessage().getVideo());
            execute(markupButtons, null);
        }
        else if (update.getMessage().hasVoice()) {
            currentUser.setVoice(update.getMessage().getVoice());
            execute(markupButtons, null);
        }
    }

    private void sendFiles(User currentUser, long userChatId) throws TelegramApiException
    {
        if (currentUser.getDocument() != null) {
            SendDocument document = new SendDocument().setChatId(userChatId)
                    .setDocument(currentUser.getDocument().getFileId());
            execute(document);
        }
        if (currentUser.getPhotoSize() != null) {

            List<PhotoSize> photoSizes = new ArrayList<>(currentUser.getPhotoSize());

            // Know file_id
            String f_id = photoSizes.stream().findFirst().get().getFileId();

            SendPhoto msg = new SendPhoto()
                    .setChatId(userChatId)
                    .setPhoto(f_id);

            execute(msg);
        }
        if (currentUser.getAudio() != null) {
            SendAudio sendAudio = new SendAudio().setChatId(userChatId).setAudio(currentUser.getAudio().getFileId());
            execute(sendAudio);
        }
        if (currentUser.getVideo() != null) {
            SendVideo sendVideo = new SendVideo().setChatId(userChatId).setVideo(currentUser.getVideo().getFileId());
            execute(sendVideo);
        }
        if (currentUser.getVoice() != null) {
            SendVoice sendVoice = new SendVoice().setChatId(userChatId).setVoice(currentUser.getVoice().getFileId());
            execute(sendVoice);
        }
    }
}