package service;


public class MailMessage
{
    private final String subject;
    private final String body;


    public MailMessage( String subject, String body )
    {
        this.subject = subject;
        this.body = body;
    }


    public String getSubject()
    {
        return subject;
    }


    public String getBody()
    {
        return body;
    }
}
