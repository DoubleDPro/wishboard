package ru.usachev.wishboard.controllers;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

  public AuthController(
      OAuth2AuthorizedClientService oAuth2AuthorizedClientService) {
    this.oAuth2AuthorizedClientService = oAuth2AuthorizedClientService;
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
}
