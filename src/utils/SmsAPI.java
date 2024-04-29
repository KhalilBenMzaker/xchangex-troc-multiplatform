/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
/**
 *
 * @author Skymil
 */
public class SmsAPI {
    public static void send(String numero, String contenu) {

        final String AUTH_TOKEN = "cbb6e7e4f68af41de53768405d74b37e";
        final String ACCOUNT_SID = "AC8c4f03ca546dc29f21f0c1b0fde3ce2f";

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                new PhoneNumber(numero), // TO
                new PhoneNumber("+15672922043"), // FROM
                contenu
        ).create();

        System.out.println(message.getSid());
    }
}
