package cursojava.spring.springboot.controladores;


import cursojava.spring.springboot.dto.autorizacion.AuthResponse;
import cursojava.spring.springboot.dto.autorizacion.LoginRequest;
import cursojava.spring.springboot.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.AuthenticationException;

@RestController
@RequestMapping("/login")
public class ControladorLogin {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private Logger logger = LoggerFactory.getLogger(ControladorLogin.class);

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsuario(), loginRequest.getPassword()));
        } catch (AuthenticationException e) {
            logger.error("Error al hacer login", e);
            return ResponseEntity.badRequest().body("Usuario o password incorrectos");
        } catch (Exception e) {
            logger.error("Error al hacer login", e);
            return ResponseEntity.internalServerError().build();
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsuario());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(jwt));

    }
}
