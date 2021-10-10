package beers.mandatory.repository;

import beers.mandatory.models.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerRepo extends JpaRepository<Beer,Long> {
}
