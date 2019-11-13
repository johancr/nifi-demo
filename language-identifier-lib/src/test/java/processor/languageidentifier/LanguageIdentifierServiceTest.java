package processor.languageidentifier;

import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class LanguageIdentifierServiceTest {

    private final LanguageIdentifierService languageIdentifierService = new LanguageIdentifierService();

    @Test
    public void swedish() {
        InputStream data = LanguageIdentifierServiceTest.class.getResourceAsStream("/swedish.txt");

        Language language = languageIdentifierService.identify(() -> data);

        assertEquals(Language.SWEDISH, language);
    }

    @Test
    public void english() {
        InputStream data = LanguageIdentifierServiceTest.class.getResourceAsStream("/english.txt");

        Language language = languageIdentifierService.identify(() -> data);

        assertEquals(Language.ENGLISH, language);
    }
}
