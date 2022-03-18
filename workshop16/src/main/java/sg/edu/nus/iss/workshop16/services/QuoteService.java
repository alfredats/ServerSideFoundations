package sg.edu.nus.iss.workshop16.services;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class QuoteService {
    private static final String[] QUOTES = {
        "one", "two", "three", "four", "five"
    };
    final Random rand = new SecureRandom();

    public String getQuote() {
        final Integer idx = rand.nextInt(QUOTES.length);
        return QUOTES[idx];
    }

    public Collection<String> getQuotes(int count) {
        List<String> q = new LinkedList<>();
        for (int i = 0; i < count; i++) { q.add(getQuote()); }
        return q;
    }
}
