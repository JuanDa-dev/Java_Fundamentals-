package com.epam.rd.autocode.observer.git;

import java.util.*;
public class GitRepoObservers {
    public static Repository newRepository() {
        return new Repository() {
            private final List<WebHook> webHooks = new ArrayList<>();
            private final Map<String, List<Commit>> commitsByBranch = new HashMap<>();
            private final Map<WebHook, Integer> webHookCommitCounts = new HashMap<>();

            @Override
            public void addWebHook(WebHook webHook) {
                webHooks.add(webHook);
                webHookCommitCounts.put(webHook, commitsByBranch.getOrDefault(webHook.branch(), List.of()).size());
            }

            @Override
            public Commit commit(String branch, String author, String[] changes) {
                Commit commit = new Commit(author, changes);
                commitsByBranch.computeIfAbsent(branch, k -> new ArrayList<>()).add(commit);
                Event event = new Event(Event.Type.COMMIT, branch, List.of(commit));
                webHooks.stream()
                        .filter(webHook -> webHook.branch().equals(branch) && webHook.type() == Event.Type.COMMIT)
                        .forEach(webHook -> webHook.onEvent(event));
                return commit;
            }

            @Override
            public void merge(String sourceBranch, String targetBranch) {
                if (!sourceBranch.equals(targetBranch)) {
                    List<Commit> sourceCommits = commitsByBranch.getOrDefault(sourceBranch, new ArrayList<>());
                    List<Commit> targetCommits = commitsByBranch.getOrDefault(targetBranch, new ArrayList<>());
            
                    // Trigger WebHooks for the target branch
                    for (WebHook webHook : webHooks) {
                        if (webHook.branch().equals(targetBranch) && webHook.type() == Event.Type.MERGE) {
                            int startIndex = webHookCommitCounts.getOrDefault(webHook, 0);
                            if (startIndex < sourceCommits.size()) {
                                List<Commit> filteredCommits = sourceCommits.subList(startIndex, sourceCommits.size());
                                Event event = new Event(Event.Type.MERGE, targetBranch, filteredCommits);
                                webHook.onEvent(event);
                                webHookCommitCounts.put(webHook, sourceCommits.size());
                            }
                        }
                    }
            
                    // Trigger WebHooks for the source branch
                    for (WebHook webHook : webHooks) {
                        if (webHook.branch().equals(sourceBranch) && webHook.type() == Event.Type.MERGE) {
                            int startIndex = webHookCommitCounts.getOrDefault(webHook, 0);
                            if (startIndex < targetCommits.size()) {
                                List<Commit> filteredCommits = targetCommits.subList(startIndex, targetCommits.size());
                                Event event = new Event(Event.Type.MERGE, sourceBranch, filteredCommits);
                                webHook.onEvent(event);
                                webHookCommitCounts.put(webHook, targetCommits.size());
                            }
                        }
                    }
            
                    // Trigger WebHooks for the merged branch
                    for (WebHook webHook : webHooks) {
                        if ((webHook.branch().equals(sourceBranch) || webHook.branch().equals(targetBranch)) && webHook.type() == Event.Type.MERGE) {
                            int startIndex = webHookCommitCounts.getOrDefault(webHook, 0);
                            if (startIndex < sourceCommits.size() || startIndex < targetCommits.size()) {
                                List<Commit> filteredCommits = new ArrayList<>();
                                filteredCommits.addAll(sourceCommits.subList(startIndex, sourceCommits.size()));
                                filteredCommits.addAll(targetCommits.subList(startIndex, targetCommits.size()));
                                Event event = new Event(Event.Type.MERGE, webHook.branch(), filteredCommits);
                                webHook.onEvent(event);
                                webHookCommitCounts.put(webHook, Math.max(sourceCommits.size(), targetCommits.size()));
                            }
                        }
                    }
                }
            }
        };
    }
    public static WebHook mergeToBranchWebHook(String branchName) {
        return new WebHook() {
            private final List<Event> caughtEvents = new ArrayList<>();

            @Override
            public String branch() {
                return branchName;
            }

            @Override
            public Event.Type type() {
                return Event.Type.MERGE;
            }

            @Override
            public List<Event> caughtEvents() {
                return caughtEvents;
            }

            @Override
            public void onEvent(Event event) {
                caughtEvents.add(event);
            }
        };
    }

    public static WebHook commitToBranchWebHook(String branchName) {
        return new WebHook() {
            private final List<Event> caughtEvents = new ArrayList<>();

            @Override
            public String branch() {
                return branchName;
            }

            @Override
            public Event.Type type() {
                return Event.Type.COMMIT;
            }

            @Override
            public List<Event> caughtEvents() {
                return caughtEvents;
            }

            @Override
            public void onEvent(Event event) {
                caughtEvents.add(event);
            }
        };
    }
}
