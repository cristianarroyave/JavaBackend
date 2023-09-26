package cursojava.spring.springboot.util;

import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class EncriptacionUtil {

    @Value("${cursojava.salt}")
    private String salt;

    public String hashConSalt(String cadena) {
        return Hashing.sha256()
                .hashString(cadena.concat(salt), StandardCharsets.UTF_8)
                .toString();
    }
}
