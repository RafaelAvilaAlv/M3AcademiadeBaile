package Controlador;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

public class Email {

    public void enviarEmail(String correoDestino, String ruta, String nombreDocumento) {

        try {
            //ENVIAR CORREO: CON TEXTO Y CON ARCHIVO ADJUNTO

            String correo = "mongebyron46@gmail.com";
            String contra = "cjqntdlnibofgbvc"; //En esta parte se debe colocar la contraseña generada de google

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", correo);
            props.setProperty("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props);

            ////////////Enviar archivo Adjunto/////////////////
            BodyPart texto = new MimeBodyPart();
            texto.setText("REGISTRO DE MATRÍCULA"); //Aqui va el texto que encabezara al documento enviado
            BodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler(new DataHandler(new FileDataSource(ruta))); //Aqui va la ruta del archivo que deseo enviar
            adjunto.setFileName(nombreDocumento);//Aqui va el nombre del archivo que estoy enviado mas su extension
            MimeMultipart m = new MimeMultipart();
            m.addBodyPart(texto);
            m.addBodyPart(adjunto);
            /////////////////////////////////////////////////

            MimeMessage adjunto2 = new MimeMessage(session);

            MimeMessage mensaje = new MimeMessage(session);

            adjunto2.setFrom(new InternetAddress(correo));
            adjunto2.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDestino));
            adjunto2.setContent(m); // PARA ARCHIVO ADJUNTO

            mensaje.setFrom(new InternetAddress(correo));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDestino));
            adjunto2.setSubject("Matrícula"); //Aqui va el asunto del email
            Transport transport = session.getTransport("smtp");
            transport.connect(correo, contra);
            transport.sendMessage(adjunto2, adjunto2.getAllRecipients());
            transport.close();

            JOptionPane.showMessageDialog(null, "La matrícula ha sido enviada al correo del estudiante");

        } catch (MessagingException e) {

            System.out.println("Error: " + e);
        }
    }
}
