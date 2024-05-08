package com.javarush.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

import static com.javarush.telegrambot.TelegramBotContent.*;

public class MyFirstTelegramBot extends MultiSessionTelegramBot {
    public static final String NAME = "*************_bot"; // TODO: добавьте имя бота в кавычках
    public static final String TOKEN = "*******:**************"; //TODO: добавьте токен бота в кавычках

    public MyFirstTelegramBot() {
        super(NAME, TOKEN);
    }

    @Override
    public void onUpdateEventReceived(Update updateEvent) {

        // отоброзить сообщение о начале игры - нужно взломать холодильник
        if (getMessageText().equals("/start")) {

            setUserGlory(0);
            sendMessage("step_1_pic",
                    STEP_1_TEXT,
                    Map.of("Взлом холодильника", "step_1_btn"));
//            sendPhotoMessageAsync("step_1_pic");
//            sendTextMessageAsync(STEP_1_TEXT,
//                    Map.of("Взлом холодильника", "step_1_btn"));
        }
        if (getCallbackQueryButtonKey().equals("step_1_btn")) {
            addUserGlory(20);
            sendMessage("step_2_pic",
                    STEP_2_TEXT,
                    Map.of("Взять сосиску! +20 славы", "step_2_btn",
                            "Взять рыбку! +20 славы", "step_2_btn",
                            "Сбросить банку з огурцами! +20 славы", "step_2_btn"));
//            sendPhotoMessageAsync("step_2_pic");
//            sendTextMessageAsync(STEP_2_TEXT,
//                    Map.of("Взять сосиску! +20 славы", "step_2_btn",
//                            "Взять рыбку! +20 славы", "step_2_btn",
//                            "Сбросить банку з огурцами! +20 славы", "step_2_btn"));
        }

        // взломаем робот-пылесос
        if (getCallbackQueryButtonKey().equals("step_2_btn")) {
            addUserGlory(20);
            sendMessage("step_3_pic",
                    STEP_3_TEXT, Map.of("Взлом робота пылесоса", "step_3_btn"));
//            sendPhotoMessageAsync("step_3_pic");
//            sendTextMessageAsync(STEP_3_TEXT, Map.of("Взлом робота пылесоса", "step_3_btn"));
        }
        if (getCallbackQueryButtonKey().equals("step_3_btn")) {
            addUserGlory(30);
            sendMessage("step_4_pic",
                    STEP_4_TEXT,
                    Map.of("Отправить робопылесос за едой! +30 славы", "step_4_btn",
                            "Проехаться на робопылесосе! +30 славы", "step_4_btn",
                            "Убегать от робопылесоса! +30 славы", "step_4_btn"));
//            sendPhotoMessageAsync("step_4_pic");
//            sendTextMessageAsync(STEP_4_TEXT,
//                    Map.of("Отправить робопылесос за едой! +30 славы", "step_4_btn",
//                            "Проехаться на робопылесосе! +30 славы", "step_4_btn",
//                            "Убегать от робопылесоса! +30 славы", "step_4_btn"));
        }

        // взламываем камеру Go-Pro
        if (getCallbackQueryButtonKey().equals("step_4_btn")) {
            addUserGlory(30);
            sendMessage("step_5_pic",
                    STEP_5_TEXT, Map.of("Надеть и включить GoPro!", "step_5_btn"));
//            sendPhotoMessageAsync("step_5_pic");
//            sendTextMessageAsync(STEP_5_TEXT, Map.of("Надеть и включить GoPro!", "step_5_btn"));
        }
        if (getCallbackQueryButtonKey().equals("step_5_btn")) {
            addUserGlory(40);
            sendMessage("step_6_pic",
                    STEP_6_TEXT,
                    Map.of("Бегать по крышам, снимать на GoPro! +40 славы", "step_6_btn",
                            "С GoPro нападать на других котов из засады! +40 славы", "step_6_btn",
                            "С GoPro нападать на собак из засады! +40 славы", "step_6_btn"));
//            sendPhotoMessageAsync("step_6_pic");
//            sendTextMessageAsync(STEP_6_TEXT,
//                    Map.of("Бегать по крышам, снимать на GoPro! +40 славы", "step_6_btn",
//                            "С GoPro нападать на других котов из засады! +40 славы", "step_6_btn",
//                            "С GoPro нападать на собак из засады! +40 славы", "step_6_btn"));
        }

        // вламываем компьютер
        if (getCallbackQueryButtonKey().equals("step_6_btn")) {
            addUserGlory(40);
            sendMessage("step_7_pic",
                    STEP_7_TEXT, Map.of("Взлом пароля", "step_7_btn"));
//            sendPhotoMessageAsync("step_7_pic");
//            sendTextMessageAsync(STEP_7_TEXT, Map.of("Взлом пароля", "step_7_btn"));
        }

        // хвастаемся дворовым котам
        if (getCallbackQueryButtonKey().equals("step_7_btn")) {
            addUserGlory(50);
            sendMessage("step_8_pic",
                    STEP_8_TEXT, Map.of("Выйти во двор", "step_8_btn"));
//            sendPhotoMessageAsync("step_8_pic");
//            sendTextMessageAsync(STEP_8_TEXT, Map.of("Выйти во двор", "step_8_btn"));
        }
        if (getCallbackQueryButtonKey().equals("step_8_btn")) {
            sendImageMessageAsync("C:\\project\\JavaRush\\TelegramBot\\src\\main\\resources\\images\\final_pic.jpg");
//            sendPhotoMessageAsync("final_pic");
            sendTextMessageAsync(FINAL_TEXT);
        }

        if (getMessageText().equals("/glory")) {
            sendTextMessageAsync("Накоплено: " + getUserGlory() + " славы");
        }
    }

    private void sendMessage(String photoKey, String text, Map<String, String> buttons) {
        sendPhotoMessageAsync(photoKey);
        sendTextMessageAsync(text, buttons);
    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}

/*
 if (getMessageText().equals("/bye")) {
            sendTextMessageAsync("Asta la vista, baby!");
            sendTextMessageAsync("Ваше любимое животное? ",
                    Map.of("Кот", "cat", "Собака", "dog"));
        }
        if (getCallbackQueryButtonKey().equals("cat")) {
            sendPhotoMessageAsync("step_4_pic");
        }

        if (getCallbackQueryButtonKey().equals("dog")) {
            sendPhotoMessageAsync("step_6_pic");
        }


        if (getMessageText().equals("/start")) {
            sendTextMessageAsync("Привет!");
        }

        if (getMessageText().contains("бомба")) {
            sendTextMessageAsync("Опасность!");
        }

        if (getMessageText().contains("картинка")) {
            sendPhotoMessageAsync("step_8_pic");
        }

        if (getMessageText().contains("кот")) {
            sendTextMessageAsync("выбери номер кота: ",
                    Map.of("Текст-на-кнопке1", "команда1", "Текст-на-кнопке2", "команда2"));
        }

        if (getCallbackQueryButtonKey().equals("команда1")) {
            sendPhotoMessageAsync("step_1_pic");
        }

        if (getCallbackQueryButtonKey().equals("команда2")) {
            sendPhotoMessageAsync("step_2_pic");
        }

        if (getMessageText().equals("smile")) {
            var message = getLastSentMessage();
            editTextMessageAsync(message.getMessageId(), message.getText() + " :) ");
        }
*/