package net.petrabarus.java.examples.java_watch_service_example;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import java.util.List;

public class App {
    
    
    private static class MainCommand {
        
        @Parameter(description = "Directory path")
        public List<String> directory;
    }

    public static void main(String[] args) throws Exception {
        String path = getDirectoryByArgs(args);
        DirectoryWatcher watcher = new DirectoryWatcher(path);
        watcher.run();
    }
    
    private static String getDirectoryByArgs(String[] args) throws Exception {
        MainCommand command = new MainCommand();
        JCommander jCommander = JCommander.newBuilder()
                .addObject(command)
                .build();
        jCommander.parse(args);
        
        if (command.directory.isEmpty()) {
            throw new Exception("No directory found");
        }
        return command.directory.get(0);
    }
}
