package domen;

import infrastructure.TransactionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceAppTest {
    @Mock
    private TransactionService transactionService;
    @Test
    @DisplayName("debit_Should_Return_True")
     void debitShouldReturnTrue() throws Exception {
        Player player = new Player("remy@gmail.com", "123".toCharArray());
        player.setBalance(200);
        String transactionId = "111";
        long amount =100;
        transactionService.debit(player,amount,transactionId);
    }
}
