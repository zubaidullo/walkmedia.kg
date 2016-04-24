package service;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import ninja.utils.NinjaProperties;


@Singleton
public class MailService
{
    @Inject
    private NinjaProperties ninjaProperties;


    public MailMessage send( String email, String subject, String body, boolean html )
    {
        Properties prop = new Properties();

        prop.put( "mail.smtp.auth", ninjaProperties.get( "smtp.auth" ) );
        prop.put( "mail.smtp.host", ninjaProperties.get( "smtp.host" ) );
        prop.put( "mail.smtp.port", ninjaProperties.get( "smtp.port" ) );

        Session session = Session.getDefaultInstance( prop );

        Message message = new MimeMessage( session );

        try
        {
            message.setRecipients( Message.RecipientType.TO, InternetAddress.parse( email ) );

            message.setSubject( subject );

            message.setText( body );

            Transport.send( message, InternetAddress.parse( email ) );

            return new MailMessage( subject, body );
        }
        catch ( MessagingException e )
        {
            throw new RuntimeException( e );
        }
    }
}

