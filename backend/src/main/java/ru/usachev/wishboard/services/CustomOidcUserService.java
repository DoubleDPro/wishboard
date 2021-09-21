package ru.usachev.wishboard.services;

import java.util.Date;
import java.util.Optional;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import ru.usachev.wishboard.entities.GoogleUserInfo;
import ru.usachev.wishboard.entities.Person;
import ru.usachev.wishboard.repositories.PersonRepository;

@Service
public class CustomOidcUserService extends OidcUserService {

  private final PersonRepository personRepository;

  public CustomOidcUserService(
      PersonRepository userRepository) {
    this.personRepository = userRepository;
  }

  @Override
  public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
    OidcUser oidcUser = super.loadUser(userRequest);
    try {
      return processOidcUser(userRequest, oidcUser);
    } catch (Exception ex) {
      throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
    }
  }

  private OidcUser processOidcUser(OidcUserRequest userRequest, OidcUser oidcUser) {
    GoogleUserInfo googleUserInfo = new GoogleUserInfo(oidcUser.getAttributes());
    Optional<Person> userOptional = personRepository.findByEmail(googleUserInfo.getEmail());
    if (userOptional.isEmpty()) {
      Person person = new Person();
      person.setEmail(googleUserInfo.getEmail());
      person.setName(googleUserInfo.getName());
      person.setCreatedDate(new Date());
      personRepository.save(person);
    }
    return oidcUser;
  }
}