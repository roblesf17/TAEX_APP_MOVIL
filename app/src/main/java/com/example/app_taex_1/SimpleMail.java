package com.example.app_taex_1;

import java.io.File;
import java.util.Properties;

import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class SimpleMail {


    private static final String SMTP_HOST_NAME = "smtp.gmail.com"; //host
    private static final String SMTP_AUTH_USER = "taex.upt@gmail.com"; //usuario que envía el correo
    private static final String SMTP_AUTH_PWD  = "sharonykatty"; //contraseña del correo
    private String path_img;
    private static Message message;



    public static void enviarEmail(String nombre_alumno, String apellido_alumno, String to, String subject, String taller, String docente, String dia, String hora, String lugar, String costo){

        String email_origen = "taex.upt@gmail.com";

        final String usuario = SMTP_AUTH_USER;
        final String password = SMTP_AUTH_PWD;

        String host = SMTP_HOST_NAME;


        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Obtener la session
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(usuario, password);
                    }
                });

        try {
            //Creando un objeto MimeMessage predeterminado.
            message = new MimeMessage(session);

            // De
            message.setFrom(new InternetAddress(email_origen));

            // Para
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Asunto
            message.setSubject(subject);

            // Cuerpo del Mensaje
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("<html><head>"
                            + "<title>TAEX</title>"
                            + "</head>\n"
                            + "<body>"

                            +"<img src=\"https://firebasestorage.googleapis.com/v0/b/taex-cd0ee.appspot.com/o/logo_taex.png?alt=media&token=1e39d56d-1ebf-4bd9-b97c-fde8325119d3\"width=\"100\" height=\"100\"/>"
                            + "<div> <font color=\"#041F85\"> <b>¡FELICITACIONES " + nombre_alumno + " " + apellido_alumno + "!</b> </font></div>\n"
                            + "<br>"
                            + "<div> <font color=\"#D89000\"> <i> Te has registrado en el siguiente taller: </i> </font></div>\n"
                            + "<br>"

                            +"<table border=\"1\" cellspacing=\"0\" cellpadding=\"2\" bordercolor=\"#041F85\" >\n" +
                            "\t\t<tr>\n" +
                            "\t\t\t<th>TALLER</th>\n" +
                            "\t\t\t<th>DOCENTE</th>\n" +
                            "\t\t\t<th>DÍA</th>\n" +
                            "\t\t\t<th>HORA</th>\n" +
                            "\t\t\t<th>LUGAR</th>\n" +
                            "\t\t</tr>\n" +
                            "\t\t<tr>\n" +
                            "\t\t\t<td>" + taller + "</td>\n" +
                            "\t\t\t<td>" + docente + "</td>\n" +
                            "\t\t\t<td>" + dia + "</td>\n" +
                            "\t\t\t<td>" + hora + "</td>\n" +
                            "\t\t\t<td>" + lugar + "</td>\n" +
                            "\t\t</tr>\n" +
                            "\t\t</table>"

                            + "<br>"
                            + "<div> <font color=\"#D89000\"> <i> El costo del taller es: S/" + costo + "</i> </font></div>\n"

                            + "<br>"

                            +"<img src=\"https://firebasestorage.googleapis.com/v0/b/taex-cd0ee.appspot.com/o/LOGO_OBUN.png?alt=media&token=3c82683c-952c-4e6a-8f53-7b2a5d9e0208\" width=\"150\" height=\"80\"/>"
                            +"<img src=\"https://1.bp.blogspot.com/-fiCqijmx7UY/Won9KRnJa0I/AAAAAAAAAII/b6s8IbQxqosNaboSBHLpNH_189pil13ZACLcBGAs/s1600/upt-resultados.jpg\" width=\"200\" height=\"100\"/>"

                            + "</body>"
                            + "</html>",
                    "US-ASCII", "html");

            MimeMultipart content = new MimeMultipart("related");

            content.addBodyPart(textPart);
            message.setContent(content);



            /****/

            /*BodyPart messageBodyPart = new MimeBodyPart();

            messageBodyPart.setContent(msg, "text/html");

            Multipart multipart = new MimeMultipart();

            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);*/

            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    try  {

                        // Enviar mensaje
                        Transport.send(message);
                        System.out.println("Inscripción exitosa...");

                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }
            });

            thread.start();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
