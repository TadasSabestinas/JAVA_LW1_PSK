package lt.vu.services;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
@Producer
@Decorator
public class ContractGeneratorEuros implements IContractGenerator {
    @Inject
    @Delegate
    @Producer
    IContractGenerator contractGenerator;

    public String generateContract(){
        System.out.println("Using decorate implementation for contractGenerator");

        return contractGenerator.generateContract() + " euros";
    }
}
