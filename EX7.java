import java.io.*;

class EX7 {
    public static void main(String[] args) {
        try {
            FileInputStream fin = new FileInputStream("src.txt");
            FileOutputStream fout = new FileOutputStream("dst.txt");

            int data;

            while ((data = fin.read()) != -1) {
                fout.write(data);
            }

            fin.close();
            fout.close();

            System.out.println("File created successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

