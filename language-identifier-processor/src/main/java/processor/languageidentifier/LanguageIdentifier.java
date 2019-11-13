package processor.languageidentifier;

import org.apache.nifi.flowfile.FlowFile;
import org.apache.nifi.processor.AbstractProcessor;
import org.apache.nifi.processor.ProcessContext;
import org.apache.nifi.processor.ProcessSession;
import org.apache.nifi.processor.Relationship;
import org.apache.nifi.processor.exception.ProcessException;

import java.util.HashSet;
import java.util.Set;

public class LanguageIdentifier extends AbstractProcessor {

    private final LanguageIdentifierService languageIdentifierService = new LanguageIdentifierService();

    private final Relationship english = new Relationship.Builder().name("english").build();
    private final Relationship swedish = new Relationship.Builder().name("swedish").build();

    @Override
    public Set<Relationship> getRelationships() {
        return new HashSet<Relationship>() {{
            add(english);
            add(swedish);
        }};
    }

    @Override
    public void onTrigger(ProcessContext processContext, ProcessSession processSession) throws ProcessException {
        FlowFile flowFile = processSession.get();
        if (flowFile == null) {
            return;
        }

        Language language = languageIdentifierService.identify(() -> processSession.read(flowFile));

        if (Language.ENGLISH.equals(language)) {
            processSession.transfer(flowFile, english);
        } else if (Language.SWEDISH.equals(language)) {
            processSession.transfer(flowFile, swedish);
        }
    }
}
