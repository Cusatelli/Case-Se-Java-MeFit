package com.noroff.mefit.backup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Map;

public class BackUp {
    public static void DumpBackup() {
        Process p;
        ProcessBuilder pb;
        pb = new ProcessBuilder(
                "C:\\Program Files\\PostgreSQL\\14\\bin\\pg_dump.exe",
                "--host=localhost",
                "--port=5432",
                "--username=postgres",
                "--no-password",
                "--format=custom",
                "--blobs",
                "--verbose", "--file", "C:\\Users\\OmaAli\\{data_source}-{timestamp}-dump.sql", "postgres");
        try {
            final Map<String, String> env = pb.environment();
            env.put("PGPASSWORD", "admin");
            p = pb.start();
            final BufferedReader r = new BufferedReader(
                    new InputStreamReader(p.getErrorStream()));
            String line = r.readLine();
            while (line != null) {
                System.err.println(line);
                line = r.readLine();
            }
            r.close();
            p.waitFor();
            System.out.println(p.exitValue());

        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void RestoreBackup() throws IOException {
        Runtime r = Runtime.getRuntime();
        Process p;
        ProcessBuilder pb;
        pb = new ProcessBuilder(
                "C:/Program Files/PostgreSQL/14/bin/pg_restore.exe",
                "--host=localhost",
                "--port=5432",
                "--username=postgres",
                "--no-password",
                "--dbname=postgres",
                "--section=pre-data",
                "--section=data",
                "--section=post-data",
                "--verbose", "C:\\Users\\OmaAli\\{data_source}-{timestamp}-dump.sql");
        pb.redirectErrorStream(true);
        p = pb.start();
        InputStream is = p.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String ll;
        while ((ll = br.readLine()) != null) {
            System.out.println(ll);
        }

        // scheduled the task to be run every 24 hours at a fixed time.
        Calendar taskTime = Calendar.getInstance();
        // task will run at 1 o'clock in the morning
        taskTime.set(Calendar.HOUR_OF_DAY, 1);
        // clear the rest of the values for the calendar
        taskTime.clear(Calendar.MINUTE);
        taskTime.clear(Calendar.SECOND);
        taskTime.clear(Calendar.MILLISECOND);

        // If the time has already passed, we add another day to the count, and the task will start tomorrow
        Calendar currentDate = Calendar.getInstance();
        if (currentDate.after(taskTime)) {
            taskTime.add(Calendar.DAY_OF_YEAR, 1);
        }
    }
}
