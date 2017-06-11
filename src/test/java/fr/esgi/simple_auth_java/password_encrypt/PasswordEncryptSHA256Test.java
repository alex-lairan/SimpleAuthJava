package fr.esgi.simple_auth_java.password_encrypt;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Created by Blixel on 04/06/2017.
 */
@RunWith(JUnitParamsRunner.class)
public class PasswordEncryptSHA256Test {
    @Test
    @Parameters
    @TestCaseName("{0} => {1}")
    public void should_encrypt(final String pwd, final String hash) throws Exception {
        assertThat(PasswordEncryptSHA256.hash(pwd)).isNotNull().isNotEmpty().isNotBlank().isEqualTo(hash);
    }
    private String[][] parametersForShould_encrypt() {
        //http://www.di-mgt.com.au/sha_testvectors.html
        return new String[][]{{"", "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855"},
                {"abc", "ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad"},
                {"abcdbcdecdefdefgefghfghighijhijkijkljklmklmnlmnomnopnopq", "248d6a61d20638b8e5c026930c3e6039a33ce45964ff2167f6ecedd419db06c1"},
                {"abcdefghbcdefghicdefghijdefghijkefghijklfghijklmghijklmnhijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu", "cf5b16a778af8380036ce59e7b0492370b249b11e8f07a51afac45037afee9d1"},
                {"SHA-256", "bbd07c4fc02c99b97124febf42c7b63b5011c0df28d409fbb486b5a9d2e615ea"}};
    }

    @Test
    public void should_not_encrypt_null() {
        assertThatThrownBy(() -> PasswordEncryptSHA256.hash(null)).isInstanceOf(NullPointerException.class);
    }
}
