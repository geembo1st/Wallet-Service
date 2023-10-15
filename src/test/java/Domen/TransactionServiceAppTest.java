package Domen;

import Infrastructure.TransactionService;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceAppTest {
    @Mock
    private TransactionService transactionService;
    @Test
     void debit_Should_Return_True() throws Exception {
        Player player = new Player("remy@gmail.com", "123".toCharArray());
        player.setBalance(200);
        String transactionId = "111";
        long amount =100;
        transactionService.debit(player,amount,transactionId);
    }
}
