package telegrambot.handlers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.config.interceptor.AdditionalUserPropertiesContextHolder;

@AllArgsConstructor
@Component
public class StartCmdHandler extends AbstractCmdHandler {
    private static final String THIS_CMD = "/start";

    @Override
    public SendMessage processMessage() {
        Update update = AdditionalUserPropertiesContextHolder.getContext().getUpdate();
        return SendMessage.builder()
                .chatId(update.getMessage().getChatId())
                .text("Greetings, " + update.getMessage().getFrom().getFirstName() + "!"
                        + "\nChoose your destiny...:"
                        + "\n/start"
                        + "\n/createCard"
                        + "\n/back")
                .build();
    }

    @Override
    public boolean canProcessMessage() {
        Update update = AdditionalUserPropertiesContextHolder.getContext().getUpdate();
        return update.getMessage().getText().equals(THIS_CMD);
    }
}