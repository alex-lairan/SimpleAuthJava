package fr.esgi.simple_auth_java;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.validation.Validation;
import javax.validation.Validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


/**
 * Created by Blixel on 04/06/2017.
 */
@RunWith(JUnitParamsRunner.class)
public class UserTest {
    private Validator validator;

    @Before
    public void setUp() throws Exception {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    @Parameters
    @TestCaseName("{1} <- {0}")
    public void is_valide(final User user, final int nb_violation) {
        //Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertThat(validator.validate(user)).hasSize(nb_violation);
    }
    private Object[][] parametersForIs_valide() {
        return new Object[][] {
                /* correct */
                {new User("its@mail.com", "first", "last", "password"), 0},
                /* test mail */
                {new User("@mail.com", "f", "l", "pass"), 1},
                {new User("not correct@domain.org", "f", "l", "pass"), 1},
                /* test password */
                {new User("mail@domain.com", "f", "l", "non"), 1}
        };
    }

    @Test
    public void should_not_init_with_null() {
        assertThatThrownBy(() -> new User(null, "f", "l", "pass")).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> new User("m@d.c", null, "l", "pass")).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> new User("m@d.c", "f", null, "pass")).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> new User("m@d.c", "f", "l", null)).isInstanceOf(NullPointerException.class);
    }
}
