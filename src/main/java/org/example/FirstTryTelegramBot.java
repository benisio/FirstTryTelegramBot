package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class FirstTryTelegramBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "FirstPyramidBot";
    }

    @Override
    public String getBotToken() {
        return "5248823916:AAFQjsaSEcpwXg0tkJpxwSMCr6BvZERTn54";
    }

    @Override
    public void onUpdateReceived(Update update) {
        // Проверяем, содержит ли update сообщение и содержится ли в сообщении текст
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText(); // получаем текст сообщения
            SendMessage message = new SendMessage(); // Создаем объект класса SendMessage с обязательными полями
            message.setChatId(update.getMessage().getChatId().toString()); // без этой строчки бот не отправляет сообщеня юзеру
            /*message.setText("Ваш chat id: " + update.getMessage().getChatId().toString() + "\n" +
                    "От Вас получен текст: " + update.getMessage().getText());*/

            // обработка нажатий пунктов меню
            switch (text) {
                case "/start":
                    message.setText("Выберите нужный пункт меню:"); // без этой строки клавиатура не отображается
                    setMainMenuKeyboard(message);
                    break;

                case "\uD83D\uDCF2 Внести данные":
                    message.setText("Окей, давайте вносить данные !"); break;

                case "\uD83D\uDCCA Статистика":
                    message.setText("Сейчас подготовлю цифры !"); break;

                case "\uD83D\uDEE0 Редактор CAThlete":
                    message.setText("Что будем редактировать ?"); break;

                case "ℹ Информация":
                    message.setText("Этот бот нужен для того, чтобы Ден научился писать Telegram-боты на Java !"); break;

                default:
                    message.setText("Я Вас не понял");
            }

            try {
                execute(message); // вызываем метод для отправки сообщения
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    // создаем клавиатуру главного меню
    private void setMainMenuKeyboard(SendMessage message) {
        ReplyKeyboardMarkup mainMenuKeyboard = new ReplyKeyboardMarkup();
        message.setReplyMarkup(mainMenuKeyboard); // подключение клавиатуры к сообщению
        mainMenuKeyboard.setSelective(true);
        mainMenuKeyboard.setResizeKeyboard(true); // подгонка клавиатуры
        mainMenuKeyboard.setOneTimeKeyboard(true); // скрывать клаву после первого нажатия кнопку ? true = да, false - нет

        // создание кнопок
        List<KeyboardRow> keyboardList = new ArrayList<>();

        KeyboardRow firstRow = new KeyboardRow();
        KeyboardRow secondRow = new KeyboardRow();

        firstRow.add(new KeyboardButton("\uD83D\uDCF2 Внести данные")); // добавляем кнопки в строку клавиатуры
        firstRow.add(new KeyboardButton("\uD83D\uDCCA Статистика"));

        secondRow.add(new KeyboardButton("\uD83D\uDEE0 Редактор CAThlete"));
        secondRow.add(new KeyboardButton("ℹ Информация"));

        keyboardList.add(firstRow);
        keyboardList.add(secondRow);

        mainMenuKeyboard.setKeyboard(keyboardList); // добавляем список строк с кнопками к объекту клавиатуры
    }

    // создаем клавиатуру меню "Статистика"
    private void setStatisticsKeyboard(SendMessage message) {

    }
}