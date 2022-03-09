package sg.edu.nus.iss.howToRedis.repo;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import sg.edu.nus.iss.howToRedis.model.UserSession;

@Repository
public interface UserSessionRepo extends CrudRepository<UserSession, String> {
    
}
