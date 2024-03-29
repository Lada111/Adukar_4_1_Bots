package by.adukar.telegrambot.buttons.reply;

import by.adukar.telegrambot.consts.Paths;
import by.adukar.telegrambot.service.TextService;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.IOException;
import java.util.ArrayList;

public class ReplyButtons {

    TextService textService = new TextService();

    /*public ReplyKeyboardMarkup keyboardMarkupForSelectStudentOrTeacher() {

        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

            keyboardFirstRow.add("телефон");
            keyboardFirstRow.add("помощь");

            keyboardSecondRow.add("cat1");
            keyboardSecondRow.add("cat2");
            keyboardSecondRow.add("cat3");

            keyboard.add(keyboardFirstRow);
            keyboard.add(keyboardSecondRow);

        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;

    }*/

    public ReplyKeyboardMarkup keyboardMarkupForSelectTheme() {

        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        keyboardFirstRow.add("еда");
        keyboardFirstRow.add("мебель");

        keyboardSecondRow.add("одежда");
        keyboardSecondRow.add("цвета");

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);

        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;

    }
}
