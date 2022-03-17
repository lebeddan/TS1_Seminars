package cz.fel.cvut.lab5;

import cz.fel.cvut.lab4.refactoring.DBManager;
import cz.fel.cvut.lab4.refactoring.Mail;
import cz.fel.cvut.lab4.refactoring.MailHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;

public class MockMailHelperTest
{
    DBManager mockDBManager;
    MailHelper mailHelper;

    @BeforeEach
    void setUp()
    {
        mockDBManager = Mockito.mock(DBManager.class);
        mailHelper = new MailHelper(mockDBManager);
    }

    @Test
    void negativeVerifyTwoTimes()
    {
        //mailHelper.sendMail(Mockito.anyInt());
        Mockito.verify(mockDBManager, times(2)).findMail(Mockito.anyInt());
    }

    @Test
    void positiveVerifySendMailOnes()
    {
        mailHelper.sendMail(Mockito.anyInt());
        Mockito.verify(mockDBManager).findMail(Mockito.anyInt());
    }

    @Test
    void mockTest()
    {
        int mailId = 1;
        Mail mail = new Mail();

        Mockito.when(mockDBManager.findMail(mailId)).thenReturn(mail);
        mailHelper.sendMail(mailId);
        Mockito.verify(mockDBManager).findMail(mailId);
    }

    @Test
    void checkMailReturnsCorrectTo()
    {
        int mailId = Mockito.anyInt();
        Mockito.when(mockDBManager.findMail(Mockito.anyInt())).thenReturn(getMail());
        mailHelper.sendMail(mailId);
        Assertions.assertEquals("ABCD", mailHelper.getMail().getTo());
    }

}
