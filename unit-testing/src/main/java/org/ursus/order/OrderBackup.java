package org.ursus.order;

import org.ursus.order.Order;

import java.io.*;

public class OrderBackup {
    private Writer writer;

    public Writer getWriter() {
        return writer;
    }

    public void createFile() throws FileNotFoundException {
        File file = new File("orderBackup");

        // FileOutputStream is a class that writes data to a file. It is a byte oriented class, which means it
        // writes data in the form of bytes. It is used to create a file and write data into it.
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        // OutputStreamWriter is a bridge from character streams to byte streams: Characters written to it are
        // encoded into bytes using a specified charset. The charset that it uses may be specified by name or may
        // be given explicitly, or the platform's default charset may be accepted.
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);

        writer = new BufferedWriter(outputStreamWriter);
    }

    public void backupOrder(Order order) throws IOException {
        if(writer == null) {
            throw new IOException("Backup file doesn't exist");
        }
        writer.append(order.toString());
    }

    public void closeFile() throws IOException {
        writer.close();
    }
}
