package utils;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.DWORD;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

public class WindowHandle {

    public static long getPID(String processName) {
        return getProcessHandle(processName).get().pid();
    }

    private static Optional<ProcessHandle> getProcessHandle(String processName) {
        Optional<ProcessHandle> processHandle = ProcessHandle.allProcesses()
                .filter(p -> p.info().command().isPresent())
                .filter(p -> p.info().command().get().endsWith(processName))
                .findFirst();
        if (processHandle.isPresent()) {
            long pid = processHandle.get().pid();
            System.out.println("Found process with PID: " + pid);
            return processHandle;
        } else {
            System.out.println("Process not found.");
        }
        return Optional.empty();
    }

    public static String getProcessId(String processName) {
        try {
            String line;
            var process = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe");
            var input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = input.readLine()) != null) {
                if (line.contains(processName)) {
                    return line.split("\\s+")[1];
                }
            }
            input.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
        return null;
    }
}
