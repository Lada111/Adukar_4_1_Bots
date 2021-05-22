package by.adukar.telegrambot.buttons.inline;

import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class InlineButtons {

    @SneakyThrows
    public InlineKeyboardMarkup keyboardMarkup(String text1, String text2, String text3) {
        List<List<InlineKeyboardButton>> listKeyboard = new ArrayList<>();
        List<InlineKeyboardButton> buttonsList = new ArrayList<>();

        InlineKeyboardButton keyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton keyboardButton2 = new InlineKeyboardButton();
        InlineKeyboardButton keyboardButton3 = new InlineKeyboardButton();


        keyboardButton1.setCallbackData(text1);
        keyboardButton1.setText(text1);

        keyboardButton2.setCallbackData(text2);
        keyboardButton2.setText(text2);

        keyboardButton3.setCallbackData(text3);
        keyboardButton3.setText(text3);



        buttonsList.add(keyboardButton1);
        buttonsList.add(keyboardButton2);
        buttonsList.add(keyboardButton3);
        listKeyboard.add(buttonsList);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(listKeyboard);
        return inlineKeyboardMarkup;
    }

    @SneakyThrows
    public InlineKeyboardMarkup keyboardMarkup(String text1, String text2, String text3, String text4) {
        List<List<InlineKeyboardButton>> listKeyboard = new ArrayList<>();
        List<InlineKeyboardButton> buttonsList = new ArrayList<>();

        InlineKeyboardButton keyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton keyboardButton2 = new InlineKeyboardButton();
        InlineKeyboardButton keyboardButton3 = new InlineKeyboardButton();
        InlineKeyboardButton keyboardButton4 = new InlineKeyboardButton();


        keyboardButton1.setCallbackData(text1);
        keyboardButton1.setText(text1);

        keyboardButton2.setCallbackData(text2);
        keyboardButton2.setText(text2);

        keyboardButton3.setCallbackData(text3);
        keyboardButton3.setText(text3);


        keyboardButton4.setCallbackData(text4);
        keyboardButton4.setText(text4);



        buttonsList.add(keyboardButton1);
        buttonsList.add(keyboardButton2);
        buttonsList.add(keyboardButton3);
        buttonsList.add(keyboardButton4);
        listKeyboard.add(buttonsList);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(listKeyboard);
        return inlineKeyboardMarkup;
    }
}