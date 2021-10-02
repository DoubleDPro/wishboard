package ru.usachev.wishboard.controllers;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.usachev.wishboard.entities.Person;
import ru.usachev.wishboard.repositories.PersonRepository;

@RestController
public class AuthController {

  final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

  final PersonRepository personRepository;

  public AuthController(
      OAuth2AuthorizedClientService oAuth2AuthorizedClientService,
      PersonRepository personRepository) {
    this.oAuth2AuthorizedClientService = oAuth2AuthorizedClientService;
    this.personRepository = personRepository;
  }

  @GetMapping("auth")
  public void auth(@AuthenticationPrincipal OAuth2User user, HttpServletResponse response)
      throws IOException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
    OAuth2AuthorizedClient client =
        oAuth2AuthorizedClientService.loadAuthorizedClient(
            oAuth2AuthenticationToken.getAuthorizedClientRegistrationId(),
            oAuth2AuthenticationToken.getName());
    String accessToken = client.getAccessToken().getTokenValue();
    response.addHeader("Set-Cookie", "Authorization=" + accessToken);
    response.addHeader("Set-Cookie",
        "Person=" + URLEncoder.encode(user.getAttribute("name"), StandardCharsets.UTF_8).replace("+", "%20"));
    String redirect = "http://localhost:8081";
    response.sendRedirect(redirect);
  }

  @PostMapping("reg")
  public ResponseEntity<String> reg(@RequestBody Person person) {
    // TODO change person variable type to PersonDto
    // TODO add jwt exchange https://java-master.com/spring-security-%D1%81-%D0%BF%D0%BE%D0%BC%D0%BE%D1%89%D1%8C%D1%8E-jwt-%D1%82%D0%BE%D0%BA%D0%B5%D0%BD%D0%B0/
    if (personWithCurrentEmailExist(person.getEmail())) {
      return new ResponseEntity<>("{message : Пользователь с таким Email уже существует}", HttpStatus.CONFLICT);
    } else {
      //TODO Разобраться с полем salt и генерить его
      person.setCreatedDate(new Date());
      person.setPassSalt("salt");
      Person savedPerson = personRepository.save(person);
      return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }

  }

  private boolean personWithCurrentEmailExist(String email) {
    return personRepository.findByEmail(email).isPresent();
  }
}
