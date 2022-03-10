package cz.fel.cvut.lab4.refactoring;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MailHelperTest {

    private Mail mail;
    private MailHelper mailHelper;

    @BeforeEach
    void setUp()
    {
        mail = new Mail();
        mailHelper = new MailHelper(mail);
        mailHelper.createAndSendMail("To", "Subject", "Body");
    }

    @Test
    void GetMailTest()
    {
       assertEquals(mail, mailHelper.getMail());
    }

    @Test
    void setTo_toIsSet_returnsTo()
    {
        assertEquals("To", mailHelper.getMail().getTo());
    }

    @Test
    void setSubject_toIsSet_returnsSubject()
    {
        assertEquals("Subject", mailHelper.getMail().getSubject());
    }

    @Test
    void setBody_toIsSet_returnsBody()
    {
        assertEquals("Body", mailHelper.getMail().getBody());
    }
}