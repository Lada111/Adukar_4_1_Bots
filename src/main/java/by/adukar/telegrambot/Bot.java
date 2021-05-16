package by.adukar.telegrambot;

import by.adukar.telegrambot.buttons.inline.InlineButtons;
import by.adukar.telegrambot.buttons.reply.ReplyButtons;
import by.adukar.telegrambot.consts.*;
import by.adukar.telegrambot.enums.Color;
import by.adukar.telegrambot.service.FileService;
import by.adukar.telegrambot.service.TextService;
import by.adukar.telegrambot.service.UserService;
import lombok.SneakyThrows;
import org.telegram.telegrambots.api.methods.send.*;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;


public class Bot extends TelegramLongPollingBot {

    ReplyButtons replyButtons = new ReplyButtons();

    UserService userService = new UserService();
    TextService textService = new TextService();
    FileService fileService = new FileService();
    InlineButtons inlineButtons = new InlineButtons();

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        sendAnswerFromBot(update);
    }

    @SneakyThrows
    public void sendAnswerFromBot(Update update){


        if (update.hasCallbackQuery()) {
            Long chatIdFromCallBack = update.getCallbackQuery().getFrom().getId().longValue();

            if(update.getCallbackQuery().getData().equals("английский")) {
                sendMsgWithButtons("Ты выбрал английский язык. Теперь выбери тему:", inlineButtons.keyboardMarkup("еда", "мебель", "одежда", "цвета"), chatIdFromCallBack);
            }
            if(update.getCallbackQuery().getData().equals("немецкий")) {
                sendMsgWithButtons("Ты выбрал немецкий язык. Теперь выбери тему:", inlineButtons.keyboardMarkup("еда", "мебель", "одежда", "цвета"), chatIdFromCallBack);
            }
            if(update.getCallbackQuery().getData().equals("французский")) {
                sendMsgWithButtons("Ты выбрал французский язык. Теперь выбери тему:", inlineButtons.keyboardMarkup("еда", "мебель", "одежда", "цвета"), chatIdFromCallBack);
            }
            if(update.getCallbackQuery().getData().equals("еда")) {
                sendMsg("Ты выбрал тему еда. Пример ввода: e_eng(fre)(ger)_ВашеСлово", chatIdFromCallBack);
            }
            if(update.getCallbackQuery().getData().equals("мебель")) {
                sendMsg("Ты выбрал тему мебель. Пример ввода: f_ВашеСлово", chatIdFromCallBack);
            }
            if(update.getCallbackQuery().getData().equals("одежда")) {
                sendMsg("Ты выбрал тему одежда. Пример ввода: cl_ВашеСлово", chatIdFromCallBack);
            }
            if(update.getCallbackQuery().getData().equals("цвета")) {
                sendMsg("Ты выбрал тему цвета. Пример ввода: c_ВашеСлово", chatIdFromCallBack);
            }


        }else {
            Long chatId = update.getMessage().getChatId();


            if (update.getMessage().getText().equals("/start")) {
                sendMsg("Привет! Я бот - учитель языков", chatId);
                //sendMsgWithButtons("внизу есть кнопки", replyButtons.keyboardMarkupForSelectStudentOrTeacher(), chatId);
                sendMsgWithButtons("выбери язык, который хочешь учить:", inlineButtons.keyboardMarkup("английский", "немецкий", "французский"), chatId);
            }
            if (update.getMessage().getText().startsWith("e_eng")){
                String word = update.getMessage().getText().substring(6);
                if(Dictionary.dictionaryEnglishEat.containsKey(word)) {
                    sendMsg("Перевод найден(англиский) - " + Dictionary.dictionaryEnglishEat.get(word), chatId);
                }
               else{
                    sendMsg("Перевод не найден", chatId);
                }
            }
            if (update.getMessage().getText().startsWith("e_fre")){
                String word = update.getMessage().getText().substring(6);
                if(Dictionary.dictionaryFrenchEat.containsKey(word)) {
                    sendMsg("Перевод найден(французкий) - " + Dictionary.dictionaryFrenchEat.get(word), chatId);
                }
                else{
                    sendMsg("Перевод не найден", chatId);
                }
            }

        /*
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
        }*/
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

    public synchronized void sendMsgWithButtons(String message, InlineKeyboardMarkup replyKeyboardMarkup, Long chatId) {
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