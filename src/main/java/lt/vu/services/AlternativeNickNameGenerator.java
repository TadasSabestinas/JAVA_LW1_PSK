package lt.vu.services;

import org.apache.deltaspike.core.api.future.Futureable;
import lt.vu.interceptors.*;

import javax.ejb.AsyncResult;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.Random;

@ApplicationScoped
@Alternative
public class AlternativeNickNameGenerator implements Serializable, INickNameGenerator {
    private static final String[] WORDS = {"fiona", "max", "luna", "jack", "sophie", "oliver", "amelia", "logan", "mia", "noah", "emma", "aiden", "ava", "liam", "isabella"};
    private static final Random RANDOM = new Random();
    @Futureable
    public Future<String> generateNickName() {
        System.out.println("Using alternative implementation of NickNameGenerator");

        try {
            Thread.sleep(4000); // Simulate intensive work
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        int number = 1000 + RANDOM.nextInt(9000);

        String word = WORDS[RANDOM.nextInt(WORDS.length)];
        String generatedNickname = word + number;

        return new AsyncResult<>(generatedNickname);
    }
}
