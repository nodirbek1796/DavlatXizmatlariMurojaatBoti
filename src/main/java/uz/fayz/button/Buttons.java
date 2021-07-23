package uz.fayz.button;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class Buttons {

    public static InlineKeyboardMarkup sendInlineKeyBoardMessage() {
        return helper("Kanalga a'zo bo'lish \uD83E\uDD14!", "A'zo bo'lganman \uD83D\uDE0A!");
    }

    public static InlineKeyboardMarkup sendInlineKeyBoardMessageRu() {
        return helper("Теперь подпишусь \uD83E\uDD14!", "Подписался \uD83D\uDE0A!");
    }

    public static InlineKeyboardMarkup sendInlineKeyBoardMessageKrill() {
        return helper("Каналга аьзо бўлиш \uD83E\uDD14!", "Аьзо бўлганман \uD83D\uDE0A!");
    }

    private static InlineKeyboardMarkup helper(String firstButton, String secondButton){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();

        inlineKeyboardButton1.setText(firstButton);
        inlineKeyboardButton1.setUrl("https://t.me/argos_uz");

        inlineKeyboardButton2.setText(secondButton);
        return getInlineKeyboardMarkup(inlineKeyboardMarkup, inlineKeyboardButton1, inlineKeyboardButton2);
    }

    private static InlineKeyboardMarkup getInlineKeyboardMarkup
            (
                    InlineKeyboardMarkup inlineKeyboardMarkup,
                    InlineKeyboardButton inlineKeyboardButton1,
                    InlineKeyboardButton inlineKeyboardButton2
            )
    {
        inlineKeyboardButton2.setCallbackData("Everything is okay with me!");

        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        keyboardButtonsRow.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow);
        keyboardButtonsRow = new ArrayList<>();
        keyboardButtonsRow.add(inlineKeyboardButton2);
        rowList.add(keyboardButtonsRow);

        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }
}
