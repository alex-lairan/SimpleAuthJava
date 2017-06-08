package fr.esgi.simple_auth_java.password_encrypt;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author Tristan
 */
@RunWith(JUnitParamsRunner.class)
public class PasswordEncryptDisableTest {
    @Test
    @Parameters
    @TestCaseName("{0}")
    public void should_encrypt(final String pwd) throws Exception {
        assertThat(new PasswordEncryptDisable().encrypt(pwd)).isEqualTo(pwd);
    }
    private List<String> parametersForShould_encrypt() {
        //http://www.di-mgt.com.au/sha_testvectors.html
        return Arrays.asList("", "abc", "SHA-256", "bbd07c4fc02c99b97124febf42c7b63b5011c0df28d409fbb486b5a9d2e615ea");
    }

}
