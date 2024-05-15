package lt.vu.services;

import org.apache.deltaspike.core.api.future.Futureable;

import javax.ejb.AsyncResult;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.Random;

@ApplicationScoped

public class NickNameGenerator implements Serializable, INickNameGenerator {
    private static final String[] NAMES = {"fiona", "max", "luna", "jack", "sophie", "oliver", "amelia", "logan", "mia", "noah", "emma", "aiden", "ava", "liam", "isabella"};
    private static final Random RANDOM = new Random();
    @Futureable
    public Future<String> generateNickName() {
        System.out.println("Using default implementation of NickNameGenerator");

        try {
            Thread.sleep(3000); // Simulate intensive work
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        String name = NAMES[RANDOM.nextInt(NAMES.length)];

        int number = 1000 + RANDOM.nextInt(9000);

        String generatedNickname = number + name;
        return new AsyncResult<>(generatedNickname);
    }
}
