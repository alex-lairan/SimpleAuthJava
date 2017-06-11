package fr.esgi.simple_auth_java.common;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.util.ConfigLoader;

import java.io.IOException;
import java.io.InputStream;

/**
 * Mailer system.
 * Log email by default instead of send really them.
 * Else send to YopMail as set in params.
 *
 * @author Tristan
 */
@EqualsAndHashCode
@ToString
@Slf4j
public class Mailer {
    final private org.simplejavamail.mailer.Mailer mailer;

    public Mailer() {
        try(final InputStream conf = this.getClass().getResourceAsStream("simple-java-mail.properties")) {
            ConfigLoader.loadProperties(conf, false);
            this.mailer = new org.simplejavamail.mailer.Mailer();
            //this.mailer.setTransportModeLoggingOnly(true);
        } catch (IOException e) {
            throw new RuntimeException("Error while initialisation of Mailer", e); //TODO: create proper exception
        }
    }

    public boolean validate(@NonNull final Email mail) {
        return this.mailer.validate(mail);
    }

    public void sendMail(@NonNull final Email mail) {
        this.mailer.sendMail(mail);
    }

    /*
     * Simple Java Mail throws a MailException if something goes wrong and logs these by default using SLF4J. This includes checked exceptions thrown by underlying frameworks.
     *  An exception might be thrown when:
     * 1. sending an email
     * 2. signing an email with DKIM
     * 3. converting from MimeMessage to Email
     */

    /**
     * Singleton instance
     */
    private static Mailer instance;

    /**
     * get singleton
     * @return instance of Mailer
     */
    public static synchronized Mailer getInstance() {
        if(instance == null)
            instance = new Mailer();
        return instance;
    }
}
