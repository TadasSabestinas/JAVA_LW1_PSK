package lt.vu.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.util.Random;

@ApplicationScoped
@Default
@Producer

public class ContractGenerator implements IContractGenerator {
    public String generateContract() {
        System.out.println("Using default implementation of ContractGenerator");

        Random rand = new Random();

        return Integer.toString(rand.nextInt() & Integer.MAX_VALUE);
    }
}
