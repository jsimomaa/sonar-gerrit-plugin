package org.jenkinsci.plugins.sonargerrit.filter.predicates;

import com.google.common.base.Predicate;
import org.jenkinsci.plugins.sonargerrit.inspection.entity.IssueAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Project: Sonar-Gerrit Plugin
 * Author:  Tatiana Didik
 * Created: 20.11.2017 13:47
 * <p>
 * $Id$
 */
public class ByChangedLinesPredicate implements Predicate<IssueAdapter> {
    private final Map<String, Set<Integer>> allowedFilesAndLines;

    private ByChangedLinesPredicate(Map<String, Set<Integer>> allowedFilesAndLines) {
        if (allowedFilesAndLines != null) {
            this.allowedFilesAndLines = new HashMap<>();
            this.allowedFilesAndLines.putAll(allowedFilesAndLines);
        } else {
            this.allowedFilesAndLines = null;
        }
    }

    @Override
    public boolean apply(IssueAdapter issue) {
        return allowedFilesAndLines == null
                || allowedFilesAndLines.keySet().contains(issue.getComponent())
                && isLineChanged(issue.getLine(), allowedFilesAndLines.get(issue.getFilepath()));
    }

    private boolean isLineChanged(Integer line, Set<Integer> changedLines) {
        return changedLines.contains(line);
    }

    public static ByChangedLinesPredicate apply(Map<String, Set<Integer>> allowedComponents) {
        return new ByChangedLinesPredicate(allowedComponents);
    }
}
