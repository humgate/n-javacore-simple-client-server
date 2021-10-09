import java.io.*;
import java.net.Socket;

public class SimpleClient {
    public static void main(String[] args) {
        //Создаем сокет соединения с сервером по адресу localhost и по порту 9090
        try (Socket socket = new Socket("localhost", 8090);

             //Текстовый писатель в поток вывода сокета соединения с сервером
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

             //Буферизированный текстовый читатель из потока ввода сокета соединения с сервером
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            //отправляем сообщение серверу
            out.println("Клиент: Привет, сервер!");
            //читаем и печатаем сообщение от сервера
            in.lines().forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("Ошибка соединения: " + e.getMessage());
        }
    }
}
