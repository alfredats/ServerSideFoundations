package sg.edu.nus.iss.howToRedis;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.nus.iss.howToRedis.model.UserSession;
import sg.edu.nus.iss.howToRedis.repo.UserSessionRepo;

@SpringBootTest
public class UserSessionRepositoryTests {

    @Autowired
    private UserSessionRepo userSessionRepo;

    @BeforeEach
    public void setUp() {
        userSessionRepo.deleteAll();
    }

    @Test
    public void shouldSaveUserSession() {
        UserSession userSession = new UserSession(UUID.randomUUID()
                                                      .toString(),
                "USERNAME", new Date(), "Chrome");

        UserSession saved = userSessionRepo.save(userSession);

        assertThat(saved).isNotNull();
        assertThat(userSessionRepo.count()).isEqualTo(1);
    }

    @Test
    public void shouldGetSavedUserSession() {
        String id = UUID.randomUUID()
                        .toString();
        Date loginTime = new Date();

        UserSession userSession = new UserSession(id, "USERNAME", loginTime, "Chrome");

        userSession.setLoginTime(loginTime);

        userSessionRepo.save(userSession);
        Optional<UserSession> userSessionOptional = userSessionRepo.findById(id);

        assertThat(userSessionOptional).isPresent();

        UserSession saved = userSessionOptional.get();

        assertThat(saved).isNotNull();
        assertThat(saved.getBrowser()).isEqualTo("Chrome");
        assertThat(saved.getUsername()).isEqualTo("USERNAME");
        assertThat(saved.getLoginTime()).isEqualTo(loginTime);
    }

    @Test
    public void shouldUpdateSavedUserSession() {
        String id = UUID.randomUUID()
                        .toString();
        UserSession userSession = new UserSession(id, "USERNAME", new Date(), "Chrome");

        UserSession saved = userSessionRepo.save(userSession);
        saved.setBrowser("IE");
        userSessionRepo.save(saved);

        Optional<UserSession> userSessionOptional = userSessionRepo.findById(id);

        assertThat(userSessionOptional).isPresent();

        UserSession updated = userSessionOptional.get();

        assertThat(updated).isNotNull();
        assertThat(updated.getBrowser()).isEqualTo("IE");
        assertThat(updated.getUsername()).isEqualTo("USERNAME");
    }

    @Test
    public void shouldDeleteUserSession() {
        String id = UUID.randomUUID()
                        .toString();
        UserSession userSession = new UserSession(id, "USERNAME", new Date(), "Chrome");

        userSessionRepo.save(userSession);
        userSessionRepo.deleteById(id);

        assertThat(userSessionRepo.count()).isEqualTo(0);
    }

    @Test
    public void shouldFindAllUserSessionObjects() {
        UserSession userSession1 = new UserSession(UUID.randomUUID()
                                                       .toString(),
                "USERNAME", new Date(), "Chrome");

        UserSession userSession2 = new UserSession(UUID.randomUUID()
                                                       .toString(),
                "USERNAME2", new Date(), "IE");

        userSessionRepo.save(userSession1);
        userSessionRepo.save(userSession2);

        Spliterator<UserSession> spliterator = userSessionRepo.findAll()
                                                                    .spliterator();

        List<UserSession> all = StreamSupport.stream(spliterator, false)
                                             .collect(Collectors.toList());

        assertThat(all.size()).isEqualTo(2);
        assertThat(all).extracting("username", "browser")
                       .containsOnly(tuple("USERNAME", "Chrome"), tuple("USERNAME2", "IE"));
    }
}
