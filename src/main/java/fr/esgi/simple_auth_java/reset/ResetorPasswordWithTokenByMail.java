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
import java.util.Scanner;

/**
 * <p>Implementation for reset password from console with temp pwd by mail.</p>
 * <p>It permit only 3 try for the temporary password</p>
 *
 * @author Tristan
 * @see User
 */
@EqualsAndHashCode
@ToString
@Slf4j
public class ResetorPasswordWithTokenByMail implements Resetor {
    private final static int maxTry = 3;

    /**
     * For permit tests
     */
    @Setter(AccessLevel.PACKAGE)
    @NonNull private Mailer mailer = Mailer.getInstance();

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
        //final Console console = System.console();
        final Scanner scanner = new Scanner(System.in);
        final String token = RandomStringUtils.randomAlphanumeric(10, 21);
        {
            System.out.println("Send temporary password by mail ...");
            this.mailer.sendMail(new EmailBuilder().to(String.join(" ", user.getLast_name(), user.getFirst_name()), user.getEmail())
                    .subject("Reset Password").text("Your temporary password is : " + token).build());
        }
        {
            int nb = 0;
            while(nb < maxTry) {
                log.debug("try {}", nb+1);
                //String tkn = String.valueOf(console.readPassword("Your temp password : "));
                System.out.print("Your temp password : ");
                String tkn = String.valueOf(scanner.nextLine());
                if(token.equals(tkn)) {
                    nb = Integer.MIN_VALUE;
                    break;
                }
                nb++;
            }
            log.debug("try state = {}", nb);
            if(nb > 0)
                throw new IllegalResetException("Temp password user not correct");
            {
                /* à partir d'ici, utilisateur identifié/confirmé */
                String verif = new String();
                //String newPwd = String.valueOf(console.readPassword("Your new password : "));
                System.out.print("Your new password : ");
                String newPwd = scanner.nextLine();
                do {
                    //verif = String.valueOf(console.readPassword("Confirm your new password : "));
                    System.out.print("Confirm your new password : ");
                    verif = scanner.nextLine();
                } while(!newPwd.equals(verif));
                /* nouveau mdp confirmé */
                log.trace("set user password");
                user.setPassword(newPwd);
                System.out.println("Password changed !");
            }
        }
        scanner.close();
    }
}
