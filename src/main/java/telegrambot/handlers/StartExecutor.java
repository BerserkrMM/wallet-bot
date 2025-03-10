package telegrambot.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import telegrambot.config.interceptor.UserDataContextHolder;
import telegrambot.repository.util.CurrentConditionRepository;
import telegrambot.repository.util.MsgFromStateHistoryRepository;

import static telegrambot.model.enums.CommandEnum.START_COMMAND;
import static telegrambot.model.enums.CommandEnum.getGlobalCommands;

@RequiredArgsConstructor
@Component
public class StartExecutor extends AbstractCommandExecutor {
    private final CurrentConditionRepository currentConditionRepository;
    private final MsgFromStateHistoryRepository msgFromStateHistoryRepository;

    private static final String THIS_CMD = START_COMMAND.getCommand();

    @Override
    public boolean isSystemHandler() {
        return true;
    }

    @Override
    public void processMessage() {
        UserDataContextHolder.getFacade()
                .setText("Greetings, "
                        + UserDataContextHolder.getSenderName()
                        + "!\nChoose your destiny...:")
                .addButtons(getGlobalCommands());

        currentConditionRepository.updateCommandAndState(1L, 1L);

        boolean notCleaned = !cleanAllData();
        if (notCleaned) {
            throw new IllegalStateException("Eternal server error: cannot clear all old useless data.");
        }
    }

    @Override
    public boolean canProcessMessage() {
        return UserDataContextHolder.getInputtedTextCommand().equals(THIS_CMD);
    }

    @Override
    public boolean cleanAllData() {
        var handlers = AbstractCommandExecutor.getAllChildEntities();
        handlers.remove(this);

        for (AbstractCommandExecutor handler : handlers) {
            if (!handler.cleanAllData()) {
                return false;
            }
        }

        msgFromStateHistoryRepository.deleteAll();

        return msgFromStateHistoryRepository.findLast() == null;
    }
}