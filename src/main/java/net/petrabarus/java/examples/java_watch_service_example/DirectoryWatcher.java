package net.petrabarus.java.examples.java_watch_service_example;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class DirectoryWatcher {

    public WatchService watcher;

    public Path path;

    public DirectoryWatcher(String directoryPath) throws IOException {
        watcher = FileSystems.getDefault().newWatchService();
        path = FileSystems.getDefault().getPath(directoryPath);
    }

    public void run() throws IOException {
        try {
            WatchKey key = path.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        }
        while (true) {
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
                return;
            }
            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();
                WatchEvent<Path> ev = (WatchEvent<Path>) event;
                Path fileName = ev.context();

                System.out.println(kind.name() + ": " + fileName);
                if (kind == OVERFLOW) {
                    continue;
                } else if (kind == ENTRY_CREATE) {
                    //TODO
                } else if (kind == ENTRY_DELETE) {
                    //TODO
                } else if (kind == ENTRY_MODIFY) {
                    //TODO
                }
            }
            boolean valid = key.reset();
            if (!valid) {
                break;
            }
        }
    }
}
