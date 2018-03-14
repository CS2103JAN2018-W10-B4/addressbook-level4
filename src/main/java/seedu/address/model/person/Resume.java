package seedu.address.model.person;

public class Resume {

    private String resumeLink;

    /**
     * Constructs {@code Resume}.
     *
     * @param  currently just links to person's online resume. Will latter support individual
     *                   parts of a users resume and potentially parse and store files
     */
    public Resume(String link) {
        resumeLink = link;
    }
}
