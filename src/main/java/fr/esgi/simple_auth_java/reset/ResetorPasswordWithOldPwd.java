package fr.esgi.simple_auth_java.reset;

import fr.esgi.simple_auth_java.User;

import java.io.Console;

/**
 * <p>Implementation for reset password from console with the old pwd.</p>
 * <p>It permit only 3 try for the old password</p>
 *
 * @author Tristan
 * @see User
 */
public class ResetorPasswordWithOldPwd implements Resetor {
    private final static int maxTry = 3;

    /**
     * Reset identification of a user
     *
     * @param user {@link User} to reset
     * @throws IllegalResetException If reset are not permit or the procedure isn't follow correctly (by user)
     * @throws ResetException an error occur during reset
     */
    @Override
    public void reset(User user) throws IllegalResetException, ResetException {
        //TODO: permettre à l'utilisateur d'annulé
        System.out.println(">> Reset Password :");
        final Console console = System.console();
        {
            int nb = 0;
            while(nb < maxTry) {
                String old = user.getEncryptor().encrypt(String.valueOf(console.readPassword("Your old password : ")));
                if(user.getPassword().equals(old)) {
                    nb = Integer.MIN_VALUE;
                    break;
                }
            }
            if(nb > 0)
                throw new IllegalResetException("Old password user not correct");
            {
                /* à partir d'ici, utilisateur identifié/confirmé */
                String verif = new String();
                String newPwd = String.valueOf(console.readPassword("Your new password : "));
                do {
                    verif = String.valueOf(console.readPassword("Confirm your password : "));
                } while(!newPwd.equals(verif));
                /* nouveau mdp confirmé */
                user.setPassword(newPwd);
                System.out.println("Password changed !");
            }
        }
    }
}
