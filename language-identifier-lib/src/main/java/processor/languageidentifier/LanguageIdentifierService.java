package processor.languageidentifier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Supplier;

public class LanguageIdentifierService {

    public Language identify(Supplier<InputStream> data) {
        try (InputStream inputStream = data.get()) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.matches(".*[åäö].*")) {
                    return Language.SWEDISH;
                }
            }
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
        return Language.ENGLISH;
    }
}
