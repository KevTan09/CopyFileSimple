import java.nio.file.*;
import java.util.Scanner;

public class Main {
    private static void print(Object message) {
        System.out.println(message);
    }

    /**
     * Mengecek apakah file benar-benar ada atau tidak
     *
     * @param originalSource nama dari file tersebut lengkap dengan directorynya
     * @return boolean
     */
    private static boolean checkFile(Path originalSource) {
        if (!Files.isRegularFile(originalSource)) {
            print("file tidak ditemukan");
            print("tips:");
            print("1. Ketiklah nama file lengkap dengan path directory dan extensionnya");
            print("contoh: sample/sample_file_1.txt");
            print("2. Jika File berada di luar directory tempat program dijalankan, ketiklah path directory dari root directory");
            print("contoh:");
            print("- Windows : C:\\WINDOWS\\path\\to\\file\\file.txt");
            print("- Linux : home/user/Documents/path/to/file/file.txt");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Mengecek apakah folder tujuan benar-benar merupakan sebuah directory penyimpanan
     *
     * @param destination folder tujuan
     * @return boolean
     */
    private static boolean checkDirectory(Path destination) {
        if (!Files.isDirectory(destination)) {
            print("folder tidak ditemukan");
            print("tips:");
            print("1. Ketiklah nama folder lengkap dengan path directory");
            print("contoh: sample/destination/");
            print("2. Jika File berada di luar directory tempat program dijalankan, ketiklah path directory dari root directory");
            print("contoh:");
            print("- Windows : C:\\WINDOWS\\path\\to\\file\\");
            print("- Linux : home/user/Documents/path/to/file/");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Copy ini akan mengcopy keseluruhan file, dimana prosesnya diatur dalam oleh java.nio.file.Files
     * Tes untuk mengcopy gambar(jpg) dan text biasa(txt) menunjukkan hasil yang positif
     *
     * @param origin Path file origin
     * @param destination Path file tujuan
     */
    private static void copy_v1(Path origin, Path destination){
        try {
            Path result = Files.copy(
                    origin,
                    Paths.get(destination.toString(), origin.getFileName().toString()).toAbsolutePath()
            );
            print("File telah diCopy kedalam " + result.toAbsolutePath().toString());
        } catch (FileAlreadyExistsException e) {
            print("Copy file gagal karena nama file sudah ada");
        } catch (Exception e) {
            print("Copy file gagal ");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Path source, destination;

        print("Hai, ini adalah aplikasi sederhana untuk melakukan copy file");
        print("Silahkan masukkan file yang akan diCopy..");
        source = Paths.get(input.nextLine()).toAbsolutePath();

        if (checkFile(source)) {
            print("Silahkan masukkan folder tujuan..");
            destination = Paths.get(input.nextLine()).toAbsolutePath();

            if (checkDirectory(destination)) {
                print("Melakukan proces copy");
                copy_v1(source, destination);
            }
        }
    }
}
