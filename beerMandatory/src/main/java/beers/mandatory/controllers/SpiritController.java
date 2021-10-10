package beers.mandatory.controllers;

import beers.mandatory.models.Beer;
import beers.mandatory.models.Spirit;
import beers.mandatory.repository.BeerRepo;
import beers.mandatory.repository.SpiritRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SpiritController {

        @Autowired
        SpiritRepo spirits;

        @GetMapping("/spirits")
        public Iterable<Spirit> getSpirits() {
            return spirits.findAll();
        }

        @GetMapping("/spirits/{id}")
        public Spirit getSpiritById(@PathVariable Long id) {
            return spirits.findById(id).get();
        }

        @PostMapping("/spirits/{id}")
        public Spirit addSpirit(@RequestBody Spirit newSpirit) {
            newSpirit.setSpiritId(null);
            return spirits.save(newSpirit);
        }

        @DeleteMapping("/spirits/{id}")
        public void delSpiritById(@PathVariable Long id) {
            spirits.deleteById(id);
        }

        @PutMapping("/spirits/{id}")
        public String updateSpiritWithId(@PathVariable Long id, @RequestBody Spirit spiritToUpdateWith) {
        if (spirits.existsById(id)) {
            spiritToUpdateWith.setSpiritId(id);
            spirits.save(spiritToUpdateWith);
            return "Spirit has been submitted!";
            } else {
            return "The requested spirit could not be found.";
            }
        }
        @PatchMapping("/spirits/{id}")
        public String patchSpiritById(@PathVariable Long id, @RequestBody Spirit spiritToUpdateWith) {
        return spirits.findById(id).map(foundSpirit -> {
            if (spiritToUpdateWith.getName() != null) foundSpirit.setName(spiritToUpdateWith.getName());
            if (spiritToUpdateWith.getDescription() != null) foundSpirit.setDescription(spiritToUpdateWith.getDescription());
            if (spiritToUpdateWith.getType() != null) foundSpirit.setType(spiritToUpdateWith.getType());
            if (spiritToUpdateWith.getAlcPercent() != 0) foundSpirit.setAlcPercent(spiritToUpdateWith.getAlcPercent());

            spirits.save(foundSpirit);
            return "Spirit updated!";
        }).orElse("Spirit could not be found");
    }
}
