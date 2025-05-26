import org.mindrot.jbcrypt.BCrypt;

public class Main {
    public static void main(String[] args) {
        String contrasenha = "gato123";
        String hash = BCrypt.hashpw(contrasenha, BCrypt.gensalt(12));

        System.out.println("Hash: " + hash);

        if (BCrypt.checkpw("gato123", hash)) {
            System.out.println("Contraseña correcta");
        } else {
            System.out.println("Contraseña incorrecta");
        }
    }
}


