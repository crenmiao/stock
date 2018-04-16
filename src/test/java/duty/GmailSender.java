package duty;
import java.security.Security;
import java.util.Date;
import java.util.Properties;
 
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
/**
 * 使用Gmail发送邮件
 * @author Winter Lau
 */
public class GmailSender {
 
 public static void main(String[] args) throws AddressException, MessagingException {
  Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
  final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
  // Get a Properties object
  Properties props = System.getProperties();
  props.setProperty("mail.smtp.host", "smtp.gmail.com");
  props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
  props.setProperty("mail.smtp.socketFactory.fallback", "false");
  props.setProperty("mail.smtp.port", "465");
  props.setProperty("mail.smtp.socketFactory.port", "465");
  props.put("mail.smtp.auth", "true");
  final String username = "stocklidage@gmail.com";
  final String password = "web@PWD2018";
  Session session = Session.getDefaultInstance(props, new Authenticator(){
      protected PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication(username, password);
      }});
 
       // -- Create a new message --
  Message msg = new MimeMessage(session);
 
  // -- Set the FROM and TO fields --
  msg.setFrom(new InternetAddress("stocklidage@gmail.com"));
  msg.setRecipients(Message.RecipientType.TO, 
    InternetAddress.parse("573245570@qq.com",false));
  msg.setSubject("Hello");
  msg.setText("How are you");
  msg.setSentDate(new Date());
  Transport.send(msg);
   
  System.out.println("Message sent.");
 }
}