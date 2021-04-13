package by.adukar.telegrambot;

import by.adukar.telegrambot.buttons.reply.ReplyButtons;
import by.adukar.telegrambot.consts.Paths;
import by.adukar.telegrambot.consts.Photos;
import by.adukar.telegrambot.consts.Text;
import by.adukar.telegrambot.enums.Color;
import by.adukar.telegrambot.service.TextService;
import by.adukar.telegrambot.service.UserService;
import lombok.SneakyThrows;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;


public class Bot extends TelegramLongPollingBot {

    /**
     * Метод для приема сообщений.
     * @param update Содержит сообщение от пользователя.
     */
    ReplyButtons replyButtons = new ReplyButtons();

    UserService userService = new UserService();
    TextService textService = new TextService();

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        if(update.getMessage().getText().equals("/start")){
            userService.addUserToList(userService.createUserFromUpdate(update));
            sendMsg(textService.getPropValues(Paths.HELLO_STRING_PATH, Text.SAY_HELLO_PROPERTY), update.getMessage().getChatId());
            sendPhoto(textService.getPropValues(Paths.PHOTOS_URLS_PATH, Photos.HELLO_PHOTO_PATH), update.getMessage().getChatId());
        }
        if(update.getMessage().getText().equals("Кнопки")){
            sendMsgWithButtons("Сделайте выбор:", replyButtons.keyboardMarkupForSelectStudentOrTeacher(), update.getMessage().getChatId());
        }
        if(update.getMessage().getText().equals("Пользователи")){
            sendMsg(userService.getAllUser(), update.getMessage().getChatId());
        }
        if(update.getMessage().getText().equals("Цвета")){
            sendMsg(Color.RED.getCode(), update.getMessage().getChatId());
        }
    }


    public synchronized void sendMsg(String message, Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println( "Exception: " + e.toString());
        }
    }

    public synchronized void sendPhoto(String pathToPhoto, Long chatId) {
        SendPhoto sendPhotoRequest = new SendPhoto();
        sendPhotoRequest.setChatId(chatId);
        sendPhotoRequest.setPhoto(pathToPhoto);
        try {
            sendPhoto(sendPhotoRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public synchronized void sendMsgWithButtons(String message, ReplyKeyboardMarkup replyKeyboardMarkup, Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println( "Exception: " + e.toString());
        }
    }

    /**
     * Метод возвращает имя бота, указанное при регистрации.
     * @return имя бота
     */
    @Override
    public String getBotUsername() {
        return "adukar4_1bot";
    }

    /**
     * Метод возвращает token бота для связи с сервером Telegram
     * @return token для бота
     */
    @Override
    public String getBotToken() {
        return "1741890763:AAEiZmqGQOPicqDbZrkDD_V26rRHI35Ik4k";
    }
}