package project;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ForgotPassword {

    public static void Send(final String username, final String password, String recipientEmail, String title, String message) throws AddressException, MessagingException {
        ForgotPassword.Send(username, password, recipientEmail, "", title, message);
    }

    public static void Send(final String username, final String password, String recipientEmail, String ccEmail, String title, String message) throws AddressException, MessagingException {

        // Get a Properties object
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props);

        // -- Create a new message --
        MimeMessage msg = new MimeMessage(session);

        // -- Set the FROM and TO fields --
        msg.setFrom(new InternetAddress(username + "@gmail.com"));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail, false));

        if (!ccEmail.isEmpty()) {
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmail, false));
        }

        msg.setSubject(title);
        msg.setText(message, "utf-8");
        msg.setSentDate(new Date());

        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.gmail.com", username, password);
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
    }
}







//package project;
////import com.sun.mail.smtp.SMTPTransport;
//import java.security.Security;
//import java.util.Date;
//import java.util.Properties;
//
//import javax.mail.Session;
//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
////import java.*;
//
///**
// *
// * @author Void
// */
//
//
//public class ForgotPassword {
//
//    public static void Send(final String username, final String password, String recipientEmail, String title, String message) throws AddressException, MessagingException {
//        ForgotPassword.Send(username, password, recipientEmail, "", title, message);
//    }
//
//    public static void Send(final String username, final String password, String recipientEmail, String ccEmail, String title, String message) throws AddressException, MessagingException {
//        Security.addProvider(new com.sun.sun.net.ssl.internal.ssl.Provider());
//        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
//
//        // Get a Properties object
//        Properties props = System.getProperties();
//        props.setProperty("mail.smtps.host", "smtp.gmail.com");
//        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
//        props.setProperty("mail.smtp.socketFactory.fallback", "false");
//        props.setProperty("mail.smtp.port", "465");
//        props.setProperty("mail.smtp.socketFactory.port", "465");
//        props.setProperty("mail.smtps.auth", "true");
//
//        props.put("mail.smtps.quitwait", "false");
//
//        Session session = Session.getInstance(props, null);
//
//        // -- Create a new message --
//        final MimeMessage msg = new MimeMessage(session);
//
//        // -- Set the FROM and TO fields --
//        msg.setFrom(new InternetAddress(username + "@gmail.com"));
//        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail, false));
//
//        if (ccEmail.length() > 0) {
//            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmail, false));
//        }
//
//        msg.setSubject(title);
//        msg.setText(message, "utf-8");
//        msg.setSentDate(new Date());
//
//        SMTPTransport t = (SMTPTransport)session.getTransport("smtps");
//
//        t.connect("smtp.gmail.com", username, password);
//        t.sendMessage(msg, msg.getAllRecipients());      
//        t.close();
//    }
//}