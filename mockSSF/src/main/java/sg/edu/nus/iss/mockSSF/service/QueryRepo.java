package sg.edu.nus.iss.mockSSF.service;

import org.springframework.data.repository.CrudRepository;
import sg.edu.nus.iss.mockSSF.model.Query;

public interface QueryRepo extends CrudRepository<Query, String> {
    
}
