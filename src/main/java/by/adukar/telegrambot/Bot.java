package by.adukar.telegrambot;

import by.adukar.telegrambot.buttons.inline.InlineButtons;
import by.adukar.telegrambot.buttons.reply.ReplyButtons;
import by.adukar.telegrambot.consts.*;
import by.adukar.telegrambot.enums.Color;
import by.adukar.telegrambot.service.FileService;
import by.adukar.telegrambot.service.TextService;
import by.adukar.telegrambot.service.UserService;
import lombok.SneakyThrows;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.methods.send.*;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.List;
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
    public void sendAnswerFromBot(Update update) {


        if (update.hasCallbackQuery()) {
            Long chatIdFromCallBack = update.getCallbackQuery().getFrom().getId().longValue();

            if (update.getCallbackQuery().getData().equals("английский")) {
                sendMsgWithButtons("Ты выбрал английский язык. Теперь выбери тему:", inlineButtons.keyboardMarkup("еда", "мебель", "одежда", "цвета"), chatIdFromCallBack);
            }
            if (update.getCallbackQuery().getData().equals("немецкий")) {
                sendMsgWithButtons("Ты выбрал немецкий язык. Теперь выбери тему:", inlineButtons.keyboardMarkup("еда", "мебель", "одежда", "цвета"), chatIdFromCallBack);
            }
            if (update.getCallbackQuery().getData().equals("французский")) {
                sendMsgWithButtons("Ты выбрал французский язык. Теперь выбери тему:", inlineButtons.keyboardMarkup("еда", "мебель", "одежда", "цвета"), chatIdFromCallBack);
            }

            if (update.getCallbackQuery().getData().equals("еда")) {
                sendMsg("Ты выбрал тему еда. Пример ввода: e_eng(fre)(ger)_ВашеСлово", chatIdFromCallBack);
            }
            if (update.getCallbackQuery().getData().equals("мебель")) {
                sendMsg("Ты выбрал тему мебель. Пример ввода: m_eng(fre)(ger)_ВашеСлово", chatIdFromCallBack);
            }
            if (update.getCallbackQuery().getData().equals("одежда")) {
                sendMsg("Ты выбрал тему одежда. Пример ввода: cl_eng(fre)(ger)_ВашеСлово", chatIdFromCallBack);
            }
            if (update.getCallbackQuery().getData().equals("цвета")) {
                sendMsg("Ты выбрал тему цвета. Пример ввода: co_eng(fre)(ger)_ВашеСлово", chatIdFromCallBack);
            }


        } else {
            Long chatId = update.getMessage().getChatId();
            if (update.getMessage().getText().equals("Голосование")) {
                sendPoll(chatId);
            } else {
                if (update.getMessage().getText().equals("Проверка знаний")) {
                    sendPoll1(chatId);
                    sendPoll2(chatId);
                    sendPoll3(chatId);
                } else {
                    if (update.getMessage().getText().equals("Еще")) {
                        sendPoll4(chatId);
                        sendPoll5(chatId);
                        sendPoll6(chatId);
                    }
                }

                if (update.getMessage().getText().equals("/start")) {
                    sendMsg("Привет! Я бот - учитель языков. Я еще умею /location и /телефон создателя, /joke /милаякартинка1 /милаякартинка2 /милаякартинка3", chatId);
                    //sendMsgWithButtons("внизу есть кнопки", replyButtons.keyboardMarkupForSelectStudentOrTeacher(), chatId);
                    sendMsgWithButtons("выбери язык, который хочешь учить:", inlineButtons.keyboardMarkup("английский", "немецкий", "французский"), chatId);
                }
                if (update.getMessage().getText().equals("Брось кубик")) {
                    sendDise(chatId);
                }
                if (update.getMessage().getText().startsWith("e_eng")) {
                    String word = update.getMessage().getText().substring(6);
                    if (Dictionary.dictionaryEnglishEat.containsKey(word)) {
                        sendMsg("Перевод найден(англиский) - " + Dictionary.dictionaryEnglishEat.get(word), chatId);
                    } else {
                        sendMsg("Перевод не найден", chatId);
                    }
                }
                if (update.getMessage().getText().startsWith("e_fre")) {
                    String word = update.getMessage().getText().substring(6);
                    if (Dictionary.dictionaryFrenchEat.containsKey(word)) {
                        sendMsg("Перевод найден(французкий) - " + Dictionary.dictionaryFrenchEat.get(word), chatId);
                    } else {
                        sendMsg("Перевод не найден", chatId);
                    }
                }
                if (update.getMessage().getText().startsWith("e_ger")) {
                    String word = update.getMessage().getText().substring(6);
                    if (Dictionary.dictionaryGermanEat.containsKey(word)) {
                        sendMsg("Перевод найден(немецкий) - " + Dictionary.dictionaryGermanEat.get(word), chatId);
                    } else {
                        sendMsg("Перевод не найден", chatId);
                    }
                }

                if (update.getMessage().getText().startsWith("m_eng")) {
                    String word = update.getMessage().getText().substring(6);
                    if (Dictionary.dictionaryEnglishFurniture.containsKey(word)) {
                        sendMsg("Перевод найден(англиский) - " + Dictionary.dictionaryEnglishFurniture.get(word), chatId);
                    } else {
                        sendMsg("Перевод не найден", chatId);
                    }
                }
                if (update.getMessage().getText().startsWith("m_fre")) {
                    String word = update.getMessage().getText().substring(6);
                    if (Dictionary.dictionaryFrenchFurniture.containsKey(word)) {
                        sendMsg("Перевод найден(французкий) - " + Dictionary.dictionaryFrenchFurniture.get(word), chatId);
                    } else {
                        sendMsg("Перевод не найден", chatId);
                    }
                }
                if (update.getMessage().getText().startsWith("m_ger")) {
                    String word = update.getMessage().getText().substring(6);
                    if (Dictionary.dictionaryGermanFurniture.containsKey(word)) {
                        sendMsg("Перевод найден(немецкий) - " + Dictionary.dictionaryGermanFurniture.get(word), chatId);
                    } else {
                        sendMsg("Перевод не найден", chatId);
                    }
                }

                if (update.getMessage().getText().startsWith("cl_eng")) {
                    String word = update.getMessage().getText().substring(7);
                    if (Dictionary.dictionaryEnglishСlothing.containsKey(word)) {
                        sendMsg("Перевод найден(англиский) - " + Dictionary.dictionaryEnglishСlothing.get(word), chatId);
                    } else {
                        sendMsg("Перевод не найден", chatId);
                    }
                }
                if (update.getMessage().getText().startsWith("cl_fre")) {
                    String word = update.getMessage().getText().substring(7);
                    if (Dictionary.dictionaryFrenchСlothing.containsKey(word)) {
                        sendMsg("Перевод найден(французкий) - " + Dictionary.dictionaryFrenchСlothing.get(word), chatId);
                    } else {
                        sendMsg("Перевод не найден", chatId);
                    }
                }
                if (update.getMessage().getText().startsWith("cl_ger")) {
                    String word = update.getMessage().getText().substring(7);
                    if (Dictionary.dictionaryGermanСlothing.containsKey(word)) {
                        sendMsg("Перевод найден(немецкий) - " + Dictionary.dictionaryGermanСlothing.get(word), chatId);
                    } else {
                        sendMsg("Перевод не найден", chatId);
                    }
                }

                if (update.getMessage().getText().startsWith("co_eng")) {
                    String word = update.getMessage().getText().substring(7);
                    if (Dictionary.dictionaryEnglishColors.containsKey(word)) {
                        sendMsg("Перевод найден(англиский) - " + Dictionary.dictionaryEnglishColors.get(word), chatId);
                    } else {
                        sendMsg("Перевод не найден", chatId);
                    }
                }
                if (update.getMessage().getText().startsWith("co_fre")) {
                    String word = update.getMessage().getText().substring(7);
                    if (Dictionary.dictionaryFrenchColors.containsKey(word)) {
                        sendMsg("Перевод найден(французкий) - " + Dictionary.dictionaryFrenchColors.get(word), chatId);
                    } else {
                        sendMsg("Перевод не найден", chatId);
                    }
                }
                if (update.getMessage().getText().startsWith("co_ger")) {
                    String word = update.getMessage().getText().substring(7);
                    if (Dictionary.dictionaryGermanColors.containsKey(word)) {
                        sendMsg("Перевод найден(немецкий) - " + Dictionary.dictionaryGermanColors.get(word), chatId);
                    } else {
                        sendMsg("Перевод не найден", chatId);
                    }
                }


                if (update.getMessage().getText().equals("/location")) {
                    sendLocation(chatId);
                }
                if (update.getMessage().getText().equals("/joke")) {
                    sendMsg("I don't know jokes yet, but I will improve :)", chatId);
                }
                if (update.getMessage().getText().equals("/милаякартинка1")) {
                    sendPhoto("https://мтв.онлайн/files/1/2019/30620-119720-30365-hsa705.shej.jpg", chatId);
                }
                if (update.getMessage().getText().equals("/милаякартинка2")) {
                    sendPhoto("https://cdn.ren.tv/cache/960x540/media/img/07/a8/07a827ebba12828c211675d79fe991c6756a3924.jpg", chatId);
                }
                if (update.getMessage().getText().equals("/милаякартинка3")) {
                    sendPhoto("https://telegraf.com.ua/files/2017/04/00000000000000000000000003.jpg", chatId);
                }
                if (update.getMessage().getText().equals("/телефон")) {
                    sendContact(chatId);
                }
                if (update.getMessage().getText().equals("/помощь")) {
                    sendMsg("ничем не могу помочь", chatId);
                }
            }
        }
    }

    private void sendPhoto(String s, Long chatId) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setPhoto(s);
        sendPhoto.setChatId(chatId);
        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /*private void sendSticker(Long chatId){
        SendSticker sendSticker = new SendSticker();
        sendSticker.setSticker(stickerId);
        return sendSticker;
    }*/

    @SneakyThrows
    public synchronized void sendPoll(Long chatId) {
        SendPoll sendPoll = new SendPoll();
        sendPoll.enableNotification();
        sendPoll.setQuestion("синий кит это...");
        sendPoll.setAnonymous(true);
        sendPoll.setOptions(List.of("самое большое насекомое", "самое большое млекопитающее", "самая большая рыба"));
        sendPoll.setChatId(chatId);
        sendPoll.setType("quiz");
        sendPoll.setCorrectOptionId(0);
        execute(sendPoll);
    }

    @SneakyThrows
    public synchronized void sendPoll1(Long chatId) {
        SendPoll sendPoll = new SendPoll();
        sendPoll.enableNotification();
        sendPoll.setQuestion("красный по английски...");
        sendPoll.setAnonymous(true);
        sendPoll.setOptions(List.of("Schrank", "Blau", "red"));
        sendPoll.setChatId(chatId);
        sendPoll.setType("quiz");
        sendPoll.setCorrectOptionId(2);
        execute(sendPoll);
    }

    @SneakyThrows
    public synchronized void sendPoll2(Long chatId) {
        SendPoll sendPoll = new SendPoll();
        sendPoll.enableNotification();
        sendPoll.setQuestion("конфета по немецки...");
        sendPoll.setAnonymous(true);
        sendPoll.setOptions(List.of("veste", "Des bonbons", "Süßigkeiten"));
        sendPoll.setChatId(chatId);
        sendPoll.setType("quiz");
        sendPoll.setCorrectOptionId(2);
        execute(sendPoll);
    }

    @SneakyThrows
    public synchronized void sendPoll3(Long chatId) {
        SendPoll sendPoll = new SendPoll();
        sendPoll.enableNotification();
        sendPoll.setQuestion("носки по французски...");
        sendPoll.setAnonymous(true);
        sendPoll.setOptions(List.of("Gelb", "Apfel", "chaussettes"));
        sendPoll.setChatId(chatId);
        sendPoll.setType("quiz");
        sendPoll.setCorrectOptionId(2);
        execute(sendPoll);
    }

    @SneakyThrows
    public synchronized void sendPoll4(Long chatId) {
        SendPoll sendPoll = new SendPoll();
        sendPoll.enableNotification();
        sendPoll.setQuestion("яблоко по английски...");
        sendPoll.setAnonymous(true);
        sendPoll.setOptions(List.of("Gelb", "Apfel", "аpple"));
        sendPoll.setChatId(chatId);
        sendPoll.setType("quiz");
        sendPoll.setCorrectOptionId(2);
        execute(sendPoll);
    }

    @SneakyThrows
    public synchronized void sendPoll5(Long chatId) {
        SendPoll sendPoll = new SendPoll();
        sendPoll.enableNotification();
        sendPoll.setQuestion("куртка по немецки...");
        sendPoll.setAnonymous(true);
        sendPoll.setOptions(List.of("yellow", "Apfel", "Jacke"));
        sendPoll.setChatId(chatId);
        sendPoll.setType("quiz");
        sendPoll.setCorrectOptionId(2);
        execute(sendPoll);
    }

    @SneakyThrows
    public synchronized void sendPoll6(Long chatId) {
        SendPoll sendPoll = new SendPoll();
        sendPoll.enableNotification();
        sendPoll.setQuestion("шкаф по французски...");
        sendPoll.setAnonymous(true);
        sendPoll.setOptions(List.of("Tisch", "Socken", "armoire"));
        sendPoll.setChatId(chatId);
        sendPoll.setType("quiz");
        sendPoll.setCorrectOptionId(2);
        execute(sendPoll);
    }




    @SneakyThrows
    public synchronized void sendDise(Long chatId) {
        SendDice sendDise = new SendDice();
        sendDise.setChatId(chatId);
        try {
            execute(sendDise);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }     //кубик


    public synchronized void sendMsg(String message, Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println("Exception: " + e.toString());
        }
    }

    @SneakyThrows
    public synchronized void sendContact(Long chatId) {
        SendContact sendContact = new SendContact();
        sendContact.setPhoneNumber("+375336548560");
        sendContact.setFirstName("Lada");
        sendContact.setLastName("Kirienko");
        sendContact.setChatId(chatId);

        execute(sendContact);

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

    @SneakyThrows
    public synchronized void sendLocation(Long chatId) {
        SendLocation sendLocation = new SendLocation();
        sendLocation.setChatId(chatId);
        sendLocation.setLatitude(Float.valueOf("53.862942"));
        sendLocation.setLongitude(Float.valueOf("27.430991"));
        execute(sendLocation);
    }


    /*public synchronized void sendMsgWithButtons(String message, ReplyKeyboardMarkup replyKeyboardMarkup, Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println("Exception: " + e.toString());
        }
    }*/

    public synchronized void sendMsgWithButtons(String message, InlineKeyboardMarkup replyKeyboardMarkup, Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println("Exception: " + e.toString());
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