package com.example.seireibot.modells;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class TelegramBot extends TelegramLongPollingBot {
Logger logger = LoggerFactory.getLogger(TelegramBot.class);

    @Override
    public String getBotUsername() {
        return "your botusername";
    }

    @Override
    public String getBotToken() {
        return "your token";
    }

    @Override
    public void onUpdateReceived(Update update) {
        logger.info(update.getMessage().getChat().getUserName());
        logger.info(update.getMessage().toString());



        if (update.hasMessage() && update.getMessage().hasText()) {
            Long chatId = update.getMessage().getChatId();
            // Process the message and generate a response

            if(update.getMessage().getText().contains("/start")){



                try {
                    File file = new File();
                    file.setFilePath("rickroll.gif");

                    update.getChatMember();
                    execute(new SendMessage(chatId.toString(), "Hello there "));
                    execute(new SendAnimation(chatId.toString(),new InputFile("https://media1.tenor.com/m/x8v1oNUOmg4AAAAd/rickroll-roll.gif")));

                }
                catch (TelegramApiException e){
                    e.printStackTrace();
                }
            }

            else{
                try {
                    update.getChatMember();

                    execute(new SendMessage(chatId.toString(), "You lost"));

                }
                catch (TelegramApiException e){
                    e.printStackTrace();
                }

            }

        }

    }
}
