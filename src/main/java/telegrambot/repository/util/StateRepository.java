package telegrambot.repository.util;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import telegrambot.model.util.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

    @Query(value = " SELECT id, name FROM states WHERE name=:name ", nativeQuery = true)
    State findByName(@Param("name") String name);
}