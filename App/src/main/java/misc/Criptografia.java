package misc;

import com.password4j.BcryptFunction;
import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.types.Bcrypt;

public final class Criptografia
{
    public static String encriptar(String texto)
    {
        BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B,12);

        Hash hash = Password.hash(texto)
                .with(bcrypt);

        return hash.getResult();
    }

    public static boolean verificar(String cadena, String hash)
    {
        BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B,12);
        return Password.check(cadena, hash)
                .with(bcrypt);
    }
}
