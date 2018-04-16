package duty;
import java.util.Date;
import java.util.Properties;  
  
import javax.mail.Message;  
import javax.mail.MessagingException;  
import javax.mail.Session;  
import javax.mail.Transport;  
import javax.mail.internet.AddressException;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeMessage;  
  
public class JavaEmail  
{  
  
    Properties emailProperties;  
    Session mailSession;  
    MimeMessage emailMessage;  
  
    public static void main(String args[])  
    {  
  
        JavaEmail javaEmail = new JavaEmail();  
  
        javaEmail.setMailServerProperties();  
        try  
        {  
           // javaEmail.createEmailMessage();  
           // javaEmail.sendEmail();  
        	sendMailAdvance("573245570@qq.com", "tet","kdjfkdjfdk");
        }  
        catch (Exception e)  
        {  
            System.out.println("Address Exception:" + e.getMessage());  
            e.printStackTrace();  
        }  
        
    }  
  
    public void setMailServerProperties()  
    {  
  
        String emailPort = "587";// gmail's smtp port  
  
        emailProperties = System.getProperties();  
        emailProperties.put("mail.smtp.port", emailPort);  
        emailProperties.put("mail.smtp.auth", "true");  
        emailProperties.put("mail.smtp.starttls.enable", "true");  
  
    }  
    
    public  static boolean sendMailAdvance(String emailTo, String subject, String body)
    {
    String host = "smtp.gmail.com";
    String userName = "stocklidage@gmail.com";
    String password = "web@PWD2018";
    String port = "465";

            try
            {
                java.util.Properties props = null;
                props = System.getProperties();
                props.put("mail.smtp.user", userName);
                props.put("mail.smtp.host", host);
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.debug", "true");

                if(!"".equals(port))
                {
                    props.put("mail.smtp.port", port);
                    props.put("mail.smtp.socketFactory.port", port);
                }

                
                   props.put("mail.smtp.starttls.enable","true");

                
                    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");

                 
                    props.put("mail.smtp.socketFactory.fallback", "false");

                Session session = Session.getDefaultInstance(props, null);
                session.setDebug(true);

                MimeMessage msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress(userName));
                msg.setSubject(subject);                
                msg.setText(body, "ISO-8859-1");
                msg.setSentDate(new Date());
                msg.setHeader("content-Type", "text/html;charset=\"ISO-8859-1\"");
                msg.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
                msg.saveChanges();

                Transport transport = session.getTransport("smtp");
                transport.connect(host, userName, password);
                transport.sendMessage(msg, msg.getAllRecipients());
                transport.close();

                return true;
            }
            catch (Exception mex)
            {
                mex.printStackTrace();
                return false;
            }
    }
  
    public void createEmailMessage() throws AddressException,  
            MessagingException  
    {  
        String[] toEmails =  
        { "stocklidage@gmail.com" };  
        String emailSubject = "Java Email";  
        String emailBody = "This is an email sent by <b>JavaMail</b> api.";  
  
        mailSession = Session.getDefaultInstance(emailProperties, null);  
        emailMessage = new MimeMessage(mailSession);  
  
        for (int i = 0; i < toEmails.length; i++)  
        {  
            emailMessage.addRecipient(Message.RecipientType.TO,  
                    new InternetAddress(toEmails[i]));  
        }  
  
        emailMessage.setSubject(emailSubject);  
        emailMessage.setContent(emailBody, "text/html");// for a html email  
        // emailMessage.setText(emailBody);// for a text email  
  
    }  
  
    public void sendEmail() throws AddressException, MessagingException  
    {  
  
        String emailHost = "smtp.gmail.com";  
        String fromUser = "stocklidage@gmail.com";// just the id alone without  
        // @gmail.com  
        String fromUserEmailPassword = "web@PWD2018";  
  
        Transport transport = mailSession.getTransport("smtp");  
  
        transport.connect(emailHost, fromUser, fromUserEmailPassword);  
        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());  
        transport.close();  
        System.out.println("Email sent successfully.");  
    }  
  
}  