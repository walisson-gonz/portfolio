package br.com.portfolio.service;

import br.com.portfolio.model.Usuario;
import br.com.portfolio.model.UsuarioLogin;
import br.com.portfolio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Usuario> CadastrarUsuario(Usuario usuario) {

        if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
            return null;

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String senhaEnconder = encoder.encode(usuario.getSenha());

        return Optional.of(usuarioRepository.save(usuario));
    }

    public Optional<Usuario> atualizarUsuario(Usuario usuario){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String senhaEncoder = encoder.encode(usuario.getSenha());
        usuario.setSenha(senhaEncoder);

        return Optional.of(usuarioRepository.save(usuario));
    }

    public Optional<UsuarioLogin> Logar (Optional<UsuarioLogin> usuarioLogin){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Optional<Usuario> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());
    }
}
