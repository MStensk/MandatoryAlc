package beers.mandatory.controllers;

import beers.mandatory.models.Beer;
import beers.mandatory.models.Spirit;
import beers.mandatory.repository.BeerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
    public class BeerController{
        @Autowired
        BeerRepo beers;

        @GetMapping("/beers")
        public Iterable<Beer> getBeers() {
            return beers.findAll();
        }

        @GetMapping("/beers/{id}")
        public Beer getBeerById(@PathVariable Long id) {
            return beers.findById(id).get();
        }

        @PostMapping("/beers/{id]")
        public Beer addBeer(@RequestBody Beer newBeer) {
        newBeer.setBeerId(null);
        return beers.save(newBeer);
        }

        @DeleteMapping("/beers/{id}")
        public void delBeerById(@PathVariable Long id) {
            beers.deleteById(id);
        }
        @PatchMapping("/beers/{id}")
        public String patchBeerById(@PathVariable Long id, @RequestBody Beer beerToUpdateWith) {
        return beers.findById(id).map(foundBeer -> {
            if (beerToUpdateWith.getName() != null) foundBeer.setName(beerToUpdateWith.getName());
            if (beerToUpdateWith.getDescription() != null) foundBeer.setDescription(beerToUpdateWith.getDescription());
            if (beerToUpdateWith.getType() != null) foundBeer.setType(beerToUpdateWith.getType());
            if (beerToUpdateWith.getAlcPercent() != 0) foundBeer.setAlcPercent(beerToUpdateWith.getAlcPercent());

            beers.save(foundBeer);
            return "Beer updated!";
        }).orElse("Beer could not be found");
    }
    }


