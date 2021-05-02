package by.adukar.telegrambot;

import by.adukar.telegrambot.buttons.reply.ReplyButtons;
import by.adukar.telegrambot.consts.Commands;
import by.adukar.telegrambot.consts.Paths;
import by.adukar.telegrambot.consts.Photos;
import by.adukar.telegrambot.consts.Text;
import by.adukar.telegrambot.enums.Color;
import by.adukar.telegrambot.service.FileService;
import by.adukar.telegrambot.service.TextService;
import by.adukar.telegrambot.service.UserService;
import lombok.SneakyThrows;
import org.telegram.telegrambots.api.methods.send.*;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;


public class Bot extends TelegramLongPollingBot {

    ReplyButtons replyButtons = new ReplyButtons();

    UserService userService = new UserService();
    TextService textService = new TextService();
    FileService fileService = new FileService();

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        sendAnswerFromBot(update);
    }

    @SneakyThrows
    public void sendAnswerFromBot(Update update){
        Long chatId = update.getMessage().getChatId();
        if(update.getMessage().getText().equals("/start")){
            sendMsg("Hi! I am very good bot. I can: /location /joke and I am very like cats", chatId);
            sendMsgWithButtons("внизу есть кнопки", replyButtons.keyboardMarkupForSelectStudentOrTeacher(), chatId);

        }
        if(update.getMessage().getText().equals("/location")){
            sendLocation(chatId);
        }
        if(update.getMessage().getText().equals("/joke")){
            sendMsg("I don't know jokes yet, but I will improve :)", chatId);
        }
        if(update.getMessage().getText().equals("cat1")){
            sendPhoto("https://мтв.онлайн/files/1/2019/30620-119720-30365-hsa705.shej.jpg", chatId);
        }
        if(update.getMessage().getText().equals("cat2")){
            sendPhoto("https://cdn.ren.tv/cache/960x540/media/img/07/a8/07a827ebba12828c211675d79fe991c6756a3924.jpg", chatId);
        }
        if(update.getMessage().getText().equals("cat3")){
            sendPhoto("https://telegraf.com.ua/files/2017/04/00000000000000000000000003.jpg", chatId);
        }
        if(update.getMessage().getText().equals("телефон")){
            sendContact(chatId);
        }
        if(update.getMessage().getText().equals("помощь")){
            sendMsg("ничем не могу помочь", chatId);
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

    public synchronized void sendContact(Long chatId) {
        SendContact sendContact = new SendContact();
        sendContact.setPhoneNumber("+375336548560");
        sendContact.setFirstName("Lada");
        sendContact.setLastName("Kirienko");
        sendContact.setChatId(chatId);
        try {
            execute(sendContact);
        } catch (TelegramApiException e) {
            System.out.println( "Exception: " + e.toString());
        }
    }

  /*  public synchronized void sendDocument(Long chatId) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId);
        sendDocument.setDocument("http://www.africau.edu/images/default/sample.pdf");
        sendDocument.setCaption("Текст к документу");
        try {
            execute(sendDocument);
        } catch (TelegramApiException e) {
            System.out.println( "Exception: " + e.toString());
        }
    }*/

    public synchronized void sendLocation(Long chatId){
        SendLocation sendLocation = new SendLocation();
        sendLocation.setChatId(chatId);
        sendLocation.setLatitude(Float.valueOf("53.862942"));
        sendLocation.setLongitude(Float.valueOf("27.430991"));

        try {
            execute(sendLocation);
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


    @Override
    public String getBotUsername() {
        return "LadinMyBot";
    }

    @Override
    public String getBotToken() {
        return "1750025939:AAFMZgFd5d2tk9xr6boZkVS-yiKWsnkKSlU";
    }
}