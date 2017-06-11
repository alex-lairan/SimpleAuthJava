package fr.esgi.simple_auth_java.reset;

import fr.esgi.simple_auth_java.User;
import fr.esgi.simple_auth_java.common.Mailer;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.simplejavamail.email.EmailBuilder;

import java.io.Console;

/**
 * <p>Implementation for reset password bu change password and send it by mail.</p>
 *
 * @author Tristan
 * @see User
 */
@Slf4j
@ToString
@EqualsAndHashCode
public class ResetorPasswordByMail implements Resetor {
    /**
     * Default constructor
     */
    public ResetorPasswordByMail() {
        this.mailer = Mailer.getInstance();
    }

    /**
     * Constructor for unit tests
     * @param mailer mailer (mock)
     */
    ResetorPasswordByMail(@NonNull final Mailer mailer) {
        this.mailer = mailer;
    }

    /**
     * For permit tests
     */
    @Setter(AccessLevel.PACKAGE)
    @NonNull
    private Mailer mailer;

    /**
     * Reset identification of a user
     *
     * @param user {@link User} to reset
     * @throws IllegalResetException If reset are not permit or the procedure isn't follow correctly (by user)
     * @throws ResetException an error occur during reset
     */
    @Override
    public void reset(@NonNull final User user) throws IllegalResetException, ResetException {
        //TODO: permettre à l'utilisateur d'annulé
        System.out.println(">> Reset Password :");
        final String newPwd = RandomStringUtils.randomAlphanumeric(10, 21);
        {
            System.out.println("Send new password by mail ...");
            this.mailer.sendMail(new EmailBuilder().to(String.join(" ", user.getLast_name(), user.getFirst_name()), user.getEmail())
                    .subject("Reset Password").text("Your new password is : " + newPwd + "\nRemember to change it quickly !").build());
            log.trace("set user password");
            user.setPassword(newPwd);
            System.out.println("Password changed and send by mail !");
        }
    }
}
