package lt.vu.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import java.util.Random;

@ApplicationScoped
@Specializes
public class ImitationContractGenerator extends ContractGenerator {
    @Override
    public String generateContract() {
        System.out.println("Using imitation (@specializes) implementation of contract generator");

        Random rand = new Random();

        return "Imitation contract: " + (rand.nextInt() & Integer.MAX_VALUE);
    }
}
