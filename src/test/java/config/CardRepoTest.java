//package config;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import telegrambot.TelegramBotApplication;
//import telegrambot.model.Card;
//import telegrambot.repository.CardRepository;
//
//import java.math.BigDecimal;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.mockito.Mockito.verify;
//
////@ActiveProfiles("test")
//@SpringBootTest(classes = {
////        CardRepository.class
//         TestDataSourceConfig.class
//        , TelegramBotApplication.class
//
//})
//public class CardRepoTest {
//
//    @Autowired
//    CardRepository cardRepository;
//
//    @Test
//    public void test1() {
//        Card cardToCheck = new Card(1L, "a", BigDecimal.valueOf(100));
//
//        var card = cardRepository.findById(2L).orElse(new Card(0L, "aa", BigDecimal.valueOf(1)));
//        assertThat(card)
//                .isNotNull()
//                .isEqualTo(cardToCheck);
//        verify(cardRepository).findById(2L);
//    }
//}
