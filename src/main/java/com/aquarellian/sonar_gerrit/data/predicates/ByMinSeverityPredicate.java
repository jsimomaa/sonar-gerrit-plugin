package com.aquarellian.sonar_gerrit.data.predicates;

import com.aquarellian.sonar_gerrit.data.entity.Issue;
import com.aquarellian.sonar_gerrit.data.entity.Severity;
import com.google.common.base.Predicate;

/**
 * Project: genar
 * Author:  Tatiana Didik
 * Created: 16.09.2015 13:24
 * <p/>
 * $Id$
 */
public class ByMinSeverityPredicate implements Predicate<Issue> {

    private final Severity severity;

    public static ByMinSeverityPredicate apply(Severity severity) {
        return new ByMinSeverityPredicate(severity);
    }

    private ByMinSeverityPredicate(Severity severity) {
        this.severity = severity;
    }

    @Override
    public boolean apply(Issue issue) {
        return issue.getSeverity().equals(severity) || issue.getSeverity().ordinal() >= severity.ordinal();
    }
}